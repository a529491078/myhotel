package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = "string",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "string",required = true)
    })
    /**
     * 登录模块
     */
    public ResultJson login(String userName,String password){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String sessionId=subject.getSession().getId().toString();//获取sessionId，实现授权
            HashMap<String,String> map=new HashMap<>();
            map.put("token",sessionId);
            return new ResultJson("200","登录成功!",map);
        }catch(UnknownAccountException e){
            //账户不存在
            return new ResultJson("400","用户名或密码错误!",null);
        }catch(DisabledAccountException e){
            //账户被禁用
            return new ResultJson("400","该账户已被禁用!",null);
        }catch(IncorrectCredentialsException e){
            //账户被禁用
            return new ResultJson("400","用户名或密码错误!",null);
        }catch (Exception e){
            return new ResultJson("400","未知错误，请联系管理员!",null);
        }
    }

    @GetMapping("/need_login")
    public ResultJson needLogin(){
        return new ResultJson("400","请先登陆!",null);
    }

    @GetMapping("/unauth")
    public ResultJson unauth(){
        return new ResultJson("400","您不具备相应权限!",null);
    }

    @GetMapping("/add_user")
    @ApiOperation("添加用户")
    @RequiresRoles(value={"admin"},logical = Logical.OR)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "String",required = true,paramType = "header"),
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",required = true),
            @ApiImplicitParam(name = "roleName",value = "角色名",dataType = "String",required = true)
    })
    public ResultJson addUser(String username,String password,String roleName){
        try {
            userService.addUser(username, password, roleName);
            return new ResultJson("200","添加用户成功!",null);
        }catch(Exception e){
            return new ResultJson("400","添加用户失败!",null);
        }
    }
}
