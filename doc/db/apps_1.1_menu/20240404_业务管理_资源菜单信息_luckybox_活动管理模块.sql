delete from SysResources where id between 260400000001 and 260499999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000001, 260000000002, 'activity:index', '活动管理', null, false, 'Icon30n', 4, 'activity', '/business/luckybox/activity', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000002, 260400000001, 'activity:activityTreasureHunt:index', '一元夺宝', null, false, 'Icon30n', 1, 'activityTreasureHunt', '/business/luckybox/activity/activityTreasureHunt', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000003, 260400000002, 'activity:activityTreasureHunt:operator', '操作权限', null, true, null, null, 'activityTreasureHunt', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000004, 260400000002, 'activity:activityTreasureHunt:data', '查询权限', null, true, null, null, 'activityTreasureHunt', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000005, 260400000001, 'activity:activityHotDeals:index', '半价购买', null, false, 'Icon30n', 2, 'activityHotDeals', '/business/luckybox/activity/activityHotDeals', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000006, 260400000005, 'activity:activityHotDeals:operator', '操作权限', null, true, null, null, 'activityHotDeals', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000007, 260400000005, 'activity:activityHotDeals:data', '查询权限', null, true, null, null, 'activityHotDeals', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000008, 260400000001, 'activity:activityOperRecord:index', '活动操作记录', null, false, 'Icon30n', 3, 'activityOperRecord', '/business/luckybox/activity/activityOperRecord', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000009, 260400000008, 'activity:activityOperRecord:operator', '操作权限', null, true, null, null, 'activityOperRecord', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260400000010, 260400000008, 'activity:activityOperRecord:data', '查询权限', null, true, null, null, 'activityOperRecord', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;