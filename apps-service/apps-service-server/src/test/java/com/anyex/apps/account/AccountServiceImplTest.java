package com.anyex.apps.account;

import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountServiceImplTest extends BaseServiceImplTest
{
    @Autowired
    private AccountService accountService;

    @Test
    public void verifySignature() throws BusinessException {
        Account account = accountService.selectByPrimaryKey(863947054664257536l );
        log.info("account:{}", account);
        log.info("getSign:{}", account.verifySignature());
    }
}
