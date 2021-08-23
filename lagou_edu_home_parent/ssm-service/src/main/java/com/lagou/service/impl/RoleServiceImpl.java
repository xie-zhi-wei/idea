package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }


    @Override
    public List<Integer> findMenuByRoleId(int id) {
        List<Integer> integerList = roleMapper.findMenuByRoleId(id);
        return integerList;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        //清空关联信息
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        Role_menu_relation role_menu_relation = new Role_menu_relation();

        for (Integer id : roleMenuVo.getMenuIdList()) {

            //补全信息
            role_menu_relation.setMenuId(id);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.roleContextMenu(role_menu_relation);

        }

    }

    @Override
    public void deleteRole(Integer roleId) {
        //删除角色关联的菜单、
        roleMapper.deleteRoleContextMenu(roleId);

        //删除角色
        roleMapper.deleteRole(roleId);
    }



    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        //查询角色id对应的资源信息
        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);

        //查询查询当前角色拥有的资源分类信息
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(roleId);
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            //把资源信息封装进集合里面
            resourceCategory.setResourceList(resourceList);
        }

        return resourceCategoryList;
    }



    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        //删除原有角色的资源信息
        roleMapper.deleteRoleResourceById(roleResourceVo.getRoleId());
        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
        for (Integer id : roleResourceVo.getResourceIdList()) {
            //补全信息

            roleResourceRelation.setResourceId(id);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setCreatedTime(new Date());
            roleResourceRelation.setUpdatedTime(new Date());
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            roleMapper.roleContextResource(roleResourceRelation);
            
        }




    }
}
