package com.anyex.apps.controller.auth.reqnew;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *  登录参数对象
 * <p>File： ReqAccountToken.java </p>
 * <p>Title:  ReqAccountToken </p>
 * <p>Description: ReqAccountToken </p>
 * <p>Copyright: Copyright (c) 2017/8/2 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAccountTokenNew implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotEmpty(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String            loginPwd;

    /**
     * 随机验证码
     */
    @NotEmpty(message = "随机验证码不可为空")
    @ApiModelProperty(value = "随机验证码", required = true)
    private String            kaptcha;
}
