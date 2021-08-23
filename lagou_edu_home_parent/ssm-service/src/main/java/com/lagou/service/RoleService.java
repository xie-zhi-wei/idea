package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {


    /**
     * 查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);



    /**
     * 根据角色id查询到关联的菜单id[]
     */
    public List<Integer> findMenuByRoleId(int id);

    /**
     * 为角色关联菜单
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);


    /**
     * 删除角色
     */
    public void deleteRole(Integer roleId);

    /**
     *获取当前角色拥有的 资源信息(包括资源分类信息）
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);


    /**
     * 为角色重新分配资源信息
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);

}
