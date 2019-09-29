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
}