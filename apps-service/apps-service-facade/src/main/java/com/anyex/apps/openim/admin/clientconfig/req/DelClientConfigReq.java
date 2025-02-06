package com.anyex.apps.openim.admin.clientconfig.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class DelClientConfigReq {
    @NotEmpty
    private List<String> keys;
}
