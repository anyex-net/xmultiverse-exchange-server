package com.anyex.netease.api;

import com.anyex.apps.exception.BusinessException;
import com.anyex.netease.config.NeteaseConfig;

public class NeteaseAuthApi{
    static
    {

    }

    /**
     * 获取动态token
     * @param accid
     * @return
     * @throws BusinessException
     */
    public static String getAuthToken(String accid) throws BusinessException {
        return NeteaseConfig.getAuthToken(accid);
    }

}
