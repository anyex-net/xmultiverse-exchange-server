package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户设置密码 请求对象
 * <p>File：ReqUserSetPwd.java</p>
 * <p>Title: ReqUserSetPwd</p>
 * <p>Description: ReqUserSetPwd</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserSetPwd implements Serializable
{
    /**
     * 密码
     */
    @NotEmpty(message = "密码不可为空")
    @ApiModelProperty(value = "密码", required = true)
    private String            password;

//    /**
//     * 随机验证码
//     */
//    @NotEmpty(message = "随机验证码不可为空")
//    @ApiModelProperty(value = "随机验证码", required = true)
//    private String            captcha;
}
