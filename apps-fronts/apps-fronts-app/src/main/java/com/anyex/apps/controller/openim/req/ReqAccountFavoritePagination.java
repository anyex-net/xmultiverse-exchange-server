/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 账户收藏 分页查询
 * <p>File：AccountFavorite.java</p>
 * <p>Title: AccountFavorite</p>
 * <p>Description:AccountFavorite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户收藏分页查询")
public class ReqAccountFavoritePagination extends Pagination
{
    @ApiModelProperty(value = "账户ID", position = 2 )
    private Long accountId;

    @ApiModelProperty(value = "来源", position = 3 )
    private String source;

    @ApiModelProperty(value = "内容", position = 4  )
    private String content;

    @ApiModelProperty(value = "标签:TEXT文本 IMAGE图片视频 LINK链接 FILE文件 MSG聊天记录", position = 5 )
    private String favoriteType;

    @ApiModelProperty(value = "应用类型(IM,社交，商场)", position = 6 )
    private String functionType;

    @ApiModelProperty(value = "备注", position = 6  )
    private String remark;

}
