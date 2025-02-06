package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消息模版分页请求对象")
public class ReqSysMsgTemplatePagination extends Pagination
{
    /**模版KEY*/
    @ApiModelProperty(value = "模版KEY")
    private String            tplKey;

    /**语言编码（en_US,zh_CN,zh_HK)*/
    @ApiModelProperty(value = "语言编码（en_US,zh_CN,zh_HK)")
    private String            lang;

    /**模版类型(email:邮件、sms:短信)*/
    @ApiModelProperty(value = "模版类型(email:邮件、sms:短信)")
    private String            type;

    /**标题*/
    @ApiModelProperty(value = "消息标题")
    private String            title;

    /**模版内容*/
    @ApiModelProperty(value = "模版内容")
    private String            content;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;
}