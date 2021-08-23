package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //@RequestBody,把前台传过来的json对象转换为pojo对象，然后封装进CourseVo pojo实体类
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo) {

        //调用接口
        List<Course> list = courseService.findCourseByCondition(courseVo);

        return new ResponseResult(true, 200, "响应成功", list);

    }


    /**
     * 文件图片上传接口
     */
    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2.获取项目的部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        //截取文件名称 // D:\apache-tomcat-8.5.56\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //3.获取原文件名
        //lagou.jpg
        String filename = file.getOriginalFilename();

        //4.生成新文件名
        //时间搓  1234321.jpg
        String newFileName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));



        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath,newFileName);

        //6.判断upload目录是否存在
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();

            System.out.println("创建目录" + filePath);
        }

        //7.调用方法进行图片上传
        file.transferTo(filePath);

        //将文件名和文件路径进行返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("filename",newFileName);
        map.put("filePath","http://localhost:8080/upload/" +newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }


    /**
     * 新增课程信息和讲师信息
     * 新增课程和修改课程需要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        if (courseVo.getId() != null){
           courseService.updateCourseOrTeacher(courseVo);

            return new ResponseResult(true,200,"修改课程信息成功",null);

        }else {
            //调用service
            courseService.saveCourseOrTeacher(courseVo);

            return new ResponseResult(true,200,"添加课程信息成功",null);
        }

    }


    /**
     * 根据id查询课程信息
     */

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo course = courseService.findCourseById(id);
        return new ResponseResult(true,200,"响应成功",course);
    }


    /**
     * 修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){

        courseService.updateCourseStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"修改课程状态成功",map);

    }


}