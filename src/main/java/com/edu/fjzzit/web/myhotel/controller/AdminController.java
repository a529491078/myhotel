package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.model.Page;
import com.edu.fjzzit.web.myhotel.service.OrderManagementService;
import com.edu.fjzzit.web.myhotel.service.RoomManagementService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderManagementService orderManagementService;

    @Autowired
    private RoomManagementService roomManagementService;

    @RequestMapping("{page}")
    public String main(@PathVariable String page) {
        return "/view/admin/"+page;
    }

    @RequestMapping("/login_list")
    @ResponseBody
    public ResultJson adminLogin(String adminName, String adminPassword){
        String dates=null;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(adminName, adminPassword);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            dates="success";
            return new ResultJson("200","登录成功!",dates);
        }catch(Exception e){
            return new ResultJson("500","登录失败!",null);
        }
    }

    @RequestMapping("/get_room_order_page")
    public ModelAndView fingOrderInfoAllByPage(@RequestParam(defaultValue="1")Integer pageNumber, @RequestParam(defaultValue="5")Integer pageSize){
        try{
            Page page = orderManagementService.findRoomOrderDetailAll(pageNumber, pageSize);
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("page",page);
            modelAndView.setViewName("/view/admin/order_management_page");
            return modelAndView;
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping("/get_room_info_page")
    public ModelAndView fingRoomInfoAllByPage(@RequestParam(defaultValue="1")Integer pageNumber, @RequestParam(defaultValue="5")Integer pageSize){
        try{
            Page page = roomManagementService.findRoomInfoAll(pageNumber, pageSize);
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("page",page);
            modelAndView.setViewName("/view/admin/room_management_page");
            return modelAndView;
        }catch(Exception e){
            return null;
        }
    }
}
