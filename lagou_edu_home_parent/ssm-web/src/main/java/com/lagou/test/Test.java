package com.lagou.test;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.service.CourseService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


public class Test {

    @Autowired
    private CourseService courseService;

    @org.junit.Test
    public void test(){

        CourseVo courseVo = new CourseVo();
        courseVo.setCourseName("Vue.js 3.0 核心源码解析");
        courseVo.setStatus(1);
        List<Course> list = courseService.findCourseByCondition(courseVo);
        System.out.println(list);
    }


}
