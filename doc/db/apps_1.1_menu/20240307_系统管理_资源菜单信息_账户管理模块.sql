delete from SysResources where id between 240000000001 and 249999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000001, null, 'account:index', '账户管理', null, false, 'Icon30n', 4, '/account', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000002, 240000000001, 'account:account:index', '账户管理', null, false, 'Icon30n', 1, 'account', '/account/account', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000003, 240000000002, 'account:account:operator', '操作权限', null, true, null, null, 'account', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000004, 240000000002, 'account:account:data', '查询权限', null, true, null, null, 'account', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000005, 240000000001, 'account:accountAddress:index', '收货地址', null, false, 'Icon30n', 2, 'accountAddress', '/account/accountAddress', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000006, 240000000005, 'account:accountAddress:operator', '操作权限', null, true, null, null, 'accountAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000007, 240000000005, 'account:accountAddress:data', '查询权限', null, true, null, null, 'accountAddress', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000008, 240000000001, 'account:accountReceivingBank:index', '收款银行', null, false, 'Icon30n', 3, 'accountReceivingBank', '/account/accountReceivingBank', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000009, 240000000008, 'account:accountReceivingBank:operator', '操作权限', null, true, null, null, 'accountReceivingBank', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000010, 240000000008, 'account:accountReceivingBank:data', '查询权限', null, true, null, null, 'accountReceivingBank', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

--
--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (240000000011, 240000000001, 'account:accountInviteRewards:index', '邀请返佣', null, false, 'Icon30n', 4, 'accountInviteRewards', '/account/accountInviteRewards', 200000000000, 1501467844534, 200000000000, 1501467844534);
--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (240000000012, 240000000011, 'account:accountInviteRewards:operator', '操作权限', null, true, null, null, 'accountInviteRewards', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (240000000013, 240000000011, 'account:accountInviteRewards:data', '查询权限', null, true, null, null, 'accountInviteRewards', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000014, 240000000001, 'account:accountInviteRegister:index', '邀请注册', null, false, 'Icon30n', 5, 'accountInviteRegister', '/account/accountInviteRegister', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000015, 240000000014, 'account:accountInviteRegister:operator', '操作权限', null, true, null, null, 'accountInviteRegister', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000016, 240000000014, 'account:accountInviteRegister:data', '查询权限', null, true, null, null, 'accountInviteRegister', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000017, 240000000001, 'account:accountInviteStatistics:index', '邀请统计', null, false, 'Icon30n', 6, 'accountInviteStatistics', '/account/accountInviteStatistics', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000018, 240000000017, 'account:accountInviteStatistics:operator', '操作权限', null, true, null, null, 'accountInviteStatistics', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000019, 240000000017, 'account:accountInviteStatistics:data', '查询权限', null, true, null, null, 'accountInviteStatistics', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000020, 240000000001, 'account:accountInviteRewardsDetail:index', '邀请奖励', null, false, 'Icon30n', 7, 'accountInviteRewardsDetail', '/account/accountInviteRewardsDetail', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000021, 240000000020, 'account:accountInviteRewardsDetail:operator', '操作权限', null, true, null, null, 'accountInviteRewardsDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000022, 240000000020, 'account:accountInviteRewardsDetail:data', '查询权限', null, true, null, null, 'accountInviteRewardsDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000023, 240000000001, 'account:accountSignInInfo:index', '账户签到信息', null, false, 'Icon30n', 8, 'accountSignInInfo', '/account/accountSignInInfo', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000024, 240000000023, 'account:accountSignInInfo:operator', '操作权限', null, true, null, null, 'accountSignInInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000025, 240000000023, 'account:accountSignInInfo:data', '查询权限', null, true, null, null, 'accountSignInInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000026, 240000000001, 'account:accountSignInDetail:index', '账户签到明细', null, false, 'Icon30n', 9, 'accountSignInDetail', '/account/accountSignInDetail', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000027, 240000000026, 'account:accountSignInDetail:operator', '操作权限', null, true, null, null, 'accountSignInDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (240000000028, 240000000026, 'account:accountSignInDetail:data', '查询权限', null, true, null, null, 'accountSignInDetail', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
