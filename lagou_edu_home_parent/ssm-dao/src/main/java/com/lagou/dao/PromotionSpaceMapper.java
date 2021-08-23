package com.lagou.dao;

import com.lagou.domain.PromotionSpace;
import sun.awt.SunHints;

import java.util.List;

public interface PromotionSpaceMapper {

    /**
     * 获取所有广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位方法
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id回显广告位方法
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /**
     * 根据id修改广告位的方法
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
