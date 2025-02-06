
package com.anyex.apps.account.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.AccountSignInDetail;
import com.anyex.apps.account.mapper.AccountSignInDetailMapper;

/**
 * 账户签到明细 服务实现类
 * <p>File：AccountSignInDetailServiceImpl.java </p>
 * <p>Title: AccountSignInDetailServiceImpl </p>
 * <p>Description:AccountSignInDetailServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountSignInDetailServiceImpl extends GenericServiceImpl<AccountSignInDetail> implements AccountSignInDetailService
{
    protected AccountSignInDetailMapper accountsignindetailMapper;

    @Autowired(required = false)
    public AccountSignInDetailServiceImpl(AccountSignInDetailMapper accountsignindetailMapper)
    {
        super(accountsignindetailMapper);
        this.accountsignindetailMapper = accountsignindetailMapper;
    }

    @Override
    public AccountSignInDetail findByAccountIdAndSignInDate(Long accountId, String signInDate) {
        return accountsignindetailMapper.findByAccountIdAndSignInDate(accountId, signInDate);
    }
}
