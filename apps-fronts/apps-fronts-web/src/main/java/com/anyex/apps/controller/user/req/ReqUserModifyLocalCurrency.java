package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户修改本地货币 请求对象
 * <p>File：ReqUserModifyLocalCurrency.java</p>
 * <p>Title: ReqUserModifyLocalCurrency</p>
 * <p>Description: ReqUserModifyLocalCurrency</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserModifyLocalCurrency implements Serializable
{
    /**
     * 本地货币
     */
    @NotEmpty(message = "本地货币不可为空")
    @ApiModelProperty(value = "本地货币货币(USD、CNY)", required = true)
    private String            localCurrency;
}
