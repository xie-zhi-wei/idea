package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {


    /**
     * 根据课程id查询关联的章节信息和关联的课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);


    /**
     * 回显章节对应的课程信息
     */
    public Course findCourseByCourseId(Integer id);

    /**
     * 新建章节信息
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 更新
     * @param courseSection
     */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(int id,int status);


    /**
     * 新建课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);
}
