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
    userLevel               varchar(16)             default 0 comment '用户等级',
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
    passportType      varchar(16)              not null comment '证件类型(身份证IdentityCard、护照Passport)',
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


--用户等级，根据用户持有USDT数量划分等级，后台可设置等级，不同等级交易手续费享受不同折扣
--用户持有数量奖励配置
drop table if exists UserHoldAmountRewardConfig;
create table UserHoldAmountRewardConfig
(
    id                bigint(20)               not null comment 'ID' primary key,
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    holdAmount1       decimal(22, 8) default 0 not null comment '持有数量1(起)',
    holdAmount2       decimal(22, 8) default 0 not null comment '持有数量2(终)',
    holdLevel         varchar(16)    default 0 not null comment '持有等级',
    holdRateDiscount  decimal(22, 8) default 0 not null comment '持有对应交易手续费费率折扣',
    state             int                      not null comment '状态(0不可用、1可用)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间'
) comment '用户持有数量奖励配置';

--邀请返佣，返给邀请人被邀请人的交易手续费的一定比例，这个比例后台可配置
--用户邀请返佣奖励配置
drop table if exists UserInviteRewardConfig;
create table UserInviteRewardConfig
(
    id                bigint(20)               not null comment 'ID' primary key,
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    tradeFeeSum1      decimal(22, 8) default 0 not null comment '交易手续费累积1(起)',
    tradeFeeSum2      decimal(22, 8) default 0 not null comment '交易手续费累积2(终)',
    rewardLevel       varchar(16)    default 0 not null comment '奖励等级',
    rewardDiscount    decimal(22, 8) default 0 not null comment '奖励折扣(交易手续费)',
    state             int                      not null comment '状态(0不可用、1可用)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间'
) comment '用户邀请返佣奖励配置';

--用户API
drop table if exists UserApi;
create table UserApi
(
    id                bigint(20)               not null comment 'ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    keyType           int                      not null comment '密钥类型(0:只读、1:交易、2:提币)',
    apiKey            varchar(64)              not null comment 'apiKey',
    pubKey            varchar(128)             not null comment '公钥',
    priKey            varchar(128)             not null comment '私钥',
    closeTime         bigint(13)               not null comment '过期时间',
    ipGroup           varchar(128)             not null comment 'ip地址',
    state             int                      not null comment '状态(0不可用、1可用)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_UserApi unique (userId, keyType, apiKey)
) comment '用户API';

drop table if exists UserInvite;
create table UserInvite
(
    id	             bigint(20)               not null comment 'ID' primary key,
    inviterId	     bigint(20)	              not null comment '邀请人用户ID（若系统为默认邀请人，可设为0或特定值）',
    inviteeId	     bigint(20)	              not null comment '被邀请人用户ID（必须唯一，不能重复被邀请）',
    inviteType	     VARCHAR(50)	          not null comment '邀请方式（如链接邀请、推荐码邀请）',
    inviteCodeUsed   VARCHAR(50)	                   comment '使用的推荐码（如果有的话）',
    isValid	         int                      not null comment '是否为有效邀请（是否完成实名认证后才算有效）',
    createTime	     bigint(13) 	          not null comment '邀请时间（即被邀请人注册时间）',
    updateTime       bigint(13)                        comment '更新时间',
    constraint index_UserInvite unique (inviteeId)
) comment '用户邀请关系';

drop table if exists UserRebate;
create table UserRebate
(
    id	           bigint(20)               not null comment 'ID' primary key,
    inviterId      bigint(20)	            not null comment '邀请人用户ID（若系统为默认邀请人，可设为0或特定值）',
    inviteeId	   bigint(20)	            not null comment '被邀请人用户ID（必须唯一，不能重复被邀请）',
    tradeId	       bigint(20)	            not null comment '关联交易ID（如订单ID）',
    symbol         VARCHAR(20)              not null comment '交易对（如 BTCUSDT, ETHUSDT）',
    tradeSide      VARCHAR(10)              not null comment '交易方向（buy/sell）',
    tradeAmount    decimal(22, 8)	        default 0 not null comment '交易金额',
    feeAmount	   decimal(22, 8)	        default 0 not null comment '手续费金额（真实产生）',
    rebateRate	   decimal(22, 8)	        default 0 not null comment '返佣比例（如 0.2 表示20%）',
    priceUSDT      decimal(22, 8)	        not null comment '换算汇率',
    rebateAmount   decimal(22, 8)	        default 0 not null comment '实际返佣金额（fee_amount *  priceUSDT × rebate_rate）',
    status	       VARCHAR(30)	            not null comment '状态（如 pending, settled, canceled）',
    settleDate	   DATE	                    not null comment '结算日期（可为空，直到结算时写入）',
    createTime	   bigint(13) 	            not null comment '创建时间',
    updateTime     bigint(13)                        comment '更新时间'
) comment '用户返佣记录';
