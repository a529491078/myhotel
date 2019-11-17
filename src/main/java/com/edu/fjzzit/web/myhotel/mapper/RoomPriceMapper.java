package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.dto.FreeRoomDTO;
import com.edu.fjzzit.web.myhotel.model.RoomPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomPriceMapper {

    /**
     * 根据房间流水号查询房价信息
     * @param roomTypeNum
     * @return
     */
    List<FreeRoomDTO> queryFreeRoomDTOByRoomTypeNum(@Param("roomTypeNum") Long roomTypeNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long roomPriceNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    int insert(RoomPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    int insertSelective(RoomPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    RoomPrice selectByPrimaryKey(Long roomPriceNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoomPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_price
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoomPrice record);
}