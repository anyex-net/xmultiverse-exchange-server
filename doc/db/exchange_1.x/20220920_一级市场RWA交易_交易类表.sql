--RWA认证机构SPV发起人
drop table if exists RwaCertInstSpvPromoter;
create table RwaCertInstSpvPromoter
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    --发起人公司信息
    promoterCoName          varchar(64)              not null comment '发起人公司名称',
    promoterCoType          varchar(32)              not null comment '发起人公司类型',
    promoterCoRegistrNo     varchar(64)              not null comment '发起人公司注册编号',
    promoterCoRegistrImg    varchar(64)              not null comment '发起人公司注册证书图片',
    promoterCoCountry       varchar(64)              not null comment '发起人公司所在国家地区',
    promoterCoEmail         varchar(32)              not null comment '发起人公司联系邮箱',
    promoterCoMobileNo      varchar(32)              not null comment '发起人公司联系电话',
    --代理人信息
    agentName               varchar(32)              not null comment '代理人姓名',
    agentRegion             varchar(32)              not null comment '代理人国家地区',
    agentPassportType       varchar(16)              not null comment '代理人证件类型',
    agentPassportNo         varchar(64)              not null comment '代理人证件号码',
    agentPassportImg1       varchar(64)              not null comment '代理人证件照片1',
    agentPassportImg2       varchar(64)                       comment '代理人证件照片2',
    agentPassportImg3       varchar(64)                       comment '代理人证件照片3',
    agentAuthorizationFile  varchar(64)              not null comment '代理人授权文件',
    agentEmail              varchar(32)              not null comment '代理人联系邮箱',
    agentMobileNo           varchar(32)              not null comment '代理人联系电话',
    --
    state                   varchar(16)              not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    checkBy                 varchar(32)                       comment '复核人',
    checkTime               bigint(13)                        comment '复核时间',
    constraint index_RwaCertInstSpvPromoter unique (userId)
) comment 'RWA认证机构SPV发起人';

--RWA认证机构投资者
drop table if exists RwaCertInstInvestor;
create table RwaCertInstInvestor
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    --代理人信息
    agentName               varchar(32)              not null comment '代理人姓名',
    agentRegion             varchar(32)              not null comment '代理人国家地区',
    agentPassportType       varchar(16)              not null comment '代理人证件类型',
    agentPassportNo         varchar(64)              not null comment '代理人证件号码',
    agentPassportImg1       varchar(64)              not null comment '代理人证件照片1',
    agentPassportImg2       varchar(64)                       comment '代理人证件照片2',
    agentPassportImg3       varchar(64)                       comment '代理人证件照片3',
    agentEmail              varchar(32)              not null comment '代理人联系邮箱',
    agentMobileNo           varchar(32)              not null comment '代理人联系电话',
    agentAuthorizationFile  varchar(64)              not null comment '代理人授权文件',
    --公司信息
    companyName             varchar(64)              not null comment '公司名称',
    companyType             varchar(32)              not null comment '公司类型',
    companyBusinessType     varchar(32)              not null comment '公司业务类型',
    companyRegistrNo        varchar(64)              not null comment '公司注册编号',
    companyRegistrImg       varchar(64)              not null comment '公司注册证书图片',
    companyCountry          varchar(64)              not null comment '公司所在国家地区',
    companyRegistrAddress   varchar(128)             not null comment '公司注册地址',
    companyFoundedDate      date                     not null comment '公司成立日期',
    companyLicenseNumber    varchar(64)              not null comment '公司持牌编号',
    companyRegulator        varchar(64)              not null comment '公司监管机构',
    companyAmlCertificate   varchar(64)              not null comment '公司AML反洗钱证明',
    --
    state                   varchar(16)              not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    checkBy                 varchar(32)                       comment '复核人',
    checkTime               bigint(13)                        comment '复核时间',
    constraint index_RwaCertInstInvestor unique (userId)
) comment 'RWA认证机构投资者';

--RWA账户余额
drop table if exists RwaBalances;
create table RwaBalances
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    instSpvProductId  bigint(20)                   null comment '机构SPV产品ID',
    currency          varchar(8)               not null comment '币种(BTC、ETH、USDT)',
    balance           decimal(20, 8) default 0 not null comment '余额',
    frozenBal         decimal(20, 8) default 0 not null comment '冻结(不可用)',
    availBal          decimal(20, 8) default 0 not null comment '可用余额',
    remark            varchar(16)                       comment '备注',
    updateTime        bigint(13)                        comment '更新时间',
    constraint index_RwaBalances unique (userId, currency)
) comment 'RWA账户余额';

