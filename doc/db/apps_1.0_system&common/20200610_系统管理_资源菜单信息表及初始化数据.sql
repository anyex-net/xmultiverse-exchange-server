drop table if exists SysResources;
create table SysResources
(
    id              bigint(18)   not null comment '主键' primary key,
    parentId        bigint(18)       null comment '上级编号',
    resCode         varchar(64)  not null comment '资源编码',
    resName         varchar(128) not null comment '资源名称',
    resDest         varchar(512)     null comment '资源描述',
    type            bit              null comment '类型（菜单、权限）',
    icon            varchar(64)      null comment '图标',
    sortNum         int(5)           null comment '排序号',
    resShortUrl     varchar(32)  not null comment '资源短地址',
    resUrl          varchar(128) not null comment '资源地址',
    createBy        bigint(18)       null comment '创建人',
    createDate      bigint(13)       null comment '创建时间',
    updateBy        bigint(18)       null comment '修改人',
    updateDate      bigint(13)       null comment '修改时间'
) comment '资源菜单信息表';


INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000001, null, 'system:index', '系统管理', null, false, 'fi-widget', 0, '/system', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000002, 200000000001, 'system:organization:index', '机构管理', null, false, 'fi-results-demographics', 1, 'organization', '/system/organization', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000003, 200000000002, 'system:organization:operator', '操作权限', null, true, null, null, 'organization', '/organization', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000004, 200000000002, 'system:organization:data', '查询权限', null, true, null, null, 'organization', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000005, 200000000001, 'system:resource:index', '资源管理', null, false, 'fi-database', 2, 'resource', '/system/resource', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000006, 200000000005, 'system:resource:operator', '操作权限', null, true, null, null, 'resource', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000007, 200000000005, 'system:resource:data', '查询权限', null, true, null, null, 'resource', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000008, 200000000001, 'system:role:index', '角色管理', null, false, 'fi-torso-business', 3, 'role', '/system/role', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000009, 200000000008, 'system:role:operator', '操作权限', null, true, null, null, 'role', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000010, 200000000008, 'system:role:data', '查询权限', null, true, null, null, 'role', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000011, 200000000001, 'system:userInfo:index', '用户管理', null, false, 'fi-torsos-all', 4, 'userInfo', '/system/userInfo', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000012, 200000000011, 'system:userInfo:operator', '操作权限', null, true, null, null, 'userInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000013, 200000000011, 'system:userInfo:data', '查询权限', null, true, null, null, 'userInfo', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000014, 200000000001, 'system:cache:index', '缓存管理', null, false, 'fi-torsos-all', 5, 'cache', '/system/cache', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000015, 200000000014, 'system:cache:operator', '操作权限', null, true, null, null, 'cache', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000016, 200000000014, 'system:cache:data', '查询权限', null, true, null, null, 'cache', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000017, null, 'common:index', '公共管理', null, false, null, 1, '/common', 'Layout', 200000000000, 1571883511490, 200000000000, 1571884027177);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000018, 200000000017, 'common:region:index', '区域代码', null, false, 'fi-torsos-all', 1, 'region', '/common/region', 200000000000, 1501467844534, 200000000000, 1571883572513);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000019, 200000000018, 'common:region:operator', '操作权限', null, true, null, null, 'region', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000020, 200000000018, 'common:region:data', '查询权限', null, true, null, null, 'region', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000021, 200000000017, 'common:dict:index', '数据字典', null, false, 'fi-list-thumbnails icon-green', 2, 'dictionary', '/common/dict', 200000000000, 1501467844534, 200000000000, 1571883583741);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000022, 200000000021, 'common:dict:operator', '操作权限', null, true, null, null, 'dictionary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000023, 200000000021, 'common:dict:data', '查询权限', null, true, null, null, 'dictionary', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000024, 200000000017, 'common:msgTemplate:index', '消息模板', null, false, 'fi-social-evernote', 3, 'msgTemplate', '/common/msgTemplate', 200000000000, 1501467844534, 200000000000, 1571883593107);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000025, 200000000024, 'common:msgTemplate:operator', '操作权限', null, true, null, null, 'msgTemplate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000026, 200000000024, 'common:msgTemplate:data', '查询权限', null, true, null, null, 'msgTemplate', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000027, 200000000017, 'common:msgRecord:index', '消息记录', null, false, 'fi-clipboard-pencil on', 4, 'msgRecord', '/common/msgRecord', 200000000000, 1519875042081, 200000000000, 1571907998769);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000028, 200000000027, 'common:msgRecord:data', '查询权限', null, true, null, null, 'msgRecord', '#', 200000000000, 1519875247868, 200000000000, 1571884285532);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000029, 200000000017, 'common:appVersion:index', 'app版本', null, false, 'fi-book', 5, 'appVersion', '/common/appVersion', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000030, 200000000029, 'common:appVersion:data', '查询权限', null, true, null, null, 'appVersion', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000031, 200000000029, 'common:appVersion:operator', '操作权限', null, true, null, null, 'appVersion', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000032, 200000000017, 'common:appDevice:index', 'app设备', null, false, 'fi-book', 6, 'appDevice', '/common/appDevice', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000033, 200000000032, 'common:appDevice:data', '查询权限', null, true, null, null, 'appDevice', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000034, 200000000032, 'common:appDevice:operator', '操作权限', null, true, null, null, 'appDevice', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000035, 200000000017, 'common:notice:index', '平台公告', null, false, 'fi-book', 7, 'notice', '/common/notice', 200000000000, 1535541344641, 200000000000, 1571884411486);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000036, 200000000035, 'common:notice:data', '查询权限', null, true, null, null, 'notice', '#', 200000000000, 1535542477761, 200000000000, 1535542477761);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (200000000037, 200000000035, 'common:notice:operator', '操作权限', null, true, null, null, 'notice', '#', 200000000000, 1535542724252, 200000000000, 1535542724252);


commit;
