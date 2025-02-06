drop table if exists Balances;
create table Balances
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    balance           decimal(22, 8) default 0 not null comment '余额',
    frozenBal         decimal(22, 8) default 0 not null comment '冻结(不可用)',
    availBal          decimal(22, 8) default 0 not null comment '可用余额',
    updateDate        bigint(13)                        comment '更新时间',
    remark            varchar(64)                       comment '备注',
    constraint index_Balances unique (userId, currency)
) comment '资金账户余额表';

drop table if exists TransferHistory;
create table TransferHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '划转币种(BTC ETH USDT)',
    type              varchar(32)              not null comment '0账户内划转 1母账户转子账户 2子账户转母账户',
    fromAcct          varchar(64)              not null comment '转出账户',
    amount            decimal(22, 8) default 0 not null comment '划转量',
    toAcct            varchar(64)              not null comment '转入账户',
    subAcct           varchar(64)                       comment '子账户名称',
    fromInstId        varchar(64)              not null comment '币币杠杆转出币对(如BTC-USDT)或者转出合约的underlying(如BTC-USD)',
    toInstId          varchar(64)              not null comment '币币杠杆转入币对(如BTC-USDT)或者转入合约的underlying(如BTC-USD)',
    state             varchar(16)              not null comment '转账状态 成功success 处理中pending 失败failed',
    createDate        bigint(13)               not null comment '创建时间',
    remark            varchar(64)                       comment '备注'
) comment '资金划转历史表';
