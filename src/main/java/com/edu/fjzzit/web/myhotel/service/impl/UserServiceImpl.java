package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.mapper.RoleInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserRoleMapper;
import com.edu.fjzzit.web.myhotel.model.ErrorCodeEnum;
import com.edu.fjzzit.web.myhotel.model.MyException;
import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.model.UserRole;
import com.edu.fjzzit.web.myhotel.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserInfo getUserInfo(String userName) {
        return userInfoMapper.findFirstByUserName(userName);
    }

    /**
     * 获取角色集合
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRoles(Integer userId) {
        List<String> roleNameList = roleInfoMapper.queryRoleNameByUserId(userId);
        Set<String> roles = new HashSet<>(roleNameList);
        return roles;
    }

    /**
     * 添加用户
     *
     * @param userName
     * @param password
     * @param roleName
     */
    @Override
    public void addUser(String userName, String password, String roleName) throws Exception {
        //查找角色ID
        Integer roleId = roleInfoMapper.findRoleIdByRoleName(roleName);
        if (roleId == null) {
            throw new Exception();
        }

        String salt = UUID.randomUUID().toString().replace("-", "");
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 3);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setSalt(salt);
        userInfo.setPassword(simpleHash.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setLastTime(null);
        userInfo.setUserState(Byte.parseByte("0"));

        userInfoMapper.insert(userInfo);

        Integer userId = userInfoMapper.getIdByUserName(userName);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);

    }

    /**
     * 删除用户
     *
     * @param userName
     */
    @Override
    public void delUser(String userName) throws Exception {
        Integer userId = IsEmptyUser(userName);

        //删除UserRole的信息
        userRoleMapper.deleteByUserId(userId);

        //删除UserInfo的信息
        userInfoMapper.deleteByUserId(userId);
    }

    /**
     * 修改密码(普通用户)
     *
     * @param oldPwd
     * @param password
     * @throws Exception
     */
    @Override
    public void resetSelfPassword(String oldPwd, String password) throws Exception {
        UserInfo userInfo = getCurrentAccountUserInfo();//获取当前用户信息

        String psdGet = userInfo.getPassword();
        String salt = userInfo.getSalt();//数据库中的盐，不可变
        SimpleHash oldSimpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, oldPwd, salt, 3);

        if (!oldSimpleHash.toString().equals(psdGet)) {
            throw new MyException(ErrorCodeEnum.UNVERIFY_OLDPSD);
        } else {
            SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 3);

            userInfoMapper.updateSelfPassword(userInfo.getId(), simpleHash.toString());
        }
    }

    /**
     * 重置密码(管理员)
     *
     * @param userName
     * @param password
     */
    @Override
    public void resetPassword(String userName, String password) throws Exception {
        Integer userId = IsEmptyUser(userName);

        String salt = UUID.randomUUID().toString().replace("-", "");
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 3);
        userInfoMapper.updatePassword(userId, simpleHash.toString(), salt);
    }

    /**
     * 禁用账户(管理员)
     *
     * @param userName
     */
    @Override
    public void disableAccount(String userName) throws Exception {
        Integer userId = IsEmptyUser(userName);
        userInfoMapper.setAccountState(userId, 2);
    }

    /**
     * 恢复账户(管理员)
     *
     * @param userName
     */
    @Override
    public void enableAccount(String userName) throws Exception {
        Integer userId = IsEmptyUser(userName);
        userInfoMapper.setAccountState(userId, 1);
    }

    /**
     * 重置角色(管理员)
     *
     * @param userName
     * @param roleName
     * @throws Exception
     */
    @Override
    public void resetRoles(String userName, String roleName) throws Exception {
        //获取用户ID
        Integer userId = IsEmptyUser(userName);
        //获取角色ID
        Integer roleId = IsEmptyRole(roleName);
        //重置角色
        userInfoMapper.updateRoles(userId, roleId);
    }

    /**
     * 查询个人信息(普通用户)
     *
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> findSelfUserInfo() throws Exception {
        UserInfo userInfo = getCurrentAccountUserInfo();//获取当前用户信息

        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("user_id", userInfo.getUserId());
        userInfoMap.put("user_name", userInfo.getUserName());
        userInfoMap.put("salt", userInfo.getSalt());
        userInfoMap.put("password", userInfo.getPassword());
        userInfoMap.put("user_state", userInfo.getUserState());

        return userInfoMap;
    }

    /**
     * 查询所有用户信息(管理员)
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAllUserInfo(String userName) throws Exception {
        if (userName.contains("*")) {//所有用户信息
           return userInfoMapper.getAllUserInfo();
        }
        Integer userId = IsEmptyUser(userName);

        return userInfoMapper.findFirstByUserIdList(userId);//指定用户信息
    }

    /**
     * 判断用户是否存在,返回用户ID
     *
     * @param userName
     */
    private Integer IsEmptyUser(String userName) {
        try {
            Integer userId = userInfoMapper.getIdByUserName(userName);//获取用户ID
            return userId;
        } catch (Exception e) {
            throw new MyException(ErrorCodeEnum.NON_USERNAME);
        }
    }

    /**
     * 判断角色是否存在,返回角色ID
     *
     * @param roleName
     */
    private Integer IsEmptyRole(String roleName) {
        try {
            Integer roleId = userInfoMapper.getRoleIdByRoleName(roleName);//获取角色ID
            return roleId;
        } catch (Exception e) {
            throw new MyException(ErrorCodeEnum.NON_ROLENAME);
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    protected UserInfo getCurrentAccountUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipals().getPrimaryPrincipal();

        return userInfo;
    }
}
