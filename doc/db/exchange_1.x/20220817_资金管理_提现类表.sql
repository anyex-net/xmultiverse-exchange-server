--提现历史
--包含业务：提现withdraw
drop table if exists WithdrawalHistory;
create table WithdrawalHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    fromAddress       varchar(64)              not null comment '提地址',
    toAddress         varchar(64)              not null comment '收地址',
    amount            decimal(22, 8) default 0 not null comment '提现数量',
    fee               decimal(22, 8) default 0 not null comment '提现手续费',
    transId           varchar(64)                       comment '交易ID',
    state             varchar(16)              not null comment '提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    checkBy           varchar(32)                       comment '复核人',
    checkTime         bigint(13)                        comment '复核时间',
    constraint index_WithdrawalHistory unique (userId, currency, blockchain, transId)
) comment '提现历史';



/*
drop table if exists WithdrawalWallet;
create table WithdrawalWallet
(
    id                bigint(20)               not null comment '主键' primary key,
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    walletId          varchar(64)              not null comment '钱包Id',
    walletName        varchar(64)              not null comment '钱包名称',
    walletType        varchar(64)              not null comment '钱包类型(withdrawalHot付款热钱包 withdrawalCold付款冷钱包 withdrawalFee归集费用钱包)',
    walletPwd         varchar(256)             not null comment '钱包密码(密文)',
    sign              varchar(64)              not null comment 'sign',
    randomKey         varchar(128)             not null comment 'randomKey',
    createDate        bigint(13)               not null comment '创建时间',
    remark            varchar(64)                       comment '备注',
    constraint index_WithdrawalWallet unique (currency, blockchain, walletId, walletType)
) comment '提现钱包表';

drop table if exists WithdrawalHistory;
create table WithdrawalHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    fromAddress       varchar(64)              not null comment '提地址',
    toAddress         varchar(64)              not null comment '收地址',
    amount            decimal(22, 8) default 0 not null comment '提现数量',
    fee               decimal(22, 8) default 0 not null comment '提现手续费',
    transId           varchar(64)                       comment '交易ID',
    state             varchar(16)              not null comment '提现状态 canceled已撤销 applied已申请 checked已复核 exported已汇出',
    createDate        bigint(13)               not null comment '创建时间',
    checkDate         bigint(13)                        comment '复核时间',
    updateDate        bigint(13)                        comment '更新时间',
    remark            varchar(64)                       comment '备注',
    constraint index_WithdrawalHistory unique (userId, currency, blockchain, transId)
) comment '提现历史表';
*/
