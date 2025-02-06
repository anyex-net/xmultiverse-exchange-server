
-- 商品活动一元夺宝表
DROP TABLE IF EXISTS ActivityTreasureHunt;
CREATE TABLE ActivityTreasureHunt (
  id                        bigint(20)          not null comment '主键' primary key,
  activityName              varchar(128)        not null comment '活动名称',
  skuId                     bigint(20)          not null comment '商品ID',
  spuId                     bigint(20)          not null comment '产品ID',
  treasurePrice             decimal(22, 8)      not null comment '夺宝价格(金额)',
  treasureSumNum            int                 not null comment '夺宝一轮总份数',
  treasureRobotNum          int                 not null comment '夺宝一轮机器人份数',
  treasureSumRound          bigint              not null comment '夺宝总轮数',
  treasureStartTime         bigint(13)          not null comment '夺宝开始时间',
  treasureEndTime           bigint(13)          not null comment '夺宝结束时间',
  status                    tinyint(1)          not null comment '夺宝活动是否启用(0未启用、1启用)',
  actCurrentRound           bigint              not null comment '夺宝当前轮数',
  actCurrentPurchasedNum    int                 not null comment '夺宝当前轮已购买份数',
  actCurrentAccountNum      int                 not null comment '夺宝当前轮已参加账户数',
  remark                    varchar(256)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '活动一元夺宝表';

INSERT INTO ActivityTreasureHunt VALUES
(200000000000, '一元夺宝V1', 1, 1, 3299.00, 200, 1, 1, 1709529514832, 1709529514832, 1, 1, 0, 0, 'remark', 1709529514832, 1709529514832);
commit;

-- 商品活动半价购买表
DROP TABLE IF EXISTS ActivityHotDeals;
CREATE TABLE ActivityHotDeals (
  id                        bigint(20)          not null comment '主键' primary key,
  activityName              varchar(128)        not null comment '活动名称',
  skuId                     bigint(20)          not null comment '商品ID',
  spuId                     bigint(20)          not null comment '产品ID',
  activityPrice             decimal(22, 8)      not null comment '活动价格(金额)',
  balancePayment            decimal(22, 8)      not null comment '活动尾款金额',
  activitySumNum            int                 not null comment '活动一轮总份数',
  activityRobotNum          int                 not null comment '活动一轮机器人份数',
  activitySumRound          bigint              not null comment '活动总轮数',
  activitySumStock          bigint              not null comment '活动总库存',
  activityStartTime         bigint(13)          not null comment '活动开始时间',
  activityEndTime           bigint(13)          not null comment '活动结束时间',
  status                    tinyint(1)          not null comment '活动是否启用(0未启用、1启用)',
  actCurrentRound           bigint              not null comment '活动当前轮数',
  actCurrentPurchasedNum    int                 not null comment '活动当前轮已购买份数',
  actCurrentAccountNum      int                 not null comment '夺宝当前轮已参加账户数',
  remark                    varchar(256)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '活动半价购买表';

INSERT INTO ActivityHotDeals VALUES
(200000000000, '活动半价购买V1', 1, 2, 300.00, 1000.00, 200, 1, 1, 0, 1709529514832, 1709529514832, 1, 1, 0, 0, 'remark', 1709529514832, 1709529514832);
commit;


-- 商品活动操作记录表
drop table if exists ActivityOperRecord;
create table ActivityOperRecord
(
    id                      bigint(20)               not null comment 'ID' primary key,
    activityId              bigint(20)               not null comment '商品活动ID',
    activityType            varchar(64)              not null comment '商品活动类型(TreasureHunt、HotDeals等)',
    requestIp               varchar(64)                       comment '请求IP',
    accountId               bigint(20)                        comment '账户Id',
    operType                varchar(16)              not null comment '操作类型(浏览browse、favorite收藏、praise点赞、comment评论)',
    operContent             varchar(128)                      comment '操作内容(评论内容)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
  updateTime                bigint(13)                        comment '更新时间'
) comment '活动操作记录表';
