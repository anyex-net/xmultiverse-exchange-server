delete from SysResources where id between 200000000001 and 219999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000001, null, 'system:index', '系统管理', null, false, 'Icon7n', 0, '/system', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000002, 200000000001, 'system:organization:index', '机构管理', null, false, 'Icon30n', 1, 'organization', '/system/organization', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000003, 200000000002, 'system:organization:operator', '操作权限', null, true, null, null, 'organization', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000004, 200000000002, 'system:organization:data', '查询权限', null, true, null, null, 'organization', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000005, 200000000001, 'system:resource:index', '资源管理', null, false, 'Icon30n', 2, 'resource', '/system/resource', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000006, 200000000005, 'system:resource:operator', '操作权限', null, true, null, null, 'resource', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000007, 200000000005, 'system:resource:data', '查询权限', null, true, null, null, 'resource', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000008, 200000000001, 'system:role:index', '角色管理', null, false, 'Icon30n', 3, 'role', '/system/role', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000009, 200000000008, 'system:role:operator', '操作权限', null, true, null, null, 'role', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000010, 200000000008, 'system:role:data', '查询权限', null, true, null, null, 'role', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000011, 200000000001, 'system:userInfo:index', '用户管理', null, false, 'Icon30n', 4, 'userInfo', '/system/userInfo', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000012, 200000000011, 'system:userInfo:operator', '操作权限', null, true, null, null, 'userInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000013, 200000000011, 'system:userInfo:data', '查询权限', null, true, null, null, 'userInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000014, 200000000001, 'system:cache:index', '缓存管理', null, false, 'Icon30n', 5, 'cache', '/system/cache', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000015, 200000000014, 'system:cache:operator', '操作权限', null, true, null, null, 'cache', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000016, 200000000014, 'system:cache:data', '查询权限', null, true, null, null, 'cache', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000017, 200000000001, 'system:accessLog:index', '访问日志', null, false, 'Icon30n', 6, 'accessLog', '/system/accessLog', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000018, 200000000017, 'system:accessLog:operator', '操作权限', null, true, null, null, 'accessLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000019, 200000000017, 'system:accessLog:data', '查询权限', null, true, null, null, 'accessLog', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

/*
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000017, 200000000001, 'system:frontModule:index', '前端功能', null, false, 'Icon30n', 6, 'frontModule', '/system/frontModule', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000018, 200000000017, 'system:frontModule:operator', '操作权限', null, true, null, null, 'frontModule', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000019, 200000000017, 'system:frontModule:data', '查询权限', null, true, null, null, 'frontModule', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
*/

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000001, null, 'common:index', '公共管理', null, false, 'Icon21n', 1, '/common', 'Layout', 200000000000, 1571883511490, 200000000000, 1571884027177);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000002, 210000000001, 'common:region:index', '区域代码', null, false, 'Icon30n', 1, 'region', '/common/region', 200000000000, 1501467844534, 200000000000, 1571883572513);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000003, 210000000002, 'common:region:operator', '操作权限', null, true, null, null, 'region', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000004, 210000000002, 'common:region:data', '查询权限', null, true, null, null, 'region', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000005, 210000000001, 'common:dictionary:index', '数据字典', null, false, 'Icon30n', 2, 'dictionary', '/common/dictionary', 200000000000, 1501467844534, 200000000000, 1571883583741);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000006, 210000000005, 'common:dictionary:operator', '操作权限', null, true, null, null, 'dictionary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000007, 210000000005, 'common:dictionary:data', '查询权限', null, true, null, null, 'dictionary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000008, 210000000001, 'common:msgTemplate:index', '消息模板', null, false, 'Icon30n', 3, 'msgTemplate', '/common/msgTemplate', 200000000000, 1501467844534, 200000000000, 1571883593107);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000009, 210000000008, 'common:msgTemplate:operator', '操作权限', null, true, null, null, 'msgTemplate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000010, 210000000008, 'common:msgTemplate:data', '查询权限', null, true, null, null, 'msgTemplate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000011, 210000000001, 'common:msgRecord:index', '消息记录', null, false, 'Icon30n', 4, 'msgRecord', '/common/msgRecord', 200000000000, 1519875042081, 200000000000, 1571907998769);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000012, 210000000011, 'common:msgRecord:data', '查询权限', null, true, null, null, 'msgRecord', '#', 200000000000, 1519875247868, 200000000000, 1571884285532);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000013, 210000000001, 'common:appVersion:index', 'app版本', null, false, 'Icon30n', 5, 'appVersion', '/common/appVersion', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000014, 210000000013, 'common:appVersion:data', '查询权限', null, true, null, null, 'appVersion', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000015, 210000000013, 'common:appVersion:operator', '操作权限', null, true, null, null, 'appVersion', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000016, 210000000001, 'common:appDevice:index', 'app设备', null, false, 'Icon30n', 6, 'appDevice', '/common/appDevice', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000017, 210000000016, 'common:appDevice:data', '查询权限', null, true, null, null, 'appDevice', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000018, 210000000016, 'common:appDevice:operator', '操作权限', null, true, null, null, 'appDevice', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000019, 210000000001, 'common:notice:index', '平台公告', null, false, 'Icon30n', 7, 'notice', '/common/notice', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000020, 210000000019, 'common:notice:data', '查询权限', null, true, null, null, 'notice', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000021, 210000000019, 'common:notice:operator', '操作权限', null, true, null, null, 'notice', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000022, 210000000001, 'common:parameter:index', '参数配置', null, false, 'Icon30n', 8, 'parameter', '/common/parameter', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000023, 210000000022, 'common:parameter:data', '查询权限', null, true, null, null, 'parameter', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000024, 210000000022, 'common:parameter:operator', '操作权限', null, true, null, null, 'parameter', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000025, 210000000001, 'common:tradeDay:index', '交易日设置', null, false, 'Icon30n', 9, 'tradeDay', '/common/tradeDay', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000026, 210000000025, 'common:tradeDay:data', '查询权限', null, true, null, null, 'tradeDay', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000027, 210000000025, 'common:tradeDay:operator', '操作权限', null, true, null, null, 'tradeDay', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000028, 210000000001, 'common:regionCn:index', '中国区域设置', null, false, 'Icon30n', 10, 'regionCn', '/common/regionCn', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000029, 210000000028, 'common:regionCn:data', '查询权限', null, true, null, null, 'regionCn', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (210000000030, 210000000028, 'common:regionCn:operator', '操作权限', null, true, null, null, 'regionCn', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);


commit;
