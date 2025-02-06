package com.anyex.apps.controller.account.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "账户收货地址分页请求对象")
public class ReqAccountAddressPagination extends Pagination
{
    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private java.lang.Long accountId;

    /**收件人姓名*/
    @ApiModelProperty(value = "收件人姓名")
    private java.lang.String name;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private java.lang.String mobile;

    /**邮箱*/
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;

    /**区域*/
    @ApiModelProperty(value = "区域")
    private java.lang.String area;

    /**地址*/
    @ApiModelProperty(value = "地址")
    private java.lang.String address;

    /**地标*/
    @ApiModelProperty(value = "地标")
    private java.lang.String landmark;

    /**是否默认地址(0否、1是)*/
    @ApiModelProperty(value = "是否默认地址(0否、1是)")
    private java.lang.Boolean prime;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}