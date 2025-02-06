package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "app设备分页请求对象")
public class ReqSysNoticePagination extends Pagination
{
    /**语言类型(zh_CN简体、zh_HK繁体、en_US英文)*/
    @ApiModelProperty(value = "语言类型(zh_CN简体、zh_HK繁体、en_US英文)")
    private String langType;

    /**标题*/
    @ApiModelProperty(value = "标题")
    private String title;

    /**图片*/
    @ApiModelProperty(value = "图片")
    private String imageUrl;

    /**内容*/
    @ApiModelProperty(value = "内容")
    private String content;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**状态(0未发布、1已发布)*/
    @ApiModelProperty(value = "状态(0未发布、1已发布)")
    private Boolean status;
}