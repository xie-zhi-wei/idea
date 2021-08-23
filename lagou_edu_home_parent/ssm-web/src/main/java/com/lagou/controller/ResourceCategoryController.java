package com.lagou.controller;


import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询查询资源分类信息列表
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true,200,"查询查询资源分类信息列表成功",list);
    }


    /**
     * 保存&xiu修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if (resourceCategory.getId() == null){
            resourceCategoryService.saveResourceCategory(resourceCategory);

            return new ResponseResult(true,200,"保存资源分类信息成功",null);
        }else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改资源分类信息成功",null);
        }


    }

    /**
     * 根据id删除资源分类信息
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        return  new ResponseResult(true,200,"根据id删除对应的资源分类成功",null);
    }




}
