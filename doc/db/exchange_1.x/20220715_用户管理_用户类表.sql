drop table if exists User;
create table User
(
    id                      bigint(20)               not null comment '用户ID' primary key,
    uid                     bigint(20)               not null comment 'UID',
    userName                varchar(32)              not null comment '用户名',
    loginPwd                varchar(64)              not null comment '登录密码',
    cashPwd                 varchar(64)                       comment '资金密码',
    email                   varchar(32)                       comment '邮箱',
    country                 varchar(32)                       comment '国家地区',
    mobNo                   varchar(12)                       comment '手机',
    gaauthKey               varchar(64)                       comment 'Google验证器私钥',
    location                varchar(64)                       comment '注册所在地或IP',
    state                   varchar(16)              not null comment '状态(0:正常、1:冻结、2:注销)',
    thawTime                bigint(13)                        comment '解冻时间',
    securityPolicy          int                     default 0 comment '安全验证策略',
    tradePolicy             int                     default 0 comment '交易验证策略',
    inviteCode              varchar(32)                       comment '邀请码',
    riskEvaluation          int                     default 0 comment '是否风评',
    certState               int                     default 0 comment '认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)',
    lang                    varchar(32)                       comment '语言',
    localCurrency           varchar(16)                       comment '本地货币',
    stableCoinPreference    varchar(16)                       comment '稳定币偏好',
    remark                  varchar(64)                       comment '备注',
    sign                    varchar(64)              not null comment 'sign',
    randomKey               varchar(128)             not null comment 'randomKey',
    createDate              bigint(13)               not null comment '创建时间',
    updateDate              bigint(13)                        comment '更新时间',
    constraint index_User unique (uid)
) comment '用户表';

drop table if exists UserCertKyc;
create table UserCertKyc
(
    id                bigint(20)               not null comment '账户ID' primary key,
    userId            bigint(20)               not null comment '本平台用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportNo        varchar(64)              not null comment '证件号',
    attachments       varchar(2048)            not null comment '附件信息',
    state             varchar(16)              not null comment '状态(0未审核 1审核通过 2审核失败)',
    remark            varchar(64)                       comment '备注'
) comment '用户认证个人Kyc';

drop table if exists UserCertInstInvestor;
create table UserCertInstInvestor
(
    id                bigint(20)               not null comment '账户ID' primary key,
    userId            bigint(20)               not null comment '本平台用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportNo        varchar(64)              not null comment '证件号',
    attachments       varchar(2048)            not null comment '附件信息',
    state             varchar(16)              not null comment '状态(0未审核 1审核通过 2审核失败)',
    remark            varchar(64)                       comment '备注'
) comment '用户认证机构投资者';

drop table if exists UserCertInstSpvPromoter;
create table UserCertInstSpvPromoter
(
    id                bigint(20)               not null comment '账户ID' primary key,
    userId            bigint(20)               not null comment '本平台用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportNo        varchar(64)              not null comment '证件号',
    attachments       varchar(2048)            not null comment '附件信息',
    state             varchar(16)              not null comment '状态(0未审核 1审核通过 2审核失败)',
    remark            varchar(64)                       comment '备注'
) comment '用户认证机构SPV发起人';
