drop table if exists SysAppVersion;
create table SysAppVersion
(
    id            bigint                   not null comment '主键id' primary key,
    deviceType    varchar(20)              not null comment '设备类型(ios、android、client)',
    appVersion    varchar(20)              not null comment '版本号',
    buildVersion  varchar(20)              not null comment 'build版本号',
    canSupport    int                      not null comment '是否支持(1、0)',
    checkStatus   int                      not null comment '审核状态(1、0)',
    createBy      bigint                   not null comment '创建人',
    createDate    bigint(13)               not null comment '创建时间',
    remark        varchar(64)                  null comment '备注'
) comment 'app版本表';


INSERT INTO SysAppVersion (id, deviceType, appVersion, buildVersion, canSupport, checkStatus, createBy, createDate, remark) 
VALUES (200000000000, 'ios', '1.0.0', 'build1', 1, 1, 200000000000, 1540286666844, null);
INSERT INTO SysAppVersion (id, deviceType, appVersion, buildVersion, canSupport, checkStatus, createBy, createDate, remark) 
VALUES (200000000001, 'android', '1.0.0', 'build1', 1, 1, 200000000000, 1545982059556, null);

commit;
