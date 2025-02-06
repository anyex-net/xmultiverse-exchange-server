drop table if exists SysUserData;
create table SysUserData
(
    id          bigint(18) not null comment '主键' primary key,
    userId      bigint(18) not null comment '用户Id',
    orgId       bigint(18) not null comment '机构Id'
) comment '用户数据权限表';


drop table if exists SysRoleData;
create table SysRoleData
(
    id          bigint(18) not null comment '主键' primary key,
    roleId      bigint(18) not null comment '用户Id',
    orgId       bigint(18) not null comment '机构Id'
) comment '角色数据权限表';

