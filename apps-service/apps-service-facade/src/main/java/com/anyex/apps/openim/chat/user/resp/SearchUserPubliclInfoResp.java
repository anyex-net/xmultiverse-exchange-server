package com.anyex.apps.openim.chat.user.resp;

import com.anyex.apps.openim.chat.vo.UserPublicInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchUserPubliclInfoResp {
    @ApiModelProperty(value = "列表")
    private List<UserPublicInfo> users;
    @ApiModelProperty(value = "数量")
    private Integer total;
}
