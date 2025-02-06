delete from SysResources where id between 260200000001 and 260299999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000001, 260000000002, 'goods:index', '商品管理', null, false, 'Icon30n', 2, 'goods', '/business/luckybox/goods', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000002, 260200000001, 'goods:goodsBrand:index', '商品品牌', null, false, 'Icon30n', 1, 'goodsBrand', '/business/luckybox/goods/goodsBrand', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000003, 260200000002, 'goods:goodsBrand:operator', '操作权限', null, true, null, null, 'goodsBrand', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000004, 260200000002, 'goods:goodsBrand:data', '查询权限', null, true, null, null, 'goodsBrand', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000005, 260200000001, 'goods:goodsCategory:index', '商品分类', null, false, 'Icon30n', 2, 'goodsCategory', '/business/luckybox/goods/goodsCategory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000006, 260200000005, 'goods:goodsCategory:operator', '操作权限', null, true, null, null, 'goodsCategory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000007, 260200000005, 'goods:goodsCategory:data', '查询权限', null, true, null, null, 'goodsCategory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000008, 260200000001, 'goods:goodsSpecGroup:index', '商品品类', null, false, 'Icon30n', 3, 'goodsSpecGroup', '/business/luckybox/goods/goodsSpecGroup', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000009, 260200000008, 'goods:goodsSpecGroup:operator', '操作权限', null, true, null, null, 'goodsSpecGroup', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000010, 260200000008, 'goods:goodsSpecGroup:data', '查询权限', null, true, null, null, 'goodsSpecGroup', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000011, 260200000001, 'goods:goodsSpecParam:index', '商品品类参数', null, false, 'Icon30n', 4, 'goodsSpecParam', '/business/luckybox/goods/goodsSpecParam', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000012, 260200000011, 'goods:goodsSpecParam:operator', '操作权限', null, true, null, null, 'goodsSpecParam', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000013, 260200000011, 'goods:goodsSpecParam:data', '查询权限', null, true, null, null, 'goodsSpecParam', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000014, 260200000001, 'goods:goodsSpu:index', '产品SPU', null, false, 'Icon30n', 5, 'goodsSpu', '/business/luckybox/goods/goodsSpu', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000015, 260200000014, 'goods:goodsSpu:operator', '操作权限', null, true, null, null, 'goodsSpu', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000016, 260200000014, 'goods:goodsSpu:data', '查询权限', null, true, null, null, 'goodsSpu', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000017, 260200000001, 'goods:goodsSku:index', '商品SKU', null, false, 'Icon30n', 6, 'goodsSku', '/business/luckybox/goods/goodsSku', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000018, 260200000017, 'goods:goodsSku:operator', '操作权限', null, true, null, null, 'goodsSku', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260200000019, 260200000017, 'goods:goodsSku:data', '查询权限', null, true, null, null, 'goodsSku', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;