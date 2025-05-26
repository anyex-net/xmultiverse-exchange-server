--平台币种
drop table if exists Currencies;
create table Currencies
(
    id                bigint(20)               not null comment '主键' primary key,
    currency          varchar(16)              not null comment '币种(BTC、ETH、USDT)',
    currencyName      varchar(32)              not null comment '币种中文名',
    blockchain        varchar(16)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    canDep            varchar(16)              not null comment '是否可充值 false表示不可链上充值 true表示可以链上充值',
    canWd             varchar(16)              not null comment '是否可提币 false表示不可链上提币 true表示可以链上提币',
    canInternal       varchar(16)              not null comment '是否可内部转账 false表示不可内部转账 true表示可以内部转账',
    minWd             decimal(12, 2) default 0 not null comment '币种单笔最小提币量',
    maxWd             decimal(12, 2) default 0 not null comment '币种单笔最大提币量',
    wdTickSz          int                      not null comment '提币精度 表示小数点后的位数',
    wdQuota           decimal(12, 2) default 0 not null comment '过去24小时内提币额度',
    usedWdQuota       decimal(12, 2) default 0 not null comment '过去24小时内已用提币额度',
    minFee            decimal(12, 8) default 0 not null comment '最小提币手续费数量',
    maxFee            decimal(12, 8) default 0 not null comment '最大提币手续费数量',
    mainNet           varchar(16)              not null comment '当前链是否为主链 如果是则返回true 否则返回false',
    state             varchar(16)              not null comment '币种状态 开放中live 关闭中closed',
    remark            varchar(32)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_Currencies unique (currency, blockchain)
) comment '平台币种';

--平台交易产品
drop table if exists Instruments;
create table Instruments
(
    id                bigint(20)               not null comment '主键' primary key,
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品ID 如BTC-USD-SWAP',
    uly               varchar(16)                       comment '标的指数 仅适用于instType为交割/永续/期权 如BTC-USD',
    category          varchar(16)              not null comment '手续费档位 每个交易产品属于哪个档位手续费',
    baseCcy           varchar(16)              not null comment '交易货币币种 如BTC-USDT中的BTC 仅适用于币币',
    quoteCcy          varchar(16)              not null comment '计价货币币种 如BTC-USDT中的USDT 仅适用于币币',
    settleCcy         varchar(16)              not null comment '盈亏结算和保证金币种 如BTC 仅适用于交割/永续/期权',
    ctVal             decimal(12, 4) default 0 not null comment '合约面值 仅适用于交割/永续/期权',
    ctMult            int                      not null comment '合约乘数 仅适用于交割/永续/期权',
    ctValCcy          varchar(16)              not null comment '合约面值计价币种 仅适用于交割/永续/期权',
    optType           varchar(16)              not null comment '期权类型 C或P 仅适用于期权',
    stk               decimal(12, 4) default 0 not null comment '行权价格 仅适用于期权',
    listTime          bigint(13)               not null comment '上线日期 Unix时间戳的毫秒数格式 如1597026383085',
    expTime           bigint(13)               not null comment '交割/行权日期 仅适用于交割和期权 Unix时间戳的毫秒数格式 如1597026383085',
    lever             int                      not null comment '该instId支持的最大杠杆倍数 不适用于币币、期权',
    tickSz            decimal(12, 4) default 0 not null comment '下单价格精度 如0.0001',
    lotSz             decimal(12, 4) default 0 not null comment '下单数量精度 如BTC-USDT-SWAP 1',
    minSz             decimal(12, 4) default 0 not null comment '最小下单数量',
    ctType            varchar(16)              not null comment '正向合约linear 反向合约inverse 仅适用于交割/永续',
    alias             varchar(16)              not null comment '合约日期别名 本周this_week 次周next_week 季度quarter 次季度next_quarter 仅适用于交割',
    state             varchar(16)              not null comment '产品状态 关闭中closed 交易中live 暂停中suspend 预上线preopen 资金费结算settlement',
    maxLmtSz          int                      not null comment '合约或现货限价单的单笔最大委托数量',
    maxMktSz          int                      not null comment '合约或现货市价单的单笔最大委托数量',
    maxTwapSz         int                      not null comment '合约或现货时间加权单的单笔最大委托数量',
    maxIcebergSz      int                      not null comment '合约或现货冰山委托的单笔最大委托数量',
    maxTriggerSz      int                      not null comment '合约或现货计划委托委托的单笔最大委托数量',
    maxStopSz         int                      not null comment '合约或现货止盈止损委托的单笔最大委托数量',
    tags              varchar(64)                       comment '板块分区标签',
    detailDesc        varchar(2048)                     comment '详情描述',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_Instruments unique (instType, instId)
) comment '平台交易产品';


--平台交易产品币种信息
drop table if exists InstrumentsCurrency;
create table InstrumentsCurrency
(
    id                      bigint(20)               not null comment '主键' primary key,
    instType                varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId                  varchar(16)              not null comment '产品ID 如BTC-USD-SWAP',
    blockchain              varchar(16)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    currencyName            varchar(32)              not null comment '币种中文名',
    currencyDesc            varchar(256)                      comment '资产介绍',
    currencyNum             varchar(16)              not null comment '资产总量',
    currencyContract        varchar(128)             not null comment '资产合约',
    currencyPmDesc          varchar(128)             not null comment '项目简介',
    currencyLink            varchar(256)             not null comment '资产链接',
    currencySite            varchar(256)                       comment '项目网址',
    currencyBrowser         varchar(256)             not null comment '区块浏览器',
    currencyWhitepaperUrl   varchar(256)             not null comment '白皮书',
    currencyX               varchar(64)              not null comment 'X',
    currencyTelegram        varchar(64)              not null comment 'Telegram',
    currencyLogoUrl         varchar(64)              not null comment 'Logo',
    remark                  varchar(128)                      comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    constraint index_InstrumentsCurrency unique (instType, instId, currencyName)
) comment '平台交易产品币种信息';


--用户交易产品收藏
drop table if exists UserInstrumentsFavorite;
create table UserInstrumentsFavorite
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品ID 如BTC-USD-SWAP',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_UserInstrumentsFavorite unique (userId, instType, instId)
) comment '用户交易产品收藏';
