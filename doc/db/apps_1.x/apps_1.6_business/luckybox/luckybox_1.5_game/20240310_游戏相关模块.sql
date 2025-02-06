-- 游戏信息表
DROP TABLE IF EXISTS Game;
CREATE TABLE Game (
  id                        bigint(20)          not null comment '主键ID' primary key,
  name                      varchar(64)         not null comment '游戏名称',
  gameImgUrl                varchar(64)         not null comment '游戏图片url',
  gameChips                 decimal(12, 2)      not null comment '游戏参与筹码(金额)',
  status                    tinyint(1)          not null comment '游戏是否启用(0未启用、1启用)',
  remark                    varchar(128)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '游戏信息表';

INSERT INTO Game VALUES
(200000000000,  'Lucky wheel', 'lucky/LuckyWheel.png', 100, 1, 'remark', 1709529514832, 1709529514832);

commit;


-- 游戏奖品表
DROP TABLE IF EXISTS GamePrize;
CREATE TABLE GamePrize (
  id                        bigint(20)          not null comment '主键ID' primary key,
  gameId                    bigint(20)          not null comment '游戏ID',
  prizeName                 varchar(64)         not null comment '奖品名称',
  prizeImgUrl               varchar(64)         not null comment '奖品图片Url',
  price                     decimal(12, 2)      not null comment '商品价格',
  cost                      decimal(12, 2)      not null comment '成本',
  percentWinningAmount      int                 not null comment '百份中奖数量',
  rewardBalance             int                 not null comment '奖励金额',
  status                    tinyint(1)          not null comment '游戏是否启用(0未启用、1启用)',
  remark                    varchar(128)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '游戏奖品表';

INSERT INTO GamePrize VALUES
(200000000000,  200000000000, 'It is a pity', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 0, 0, 15, 0, 1, 'It is a pity', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000001,  200000000000, 'iphone 12', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 4500, 4500, 0, 0, 1, 'iphone 12', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000002,  200000000000, '1500', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 1500, 1500, 0, 1500, 1, '1500', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000003,  200000000000, '1200', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 1200, 1200, 0, 1200, 1, '1200', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000004,  200000000000, '1000', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 1000, 1000, 1, 1000, 1, '1000', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000005,  200000000000, '100', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 100, 100, 10, 100, 1, '100', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000006,  200000000000, '50', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 50, 50, 54, 50, 1, '50', 1709529514832, 1709529514832);
INSERT INTO GamePrize VALUES
(200000000007,  200000000000, '20', 'lucky/e2d5f4cf1a394165b73a138072e93389.png', 20, 20, 20, 20, 1, '20', 1709529514832, 1709529514832);

commit;
