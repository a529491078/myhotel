package com.edu.fjzzit.web.myhotel.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultJson {
    private String code;
    private String msg;
    private Object data;
}
