package com.anyex.openim.api.user.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class AccountCheckReq {
    @NotEmpty
    private List<String> checkUserIDs = new ArrayList<>();
}
