-- 账户用户

-- 社交帖子
drop table if exists SnsPost;
create table SnsPost
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  postTextContent         varchar(512)                      comment '帖子文本内容',
  postImageUrl            text                              comment '帖子图片URL',
  postVideoUrl            text                              comment '帖子视频URL',
  openness                int                     default 0 comment '0匿名、1公开',
  viewer                  int                     default 0 comment '0公开、1仅限好友、2仅限粉丝、3仅限自己',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  city                    varchar(64)                       comment '位置城市',
  viewNum                 int                     default 0 comment '浏览数量',
  favoriteNum             int                     default 0 comment '收藏数量',
  likeNum                 int                     default 0 comment '点赞数量',
  commentNum              int                     default 0 comment '评论数量',
  shareNum                int                     default 0 comment '分享数量',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间'
) comment '社交帖子';

INSERT INTO SnsPost VALUES
(10000, 10000, 'postTextContent', 'postImageUrl', 'postVideoUrl', 1, 1, '121.123456', '29.123456', '宁波', 0, 0, 0, 0, 0, 'remark', 1709529514832, null);
commit;

-- 社交帖子点赞
drop table if exists SnsPostLike;
create table SnsPostLike
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  postId                  bigint(20)               not null comment '帖子Id',
  likeUserId              varchar(64)              not null comment '点赞用户Id',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  city                    varchar(64)                       comment '位置城市',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `SnsPostLike_unique`(`postId`, `likeUserId`) USING BTREE
) comment '社交帖子点赞';

INSERT INTO SnsPostLike VALUES
(10000, 10000, 10000, 10000, '121.123456', '29.123456', '宁波', 'remark', 1709529514832, null);
commit;

-- 社交帖子分享
drop table if exists SnsPostShare;
create table SnsPostShare
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  postId                  bigint(20)               not null comment '帖子Id',
  shareUserId             varchar(64)              not null comment '分享用户Id',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  city                    varchar(64)                       comment '位置城市',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间'
) comment '社交帖子分享';

INSERT INTO SnsPostShare VALUES
(10000, 10000, 10000, 10000, '121.123456', '29.123456', '宁波', 'remark', 1709529514832, null);
commit;

-- 社交帖子评论
drop table if exists SnsPostComment;
create table SnsPostComment
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  postId                  bigint(20)               not null comment '帖子Id',
  commentUserId           varchar(64)              not null comment '评论用户Id',
  commentContent          varchar(256)             not null comment '评论内容',
  commentLikeNum          int                     default 0 comment '评论点赞数量',
  replyTo                 varchar(20)                       comment '父级评论的ID(如果是根评论则为NULL)',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  city                    varchar(64)                       comment '位置城市',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间'
) comment '社交帖子评论';

INSERT INTO SnsPostComment VALUES
(10000, 10000, 10000, 10000, 'commentContent', 0, null, '121.123456', '29.123456', '宁波', 'remark', 1709529514832, null);
commit;

-- 社交帖子评论点赞
drop table if exists SnsPostCommentLike;
create table SnsPostCommentLike
(
  id                      bigint(20)               not null comment 'Id' primary key,
  postId                  bigint(20)               not null comment '帖子Id',
  postCommentId           bigint(20)               not null comment '帖子评论Id',
  likeUserId              varchar(64)              not null comment '点赞用户Id',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  city                    varchar(64)                       comment '位置城市',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `SnsPostCommentLike_unique`(`postCommentId`, `likeUserId`) USING BTREE
) comment '社交帖子评论点赞';

INSERT INTO SnsPostCommentLike VALUES
(10000, 10000, 10000, 10000, '121.123456', '29.123456', '宁波', 'remark', 1709529514832, null);
commit;

-- 社交关注(我关注的)
drop table if exists SnsFollow;
create table SnsFollow
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  followedUserId          varchar(64)              not null comment '被关注者用户Id',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `SnsFollow_unique`(`userId`, `followedUserId`) USING BTREE
) comment '社交关注(我关注的)';

INSERT INTO SnsFollow VALUES
(10000, 10000, 10001, 'remark', 1709529514832, null);
commit;

-- 社交粉丝(关注我的)
drop table if exists SnsFans;
create table SnsFans
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  followerUserId          varchar(64)              not null comment '关注者用户Id',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `SnsFollowFans_unique`(`userId`, `followerUserId`) USING BTREE
) comment '社交粉丝(关注我的)';

INSERT INTO SnsFans VALUES
(10000, 10000, 10002, 'remark', 1709529514832, null);
commit;

-- 社交好友(互相关注)
drop table if exists SnsFriend;
create table SnsFriend
(
  id                      bigint(20)               not null comment 'Id' primary key,
  userId                  varchar(64)              not null comment '用户Id',
  friendUserId            varchar(64)              not null comment '好友用户Id',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `SnsFriend_unique`(`userId`, `friendUserId`) USING BTREE
) comment '社交好友(互相关注)';

INSERT INTO SnsFriend VALUES
(10000, 10000, 10003, 'remark', 1709529514832, null);
(10001, 10003, 10000, 'remark', 1709529514832, null);
commit;
