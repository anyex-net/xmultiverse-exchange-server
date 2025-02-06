delete from SysResources where id between 250000000001 and 259999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000001, null, 'asset:index', '资产管理', null, false, 'Icon30n', 5, '/asset', 'Layout', 200000000000, 1501467844534, 200000000000, 1572250046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000002, 250000000001, 'asset:walletAsset:index', '钱包资产', null, false, 'Icon30n', 1, 'walletAsset', '/asset/walletAsset', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000003, 250000000002, 'asset:walletAsset:operator', '操作权限', null, true, null, null, 'walletAsset', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000004, 250000000002, 'asset:walletAsset:data', '查询权限', null, true, null, null, 'walletAsset', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000005, 250000000001, 'asset:walletAssetFlows:index', '资产流水', null, false, 'Icon30n', 2, 'walletAssetFlows', '/asset/walletAssetFlows', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000006, 250000000005, 'asset:walletAssetFlows:operator', '操作权限', null, true, null, null, 'walletAssetFlows', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000007, 250000000005, 'asset:walletAssetFlows:data', '查询权限', null, true, null, null, 'walletAssetFlows', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000008, 250000000001, 'asset:walletAssetTransactions:index', '充提记录', null, false, 'Icon30n', 3, 'walletAssetTransactions', '/asset/walletAssetTransactions', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000009, 250000000008, 'asset:walletAssetTransactions:operator', '操作权限', null, true, null, null, 'walletAssetTransactions', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000010, 250000000008, 'asset:walletAssetTransactions:data', '查询权限', null, true, null, null, 'walletAssetTransactions', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000011, 250000000001, 'asset:walletAssetAdjust:index', '钱包资产调整', null, false, 'Icon30n', 4, 'walletAssetAdjust', '/asset/walletAssetAdjust', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000012, 250000000011, 'asset:walletAssetAdjust:operator', '操作权限', null, true, null, null, 'walletAssetAdjust', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000013, 250000000011, 'asset:walletAssetAdjust:data', '查询权限', null, true, null, null, 'walletAssetAdjust', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000014, 250000000001, 'asset:walletAssetTipGift:index', '钱包资产打赏', null, false, 'Icon30n', 5, 'walletAssetTipGift', '/asset/walletAssetTipGift', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000015, 250000000014, 'asset:walletAssetTipGift:operator', '操作权限', null, true, null, null, 'walletAssetTipGift', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (250000000016, 250000000014, 'asset:walletAssetTipGift:data', '查询权限', null, true, null, null, 'walletAssetTipGift', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;