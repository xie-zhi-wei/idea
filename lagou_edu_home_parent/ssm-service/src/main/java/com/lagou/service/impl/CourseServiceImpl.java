package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     *  多条件列表查询
     * @param courseVo
     * @return
     */
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {

        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }

    /**
     * 添加课程及讲师信息
     * @param courseVo
     */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        //调用工具类API
        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存添加课程
        courseMapper.saveCourse(course);

        //获取新插入数据的id
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setCourseId(id);
        teacher.setIsDel(0);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);

    }

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    @Override
    public CourseVo findCourseById(Integer id) {
        CourseVo course = courseMapper.findCourseById(id);
        return course;
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        //修改2信息
        courseMapper.updateCourse(course);

        //封装教师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全信息
        teacher.setId(course.getId());
        teacher.setUpdateTime(date);

        //更新信息
        courseMapper.updateTeacher(teacher);

    }

    @Override
    public void updateCourseStatus(int id, int status) {

        //封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }
}
