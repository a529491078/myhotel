package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return UserInfo
     */
    UserInfo findFirstByUserName(@Param("userName") String userName);

    /**
     * 获取用户ID(通过用户名)
     * @param userName
     * @return
     */
    int getIdByUserName(@Param("userName") String userName);

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") Integer userId);

    /**
     * 修改密码(普通用户)
     * @param userId
     * @param password
     * @return
     */
    int updateSelfPassword(@Param("userId") Integer userId,@Param("password") String password);

    /**
     * 重置密码(管理员)
     * @param userId
     * @param password
     * @return
     */
    int updatePassword(@Param("userId") Integer userId,@Param("password") String password,@Param("salt") String salt);
}