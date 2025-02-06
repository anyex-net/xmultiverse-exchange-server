package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品品类请求对象")
public class ReqGoodsSpecParamPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**品类Id*/
    @ApiModelProperty(value = "品类Id")
    private Long spgId;

    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    /**是否为数字参数*/
    @ApiModelProperty(value = "是否为数字参数")
    private Boolean isNumeric;

    /**单位(量词)*/
    @ApiModelProperty(value = "单位(量词)")
    private String unit;

    /**参数值*/
    @ApiModelProperty(value = "参数值")
    private String paramValue;
}