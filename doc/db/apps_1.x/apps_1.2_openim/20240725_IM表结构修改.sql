use imbiz;

/* drop table if exists account;
CREATE TABLE `account`
(
    `id`               bigint       NOT NULL comment 'ID',
    `user_id`          VARCHAR(255) NOT NULL COMMENT '用户ID',
    `password`         VARCHAR(255) NOT NULL COMMENT '密码',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `change_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `operator_user_id` VARCHAR(255) NOT NULL COMMENT '操作人ID',
    PRIMARY KEY (`id`)
) COMMENT '账户表'; */

/*drop table if exists admin;
CREATE TABLE `admin`
(
    `id`          bigint       NOT NULL COMMENT 'ID',
    `account`     VARCHAR(255) NOT NULL COMMENT '账号',
    `password`    VARCHAR(255) NOT NULL COMMENT '密码',
    `face_url`    VARCHAR(255) COMMENT '头像',
    `nickname`    VARCHAR(255) COMMENT '昵称',
    `user_id`     VARCHAR(255) NOT NULL COMMENT '用户ID',
    `level`       INT          NOT NULL COMMENT '',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) COMMENT '后台管理用户表';*/


drop table if exists Attribute;
CREATE TABLE `Attribute`
(
    `id`                  bigint                               NOT NULL COMMENT 'ID',
    `user_id`             VARCHAR(255)                         NOT NULL COMMENT '用户ID',
    `account`             VARCHAR(255)                         NOT NULL COMMENT '账户名',
    `phone_number`        VARCHAR(20) COMMENT '手机号',
    `area_code`           VARCHAR(10)                          COMMENT '手机区域',
    `email`               VARCHAR(255)                         COMMENT '邮箱',
    `nickname`            VARCHAR(255)                         NOT NULL comment '昵称',
    `face_url`            VARCHAR(255) COMMENT '头像',
    `gender`              TINYINT                              COMMENT '性别',
    `create_time`         DATETIME                             NOT NULL COMMENT '创建时间',
    `change_time`         DATETIME ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    `birth_time`          DATETIME                             COMMENT '生日',
    `level`               INT                                  NOT NULL COMMENT '级别',
    `allow_vibration`     TINYINT                              NOT NULL COMMENT '震动提醒',
    `allow_beep`          TINYINT                              NOT NULL COMMENT '消息提示',
    `allow_add_friend`    TINYINT                              NOT NULL COMMENT '允许添加好友',
    `global_recv_msg_opt` TINYINT                              NOT NULL COMMENT '全局接收消息选项',
    `register_type`       TINYINT                              NOT NULL COMMENT '注册类型',
    PRIMARY KEY (`id`)
) COMMENT '账户属性表';

/*
CREATE TABLE `applets`
(
    `id`          bigint       NOT NULL COMMENT 'ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '名称',
    `app_id`      VARCHAR(255) NOT NULL COMMENT 'appid',
    `icon`        VARCHAR(255) COMMENT '图标',
    `url`         VARCHAR(255) NOT NULL COMMENT '地址',
    `md5`         VARCHAR(32)  NOT NULL COMMENT 'md5',
    `size`        BIGINT       NOT NULL COMMENT '大小',
    `version`     VARCHAR(255) comment '版本',
    `priority`    INT comment '优先权 排序',
    `status`      TINYINT UNSIGNED COMMENT '状态',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) comment '小程序';*/

drop table if exists `RegisterDefaultFriend`;
CREATE TABLE `RegisterDefaultFriend`
(
    `id`       bigint       NOT NULL COMMENT 'ID',
    `user_id`  VARCHAR(255) NOT NULL COMMENT '用户ID',
    `nickname` VARCHAR(255) NOT NULL comment '昵称',
    `face_url` VARCHAR(255) COMMENT '头像',
    PRIMARY KEY (`id`)
) COMMENT '注册默认好友';

drop table if exists `RegisterDefaultGroup`;
CREATE TABLE `RegisterDefaultGroup`
(
    `id`             bigint       NOT NULL COMMENT 'ID',
    `group_id`       VARCHAR(255) NOT NULL COMMENT '群ID',
    `group_name`     VARCHAR(255) NOT NULL comment '群名称',
    `group_face_url` VARCHAR(255) COMMENT '头像',
    PRIMARY KEY (`id`)
) COMMENT '注册默认群';

drop table if exists `NoticeAccount`;
CREATE TABLE `NoticeAccount`
(
    `id`       bigint       NOT NULL COMMENT 'ID',
    `user_id`  VARCHAR(255) NOT NULL COMMENT '用户ID',
    `nickname` VARCHAR(255) NOT NULL comment '昵称',
    `face_url` VARCHAR(255) COMMENT '头像',
    PRIMARY KEY (`id`)
) COMMENT '通知账号';

