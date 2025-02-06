package com.anyex.openim.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class OpenImResult<T> implements Serializable {
    public boolean isOk() {
        return errCode != null && errCode == 0;
    }

    public void success() {
        this.errCode = 0;
        this.errMsg = "ok";
    }

    public void success(T data) {
        this.errCode = 0;
        this.errMsg = "ok";
        this.data = data;
    }


    private String errDlt;

    private T data;

    private Integer errCode;

    private String errMsg;

}
