package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.mapper.RoleInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserRoleMapper;
import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.model.UserRole;
import com.edu.fjzzit.web.myhotel.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private RoleInfoMapper roleInfoMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

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

    /**
     * 添加用户
     * @param username
     * @param password
     * @param roleName
     */
    @Override
    public void addUser(String username, String password, String roleName) throws Exception {
        //查找角色ID
        Integer roleId=roleInfoMapper.findRoleIdByRoleName(roleName);
        if (roleId==null){
            throw new Exception();
        }

        String salt= UUID.randomUUID().toString().replace("-","");
        SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,3);

        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(username);
        userInfo.setSalt(salt);
        userInfo.setPassword(simpleHash.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setLastTime(null);
        userInfo.setUserState(Byte.parseByte("0"));

        userInfoMapper.insert(userInfo);

        Integer userId=userInfoMapper.findFirstByUserName(username).getUserId();
        UserRole userRole=new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);

    }
}
