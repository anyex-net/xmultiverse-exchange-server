package com.anyex.apps.openim.chat.resp;

import com.anyex.apps.openim.chat.vo.UserFullInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchUserInfoResp {

    @ApiModelProperty(value = "数量")
    private Integer total;

    @ApiModelProperty(value = "用户列表")
    private List<UserFullInfo> users = new ArrayList<UserFullInfo>();
}
