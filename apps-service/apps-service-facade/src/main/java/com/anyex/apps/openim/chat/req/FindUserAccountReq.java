package com.anyex.apps.openim.chat.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author  anyex
 */
@Data
public class FindUserAccountReq {
    @NotEmpty
    private List<String> userIDs;
}
