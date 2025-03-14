delete from SysResources where id between 340000000001 and 349999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000001, null, 'spot:index', '现货交易管理', null, false, 'Icon30n', 6, '/spotMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572340046622);
--asset.list
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000002, 340000000001, 'spot:spotAssetList:index', '现货资产币种', null, false, 'Icon30n', 1, 'spotAssetList', '/spot/spotAssetList', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000003, 340000000002, 'spot:spotAssetList:operator', '操作权限', null, true, null, null, 'spotAssetList', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000004, 340000000002, 'spot:spotAssetList:data', '查询权限', null, true, null, null, 'spotAssetList', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--market.list
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000005, 340000000001, 'spot:spotMarketList:index', '现货市场币对', null, false, 'Icon30n', 2, 'spotMarketList', '/spot/spotMarketList', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000006, 340000000005, 'spot:spotMarketList:operator', '操作权限', null, true, null, null, 'spotMarketList', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000007, 340000000005, 'spot:spotMarketList:data', '查询权限', null, true, null, null, 'spotMarketList', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--balance.query
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000008, 340000000001, 'spot:spotBalances:index', '现货账户余额', null, false, 'Icon30n', 3, 'spotBalances', '/fund/spotBalances', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000009, 340000000008, 'spot:spotBalances:operator', '操作权限', null, true, null, null, 'spotBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000010, 340000000008, 'spot:spotBalances:data', '查询权限', null, true, null, null, 'spotBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--balance.history
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000011, 340000000001, 'spot:spotBalancesHistory:index', '现货账户流水', null, false, 'Icon30n', 4, 'spotBalancesHistory', '/fund/spotBalancesHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000012, 340000000011, 'spot:spotBalancesHistory:operator', '操作权限', null, true, null, null, 'spotBalancesHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000013, 340000000011, 'spot:spotBalancesHistory:data', '查询权限', null, true, null, null, 'spotBalancesHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--balance.update
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000014, 340000000001, 'spot:spotBalancesUpdate:index', '现货账户调整', null, false, 'Icon30n', 5, 'spotBalancesUpdate', '/fund/spotBalancesUpdate', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000015, 340000000014, 'spot:spotBalancesUpdate:operator', '操作权限', null, true, null, null, 'spotBalancesUpdate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000016, 340000000014, 'spot:spotBalancesUpdate:data', '查询权限', null, true, null, null, 'spotBalancesUpdate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000017, 340000000014, 'spot:spotBalancesUpdate:check', '复核权限', null, true, null, null, 'spotBalancesUpdate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--asset.summary
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000018, 340000000001, 'spot:spotAssetSummary:index', '现货资产总览', null, false, 'Icon30n', 6, 'spotAssetSummary', '/spot/spotAssetSummary', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000019, 340000000018, 'spot:spotAssetSummary:operator', '操作权限', null, true, null, null, 'spotAssetSummary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000020, 340000000018, 'spot:spotAssetSummary:data', '查询权限', null, true, null, null, 'spotAssetSummary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--market.summary
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000021, 340000000001, 'spot:spotMarketSummary:index', '现货市场总览', null, false, 'Icon30n', 7, 'spotMarketSummary', '/spot/spotMarketSummary', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000022, 340000000021, 'spot:spotMarketSummary:operator', '操作权限', null, true, null, null, 'spotMarketSummary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000023, 340000000021, 'spot:spotMarketSummary:data', '查询权限', null, true, null, null, 'spotMarketSummary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--order.book
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000024, 340000000001, 'spot:spotOrderBook:index', '现货订单簿', null, false, 'Icon30n', 8, 'spotOrderBook', '/spot/spotOrderBook', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000025, 340000000024, 'spot:spotOrderBook:operator', '操作权限', null, true, null, null, 'spotOrderBook', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000026, 340000000024, 'spot:spotOrderBook:data', '查询权限', null, true, null, null, 'spotOrderBook', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--order.pending
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000027, 340000000001, 'spot:spotOrderPending:index', '现货待处理订单', null, false, 'Icon30n', 9, 'spotOrderPending', '/spot/spotOrderPending', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000028, 340000000027, 'spot:spotOrderPending:operator', '操作权限', null, true, null, null, 'spotOrderPending', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000029, 340000000027, 'spot:spotOrderPending:data', '查询权限', null, true, null, null, 'spotOrderPending', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--order.finished
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000030, 340000000001, 'spot:spotOrderFinished:index', '现货已完成订单', null, false, 'Icon30n', 10, 'spotOrderFinished', '/spot/spotOrderFinished', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000031, 340000000030, 'spot:spotOrderFinished:operator', '操作权限', null, true, null, null, 'spotOrderFinished', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000032, 340000000030, 'spot:spotOrderFinished:data', '查询权限', null, true, null, null, 'spotOrderFinished', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--market.deals
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000033, 340000000001, 'spot:spotMarketDeals:index', '现货市场成交', null, false, 'Icon30n', 11, 'spotMarketDeals', '/spot/spotMarketDeals', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000034, 340000000033, 'spot:spotMarketDeals:operator', '操作权限', null, true, null, null, 'spotMarketDeals', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000035, 340000000033, 'spot:spotMarketDeals:data', '查询权限', null, true, null, null, 'spotMarketDeals', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


