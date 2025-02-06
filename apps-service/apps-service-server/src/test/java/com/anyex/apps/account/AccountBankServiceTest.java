package com.anyex.apps.account;


import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountBankServiceTest extends BaseServiceImplTest {

    @Autowired
    AccountReceivingBankService accountReceivingBankService;

    @Autowired
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Test
    public void testFindBank()
    {
        System.out.println(accountReceivingBankService.findByAccountIdAndAccountNo(200000000000L,"'22010104830000'"));
        WalletAssetTransactions transactions = walletAssetTransactionsService.selectByPrimaryKey(1L);
        checkAccountReceivingBank(transactions);

    }

    void checkAccountReceivingBank(WalletAssetTransactions transactions)
    {
        try
        {
            AccountReceivingBank bank = accountReceivingBankService.findByAccountIdAndAccountNo(transactions.getAccountId(),transactions.getTrxAccountNo());
            if(null == bank)
            {
                bank = new AccountReceivingBank();
                bank.setAccountId(transactions.getAccountId());
                bank.setAccountType(transactions.getTrxAccountType());
                bank.setAccountNo(transactions.getTrxAccountNo());
                bank.setAccountName(transactions.getTrxAccountName());
                bank.setBankName(transactions.getTrxBankName());
                bank.setIban(transactions.getTrxIban());
                bank.setCnic(transactions.getTrxCnic());
                bank.setEmail(transactions.getTrxEmail());
                bank.setMobile(transactions.getTrxMobile());
                bank.setRemark("提现成功后添加");
                bank.setCreateTime(System.currentTimeMillis());
                bank.setUpdateTime(System.currentTimeMillis());
                accountReceivingBankService.insert(bank);
            }
        }catch (Exception e)
        {
            log.error("提现确认后添加银行账号信息失败："+e.getMessage());
        }
    }

}