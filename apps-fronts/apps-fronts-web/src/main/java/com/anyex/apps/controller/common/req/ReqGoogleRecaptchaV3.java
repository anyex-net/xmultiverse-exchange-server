package com.anyex.apps.controller.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 谷歌随机验证码GoogleRecaptcha请求对象 进行GoogleRecaptchaVerify
 * <p>File：ReqGoogleRecaptchaV3.java</p>
 * <p>Title: ReqGoogleRecaptchaV3</p>
 * <p>Description: ReqGoogleRecaptchaV3</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqGoogleRecaptchaV3 implements Serializable
{
    /**
     * recaptchaToken
     */
    @NotEmpty(message = "google recaptchaToken")
    @ApiModelProperty(value = "google recaptchaToken", required = true)
    private String            recaptchaToken;
}
