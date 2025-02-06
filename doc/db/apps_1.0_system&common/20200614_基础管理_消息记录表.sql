drop table if exists SysMsgRecord;
create table SysMsgRecord
(
    id         bigint(20)  not null comment '主键' primary key,
    type       varchar(8)  not null comment '消息类型(email:邮件、sms:短信)',
    object     varchar(64) not null comment '发送对象',
    content    text        not null comment '内容',
    createDate bigint(13)  not null comment '创建时间',
    status     bit         not null comment '发送状态(0:成功，1:失败)'
) comment '消息发送记录';