--RWA账户交易历史
--包含业务：转入transferIn、转出transferOut、冻结forzen、解冻unforzen、申购purchase、赎回redemption、分红dividend
drop table if exists RwaBalancesTransHistory;
create table RwaBalancesTransHistory
(
    id                bigint(20)               not null comment '主键' primary key,
    userId            bigint(20)               not null comment '用户ID',
    currency          varchar(32)              not null comment '币种(BTC、ETH、USDT)',
    type              varchar(32)              not null comment '类型(转入、转出、冻结、解冻、申购、分红)',
    beforeBal         decimal(22, 8) default 0 not null comment '前余额',
    changeAmt         decimal(22, 8) default 0 not null comment '发生数量',
    afterBal          decimal(22, 8) default 0 not null comment '后余额',
    businessId        varchar(64)                       comment '原业务ID',
    fromAcct          varchar(64)                       comment '转出账户',
    toAcct            varchar(64)                       comment '转入账户',
    state             varchar(16)              not null comment '状态(成功success、处理中pending、失败failed)',
    transDesc         varchar(128)             not null comment '交易描述',
    remark            varchar(64)                       comment '备注',
    createTime        bigint(13)               not null comment '创建时间'
) comment 'RWA账户交易历史';

--RWA机构SPV公司
drop table if exists RwaInstSpvCompany;
create table RwaInstSpvCompany
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instSpvPromoterId       bigint(20)               not null comment '机构SPV发起人ID',
    --公司信息
    spvCompanyName          varchar(64)              not null comment '公司名称',
    spvCompanyType          varchar(32)              not null comment '公司类型',
    spvCompanyIndustry      varchar(32)              not null comment '公司行业',
    spvCompanyRegistrNo     varchar(64)              not null comment '公司注册编号',
    spvCompanyRegistrImg    varchar(64)              not null comment '公司注册证书图片',
    spvCompanyCountry       varchar(64)              not null comment '公司所在国家地区',
    spvCompanyEmail         varchar(32)              not null comment '公司联系邮箱',
    spvCompanyMobileNo      varchar(32)              not null comment '公司联系电话',
    spvCompanyAddress       varchar(128)             not null comment '公司地址',
    spvCompanyDesc          varchar(256)             not null comment '公司介绍',
    --
    state                   varchar(16)              not null comment '状态(0未审核、1审核通过、2审核拒绝)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    checkBy                 varchar(32)                       comment '复核人',
    checkTime               bigint(13)                        comment '复核时间'
) comment 'RWA机构SPV公司';

--RWA机构SPV产品
drop table if exists RwaInstSpvProduct;
create table RwaInstSpvProduct
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instSpvPromoterId       bigint(20)               not null comment '机构SPV发起人ID',
    instSpvCompanyId        bigint(20)               not null comment '机构SPV公司ID',
    --发行信息
    productNo               varchar(64)              not null comment '产品编号',
    productName             varchar(128)             not null comment '产品名称',
    tokenName               varchar(32)              not null comment '代币名称',
    tokenLogo               varchar(64)              not null comment '代币Logo',
    tokenIssueNumber        decimal(20, 8)           not null comment '代币发行数量',
    raiseCurrency           varchar(32)              not null comment '募集币种',
    raiseAmount             decimal(20, 8)           not null comment '募集金额',
    assetEndValuation       decimal(20, 8)           not null comment '资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额',
    issueDays               int                      not null comment '发行天数',
    purchaseStartDate       date                     not null comment '申购开始日期',
    purchaseEndDate         date                     not null comment '申购结束日期',
    operationStarDate       date                     not null comment '运营开始日期',
    operationEndDate        date                     not null comment '运营结束日期',
    raiseEstablishedRatio   decimal(8, 4)            not null comment '募集成立条件比例',
    --收入分成公司信息
    companyAssetName        varchar(64)              not null comment '公司资产名称',
    companyAssetDesc        varchar(128)             not null comment '公司资产描述',
    companyRaiseUse         varchar(128)             not null comment '公司募集用途',
    --合约信息
    tokenContractAddress    varchar(128)                      comment '代币合约地址',
    shareContractAddress    varchar(128)                      comment '分润合约地址',
    dividendRatio           decimal(8, 4)            not null comment '分红比例',
    dividendFrequency       varchar(16)              not null comment '分红频率',
    dividendDate            date                     not null comment '分红周期',
    dividendFreezeDays      int                      not null comment '分红冻结天数',
    --保证金信息
    raiseMargin             decimal(20, 8)           not null comment '募集对应保证金',
    raiseMarginState        int                      not null comment '募集对应保证金状态(0未缴、1已缴)',
    --已申购信息
    purchasedSumAmount      decimal(20, 8)           not null comment '已申购总数量',
    --
    state                   varchar(16)              not null comment '状态(-1提交未缴保证金 0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)',
    isActive                int            default 1 not null comment '活动状态(0已下架，1已上架)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间',
    checkBy                 varchar(32)                       comment '复核人',
    checkTime               bigint(13)                        comment '复核时间'
) comment 'RWA机构SPV产品';

--RWA机构SPV产品申购记录
drop table if exists RwaInstSpvProductPurchase;
create table RwaInstSpvProductPurchase
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instInvestorId          bigint(20)                        comment '机构投资者ID',
    instSpvProductId        bigint(20)               not null comment '机构SPV产品ID',
    purchaseCurrency        varchar(32)              not null comment '申购币种',
    purchasePrice           decimal(20, 8)           not null comment '申购价格',
    purchaseAmount          decimal(20, 8)           not null comment '申购数量',
    state                   varchar(16)              not null comment '状态(成功success、处理中pending、失败failed)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品申购记录';

