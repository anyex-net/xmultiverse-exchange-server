package com.anyex.globalpay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.anyex.globalpay")
public class GlobalPayConfig {

    public String rootUrl = "";

    public String rootUrlUnifiedorder = "/open-api/pay";

    public String rootUrlUnifiedorderQuery = "/open-api/query";

    public String rootUrlTransferorder = "/open-api/transfer";

    public String rootUrlTransferorderQuery = "/open-api/query";

    public String key = "";

    public String mchno = "";

    public  String appid = "";

    public String payInNotifyUrl = "";

    public String payOutNotifyUrl = "";

}
