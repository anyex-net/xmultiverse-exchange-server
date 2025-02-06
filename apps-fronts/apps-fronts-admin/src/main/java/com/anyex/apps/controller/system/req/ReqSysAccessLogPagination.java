package com.anyex.apps.controller.system.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "访问日志分页请求对象")
public class ReqSysAccessLogPagination extends Pagination
{
    /**用户名称*/
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**模块*/
    @ApiModelProperty(value = "模块")
    private String module;

    /**方法*/
    @ApiModelProperty(value = "方法")
    private String method;

    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String remark;

    /**请求URI*/
    @ApiModelProperty(value = "请求URI")
    private String uri;

    /**ip*/
    @ApiModelProperty(value = "ip")
    private String ip;
}