--RWA机构SPV产品资产信息
drop table if exists RwaInstSpvProductAsset;
create table RwaInstSpvProductAsset
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instInvestorId          bigint(20)                        comment '机构投资者ID',
    instSpvProductId        bigint(20)               not null comment '机构SPV产品ID',
    currency                varchar(32)              not null comment '申请解冻币种',
    productAmount           decimal(20, 8)           not null comment '发行人持有量',
    investorAmount          decimal(20, 8)           not null comment '投资人持有量',
    totalAmount             decimal(20, 8)           not null comment '总融资',
    amount                  decimal(20, 8)           not null comment '已解冻',
    lastAmount              decimal(20, 8)           not null comment '申请解冻',
    state                   int                      not null comment '状态(0审核中、1审核通过、2已驳回)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品资产信息';

--RWA机构SPV产品分红记录
drop table if exists RwaInstSpvProductDividend;
create table RwaInstSpvProductDividend
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instInvestorId          bigint(20)                        comment '机构投资者ID',
    instSpvProductId        bigint(20)               not null comment '机构SPV产品ID',
    dividendStartDate       bigint(13)               not null comment '分红开始时间',
    dividendEndDate         bigint(13)               not null comment '分红结束时间',
    dividendCurrency        varchar(32)              not null comment '分红币种',
    dividendAmount          decimal(20, 8)           not null comment '分红金额',
    state                   varchar(16)              not null comment '状态(成功success、处理中pending、失败failed)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品分红记录';

--RWA机构SPV产品赎回记录
drop table if exists RwaInstSpvProductRedemption;
create table RwaInstSpvProductRedemption
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instInvestorId          bigint(20)               not null comment '机构投资者ID',
    instSpvProductId        bigint(20)               not null comment '机构SPV产品ID',
    redemptionCurrency      varchar(32)              not null comment '赎回币种',
    redemptionPrice         decimal(20, 8)           not null comment '赎回价格',
    redemptionAmount        decimal(20, 8)           not null comment '赎回数量',
    state                   varchar(16)              not null comment '状态(成功success、处理中pending、失败failed)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品赎回记录';

--RWA机构SPV产品公告
drop table if exists RwaInstSpvProductNotice;
create table RwaInstSpvProductNotice
(
    id                      bigint(20)               not null comment 'ID' primary key,
    userId                  bigint(20)               not null comment '用户ID',
    instInvestorId          bigint(20)                        comment '机构投资者ID',
    instSpvProductId        bigint(20)               not null comment '机构SPV产品ID',
    noticeTitle             varchar(128)             not null comment '公告标题',
    noticeContent           varchar(512)             not null comment '公告内容',
    checkOpinion            varchar(256)                      comment '审核意见',
    state                   int                      not null comment '状态(0审核中、1已驳回、2待发布、3已发布)',
    remark                  varchar(64)                       comment '备注',
    createTime              bigint(13)               not null comment '创建时间',
    updateBy                varchar(32)                       comment '更新人',
    updateTime              bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品公告';

--RWA机构SPV产品投资者分红快照
drop table if exists RwaInstSpvProductDividendSnapshot;
create table RwaInstSpvProductDividendSnapshot
(
    id                          bigint(20)               not null comment 'ID' primary key,
    userId                      bigint(20)               not null comment '用户ID',
    instInvestorId              bigint(20)                        comment '机构投资者ID',
    instSpvProductId            bigint(20)               not null comment '机构SPV产品ID',
    instSpvProductDividendNo    varchar(32)              not null comment '机构SPV产品分红记录编号',
    walletAddress               varchar(128)             not null comment '链上钱包地址',
    chainHoldAmount             decimal(20, 8)           not null comment '链上持币数量',
    chainDividendAmount         decimal(20, 8)           not null comment '链上分成金额',
    holdAmount                  decimal(20, 8)           not null comment '平台分成持币数量',
    dividendAmount              decimal(20, 8)           not null comment '平台分成金额',
    remark                      varchar(64)                       comment '备注',
    createTime                  bigint(13)               not null comment '创建时间',
    updateBy                    varchar(32)                       comment '更新人',
    updateTime                  bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品投资者分红快照';

--RWA机构SPV产品实际收入
drop table if exists RwaInstSpvProductRealizedIncome;
create table RwaInstSpvProductRealizedIncome
(
    id                          bigint(20)               not null comment 'ID' primary key,
    userId                      bigint(20)               not null comment '用户ID',
    instSpvProductId            bigint(20)               not null comment '机构SPV产品ID',
    incomeDistributionDate      date                     not null comment '收入分成日期',
    incomeAmount                decimal(20, 8)           not null comment '收入金额',
    incomeCurrency              varchar(32)              not null comment '收入币种',
    remark                      varchar(64)                       comment '备注',
    createTime                  bigint(13)               not null comment '创建时间',
    updateBy                    varchar(32)                       comment '更新人',
    updateTime                  bigint(13)                        comment '更新时间'
) comment 'RWA机构SPV产品实际收入';