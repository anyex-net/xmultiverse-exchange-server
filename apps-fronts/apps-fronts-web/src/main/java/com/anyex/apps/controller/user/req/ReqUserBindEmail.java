package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户绑定邮箱请求对象
 * <p>File：ReqUserBindEmail.java</p>
 * <p>Title: ReqUserBindEmail</p>
 * <p>Description: ReqUserBindEmail</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserBindEmail implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotEmpty(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * 邮件验证码
     */
    @NotEmpty(message = "邮件验证码不可为空")
    @ApiModelProperty(value = "邮件验证码", required = true)
    private String            emailCode;
}
