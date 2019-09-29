package com.edu.fjzzit.web.myhotel.mapper;

import com.edu.fjzzit.web.myhotel.model.UserInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testFindFirstByUserName(){
        UserInfo userInfo=userInfoMapper.findFirstByUserName("admin");
        Assert.assertNull(userInfo);

//        String salt="abcd";
//        SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,3);
//
//        UserInfo userInfo2=new UserInfo();
//        userInfo2.setUserId(1);
//        userInfo2.setUserName("admin");
//        userInfo2.setSalt(salt);
//        userInfo2.setPassword(simpleHash.toString());
//        userInfo2.setCreateTime(new Date());
//        userInfo2.setUserState(Byte.parseByte("1"));
//
//        userInfo=userInfoMapper.findFirstByUserName("admin");
//        Assert.assertEquals(userInfo2,userInfo);
    }

    @Test
    @Rollback(false)
    public void testInsert(){
        String salt="abcd";
        SimpleHash simpleHash=new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,3);

        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("admin1");
        userInfo.setSalt(salt);
        userInfo.setPassword(simpleHash.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setUserState(Byte.parseByte("1"));

        int insertResult=userInfoMapper.insert(userInfo);
        Assert.assertEquals(insertResult,1);
    }
}
