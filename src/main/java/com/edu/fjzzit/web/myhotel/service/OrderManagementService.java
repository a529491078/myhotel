package com.edu.fjzzit.web.myhotel.service;

import com.edu.fjzzit.web.myhotel.dto.RoomOrderDetailAndRoomOrderDTO;
import com.edu.fjzzit.web.myhotel.model.Page;

public interface OrderManagementService {
    /**
     * 分页查询订单信息
     * @param pageNumber 当前页
     * @param pageNumber 每页个数
     * @return 分页对象
     */
    Page findRoomOrderDetailAll(int pageNumber, int pageSize);

    /**
     * 删除订单信息
     * @param roomOrderDetailId 主键
     * @return 受影响行数
     */
    int delRoomOrderDetailById(Long roomOrderDetailId);

    /**
     * 修改框显示订单信息
     * @param roomOrderDetailId 主键
     * @return 客房信息
     */
    RoomOrderDetailAndRoomOrderDTO findRoomOrderDetailById(Long roomOrderDetailId);

    /**
     * 修改订单信息
     * @param roomOrderDetailAndRoomOrderDTO 所有订单信息
     * @return 受影响行数
     */
    int updRoomOrderDetailAll(RoomOrderDetailAndRoomOrderDTO roomOrderDetailAndRoomOrderDTO);
}
