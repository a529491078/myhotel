package com.edu.fjzzit.web.myhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeRoomCalendar {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column free_room_calendar.free_room_calendar_num
     *
     * @mbggenerated
     */
    private Long freeRoomCalendarNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column free_room_calendar.room_type_num
     *
     * @mbggenerated
     */
    private Long roomTypeNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column free_room_calendar.calendar_date
     *
     * @mbggenerated
     */
    private Date calendarDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column free_room_calendar.total_count
     *
     * @mbggenerated
     */
    private Integer totalCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column free_room_calendar.free_count
     *
     * @mbggenerated
     */
    private Integer freeCount;
}