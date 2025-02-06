/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.model;

import com.anyex.apps.account.entity.Account;
import io.swagger.annotations.ApiModel;
import lombok.Data;



@Data
@ApiModel(description = "邀请关系表")
public class AccountInviteRewardsAccountsModel
{

    // 新注册用户
    Account registerAccount;

    // 有效的一级邀请用户
    Account effectiveFirstInviteAccount;

    // 有效的二级邀请用户
    Account effectiveSecondInviteAccount;

    // 有效的三级邀请用户
    Account effectiveThirdInviteAccount;

}

