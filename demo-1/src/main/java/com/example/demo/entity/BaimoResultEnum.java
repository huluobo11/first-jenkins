package com.example.demo.entity;

/**
 * 登录注册验证枚举
 *
 * Created by ybl on 2018/11/14.
 */
public enum BaimoResultEnum {

    UNKNOW_ERROR(-1,"未知错误","FAIL"),
    IS_NOT_MOBILE(40101,"不是正确的手机号码","FAIL"),
    IS_EMPTY_MOBILE_ERROR(40101,"手机号码不能为空","FAIL"),
    IS_SENT_INFO_NOT_NULL(40101,"传过来的对象不能为空","FAIL"),

    // 注册相关
    SMS_ALLOW_REGISTER(40601,"这个手机尚未注册，可以进行注册","SUCCESS"),
    MOBILE_ALREADUY_EXISTS(40601,"这个手机号码已经被注册","FAIL"),
    TWOPASSWORD_AUTH_FAIL(40601,"两次密码不一致","FAIL"),
    PASSWORD_NOT_NULL(40601,"密码不能为空","FAIL"),
    REGISTER_SUCCESS(40601,"注册成功","SUCCESS"),

    REGISTER_FAIL(40601,"注册失败","FAIL"),

    // 修改密码相关
    UPDATE_SUCCESS(40601,"密码修改成功","SUCCESS"),
    UPDATE_FAIL(40601,"密码修改失败","FAIL"),

    // 登录相关
    LOGIN_SUCCESS(40601,"登录成功","SUCCESS"),
    LOGIN_FAIL(40601,"登录失败","FAIL"),
    ACCOUNT_NO_EXIST(40601,"账号不存在","FAIL"),

    // 短信服务相关
    SMS_AUTHOR_FAIL(40601,"短信验证失败,两次验证码不一致","FAIL"),
    SMS_SENT_FAIL(40601,"验证码短信发送失败","FAIL"),
    SMS_SENT_SUCCESS(40601,"验证码发送成功","SUCCESS"),
    SMS_CHECK_CODE_SUCCESS(40601,"验证码验证码成功","SUCCESS"),
    SMS_CHECK_CODE_FAIL(40601,"请输入正确的验证码","SUCCESS"),
    USER_TOROLES_FAIL(40601,"用户角色信息添加失败","FAIL"),
    USER_ROLES_FAIL(40601,"用户角色信息修改失败","FAIL"),

    ;

    private Integer code;

    private String data;

    private String flag;
    BaimoResultEnum(Integer code, String data,String flag) {
        this.code = code;
        this.data = data;
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public String getData() {
        return data;
    }
    public String getFlag() {
        return flag;
    }
}
