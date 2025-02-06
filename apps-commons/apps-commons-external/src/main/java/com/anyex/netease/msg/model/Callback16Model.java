package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback16Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#更新群成员信息回调
     */
    private Integer eventType;

    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private Long tid;// 群id
    private String nick;// 群昵称
    private String custom;// 自定义字段
    private Integer notifyType;// 通知类型，0表示通知，1表示关闭通知，2表示仅接受管理员消息
    private String timestamp;// 操作时间，字符串类型
}
