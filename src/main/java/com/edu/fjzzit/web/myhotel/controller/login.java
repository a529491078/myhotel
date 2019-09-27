package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;

public class login {
    public ResultJson login(String name,String password){
        return new ResultJson("200","登录成功",null);
    }
}
