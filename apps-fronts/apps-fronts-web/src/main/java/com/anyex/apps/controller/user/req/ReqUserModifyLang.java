package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户修改语言 请求对象
 * <p>File：ReqUserModifyLang.java</p>
 * <p>Title: ReqUserModifyLang</p>
 * <p>Description: ReqUserModifyLang</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserModifyLang implements Serializable
{
    /**
     * 语言
     */
    @NotEmpty(message = "语言不可为空")
    @ApiModelProperty(value = "语言(en_US、zh_CN、zh_HK)", required = true)
    private String            lang;
}
