package com.anyex.apps.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserScanLoginModel
 * <p>File: UserScanLoginModel.java </p>
 * <p>Title: UserScanLoginModel </p>
 * <p>Description: UserScanLoginModel </p>
 * <p>Copyright: Copyright (c) 2018/11/9</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("扫码登录")
public class UserScanLoginModel implements Serializable
{
    private String qrCode;

    @JsonProperty("auth_token")
    protected String  authToken;

    private Long userId;
    
    private String userName;

    private Integer status = 0; // -1重新加载二维码 0APP未扫描  1APP已扫描 2APP确认登录 9WEB登录成功跳转
}
