delete from SysResources where id between 260100000001 and 260199999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260100000001, 260000000002, 'shop:index', '店铺管理', null, false, 'Icon30n', 1, 'shop', '/business/luckybox/shop', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260100000002, 260100000001, 'shop:shop:index', '店铺管理', null, false, 'Icon30n', 1, 'shop', '/business/luckybox/shop/shop', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260100000003, 260100000002, 'shop:shop:operator', '操作权限', null, true, null, null, 'shop', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260100000004, 260100000002, 'shop:shop:data', '查询权限', null, true, null, null, 'shop', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

commit;
