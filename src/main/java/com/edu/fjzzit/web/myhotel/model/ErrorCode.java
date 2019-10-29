package com.edu.fjzzit.web.myhotel.model;

/**
 * 错误码接口
 * @author zly
 * @version 1.0
 */
public interface ErrorCode {
    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getDescription();
}
