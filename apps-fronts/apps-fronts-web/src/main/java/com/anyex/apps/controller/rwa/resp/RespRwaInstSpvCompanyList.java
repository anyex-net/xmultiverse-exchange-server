package com.anyex.apps.controller.rwa.resp;

import com.anyex.apps.bean.GenericEntity;
import com.anyex.apps.rwa.entity.RwaInstSpvCompany;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "RWA机构SPV公司返回信息列表")
public class RespRwaInstSpvCompanyList extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**用户ID*/
    @NotNull(message = "用户ID不可为空")
    @ApiModelProperty(value = "用户ID", position = 1, required = true)
    private java.lang.Long userId;

    /**机构SPV发起人ID*/
    @NotNull(message = "机构SPV发起人ID不可为空")
    @ApiModelProperty(value = "机构SPV发起人ID", position = 2, required = true)
    private java.lang.Long instSpvPromoterId;

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

    /**状态(0未审核、1审核通过、2审核拒绝)*/
    @NotEmpty(message = "状态(0未审核、1审核通过、2审核拒绝)不可为空")
    @ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 13, required = true)
    private java.lang.String state;

    /**备注*/
    @ApiModelProperty(value = "备注", position = 14)
    private java.lang.String remark;

    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", position = 15, required = true)
    private java.lang.Long createTime;

    //发行代币数量
    @ApiModelProperty(value = "发行代币数量", position = 16, required = true)
    private java.lang.Integer totalQuantity;
}
