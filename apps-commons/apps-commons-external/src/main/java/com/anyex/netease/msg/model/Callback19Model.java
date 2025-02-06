package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback19Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#申请入群回调
     */
    private Integer eventType;

    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private Long tid;// 群id
    private String msg;// 申请附言
    private Integer joinmode;// 申请入群权限，0表示不需要申请，1表示需要申请，2表示不允许申请
    private String timestamp;// 操作时间，字符串类型
}
