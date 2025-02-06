delete from SysResources where id between 230000000001 and 239999999999;

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000001, null, 'social:index', '社交管理', null, false, 'Icon30n', 3, '/social', 'Layout', 200000000000, 1501467844534, 200000000000, 1572230046622);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000002, 230000000001, 'social:snsPost:index', '社交帖子', null, false, 'Icon30n', 1, 'snsPost', '/social/snsPost', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000003, 230000000002, 'social:snsPost:operator', '操作权限', null, true, null, null, 'snsPost', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000004, 230000000002, 'social:snsPost:data', '查询权限', null, true, null, null, 'snsPost', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000005, 230000000001, 'social:snsPostLike:index', '社交帖子点赞', null, false, 'Icon30n', 2, 'snsPostLike', '/social/snsPostLike', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000006, 230000000005, 'social:snsPostLike:operator', '操作权限', null, true, null, null, 'snsPostLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000007, 230000000005, 'social:snsPostLike:data', '查询权限', null, true, null, null, 'snsPostLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000008, 230000000001, 'social:snsPostShare:index', '社交帖子分享', null, false, 'Icon30n', 3, 'snsPostShare', '/social/snsPostShare', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000009, 230000000008, 'social:snsPostShare:operator', '操作权限', null, true, null, null, 'snsPostShare', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000010, 230000000008, 'social:snsPostShare:data', '查询权限', null, true, null, null, 'snsPostShare', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000011, 230000000001, 'social:snsPostComment:index', '社交帖子评论', null, false, 'Icon30n', 4, 'snsPostComment', '/social/snsPostComment', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000012, 230000000011, 'social:snsPostComment:operator', '操作权限', null, true, null, null, 'snsPostComment', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000013, 230000000011, 'social:snsPostComment:data', '查询权限', null, true, null, null, 'snsPostComment', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000014, 230000000001, 'social:snsPostCommentLike:index', '社交帖子评论点赞', null, false, 'Icon30n', 5, 'snsPostCommentLike', '/social/snsPostCommentLike', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000015, 230000000014, 'social:snsPostCommentLike:operator', '操作权限', null, true, null, null, 'snsPostCommentLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000016, 230000000014, 'social:snsPostCommentLike:data', '查询权限', null, true, null, null, 'snsPostCommentLike', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000017, 230000000001, 'social:snsFollow:index', '社交关注(我关注的)', null, false, 'Icon30n', 6, 'snsFollow', '/social/snsFollow', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000018, 230000000017, 'social:snsFollow:operator', '操作权限', null, true, null, null, 'snsFollow', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000019, 230000000017, 'social:snsFollow:data', '查询权限', null, true, null, null, 'snsFollow', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000020, 230000000001, 'social:snsFans:index', '社交粉丝(关注我的)', null, false, 'Icon30n', 7, 'snsFans', '/social/snsFans', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000021, 230000000020, 'social:snsFans:operator', '操作权限', null, true, null, null, 'snsFans', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000022, 230000000020, 'social:snsFans:data', '查询权限', null, true, null, null, 'snsFans', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000023, 230000000001, 'social:snsFriend:index', '社交好友', null, false, 'Icon30n', 8, 'snsFriend', '/social/snsFriend', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000024, 230000000023, 'social:snsFriend:operator', '操作权限', null, true, null, null, 'snsFriend', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000025, 230000000023, 'social:snsFriend:data', '查询权限', null, true, null, null, 'snsFriend', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);

INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000026, 230000000001, 'social:snsActivity:index', '社交活动', null, false, 'Icon30n', 8, 'snsActivity', '/social/snsActivity', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000027, 230000000026, 'social:snsActivity:operator', '操作权限', null, true, null, null, 'snsActivity', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);
INSERT INTO SysResources (id, parentId, resCode, resName, resDest, type, icon, sortNum, resShortUrl, resUrl, createBy, createDate, updateBy, updateDate)
VALUES (230000000028, 230000000026, 'social:snsActivity:data', '查询权限', null, true, null, null, 'snsActivity', '#', 200000000000, 1501467844534, 200000000000, 1501467844534);


commit;
