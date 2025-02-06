delete from SysResources where id between 260300000001 and 260399999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000001, 260000000002, 'order:index', '订单管理', null, false, 'Icon30n', 3, 'order', '/business/luckybox/order', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000002, 260300000001, 'order:order4Activity:index', '活动订单', null, false, 'Icon30n', 1, 'order4Activity', '/business/luckybox/order/order4Activity', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000003, 260300000002, 'order:order4Activity:operator', '操作权限', null, true, null, null, 'order4Activity', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000004, 260300000002, 'order:order4Activity:data', '查询权限', null, true, null, null, 'order4Activity', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000005, 260300000001, 'order:order4Game:index', '游戏订单', null, false, 'Icon30n', 2, 'order4Game', '/business/luckybox/order/order4Game', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000006, 260300000005, 'order:order4Game:operator', '操作权限', null, true, null, null, 'order4Game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260300000007, 260300000005, 'order:order4Game:data', '查询权限', null, true, null, null, 'order4Game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;