package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback03Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/DI1NDU2MjI?platform=server#用户资料变更回调
     */
    private Integer eventType;

    private String account;//	String	是	用户账号
    private String deviceId	;//String	是	发送方设备id
    private String clientType;//	String	是	发送客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private String name;//	String	否	昵称
    private String icon;//	String	否	头像图标
    private String sign;//	String	否	签名
    private String email;//	String	否	email
    private String birth;//	String	否	生日
    private String mobile;//	String	否	手机号
    private String gender;//	Integer	否	用户性别，0表示未知，1表示男，2表示女
    private String ex;//	String	否	用户名片扩展字段
    private String timestamp;//	String	是	操作时间，字符串类型
}
