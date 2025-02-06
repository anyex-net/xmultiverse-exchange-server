package com.anyex.apps.openim.chat.user.resp;

import com.anyex.apps.openim.chat.vo.UserSearchFullInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchUserFullInfoResp {
    @ApiModelProperty(value = "列表")
    private List<UserSearchFullInfo> users = new ArrayList<UserSearchFullInfo>();
    @ApiModelProperty(value = "数量")
    private Integer total;
}
