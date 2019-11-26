package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.model.MyException;
import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.service.CheckInInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CheckInInfoService checkInInfoService;

    @RequestMapping("{page}")
    public String main(@PathVariable String page) {
        return "/view/admin/"+page;
    }

    @RequestMapping("/login_list")
    @ResponseBody
    public ResultJson adminLogin(String adminName, String adminPassword,HttpSession httpSession){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(adminName, adminPassword);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
            httpSession.setAttribute("admin",user.getUserName());
            return new ResultJson("200","登录成功!",null);
        }catch(Exception e){
            return new ResultJson("500","登录失败!",null);
        }
    }

    @RequestMapping("/get_main")
    @RequiresUser
    public String get_main(){
        return "/view/admin/main";
    }

    @RequestMapping("/checkIn")
    @ResponseBody
    @RequiresUser
    public ResultJson checkIn(String customerName, String customerPhone, String customerId){
        try {
            String s = checkInInfoService.checkIn(customerName, customerPhone, customerId);
            return new ResultJson("200","入住成功",s);
        } catch(MyException e){
            return new ResultJson(e.getErrorCode(),e.getDescription(),null);
        }catch (Exception e) {
            return new ResultJson("400","入住失败",null);
        }
    }
}
