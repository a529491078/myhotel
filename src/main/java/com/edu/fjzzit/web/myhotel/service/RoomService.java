package com.edu.fjzzit.web.myhotel.service;

import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;

import java.util.List;

public interface RoomService {

    /**
     * 查找空房
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    List<FreeRoomDTO> findFreeRoom(String checkInDate, String checkOutDate);
}
