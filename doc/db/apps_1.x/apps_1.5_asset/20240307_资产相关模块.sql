-- 钱包资产表
DROP TABLE IF EXISTS WalletAsset;
CREATE TABLE WalletAsset (
  id                        bigint(20)               not null comment '主键订单ID' primary key,
  accountId                 bigint(20)               not null comment '账户ID',
  currency                  varchar(16)              not null comment '币种(法币、BTC、ETH、USDT)',
  balance                   decimal(22, 8) default 0 not null comment '余额',
  frozenBal                 decimal(22, 8) default 0 not null comment '冻结(不可用)',
  updateTime                bigint(13)                        comment '更新时间',
  remark                    varchar(64)                       comment '备注'
) comment '钱包资产表';

INSERT INTO WalletAsset VALUES
(200000000000, 200000000000, 'PKR', 1000.00, 0.0, 1709529514832, 'remark');
commit;

-- 钱包资产流水表
drop table if exists WalletAssetFlows;
create table WalletAssetFlows
(
    id                  bigint(20)               not null comment '主键' primary key,
    accountId           bigint(20)               not null comment '账户ID',
    currency            varchar(32)              not null comment '币种(法币、BTC、ETH、USD)',
    businessCategory    varchar(64)              not null comment '业务分类(收入revenue、支出expend等)',
    businessType        varchar(64)              not null comment '业务类型(充值deposit、提现withDraw等)',
    beforeBalance       decimal(22, 8)           not null comment '变动前余额',
    balance             decimal(22, 8)           not null comment '变动发生额',
    fee                 decimal(22, 8)           not null comment '手续费',
    direction           varchar(4)               not null comment '变动方向(+、-)',
    afterBalance        decimal(22, 8)           not null comment '变动后余额',
    orgBusinessId       bigint(20)               not null comment '原业务Id',
    orgBusinessNo       varchar(64)              not null comment '原业务No',
    status              tinyint(1)               not null comment '状态(0无效、1有效)',
    createTime          bigint(13)               not null comment '创建时间',
    updateTime          bigint(13)                        comment '更新时间',
    remark              varchar(128)                      comment '备注'
) comment '钱包资产流水表';

INSERT INTO WalletAssetFlows VALUES
(200000000000, 200000000000, 'PKR', 'revenue', 'deposit', 0.0, 1000.00, 0.0, '+', 1000.0, 1, 1, 1, 1709529514832, 1709529514832, 'remark');
commit;


-- 钱包资产转账记录表 充值支付、提现支付等
drop table if exists WalletAssetTransactions;
create table WalletAssetTransactions
(
    id                      bigint(20)               not null comment '主键' primary key,
    accountId               bigint(20)               not null comment '账户ID',
    currency                varchar(32)              not null comment '币种(法币、BTC、ETH、USD)',
    trxType                 varchar(32)              not null comment '交易类型(充值deposit、提现withDraw)',
    trxAmount               decimal(22, 8)           not null comment '转账金额',
    trxActAmount            decimal(22, 8)           not null comment '实际转账金额',
    trxFee                  decimal(22, 8)           not null comment '手续费',
    trxTime                 bigint(13)               not null comment '转账时间',
    trxNo                   varchar(64)              not null comment '转账编号',
    trxStatus               varchar(16)              not null comment '转账状态(成功success、处理中pending、失败failed)',
    trxDesc                 varchar(1024)            not null comment '转账描述',
    queryDesc               varchar(1024)                     comment '转账查询描述',
    trxChannel              varchar(64)              not null comment '转账渠道',
    trxAccountType          varchar(32)              not null comment '账户类型(BANK、WALLET)',
    trxAccountNo            varchar(32)              not null comment '收款账号(手机号码)',
    trxAccountName          varchar(64)              not null comment '收款姓名',
    trxBankName             varchar(64)                       comment '银行名字',
    trxIban                 varchar(64)                       comment '国际银行账户号码(InternationalBankAccountNumber)',
    trxCnic                 varchar(32)              not null comment '身份证号码',
    trxEmail                varchar(32)              not null comment '邮箱',
    trxMobile               varchar(16)              not null comment '手机号码',
    createTime              bigint(13)               not null comment '创建时间',
    updateTime              bigint(13)                        comment '更新时间',
    remark                  varchar(128)                      comment '备注',
    platTrxNo               varchar(64)                       comment '平台交易流水号'
) comment '钱包资产转账记录表';


-- 钱包资产调整记录表
drop table if exists WalletAssetAdjust;
create table WalletAssetAdjust
(
    id                  bigint(20)               not null comment '主键' primary key,
    accountId           bigint(20)               not null comment '账户ID',
    currency            varchar(32)              not null comment '币种(法币、BTC、ETH、USD)',
    adjustTrxNo         varchar(64)              not null comment '调整交易编号',
    adjustType          varchar(64)              not null comment '调整类型(强增assetAdjustAdd、强减assetAdjustSub)',
    adjustBalance       decimal(22, 8)           not null comment '调整金额',
    attachment          varchar(128)                      comment '凭证附件url',
    status              tinyint(1)               not null comment '状态(0无效、1有效)',
    createTime          bigint(13)               not null comment '创建时间',
    updateTime          bigint(13)                        comment '更新时间',
    remark              varchar(128)                      comment '备注'
) comment '钱包资产调整记录表';


-- 钱包资产打赏礼物记录表
drop table if exists WalletAssetTipGift;
create table WalletAssetTipGift
(
    id                  bigint(20)               not null comment '主键' primary key,
    fromAccountId       bigint(20)               not null comment '来源账户ID',
    toAccountId         bigint(20)               not null comment '去处账户ID',
    currency            varchar(32)              not null comment '币种(法币、BTC、ETH、USD)',
    trxNo               varchar(64)              not null comment '交易编号',
    trxBalance          decimal(22, 8)           not null comment '金额',
    trxFee              decimal(22, 8)           not null comment '手续费',
    status              tinyint(1)               not null comment '状态(1已送出待接收、2已接收)',
    createTime          bigint(13)               not null comment '创建时间',
    updateTime          bigint(13)                        comment '更新时间',
    remark              varchar(128)                      comment '备注'
) comment '钱包资产打赏礼物记录';

