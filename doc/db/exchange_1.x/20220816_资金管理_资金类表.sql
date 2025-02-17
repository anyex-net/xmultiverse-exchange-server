--资金账户余额
drop table if exists Balances;
create table Balances
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    balance           decimal(22, 8) default 0 not null comment '余额',
    frozenBal         decimal(22, 8) default 0 not null comment '冻结(不可用)',
    availBal          decimal(22, 8) default 0 not null comment '可用余额',
    remark            varchar(64)                       comment '备注',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_Balances unique (userId, currency)
) comment '资金账户余额';

--资金账户交易历史
--包含业务：充值deposit、提现withdraw、冻结forzen、解冻unforzen、转入transferIn、转出transferOut
drop table if exists BalancesTransHistory;
create table BalancesTransHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    type              varchar(32)              not null comment '类型(充值、提现、冻结、解冻、转入、转出)',
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
) comment '资金账户交易历史';



/*
--资金账户划转历史
drop table if exists BalancesTransferHistory;
create table BalancesTransferHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '划转币种(BTC、ETH、USDT)',
    type              varchar(32)              not null comment '类型(0账户内划转、1母账户转子账户、2子账户转母账户)',
    fromAcct          varchar(64)              not null comment '转出账户',
    amount            decimal(22, 8) default 0 not null comment '划转量',
    toAcct            varchar(64)              not null comment '转入账户',
    subAcct           varchar(64)                       comment '子账户名称',
    fromInstId        varchar(64)              not null comment '币币杠杆转出币对(如BTC-USDT)或者转出合约的underlying(如BTC-USD)',
    toInstId          varchar(64)              not null comment '币币杠杆转入币对(如BTC-USDT)或者转入合约的underlying(如BTC-USD)',
    state             varchar(16)              not null comment '转账状态(成功success、处理中pending、失败failed)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
) comment '资金账户划转历史';
*/
