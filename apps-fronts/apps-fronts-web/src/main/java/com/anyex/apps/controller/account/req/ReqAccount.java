package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 账户请求对象
 * <p>File：ReqAccount.java</p>
 * <p>Title: ReqAccount</p>
 * <p>Description: ReqAccount</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAccount implements Serializable
{
    /**
     * 国家地区
     */
    @NotEmpty(message = "国家地区不可为空")
    @ApiModelProperty(value = "国家地区(默认86)", required = true)
    private String            country;

    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号码不可为空")
    @ApiModelProperty(value = "手机号码", required = true)
    private String            mobile;
}
