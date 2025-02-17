delete from SysResources where id between 290000000001 and 299999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000001, null, 'news:index', '资讯管理', null, false, 'Icon30n', 9, '/newsMgt', 'Layout', 200000000000, 1501467844534, 200000000000, 1572240046622);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000002, 290000000001, 'news:news:index', '资讯管理', null, false, 'Icon30n', 1, 'news', '/news/news', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000003, 290000000002, 'news:news:operator', '操作权限', null, true, null, null, 'news', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000004, 290000000002, 'news:news:data', '查询权限', null, true, null, null, 'news', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000005, 290000000001, 'news:newsFavorite:index', '资讯收藏', null, false, 'Icon30n', 2, 'newsFavorite', '/news/newsFavorite', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000006, 290000000005, 'news:newsFavorite:operator', '操作权限', null, true, null, null, 'newsFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000007, 290000000005, 'news:newsFavorite:data', '查询权限', null, true, null, null, 'newsFavorite', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000008, 290000000001, 'news:newsLike:index', '资讯点赞', null, false, 'Icon30n', 3, 'newsLike', '/news/newsLike', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000009, 290000000008, 'news:newsLike:operator', '操作权限', null, true, null, null, 'newsLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (290000000010, 290000000008, 'news:newsLike:data', '查询权限', null, true, null, null, 'newsLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;