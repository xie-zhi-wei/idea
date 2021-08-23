package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return list;
    }



    @Override
    public Course findCourseByCourseId(Integer id) {
        Course course = courseContentMapper.findCourseByCourseId(id);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {

        //补全信息
        courseSection.setCreateTime(new Date());
        courseSection.setUpdateTime(new Date());
       // courseSection.setStatus(0);

        courseContentMapper.saveSection(courseSection);

    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);

    }

    @Override
    public void updateSectionStatus(int id, int status) {

        //补全信息
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setId(id);
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        courseLesson.setCreateTime(new Date());
        courseLesson.setUpdateTime(new Date());
        courseLesson.setIsDel(0);
        courseLesson.setStatus(0);

        courseContentMapper.saveLesson(courseLesson);
    }

    @Override
    public void updateLesson(CourseLesson courseLesson) {
        //补全信息
        courseLesson.setUpdateTime(new Date());
        courseContentMapper.updateLesson(courseLesson);

    }
}
