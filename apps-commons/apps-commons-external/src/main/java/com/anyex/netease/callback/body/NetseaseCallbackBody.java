package com.anyex.netease.callback.body;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class NetseaseCallbackBody {
    private Integer errCode;
    private Integer responseCode;

    @JSONField(name = "modifyResponse")
    JSONObject modifyResponse;

    @JSONField(name = "callbackExt")
    private String callbackExt;

    public NetseaseCallbackBody(Integer errCode, Integer responseCode) {
        this.errCode = errCode;
        this.responseCode = responseCode;
    }

    public NetseaseCallbackBody(Integer errCode, Integer responseCode, JSONObject modifyResponse, String callbackExt) {
        this.errCode = errCode;
        this.responseCode = responseCode;
        modifyResponse = modifyResponse;
        this.callbackExt = callbackExt;
    }

    public NetseaseCallbackBody() {
        // 默认构造函数
    }
}
