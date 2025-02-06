package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "区域代码分页请求对象")
public class ReqSysRegionPagination extends Pagination
{
    /**国际简码*/
    @ApiModelProperty(value = "国际简码")
    private String            sCode;

    /**国际代码*/
    @ApiModelProperty(value = "国际代码")
    private String            lCode;

    /**英文名称*/
    @ApiModelProperty(value = "英文名称")
    private String            enName;

    /**中文名称*/
    @ApiModelProperty(value = "中文名称")
    private String            cnName;

    /**区域*/
    @ApiModelProperty(value = "区域")
    private String            area;

    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;
}