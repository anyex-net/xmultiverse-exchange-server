package com.anyex.apps.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * EmailModel 介绍
 * <p>File：EmailModel.java </p>
 * <p>Title: EmailModel </p>
 * <p>Description:EmailModel </p>
 * <p>Copyright: Copyright (c) May 2017/12/28 </p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel implements Serializable
{
    private static final long serialVersionUID = -610255992906369700L;
    
    private String            email;
    
    private String            invitCode;
    
    private String            randomKey;
    
    private String            requestIp;
}
