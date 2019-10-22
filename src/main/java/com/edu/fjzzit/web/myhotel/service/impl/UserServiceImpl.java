package com.edu.fjzzit.web.myhotel.service.impl;

import com.edu.fjzzit.web.myhotel.config.MyShiroRealm;
import com.edu.fjzzit.web.myhotel.mapper.RoleInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserInfoMapper;
import com.edu.fjzzit.web.myhotel.mapper.UserRoleMapper;
import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.model.UserRole;
import com.edu.fjzzit.web.myhotel.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param userName
     * @param password
     * @param roleName
     */
    @Override
    public void addUser(String userName, String password, String roleName) throws Exception {
        //查找角色ID
        Integer roleId=roleInfoMapper.findRoleIdByRoleName(roleName);
        if (roleId==null){
            throw new Exception();
        }

        String salt= UUID.randomUUID().toString().replace("-","");
        SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,password,salt,3);

        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setSalt(salt);
        userInfo.setPassword(simpleHash.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setLastTime(null);
        userInfo.setUserState(Byte.parseByte("0"));

        userInfoMapper.insert(userInfo);

        Integer userId=userInfoMapper.getIdByUserName(userName);
        UserRole userRole=new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);

    }

    /**
     * 删除用户
     * @param userName
     */
    @Override
    public void delUser(String userName) throws Exception {
        Integer userId=userInfoMapper.getIdByUserName(userName);
        if (userId==null){
            throw new Exception();
        }
        //删除UserRole的信息
        userRoleMapper.deleteByUserId(userId);

        //删除UserInfo的信息
        userInfoMapper.deleteByUserId(userId);
    }

    /**
     * 修改密码(普通用户)
     * @param oldPwd
     * @param password
     * @throws Exception
     */
    @Override
    public void resetSelfPassword(String oldPwd,String password) throws Exception {
        Subject subject= SecurityUtils.getSubject();
        UserInfo userInfo=(UserInfo) subject.getPrincipals().getPrimaryPrincipal();

        String psdGet=userInfo.getPassword();
        String salt=userInfo.getSalt();//数据库中的盐，不可变
        SimpleHash oldSimpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,oldPwd,salt,3);

        if (!oldSimpleHash.toString().equals(psdGet)){
            throw new  Exception();
        }else{
            SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,password,salt,3);

            userInfoMapper.updateSelfPassword(userInfo.getId(),simpleHash.toString());
        }
    }

    /**
     * 重置密码(管理员)
     * @param userName
     * @param password
     */
    @Override
    public void resetPassword(String userName, String password) throws Exception {
        Integer userId=userInfoMapper.getIdByUserName(userName);//获取用户ID
        if (userId==null){//如果操作用户ID不存在，抛出异常
            throw new Exception();
        }
        String salt= UUID.randomUUID().toString().replace("-","");
        SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,password,salt,3);
        userInfoMapper.updatePassword(userId,simpleHash.toString(),salt);
    }
}
