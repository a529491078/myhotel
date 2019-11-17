package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.model.FreeRoomCalendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FreeRoomCalendarMapper {

    /**
     * 查询空房数
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    List<FreeRoomCalendar> selectByCheckDate(@Param("checkInDate") String checkInDate,
                                             @Param("checkOutDate") String checkOutDate);

    /**
     * 更新空房数
     * @param roomTypeNum
     * @param checkInDate
     * @param checkOutDate
     * @param count
     * @return
     */
    int updateFreeCount(@Param("roomTypeNum") Long roomTypeNum,@Param("checkInDate") String checkInDate,
                        @Param("checkOutDate") String checkOutDate,@Param("count") Integer count);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long freeRoomCalendarNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    int insert(FreeRoomCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    int insertSelective(FreeRoomCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    FreeRoomCalendar selectByPrimaryKey(Long freeRoomCalendarNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FreeRoomCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table free_room_calendar
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FreeRoomCalendar record);
}