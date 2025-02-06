package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback04Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/DI1NDU2MjI?platform=server#好友关系变更回调
     */
    private Integer eventType;// 	值为4，表示是添加好友回调；  值为5，表示是删除好友回调

    private String fromAccount;//	String	是	发起者账号
    private String toAccount;//	String	是	接收者账号
    private String fromDeviceId;//	String	是	发送方设备id
    private String fromClientType;//	String	是	发送客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private Integer verifyType;//	Integer	否	添加好友时此字段必有；含义:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友
    private String msg;//	String	否	添加好友时此字段有效；含义:加好友对应的请求信息
    private String timestamp;//	String	是	操作时间，字符串类型
}
