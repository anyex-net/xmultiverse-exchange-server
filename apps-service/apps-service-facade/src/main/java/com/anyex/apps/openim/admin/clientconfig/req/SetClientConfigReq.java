package com.anyex.apps.openim.admin.clientconfig.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class SetClientConfigReq {
    @NotNull
    private Map<String, String> config;
}
