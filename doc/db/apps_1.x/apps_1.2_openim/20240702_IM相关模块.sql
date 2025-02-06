/*-- IM用户表
drop table if exists ImUser;
create table ImUser
(
    id                      bigint(20)               not null comment '用户ID' primary key,
    nickName                varchar(32)              not null comment '用户昵称',
    loginPwd                varchar(64)              not null comment '登录密码',
    realName                varchar(32)                       comment '用户真名',
    email                   varchar(32)              not null comment '邮箱',
    country                 varchar(8)               not null comment '国家(巴基斯坦92)',
    mobile                  varchar(16)                       comment '手机号码',
    avatar                  varchar(128)             not null comment '头像URL',
    gender                  tinyint(1)                        comment '性别(0男、1女)',
    birth                   date                              comment '生日日期',
    ip                      varchar(64)                       comment 'IP地址',
    deviceNo                varchar(64)                       comment '设备编号',
    lng                     varchar(24)                       comment '最新位置经度',
    lat                     varchar(24)                       comment '最新位置维度',
    invitationCode          varchar(8)               not null comment '邀请码',
    referralCode            varchar(8)                        comment '推荐码',
    source                  varchar(32)                       comment '来源',
    status                  int            default 1 not null comment '状态(1:正常、2:冻结、3:注销)',
    sign                    varchar(64)              not null comment 'sign',
    randomKey               varchar(8)               not null comment 'randomKey',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                bigint(20)                        comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'IM用户';

-- IM好友表
drop table if exists ImFriend;
create table ImFriend
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    ownerUserId             bigint(20)               not null comment '所属用户ID',
    friendUserId            bigint(20)               not null comment '好友用户ID',
    addSource               varchar(32)                       comment '添加来源',
    operatorUserId          bigint(20)                        comment '操作者用户ID',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM好友';
CREATE INDEX `idx_ImFriend_ownerUserId_friendUserId` ON ImFriend(`ownerUserId`,`friendUserId`);

-- IM黑名单表
drop table if exists ImBlack;
create table ImBlack
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    ownerUserId             bigint(20)               not null comment '所属用户ID',
    blackUserId             bigint(20)               not null comment '黑名单用户ID',
    addSource               varchar(32)                       comment '拉黑来源',
    operatorUserId          bigint(20)                        comment '操作者用户ID',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM黑名单';
CREATE INDEX `idx_ImBlack_ownerUserId_blackUserId` ON ImUserFriend(`ownerUserId`,`blackUserId`);

-- IM群信息表
drop table if exists ImGroup;
create table ImGroup
(
    id                      bigint(20)               not null comment '群ID' primary key,
    ownerUserId             bigint(20)               not null comment '群主用户ID',
    creatorUserId           bigint(20)               not null comment '群创建用户ID',
    groupType               int            default 1 not null comment '群类型(1:普通群、2:其他群)',
    groupName               varchar(32)              not null comment '群名称',
    groupAvatar             varchar(128)             not null comment '群头像',
    introduction            varchar(64)              not null comment '群介绍',
    notification            varchar(64)              not null comment '群公告',
    memberCount             int                      not null comment '群成员数量',
    needVerification        int            default 1 not null comment '进群是否需要验证(0:申请加入群需要同意，成员邀请可直接进群、1:所有人进群需要验证，除了群主管理员邀请进群、2:直接进群)',
    status                  int            default 1 not null comment '群状态(1:正常、2:封禁、3:解散、4:全体禁言)',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM群信息';

-- IM群成员表
drop table if exists ImGroupMember;
create table ImGroupMember
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    groupId                 bigint(20)               not null comment '群ID',
    groupMemberUserId       bigint(20)               not null comment '群成员用户ID',
    roleLevel               int            default 0 not null comment '群成员等级(100:群主、60:管理员、20:普通成员)',
    joinTime                bigint(13)               not null comment '群成员加群时间',
    joinSource              int            default 0 not null comment '加群来源(1:管理员邀请、2:群成员邀请、3:搜索加入、4:扫码加入)',
    nickname                varchar(32)              not null comment '群成员昵称',
    operatorUserId          bigint(20)                        comment '操作加群的用户ID',
    inviterUserId           bigint(20)                        comment '邀请进群的用户ID',
    muteEndTime             bigint(13)                        comment '封禁结束时间',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM群成员';

-- IM好友申请表
drop table if exists ImFriendRequest;
create table ImFriendRequest
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    fromUserId              bigint(20)               not null comment '发出申请者用户ID',
    fromNickName            varchar(32)              not null comment '发出申请者用户名',
    fromAvatar              varchar(128)             not null comment '发出申请者头像',
    toUserId                bigint(20)               not null comment '接收申请者用户ID',
    toNickName              varchar(32)              not null comment '接收申请者用户名',
    toAvatar                varchar(128)             not null comment '接收申请者头像',
    reqMsg                  varchar(64)              not null comment '申请的信息',
    handleResult            int            default 0 not null comment '处理结果(1:同意、0:未处理、-1:拒绝)',
    handlerUserId           bigint(20)                        comment '处理用户ID',
    handleMsg               varchar(32)                       comment '处理信息',
    handleTime              bigint(13)                        comment '处理时间',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM好友申请';

-- GroupRequest
-- IM入群申请表
drop table if exists ImGroupRequest;
create table ImGroupRequest
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    reqUserId               bigint(20)               not null comment '申请者用户ID',
    groupId                 bigint(20)               not null comment '群ID',
    reqMsg                  varchar(64)              not null comment '申请加群的消息',
    handleResult            int            default 0 not null comment '处理结果(1:同意、0:未处理、-1:拒绝)',
    handlerUserId           bigint(20)                        comment '处理用户ID',
    handleMsg               varchar(32)                       comment '处理信息',
    handleTime              bigint(13)                        comment '处理时间',
    joinSource              int                      not null comment '加群来源(1:管理员邀请、2:群成员邀请、3:搜索加入、4:扫码加入)',
    inviterUserId           bigint(20)               not null comment '被邀请者用户ID',
    remark                  varchar(64)                       comment '备注',
    ex                      varchar(256)                      comment '扩展字段Ex',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM入群申请';

-- IM消息表
drop table if exists ImMessage;
create table ImMessage
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    sendId                  bigint(20)               not null comment '发送者ID(APP管理员ID、用户ID或系统通知号)',
    recvId                  bigint(20)                        comment '接收者ID，sessionType1或者4时必填，如果是群聊则不填',
    groupId                 bigint(20)                        comment '群ID，sessionType2或者3时必填，如果为单聊则不填',
    senderNickName          varchar(32)                       comment '消息发送者昵称',
    senderAvatar            varchar(128)                      comment '消息发送者头像',
    senderPlatformId        int                               comment '发送者平台(1:IOS，2:Android，3:Windows，4:OSX，5:Web，6:MiniWeb，7:Linux，8:Android Pad，9:IPad，10:admin)',
    content                 varchar(1024)            not null comment '消息的具体内容(内部是json 对象，其他消息的详细字段请参考消息类型格式描述文档)',
    contentType             int                      not null comment '消息类型(101:文本、102:图片、103:音频、104:视频、105:文件、106:@、109:位置、110:自定义、199:系统通知))',
    sessionType             int                      not null comment '会话类型(1:单聊，3:群聊，4:系统通知)',
    isOnlineOnly            boolean                           comment '接收者在线才能收到，否则将会丢失',
    notOfflinePush          boolean                           comment '不进行离线推送',
    offlinePushInfo         varchar(1024)                     comment '离线推送的具体内容，如果不填写，使用服务器默认推送标题(
                                                offlinePushInfo.title、offlinePushInfo.desc、offlinePushInfo.ex、offlinePushInfo.iOSPushSound、offlinePushInfo.iOSBadgeCount)',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM消息';


{
  "sendID": "openIMAdmin",
  "recvID": "2839678182",
  "groupID": "",
  "senderNickname": "openIMAdmin-Gordon",
  "senderFaceURL": "http://www.head.com",
  "senderPlatformID": 1,
  "content": {
    "content": "hello!!"
  },
  "contentType": 101,
  "sessionType": 1,
  "isOnlineOnly": false,
  "notOfflinePush": false,
  "sendTime": 1695212630740,
  "offlinePushInfo": {
    "title": "send message",
    "desc": "",
    "ex": "",
    "iOSPushSound": "default",
    "iOSBadgeCount": true
  }
}


-- IM会话概览表
drop table if exists ImConversationSummary;
create table ImConversationSummary
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    unreadTotal             int                      not null comment '未读数总数',
    conversationToal        int                      not null comment '会话数总数',
    ex                      varchar(256)                      comment '扩展字段Ex'
) comment 'IM会话概览';

-- IM会话表
drop table if exists ImConversation;
create table ImConversation
(
    id                      bigint(20)               not null comment '主键ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    conversationId          varchar(64)              not null comment '会话ID',
    conversationType        int                      not null comment '会话类型(1:单聊，3:群聊，4:系统通知)',
    recvMsgOpt              boolean                  not null comment '会话免打扰状态',
    isPinned                boolean                  not null comment '会话是否置顶',
    unreadCount             int                      not null comment '未读消息数',
    msgId                   bigint(20)               not null comment '消息ID',
    sessionType             int                      not null comment '会话类型(1:单聊、2:群聊(普通写扩散)、3:大群(读扩散接口)、4:通知会话)',
    recvId                  bigint(20)               not null comment '接收者ID',
    sendId                  bigint(20)               not null comment '发送者ID',
    senderAvatar            varchar(128)                      comment '头像URL(在单聊中，当发送者为当前用户时，该字段为好友头像URL)',
    senderName              varchar(128)                      comment '发送者呢称(在单聊中，当发送者为当前用户时，该字段为好友呢称。在群聊中，该字段为对应的发送者呢称)',
    latestMsgRecvTime       bigint(13)                        comment '最后一条消息的接收时间'
    msgFrom                 int                      not null comment '消息来源(100来源于用户发送，200来源于管理员发送或者系统广播通知等)',
    content                 varchar(1024)            not null comment '消息的具体内容(内部是json 对象，其他消息的详细字段请参考消息类型格式描述文档)',
    contentType             int                      not null comment '消息类型(101:文本、102:图片、103:音频、104:视频、105:文件、106:@、109:位置、110:自定义、199:系统通知))',
    groupId                 bigint(20)                        comment '群ID',
    groupName               varchar(32)                       comment '群名称',
    groupAvatar             varchar(128)                      comment '群头像',
    groupMemberCount        int                               comment '群成员数量',
    ex                      varchar(256)                      comment '扩展字段Ex'
) comment 'IM会话';

/*
{
  "errCode": 0,
  "errMsg": "",
  "errDlt": "",
  "unreadTotal": 2,
  "conversationTotal": 2,
  "data": {
    "conversationElems": [
      {
        "conversationID": "si_110_114",
        "recvMsgOpt": 0,
        "unreadCount": 1,
        "IsPinned": false,
        "msgInfo": {
          "serverMsgID": "c54203436b727117226cb528fc7b08e8",
          "clientMsgID": "c972d53afb9d6b9744f1edfc4ac1aeef",
          "sessionType": 1,
          "sendID": "114",
          "recvID": "110",
          "senderName": "yourNickname",
          "faceURL": "yourFaceURL",
          "groupID": "",
          "groupName": "",
          "groupFaceURL": "",
          "groupType": 0,
          "groupMemberCount": 0,
          "LatestMsgRecvTime": 1695212630741,
          "msgFrom": 200,
          "contentType": 101,
          "content": "{\"content\":\"hello!!\"}",
          "ex":""
        }
      },
      {
        "conversationID": "si_110_111",
        "recvMsgOpt": 0,
        "unreadCount": 1,
        "IsPinned": false,
        "msgInfo": {
          "serverMsgID": "5c3d8542f9eae1487283a5fe335aab1a",
          "clientMsgID": "e09109bdfeb221cec1827317c313e3d0",
          "sessionType": 1,
          "sendID": "111",
          "recvID": "110",
          "senderName": "yourNickname",
          "faceURL": "yourFaceURL",
          "groupID": "",
          "groupName": "",
          "groupFaceURL": "",
          "groupType": 0,
          "groupMemberCount": 0,
          "LatestMsgRecvTime": 1695212630740,
          "msgFrom": 200,
          "contentType": 101,
          "content": "{\"content\":\"hello!!\"}",
          "ex":""
        }
      }
    ]
  }
}
*/


