package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "修改帖子")
public class ReqPostUpdate {

    @NotNull(message = "帖子ID不能为空")
    @ApiModelProperty(value = "帖子ID",required = true)
    private Long id;

    @ApiModelProperty(value = "帖子文本内容")
    private String postTextContent;

    /**帖子图片URL*/
    @ApiModelProperty(value = "帖子图片URL")
    private String postImageUrl;

    /**0匿名、1公开*/
    @ApiModelProperty(value = "0匿名、1公开")
    private Integer openness;

    /**0公开、1仅限好友、2仅限粉丝、3仅限自己*/
    @ApiModelProperty(value = "0公开、1仅限好友、2仅限粉丝、3仅限自己")
    private Integer viewer;

   /* *//**位置经度*//*
    @ApiModelProperty(value = "位置经度")
    private String lng;

    *//**位置维度*//*
    @ApiModelProperty(value = "位置维度")
    private String lat;

    *//**城市*//*
    @ApiModelProperty(value = "城市")
    private String city;*/
}
