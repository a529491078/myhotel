package com.edu.fjzzit.web.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomOrderDetailDTO {
    private String roomTypeName;
    private String roomPriceName;
    private String breakfastType;
    private Integer roomPrice;
    private Integer roomCount;
    private String checkInDate;
    private String checkOutDate;
    private Integer roomOrderDetailPrice;
}
