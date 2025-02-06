package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "APP版本分页请求对象")
public class ReqSysAppVersionPagination extends Pagination
{
    /**设备类型*/
    @ApiModelProperty(value = "设备类型")
    private String            deviceType;

    /**版本号*/
    @ApiModelProperty(value = "版本号")
    private String            appVersion;

    /**build版本号*/
    @ApiModelProperty(value = "build版本号")
    private String            buildVersion;

    /**是否支持(true、false)*/
    @ApiModelProperty(value = "是否支持(true、false)")
    private Boolean           canSupport;

    /**审核状态(true、false)*/
    @ApiModelProperty(value = "审核状态(true、false)")
    private Boolean           checkStatus;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String            remark;
}