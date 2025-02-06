package com.anyex.netease.callback.body;

import com.alibaba.fastjson.JSONObject;

public class BodyBuilder {


    public static NetseaseCallbackBody getBody(Integer errCode,Integer responseCode)
    {
        return new NetseaseCallbackBody(errCode,responseCode);
    }


    public static NetseaseCallbackBody getBody(Integer errCode,Integer responseCode,JSONObject ModifyResponseObject,String callback)
    {
        return new NetseaseCallbackBody(errCode,responseCode,ModifyResponseObject,callback);
    }


}
