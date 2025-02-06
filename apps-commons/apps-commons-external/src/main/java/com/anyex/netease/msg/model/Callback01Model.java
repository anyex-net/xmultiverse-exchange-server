package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback01Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/TQ5NDQwNDQ?platform=server#会话消息回调
     */
    private Integer eventType;

    private String fromAccount;//	String	是	消息发送者的用户账号
    private String fromNick;//	String	否	发送方昵称
    private String fromClientType;//	String	是 AOS- Android 客户端 IOS- iOS 客户端 PC- PC 客户端 WINPHONE- Windows 操作系统手机客户端  WEB- Web 客户端 REST - 服务端
    private String fromDeviceId;//	String	是	发送设备id
    private String to;//	String	是 若 eventType 为1，则 to 为消息接收者的用户账号，字符串类型 若eventType 为 2，则 to 为 tid，即群 ID，可转为 Long 型数据  若 eventType 为 6，则 to 为 roomid，即聊天室 ID，可转为 Long 型数据 若 eventType 为 22，则 to 为 tid，即超大群ID，可转为 Long 型数据
    private String msgTimestamp;//	String	是	消息发送时间
    private String msgType;//	String	是  TEXT：文本消息 PICTURE：图片消息 AUDIO：语音消息 VIDEO：视频消息 LOCATION：地理位置 NOTIFICATION：通知 FILE：文件消息 TIPS：提示类型消息 CUSTOM：自定义消息
    private String fromClientIp;//	String	否	消息发送方的客户端IP地址
    private String fromClientPort;//	String	否	消息发送方的客户端端口号
    private String msgidClient;//	String	否	客户端消息Id
    private String body;//	String	否	消息内容，部分消息类型不需要传入该参数，但需传入 attach，示例以及具体说明请参见回调的消息格式示例
    private String attach;//	String	否	消息附件，部分消息类型不需要传入该参数，但需传入 body，示例以及具体说明请参见回调的消息格式示例
    private String ext;//	String	否	消息扩展字段
}
