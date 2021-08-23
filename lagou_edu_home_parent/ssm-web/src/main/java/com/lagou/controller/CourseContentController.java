package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 根据课程id查询关联的章节信息和关联的课时信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true,200,"查询对应的课程章节和课时信息成功",list);

    }


    /**
     * 回显章节对应的课程信息
     */

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam  Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        Map<String,Object> map = new HashMap<>();

        map.put("courseName",course.getCourseName());
        map.put("id",course.getId());

        return new ResponseResult(true,200,"回显章节对应的课程名字成功",map);

    }

    /**
     * 新建章节信息
     */

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        }else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"更新章节成功",null);
        }


    }

    /**
     * 修改章节状态
     */

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"修改章节状态成功",map);

    }


    /**
     * 添加课时
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        if (courseLesson.getId() == null){
            courseContentService.saveLesson(courseLesson);

            return new ResponseResult(true,200,"新建课时信息成功",null);
        }else {

            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"修改课时信息成功",null);
        }


    }

}
