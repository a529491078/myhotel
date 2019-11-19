package com.edu.fjzzit.web.myhotel.model;

public class RoomOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_order.room_order_num
     *
     * @mbggenerated
     */
    private Long roomOrderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_order.customer_name
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_order.customer_phone
     *
     * @mbggenerated
     */
    private String customerPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_order.room_order_state
     *
     * @mbggenerated
     */
    private Byte roomOrderState;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_order.room_order_num
     *
     * @return the value of room_order.room_order_num
     *
     * @mbggenerated
     */
    public Long getRoomOrderNum() {
        return roomOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_order.room_order_num
     *
     * @param roomOrderNum the value for room_order.room_order_num
     *
     * @mbggenerated
     */
    public void setRoomOrderNum(Long roomOrderNum) {
        this.roomOrderNum = roomOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_order.customer_name
     *
     * @return the value of room_order.customer_name
     *
     * @mbggenerated
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_order.customer_name
     *
     * @param customerName the value for room_order.customer_name
     *
     * @mbggenerated
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_order.customer_phone
     *
     * @return the value of room_order.customer_phone
     *
     * @mbggenerated
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_order.customer_phone
     *
     * @param customerPhone the value for room_order.customer_phone
     *
     * @mbggenerated
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_order.room_order_state
     *
     * @return the value of room_order.room_order_state
     *
     * @mbggenerated
     */
    public Byte getRoomOrderState() {
        return roomOrderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_order.room_order_state
     *
     * @param roomOrderState the value for room_order.room_order_state
     *
     * @mbggenerated
     */
    public void setRoomOrderState(Byte roomOrderState) {
        this.roomOrderState = roomOrderState;
    }
}