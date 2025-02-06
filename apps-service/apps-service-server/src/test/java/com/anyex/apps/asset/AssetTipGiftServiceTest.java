package com.anyex.apps.asset;


import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetTipGiftService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class AssetTipGiftServiceTest extends BaseServiceImplTest {

    @Autowired
    WalletAssetTipGiftService walletAssetTipGiftService;


    @Test
    public void sendGift()
    {
        // 917939840169938944 917941664948031488
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(917939840169938944L);
        walletAssetTipGift.setToAccountId(917941664948031488L);
        walletAssetTipGift.setRemark("GIFT001");
        walletAssetTipGift.setTrxBalance(BigDecimal.valueOf(100));
        walletAssetTipGift.setTrxFee(BigDecimal.valueOf(30));
        walletAssetTipGiftService.sendGift(walletAssetTipGift);
    }

    @Test
    public void getGift()
    {
        //
        WalletAssetTipGift walletAssetTipGift = walletAssetTipGiftService.selectByPrimaryKey(925672862369583104L);
        walletAssetTipGiftService.getGift(walletAssetTipGift);
    }

    @Test
    public void getGiftCnt()
    {
        //
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(917939840169938944L);
        walletAssetTipGift.setToAccountId(917941664948031488L);
        walletAssetTipGift.setStatus(2);
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        System.out.println(result.size());
    }



    @Test
    public void getGiftGetList()
    {
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setToAccountId(917939840169938944L);
        walletAssetTipGift.setStatus(2);
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        System.out.println(result.size());
    }


    @Test
    public void getGiftSendList()
    {
        WalletAssetTipGift walletAssetTipGift = new WalletAssetTipGift();
        walletAssetTipGift.setFromAccountId(917939840169938944L);
       // walletAssetTipGift.setStatus(2);
        List<WalletAssetTipGift> result = walletAssetTipGiftService.findList(walletAssetTipGift);
        System.out.println(result.size());
    }



}