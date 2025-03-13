package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 安全策略对象
 * <p>
 *     通常情况下，只需要传入默认验证码即可;
 *     只有在GA和短信同时需要验证时才需要传入ga和sms
 * </p>
 * <p>File：PolicyModel.java</p>
 * <p>Title: PolicyModel</p>
 * <p>Description: PolicyModel</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "策略对象")
public class PolicyModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String            pwd;
    
    /**
     * GA验证码
     */
    @ApiModelProperty(value = "GA验证码")
    private String            ga;
    
    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码")
    private String            sms;
    
    public PolicyModel(String ga, String sms)
    {
        this.ga = ga;
        this.sms = sms;
    }
}
