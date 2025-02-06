-- APP下载信息表
DROP TABLE IF EXISTS AppDownloadInfo;
CREATE TABLE AppDownloadInfo (
  id                        bigint(20)          not null comment '主键ID' primary key,
  ip                        varchar(64)         not null comment 'IP地址',
  sourceUrl                 varchar(128)        not null comment '来源Url',
  remark                    varchar(64)                  comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment 'APP下载信息表';

INSERT INTO AppDownloadInfo VALUES
(200000000000,  '192.168.1.1', 'https://app.luckyboxpk.com/sharelink/download', 'remark', 1709529514832, 1709529514832);

commit;


-- APP激活信息表
DROP TABLE IF EXISTS AppActivationInfo;
CREATE TABLE AppActivationInfo (
  id                        bigint(20)          not null comment '主键ID' primary key,
  ip                        varchar(64)         not null comment 'IP地址',
  deviceId                  varchar(64)         not null comment '设备编号',
  appVersion                varchar(16)         not null comment 'app版本',
  source                    varchar(32)                  comment '来源',
  remark                    varchar(64)                  comment '备注',
  createTime                bigint(13)          not null comment '创建时间',
  updateTime                bigint(13)                   comment '更新时间'
) comment 'APP激活信息表';

INSERT INTO AppActivationInfo VALUES
(200000000000,  '192.168.1.1', '1234567890987654321', '1.0.0', 'luckybox', 'remark', 1709529514832, 1709529514832);

commit;
