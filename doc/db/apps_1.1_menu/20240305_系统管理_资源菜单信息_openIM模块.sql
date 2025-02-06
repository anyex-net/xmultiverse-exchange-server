delete from SysResources where id between 220000000001 and 229999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000001, null, 'openim:index', 'IM管理', null, false, 'Icon30n', 2, '/openim', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000002, 220000000001, 'openim:register:index', '注册管理', null, false, 'Icon30n', 1, 'register', '/openim/register', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000003, 220000000002, 'openim:registerDefaultFriends:index', '默认好友', null, false, 'Icon30n', 1, 'registerDefaultFriends', '/openim/register/registerDefaultFriends', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000004, 220000000003, 'openim:registerDefaultFriends:operator', '操作权限', null, true, null, null, 'registerDefaultFriends', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000005, 220000000003, 'openim:registerDefaultFriends:data', '查询权限', null, true, null, null, 'registerDefaultFriends', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000006, 220000000002, 'openim:registerDefaultGroup:index', '默认群组', null, false, 'Icon30n', 2, 'registerDefaultGroup', '/openim/register/registerDefaultGroup', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000007, 220000000006, 'openim:registerDefaultGroup:operator', '操作权限', null, true, null, null, 'registerDefaultGroup', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000008, 220000000006, 'openim:registerDefaultGroup:data', '查询权限', null, true, null, null, 'registerDefaultGroup', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000009, 220000000001, 'openim:app:index', '应用管理', null, false, 'Icon30n', 2, 'app', '/openim/app', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000010, 220000000009, 'openim:appConfig:index', '全局配置', null, false, 'Icon30n', 1, 'appConfig', '/openim/app/appConfig', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000011, 220000000010, 'openim:appConfig:operator', '操作权限', null, true, null, null, 'appConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000012, 220000000010, 'openim:appConfig:data', '查询权限', null, true, null, null, 'appConfig', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000013, 220000000001, 'openim:imuser:index', '用户管理', null, false, 'Icon30n', 3, 'imuser', '/openim/imuser', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000014, 220000000013, 'openim:imuser:operator', '操作权限', null, true, null, null, 'imuser', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000015, 220000000013, 'openim:imuser:data', '查询权限', null, true, null, null, 'imuser', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000016, 220000000001, 'openim:group:index', '群组管理', null, false, 'Icon30n', 4, 'group', '/openim/group', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000017, 220000000016, 'openim:group:operator', '操作权限', null, true, null, null, 'group', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000018, 220000000016, 'openim:group:data', '查询权限', null, true, null, null, 'group', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000019, 220000000001, 'openim:message:index', '消息管理', null, false, 'Icon30n', 5, 'message', '/openim/message', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000020, 220000000019, 'openim:userMessage:index', '用户消息', null, false, 'Icon30n', 1, 'userMessage', '/openim/message/userMessage', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000021, 220000000020, 'openim:userMessage:operator', '操作权限', null, true, null, null, 'userMessage', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000022, 220000000020, 'openim:userMessage:data', '查询权限', null, true, null, null, 'userMessage', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000023, 220000000019, 'openim:groupMessage:index', '群组消息', null, false, 'Icon30n', 2, 'groupMessage', '/openim/message/groupMessage', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000024, 220000000023, 'openim:groupMessage:operator', '操作权限', null, true, null, null, 'groupMessage', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000025, 220000000023, 'openim:groupMessage:data', '查询权限', null, true, null, null, 'groupMessage', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000026, 220000000001, 'openim:log:index', '日志管理', null, false, 'Icon30n', 6, 'log', '/openim/log', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000027, 220000000026, 'openim:log:operator', '操作权限', null, true, null, null, 'log', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000028, 220000000026, 'openim:log:data', '查询权限', null, true, null, null, 'log', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000029, 220000000001, 'openim:notification:index', '通知管理', null, false, 'Icon30n', 7, 'notification', '/openim/notification', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000030, 220000000029, 'openim:notificationAccount:index', '通知账号', null, false, 'Icon30n', 1, 'notificationAccount', '/openim/notification/notificationAccount', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000031, 220000000030, 'openim:notificationAccount:operator', '操作权限', null, true, null, null, 'notificationAccount', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000032, 220000000030, 'openim:notificationAccount:data', '查询权限', null, true, null, null, 'notificationAccount', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000033, 220000000029, 'openim:notificationPublish:index', '发送通知', null, false, 'Icon30n', 2, 'notificationPublish', '/openim/notification/notificationPublish', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000034, 220000000033, 'openim:notificationPublish:operator', '操作权限', null, true, null, null, 'notificationPublish', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000035, 220000000033, 'openim:notificationPublish:data', '查询权限', null, true, null, null, 'notificationPublish', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000036, 220000000001, 'account:accountFavorite:index', '账户收藏', null, false, 'Icon30n', 8, 'accountFavorite', '/openim/accountFavorite', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000037, 220000000036, 'account:accountFavorite:operator', '操作权限', null, true, null, null, 'accountFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (220000000038, 220000000036, 'account:accountFavorite:data', '查询权限', null, true, null, null, 'accountFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
