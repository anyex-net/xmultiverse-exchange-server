package com.anyex.apps.openim.chat.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author  anyex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserFullInfoReq {

    @NotNull(message = "用户 ID 不能为空")
    @ApiModelProperty(value = "用户id数组",required = true)
    private List<String> userIDs;
}
