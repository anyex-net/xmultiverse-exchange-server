drop table if exists SysRoleInfo;
create table SysRoleInfo
(
    id         bigint(18)   not null comment '主键' primary key,
    roleCode   varchar(32)  not null comment '角色编码',
    roleName   varchar(64)  not null comment '角色名称',
    roleDest   varchar(128)     null comment '角色描述',
    needGa     bit              null comment '需要GA验证 1需要 0不需要',
    createBy   bigint(18)       null comment '创建人',
    createDate bigint(13)       null comment '创建时间',
    updateBy   bigint(18)       null comment '修改人',
    updateDate bigint(13)       null comment '修改时间'
) comment '角色信息表';


INSERT INTO SysRoleInfo (id, roleCode, roleName, roleDest, needGa, createBy, createDate, updateBy, updateDate)
VALUES (200000000000, 'ROLE_ADMIN', '管理员', '管理员', false, 200000000000, 1501467844534, 200000000000, 1501467844534);

commit;