--RWA认证机构投资者
drop table if exists RwaCertInstInvestor;
create table RwaCertInstInvestor
(
    id                bigint(20)               not null comment '账户ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportNo        varchar(64)              not null comment '证件号',
    attachments       varchar(2048)            not null comment '附件信息',
    state             varchar(16)              not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark            varchar(64)                       comment '备注',
    constraint index_RwaCertInstInvestor unique (userId)
) comment 'RWA认证机构投资者';

--RWA认证机构SPV发起人
drop table if exists RwaCertInstSpvPromoter;
create table RwaCertInstSpvPromoter
(
    id                bigint(20)               not null comment '账户ID' primary key,
    userId            bigint(20)               not null comment '用户ID',
    surName           varchar(32)              not null comment '姓氏',
    realName          varchar(32)              not null comment '名字',
    region            varchar(32)              not null comment '国家地区',
    passportNo        varchar(64)              not null comment '证件号',
    attachments       varchar(2048)            not null comment '附件信息',
    state             varchar(16)              not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark            varchar(64)                       comment '备注',
    constraint index_RwaCertInstSpvPromoter unique (userId)
) comment 'RWA认证机构SPV发起人';

--RWA账户余额
drop table if exists RwaBalances;
create table RwaBalances
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(8)               not null comment '币种(BTC、ETH、USDT)',
    balance           decimal(20, 8) default 0 not null comment '余额',
    frozenBal         decimal(20, 8) default 0 not null comment '冻结(不可用)',
    availBal          decimal(20, 8) default 0 not null comment '可用余额',
    remark            varchar(16)                       comment '备注',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_RwaBalances unique (userId, currency)
) comment 'RWA账户余额';

--RWA账户交易历史
--包含业务：转入transferIn、转出transferOut、冻结forzen、解冻unforzen、申购purchase、赎回redemption、分红dividend
drop table if exists RwaBalancesTransHistory;
create table RwaBalancesTransHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    type              varchar(32)              not null comment '类型(转入、转出、冻结、解冻、申购、分红)',
    beforeBal         decimal(22, 8) default 0 not null comment '前余额',
    changeAmt         decimal(22, 8) default 0 not null comment '发生数量',
    afterBal          decimal(22, 8) default 0 not null comment '后余额',
    businessId        varchar(64)                       comment '原业务ID',
    fromAcct          varchar(64)                       comment '转出账户',
    toAcct            varchar(64)                       comment '转入账户',
    state             varchar(16)              not null comment '状态(成功success、处理中pending、失败failed)',
    transDesc         varchar(128)             not null comment '交易描述',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间'
) comment 'RWA账户交易历史';



/*
drop table if exists OrdersRwa;
create table OrdersRwa
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
) comment 'RWA交易普通订单信息表';

drop table if exists FillsRwa;
create table FillsRwa
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
) comment 'RWA交易成交明细表';
*/
