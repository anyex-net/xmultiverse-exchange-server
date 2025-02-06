package com.anyex.apps.controller.auth.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 校验邮箱验证码请求对象
 * <p>File：ReqCheckEmail.java</p>
 * <p>Title: ReqCheckEmail</p>
 * <p>Description: ReqCheckEmail</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqCheckEmail implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotNull(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * 邮箱验证码
     */
    @NotNull(message = "邮箱验证码不可为空")
    @ApiModelProperty(value = "邮箱验证码", required = true)
    private String            emailCode;
}
