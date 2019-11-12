package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;
import com.edu.fjzzit.web.myhotel.mapper.FreeRoomCalendarMapper;
import com.edu.fjzzit.web.myhotel.mapper.RoomPriceMapper;
import com.edu.fjzzit.web.myhotel.mapper.RoomTypeMapper;
import com.edu.fjzzit.web.myhotel.model.FreeRoomCalendar;
import com.edu.fjzzit.web.myhotel.model.RoomPrice;
import com.edu.fjzzit.web.myhotel.model.RoomType;
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
}
