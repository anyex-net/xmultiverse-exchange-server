package com.anyex.apps.controller.common.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消息记录分页请求对象")
public class ReqSysMsgRecordPagination extends Pagination
{
    /**消息类型*/
    @ApiModelProperty(value = "消息类型(email:邮件、sms:短信)")
    private String            type;

    /**手机号*/
    @ApiModelProperty(value = "发送对象")
    private String            object;

    /**内容*/
    @ApiModelProperty(value = "内容")
    private String            content;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;

    /**发送状态（0：成功，1：失败）*/
    @ApiModelProperty(value = "发送状态（0：成功，1：失败）不可为空")
    private Boolean           status;
}