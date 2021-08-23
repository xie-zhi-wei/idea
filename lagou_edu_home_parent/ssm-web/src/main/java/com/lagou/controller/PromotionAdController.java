package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 分页广告查询功能
     */

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true,200,"分页查询成功",pageInfo);

    }


    /**
     * 文件图片上传接口
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
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
     * 修改广告上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){

        promotionAdService.updatePromotionAdStatus(id,status);
        Map<String,Integer> map = new HashMap<>();

        map.put("status",status);

        return new ResponseResult(true,200,"修改广告上下线状态成功",map);

    }

    /**
     *新建广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新建广告成功",null);
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);
        }

    }

    /**
     * 根据id查询广告信息并回显
     *
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"根据id查询广告信息并回显成功",promotionAd);
    }


}
