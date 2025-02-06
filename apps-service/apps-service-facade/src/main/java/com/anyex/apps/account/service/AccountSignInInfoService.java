package com.anyex.apps.account.service;
import com.anyex.apps.account.entity.AccountSignInDetail;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.AccountSignInInfo;
import com.anyex.apps.exception.BusinessException;

import java.math.BigDecimal;

/**
 * 账户签到信息 服务接口
 * <p>File：AccountSignInInfoService.java </p>
 * <p>Title: AccountSignInInfoService </p>
 * <p>Description:AccountSignInInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountSignInInfoService extends GenericService<AccountSignInInfo> {

    AccountSignInInfo findByAccountId(Long accountId);

    void doSignIn(Long accountId, String dateStr) throws BusinessException;

    void doCheckCutOffSignIn() throws BusinessException;

    void doAwardSignIn(AccountSignInDetail detail) throws BusinessException;
}
