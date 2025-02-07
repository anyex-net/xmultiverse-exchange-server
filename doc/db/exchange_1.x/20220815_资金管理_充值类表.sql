--充值地址
drop table if exists DepositAddress;
create table DepositAddress
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    depositAddress    varchar(64)              not null comment '用户充值地址',
    accDeposit        decimal(22, 8) default 0 not null comment '累计充值(包含未确认)',
    unconfAccDeposit  decimal(22, 8) default 0 not null comment '未确认累计充值',
    sign              varchar(64)              not null comment 'sign',
    randomKey         varchar(128)             not null comment 'randomKey',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_DepositAddress unique (userId, currency, blockchain, depositAddress)
) comment '充值地址';

--充值交易历史
--包含业务：充值deposit
drop table if exists DepositTransHistory;
create table DepositTransHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC、ETH、TRON、BSC)',
    depositAddress    varchar(64)              not null comment '用户充值地址',
    transId           varchar(64)              not null comment '交易ID',
    amount            decimal(22, 8) default 0 not null comment '充值数量',
    netFee            decimal(22, 8) default 0 not null comment '网络手续费',
    confirmState      varchar(16)              not null comment '确认状态(unconfirm未确认、confirmed已确认)',
    depositState      varchar(16)              not null comment '充值入账状态(undeposit未入账、deposited已入账)',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间',
    updateBy          varchar(32)                       comment '更新人',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_DepositTransHistory unique (userId, transId)
) comment '充值交易历史';



/*
drop table if exists DepositWallet;
create table DepositWallet
(
    id                bigint(20)               not null comment '主键' primary key,
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    walletId          varchar(64)              not null comment '钱包Id',
    walletName        varchar(64)              not null comment '钱包名称',
    walletType        varchar(64)              not null comment '钱包类型(deposit充值 collect归集)',
    sign              varchar(64)              not null comment 'sign',
    randomKey         varchar(128)             not null comment 'randomKey',
    createDate        bigint(13)               not null comment '创建时间',
    remark            varchar(64)                       comment '备注',
    constraint index_DepositWallet unique (currency, blockchain, walletId, walletType)
) comment '充值钱包表';


drop table if exists DepositWalletAddress;
create table DepositWalletAddress
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    walletId          varchar(64)              not null comment '钱包Id',
    walletName        varchar(64)              not null comment '钱包名称',
    depositAddress    varchar(64)              not null comment '钱包对应用户充值地址',
    accDeposit        decimal(22, 8) default 0 not null comment '累计充值(包含未确认)',
    unconfAccDeposit  decimal(22, 8) default 0 not null comment '未确认累计充值',
    sign              varchar(64)              not null comment 'sign',
    randomKey         varchar(128)             not null comment 'randomKey',
    createDate        bigint(13)               not null comment '创建时间',
    remark            varchar(64)                       comment '备注',
    constraint index_DepositWalletAddress unique (userId, currency, blockchain, walletId, depositAddress)
) comment '钱包对应地址表';

drop table if exists BlockInfo;
create table BlockInfo
(
    id                bigint(20)               not null comment '主键' primary key,
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    hash              varchar(64)              not null comment 'hash',
    parentHash        varchar(64)              not null comment 'parentHash',
    height            bigint(20)               not null comment 'height',
    blockTimeStamp    bigint(13)               not null comment 'blockTimeStamp',
    transScanState    varchar(32)              not null comment '区块是否已经扫描对应交易true或false',
    remark            varchar(64)                       comment '备注'
) comment '区块信息表';

drop table if exists BlockTransDepositHistory;
create table BlockTransDepositHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC ETH USDT)',
    blockchain        varchar(32)              not null comment '区块链(BTC ETH TRON USDT-ERC20)',
    walletId          varchar(64)              not null comment '钱包Id',
    depositAddress    varchar(64)              not null comment '钱包对应用户充值地址',
    transId           varchar(64)              not null comment '交易ID',
    amount            decimal(22, 8) default 0 not null comment '充值数量',
    netFee            decimal(22, 8) default 0 not null comment '网络手续费',
    state             varchar(16)              not null comment '确认状态(unconfirm未确认 confirmed已确认)',
    createDate        bigint(13)               not null comment '创建时间',
    remark            varchar(64)                       comment '备注',
    constraint index_BlockTransDepositHistory unique (userId, transId)
) comment '区块交易充值表';
*/
