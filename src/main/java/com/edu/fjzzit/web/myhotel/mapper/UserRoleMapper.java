package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") Integer userId);
}