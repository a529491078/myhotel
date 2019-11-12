package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer userId;

    private String userName;

    private String salt;

    private String password;

    private Date createTime;

    private Date lastTime;

    private Byte userState;

    private static final long serialVersionUID = 1L;

    public Integer getId(){
        return userId;
    }
}