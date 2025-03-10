package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 随机验证码请求对象
 * <p>File：ReqCaptcha.java</p>
 * <p>Title: ReqCaptcha</p>
 * <p>Description: ReqCaptcha</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqCaptcha implements Serializable
{
    /**
     * 场景scene
     */
    @NotNull(message = "场景scene不可为空")
    @ApiModelProperty(value = "场景scene(email_register|email_login|email_forgetpass)", required = true)
    private String            scene;
}
