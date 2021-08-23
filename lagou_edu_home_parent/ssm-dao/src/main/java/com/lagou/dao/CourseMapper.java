package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 多条件列表查迅
     *
     */

    public List<Course> findCourseByCondition(CourseVo courseVo);


    /**
     * 新增课程信息
     */
    public void saveCourse(Course course);

    /**
     * 新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据id查询课程信息
     *
     */
    public CourseVo findCourseById(Integer id);

    /**
     * 更新课程信息
     */
    public void updateCourse(Course course);

    /**
     * 更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     */
    public void updateCourseStatus(Course course);


}
