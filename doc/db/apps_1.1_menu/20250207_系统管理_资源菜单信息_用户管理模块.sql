delete from SysResources where id between 310000000001 and 319999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000001, null, 'user:index', '用户管理', null, false, 'Icon30n', 3, '/userMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572310046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000002, 310000000001, 'user:user:index', '用户管理', null, false, 'Icon30n', 1, 'user', '/user/user', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000003, 310000000002, 'user:user:operator', '操作权限', null, true, null, null, 'user', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000004, 310000000002, 'user:user:data', '查询权限', null, true, null, null, 'user', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000005, 310000000001, 'user:userLog:index', '用户日志', null, false, 'Icon30n', 2, 'userLog', '/user/userLog', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000006, 310000000005, 'user:userLog:operator', '操作权限', null, true, null, null, 'userLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000007, 310000000005, 'user:userLog:data', '查询权限', null, true, null, null, 'userLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000008, 310000000001, 'user:userCertKyc:index', '用户个人KYC认证', null, false, 'Icon30n', 3, 'userCertKyc', '/user/userCertKyc', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000009, 310000000005, 'user:userCertKyc:operator', '操作权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000010, 310000000005, 'user:userCertKyc:data', '查询权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000011, 310000000005, 'user:userCertKyc:check', '复核权限', null, true, null, null, 'userCertKyc', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000012, 310000000001, 'base:userApi:index', '用户API', null, false, 'Icon30n', 4, 'userApi', '/base/userApi', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000013, 310000000012, 'base:userApi:operator', '操作权限', null, true, null, null, 'userApi', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000014, 310000000012, 'base:userApi:data', '查询权限', null, true, null, null, 'userApi', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000015, 310000000001, 'base:userHoldAmountRewardConfig:index', '用户持有数量奖励配置', null, false, 'Icon30n', 5, 'userHoldAmountRewardConfig', '/base/userHoldAmountRewardConfig', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000016, 310000000015, 'base:userHoldAmountRewardConfig:operator', '操作权限', null, true, null, null, 'userHoldAmountRewardConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000017, 310000000015, 'base:userHoldAmountRewardConfig:data', '查询权限', null, true, null, null, 'userHoldAmountRewardConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000018, 310000000001, 'base:userInviteRewardConfig:index', '用户邀请返佣奖励配置', null, false, 'Icon30n', 6, 'userInviteRewardConfig', '/base/userInviteRewardConfig', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000019, 310000000018, 'base:userInviteRewardConfig:operator', '操作权限', null, true, null, null, 'userInviteRewardConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (310000000020, 310000000018, 'base:userInviteRewardConfig:data', '查询权限', null, true, null, null, 'userInviteRewardConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
