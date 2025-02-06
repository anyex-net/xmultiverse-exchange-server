drop table if exists SysAccessLog;
create table SysAccessLog
(
	id             bigint                  not null comment '主键id' primary key,
	userName       varchar(64)                      comment '用户名称',
	module         varchar(256)                     comment '模块',
	method         varchar(256)            not null comment '方法',
	type           varchar(32)                      comment '类型',
	remark         varchar(2048)           not null comment '描述',
	reqParam       varchar(4000)                    comment '请求参数',
	respParam      varchar(4000)                    comment '返回参数',
	uri            varchar(1024)           not null comment '请求URI',
	ip             varchar(64)             not null comment 'ip',
	createDate     bigint(13)              not null comment '创建时间'
) comment '访问日志表';
