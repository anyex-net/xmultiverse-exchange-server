/*
drop table if exists BalancesSpot;
create table BalancesSpot
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(8)               not null comment '币种(BTC ETH USDT)',
    balance           decimal(20, 8) default 0 not null comment '余额',
    frozenBal         decimal(20, 8) default 0 not null comment '冻结(不可用)',
    availBal          decimal(20, 8) default 0 not null comment '可用余额',
    updateDate        bigint(13)                        comment '更新时间',
    remark            varchar(16)                       comment '备注',
    constraint index_BalancesSpot unique (userId, currency)
) comment '现货交易账户余额表';

drop table if exists OrdersSpot;
create table OrdersSpot
(
    id                bigint(20)               not null comment '主键 订单ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品ID',
    tgtCcy            varchar(8)               not null comment '市价单委托数量的类型base_ccy:交易货币 quote_ccy:计价货币',
    ccy               varchar(8)               not null comment '保证金币种，仅适用于单币种保证金模式下的全仓币币杠杆订单',
    clOrdId           varchar(16)              not null comment '客户自定义订单ID',
    tag               varchar(16)              not null comment '订单标签',
    px                decimal(20, 8) default 0 not null comment '委托价格',
    sz                decimal(20, 8) default 0 not null comment '委托数量',
    pnl               decimal(20, 8) default 0 not null comment '收益',
    ordType           varchar(16)              not null comment '订单类型 market:市价单 limit:限价单 post_only:只做maker单 fok:全部成交或立即取消 ioc:立即成交并取消剩余 optimal_limit_ioc:市价委托立即成交并取消剩余（仅适用交割、永续）',
    side              varchar(8)               not null comment '订单方向 buy买 sell卖',
    posSide           varchar(8)               not null comment '持仓方向 long多 short空 单向持仓模式返回net',
    tdMode            varchar(8)               not null comment '交易模式 保证金模式 isolated:逐仓 cross:全仓 非保证金模式 cash:非保证金',
    accFillSz         decimal(20, 8) default 0 not null comment '累计成交数量',
    avgPx             decimal(20, 8) default 0 not null comment '成交均价',
    state             varchar(16)              not null comment '订单状态 canceled:撤单成功 live:等待成交 partially_filled:部分成交 filled:完全成交',
    lever             decimal(8, 4) default 0  not null comment '杠杆倍数，0.01到125之间的数值，仅适用于 币币杠杆/交割/永续',
    feeCcy            varchar(8)               not null comment '交易手续费币种',
    fee               decimal(20, 8) default 0 not null comment '订单交易手续费，平台向用户收取的交易手续费，手续费扣除为负数。如: -0.01',
    source            varchar(8)               not null comment '订单来源 13:策略委托单触发后的生成的限价单',
    rebateCcy         varchar(8)               not null comment '返佣金币种',
    rebate            decimal(20, 8) default 0 not null comment '返佣金额，平台向达到指定lv交易等级的用户支付的挂单奖励（返佣），如果没有返佣金，该字段为“”。手续费返佣为正数，如：0.01',
    category          varchar(16)              not null comment '订单种类 normal:普通委托 twap:TWAP自动换币 adl:ADL自动减仓 full_liquidation:强制平仓 partial_liquidation:强制减仓 delivery:交割 ddh:对冲减仓类型订单',
    uTime             bigint(13)               not null comment '订单状态更新时间，Unix时间戳的毫秒数格式，如:1597026383085',
    cTime             bigint(13)               not null comment '订单创建时间，Unix时间戳的毫秒数格式， 如:1597026383085',
    remark            varchar(16)                       comment '备注'
) comment '现货交易普通订单信息表';

drop table if exists OrdersSpotSLTP;
create table OrdersSpotSLTP
(
    id                bigint(20)               not null comment '主键 订单ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品ID',
    tgtCcy            varchar(8)               not null comment '市价单委托数量的类型base_ccy:交易货币 quote_ccy:计价货币',
    ccy               varchar(8)               not null comment '保证金币种，仅适用于单币种保证金模式下的全仓币币杠杆订单',
    clOrdId           varchar(16)              not null comment '客户自定义订单ID',
    tag               varchar(16)              not null comment '订单标签',
    ordType           varchar(16)              not null comment '订单类型 market:市价单 limit:限价单 post_only:只做maker单 fok:全部成交或立即取消 ioc:立即成交并取消剩余 optimal_limit_ioc:市价委托立即成交并取消剩余（仅适用交割、永续）',
    side              varchar(8)               not null comment '订单方向 buy买 sell卖',
    posSide           varchar(8)               not null comment '持仓方向 long多 short空 单向持仓模式返回net',
    tdMode            varchar(8)               not null comment '交易模式 保证金模式 isolated:逐仓 cross:全仓 非保证金模式 cash:非保证金',
    state             varchar(16)              not null comment '订单状态 canceled:撤单成功 live:等待成交 partially_filled:部分成交 filled:完全成交',
    lever             decimal(8, 4) default 0  not null comment '杠杆倍数，0.01到125之间的数值，仅适用于 币币杠杆/交割/永续',

    tpTriggerPx       decimal(20, 8) default 0 not null comment '止盈触发价',
    tpTriggerPxType   varchar(8)               not null comment '止盈触发价类型 last:最新价格 index:指数价格 mark:标记价格',
    tpOrdPx           decimal(20, 8) default 0 not null comment '止盈委托价',
    slTriggerPx       decimal(20, 8) default 0 not null comment '止损触发价',
    slTriggerPxType   varchar(8)               not null comment '止损触发价类型 last:最新价格 index:指数价格 mark:标记价格',
    slOrdPx           decimal(20, 8) default 0 not null comment '止损委托价',

    source            varchar(8)               not null comment '订单来源 13:策略委托单触发后的生成的限价单',
    category          varchar(16)              not null comment '订单种类 normal:普通委托 twap:TWAP自动换币 adl:ADL自动减仓 full_liquidation:强制平仓 partial_liquidation:强制减仓 delivery:交割 ddh:对冲减仓类型订单',
    uTime             bigint(13)               not null comment '订单状态更新时间，Unix时间戳的毫秒数格式，如:1597026383085',
    cTime             bigint(13)               not null comment '订单创建时间，Unix时间戳的毫秒数格式， 如:1597026383085',
    remark            varchar(16)                       comment '备注'
) comment '现货交易止盈止损订单表';

drop table if exists FillsSpot;
create table FillsSpot
(
    id                bigint(20)               not null comment '主键 成交ID' primary key,
    instType          varchar(16)              not null comment '产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION',
    instId            varchar(16)              not null comment '产品ID',
    buyUserId         bigint(20)               not null comment '用户ID',
    sellUserId        bigint(20)               not null comment '用户ID',
    buyOrdId          bigint(20)               not null comment '订单ID',
    sellOrdId         bigint(20)               not null comment '订单ID',
    fillPx            decimal(20, 8) default 0 not null comment '成交价格',
    fillSz            decimal(20, 8) default 0 not null comment '成交数量',
    side              varchar(8)               not null comment '成交方向 buy买 sell卖',
    buyPosSide        varchar(8)               not null comment '持仓方向 long多 short空 单向持仓模式返回net',
    sellPosSide       varchar(8)               not null comment '持仓方向 long多 short空 单向持仓模式返回net',
    buyExecType       varchar(8)               not null comment '流动性方向 T taker M maker',
    sellExecType      varchar(8)               not null comment '流动性方向 T taker M maker',
    buyFeeCcy         varchar(8)               not null comment '交易手续费币种或者返佣金币种',
    buyFeeRate        decimal(20, 8) default 0 not null comment '手续费金额或者返佣金额对应费率或绝对值 手续费扣除 为‘负数’，如-0.01; 手续费返佣 为‘正数’，如0.01',
    buyFee            decimal(20, 8) default 0 not null comment '手续费金额或者返佣金额 手续费扣除 为‘负数’，如-0.01; 手续费返佣 为‘正数’，如0.01',
    sellFeeCcy        varchar(8)               not null comment '交易手续费币种或者返佣金币种',
    sellFeeRate       decimal(20, 8) default 0 not null comment '手续费金额或者返佣金额对应费率或绝对值 手续费扣除 为‘负数’，如-0.01; 手续费返佣 为‘正数’，如0.01',
    sellFee           decimal(20, 8) default 0 not null comment '手续费金额或者返佣金额 手续费扣除 为‘负数’，如-0.01; 手续费返佣 为‘正数’，如0.01',
    ts                bigint(13)               not null comment '成交明细产生时间，Unix时间戳的毫秒数格式，如 1597026383085',
    remark            varchar(16)                       comment '备注'
) comment '现货交易成交明细表';
*/
