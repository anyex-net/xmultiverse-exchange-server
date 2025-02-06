/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 社交活动 实体对象
 * <p>File：SnsActivity.java</p>
 * <p>Title: SnsActivity</p>
 * <p>Description:SnsActivity</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交活动")
public class SnsActivity extends GenericEntity
{
    @ApiModelProperty(value = "标题", position = 2 , required = true )
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "内容", position = 3 , required = true )
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "主图地址", position = 4 , required = true )
    @NotBlank(message = "主图地址不能为空")
    private String imgUrl;

    @ApiModelProperty(value = "活动标签", position = 5 , required = true )
    @NotBlank(message = "活动标签不能为空")
    private String activityTag;

    @ApiModelProperty(value = "链接地址", position = 6 )
    private String openUrl;

    @ApiModelProperty(value = "备注", position = 7 )
    private String remark;

    @ApiModelProperty(value = "状态 0未发布，1已发布", position = 8 , required = true )
    @NotNull(message = "状态 0未发布，1已发布不能为空")
    private Integer status;

    @ApiModelProperty(value = "创建时间", position = 9 , required = true )
    @NotNull(message = "创建时间不能为空")
    private Long createTime;

    @ApiModelProperty(value = "更新时间", position = 10 )
    private Long updateTime;

}
