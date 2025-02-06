package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "app设备分页请求对象")
public class ReqSysAppDevicePagination extends Pagination
{
    /**设备类型(ios、android、client)*/
    @ApiModelProperty(value = "设备类型(ios、android、client)")
    private String deviceType;

    /**设备名字*/
    @ApiModelProperty(value = "设备名字")
    private String deviceName;

    /**设备编码*/
    @ApiModelProperty(value = "设备编码")
    private String deviceNumber;

    /**IP地址*/
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;

    /**版本号*/
    @ApiModelProperty(value = "版本号")
    private String appVersion;

    /**build版本号*/
    @ApiModelProperty(value = "build版本号")
    private String buildVersion;
}