delete from SysResources where id between 270000000001 and 279999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000001, null, 'operation:index', '运营管理', null, false, 'Icon30n', 7, '/operation', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000002, 270000000001, 'operation:appDownloadInfo:index', 'APP下载信息', null, false, 'Icon30n', 1, 'appDownloadInfo', '/operation/appDownloadInfo', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000003, 270000000002, 'operation:appDownloadInfo:operator', '操作权限', null, true, null, null, 'appDownloadInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000004, 270000000002, 'operation:appDownloadInfo:data', '查询权限', null, true, null, null, 'appDownloadInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000005, 270000000001, 'operation:appActivationInfo:index', 'APP激活信息', null, false, 'Icon30n', 2, 'appActivationInfo', '/operation/appActivationInfo', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000006, 270000000005, 'operation:appActivationInfo:operator', '操作权限', null, true, null, null, 'appActivationInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000007, 270000000005, 'operation:appActivationInfo:data', '查询权限', null, true, null, null, 'appActivationInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000008, 270000000001, 'statistics:account:index', '运营账户统计', null, false, 'Icon30n', 3, 'accountStatistics', '/operation/accountStatistics', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000009, 270000000008, 'statistics:account:data', '查询权限', null, true, null, null, 'Account', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000010, 270000000001, 'statistics:asset:index', '运营资产统计', null, false, 'Icon30n', 4, 'assetStatistics', '/operation/assetStatistics', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000011, 270000000010, 'statistics:asset:data', '查询权限', null, true, null, null, 'Asset', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000012, 270000000001, 'operation:monitorWalletAssetFlows:index', '监控资产流水', null, false, 'Icon30n', 5, 'monitorWalletAssetFlows', '/operation/monitorWalletAssetFlows', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000013, 270000000012, 'operation:monitorWalletAssetFlows:operator', '操作权限', null, true, null, null, 'game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000014, 270000000012, 'operation:monitorWalletAssetFlows:data', '查询权限', null, true, null, null, 'game', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000015, 270000000001, 'operation:monitorAccountProfitLoss:index', '监控浮动盈亏', null, false, 'Icon30n', 6, 'monitorAccountProfitLoss', '/operation/monitorAccountProfitLoss', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000016, 270000000015, 'operation:monitorAccountProfitLoss:operator', '操作权限', null, true, null, null, 'monitorAccountProfitLoss', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (270000000017, 270000000015, 'operation:monitorAccountProfitLoss:data', '查询权限', null, true, null, null, 'monitorAccountProfitLoss', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;