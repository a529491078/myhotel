package com.edu.fjzzit.web.myhotel.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class PermissionInfo implements Serializable {
    private Integer permissionId;

    private String permissionName;

    private static final long serialVersionUID = 1L;
}