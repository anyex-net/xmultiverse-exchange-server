package com.anyex.exchange.okex.enums;

/**
 * Algorithm Name
 *
 * @author Tony Tian
 * @date Created in 2018/1/31 13:00
 */
public enum AlgorithmEnum {

    HMAC_SHA256("HmacSHA256"),
    HMAC_SHA384("HmacSHA384"),
    MD5("MD5"),;

    private String algorithm;

    AlgorithmEnum(String algorithm) {
        this.algorithm = algorithm;
    }

    public String algorithm() {
        return algorithm;
    }
}
