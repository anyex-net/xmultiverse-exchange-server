-- 活动订单记录表
DROP TABLE IF EXISTS Order4Activity;
CREATE TABLE Order4Activity (
  id                        bigint(20)          not null comment '主键订单ID' primary key,
  orderTxNo                 varchar(64)         not null comment '订单编号',
  activityType              varchar(64)         not null comment '商品活动类型(TreasureHunt、HotDeals等)',
  activityId                bigint(20)          not null comment '商品活动ID',
  skuId                     bigint(20)          not null comment '商品ID',
  spuId                     bigint(20)          not null comment '产品ID',
  activitySkuPrice          decimal(22, 8)      not null comment '商品活动原价(冗余SKU价格)',
  accountId                 bigint(20)          not null comment '账户ID',
  orderActPrice             decimal(22, 8)      not null comment '订单活动价格(金额)',
  orderActBalancePayment    decimal(22, 8)      not null comment '订单活动尾款金额',
  orderActPurchaseNum       int                 not null comment '订单活动购买份数',
  orderSumBalance           decimal(22, 8)      not null comment '订单总金额',
  orderStatus               int                 not null comment '订单状态(0未开奖、1未中奖、2已中奖无需支付尾款、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已支付尾款但缺货等值现金充抵、10不支付尾款直接抵扣返现)',
  balanceDeductionAmount    decimal(22, 8)               comment '订单支付尾款钱包资产余额扣减金额(已支付尾款但缺货等值现金充抵情况下)',
  paymentActualAmount       decimal(22, 8)               comment '订单支付实际金额',
  paymentTime               bigint(13)                   comment '订单支付时间',
  paymentNo                 varchar(64)                  comment '订单支付编号',
  paymentStatus             int                          comment '订单支付状态(-1不用支付、0未支付、1已支付)',
  paymentDesc               varchar(128)                 comment '订单支付描述',
  activitySumNum            int                 not null comment '活动一轮总份数',
  activitySumRound          bigint              not null comment '活动总轮数',
  activityCurrentRound      bigint              not null comment '活动当前轮数',
  isLotteryDrawn            tinyint(1)          not null comment '订单是否开奖(0未开奖、1已开奖)',
  isWinning                 tinyint(1)          not null comment '订单是否中奖(0未中奖、1已中奖)',
  isClaimLottery            tinyint(1)          not null comment '订单是否已领中奖(0未领、1已领)',
  remark                    varchar(128)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '活动订单记录表';

INSERT INTO Order4Activity VALUES
(200000000000, 'Order4Activity202403070000001', 'HotDeals', 200000000000, 1, 1, 20000, 200000000000, 300.00, 1000.00, 1, 1300.00, 0, 0, null, null, null, null, null, 200, 1, 1, 0, 0, 0, 'remark', 1709529514832, 1709529514832);
INSERT INTO Order4Activity VALUES
(200000000001, 'Order4Activity202403070000002', 'TreasureHunt', 200000000000, 1, 1, 20000, 200000000000, 300.00, 0.00, 1, 0.00, 0, 0, null, null, null, null, null, 200, 1, 1, 0, 0, 0, 'remark', 1709529514832, 1709529514832);

commit;


-- 游戏订单记录表
DROP TABLE IF EXISTS Order4Game;
CREATE TABLE Order4Game (
  id                        bigint(20)          not null comment '主键订单ID' primary key,
  orderTxNo                 varchar(64)         not null comment '订单编号',
  gameId                    bigint(20)          not null comment '游戏ID',
  accountId                 bigint(20)          not null comment '账户ID',
  gameExpendBalance         int                 not null comment '游戏花费金额',
  gamePrizeId               bigint(20)          not null comment '游戏中奖奖品ID',
  gamePrizeName             varchar(64)         not null comment '游戏中奖奖品名称',
  gameRewardBalance         int                 not null comment '游戏中奖奖励金额',
  orderStatus               tinyint(1)          not null comment '订单状态(0未中奖、1已中奖)',
  isWinning                 tinyint(1)          not null comment '订单是否中奖(0未中奖、1已中奖)',
  remark                    varchar(128)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '游戏订单记录表';

INSERT INTO Order4Game VALUES
(200000000000, 'Order4Game202403100000001', 200000000000, 200000000000, 50, 200000000000, 'It is a pity', 0, 0, 0, 'remark', 1709529514832, 1709529514832);
INSERT INTO Order4Game VALUES
(200000000001, 'Order4Game202403100000002', 200000000000, 200000000000, 50, 200000000006, '50', 50, 1, 1, 'remark', 1709529514832, 1709529514832);

commit;

