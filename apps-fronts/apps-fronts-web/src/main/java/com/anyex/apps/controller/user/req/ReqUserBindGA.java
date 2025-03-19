package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户绑定GA请求对象
 * <p>File：ReqUserBindGA.java</p>
 * <p>Title: ReqUserBindGA</p>
 * <p>Description: ReqUserBindGA</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserBindGA implements Serializable
{
    /**
     * GA私钥
     */
    @NotEmpty(message = "GA私钥不可为空")
    @ApiModelProperty(value = "GA私钥", required = true)
    private String            gaSecretKey;

    /**
     * GA验证码
     */
    @NotEmpty(message = "GA验证码不可为空")
    @ApiModelProperty(value = "GA验证码", required = true)
    private String            gaCode;
}
