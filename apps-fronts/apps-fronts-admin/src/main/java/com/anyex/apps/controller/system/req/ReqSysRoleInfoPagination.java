package com.anyex.apps.controller.system.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "角色信息分页请求对象")
public class ReqSysRoleInfoPagination extends Pagination
{
    /**角色编码*/
    @ApiModelProperty(value = "角色编码")
    private String            roleCode;

    /**角色名称*/
    @ApiModelProperty(value = "角色名称")
    private String            roleName;

    /**角色描述*/
    @ApiModelProperty(value = "角色描述")
    private String            roleDest;

    /**是否需要绑定GA 1需要 0不需要*/
    @ApiModelProperty(value = "是否需要绑定GA 1需要 0不需要")
    private Boolean           needGa;
}