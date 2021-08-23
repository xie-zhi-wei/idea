package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户分页及多条件查询方法
     */

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);


        return new ResponseResult(true,200,"多条件组合查询广告成功",pageInfo);
    }


    /**
     * 用户登录方法
     */

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);

        if (user1 != null){
            //保存用户id和access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            //将查询出来的信息响应给前台
            Map<String,Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            //将查询出来的user，存入map响应给前台，用于登出
            //map.put("user",user1);

            return new ResponseResult(true,1,"登录成功",map);


        }else {
            return  new ResponseResult(true,400,"用户名密码错误",null);
        }

    }


    /**
     * 将该用户所具有的角色信息进行回显
     */

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> list = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",list);

    }


    /**
     *
     * 根据用户id分配角色
     */

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功",null);
    }


    /**
     * 获取用户权限，进行菜单动态展示
     */

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //2.获取存入session的token
        HttpSession session = request.getSession();
        String session_token = (String)session.getAttribute("access_token");
       // String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断
        if (header_token.equals(session_token)){
            //4.获取session中的userId
            // Integer user_id = (Integer) session.getAttribute("user_id");
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            //调用service进行菜单查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;

        }else {
            return new ResponseResult(false,400,"查询菜单信息失败",null);
        }

    }


    /**
     * 根据id修改用户状态信息
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){

//        userService.updateUserStatus(id,status);
//        return new ResponseResult(true,200,"用户张贴修改成功",status);

        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",status);
        return responseResult;
    }



}
