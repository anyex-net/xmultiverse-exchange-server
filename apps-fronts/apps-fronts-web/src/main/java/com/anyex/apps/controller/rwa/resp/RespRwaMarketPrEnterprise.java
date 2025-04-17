package com.anyex.apps.controller.rwa.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RespRwaMarketPrEnterprise
{

    /**发起人公司名称*/
    @NotEmpty(message = "发起人公司名称不可为空")
    @ApiModelProperty(value = "发起人公司名称", position = 2, required = true)
    private String promoterCoName;

    /**发起人公司类型*/
    @NotEmpty(message = "发起人公司类型不可为空")
    @ApiModelProperty(value = "发起人公司类型", position = 3, required = true)
    private String promoterCoType;

    /**发起人公司注册编号*/
    @NotEmpty(message = "发起人公司注册编号不可为空")
    @ApiModelProperty(value = "发起人公司注册编号", position = 4, required = true)
    private String promoterCoRegistrNo;

//    /**发起人公司注册证书图片*/
//    @NotEmpty(message = "发起人公司注册证书图片不可为空")
//    @ApiModelProperty(value = "发起人公司注册证书图片", position = 5, required = true)
//    private String promoterCoRegistrImg;

    /**发起人公司所在国家地区*/
    @NotEmpty(message = "发起人公司所在国家地区不可为空")
    @ApiModelProperty(value = "发起人公司所在国家地区", position = 6, required = true)
    private String promoterCoCountry;

    /**发起人公司联系邮箱*/
    @NotEmpty(message = "发起人公司联系邮箱不可为空")
    @ApiModelProperty(value = "发起人公司联系邮箱", position = 7, required = true)
    private String promoterCoEmail;

//    /**发起人公司联系电话*/
//    @NotEmpty(message = "发起人公司联系电话不可为空")
//    @ApiModelProperty(value = "发起人公司联系电话", position = 8, required = true)
//    private String promoterCoMobileNo;


    /**公司名称*/
    @NotEmpty(message = "公司名称不可为空")
    @ApiModelProperty(value = "公司名称", position = 3, required = true)
    private String spvCompanyName;

    /**公司类型*/
    @NotEmpty(message = "公司类型不可为空")
    @ApiModelProperty(value = "公司类型", position = 4, required = true)
    private String spvCompanyType;

    /**公司行业*/
    @NotEmpty(message = "公司行业不可为空")
    @ApiModelProperty(value = "公司行业", position = 5, required = true)
    private String spvCompanyIndustry;

    /**公司注册编号*/
    @NotEmpty(message = "公司注册编号不可为空")
    @ApiModelProperty(value = "公司注册编号", position = 6, required = true)
    private String spvCompanyRegistrNo;

//    /**公司注册证书图片*/
//    @NotEmpty(message = "公司注册证书图片不可为空")
//    @ApiModelProperty(value = "公司注册证书图片", position = 7, required = true)
//    private String spvCompanyRegistrImg;

    /**公司所在国家地区*/
    @NotEmpty(message = "公司所在国家地区不可为空")
    @ApiModelProperty(value = "公司所在国家地区", position = 8, required = true)
    private String spvCompanyCountry;

    /**公司联系邮箱*/
    @NotEmpty(message = "公司联系邮箱不可为空")
    @ApiModelProperty(value = "公司联系邮箱", position = 9, required = true)
    private String spvCompanyEmail;

//    /**公司联系电话*/
//    @NotEmpty(message = "公司联系电话不可为空")
//    @ApiModelProperty(value = "公司联系电话", position = 10, required = true)
//    private String spvCompanyMobileNo;

    /**公司地址*/
    @NotEmpty(message = "公司地址不可为空")
    @ApiModelProperty(value = "公司地址", position = 11, required = true)
    private String spvCompanyAddress;

    /**公司介绍*/
    @NotEmpty(message = "公司介绍不可为空")
    @ApiModelProperty(value = "公司介绍", position = 12, required = true)
    private String spvCompanyDesc;
}
