drop table if exists SysAppDevice;
create table SysAppDevice
(
    id            bigint                   not null comment '主键id' primary key,
    accountId     bigint(20)               not null comment '账户id',
    deviceType    varchar(20)              not null comment '设备类型(ios、android、client)',
    deviceName    varchar(64)              not null comment '设备名字',
    deviceNumber  varchar(128)             not null comment '设备编码',
    ipAddress     varchar(64)              not null comment 'IP地址',
    appVersion    varchar(20)              not null comment '版本号',
    buildVersion  varchar(20)              not null comment 'build版本号',
    lastLoginDate bigint(13)               not null comment '最后登录时间',
    createDate    bigint(13)               not null comment '创建时间',
    remark        varchar(64)                  null comment '备注'
) comment 'app设备表';
