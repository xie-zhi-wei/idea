package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuListVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuListVo menuListVo){

        PageInfo pageInfo = menuService.findAllMenu(menuListVo);

        return new ResponseResult(true,200,"查询所有列表菜单成功",pageInfo);

    }


    /**
     * 添加菜单&回显菜单
     */

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //根据当前传过来的id判断是否为添加操作还是更新操作 判断id是值是否为-1
        if (id == -1){
            //添加 回显信息中不需要menu信息，只需要父级菜单信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"添加菜单回显成功",map);
        }else {

            //修改操作 回显所有menu信息
            Menu menu = menuService.findMenuById(id);

            //添加 回显信息中不需要menu信息，只需要父级菜单信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"修改菜单回显信息成功",map);

        }


    }


}
