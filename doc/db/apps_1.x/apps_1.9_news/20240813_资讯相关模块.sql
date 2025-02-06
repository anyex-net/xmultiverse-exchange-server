-- 资讯
drop table if exists News;
create table News
(
  id                      bigint(20)               not null comment 'Id' primary key,
  category                varchar(16)              not null comment '类别(policy政策、industry行业、platform平台)',
  title                   varchar(128)             not null comment '标题',
  summary                 varchar(512)             not null comment '摘要',
  topicImage              varchar(128)             not null comment '主题图片',
  content                 text                     not null comment '内容',
  keyword                 varchar(64)              not null comment '关键字',
  isBanner                int            not null default 0 comment '是否轮播图(0否、1是)',
  isFixed                 int            not null default 0 comment '是否固定(0否、1是)',
  state                   int            not null default 0 comment '状态(0待发布、1已发布)',
  viewNum                 int            not null default 0 comment '浏览数量',
  favoriteNum             int            not null default 0 comment '收藏数量',
  likeNum                 int            not null default 0 comment '点赞数量',
  commentNum              int            not null default 0 comment '评论数量',
  shareNum                int            not null default 0 comment '分享数量',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  createName              varchar(64)              not null comment '创建人姓名',
  updateTime              bigint(13)                        comment '更新时间',
  publishTime             bigint(13)                        comment '发布时间',
  publishName             varchar(64)                       comment '发布人姓名'
) comment '资讯';

-- 资讯收藏
drop table if exists NewsFavorite;
create table NewsFavorite
(
  id                      bigint(20)               not null comment 'Id' primary key,
  newsId                  bigint(20)               not null comment '资讯Id',
  favoriteAccountId       bigint(20)               not null comment '收藏用户Id',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `NewsFavorite_unique`(`newsId`, `favoriteAccountId`) USING BTREE
) comment '资讯收藏';

-- 资讯点赞
drop table if exists NewsLike;
create table NewsLike
(
  id                      bigint(20)               not null comment 'Id' primary key,
  newsId                  bigint(20)               not null comment '资讯Id',
  likeAccountId           bigint(20)               not null comment '点赞用户Id',
  lng                     varchar(24)                       comment '位置经度',
  lat                     varchar(24)                       comment '位置维度',
  remark                  varchar(64)                       comment '备注',
  createTime              bigint(13)               not null comment '创建时间',
  updateTime              bigint(13)                        comment '更新时间',
  UNIQUE INDEX `NewsLike_unique`(`newsId`, `likeAccountId`) USING BTREE
) comment '资讯点赞';
