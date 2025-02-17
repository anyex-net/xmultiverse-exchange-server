delete from SysResources where id between 320000000001 and 329999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000001, null, 'fund:index', '资金管理', null, false, 'Icon30n', 4, '/fundMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572320046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000002, 320000000001, 'fund:balances:index', '资金账户余额', null, false, 'Icon30n', 1, 'balances', '/fund/balances', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000003, 320000000002, 'fund:balances:operator', '操作权限', null, true, null, null, 'balances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000004, 320000000002, 'fund:balances:data', '查询权限', null, true, null, null, 'balances', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000005, 320000000001, 'fund:balancesTransHistory:index', '资金账户流水', null, false, 'Icon30n', 2, 'balancesTransHistory', '/fund/balancesTransHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000006, 320000000005, 'fund:balancesTransHistory:operator', '操作权限', null, true, null, null, 'balancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000007, 320000000005, 'fund:balancesTransHistory:data', '查询权限', null, true, null, null, 'balancesTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000008, 320000000001, 'fund:depositAddress:index', '充值地址', null, false, 'Icon30n', 3, 'depositAddress', '/fund/depositAddress', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000009, 320000000008, 'fund:depositAddress:operator', '操作权限', null, true, null, null, 'depositAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000010, 320000000008, 'fund:depositAddress:data', '查询权限', null, true, null, null, 'depositAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000011, 320000000001, 'fund:depositTransHistory:index', '充值记录', null, false, 'Icon30n', 4, 'depositTransHistory', '/fund/depositTransHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000012, 320000000011, 'fund:depositTransHistory:operator', '操作权限', null, true, null, null, 'depositTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000013, 320000000011, 'fund:depositTransHistory:data', '查询权限', null, true, null, null, 'depositTransHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000014, 320000000001, 'fund:withdrawalHistory:index', '提现管理', null, false, 'Icon30n', 5, 'withdrawalHistory', '/fund/withdrawalHistory', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000015, 320000000014, 'fund:withdrawalHistory:operator', '操作权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000016, 320000000014, 'fund:withdrawalHistory:data', '查询权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (320000000017, 320000000014, 'fund:withdrawalHistory:check', '复核权限', null, true, null, null, 'withdrawalHistory', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
