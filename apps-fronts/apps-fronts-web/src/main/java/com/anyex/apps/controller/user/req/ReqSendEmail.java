package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 发送邮箱验证码请求对象
 * <p>File：ReqSendEmail.java</p>
 * <p>Title: ReqSendEmail</p>
 * <p>Description: ReqSendEmail</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqSendEmail implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotEmpty(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * google recaptchaToken
     */
    @NotEmpty(message = "google recaptchaToken is NotEmpty")
    @ApiModelProperty(value = "google recaptchaToken", required = true)
    private String            googleRecaptchaToken;
}
