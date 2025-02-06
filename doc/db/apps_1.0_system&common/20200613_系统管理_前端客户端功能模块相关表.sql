drop table if exists SysFrontModule;
create table SysFrontModule
(
    id                bigint(20)               not null comment 'ID' primary key,
    moduleClass       varchar(32)              not null comment '功能类别',
    moduleCode        varchar(32)              not null comment '功能编码',
    moduleName        varchar(32)              not null comment '功能名称',
    moduleIconUrl     varchar(256)             not null comment '功能图标url',
    moduleJumpUrl     varchar(64)              not null comment '功能具体跳转url',
    sortNum           int(2)                   not null comment '排序号',
    remark            varchar(128)                      comment '备注',
    createBy          bigint(20)               not null comment '创建人',
    createDate        bigint(13)               not null comment '创建时间',
    updateBy          bigint(20)                        comment '更新人',
    updateDate        bigint(13)                        comment '更新时间'
) comment '前端功能模块表';


drop table if exists SysFrontRoleRes;
create table SysFrontRoleRes
(
    id          bigint(20) not null comment '主键' primary key,
    roleId      bigint(20) not null comment '角色ID',
    moduleId    bigint(20) not null comment '功能模块ID'
) comment '前端角色权限信息表';


INSERT INTO SysFrontModule (id, moduleCode, moduleName, moduleIconUrl, sortNum, remark, createBy, createDate, updateBy, updateDate, moduleClass, moduleJumpUrl) VALUES (1, '日常维修', '日常维修', '4acAnWRbcs.png', 30, '日常维修', 200000000000, 1678109708705, 985, 973, '报修', '/pages/workbench/repair/dailyRepair/index');
INSERT INTO SysFrontModule (id, moduleCode, moduleName, moduleIconUrl, sortNum, remark, createBy, createDate, updateBy, updateDate, moduleClass, moduleJumpUrl) VALUES (2, '客房维修', '客房维修', 'iFrh8exhTJ.png', 130, 'nsV7SFLY88', 200000000000, 1678756589117, 470, 987, '报修', '/pages/workbench/repair/roomRepair/index');
INSERT INTO SysFrontModule (id, moduleCode, moduleName, moduleIconUrl, sortNum, remark, createBy, createDate, updateBy, updateDate, moduleClass, moduleJumpUrl) VALUES (3, '通讯录', '通讯录', 'BKWxGmC7QR.png', 128, '7UvTvGG1yK', 200000000000, 1678109723471, 802, 234, 'OA', '/pages/workbench/oa/contacts/index');
INSERT INTO SysFrontModule (id, moduleCode, moduleName, moduleIconUrl, sortNum, remark, createBy, createDate, updateBy, updateDate, moduleClass, moduleJumpUrl) VALUES (4, '房态管理', '房态管理', 'BThFrK2fxd.png', 349, 'HFhxXVAWA3', 200000000000, 1678109729386, 726, 80, 'OA', '/pages/workbench/oa/roomRealtimeStatus/index');

INSERT INTO SysFrontRoleRes (id, roleId, moduleId) VALUES (200000000001, 200000000000, 200000000001);

commit;