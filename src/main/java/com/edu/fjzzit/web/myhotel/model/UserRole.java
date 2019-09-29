package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserRole implements Serializable {
    private Long userRoleNum;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}