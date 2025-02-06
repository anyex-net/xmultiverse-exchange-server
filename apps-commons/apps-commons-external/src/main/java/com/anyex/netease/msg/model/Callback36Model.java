package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback36Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/jc3MzA5NTk?platform=server#登录回调
     */
    private Integer eventType;

    private String fromAccount;//	String	是	操作者账号
    private String fromDeviceId;//	String	是	操作者设备id
    private String fromClientType;//	String	是	操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private String fromClientIp;//	String	否	操作者的客户端IP地址
    private String fromClientPort;//	String	否	操作者的客户端端口号
    private String token;//	String	是	登录token
    private Integer authType;//	Integer	是	登录鉴权方式，0表示经典模式，1表示动态token模式，2表示基于第三方回调的校验模式（该模式下云信对token不会做校验）
    private String loginExt;//	String	是	登录扩展字段
    private String customTag;//	String	是	登录自定义tag
    private String customClientType;//	String	是	自定义端类型
    private Boolean autoLogin;//	Boolean	否	本次登录是否是自动登录
    private String timestamp;//	String	是	操作时间，字符串类型
}
