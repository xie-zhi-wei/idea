package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuListVo;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有父子级菜单
     */

    public List<Menu> findSubMenuListByPid(int pid);


    /**
     * 查询所有菜单列表信息
     */
    public PageInfo findAllMenu(MenuListVo menuListVo);

    /**
     * 回显菜单想你想
     * @param id
     * @return
     */
    Menu findMenuById(Integer id);
}
