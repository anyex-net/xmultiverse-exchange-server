package com.anyex.apps.controller.system.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户信息分页请求对象")
public class ReqSysUserInfoPagination extends Pagination
{
    /**机构ID*/
    @ApiModelProperty(value = "机构ID")
    private Long              orgId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String            userName;

    /**真实姓名*/
    @ApiModelProperty(value = "真实姓名")
    private String            trueName;

    /**手机号*/
    @ApiModelProperty(value = "手机号")
    private String            phone;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            userDesc;

    /**职称*/
    @ApiModelProperty(value = "职称")
    private String            jobTitle;
}