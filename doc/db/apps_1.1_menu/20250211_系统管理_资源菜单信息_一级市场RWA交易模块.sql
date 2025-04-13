delete from SysResources where id between 330000000001 and 339999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000001, null, 'rwa:index', 'RWA交易管理', null, false, 'Icon30n', 5, '/rwaMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572330046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000002, 330000000001, 'rwa:rwaCertInstSpvPromoter:index', '机构SPV发起人认证', null, false, 'Icon30n', 1, 'rwaCertInstSpvPromoter', '/rwa/rwaCertInstSpvPromoter', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000003, 330000000002, 'rwa:rwaCertInstSpvPromoter:operator', '操作权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000004, 330000000002, 'rwa:rwaCertInstSpvPromoter:data', '查询权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000005, 330000000002, 'rwa:rwaCertInstSpvPromoter:check', '复核权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000006, 330000000001, 'rwa:rwaCertInstInvestor:index', '机构投资者认证', null, false, 'Icon30n', 2, 'rwaCertInstInvestor', '/rwa/rwaCertInstInvestor', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000007, 330000000006, 'rwa:rwaCertInstInvestor:operator', '操作权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000008, 330000000006, 'rwa:rwaCertInstInvestor:data', '查询权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000009, 330000000006, 'rwa:rwaCertInstInvestor:check', '复核权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000010, 330000000001, 'rwa:rwaBalances:index', 'RWA账户余额', null, false, 'Icon30n', 3, 'rwaBalances', '/rwa/rwaBalances', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000011, 330000000010, 'rwa:rwaBalances:operator', '操作权限', null, true, null, null, 'rwaBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000012, 330000000010, 'rwa:rwaBalances:data', '查询权限', null, true, null, null, 'rwaBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000013, 330000000001, 'rwa:rwaBalancesTransHistory:index', 'RWA账户流水', null, false, 'Icon30n', 4, 'rwaBalancesTransHistory', '/rwa/rwaBalancesTransHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000014, 330000000013, 'rwa:rwaBalancesTransHistory:operator', '操作权限', null, true, null, null, 'rwaBalancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000015, 330000000013, 'rwa:rwaBalancesTransHistory:data', '查询权限', null, true, null, null, 'rwaBalancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000016, 330000000001, 'rwa:rwaInstSpvCompany:index', '机构SPV公司', null, false, 'Icon30n', 5, 'rwaInstSpvCompany', '/rwa/rwaInstSpvCompany', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000017, 330000000016, 'rwa:rwaInstSpvCompany:operator', '操作权限', null, true, null, null, 'rwaInstSpvCompany', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000018, 330000000016, 'rwa:rwaInstSpvCompany:data', '查询权限', null, true, null, null, 'rwaInstSpvCompany', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000019, 330000000016, 'rwa:rwaInstSpvCompany:check', '复核权限', null, true, null, null, 'rwaInstSpvCompany', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000020, 330000000001, 'rwa:rwaInstSpvProduct:index', '机构SPV产品', null, false, 'Icon30n', 6, 'rwaInstSpvProduct', '/rwa/rwaInstSpvProduct', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000021, 330000000020, 'rwa:rwaInstSpvProduct:operator', '操作权限', null, true, null, null, 'rwaInstSpvProduct', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000022, 330000000020, 'rwa:rwaInstSpvProduct:data', '查询权限', null, true, null, null, 'rwaInstSpvProduct', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000023, 330000000020, 'rwa:rwaInstSpvProduct:check', '复核权限', null, true, null, null, 'rwaInstSpvProduct', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000024, 330000000001, 'rwa:rwaInstSpvProductPurchase:index', '机构SPV产品申购记录', null, false, 'Icon30n', 7, 'rwaInstSpvProductPurchase', '/rwa/rwaInstSpvProductPurchase', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000025, 330000000024, 'rwa:rwaInstSpvProductPurchase:operator', '操作权限', null, true, null, null, 'rwaInstSpvProductPurchase', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000026, 330000000024, 'rwa:rwaInstSpvProductPurchase:data', '查询权限', null, true, null, null, 'rwaInstSpvProductPurchase', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000027, 330000000001, 'rwa:rwaInstSpvProductDividend:index', '机构SPV产品分红记录', null, false, 'Icon30n', 8, 'rwaInstSpvProductDividend', '/rwa/rwaInstSpvProductDividend', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000028, 330000000027, 'rwa:rwaInstSpvProductDividend:operator', '操作权限', null, true, null, null, 'rwaInstSpvProductDividend', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000029, 330000000027, 'rwa:rwaInstSpvProductDividend:data', '查询权限', null, true, null, null, 'rwaInstSpvProductDividend', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000030, 330000000001, 'rwa:rwaInstSpvProductRedemption:index', '机构SPV产品赎回记录', null, false, 'Icon30n', 9, 'rwaInstSpvProductRedemption', '/rwa/rwaInstSpvProductRedemption', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000031, 330000000030, 'rwa:rwaInstSpvProductRedemption:operator', '操作权限', null, true, null, null, 'rwaInstSpvProductRedemption', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000032, 330000000030, 'rwa:rwaInstSpvProductRedemption:data', '查询权限', null, true, null, null, 'rwaInstSpvProductRedemption', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000033, 330000000001, 'rwa:rwaInstSpvProductDividendSnapshot:index', 'RWA机构SPV产品投资者分红快照', null, false, 'Icon30n', 6, 'rwaInstSpvProductDividendSnapshot', '/rwa/rwaInstSpvProductDividendSnapshot', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000034, 330000000033, 'rwa:rwaInstSpvProductDividendSnapshot:operator', '操作权限', null, true, null, null, 'rwaInstSpvProductDividendSnapshot', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000035, 330000000033, 'rwa:rwaInstSpvProductDividendSnapshot:data', '查询权限', null, true, null, null, 'rwaInstSpvProductDividendSnapshot', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000036, 330000000001, 'rwa:rwaInstSpvProductNotice:index', 'RWA机构SPV产品公告', null, false, 'Icon30n', 6, 'rwaInstSpvProductNotice', '/rwa/rwaInstSpvProductNotice', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000037, 330000000036, 'rwa:rwaInstSpvProductNotice:operator', '操作权限', null, true, null, null, 'rwaInstSpvProductNotice', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000038, 330000000036, 'rwa:rwaInstSpvProductNotice:data', '查询权限', null, true, null, null, 'rwaInstSpvProductNotice', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);



commit;
