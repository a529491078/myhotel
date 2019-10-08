package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.mapper.RoleInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private RoleInfoMapper roleInfoMapper;

    @Override
    public UserInfo getUserInfo(String userName) {
        return userInfoMapper.findFirstByUserName(userName);
    }

    /**
     * 获取角色集合
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRoles(Integer userId) {
        List<String> roleNameList=roleInfoMapper.queryRoleNameByUserId(userId);
        Set<String> roles=new HashSet<>(roleNameList);
        return roles;
    }
}
