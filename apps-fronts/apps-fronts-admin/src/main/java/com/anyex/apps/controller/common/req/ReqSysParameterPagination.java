package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "参数配置分页请求对象")
public class ReqSysParameterPagination extends Pagination
{
    /**系统名称*/
    @ApiModelProperty(value = "系统名称")
    private java.lang.String systemName;

    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private java.lang.String parameterName;

    /**参数大类*/
    @ApiModelProperty(value = "参数大类")
    private java.lang.String division;

    /**参数类型*/
    @ApiModelProperty(value = "参数类型")
    private java.lang.String type;

    /**参数值值域*/
    @ApiModelProperty(value = "参数值值域")
    private java.lang.String valueBound;

    /**参数值*/
    @ApiModelProperty(value = "参数值")
    private java.lang.String value;

    /**参数备注*/
    @ApiModelProperty(value = "参数备注")
    private java.lang.String remark;
}