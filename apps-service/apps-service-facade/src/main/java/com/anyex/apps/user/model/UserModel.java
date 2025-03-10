package com.anyex.apps.user.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * UserModel
 * <p>File: UserModel.java </p>
 * <p>Title: UserModel </p>
 * <p>Description: UserModel </p>
 * <p>Copyright: Copyright (c) 2018/11/9</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("重置密码参数对象")
public class UserModel implements Serializable
{
    private Long   id;
    
    @NotNull
    private String password;
    
    private String emailCode;
    
    private String smsCode;
    
    private String gaCode;
}
