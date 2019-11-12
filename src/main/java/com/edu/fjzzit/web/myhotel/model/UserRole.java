package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private Long userRoleNum;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}