package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品公告ID请求对象")
public class ReqRwaInstSpvProductNoticeId extends GenericEntity {
    private static final long serialVersionUID = 1L;
}
