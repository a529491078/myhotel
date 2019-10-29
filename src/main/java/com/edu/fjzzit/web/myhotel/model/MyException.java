package com.edu.fjzzit.web.myhotel.model;

/**
 * 自定义系统运行时异常
 *
 * @author zly
 * @version 1.0
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = -7864604160297181941L;

    /** 错误码 字符串*/
    protected final String errorCode;

    protected final String description;

    /**
     * 构造枚举类异常
     * @param errorCodeEnum
     */
    public MyException(ErrorCodeEnum errorCodeEnum){
        this.description=errorCodeEnum.getDescription();
        this.errorCode=errorCodeEnum.getCode();
    }

    public String getErrorCode(){
        return this.errorCode;
    }

    public String getDescription(){
        return this.description;
    }

}
