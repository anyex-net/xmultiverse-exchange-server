package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback09Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#群邀请回调
     */
    private Integer eventType;

    private Long tid;// 群id
    private String fromAccount;// 操作者账号
    private String fromDeviceId;// 操作者设备id
    private String fromClientType;// 操作者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private Integer type;// 群组类型，1表示高级群
    private Integer beinvitemode;// 被邀请人同意方式，0表示需要同意，1表示不需要同意
    private String inviteList;// 邀请的人
    private String msg;// 邀请附言
    private String attach;// 拉人的attach信息
    private String timestamp;// 操作时间，字符串类型
}
