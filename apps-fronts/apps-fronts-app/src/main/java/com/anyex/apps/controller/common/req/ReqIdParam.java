package com.anyex.apps.controller.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Id请求对象
 * <p>File：ReqIdParam.java</p>
 * <p>Title: ReqIdParam</p>
 * <p>Description:ReqIdParam</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqIdParam implements Serializable
{
    @NotNull(message = "业务记录唯一Id不允许为空")
    @ApiModelProperty(value = "业务记录唯一Id", required = true)
    private Long id;
}