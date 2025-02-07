delete from SysResources where id between 330000000001 and 339999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000001, null, 'rwa:index', 'RWA交易管理', null, false, 'Icon30n', 5, '/rwa', 'Layout', 200000000000, 1501467844534, 200000000000, 1572330046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000002, 330000000001, 'rwa:rwaCertInstInvestor:index', '机构投资者认证', null, false, 'Icon30n', 1, 'rwaCertInstInvestor', '/rwa/rwaCertInstInvestor', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000003, 330000000002, 'rwa:rwaCertInstInvestor:operator', '操作权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000004, 330000000002, 'rwa:rwaCertInstInvestor:data', '查询权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000005, 330000000002, 'rwa:rwaCertInstInvestor:check', '复核权限', null, true, null, null, 'rwaCertInstInvestor', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000006, 330000000001, 'rwa:rwaCertInstSpvPromoter:index', '机构SPV发起人认证', null, false, 'Icon30n', 2, 'rwaCertInstSpvPromoter', '/rwa/rwaCertInstSpvPromoter', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000007, 330000000006, 'rwa:rwaCertInstSpvPromoter:operator', '操作权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000008, 330000000006, 'rwa:rwaCertInstSpvPromoter:data', '查询权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000009, 330000000006, 'rwa:rwaCertInstSpvPromoter:check', '复核权限', null, true, null, null, 'rwaCertInstSpvPromoter', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000010, 330000000001, 'rwa:rwaBalances:index', 'RWA账户余额', null, false, 'Icon30n', 3, 'rwaBalances', '/rwa/rwaBalances', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000011, 330000000010, 'rwa:rwaBalances:operator', '操作权限', null, true, null, null, 'rwaBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000012, 330000000010, 'rwa:rwaBalances:data', '查询权限', null, true, null, null, 'rwaBalances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000013, 330000000001, 'fund:rwaBalancesTransHistory:index', 'RWA账户流水', null, false, 'Icon30n', 4, 'rwaBalancesTransHistory', '/fund/rwaBalancesTransHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000014, 330000000013, 'fund:rwaBalancesTransHistory:operator', '操作权限', null, true, null, null, 'rwaBalancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000015, 330000000013, 'fund:rwaBalancesTransHistory:data', '查询权限', null, true, null, null, 'rwaBalancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);




INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000008, 330000000001, 'fund:depositAddress:index', '充值地址', null, false, 'Icon30n', 3, 'depositAddress', '/fund/depositAddress', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000009, 330000000008, 'fund:depositAddress:operator', '操作权限', null, true, null, null, 'depositAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000010, 330000000008, 'fund:depositAddress:data', '查询权限', null, true, null, null, 'depositAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000011, 330000000001, 'fund:depositTransHistory:index', '充值记录', null, false, 'Icon30n', 4, 'depositTransHistory', '/fund/depositTransHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000012, 330000000011, 'fund:depositTransHistory:operator', '操作权限', null, true, null, null, 'depositTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000013, 330000000011, 'fund:depositTransHistory:data', '查询权限', null, true, null, null, 'depositTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000014, 330000000001, 'fund:withdrawalHistory:index', '提现管理', null, false, 'Icon30n', 5, 'withdrawalHistory', '/fund/withdrawalHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000015, 330000000014, 'fund:withdrawalHistory:operator', '操作权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000016, 330000000014, 'fund:withdrawalHistory:data', '查询权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (330000000017, 330000000014, 'fund:withdrawalHistory:check', '复核权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
