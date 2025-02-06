package com.anyex.apps.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Aliyun参数对象 Introduce
 * <p>File：AliyunModel.java</p>
 * <p>Title: AliyunModel</p>
 * <p>Description: AliyunModel</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "AFS参数对象")
public class AliyunModel
{
    @ApiModelProperty(value = "会话ID")
    private String csessionid;
    
    @ApiModelProperty(value = "口令")
    private String token;
    
    @ApiModelProperty(value = "密文")
    private String scene;
    
    @ApiModelProperty(value = "会话组")
    private String sig;
}
