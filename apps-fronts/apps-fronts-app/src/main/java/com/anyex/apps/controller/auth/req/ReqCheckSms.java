package com.anyex.apps.controller.auth.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 校验短信验证码请求对象
 * <p>File：ReqCheckSms.java</p>
 * <p>Title: ReqCheckSms</p>
 * <p>Description: ReqCheckSms</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqCheckSms implements Serializable
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

    /**
     * 短信验证码
     */
    @NotEmpty(message = "短信验证码不可为空")
    @ApiModelProperty(value = "短信验证码", required = true)
    private String            smsCode;
}
