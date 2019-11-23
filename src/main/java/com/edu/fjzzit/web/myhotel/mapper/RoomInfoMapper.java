package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.dto.RoomPriceNameAndRoomTypeNumDTO;
import com.edu.fjzzit.web.myhotel.dto.RoomTypeAndRoomPriceDTO;
import com.edu.fjzzit.web.myhotel.model.RoomInfo;
import com.edu.fjzzit.web.myhotel.model.RoomPrice;
import com.edu.fjzzit.web.myhotel.model.RoomType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomInfoMapper {

    /**
     * 查询房间号(0->未入住,1->已入住)
     * @param roomTypeNum
     * @return
     */
    List<String> selectRoomNumByRoomTypeNum(@Param("roomTypeNum") Long roomTypeNum,
                                             @Param("roomState") Integer roomState);

    /**
     * 更新房间状态(0->未入住,1->已入住)
     * @param roomNum
     * @param roomState
     * @return
     */
    int updateRoomStateByRoomNum(@Param("roomNum") String roomNum,@Param("roomState") Integer roomState);

    /**
     * 查询房间号是否存在
     * @param roomNum
     * @return
     */
    int queryRoomNumIsExists(@Param("roomNum") String roomNum);

    /**
     * 判断楼栋所在房间号是否存在
     * @param roomNum
     * @param buildingNum
     * @return
     */
    int queryRoomNumByRoomNumAndBuildingNum(@Param("roomNum") String roomNum,@Param("buildingNum") String buildingNum);

    /**
     * 分页查询客房信息
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<RoomInfo> findRoomInfoAll(@Param("pageStart") int pageStart, @Param("pageSize")int pageSize);

    /**
     * 分页查询客房套餐信息
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<RoomTypeAndRoomPriceDTO> findRoomTypeAndRoomPriceAll(@Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 根据主键查询客房类型表
     * @param roomTypeNum
     * @return
     */
    RoomType findRoomTypeByRoomTypeNum(@Param("roomTypeNum") long roomTypeNum);

    /**
     * 根据roomTypeNum查询客房价格表
     * @param roomTypeNum
     * @return
     */
    RoomPrice findRoomPriceByRoomTypeNum(@Param("roomTypeNum") long roomTypeNum);

    /**
     * 查询所有客房套餐名
     * @return
     */
    List<RoomPriceNameAndRoomTypeNumDTO> findRoomPriceNameAll();

    /**
     * 查询客房表条目数
     * @return
     */
    long selCount();

    /**
     * 查询客房套餐条目数
     * @return
     */
    long selRoomTypeAndRoomPriceCount();

    /**
     * 删除客房套餐信息
     * @param roomTypeNum
     * @return
     */
    int delRoomTypeAndRoomPriceById(Long roomTypeNum);

    /**
     * 根据主键查询客房套餐
     * @param roomTypeNum
     * @return
     */
    RoomTypeAndRoomPriceDTO findRoomTypeAndRoomPriceById(@Param("roomTypeNum") Long roomTypeNum);

    /**
     * 修改客房套餐
     * @param roomTypeAndRoomPriceDTO
     * @return
     */
    int updRoomTypeAndRoomPriceAll(RoomTypeAndRoomPriceDTO roomTypeAndRoomPriceDTO);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer roomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    int insert(RoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    int insertSelective(RoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    RoomInfo selectByPrimaryKey(Integer roomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoomInfo record);
}