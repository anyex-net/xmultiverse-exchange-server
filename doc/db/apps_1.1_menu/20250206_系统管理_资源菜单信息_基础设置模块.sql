delete from SysResources where id between 300000000001 and 309999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000001, null, 'base:index', '基础设置', null, false, 'Icon30n', 2, '/baseMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572300046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000002, 300000000001, 'base:currencies:index', '平台币种', null, false, 'Icon30n', 1, 'currencies', '/base/currencies', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000003, 300000000002, 'base:currencies:operator', '操作权限', null, true, null, null, 'currencies', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000004, 300000000002, 'base:currencies:data', '查询权限', null, true, null, null, 'currencies', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000005, 300000000001, 'base:instruments:index', '平台交易产品', null, false, 'Icon30n', 2, 'instruments', '/base/instruments', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000006, 300000000005, 'base:instruments:operator', '操作权限', null, true, null, null, 'instruments', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000007, 300000000005, 'base:instruments:data', '查询权限', null, true, null, null, 'instruments', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000008, 300000000001, 'base:instrumentsCurrency:index', '交易产品币种信息', null, false, 'Icon30n', 3, 'instrumentsCurrency', '/base/instrumentsCurrency', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000009, 300000000008, 'base:instrumentsCurrency:operator', '操作权限', null, true, null, null, 'instrumentsCurrency', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000010, 300000000008, 'base:instrumentsCurrency:data', '查询权限', null, true, null, null, 'instrumentsCurrency', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000011, 300000000001, 'base:instTradeFee:index', '平台交易费率', null, false, 'Icon30n', 4, 'instTradeFee', '/base/instTradeFee', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000012, 300000000011, 'base:instTradeFee:operator', '操作权限', null, true, null, null, 'instTradeFee', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000013, 300000000011, 'base:instTradeFee:data', '查询权限', null, true, null, null, 'instTradeFee', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000014, 300000000001, 'base:userInstTradeFee:index', '用户交易费率', null, false, 'Icon30n', 5, 'userInstTradeFee', '/base/userInstTradeFee', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000015, 300000000014, 'base:userInstTradeFee:operator', '操作权限', null, true, null, null, 'userInstTradeFee', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000016, 300000000014, 'base:userInstTradeFee:data', '查询权限', null, true, null, null, 'userInstTradeFee', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000017, 300000000001, 'base:userInstrumentsFavorite:index', '用户交易产品收藏', null, false, 'Icon30n', 6, 'userInstrumentsFavorite', '/base/userInstrumentsFavorite', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000018, 300000000017, 'base:userInstrumentsFavorite:operator', '操作权限', null, true, null, null, 'userInstrumentsFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (300000000019, 300000000017, 'base:userInstrumentsFavorite:data', '查询权限', null, true, null, null, 'userInstrumentsFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
