-- 店铺表
DROP TABLE IF EXISTS Shop;
CREATE TABLE Shop  (
  id                        bigint(20)          not null comment '主键' primary key,
  city                      varchar(64)         not null comment '城市',
  address                   varchar(128)        not null comment '地址',
  tel                       varchar(16)         not null comment '电话'
) comment '店铺表';

INSERT INTO Shop VALUES (200000000000, '浙江宁波', '辽宁省沈阳市黄河北大街12号', '024-11112222');

commit;
