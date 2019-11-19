package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;
import com.edu.fjzzit.web.myhotel.dto.RoomOrderDTO;
import com.edu.fjzzit.web.myhotel.dto.RoomOrderDetailDTO;
import com.edu.fjzzit.web.myhotel.mapper.*;
import com.edu.fjzzit.web.myhotel.model.FreeRoomCalendar;
import com.edu.fjzzit.web.myhotel.model.RoomOrder;
import com.edu.fjzzit.web.myhotel.model.RoomOrderDetail;
import com.edu.fjzzit.web.myhotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServicelmpl implements RoomService {

    @Autowired
    private FreeRoomCalendarMapper freeRoomCalendarMapper;

    @Autowired
    private RoomPriceMapper roomPriceMapper;

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private RoomOrderMapper roomOrderMapper;

    @Autowired
    private RoomOrderDetailMapper roomOrderDetailMapper;

    /**
     * 查找空房
     *
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    @Override
    public List<FreeRoomDTO> findFreeRoom(String checkInDate, String checkOutDate) {

        try {

            List<FreeRoomDTO> freeRoomDTOList = new ArrayList<>();
            FreeRoomDTO freeRoomDTO;

            List<FreeRoomCalendar> freeRoomCalendarList = freeRoomCalendarMapper.selectByCheckDate(checkInDate, checkOutDate);
            for (FreeRoomCalendar frc : freeRoomCalendarList
            ) {
                Integer freeCount = frc.getFreeCount();//获取最小空房数
                Long roomTypeNum = frc.getRoomTypeNum();//获取房间类型流水号

//                System.out.println("freeCount->"+freeCount);
//                System.out.println("roomTypeNum->"+roomTypeNum);

                List<FreeRoomDTO> freeRoomDTOS = roomPriceMapper.queryFreeRoomDTOByRoomTypeNum(roomTypeNum);
                for (FreeRoomDTO frd : freeRoomDTOS
                ) {
                    freeRoomDTO = new FreeRoomDTO();

                    String roomTypeName=frd.getRoomTypeName();
                    String breakfastType = frd.getBreakfastType();
                    String roomPriceName = frd.getRoomPriceName();
                    String bedType = frd.getBedType();
                    Integer roomPrice = frd.getRoomPrice();

                    freeRoomDTO.setRoomTypeName(roomTypeName);
                    freeRoomDTO.setBreakfastType(breakfastType);
                    freeRoomDTO.setRoomPriceName(roomPriceName);
                    freeRoomDTO.setBedType(bedType);
                    freeRoomDTO.setRoomPrice(roomPrice);
                    freeRoomDTO.setFreeCount(freeCount);

//                    System.out.println("roomTypeName->"+roomTypeName);
//                    System.out.println("breakfastType->"+breakfastType);
//                    System.out.println("roomPriceName->"+roomPriceName);
//                    System.out.println("bedType->"+bedType);
//                    System.out.println("roomPrice->"+roomPrice);

                    freeRoomDTOList.add(freeRoomDTO);
                }
            }

            return freeRoomDTOList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 订房
     * @param roomOrderDTO
     * @return
     */
    @Override
    public Long reserveRoom(RoomOrderDTO roomOrderDTO) {
        RoomOrder roomOrder;
        RoomOrderDetail roomOrderDetail;

        String customerName=roomOrderDTO.getCustomerName();
        String customerPhone= roomOrderDTO.getCustomerPhone();

        roomOrder=new RoomOrder();
        roomOrder.setCustomerName(customerName);
        roomOrder.setCustomerPhone(customerPhone);
        roomOrder.setRoomOrderState(Byte.parseByte("0"));
        roomOrderMapper.insert(roomOrder);//插入订单
        //获取生成的订单的流水号
        Long roomOrderNum=roomOrderMapper.selectRoomOrderNumByCustomerName(customerName);

        List<RoomOrderDetailDTO> roomOrderDetailDTOList=roomOrderDTO.getRoomOrderDetailDTOList();
        for (RoomOrderDetailDTO rddDTO:roomOrderDetailDTOList
             ) {
            String roomTypeName=rddDTO.getRoomTypeName();
            String roomPriceName=rddDTO.getRoomPriceName();
            String bedType=roomPriceName.substring(roomTypeName.length());//获取床型
            Long roomTypeNum=roomTypeMapper.getRoomTypeNumByRoomTypeNameAndBedType(roomTypeName,bedType);//1

            Integer roomPrice=rddDTO.getRoomPrice();
            String breakfastType=rddDTO.getBreakfastType();
            Integer roomCount=rddDTO.getRoomCount();
            String checkInDate=rddDTO.getCheckInDate();
            String checkOutDate=rddDTO.getCheckOutDate();
            Integer roomOrderDetailPrice=roomPrice*roomCount;//计算总价

            roomOrderDetail=new RoomOrderDetail();
            roomOrderDetail.setRoomOrderNum(roomOrderNum);
            roomOrderDetail.setRoomTypeNum(roomTypeNum);
            roomOrderDetail.setRoomPriceName(roomPriceName);
            roomOrderDetail.setRoomPrice(roomPrice);
            roomOrderDetail.setBreakfastType(breakfastType);
            roomOrderDetail.setRoomCount(roomCount);
            roomOrderDetail.setCheckInTime(checkInDate);
            roomOrderDetail.setCheckOutTime(checkOutDate);
            roomOrderDetail.setRoomOrderDetailPrice(roomOrderDetailPrice);

            roomOrderDetailMapper.insert(roomOrderDetail);//插入订单详情
            updateFreeCount(roomTypeNum,checkInDate,checkOutDate,roomCount);//更新空房数,保持数据正确性和一致性
        }

        return roomOrderNum;
    }

    /**
     * 更新空房数(成功订房，更新房间空房数)
     * @param roomTypeNum
     * @param checkInDate
     * @param checkOutCheck
     */
    private void updateFreeCount(Long roomTypeNum,String checkInDate,String checkOutCheck,Integer count){
        freeRoomCalendarMapper.updateFreeCount(roomTypeNum,checkInDate,checkOutCheck,count);
    }
}
