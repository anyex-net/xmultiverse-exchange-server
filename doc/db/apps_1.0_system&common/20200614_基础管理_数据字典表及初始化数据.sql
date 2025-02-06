drop table if exists SysDictionary;
create table SysDictionary
(
    id         bigint(18)   not null comment '主键' primary key,
    parentId   varchar(18)      null comment '上级编码',
    code       varchar(64)  not null comment '编码',
    name       varchar(128) not null comment '名称',
    lang       varchar(32)      null comment '语言类型',
    dest       varchar(128)     null comment '描述',
    sortNum    int(5)           null comment '排序号',
    active     bit          not null comment '启用标识',
    createBy   bigint(18)       null comment '创建人',
    createDate bigint(13)       null comment '创建时间',
    constraint SysDictionary_index_code unique (code)
) comment '数据字典表';


INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000028, null, 'noticeType', '公告类型', 'zh_CN', '1', 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000029, '200000000028', 'message', '消息', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000030, '200000000028', 'notice', '公告', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000031, null, 'langType', '语言类型', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000032, '200000000031', 'zh_CN', '简体', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000033, '200000000031', 'zh_HK', '繁体', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000034, '200000000031', 'en_US', '英文', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000035, null, 'noticeStatus', '公告状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000036, '200000000035', 'published', '已发布', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000037, '200000000035', 'unPublished', '未发布', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000038, null, 'transferStatus', '汇出状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000039, '200000000038', 'noTransfer', '无需汇出', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000040, '200000000038', 'unTransfer', '未汇出', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000041, '200000000038', 'transferPending', '待划拨', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000042, '200000000038', 'transfer', '已汇出', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000043, null, 'approveStatus', '审批状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000044, '200000000043', 'noApprove', '无需审批', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000045, '200000000043', 'waitingEmailConfirm', '待Email确认', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000046, '200000000043', 'auditPending', '待审核', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000047, '200000000043', 'checkPending', '待复核', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000048, '200000000043', 'auditReject', '审核拒绝', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000049, '200000000043', 'checkThrough', '复核通过', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000050, '200000000043', 'checkReject', '复核拒绝', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000051, '200000000043', 'cancel', '申请取消', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000052, null, 'status', '状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000053, '200000000052', 'effective', '有效', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000054, '200000000052', 'invalid', '无效', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000055, null, 'entrustStatus', '委托状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000056, '200000000055', 'pending', '待成交', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000057, '200000000055', 'allAccepted', '全部接受', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000058, '200000000055', 'partiaAccepted', '部分接受', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000059, '200000000055', 'refused', '已拒绝', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000060, '200000000055', 'expired', '已过期', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000061, '200000000055', 'withdrawed', '已撤单', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000062, null, 'yesOrNo', '是否可以', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000063, '200000000062', 'yes', '是', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000064, '200000000062', 'no', '否', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000065, null, 'succeedOrFail', '成功失败', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000066, '200000000065', 'succeed', '成功', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000067, '200000000065', 'fail', '失败', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000068, null, 'active_status', '启用状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000069, '200000000068', 'active_enable', '启用', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000070, '200000000068', 'active_disabe', '停用', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000071, null, 'gender_status', '男女性别', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000072, '200000000071', 'gender_man', '男', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000073, '200000000071', 'gender_wom', '女', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000074, null, 'accoutStatus', '账户状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000075, '200000000074', 'accountNormal', '正常', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000076, '200000000074', 'accountFrozen', '冻结', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000077, '200000000074', 'accountClose', '注销', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000078, null, 'accountGoogleBind', '账户绑定GA验证器否', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000079, '200000000078', 'unBindGA', '未绑定GA', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000080, '200000000078', 'bindGA', '已绑定GA', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000089, null, 'fundDirect', '资金方向', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000090, '200000000089', 'collect', '收款', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000091, '200000000089', 'payment', '付款', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000092, null, 'confirmStatus', '确认状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000093, '200000000092', 'unconfirm', '未到账', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000094, '200000000092', 'confirm', '已完成', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000095, '200000000092', 'confirmFail', '确认失败', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000141, null, 'authStatus', '认证状态', null, null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000142, '200000000141', 'unauth', '未认证', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000143, '200000000141', 'auth', '已认证', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000148, null, 'msgTemplateType', '消息模板类型', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000149, '200000000148', 'email', '邮件', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000150, '200000000148', 'sms', '短信', 'zh_CN', null, 0, true, 200000000000, 1508470145117);
-- GlobalPay 账户类型
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000151, null, 'globalPayAccountType', 'GlobalPay Account Type', 'en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000152, '200000000151', 'BANK', 'BANK', 'en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000153, '200000000151', 'WALLET', 'WALLET', 'en_US', null, 0, true, 200000000000, 1508470145117);
-- GlobalPay银行编码
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000201, null, 'globalPayBankCode', 'GlobalPay Bank Code', 'en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000202,200000000201,'ADVANS_PAKISTAN_MICRO_FINANCE_BANK','ADVANS_PAKISTAN_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000203,200000000201,'ALBARAKA_ISLAMIC_BANK','ALBARAKA_ISLAMIC_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000204,200000000201,'ALLIED_BANK_LIMITED','ALLIED_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000205,200000000201,'APNA_MICRO_FINANCE_BANK','APNA_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000206,200000000201,'ASKARI_BANK_LIMITED','ASKARI_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000207,200000000201,'BANK_AL_HABIB_LIMITED','BANK_AL_HABIB_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000208,200000000201,'BANK_ALFALAH_LIMITED','BANK_ALFALAH_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000209,200000000201,'BANK_ISLAMI_PAKISTAN_LIMITED','BANK_ISLAMI_PAKISTAN_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000210,200000000201,'BANK_OF_KHYBER','BANK_OF_KHYBER','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000211,200000000201,'CITI_BANK_NA','CITI_BANK_NA','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000212,200000000201,'DUBAI_ISLAMIC_BANK_PAKISTAN_LIMITED','DUBAI_ISLAMIC_BANK_PAKISTAN_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000213,200000000201,'FAYSAL_BANK_LIMITED','FAYSAL_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000214,200000000201,'FINCA_MICRO_FINANCE_BANK','FINCA_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000215,200000000201,'FIRST_WOMEN_BANK_LIMITED','FIRST_WOMEN_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000216,200000000201,'HABIB_BANK_LIMITED','HABIB_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000217,200000000201,'HABIB_METROPOLITAN_BANK_LIMITED','HABIB_METROPOLITAN_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000218,200000000201,'HBL_MICRO_FINANCE_BANK','HBL_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000219,200000000201,'INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA_LIMITED','INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000220,200000000201,'JS_BANK_LIMITED','JS_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000221,200000000201,'KHUSHHALI_MICRO_FINANCE_BANK','KHUSHHALI_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000222,200000000201,'MCB_ARIF_HABIB','MCB_ARIF_HABIB','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000223,200000000201,'MCB_BANK_LIMITED','MCB_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000224,200000000201,'MCB_ISLAMIC','MCB_ISLAMIC','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000225,200000000201,'MEEZAN_BANK','MEEZAN_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000226,200000000201,'NATIONAL_BANK_OF_PAKISTAN','NATIONAL_BANK_OF_PAKISTAN','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000227,200000000201,'NBP_FUNDS','NBP_FUNDS','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000228,200000000201,'NRSP_MICRO_FINANCE_BANK','NRSP_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000229,200000000201,'SAMBA_BANK_LIMITED','SAMBA_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000230,200000000201,'SILK_BANK_LIMITED','SILK_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000231,200000000201,'SINDH_BANK_LIMITED','SINDH_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000232,200000000201,'SONERI_BANK_LIMITED','SONERI_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000233,200000000201,'STANDARD_CHARTERED_BANK_LTD','STANDARD_CHARTERED_BANK_LTD','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000234,200000000201,'SUMMIT_BANK_LIMITED','SUMMIT_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000235,200000000201,'TELENOR_MICRO_FINANCE_BANK','TELENOR_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000236,200000000201,'THE_BANK_OF_PUNJAB','THE_BANK_OF_PUNJAB','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000237,200000000201,'U_MICRO_FINANCE_BANK','U_MICRO_FINANCE_BANK','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000238,200000000201,'UNITED_BANK_LIMITED','UNITED_BANK_LIMITED','en_US', null, 0, true, 200000000000, 1508470145117);
-- GlobalPay钱包编码
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000401, null, 'globalPayWalletCode', 'GlobalPay Wallet Code', 'en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000402,200000000401,'JAZZCASH','JAZZCASH','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000403,200000000401,'EASYPAISA','EASYPAISA','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000404,200000000401,'NAYAPAY','NAYAPAY','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000405,200000000401,'FINJA','FINJA','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000406,200000000401,'UPAISA','UPAISA','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000407,200000000401,'KONNECT','KONNECT','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000408,200000000401,'SADAPAY','SADAPAY','en_US', null, 0, true, 200000000000, 1508470145117);
-- 业务类型
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000501, null, 'businessType', 'Business Type', 'en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000502,200000000501,'deposit','Top Up','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000503,200000000501,'withDraw','Withdraw','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000504,200000000501,'withDrawRollBack','withDrawRollBack','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000505,200000000501,'gameSpin','Spin','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000506,200000000501,'gameSpinReward','Bonus-Spin','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000507,200000000501,'activityTreasureHunt','Lucky','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000508,200000000501,'activityTreasureHuntReward','Bonus-Lucky','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000509,200000000501,'activityHotDeals','Shop','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000510,200000000501,'activityHotDealsBalancePayment','Bonus-Shop','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000511,200000000501,'activityHotDealsReward','Balance','en_US', null, 0, true, 200000000000, 1508470145117);
INSERT INTO SysDictionary (id, parentId, code, name, lang, dest, sortNum, active, createBy, createDate) VALUES (200000000512,200000000501,'fee','Service Fee','en_US', null, 0, true, 200000000000, 1508470145117);

commit;
