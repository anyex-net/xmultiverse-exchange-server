drop table if exists InstTradeFee;
create table InstTradeFee
(
    id                bigint(20)               not null comment '主键' primary key,
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品Id 如BTC-USD-SWAP',
    uly               varchar(16)                       comment '标的指数 仅适用于instType为交割/永续/期权 如BTC-USD',
    taker             decimal(12, 8) default 0 not null comment '吃单手续费率 永续和交割合约时 为币本位U本位合约费率',
    maker             decimal(12, 8) default 0 not null comment '挂单手续费率 永续和交割合约时 为币本位U本位合约费率',
    delivery          decimal(12, 8) default 0 not null comment '交割手续费率',
    exercise          decimal(12, 8) default 0 not null comment '行权手续费率',
    createDate        bigint(13)               not null comment '创建时间',
    updateDate        bigint(13)                        comment '更新时间',
    remark            varchar(32)                       comment '备注',
    constraint index_InstTradeFee unique (instType, instId)
) comment '平台交易手续费费率表';

drop table if exists UserInstTradeFee;
create table UserInstTradeFee
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品Id 如BTC-USD-SWAP',
    uly               varchar(16)                       comment '标的指数 仅适用于instType为交割/永续/期权 如BTC-USD',
    level             varchar(16)              not null comment '手续费等级',
    taker             decimal(12, 8) default 0 not null comment '吃单手续费率 永续和交割合约时 为币本位U本位合约费率',
    maker             decimal(12, 8) default 0 not null comment '挂单手续费率 永续和交割合约时 为币本位U本位合约费率',
    delivery          decimal(12, 8) default 0 not null comment '交割手续费率',
    exercise          decimal(12, 8) default 0 not null comment '行权手续费率',
    createDate        bigint(13)               not null comment '创建时间',
    updateDate        bigint(13)                        comment '更新时间',
    remark            varchar(32)                       comment '备注',
    constraint index_UserInstTradeFee unique (instType, instId)
) comment '用户交易手续费费率表';
