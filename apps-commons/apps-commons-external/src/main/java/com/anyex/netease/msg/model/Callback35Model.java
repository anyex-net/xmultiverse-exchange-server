package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback35Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/TQ5NDQwNDQ?platform=server#消息撤回回调
     */
    private Integer eventType;

    private String fromAccount;//	String	是	消息发送者的用户账号
    private String fromDeviceId	;//String	是	操作者设备 ID
    private String fromClientType;//	String	是 操作者的客户端类型： AOS- Android 客户端 IOS- iOS 客户端 PC- PC 客户端 WINPHONE- Windows 操作系统手机客户端 WEB- Web 客户端 EST - 服务端
    private String fromClientIp;//	String	否	操作者的客户端IP地址
    private String fromClientPort;//	String	否	操作者的客户端端口号
    private String msgFromAccid;//	String	是	被撤回消息的消息发送者
    private Long msgId;//	Long	是	被撤回消息的消息 ID（服务器 ID）
    private Long time;//	Long	是	被撤回消息的消息发送时间
    private Integer opeType;//	int	是	7 表示单聊消息撤回，8 表示群消息撤回
    private String toAccount;//	String	是	消息接收者的 accid，或者，群的 tid
    private String msgidClient;//	String	否	被撤回消息的消息 ID（客户端 ID）
    private String msg;//	String	否	消息撤回附言
    private String attach;//	String	否	消息撤回扩展字段
    private String timestamp;//	String	是	操作时间，字符串类型
}
