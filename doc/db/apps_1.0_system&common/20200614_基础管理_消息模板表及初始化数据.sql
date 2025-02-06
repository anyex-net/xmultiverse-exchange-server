drop table if exists SysMsgTemplate;
create table SysMsgTemplate
(
    id         bigint(18)   not null comment '主键' primary key,
    tplKey     varchar(64)  not null comment '模版KEY',
    lang       varchar(8)   not null comment '语言编码（en_US,zh_CN,zh_HK)',
    type       varchar(16)  not null comment '模版类型(email:邮件、sms:短信)',
    title      varchar(128) not null comment '标题（邮件用）',
    content    text         not null comment '模版内容',
    dest       varchar(128)     null comment '描述',
    createBy   bigint(18)       null comment '创建人',
    createDate bigint(13)       null comment '创建时间',
    constraint index_tplkey_lang unique (tplKey, lang)
) comment '消息模版表';


INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (200000000001, 'tpl_sms_send_valid_code', 'en_US', 'sms', 'Send mobile phone verification code', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (200000000002, 'tpl_sms_send_valid_code', 'zh_CN', 'sms', '发送手机验证码', '您的验证码是:%s', '发送手机验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (200000000003, 'tpl_sms_send_valid_code', 'zh_HK', 'sms', '發送手機驗證碼', '您的驗證碼是:%s', '发送手机验证码', 200000000000, 1501467844534);

INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000001, 'tpl_email_send_register_code', 'en_US', 'email', 'Registration verification code', 'Your verification code is:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000002, 'tpl_email_send_register_code', 'zh_CN', 'email', '注册验证码', '您的验证码是:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000003, 'tpl_email_send_register_code', 'zh_HK', 'email', '注册驗證碼', '您的驗證碼是:%s', '发送邮箱验证码', 200000000000, 1501467844534);

INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000004, 'tpl_email_forget_pass_code', 'en_US', 'email', 'Temporary verification code', 'Your verification code is:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000005, 'tpl_email_forget_pass_code', 'zh_CN', 'email', '忘记密码验证码', '您的验证码是:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000006, 'tpl_email_forget_pass_code', 'zh_HK', 'email', '忘记密码驗證碼', '您的驗證碼是:%s', '发送邮箱验证码', 200000000000, 1501467844534);

INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000007, 'tpl_email_bind_send_code', 'en_US', 'email', 'BindingEmail verification code', 'Your verification code is:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000008, 'tpl_email_bind_send_code', 'zh_CN', 'email', '绑定邮箱验证码', '您的验证码是:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000009, 'tpl_email_bind_send_code', 'zh_HK', 'email', '绑定邮箱驗證碼', '您的驗證碼是:%s', '发送邮箱验证码', 200000000000, 1501467844534);

INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000010, 'tpl_email_send_other_code', 'en_US', 'email', 'Email verification code', 'Your verification code is:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000011, 'tpl_email_send_other_code', 'zh_CN', 'email', '邮箱验证码', '您的验证码是:%s', '发送邮箱验证码', 200000000000, 1501467844534);
INSERT INTO SysMsgTemplate (id, tplKey, lang, type, title, content, dest, createBy, createDate) 
VALUES (300000000012, 'tpl_email_send_other_code', 'zh_HK', 'email', '邮箱驗證碼', '您的驗證碼是:%s', '发送邮箱验证码', 200000000000, 1501467844534);

commit;
