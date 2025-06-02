package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.user.req.ReqUserInvitePagination;
import com.anyex.apps.controller.user.req.ReqUserRebatePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.UserRebate;
import com.anyex.apps.user.model.InviteRebateSummaryModel;
import com.anyex.apps.user.model.UserInviteRebateModel;
import com.anyex.apps.user.service.UserInviteService;
import com.anyex.apps.user.service.UserRebateService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user/userInviteRebate")
@Api(tags = "用户邀请返佣")
public class UserInviteRebate extends GenericController {

    @Autowired(required = false)
    private UserInviteService userInviteService;

    @Autowired(required = false)
    private UserRebateService userRebateService;


    /**
     * 获取用户邀请记录信息
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "/userInviteData")
    @ApiOperation(value = "查询推荐记录列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<UserInviteRebateModel>> userInviteData(@Validated @RequestBody ReqUserInvitePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        PaginateResult<UserInviteRebateModel> uerInvites = userInviteService.listInviteeRebatesByInviterId(pagin,principal.getId());
        return getJsonMessage(CommonEnums.SUCCESS, uerInvites);
    }

    /**
     * 获取用户返佣信息
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "/userRebateData")
    @ApiOperation(value = "查询用户返佣列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<UserRebate>> userRebateData(@Validated @RequestBody ReqUserRebatePagination pagin) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);

        UserRebate userRebate = new UserRebate();
        BeanUtils.copyProperties(pagin, userRebate);
        userRebate.setInviterId(principal.getId());
        PaginateResult<UserRebate> userRebates= userRebateService.search(pagin,userRebate);
        return getJsonMessage(CommonEnums.SUCCESS, userRebates);
    }

    /**
     * 获取用户返佣信息
     * @return
     * @throws BusinessException
     */
    @GetMapping(value = "/selectInviteRebateSummary")
    @ApiOperation(value = "查询用户总览", httpMethod = "GET")
    public JsonMessage<InviteRebateSummaryModel> selectInviteRebateSummary() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        InviteRebateSummaryModel inviteRebateSummaryModel = userInviteService.selectInviteRebateSummary(principal.getId());

        return this.getJsonMessage(CommonEnums.SUCCESS, inviteRebateSummaryModel);
    }
}
