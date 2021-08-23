package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();

        return list;
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setCreatedTime(new Date());
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        //封装信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setCreatedTime(new Date());
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("12346665888");
        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    @Override
    public void deleteResourceCategory(Integer id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }




}
