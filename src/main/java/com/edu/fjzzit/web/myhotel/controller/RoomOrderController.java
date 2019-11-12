package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;
import com.edu.fjzzit.web.myhotel.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room_order")
@Api(tags = "订房管理")
public class RoomOrderController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/getFreeRoom")
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

    public ResultJson reserveRoom(){
        return null;
    }

}
