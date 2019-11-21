package com.edu.fjzzit.web.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPriceNameAndRoomTypeNumDTO {
    private long roomTypeNum;
    private String roomPriceName;
}
