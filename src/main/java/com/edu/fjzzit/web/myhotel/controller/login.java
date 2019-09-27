package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class login {

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = "string",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "string",required = true)
    })
    public ResultJson login(String userName,String password){
        return new ResultJson("200","登录成功",null);
    }
}
