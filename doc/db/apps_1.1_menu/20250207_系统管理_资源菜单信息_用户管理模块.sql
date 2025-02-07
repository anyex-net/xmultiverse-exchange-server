delete from SysResources where id between 310000000001 and 319999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000001, null, 'user:index', '用户管理', null, false, 'Icon30n', 3, '/user', 'Layout', 200000000000, 1501467844534, 200000000000, 1572310046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000002, 310000000001, 'user:user:index', '用户管理', null, false, 'Icon30n', 1, 'user', '/user/user', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000003, 310000000002, 'user:user:operator', '操作权限', null, true, null, null, 'user', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000004, 310000000002, 'user:user:data', '查询权限', null, true, null, null, 'user', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000005, 310000000001, 'user:userCertKyc:index', '用户个人KYC认证', null, false, 'Icon30n', 2, 'userCertKyc', '/user/userCertKyc', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000006, 310000000005, 'user:userCertKyc:operator', '操作权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000007, 310000000005, 'user:userCertKyc:data', '查询权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000008, 310000000005, 'user:userCertKyc:check', '复核权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (310000000011, 310000000001, 'account:accountInviteRewards:index', '邀请返佣', null, false, 'Icon30n', 4, 'accountInviteRewards', '/account/accountInviteRewards', 200000000000, 1501467844534, 200000000000, 1501467844534);
--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (310000000012, 310000000011, 'account:accountInviteRewards:operator', '操作权限', null, true, null, null, 'accountInviteRewards', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
--INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
--VALUES (310000000013, 310000000011, 'account:accountInviteRewards:data', '查询权限', null, true, null, null, 'accountInviteRewards', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
