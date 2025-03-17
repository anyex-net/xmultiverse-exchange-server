package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户修改稳定币偏好 请求对象
 * <p>File：ReqUserModifyStableCoinPreference.java</p>
 * <p>Title: ReqUserModifyStableCoinPreference</p>
 * <p>Description: ReqUserModifyStableCoinPreference</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserModifyStableCoinPreference implements Serializable
{
    /**
     * 稳定币偏好
     */
    @NotEmpty(message = "稳定币偏好不可为空")
    @ApiModelProperty(value = "稳定币偏好(USDT、USDC)", required = true)
    private String            stableCoinPreference;
}
