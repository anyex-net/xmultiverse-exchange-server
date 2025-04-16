package com.anyex.apps.controller.rwa.resp;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "RWA市场产品资产信息列表")
public class RespRwaInstSpvProductAsset {

    /**代币名称*/
    @NotEmpty(message = "代币名称不可为空")
    @ApiModelProperty(value = "代币名称", position = 5, required = true)
    private java.lang.String tokenName;

    /**代币发行数量*/
    @NotNull(message = "代币发行数量不可为空")
    @ApiModelProperty(value = "代币发行数量", position = 7, required = true)
    private java.math.BigDecimal tokenIssueNumber;

    /**发行人持有量*/
    @NotNull(message = "发行人持有量不可为空")
    @ApiModelProperty(value = "发行人持有量", position = 4, required = true)
    private java.math.BigDecimal productAmount;

    /**投资人持有量*/
    @NotNull(message = "投资人持有量不可为空")
    @ApiModelProperty(value = "投资人持有量", position = 5, required = true)
    private java.math.BigDecimal investorAmount;

    /**总融资*/
    @NotNull(message = "总融资不可为空")
    @ApiModelProperty(value = "总融资", position = 6, required = true)
    private java.math.BigDecimal totalAmount;

    /**已解冻*/
    @NotNull(message = "已解冻不可为空")
    @ApiModelProperty(value = "已解冻", position = 7, required = true)
    private java.math.BigDecimal amount;


}
