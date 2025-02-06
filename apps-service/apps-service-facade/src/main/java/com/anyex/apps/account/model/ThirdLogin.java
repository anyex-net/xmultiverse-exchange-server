package com.anyex.apps.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 第三方登录参数对象
 * <p>File：ThirdLogin.java</p>
 * <p>Title: ThirdLogin</p>
 * <p>Description: ThirdLogin</p>
 * <p>Copyright: Copyright (c) 2019/11/6</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "第三方登录参数对象")
public class ThirdLogin implements Serializable
{
    /**
    * 用户编号
    */
    @NotNull(message = "用户编号不可为空")
    @ApiModelProperty(value = "用户编号类型", required = true)
    private String uid;
    
    /**
     * 用户昵称
     */
    @NotNull(message = "用户昵称不可为空")
    @ApiModelProperty(value = "设备类型", required = true)
    private String name;
    
    /**
     * 用户头像
     */
    @NotNull(message = "用户头像不可为空")
    @ApiModelProperty(value = "用户头像类型", required = true)
    private String iconUrl;
    
    /**平台类型*/
    @NotNull(message = "平台类型不可为空")
    @ApiModelProperty(value = "平台类型", required = true, notes = "QQ,WEIXIN,WEIBO")
    private String type;
    
    /**
     * 设备类型
     */
    @NotNull(message = "设备类型不可为空")
    @ApiModelProperty(value = "设备类型", required = true)
    private String deviceType;
    
    /**
     * 设备名称
     */
    @NotNull(message = "设备名称不可为空")
    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;
    
    /**
     * 设备号
     */
    @NotNull(message = "设备号不可为空")
    @ApiModelProperty(value = "设备号", required = true)
    private String deviceNum;
    
    /**
     * app版本号
     */
    @NotNull(message = "app版本号不可为空")
    @ApiModelProperty(value = "app版本号", required = true)
    private String appVersion;
}
