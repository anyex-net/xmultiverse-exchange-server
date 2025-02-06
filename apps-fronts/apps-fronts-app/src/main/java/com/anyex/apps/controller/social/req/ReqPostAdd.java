package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "新增帖子")
public class ReqPostAdd {

    @ApiModelProperty(value = "帖子文本内容")
    private java.lang.String postTextContent;

    /**帖子图片URL*/
    @ApiModelProperty(value = "帖子图片URL")
    private java.lang.String postImageUrl;

    /**0匿名、1公开*/
    @ApiModelProperty(value = "0匿名、1公开")
    private java.lang.Integer openness;

    /**0公开、1仅限好友、2仅限粉丝、3仅限自己*/
    @ApiModelProperty(value = "0公开、1仅限好友、2仅限粉丝、3仅限自己")
    private java.lang.Integer viewer;

    /**位置经度*/
    @ApiModelProperty(value = "位置经度")
    private java.lang.String lng;

    /**位置维度*/
    @ApiModelProperty(value = "位置维度")
    private java.lang.String lat;

    /**城市*/
    @ApiModelProperty(value = "城市")
    private java.lang.String city;

    /**位置维度*/
    @ApiModelProperty(value = "通知用户")
    private java.lang.String[] userIds;
}
