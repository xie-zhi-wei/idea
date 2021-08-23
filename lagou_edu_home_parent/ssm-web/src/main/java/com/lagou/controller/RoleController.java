package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     */

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        return new ResponseResult(true,200,"查询所有角色成功",allRole);

    }

    /**
     * 查询所有父子级菜单
     *
     */
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        //-1表示查询所有的父级菜单，但是后面还有二次查询子级菜单进行数据封装封装
        List<Menu> parentMenuList = menuService.findSubMenuListByPid(-1);

        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",parentMenuList);

        return new ResponseResult(true,200,"父子级菜单查询成功",map);
    }


    /**
     * 根据角色id信息查询关联菜单id
     */

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> integerList = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id信息查询关联的菜单id成功",integerList);

    }

    /**
     * 给角色分配菜单
     */

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.RoleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"角色分配菜单成功","");
    }


    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功","");
    }

    /**
     * 获取当前角色拥有的资源信息（包括对应的资源分类信息）
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> resourceListByRoleId = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"获取当前角色拥有的资源信息（包括对应的资源分类信息）成功",resourceListByRoleId);
    }


    /**
     * 重新给角色分配资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);

        return new ResponseResult(true,200,"重新给角色分配资源成功",null);

    }

}
