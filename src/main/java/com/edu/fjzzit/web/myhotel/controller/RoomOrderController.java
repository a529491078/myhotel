package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;
import com.edu.fjzzit.web.myhotel.dto.RoomOrderDTO;
import com.edu.fjzzit.web.myhotel.model.MyException;
import com.edu.fjzzit.web.myhotel.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room_order")
@Api(tags = "订房管理")
public class RoomOrderController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/get_FreeRoom")
    @ApiOperation("查询空房")
    @RequiresAuthentication
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "checkInDate",value = "入住时间",dataType = "string",required = true),
            @ApiImplicitParam(name = "checkOutDate",value = "退房时间",dataType = "string",required = true),
    })
    public ResultJson getFreeRoom(String checkInDate,String checkOutDate){
        try{
            List<FreeRoomDTO> findFreeRoomDTOList=roomService.findFreeRoom(checkInDate,checkOutDate);
            return new ResultJson("200","查询成功!",findFreeRoomDTOList);
        }catch(Exception e){
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/reserve_room")
    @ApiOperation("订房")
    @RequiresAuthentication
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson reserveRoom(@RequestBody RoomOrderDTO roomOrderDTO){
        try{
            Long roomOrderNum=roomService.reserveRoom(roomOrderDTO);

            return new ResultJson("200","预定成功!","您的订单号为："+roomOrderNum);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","预定失败!",null);
        }
    }

    @PostMapping("/cancelOrder")
    @ApiOperation("取消订单")
    @RequiresAuthentication
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "orderNum",value = "订单号",dataType = "string",required = true)
    })
    public ResultJson cancellationOfOrder(Long orderNum){
        try{
            roomService.cancelOrder(orderNum);
            return new ResultJson("200","取消成功!",null);
        }catch(MyException e){
            return new ResultJson(e.getErrorCode(),e.getDescription(),null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","取消失败!",null);
        }
    }

}
