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

    /**
     * 添加用户
     * @param userName
     * @param password
     * @param roleName
     */
    void addUser(String userName,String password,String roleName) throws Exception;

    /**
     * 删除用户
     * @param userName
     */
    void delUser(String userName) throws Exception;

    /**
     * 修改密码(普通用户)
     * @param oldPwd
     * @param password
     * @throws Exception
     */
    void resetSelfPassword(String oldPwd,String password) throws Exception;

    /**
     * 重置密码(管理员)
     * @param userName
     * @param password
     */
    void resetPassword(String userName,String password) throws Exception;
}
