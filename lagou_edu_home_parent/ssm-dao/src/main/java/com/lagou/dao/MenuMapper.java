package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * 查询所有父子级菜单
     */

    public List<Menu> findSubMenuListByPid(int pid);


    /**
     * 查询所有菜单列表信息
     */
    public List<Menu> findAllMenu();

    /**
     * 根据id回显菜单信息
     * @param id
     * @return
     */
    Menu findMenuById(Integer id);
}
