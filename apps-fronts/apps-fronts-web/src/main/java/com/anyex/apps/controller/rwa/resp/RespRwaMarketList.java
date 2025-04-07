package com.anyex.apps.controller.rwa.resp;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "RWA市场产品列表")
public class RespRwaMarketList extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品编号
     */
    @NotEmpty(message = "产品编号不可为空")
    @ApiModelProperty(value = "产品编号", position = 4, required = true)
    private String productNo;

    /**
     * 代币名称
     */
    @NotEmpty(message = "代币名称不可为空")
    @ApiModelProperty(value = "代币名称", position = 5, required = true)
    private String tokenName;

    /**
     * 代币Logo
     */
    @NotEmpty(message = "代币Logo不可为空")
    @ApiModelProperty(value = "代币Logo", position = 6, required = true)
    private String tokenLogo;

    /**
     * 代币发行数量
     */
    @NotNull(message = "代币发行数量不可为空")
    @ApiModelProperty(value = "代币发行数量", position = 7, required = true)
    private java.math.BigDecimal tokenIssueNumber;

    /**
     * 募集币种
     */
    @NotEmpty(message = "募集币种不可为空")
    @ApiModelProperty(value = "募集币种", position = 8, required = true)
    private String raiseCurrency;

    /**
     * 募集金额
     */
    @NotNull(message = "募集金额不可为空")
    @ApiModelProperty(value = "募集金额", position = 9, required = true)
    private java.math.BigDecimal raiseAmount;

    /**
     * 资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额
     */
    @NotNull(message = "资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额不可为空")
    @ApiModelProperty(value = "资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额", position = 10, required = true)
    private java.math.BigDecimal assetEndValuation;

    /**
     * 发行天数
     */
    @NotNull(message = "发行天数不可为空")
    @ApiModelProperty(value = "发行天数", position = 11, required = true)
    private Integer issueDays;


    /**申购开始日期*/
    @NotNull(message = "申购开始日期不可为空")
    @ApiModelProperty(value = "申购开始日期", position = 12, required = true)
    private java.util.Date purchaseStartDate;

    /**申购结束日期*/
    @NotNull(message = "申购结束日期不可为空")
    @ApiModelProperty(value = "申购结束日期", position = 13, required = true)
    private java.util.Date purchaseEndDate;

    /**
     * 状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)
     */
    @NotEmpty(message = "状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)不可为空")
    @ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)", position = 26, required = true)
    private String state;

}
