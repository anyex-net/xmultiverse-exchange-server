package com.anyex.apps.account.service;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.AccountSignInDetail;

/**
 * 账户签到明细 服务接口
 * <p>File：AccountSignInDetailService.java </p>
 * <p>Title: AccountSignInDetailService </p>
 * <p>Description:AccountSignInDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountSignInDetailService extends GenericService<AccountSignInDetail> {

    AccountSignInDetail findByAccountIdAndSignInDate(Long accountId,String signInDate);
}
