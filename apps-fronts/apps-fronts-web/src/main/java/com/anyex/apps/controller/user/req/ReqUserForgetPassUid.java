package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户忘记密码用户Id请求对象
 * <p>File：ReqUserForgetPassUid.java</p>
 * <p>Title: ReqUserForgetPassUid</p>
 * <p>Description: ReqUserForgetPassUid</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserForgetPassUid implements Serializable
{
    /**
     * 用户Id
     */
    @NotNull(message = "用户Id不可为空")
    @ApiModelProperty(value = "用户Id", required = true)
    private Long            userId;
}
