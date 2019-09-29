package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class RolePermission implements Serializable {
    private Long rolePermissionNum;

    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}