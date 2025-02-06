package com.anyex.netease.msg.model;

import lombok.Data;

@Data
public class Callback07Model {

    /**
     * https://doc.yunxin.163.com/messaging/docs/Tc5OTU0Nzk?platform=server#创建群回调
     */
    private Integer eventType;

    private Integer type;// 群组类型，1表示高级群
    private String creator;// 创建者账号
    private String fromDeviceId;// 创建者设备id
    private String fromClientType;// 创建者客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST
    private String tname;// 群名称
    private String icon;// 群头像
    private String custom;// 自定义字段
    private String intro;// 群简介
    private String msg;// 加群信息
    private String announcement;// 群公告
    private String servercustom;// 服务器自定义字段
    private String inviteList;// 建群时邀请的人
    private Integer teamMuteType;// 群禁言类型，0表示不禁言，1表示禁言普通成员，3表示禁言整个群（包括群主）
    private Integer level;// 最大群成员数量
    private Integer joinmode;// 申请入群权限，0表示不需要申请，1表示需要申请，2表示不允许申请
    private Integer beinvitemode;// 被邀请人同意方式，0表示需要同意，1表示不需要同意
    private Integer invitemode;// 谁可以邀请他人入群，0表示管理员，1表示所有人
    private Integer uptinfomode;// 谁可以修改群资料群，0表示管理员，1表示所有人
    private Integer upcustommode;// 谁可以更新自定义字段，0表示管理员，1表示所有人
    private String timestamp;// 操作时间，字符串类型
}
