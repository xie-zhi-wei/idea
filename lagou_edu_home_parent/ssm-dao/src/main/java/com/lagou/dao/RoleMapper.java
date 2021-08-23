package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询到关联的菜单id[]
     */
    public List<Integer> findMenuByRoleId(int id);


    /**
     * 根据roleid清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /**
     * 为角色分配菜单
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);


    /**
     * 删除角色
     */
    public void deleteRole(Integer roleId);



    /**
     * 根据角色id查询当前角色所拥有的资源信息
     */

    public List<Resource> findResourceByRoleId(Integer roleId);

    /**
     * 查询当前角色拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);


    /**
     * 清空当前角色对应的资源信息
     */
    public void deleteRoleResourceById(Integer roleId);

    /**
     * 为角色重新分配资源信息
     */
    public void roleContextResource(RoleResourceRelation roleResourceRelation);


}
