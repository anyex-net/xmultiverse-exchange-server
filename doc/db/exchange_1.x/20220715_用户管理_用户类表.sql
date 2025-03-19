--用户信息
drop table if exists User;
create table User
(
    id                      bigint(20)               not null comment '用户ID' primary key,
    uid                     bigint(20)               not null comment 'UID',
    userName                varchar(32)              not null comment '用户名',
    avatar                  varchar(64)              not null comment '用户头像',
    loginPwd                varchar(64)              not null comment '登录密码',
    tradePwd                varchar(64)                       comment '交易密码',
    email                   varchar(32)                       comment '邮箱',
    country                 varchar(32)                       comment '国家地区',
    mobileNo                varchar(12)                       comment '手机号',
    gaAuthKey               varchar(64)                       comment 'Google验证器私钥',
    location                varchar(64)                       comment '注册所在地或IP',
    state                   int                      not null comment '状态(0:正常、1:冻结、2:注销)',
    thawTime                bigint(13)                        comment '解冻时间',
    securityPolicy          int                     default 0 comment '安全验证策略',
    tradePolicy             int                     default 0 comment '交易验证策略',
    inviteCode              varchar(32)                       comment '邀请码',
    referralCode            varchar(32)                       comment '推荐码',
    source                  varchar(32)                       comment '来源web、app',
    riskEvaluation          int                     default 0 comment '是否风评',
    certState               int                     default 0 comment '认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)',
    lang                    varchar(32)                       comment '语言',
    localCurrency           varchar(16)                       comment '本地货币',
    stableCoinPreference    varchar(16)                       comment '稳定币偏好',
    remark                  varchar(64)                       comment '备注',
    sign                    varchar(64)              not null comment 'sign',
    randomKey               varchar(128)             not null comment 'randomKey',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    constraint index_User unique (uid)
) comment '用户信息';

--用户日志
drop table if exists UserLog;
create table UserLog
(
    id                bigint(20)               not null comment 'ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    userName          varchar(64)                       comment '用户名字',
    systemName        varchar(32)              not null comment '系统名称',
    opType            varchar(32)              not null comment '操作类型(login登录、setting安全设置)',
    ipAddr            varchar(64)              not null comment 'IP地址',
    rigonName         varchar(64)                       comment '证件号码',
    url               varchar(64)                       comment 'URL地址',
    content           varchar(256)                      comment '内容',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间'
) comment '用户日志';

--用户认证个人KYC
drop table if exists UserCertKyc;
create table UserCertKyc
(
    id                bigint(20)               not null comment 'ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportType      varchar(16)              not null comment '证件类型',
    passportNo        varchar(64)              not null comment '证件号码',
    passportImg1      varchar(64)              not null comment '证件照片1',
    passportImg2      varchar(64)              not null comment '证件照片2',
    passportImg3      varchar(64)              not null comment '证件照片3',
    state             int                      not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    checkBy           varchar(32)                       comment '复核人',
    checkTime         bigint(13)                        comment '复核时间',
    constraint index_UserCertKyc unique (userId)
) comment '用户认证个人KYC';
