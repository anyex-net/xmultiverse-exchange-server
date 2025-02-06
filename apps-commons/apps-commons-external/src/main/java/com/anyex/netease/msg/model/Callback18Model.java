package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback18Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#禁言群成员回调
     */
    private Integer eventType;

    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private Long tid;// 群id
    private String toAccount;// 被操作者
    private Integer mute;// 0表示不禁言，1表示禁言
    private String timestamp;// 操作时间，字符串类型
}
