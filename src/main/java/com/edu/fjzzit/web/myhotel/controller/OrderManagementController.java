package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.dto.RoomOrderDetailAndRoomOrderDTO;
import com.edu.fjzzit.web.myhotel.model.Page;
import com.edu.fjzzit.web.myhotel.service.OrderManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order_management")
@Api(tags = "订单管理")
public class OrderManagementController  {
    @Autowired
    private OrderManagementService orderManagementService;

    @PostMapping("/get_room_order_page")
    @ApiOperation("查询所有订单信息(分页)")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson fingRoomInfoAllByPage(@RequestParam(defaultValue="1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize){
        try{
            Page page = orderManagementService.findRoomOrderDetailAll(pageNum, pageSize);
            return new ResultJson("200","查询成功!",page);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/get_room_order_byid")
    @ApiOperation("根据主键查询订单信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomOrderDetailNum",value = "订单详情主键",dataType = "string",required = true),
    })
    public ResultJson findRoomOrderDetailById(Long roomOrderDetailNum){
        try{
            RoomOrderDetailAndRoomOrderDTO roomOrderDetailById = orderManagementService.findRoomOrderDetailById(roomOrderDetailNum);
            return new ResultJson("200","查询成功!",roomOrderDetailById);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/upd_room_order_byid")
    @ApiOperation("根据主键修改订单信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson updRoomOrderDetailAll(@RequestBody RoomOrderDetailAndRoomOrderDTO roomOrderDetailAndRoomOrderDTO){
        try{
            orderManagementService.updRoomOrderDetailAll(roomOrderDetailAndRoomOrderDTO);
            return new ResultJson("200","修改成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","修改失败!",null);
        }
    }
    @PostMapping("/del_room_order_byid")
    @ApiOperation("根据主键删除订单信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomOrderDetailNum",value = "订单详情主键",dataType = "string",required = true),
    })
    public ResultJson delRoomOrderDetailById(Long roomOrderDetailNum) {
        try {
            orderManagementService.delRoomOrderDetailById(roomOrderDetailNum);
            return new ResultJson("200", "删除成功!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson("400", "删除失败!", null);
        }
    }
}
