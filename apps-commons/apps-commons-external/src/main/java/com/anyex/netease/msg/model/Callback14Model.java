package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback14Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#踢人出群回调
     */
    private Integer eventType;

    private Long tid;// 群id
    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private String kickList;// 被踢列表
    private String timestamp;// 操作时间，字符串类型
}
