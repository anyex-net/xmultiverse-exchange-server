-- 商品品牌表
DROP TABLE IF EXISTS GoodsBrand;
CREATE TABLE GoodsBrand (
  id                        bigint(20)          not null comment '主键' primary key,
  name                      varchar(64)         not null comment '品牌名称',
  logoImageUrl              varchar(256)                 comment '品牌Logo图片Url',
  letter                    char(1)             not null comment '品牌首字母',
  status                    tinyint(1)          not null comment '状态(是否启用)',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间',
  UNIQUE INDEX `GoodsBrand_name`(`name`) USING BTREE
) comment '商品品牌表';

INSERT INTO GoodsBrand VALUES (1, '联想', NULL, 'L', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (2, '华为', NULL, 'H', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (3, '小米', NULL, 'X', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (4, '苹果', NULL, 'A', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (5, 'OPPO', NULL, 'O', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (6, '三星', NULL, 'S', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (7, 'LG', NULL, 'L', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (8, 'vivo', NULL, 'V', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (9, '飞利浦', NULL, 'F', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (10, '红米', NULL, 'H', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (11, 'IBM', NULL, 'I', 1, 1709529514832, 1709529514832);
INSERT INTO GoodsBrand VALUES (12, '戴尔', NULL, 'D', 1, 1709529514832, 1709529514832);

commit;


-- 商品分类表
DROP TABLE IF EXISTS GoodsCategory;
CREATE TABLE GoodsCategory (
  id                        bigint(20)          not null comment '主键' primary key,
  name                      varchar(128)        not null comment '分类名称',
  parentId                  bigint(20)                   comment '上级分类ID',
  sort                      int(10)             not null comment '排序',
  createTime                bigint(13)                   comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间',
  UNIQUE INDEX `GoodsCategory_name`(`name`) USING BTREE
) comment '商品分类表';

INSERT INTO GoodsCategory VALUES (1, '手机/数码/配件', NULL, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (2, '手机通讯', 1, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (3, '手机', 2, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (4, '手机配件', 1, 2, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (5, '移动电源', 4, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (6, '蓝牙耳机', 4, 2, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (7, '保护壳', 4, 3, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (8, '数码配件', 1, 3, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (9, '存储卡', 8, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (10, '读卡器', 8, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (11, '电脑/办公/外设', NULL, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (12, '电脑整机', 11, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (13, '笔记本', 12, 1, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (14, '台式电脑', 12, 2, 1709529514832, 1709529514832);
INSERT INTO GoodsCategory VALUES (15, '平板电脑', 12, 3, 1709529514832, 1709529514832);

commit;


-- 商品分类与品牌关联表
DROP TABLE IF EXISTS GoodsCategoryBrand;
CREATE TABLE GoodsCategoryBrand (
  id                        bigint(20)          not null comment '主键' primary key,
  categoryId                bigint(20)          not null comment '分类ID',
  brandId                   bigint(20)          not null comment '品牌ID'
) comment '商品分类与品牌关联表';

INSERT INTO GoodsCategoryBrand VALUES (1, 3, 1);
INSERT INTO GoodsCategoryBrand VALUES (2, 3, 2);
INSERT INTO GoodsCategoryBrand VALUES (3, 3, 3);
INSERT INTO GoodsCategoryBrand VALUES (4, 3, 4);
INSERT INTO GoodsCategoryBrand VALUES (5, 3, 5);
INSERT INTO GoodsCategoryBrand VALUES (6, 3, 6);
INSERT INTO GoodsCategoryBrand VALUES (7, 3, 8);
INSERT INTO GoodsCategoryBrand VALUES (8, 3, 10);
INSERT INTO GoodsCategoryBrand VALUES (9, 13, 1);
INSERT INTO GoodsCategoryBrand VALUES (10, 13, 2);
INSERT INTO GoodsCategoryBrand VALUES (11, 13, 12);

commit;


-- 商品品类表
DROP TABLE IF EXISTS GoodsSpecGroup;
CREATE TABLE GoodsSpecGroup (
  id                        bigint(20)          not null comment '主键' primary key,
  name                      varchar(64)         not null comment '品类名称',
  remark                    varchar(128)                 comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间',
  UNIQUE INDEX `GoodsSpecGroup_name`(`name`) USING BTREE
) comment '商品品类表';

INSERT INTO GoodsSpecGroup VALUES (1, '手机', NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSpecGroup VALUES (2, '手机线', NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSpecGroup VALUES (3, '手机电池', NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSpecGroup VALUES (4, '液晶电视', NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSpecGroup VALUES (5, '投影电视', NULL, 1709529514832, 1709529514832);

commit;


-- 商品品类参数表
DROP TABLE IF EXISTS GoodsSpecParam;
CREATE TABLE GoodsSpecParam (
  id                        bigint(20)          not null comment '主键' primary key,
  spgId                     bigint(20)          not null comment '商品品类Id',
  paramName                 varchar(64)         not null comment '参数名称',
  isNumeric                 tinyint(1)          not null comment '是否为数字参数',
  unit                      varchar(64)                  comment '单位(量词)',
  paramValue                varchar(64)                  comment '参数值'
) comment '商品品类参数表';

INSERT INTO GoodsSpecParam VALUES ( 1, 1, 'CPU', 0, NULL, NULL);
INSERT INTO GoodsSpecParam VALUES ( 2, 1, '运存', 1, 'GB', NULL);
INSERT INTO GoodsSpecParam VALUES ( 3, 1, '内存', 1, 'GB', NULL);
INSERT INTO GoodsSpecParam VALUES ( 4, 1, '屏幕尺寸', 1, '英寸', NULL);
INSERT INTO GoodsSpecParam VALUES ( 5, 1, '电池', 1, '毫安时', NULL);
INSERT INTO GoodsSpecParam VALUES ( 6, 4, '屏幕尺寸', 1, '英寸', NULL);
INSERT INTO GoodsSpecParam VALUES ( 7, 4, '长度', 1, '厘米', NULL);
INSERT INTO GoodsSpecParam VALUES ( 8, 4, '高度', 1, '厘米', NULL);
INSERT INTO GoodsSpecParam VALUES ( 9, 4, '宽度', 1, '厘米', NULL);
INSERT INTO GoodsSpecParam VALUES (10, 4, '分辨率', 0, '像素', '720P\\1080P\\4K\\8K');

commit;


-- 产品SPU表(Standard Product Unit)
DROP TABLE IF EXISTS GoodsSpu;
CREATE TABLE GoodsSpu (
  id                        bigint(20)          not null comment '主键' primary key,
  title                     varchar(128)        not null comment '标题',
  subTitle                  varchar(128)        not null comment '副标题',
  categoryId                bigint(20)          not null comment '商品分类ID',
  brandId                   bigint(20)          not null comment '商品品牌ID',
  spgId                     bigint(20)          not null comment '商品品类ID',
  saleable                  tinyint(1)          not null comment '是否上架',
  valid                     tinyint(1)          not null comment '是否有效',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '产品SPU表';

INSERT INTO GoodsSpu VALUES (1, '小米9', '小米9', 3, 3, 1, 1, 1, 1709529514832, 1709529514832);

commit;


-- 商品SKU表(Stock Keeping Unit)
DROP TABLE IF EXISTS GoodsSku;
CREATE TABLE GoodsSku (
  id                        bigint(20)          not null comment '主键' primary key,
  spuId                     bigint(20)          not null comment '产品ID',
  title                     varchar(128)        not null comment '商品标题',
  subTitle                  varchar(128)                 comment '商品副标题',
  sellingPoint              varchar(128)                 comment '商品卖点',
  iconImage                 varchar(64)         not null comment '商品图标图片',
  mainImages                json                not null comment '商品主图图片',
  detailImages              json                not null comment '商品详情图片',
  price                     decimal(10, 2)      not null comment '价格',
  stock                     bigint              not null comment '库存数量',
  param                     json                not null comment '参数',
  saleable                  tinyint(1)          not null comment '是否上架',
  valid                     tinyint(1)          not null comment '是否有效',
  description               varchar(4000)                comment '商品描述',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment '商品SKU表';

INSERT INTO GoodsSku VALUES (1, 1, 'Xiaomi/小米 小米9 8GB+128GB 全息幻彩紫 移动联通电信全网通4G手机', '商品副标题', '商品卖点', '', '{\"mainImages\": [\"http://127.0.0.1/3.jpg\", \"http://127.0.0.1/4.jpg\"]}', '{\"detailImages\": [\"http://127.0.0.1/1.jpg\", \"http://127.0.0.1/2.jpg\"]}', 3299.00, 0, '{\"CPU\": \"骁龙855\", \"内存\": 128, \"电池\": 4000, \"运存\": 8, \"屏幕尺寸\": 6.39}', 1, 1, NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSku VALUES (2, 1, 'Xiaomi/小米 小米9 8GB+128GB 全息幻彩蓝 移动联通电信全网通4G手机', '商品副标题', '商品卖点', '', '{\"mainImages\": [\"http://127.0.0.1/3.jpg\", \"http://127.0.0.1/4.jpg\"]}', '{\"detailImages\": [\"http://127.0.0.1/1.jpg\", \"http://127.0.0.1/2.jpg\"]}', 3299.00, 0, '{\"CPU\": \"骁龙855\", \"内存\": 128, \"电池\": 4000, \"运存\": 8, \"屏幕尺寸\": 6.39}', 1, 1, NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSku VALUES (3, 1, 'Xiaomi/小米 小米9 6GB+128GB 全息幻彩蓝 移动联通电信全网通4G手机', '商品副标题', '商品卖点', '', '{\"mainImages\": [\"http://127.0.0.1/3.jpg\", \"http://127.0.0.1/4.jpg\"]}', '{\"detailImages\": [\"http://127.0.0.1/1.jpg\", \"http://127.0.0.1/2.jpg\"]}', 2999.00, 0, '{\"CPU\": \"骁龙855\", \"内存\": 128, \"电池\": 4000, \"运存\": 6, \"屏幕尺寸\": 6.39}', 1, 1, NULL, 1709529514832, 1709529514832);
INSERT INTO GoodsSku VALUES (4, 1, 'Xiaomi/小米 小米9 6GB+128GB 深空灰 移动联通电信全网通4G手机', '商品副标题', '商品卖点', '', '{\"mainImages\": [\"http://127.0.0.1/3.jpg\", \"http://127.0.0.1/4.jpg\"]}', '{\"detailImages\": [\"http://127.0.0.1/1.jpg\", \"http://127.0.0.1/2.jpg\"]}', 2999.00, 0, '{\"CPU\": \"骁龙855\", \"内存\": 128, \"电池\": 4000, \"运存\": 6, \"屏幕尺寸\": 6.39}', 1, 1, NULL, 1709529514832, 1709529514832);

commit;

