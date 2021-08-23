package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(list);


        return pageInfo;
    }


    @Override
    public User login(User user) throws Exception {

        //调用mapper方法，user2数据库查询出来的对象，包含了密文密码
        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }

    }


    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {

        //清空中间表
        userMapper.deleteUserContextRole(userVo.getUserId());

        for (Integer userId : userVo.getRoleIdList()) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(userId);
            user_role_relation.setCreatedTime(new Date());
            user_role_relation.setUpdatedTime(new Date());
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);

        }


    }

    /**
     *  获取用户权限，进行菜单动态展示
     */

    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        //1.根据用户id,获取当前用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        //2.获取角色id 保存到list集合
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            //存储遍历得到的每个角色的id
            roleIds.add(role.getId());
        }
        //3.根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            //遍历获取当前父菜单关联的子菜单
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            //把查询到子菜单集合封装当前父菜单的集合
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);




        return new ResponseResult(true,1,"success",map);
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        userMapper.updateUserStatus(id,status);
    }
}
