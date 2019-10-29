package com.edu.fjzzit.web.myhotel.model;

public enum ErrorCodeEnum implements ErrorCode {

    //通用异常
    NON_USERNAME("400","该用户不存在!"),
    UNVERIFY_OLDPSD("400","旧密码不正确!"),
    NON_ROLENAME("400","该角色不存在!"),

    ;

    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String description;

    /**
     * @param code
     * @param description
     */
   ErrorCodeEnum(String code, String description){
        this.code=code;
        this.description=description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
