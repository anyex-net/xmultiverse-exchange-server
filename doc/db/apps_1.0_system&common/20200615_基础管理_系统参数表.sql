drop table if exists SysParameter;
create table SysParameter
(
    id                  bigint(20)   not null comment '主键' primary key,
    systemName          varchar(64)  not null comment '系统名称',
    parameterName       varchar(64)  not null comment '参数名称',
    division            varchar(32)  not null comment '参数大类',
    type                varchar(32)  not null comment '参数类型',
    valueBound          varchar(128)          comment '参数值值域',
    value               varchar(64)  not null comment '参数值',
    remark              varchar(64)  not null comment '参数备注',
    createBy            bigint(20)            comment '创建人',
    createDate          bigint(13)            comment '创建时间',
    updateBy            bigint(20)            comment '更新人',
    updateDate          bigint(13)            comment '更新时间'
) comment '参数配置';


-- 初始化数据;
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000000, 'APPS', 'iosAppUpdateDownloadUrl', '电商类', '文本', null, 'https://zc.pgyer.com/qKYK', 'IOSAPP更新下载URL地址', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);

insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000001, 'APPS', 'withDrawMinAmount', '资产类', '文本', '钱包提现最小金额', '100', '钱包提现最小金额', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000002, 'APPS', 'withDrawMaxAmount', '资产类', '文本', '钱包提现最大金额', '90000', '钱包提现最大金额', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000003, 'APPS', 'withDrawFeeRate', '资产类', '文本', '钱包提现费率0到1', '0.15', '钱包提现费率', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000004, 'APPS', 'withDrawGlobalPayFeeRate', '资产类', '文本', 'globalPay代付转账费率0到1', '0.10', 'globalPay代付转账费率', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000005, 'APPS', 'withdrawTrxNoNotExsitCloseTime', '资产类', '文本', '提现订单不存在多久后再查不到认定提现失败(秒)', '3600', '提现订单不存在多久后再查不到认定提现失败(秒)', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000006, 'APPS', 'SystemTradeSwitch', '资产类', '文本', '系统交易开关', 'ON', '系统交易开关 ON开 OFF关', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000007, 'APPS', 'withDrawInterfaceSwitch', '资产类', '文本', '提现接口开关', 'OFF', '提现接口开关 ON开 OFF关', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);
insert into SysParameter (id, systemName, parameterName, division, type, valueBound, value, remark, createBy, createDate, updateBy, updateDate)
values (200000000008, 'APPS', 'withDrawDayMaxAmount', '资产类', '文本', 'PKR日累计提现限额', '50000', 'PKR日累计提现限额', 200000000000, unix_timestamp()*1000, 200000000000, unix_timestamp()*1000);

commit;