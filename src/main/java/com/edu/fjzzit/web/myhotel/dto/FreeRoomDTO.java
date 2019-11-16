package com.edu.fjzzit.web.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeRoomDTO {
    private String roomTypeName;//房间类型名称
    private Integer freeCount;//空房数
    private String roomPriceName;//套餐名称
    private String bedType;//床型
    private String breakfastType;//早餐类型
    private int roomPrice;//房价

}
