package com.anyex.apps.openim.chat.user.resp;

import com.anyex.apps.openim.chat.vo.UserFullInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class FindUserFullInfoResp {

    @ApiModelProperty(value = "列表")
    private List<UserFullInfo> users = new ArrayList<UserFullInfo>();
}
