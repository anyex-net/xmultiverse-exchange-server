drop table if exists SysTradeDay;
create table SysTradeDay
(
    id              bigint(20)      not null comment '主键' primary key,
    date            varchar(8)      not null comment '日期YYYYMMDD',
    type            int             not null comment '类型(0非交易日、1交易日)',
    createTime      bigint(13)      not null comment '创建时间',
    createName      varchar(32)              comment '创建人名字',
    updateTime      bigint(13)               comment '更新时间',
    updateName      varchar(32)              comment '更新人名字'
) comment '交易日';