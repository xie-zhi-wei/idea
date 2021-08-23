package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuListVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);


        return menuList;
    }

    @Override
    public PageInfo findAllMenu(MenuListVo menuListVo) {

        PageHelper.startPage(menuListVo.getCurrentPage(),menuListVo.getPageSize());

        List<Menu> list = menuMapper.findAllMenu();

        PageInfo<Menu> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
