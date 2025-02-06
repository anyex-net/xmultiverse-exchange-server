delete from SysResources where id between 260000000001 and 269999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260000000001, null, 'business:index', '业务管理', null, false, 'Icon30n', 6, '/business', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (260000000002, 260000000001, 'business:luckybox:index', 'Luckybox', null, false, 'Icon30n', 1, 'luckybox', '/business/luckybox', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
