
package com.anyex.apps.openim.service;

import com.anyex.apps.openim.service.AccountFavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.openim.entity.AccountFavorite;
import com.anyex.apps.openim.mapper.AccountFavoriteMapper;

/**
 * 账户收藏 服务实现类
 * <p>File：AccountFavoriteServiceImpl.java </p>
 * <p>Title: AccountFavoriteServiceImpl </p>
 * <p>Description:AccountFavoriteServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountFavoriteServiceImpl extends GenericServiceImpl<AccountFavorite> implements AccountFavoriteService
{
    protected AccountFavoriteMapper accountfavoriteMapper;

    @Autowired(required = false)
    public AccountFavoriteServiceImpl(AccountFavoriteMapper accountfavoriteMapper)
    {
        super(accountfavoriteMapper);
        this.accountfavoriteMapper = accountfavoriteMapper;
    }
}
