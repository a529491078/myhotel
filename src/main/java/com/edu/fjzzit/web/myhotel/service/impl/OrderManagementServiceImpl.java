package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.dto.RoomOrderDetailAndRoomOrderDTO;
import com.edu.fjzzit.web.myhotel.mapper.RoomOrderDetailMapper;
import com.edu.fjzzit.web.myhotel.mapper.RoomOrderMapper;
import com.edu.fjzzit.web.myhotel.model.Page;
import com.edu.fjzzit.web.myhotel.model.RoomOrder;
import com.edu.fjzzit.web.myhotel.model.RoomOrderDetail;
import com.edu.fjzzit.web.myhotel.service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {
    @Autowired
    private RoomOrderDetailMapper roomOrderDetailMapper;
    @Autowired
    private RoomOrderMapper roomOrderMapper;
    @Override
    public Page findRoomOrderDetailAll(int pageNumber, int pageSize) {
        Page page=new Page();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        List<RoomOrderDetail> roomOrderDetailAll = roomOrderDetailMapper.findRoomOrderDetailAll(pageSize * (pageNumber - 1), pageSize);
        List<RoomOrderDetailAndRoomOrderDTO> roomInfoRoomTypeRoomPriceDTOS=new ArrayList<>();
        for (RoomOrderDetail roomOrderDetails:roomOrderDetailAll) {
            RoomOrderDetailAndRoomOrderDTO roomOrderDetailAndRoomOrderDTO=new RoomOrderDetailAndRoomOrderDTO();
            RoomOrder roomOrderByRoomOrderNum = roomOrderDetailMapper.findRoomOrderByRoomOrderNum(roomOrderDetails.getRoomOrderNum());
            roomOrderDetailAndRoomOrderDTO.setRoomOrderDetailNum(roomOrderDetails.getRoomOrderDetailNum());
            roomOrderDetailAndRoomOrderDTO.setRoomOrderNum(roomOrderDetails.getRoomOrderNum());
            roomOrderDetailAndRoomOrderDTO.setRoomTypeNum(roomOrderDetails.getRoomTypeNum());
            roomOrderDetailAndRoomOrderDTO.setRoomPriceName(roomOrderDetails.getRoomPriceName());
            roomOrderDetailAndRoomOrderDTO.setRoomPrice(roomOrderDetails.getRoomPrice());
            roomOrderDetailAndRoomOrderDTO.setRoomCount(roomOrderDetails.getRoomCount());
            roomOrderDetailAndRoomOrderDTO.setBreakfastType(roomOrderDetails.getBreakfastType());
            roomOrderDetailAndRoomOrderDTO.setCheckInTime(roomOrderDetails.getCheckInTime());
            roomOrderDetailAndRoomOrderDTO.setCheckOutTime(roomOrderDetails.getCheckOutTime());
            roomOrderDetailAndRoomOrderDTO.setRoomOrderDetailPrice(roomOrderDetails.getRoomOrderDetailPrice());
            roomOrderDetailAndRoomOrderDTO.setCustomerName(roomOrderByRoomOrderNum.getCustomerName());
            roomOrderDetailAndRoomOrderDTO.setCustomerPhone(roomOrderByRoomOrderNum.getCustomerPhone());
            roomOrderDetailAndRoomOrderDTO.setRoomOrderState(roomOrderByRoomOrderNum.getRoomOrderState());
            roomInfoRoomTypeRoomPriceDTOS.add(roomOrderDetailAndRoomOrderDTO);
        }
        page.setList(roomInfoRoomTypeRoomPriceDTOS);
        long total = roomOrderDetailMapper.selCount();
        page.setTotalNumber(total);
        page.setTotal(total%pageSize==0?total/pageSize:total/pageSize+1);
        return page;
    }

    @Override
    public int delRoomOrderDetailById(Long roomOrderDetailId) {
        return roomOrderDetailMapper.deleteByPrimaryKey(roomOrderDetailId);
    }

    @Override
    public RoomOrderDetailAndRoomOrderDTO findRoomOrderDetailById(Long roomOrderDetailId) {
        RoomOrderDetail roomOrderDetail = roomOrderDetailMapper.selectByPrimaryKey(roomOrderDetailId);
        RoomOrder roomOrder = roomOrderMapper.selectByPrimaryKey(roomOrderDetail.getRoomOrderNum());
        RoomOrderDetailAndRoomOrderDTO roomOrderDetailAndRoomOrderDTO=new RoomOrderDetailAndRoomOrderDTO();
        roomOrderDetailAndRoomOrderDTO.setRoomOrderDetailNum(roomOrderDetail.getRoomOrderDetailNum());
        roomOrderDetailAndRoomOrderDTO.setRoomOrderNum(roomOrderDetail.getRoomOrderNum());
        roomOrderDetailAndRoomOrderDTO.setRoomTypeNum(roomOrderDetail.getRoomTypeNum());
        roomOrderDetailAndRoomOrderDTO.setRoomPriceName(roomOrderDetail.getRoomPriceName());
        roomOrderDetailAndRoomOrderDTO.setRoomPrice(roomOrderDetail.getRoomPrice());
        roomOrderDetailAndRoomOrderDTO.setRoomCount(roomOrderDetail.getRoomCount());
        roomOrderDetailAndRoomOrderDTO.setBreakfastType(roomOrderDetail.getBreakfastType());
        roomOrderDetailAndRoomOrderDTO.setCheckInTime(roomOrderDetail.getCheckInTime());
        roomOrderDetailAndRoomOrderDTO.setCheckOutTime(roomOrderDetail.getCheckOutTime());
        roomOrderDetailAndRoomOrderDTO.setRoomOrderDetailPrice(roomOrderDetail.getRoomOrderDetailPrice());
        roomOrderDetailAndRoomOrderDTO.setCustomerName(roomOrder.getCustomerName());
        roomOrderDetailAndRoomOrderDTO.setCustomerPhone(roomOrder.getCustomerPhone());
        roomOrderDetailAndRoomOrderDTO.setRoomOrderState(roomOrder.getRoomOrderState());
        return roomOrderDetailAndRoomOrderDTO;
    }

    @Override
    public int updRoomOrderDetailAll(RoomOrderDetailAndRoomOrderDTO roomOrderDetailAndRoomOrderDTO) {
        return roomOrderDetailMapper.updRoomOrderDetailAll(roomOrderDetailAndRoomOrderDTO);
    }
}
