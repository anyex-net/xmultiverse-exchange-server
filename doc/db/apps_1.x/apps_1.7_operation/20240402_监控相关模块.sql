-- 钱包资产流水监控
DROP TABLE IF EXISTS MonitorWalletAssetFlows;
CREATE TABLE MonitorWalletAssetFlows (
  id                        bigint(20)          not null comment '主键ID' primary key,
  accountId                 bigint(20)          not null comment '账户ID',
  currency                  varchar(16)         not null comment '币种',
  lastMonitorTime           bigint(13)          not null comment '最后监控时间',
  monitorStatus             tinyint             not null comment '监控状态(0异常、1正常)',
  remark                    varchar(1024)                comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '钱包资产流水监控';

INSERT INTO MonitorWalletAssetFlows VALUES
(200000000000,  200000000000, 'PKR', 1709529514832, 1, 'remark', 1709529514832, 1709529514832);

commit;


-- 账户浮动盈亏监控
DROP TABLE IF EXISTS MonitorAccountProfitLoss;
CREATE TABLE MonitorAccountProfitLoss
(
    id              bigint(20)                    not null comment '主键ID/账户ID' primary key,
    balance         decimal(22, 8)      default 0 not null comment '余额',
    frozenBal       decimal(22, 8)      default 0 not null comment '冻结余额(不可用)',
    sumDeposit      decimal(22, 8)      default 0 not null comment '累计充值流入',
    sumWithDraw     decimal(22, 8)      default 0 not null comment '累计提现流出',
    sumAdjustAdd    decimal(22, 8)      default 0 not null comment '累计强增流入',
    sumAdjustSub    decimal(22, 8)      default 0 not null comment '累计强减流出',
    profitLoss      decimal(22, 8)      default 0 not null comment '浮动盈亏',
    updateTime      bigint(13)                             comment '更新时间'
) comment '账户浮动盈亏监控';

