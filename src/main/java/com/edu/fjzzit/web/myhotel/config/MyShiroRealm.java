package com.edu.fjzzit.web.myhotel.config;

import com.edu.fjzzit.web.myhotel.model.UserInfo;
import com.edu.fjzzit.web.myhotel.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权(权限验证)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo=(UserInfo)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRoles(userInfo.getUserId()));

        return authorizationInfo;
    }

    //认证(身份验证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken=(UsernamePasswordToken) token;
        String userName=authToken.getUsername();
        UserInfo userInfo=userService.getUserInfo(userName);

        if (userInfo==null){
            //账号不存在,抛出异常
            throw new UnknownAccountException();
        }else if(userInfo.getUserState()==2){
            //账户被禁用，抛出异常
            throw new DisabledAccountException();
        }else{
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                    userInfo,
                    userInfo.getPassword(),
                    ByteSource.Util.bytes(userInfo.getSalt()),
                    getName());
            return simpleAuthenticationInfo;
        }

    }
}