-- IM消息内容表
drop table if exists ImMsgContent;
create table ImMsgContent
(
    id                      bigint(20)               not null comment '消息ID' primary key,
    senderUserId            bigint(20)               not null comment '消息发送用户ID',
    receiverUserId          bigint(20)               not null comment '消息接收用户ID',
    msgMainType             int                      not null comment '消息主类型(1:单聊、2:群聊、3:通知)',
    msgSubType              int                      not null comment '消息子类型(101:文本、102:图片、103:音频、104:视频、105:文件、106:@、109:位置、110:自定义、199:系统通知)',
    msgContent              varchar(1024)                     comment '消息内容',
    status                  int            default 0 not null comment '状态(0:未读、1:已读、2:已撤回)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'IM消息内容';

-- IM消息索引表
drop table if exists ImMsgRelation;
create table ImMsgRelation
(
    id                      bigint(20)               not null comment '消息ID' primary key,
    ownerUserId             bigint(20)               not null comment '所属用户ID',
    otherUserId             bigint(20)               not null comment '其他用户ID',
    msgMainType             int                      not null comment '消息主类型(1:单聊、2:群聊、3:通知)',
    msgId                   bigint(20)               not null comment '消息Id',
    createTime              bigint(13)               not null comment '创建时间'
) comment 'IM消息索引';
CREATE INDEX `idx_ImMsgRelation_ownerUserId_otherUserId_msgId` ON  ImMsgRelation(`ownerUserId`,`otherUserId`,`msgId`);



-- 用户表
CREATE TABLE IM_USER (
    uid INT PRIMARY KEY,
    username VARCHAR(500) NOT NULL,
    password VARCHAR(500) NOT NULL,
    email VARCHAR(250) DEFAULT NULL,
    avatar VARCHAR(500) NOT NULL
);

-- 消息内容表
CREATE TABLE IM_MSG_CONTENT (
    mid INT AUTO_INCREMENT  PRIMARY KEY,
    content VARCHAR(1000) NOT NULL,
    sender_id INT NOT NULL,
    recipient_id INT NOT NULL,
    msg_type INT NOT NULL,
    create_time TIMESTAMP NOT NUll
);

-- 消息索引表
CREATE TABLE IM_MSG_RELATION (
    owner_uid INT NOT NULL,
    other_uid INT NOT NULL,
    mid INT NOT NULL,
    type INT NOT NULL,
    create_time TIMESTAMP NOT NULL,
    PRIMARY KEY (`owner_uid`,`mid`)
);
CREATE INDEX `idx_owneruid_otheruid_msgid` ON  IM_MSG_RELATION(`owner_uid`,`other_uid`,`mid`);

-- 联系人列表
CREATE TABLE IM_MSG_CONTACT (
    owner_uid INT NOT NULL,
    other_uid INT NOT NULL,
    mid INT NOT NULL,
    type INT NOT NULL,
    create_time TIMESTAMP NOT NULL,
    PRIMARY KEY (`owner_uid`,`other_uid`)
);



-- 账户表
drop table if exists Account;
create table Account
(
    id                      bigint(20)               not null comment '账户ID' primary key,
    unid                    bigint(20)               not null comment '账户编号',
    country                 varchar(8)               not null comment '国家默认86(巴基斯坦92)',
    mobile                  varchar(16)                       comment '手机号码',
    accountName             varchar(32)                       comment '账户昵称',
    realName                varchar(32)                       comment '账户姓名',
    cnic                    varchar(32)                       comment 'CNIC',
    loginPwd                varchar(64)              not null comment '登录密码',
    headUrl                 varchar(512)             not null comment '头像URL',
    email                   varchar(32)              not null comment '邮箱',
    birth                   varchar(16)                       comment '生日',
    gender                  tinyint(1)                        comment '性别(0男、1女)',
    deviceId                varchar(64)                       comment '设备编号',
    ip                      varchar(64)                       comment 'IP地址',
    lng                     varchar(24)                       comment '最新位置经度',
    lat                     varchar(24)                       comment '最新位置维度',
    invitationCode          varchar(8)               not null comment '邀请码',
    referralCode            varchar(8)                        comment '推荐码',
    source                  varchar(32)                       comment '来源',
    status                  int            default 0 not null comment '状态(0:正常、1:冻结、2:注销)',
    sign                    varchar(64)              not null comment 'sign',
    randomKey               varchar(8)               not null comment 'randomKey',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                bigint(20)                        comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment '账户表';

INSERT INTO Account VALUES
(200000000000, 10000, '92', 921234567890, '测试账户accountName', '测试账户realName', 'cnic123456789', 'loginPwd', 'headUrl', 'test1@qq.com', '1990-10-10', '1', '10000', '127.0.0.1', '121.123', '29.123', '87654321', '12345678', 'ios', 0, 'sign', 'random', 'remark', 1709529514832, NULL, 1709529514832);
commit;
*/