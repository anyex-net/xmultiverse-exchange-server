/*
DROP DATABASE IF EXISTS exchangev1;

CREATE DATABASE exchangev1 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE exchangev1;
*/

drop table if exists SysOrganization;
create table SysOrganization
(
    id         bigint(18)   not null comment '主键' primary key,
    parentId   bigint(18)       null comment '上级编号',
    orgCode    varchar(64)  not null comment '机构编码',
    orgName    varchar(128) not null comment '机构名称',
    orgDest    varchar(128)     null comment '机构描述',
    createBy   varchar(18)      null comment '创建人',
    createDate bigint(13)       null comment '创建时间',
    updateBy   varchar(18)      null comment '修改人',
    updateDate bigint(13)       null comment '修改时间',
    sortNum    int(5)           null comment '排序号',
    remark     varchar(64)      null comment '备注'
) comment '机构信息表';


INSERT INTO SysOrganization (id, parentId, orgCode, orgName, orgDest, createBy, createDate, updateBy, updateDate, sortNum)
VALUES (200000000000, null, 'APPS', 'APPS', 'APPS', '200000000000', 1501467844534, '200000000000', 1543375576284, 0);
INSERT INTO SysOrganization (id, parentId, orgCode, orgName, orgDest, createBy, createDate, updateBy, updateDate, sortNum)
VALUES (200000000001, 200000000000, 'APPS_MG', '管理层', '管理层', '200000000000', 1501467844534, '200000000000', 1501467844534, 0);
INSERT INTO SysOrganization (id, parentId, orgCode, orgName, orgDest, createBy, createDate, updateBy, updateDate, sortNum)
VALUES (200000000002, 200000000000, 'APPS_IT', '技术中心', '技术中心', '200000000000', 1501467844534, '200000000000', 1501467844534, 0);
INSERT INTO SysOrganization (id, parentId, orgCode, orgName, orgDest, createBy, createDate, updateBy, updateDate, sortNum)
VALUES (200000000003, 200000000000, 'APPS_OP', '运营部', '运营部', '200000000000', 1501467844534, '200000000000', 1501467844534, 0);
INSERT INTO SysOrganization (id, parentId, orgCode, orgName, orgDest, createBy, createDate, updateBy, updateDate, sortNum)
VALUES (200000000004, 200000000000, 'APPS_CC', '客服部', '客服部', '200000000000', 1501467844534, '200000000000', 1501467844534, 0);

commit;
