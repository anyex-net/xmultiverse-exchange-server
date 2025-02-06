delete from SysResources where id between 260500000001 and 260599999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000001, 260000000002, 'game:index', '游戏管理', null, false, 'Icon30n', 5, 'game', '/business/luckybox/game', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000002, 260500000001, 'game:game:index', '游戏管理', null, false, 'Icon30n', 1, 'game', '/business/luckybox/game/game', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000003, 260500000002, 'game:game:operator', '操作权限', null, true, null, null, 'game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000004, 260500000002, 'game:game:data', '查询权限', null, true, null, null, 'game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000005, 260500000001, 'game:gamePrize:index', '游戏奖品', null, false, 'Icon30n', 2, 'gamePrize', '/business/luckybox/game/gamePrize', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000006, 260500000005, 'game:gamePrize:operator', '操作权限', null, true, null, null, 'gamePrize', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260500000007, 260500000005, 'game:gamePrize:data', '查询权限', null, true, null, null, 'gamePrize', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

commit;