--trade_log
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000036, 340000000001, 'spot:operLog:index', 'ViaBtcDB操作记录', null, false, 'Icon30n', 12, 'operLog', '/spot/operLog', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000037, 340000000036, 'spot:operLog:operator', '操作权限', null, true, null, null, 'operLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000038, 340000000036, 'spot:operLog:data', '查询权限', null, true, null, null, 'operLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000039, 340000000001, 'spot:sliceHistory:index', 'ViaBtcDB切片历史', null, false, 'Icon30n', 13, 'sliceHistory', '/spot/sliceHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000040, 340000000039, 'spot:sliceHistory:operator', '操作权限', null, true, null, null, 'sliceHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000041, 340000000039, 'spot:sliceHistory:data', '查询权限', null, true, null, null, 'sliceHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000042, 340000000001, 'spot:sliceBalance:index', 'ViaBtcDB资金切片记录', null, false, 'Icon30n', 14, 'sliceBalance', '/spot/sliceBalance', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000043, 340000000042, 'spot:sliceBalance:operator', '操作权限', null, true, null, null, 'sliceBalance', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000044, 340000000042, 'spot:sliceBalance:data', '查询权限', null, true, null, null, 'sliceBalance', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000045, 340000000001, 'spot:sliceOrder:index', 'ViaBtcDB订单切片记录', null, false, 'Icon30n', 15, 'sliceOrder', '/spot/sliceOrder', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000046, 340000000045, 'spot:sliceOrder:operator', '操作权限', null, true, null, null, 'sliceOrder', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000047, 340000000045, 'spot:sliceOrder:data', '查询权限', null, true, null, null, 'sliceOrder', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

--trade_history
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000048, 340000000001, 'spot:balanceHistory:index', 'ViaBtcDB资金历史', null, false, 'Icon30n', 16, 'balanceHistory', '/spot/balanceHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000049, 340000000048, 'spot:balanceHistory:operator', '操作权限', null, true, null, null, 'balanceHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000050, 340000000048, 'spot:balanceHistory:data', '查询权限', null, true, null, null, 'balanceHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000051, 340000000001, 'spot:orderDetail:index', 'ViaBtcDB订单明细', null, false, 'Icon30n', 17, 'orderDetail', '/spot/orderDetail', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000052, 340000000051, 'spot:orderDetail:operator', '操作权限', null, true, null, null, 'orderDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000053, 340000000051, 'spot:orderDetail:data', '查询权限', null, true, null, null, 'orderDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000054, 340000000001, 'spot:orderHistory:index', 'ViaBtcDB订单历史', null, false, 'Icon30n', 18, 'orderHistory', '/spot/orderHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000055, 340000000054, 'spot:orderHistory:operator', '操作权限', null, true, null, null, 'orderHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000056, 340000000054, 'spot:orderHistory:data', '查询权限', null, true, null, null, 'orderHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000057, 340000000001, 'spot:dealHistory:index', 'ViaBtcDB成交历史', null, false, 'Icon30n', 19, 'dealHistory', '/spot/dealHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000058, 340000000057, 'spot:dealHistory:operator', '操作权限', null, true, null, null, 'dealHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000059, 340000000057, 'spot:dealHistory:data', '查询权限', null, true, null, null, 'dealHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000060, 340000000001, 'spot:userDealHistory:index', 'ViaBtcDB用户成交历史', null, false, 'Icon30n', 20, 'userDealHistory', '/spot/userDealHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000061, 340000000060, 'spot:userDealHistory:operator', '操作权限', null, true, null, null, 'dealHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (340000000062, 340000000060, 'spot:userDealHistory:data', '查询权限', null, true, null, null, 'dealHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
