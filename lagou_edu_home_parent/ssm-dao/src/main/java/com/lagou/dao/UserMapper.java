package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页及多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user);


    /**
     * 根据用户id清空关联中间表
     * @param userId
     */
    void deleteUserContextRole(Integer userId);


    /**
     * 分配角色
     * @param user_role_relation
     */
    void userContextRole(User_Role_relation user_role_relation);


    /**
     * 1.根据用户id查询关联的角色信息 会有多个角色
     * */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 2.根据角色ID，查询角色所拥有的顶级菜单（-1）
     *
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);


    /**
     * 3.根据父菜单Pid查询子菜单
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 4.获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

    /**
     * 5.用户状态设置
     */
    public void updateUserStatus(@Param("id") int id, @Param("status")String status);
    
    public void updateUserStatus(@Param("id") int id, @Param("status")String status);


}
