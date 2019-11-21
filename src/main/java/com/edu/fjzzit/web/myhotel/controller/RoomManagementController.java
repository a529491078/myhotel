package com.edu.fjzzit.web.myhotel.controller;

import com.edu.fjzzit.web.myhotel.config.ResultJson;
import com.edu.fjzzit.web.myhotel.dto.RoomPriceNameAndRoomTypeNumDTO;
import com.edu.fjzzit.web.myhotel.dto.RoomTypeAndRoomPriceDTO;
import com.edu.fjzzit.web.myhotel.model.Page;
import com.edu.fjzzit.web.myhotel.model.RoomInfo;
import com.edu.fjzzit.web.myhotel.service.RoomManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room_management")
@Api(tags = "客房管理")
public class RoomManagementController {
    @Autowired
    private RoomManagementService roomManagementService;

    @PostMapping("/get_room_info_page")
    @ApiOperation("查询所有客房信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson fingRoomInfoAllByPage(@RequestParam(defaultValue="1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize){
        try{
            Page page = roomManagementService.findRoomInfoAll(pageNum, pageSize);
            return new ResultJson("200","查询成功!",page);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/get_room_type_price_page")
    @ApiOperation("查询所有客房套餐信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson findRoomTypeAndRoomPriceAll(@RequestParam(defaultValue="1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize){
        try{
            Page page = roomManagementService.findRoomTypeAndRoomPriceAll(pageNum,pageSize);
            return new ResultJson("200","查询成功!",page);
        }catch(Exception e){
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/get_roomPriceName_roomTypeNum")
    @ApiOperation("查询所有客房类型名称和客房类型ID")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson findRoomPriceNameAll(){
        try{
            List<RoomPriceNameAndRoomTypeNumDTO> roomPriceNameAll = roomManagementService.findRoomPriceNameAll();
            return new ResultJson("200","查询成功!",roomPriceNameAll);
        }catch(Exception e){
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/ins_room_info")
    @ApiOperation("添加客房信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson insRoomInfoById(@RequestBody RoomInfo roomInfo){
        System.out.println(roomInfo);
        try{
            roomManagementService.insRoomInfoById(roomInfo);
            return new ResultJson("200","添加成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","添加失败!",null);
        }
    }

    @PostMapping("/ins_room_type_price_info")
    @ApiOperation("添加客房套餐信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson insRoomTypeAndRoomPrice(@RequestBody RoomTypeAndRoomPriceDTO roomTypeAndRoomPriceDTO){
        System.out.println(roomTypeAndRoomPriceDTO);
        try{
            roomManagementService.insRoomTypeAndRoomPrice(roomTypeAndRoomPriceDTO);
            return new ResultJson("200","添加成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","添加失败!",null);
        }
    }

    @PostMapping("/get_room_info_byid")
    @ApiOperation("根据ID查询客房信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomId",value = "客房ID",dataType = "string",required = true),
    })
    public ResultJson findRoomInfoById(Integer roomId){
        try{
            RoomInfo roomInfoById = roomManagementService.findRoomInfoById(roomId);
            return new ResultJson("200","查询成功!",roomInfoById);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","查询失败!",null);
        }
    }

    @PostMapping("/get_room_type_price_byid")
    @ApiOperation("根据ID查询客房套餐信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomTypeNum",value = "客房ID",dataType = "string",required = true),
    })
    public ResultJson findRoomTypeAndRoomPriceById(Long roomTypeNum){
        try{
            RoomTypeAndRoomPriceDTO roomTypeAndRoomPriceById = roomManagementService.findRoomTypeAndRoomPriceById(roomTypeNum);
            return new ResultJson("200","查询成功!",roomTypeAndRoomPriceById);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","查询失败!",null);
        }
    }



    @PostMapping("/upd_room_info_byid")
    @ApiOperation("根据ID修改客房信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson updRoomInfoAll(@RequestBody RoomInfo roomInfo){
        try{
            roomManagementService.updRoomInfoAll(roomInfo);
            return new ResultJson("200","修改成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","修改失败!",null);
        }
    }

    @PostMapping("/upd_room_type_price_byid")
    @ApiOperation("根据ID修改客房套餐信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
    })
    public ResultJson updRoomTypeAndRoomPriceAll(@RequestBody RoomTypeAndRoomPriceDTO roomTypeAndRoomPriceDTO){
        try{
            roomManagementService.updRoomTypeAndRoomPriceAll(roomTypeAndRoomPriceDTO);
            return new ResultJson("200","修改成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","修改失败!",null);
        }
    }

    @PostMapping("/del_room_info_byid")
    @ApiOperation("根据ID删除客房信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomId",value = "客房ID",dataType = "string",required = true),
    })
    public ResultJson delRoomInfoById(Integer roomId){
        try{
            roomManagementService.delRoomInfoById(roomId);
            return new ResultJson("200","删除成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","删除失败!",null);
        }
    }

    @PostMapping("/del_room_type_price_byid")
    @ApiOperation("根据ID删除客房套餐信息")
    @RequiresRoles(value={"admin"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "鉴权Token",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "roomTypeNum",value = "客房ID",dataType = "string",required = true),
    })
    public ResultJson delRoomTypeAndRoomPriceById(Long roomTypeNum){
        try{
            roomManagementService.delRoomTypeAndRoomPriceById(roomTypeNum);
            return new ResultJson("200","删除成功!",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResultJson("400","删除失败!",null);
        }
    }
}
