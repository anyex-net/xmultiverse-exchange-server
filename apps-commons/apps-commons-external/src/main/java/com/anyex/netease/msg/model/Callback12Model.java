package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback12Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#取消群管理员回调
     */
    private Integer eventType;

    private Long tid;// 群id
    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private String managerList;// 管理员列表
    private String timestamp;// 操作时间，字符串类型
}
