package com.edu.fjzzit.web.myhotel.service;

import com.edu.fjzzit.web.myhotel.model.UserInfo;

import java.util.Set;

public interface UserService {

    /**
     * 根据用户名返回用户信息
     * @param userName
     * @return 返回用户名对应的用户信息，若未找到则返回null
     */
    UserInfo getUserInfo(String userName);

    /**
     * 根据用户ID，返回用户拥有的角色
     * @param userId
     * @return 返回用户拥有的所有角色集合
     */
    Set<String> getRoles(Integer userId);
}
