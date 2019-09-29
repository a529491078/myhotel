package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.model.RoleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleInfoMapper {
    int insert(RoleInfo record);
}