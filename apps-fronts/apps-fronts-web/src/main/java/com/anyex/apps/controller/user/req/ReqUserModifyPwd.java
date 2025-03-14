package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户修改密码 请求对象
 * <p>File：ReqUserModifyPwd.java</p>
 * <p>Title: ReqUserModifyPwd</p>
 * <p>Description: ReqUserModifyPwd</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserModifyPwd implements Serializable
{
    /**
     * 旧密码
     */
    @NotEmpty(message = "旧密码不可为空")
    @ApiModelProperty(value = "旧密码", required = true)
    private String            oldPass;

    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不可为空")
    @ApiModelProperty(value = "新密码", required = true)
    private String            newPass;

//    /**
//     * 随机验证码
//     */
//    @NotEmpty(message = "随机验证码不可为空")
//    @ApiModelProperty(value = "随机验证码", required = true)
//    private String            captcha;
}
