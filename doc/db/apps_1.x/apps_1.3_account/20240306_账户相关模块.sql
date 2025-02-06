-- 账户表
drop table if exists Account;
create table Account
(
    id                      bigint(20)               not null comment '账户ID' primary key,
    unid                    bigint(20)               not null comment '账户编号',
    userId                  varchar(64)              not null comment 'openIM_userId',
    country                 varchar(8)               not null comment '国家默认86(巴基斯坦92)',
    mobile                  varchar(16)                       comment '手机号码',
    accountName             varchar(32)                       comment '账户昵称',
    realName                varchar(32)                       comment '账户姓名',
    cnic                    varchar(32)                       comment 'CNIC',
    loginPwd                varchar(64)              not null comment '登录密码',
    headUrl                 varchar(512)             not null comment '头像URL',
    email                   varchar(32)              not null comment '邮箱',
    birth                   varchar(16)                       comment '生日',
    gender                  tinyint(1)                        comment '性别(0男、1女)',
    deviceId                varchar(64)                       comment '设备编号',
    ip                      varchar(64)                       comment 'IP地址',
    lng                     varchar(24)                       comment '最新位置经度',
    lat                     varchar(24)                       comment '最新位置维度',
    city                    varchar(64)                       comment '最新位置城市',
    invitationCode          varchar(8)               not null comment '邀请码',
    referralCode            varchar(8)                        comment '推荐码',
    source                  varchar(32)                       comment '来源',
    status                  int            default 0 not null comment '状态(0:正常、1:冻结、2:注销)',
    sign                    varchar(64)              not null comment 'sign',
    randomKey               varchar(8)               not null comment 'randomKey',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                bigint(20)                        comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment '账户表';

INSERT INTO Account VALUES
(200000000000, 10000, 10000, '92', 921234567890, '测试账户accountName', '测试账户realName', 'cnic123456789', 'loginPwd', 'headUrl', 'test1@qq.com', '1990-10-10', '1', '10000', '127.0.0.1', '121.123', '29.123', '宁波', '87654321', '12345678', 'ios', 0, 'sign', 'random', 'remark', 1709529514832, NULL, 1709529514832);
commit;

-- 账户地址表
drop table if exists AccountAddress;
create table AccountAddress
(
    id                      bigint(20)               not null comment 'ID' primary key,
    accountId               bigint(20)               not null comment '账户ID',
    name                    varchar(64)              not null comment '收件人姓名',
    mobile                  varchar(16)              not null comment '手机号码',
    email                   varchar(16)                       comment '邮箱',
    area                    varchar(256)             not null comment '区域',
    address                 varchar(128)             not null comment '地址',
    landmark                varchar(128)             not null comment '地标',
    prime                   tinyint(1)               not null comment '是否默认地址(0否、1是)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateTime              bigint(13)                        comment '更新时间'
) comment '账户地址表';

INSERT INTO AccountAddress VALUES
(200000000000, 200000000000, '测试账户1', 921234567890, 'test1@qq.com', 'area', 'address', 'landmark', 1, 'remark', 1709529514832, 1709529514832);
commit;

-- 账户收款银行表
drop table if exists AccountReceivingBank;
create table AccountReceivingBank
(
    id                      bigint(20)               not null comment 'ID' primary key,
    accountId               bigint(20)               not null comment '账户ID',
    accountType             varchar(32)              not null comment '账户类型(BANK、WALLET)',
    accountNo               varchar(32)              not null comment '收款账号(手机号码)',
    accountName             varchar(64)              not null comment '收款姓名',
    bankName                varchar(64)              not null comment '银行名字',
    iban                    varchar(64)              not null comment '国际银行账户号码(InternationalBankAccountNumber)',
    cnic                    varchar(32)              not null comment '身份证号码',
    email                   varchar(32)              not null comment '邮箱',
    mobile                  varchar(16)              not null comment '手机号码',
    status                  int                      not null comment '状态(0未验证、1验证成功、2验证失败)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateTime              bigint(13)                        comment '更新时间'
) comment '账户收款银行表';

INSERT INTO AccountReceivingBank VALUES
(200000000000, 200000000000, 'BANK', '22010104830000', 'AL HAMAD TRADERS', 'Meezan Bank-Bhawalpur Eid Gha Branch', 'PK15MEZN0022010104830000', 'cnic', 'email', '22010104830000', 0, 'remark', 1709529514832, 1709529514832);

INSERT INTO AccountReceivingBank VALUES
(200000000001, 200000000000, 'BANK', '12427950358303', 'SHI ZHENLING', 'HABIB BANK LIMITED', 'PK60HABB0012427950358303', '0001000000127', '35148465@qq.com', '03190504096', 0, 'remark', 1709529514832, 1709529514832);
INSERT INTO AccountReceivingBank VALUES
(200000000002, 200000000000, 'WALLET', '03330361777', 'Farooq Ahmed', 'JAZZCASH', 'PK32JCMA2510923330361777', '42000-8022915-1', 'farooqsangi@gmail.com', '03330361777', 0, 'remark', 1709529514832, 1709529514832);
INSERT INTO AccountReceivingBank VALUES
(200000000003, 200000000000, 'WALLET', '03330361777', 'Farooq Ahmed', 'EASYPAISA', 'PK58TMFB0000000077275216 ', '42000-8022915-1', 'farooqsangi@gmail.com', '03330361777', 0, 'remark', 1709529514832, 1709529514832);


commit;


-- 账户邀请奖励表
drop table if exists AccountInviteRewards;
create table AccountInviteRewards
(
    id                      bigint(20)               not null comment 'ID' primary key,
    registerAccountId       bigint(20)               not null comment '注册账户ID',
    registerEmail           varchar(32)              not null comment '注册账户邮箱',
    inviteAccountId         bigint(20)               not null comment '邀请账户ID',
    inviteEmail             varchar(32)              not null comment '邀请账户邮箱',
    rewardsAccountId        bigint(20)               not null comment '奖励账户ID',
    rewardsEmail            varchar(32)              not null comment '奖励账户邮箱',
    rewardsSubAccountId     bigint(20)               not null comment '奖励账户对应直接下级账户ID(带来收益账户)',
    rewardsSubEmail         varchar(32)              not null comment '奖励账户对应直接下级邮箱(带来收益账户)',
    rewardsRate             varchar(16)              not null comment '奖励比例',
    rewardsBalance          decimal(22, 8)           not null comment '奖励金额',
    rewardsLevel            int                      not null comment '奖励级别(1、2、3)',
    rewardsTag              varchar(128)             not null comment '奖励标签(账户Id逗号隔开)',
    rewardsStatus           int                      not null comment '状态(0未奖励、1已奖励)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateTime              bigint(13)                        comment '更新时间'
) comment '账户邀请奖励表';

