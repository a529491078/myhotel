package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class RoleInfo implements Serializable {
    private Integer roleId;

    private String roleName;

    private static final long serialVersionUID = 1L;
}