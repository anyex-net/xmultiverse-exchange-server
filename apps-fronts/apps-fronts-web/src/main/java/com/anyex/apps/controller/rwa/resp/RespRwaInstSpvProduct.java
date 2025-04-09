package com.anyex.apps.controller.rwa.resp;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RespRwaInstSpvProduct {

    /**产品编号*/
    @NotEmpty(message = "产品编号不可为空")
    @ApiModelProperty(value = "产品编号", position = 4, required = true)
    private java.lang.String productNo;

    /**代币名称*/
    @NotEmpty(message = "代币名称不可为空")
    @ApiModelProperty(value = "代币名称", position = 5, required = true)
    private java.lang.String tokenName;

    /**代币Logo*/
    @NotEmpty(message = "代币Logo不可为空")
    @ApiModelProperty(value = "代币Logo", position = 6, required = true)
    private java.lang.String tokenLogo;

    /**代币发行数量*/
    @NotNull(message = "代币发行数量不可为空")
    @ApiModelProperty(value = "代币发行数量", position = 7, required = true)
    private java.math.BigDecimal tokenIssueNumber;

    /**募集币种*/
    @NotEmpty(message = "募集币种不可为空")
    @ApiModelProperty(value = "募集币种", position = 8, required = true)
    private java.lang.String raiseCurrency;

    /**募集金额*/
    @NotNull(message = "募集金额不可为空")
    @ApiModelProperty(value = "募集金额", position = 9, required = true)
    private java.math.BigDecimal raiseAmount;

    /**资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额*/
    @NotNull(message = "资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额不可为空")
    @ApiModelProperty(value = "资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额", position = 10, required = true)
    private java.math.BigDecimal assetEndValuation;

    /**发行天数*/
    @NotNull(message = "发行天数不可为空")
    @ApiModelProperty(value = "发行天数", position = 11, required = true)
    private java.lang.Integer issueDays;

    /**申购开始日期*/
    @NotNull(message = "申购开始日期不可为空")
    @ApiModelProperty(value = "申购开始日期", position = 12, required = true)
    private java.util.Date purchaseStartDate;

    /**申购结束日期*/
    @NotNull(message = "申购结束日期不可为空")
    @ApiModelProperty(value = "申购结束日期", position = 13, required = true)
    private java.util.Date purchaseEndDate;

    /**运营开始日期*/
    @NotNull(message = "运营开始日期不可为空")
    @ApiModelProperty(value = "运营开始日期", position = 14, required = true)
    private java.util.Date operationStarDate;

    /**运营结束日期*/
    @NotNull(message = "运营结束日期不可为空")
    @ApiModelProperty(value = "运营结束日期", position = 15, required = true)
    private java.util.Date operationEndDate;

    /**募集成立条件比例*/
    @NotNull(message = "募集成立条件比例不可为空")
    @ApiModelProperty(value = "募集成立条件比例", position = 16, required = true)
    private java.math.BigDecimal raiseEstablishedRatio;

    /**公司资产名称*/
    @NotEmpty(message = "公司资产名称不可为空")
    @ApiModelProperty(value = "公司资产名称", position = 17, required = true)
    private java.lang.String companyAssetName;

    /**公司资产描述*/
    @NotEmpty(message = "公司资产描述不可为空")
    @ApiModelProperty(value = "公司资产描述", position = 18, required = true)
    private java.lang.String companyAssetDesc;

    /**公司募集用途*/
    @NotEmpty(message = "公司募集用途不可为空")
    @ApiModelProperty(value = "公司募集用途", position = 19, required = true)
    private java.lang.String companyRaiseUse;

    /**分红比例*/
    @NotNull(message = "分红比例不可为空")
    @ApiModelProperty(value = "分红比例", position = 20, required = true)
    private java.math.BigDecimal dividendRatio;

    /**分红频率*/
    @NotEmpty(message = "分红频率不可为空")
    @ApiModelProperty(value = "分红频率", position = 21, required = true)
    private java.lang.String dividendFrequency;

    /**分红冻结天数*/
    @NotNull(message = "分红冻结天数不可为空")
    @ApiModelProperty(value = "分红冻结天数", position = 22, required = true)
    private java.lang.Integer dividendFreezeDays;

    /**募集对应保证金比例*/
    @NotNull(message = "募集对应保证金比例不可为空")
    @ApiModelProperty(value = "募集对应保证金比例", position = 23, required = true)
    private java.math.BigDecimal raiseMarginRatio;

    /**募集对应保证金状态(0未缴、1已缴)*/
    @NotNull(message = "募集对应保证金状态(0未缴、1已缴)不可为空")
    @ApiModelProperty(value = "募集对应保证金状态(0未缴、1已缴)", position = 24, required = true)
    private java.lang.Integer raiseMarginState;

    /**已申购总数量*/
    @NotNull(message = "已申购总数量不可为空")
    @ApiModelProperty(value = "已申购总数量", position = 25, required = true)
    private java.math.BigDecimal purchasedSumAmount;

    /**状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)*/
    @NotEmpty(message = "状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)不可为空")
    @ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)", position = 26, required = true)
    private java.lang.String state;

    /**公司名称*/
    @NotEmpty(message = "公司名称不可为空")
    @ApiModelProperty(value = "公司名称", position = 3, required = true)
    private java.lang.String spvCompanyName;

    /**公司类型*/
    @NotEmpty(message = "公司类型不可为空")
    @ApiModelProperty(value = "公司类型", position = 4, required = true)
    private java.lang.String spvCompanyType;

    /**公司行业*/
    @NotEmpty(message = "公司行业不可为空")
    @ApiModelProperty(value = "公司行业", position = 5, required = true)
    private java.lang.String spvCompanyIndustry;

    /**公司注册编号*/
    @NotEmpty(message = "公司注册编号不可为空")
    @ApiModelProperty(value = "公司注册编号", position = 6, required = true)
    private java.lang.String spvCompanyRegistrNo;

    /**公司注册证书图片*/
    @NotEmpty(message = "公司注册证书图片不可为空")
    @ApiModelProperty(value = "公司注册证书图片", position = 7, required = true)
    private java.lang.String spvCompanyRegistrImg;

    /**公司所在国家地区*/
    @NotEmpty(message = "公司所在国家地区不可为空")
    @ApiModelProperty(value = "公司所在国家地区", position = 8, required = true)
    private java.lang.String spvCompanyCountry;

    /**公司联系邮箱*/
    @NotEmpty(message = "公司联系邮箱不可为空")
    @ApiModelProperty(value = "公司联系邮箱", position = 9, required = true)
    private java.lang.String spvCompanyEmail;

    /**公司联系电话*/
    @NotEmpty(message = "公司联系电话不可为空")
    @ApiModelProperty(value = "公司联系电话", position = 10, required = true)
    private java.lang.String spvCompanyMobileNo;

    /**公司地址*/
    @NotEmpty(message = "公司地址不可为空")
    @ApiModelProperty(value = "公司地址", position = 11, required = true)
    private java.lang.String spvCompanyAddress;

    /**公司介绍*/
    @NotEmpty(message = "公司介绍不可为空")
    @ApiModelProperty(value = "公司介绍", position = 12, required = true)
    private java.lang.String spvCompanyDesc;
}
