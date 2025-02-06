package com.anyex.apps.bean;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AliyunSTS
 * <p>File：AliyunSTS.java</p>
 * <p>Title: AliyunSTS</p>
 * <p>Description: AliyunSTS</p>
 * <p>Copyright: Copyright (c) 2019/11/14</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AliyunSTS
{
    @Autowired
    private GlobalProperies properies;
    
    public static final String roleSessionName = "fileupload";
    
    public static final String endpoint        = "sts.aliyuncs.com";
    
    public static final String roleArn         = "acs:ram::1527736558702364:role/fileupload";
    
    /**
     * 通过STS API获取STS AK与SecurityToken
     * <p>需要在阿里RAM管理中添加RAM角色</p>
     * @return
     * @throws BusinessException
     */
    public AssumeRoleResponse getAssumeRoleResponse() throws BusinessException
    {
        AssumeRoleResponse response;
        try
        {
            DefaultProfile.addEndpoint("", "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile("", //
                    properies.getAliyun().getAccessKey(), //
                    properies.getAliyun().getSecretKey()//
            );
            DefaultAcsClient client = new DefaultAcsClient(profile);
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysEndpoint(endpoint);
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setDurationSeconds(1000L);
            request.setRoleSessionName(roleSessionName);
            response = client.getAcsResponse(request);
        }
        catch (ClientException e)
        {
            throw new BusinessException(e.getLocalizedMessage());
        }
        return response;
    }
}
