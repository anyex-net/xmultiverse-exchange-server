-- 用户签到信息
drop table if exists AccountSignInInfo;
CREATE TABLE AccountSignInInfo
(
    id                 BIGINT  NOT NULL COMMENT '主键',
    accountId          BIGINT  NOT NULL COMMENT '账户ID',
    totalPoints        INT     NOT NULL DEFAULT 0 COMMENT '总积分',
    remainingPoints    INT     NOT NULL DEFAULT 0 COMMENT '剩余积分',
    pointsLevel        TINYINT NOT NULL DEFAULT 0 COMMENT '积分等级',
    totalSignInTimes   INT     NOT NULL DEFAULT 0 COMMENT '总签到次数',
    maxSignInTimes     INT     NOT NULL DEFAULT 0 COMMENT '最高签到次数',
    lastSignInDate     DATE    NOT NULL COMMENT '最后一次签到日期',
    currentSignInTimes INT     NOT NULL DEFAULT 0 COMMENT '本轮累计签到次数',
    createTime         bigint  not null comment '创建时间',
    updateTime         bigint           comment '更新时间',
    PRIMARY KEY (id),                                -- 主键
    UNIQUE INDEX Idx_ActSignInInfo_AccountId (accountId) -- 唯一索引：用户ID
) comment '账户签到信息';

-- 账户签到明细
drop table if exists AccountSignInDetail;
CREATE TABLE AccountSignInDetail
(
    id                 BIGINT   NOT NULL COMMENT '主键',
    accountId          BIGINT   NOT NULL COMMENT '账户ID',
    signInDate         DATE     NOT NULL COMMENT '签到对应的日期',
    isContinuous       TINYINT  NOT NULL COMMENT '是否连续签到',
    currentSignInTimes INT      NOT NULL DEFAULT 0 COMMENT '连续签到次数',
    pointsAwarded      INT      NOT NULL DEFAULT 0 COMMENT '本次签到积分',
    signInTime         DATETIME NOT NULL COMMENT '签到时间',
    status             TINYINT  NOT NULL DEFAULT 0 COMMENT '奖励发放状态 0未发放，1已发放',
    createTime         bigint   not null comment '创建时间',
    updateTime         bigint            comment '更新时间',
    PRIMARY KEY (id),                                               -- 主键
    UNIQUE INDEX Idx_ActSignInDetail_signInDate (accountId, signInDate) -- 唯一索引：用户ID，日期
) comment '账户签到明细';

-- 账户邀请统计
drop table if exists AccountInviteStatistics;
create table AccountInviteStatistics
(
    id                  bigint         not null comment 'ID' primary key,
    accountId           bigint         not null comment '账户ID',
    inviteCnt           INT            not null DEFAULT 0 comment '邀请人数',
    inviteAwardedTotal  decimal(22, 8) not null comment '累计奖励金额',
    rewardsLevel        int            not null comment '奖励级别(1、2、3)',
    remark              varchar(64)             comment '备注',
    createTime          bigint         not null comment '创建时间',
    updateTime          bigint                  comment '更新时间'
) comment '账户邀请统计';

-- 账户邀请奖励
drop table if exists AccountInviteRewardsDetail;
create table AccountInviteRewardsDetail
(
    id           bigint         not null comment 'ID' primary key,
    accountId    bigint         not null comment '账户ID',
    inviteActId  bigint         not null    comment '被邀请人账户ID',
    inviteActNick  varchar(50)         not null    comment '被邀请人昵称',
    inviteActHead  varchar(255)        not null    comment '被邀请人头像',
    inviteActSeq int                not null    comment '顺序号',
    inviteCnt    INT            not null DEFAULT 0 comment '邀请人数',
    rewardsLevel int            not null comment '当前奖励级别(1、2、3)',
    inviteAward  decimal(22, 8) not null comment '当前奖励金额',
    status       TINYINT        NOT NULL COMMENT '状态(0未发放、1已发放)',
    remark       varchar(64)             comment '备注',
    createTime   bigint         not null comment '创建时间',
    updateTime   bigint                  comment '更新时间'
) comment '账户邀请奖励';

-- 广场消息
-- 帖子点赞是否已读
alter table SnsPostLike
    add column isRead tinyint(1) not null default 0 comment '是否已读(0未读、1已读)';
-- 最新关注我的是否已读
alter table SnsFans
    add column isRead tinyint(1) not null default 0 comment '是否已读(0未读、1已读)';
-- 评论是否已读
alter table SnsPostComment
    add column isRead tinyint(1) not null default 0 comment '是否已读(0未读、1已读)';

-- 账户收藏
drop table if exists AccountFavorite;
create table AccountFavorite
(
    id           bigint         not null comment 'ID' primary key,
    accountId    bigint         not null comment '账户ID',
    bizId        varchar(255)            comment '业务ID',
    source       varchar(255)            comment '来源',
    content      text           not null comment '内容',
    favoriteType varchar(32)    not null comment '标签:TEXT文本 IMAGE图片视频 LINK链接 FILE文件 MSG聊天记录',
    functionType varchar(50)    not null comment '应用类型(IM,社交，商场)',
    remark       varchar(64)             comment '备注',
    createTime   bigint         not null comment '创建时间',
    updateTime   bigint                  comment '更新时间'
) comment '账户收藏';

-- 社交活动
drop table if exists SnsActivity;
create table SnsActivity
(
    id          bigint           not null comment 'ID' primary key,
    title       varchar(255)     not null comment '标题',
    content     text             not null comment '内容',
    imgUrl      varchar(255)     not null comment '主图地址',
    activityTag varchar(50)      not null comment '活动标签',
    openUrl     varchar(255)              comment '链接地址',
    remark      varchar(64)               comment '备注',
    status      TINYINT          not null DEFAULT 0 comment '状态 0未发布，1已发布',
    createTime  bigint           not null comment '创建时间',
    updateTime  bigint               null comment '更新时间'
) comment '社交活动';

-- 修改字段小的问题
alter table SysAccessLog modify column module varchar(256) comment '模块';

-- 会话限制
drop table if exists ConversationLimit;
create table ConversationLimit
(
    id          bigint            not null comment 'ID' primary key,
    conversationId  varchar(255)  not null comment '会话ID',
    fromUserId   varchar(50)      not null comment '发送人ID',
    toUserId     varchar(50)      not null comment '接收人ID',
    userId      varchar(50)       not null comment '用户ID',
    msgSeq      int               not null comment '消息序号',
    status      int               not null DEFAULT 0 comment '状态 0有限制(>3条不能发)，1不限制',
    remark      varchar(64)                comment '备注',
    createTime  bigint            not null comment '创建时间',
    updateTime  bigint                null comment '更新时间'
) comment '会话限制';

-- 修改字段长度
alter table SnsPostComment modify column remark   varchar(255)    comment '备注';
