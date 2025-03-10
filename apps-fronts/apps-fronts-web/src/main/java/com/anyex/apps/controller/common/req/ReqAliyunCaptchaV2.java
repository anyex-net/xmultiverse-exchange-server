package com.anyex.apps.controller.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * ReqAliyunCaptchaV2请求对象
 * <p>File：ReqAliyunCaptchaV2.java</p>
 * <p>Title: ReqAliyunCaptchaV2</p>
 * <p>Description: ReqAliyunCaptchaV2</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAliyunCaptchaV2 implements Serializable
{
    /**
     * captchaVerifyParam
     */
    @NotEmpty(message = "captchaVerifyParam")
    @ApiModelProperty(value = "captchaVerifyParam", required = true)
    private String            captchaVerifyParam;
}
