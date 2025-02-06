package com.anyex.apps.pay;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.model.AssetWithdrawApplyResultModel;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.common.entity.SysParameter;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.pay.model.RespWithdrawAsset;
import com.anyex.apps.utils.HttpUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.globalpay.config.GlobalPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;


/**
 * 支付业务-提现(代付)  相关测试类
 * @author Playguy
 */
@Slf4j
public class PayWithdrawServiceTest extends BaseServiceImplTest {

    @Autowired
    WalletAssetTransactionsService walletAssetTransactionsService;

    @Autowired
    AccountReceivingBankService accountReceivingBankService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired
    SysParameterService sysParameterService;

    private Long bankId = 7788L;
    private Long accountId = 7788L;

    @Autowired
    GlobalPayConfig globalPayConfig;

    @Before
    public void pre()
    {

    }

    public AccountReceivingBank bankinfo(Long id)
    {
        AccountReceivingBank bank = accountReceivingBankService.selectByPrimaryKey(id);
        System.out.println("bankinfo "+ JSON.toJSONString(bank));
        return bank;
    }

    public WalletAsset wallet()
    {
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        if(null == asset)
        {
            asset = new WalletAsset();
            asset.setAccountId(accountId);
            asset.setCurrency(GlobalConst.CURRENCY_PKR);
            asset.setBalance(BigDecimal.ZERO);
            asset.setFrozenBal(BigDecimal.ZERO);
            asset.setUpdateTime(System.currentTimeMillis());
            asset.setRemark("7788");
            asset.setId(accountId);
            walletAssetService.insert(asset);
        }
        System.out.println("asset "+ JSON.toJSONString(asset));
        return asset;
    }

    @Test
    public void withdrawWithBankId()
    {
        /**
         // BANK
         INSERT INTO im.AccountReceivingBank (id, accountId, accountType, accountNo, accountName, bankName, iban, cnic, email, mobile, remark, createTime, updateTime)
         VALUES (1, 7788, 'BANK', '12427950358303', 'SHI ZHENLING', 'HABIB BANK LIMITED', 'PK60HABB0012427950358303', '0001000000127', '35148465@qq.com', '03190504096', 测试手工加入', 1710487081868, 1710487081868);

         // WALLET-JAZZCASH
         INSERT INTO im.AccountReceivingBank (id, accountId, accountType, accountNo, accountName, bankName, iban, cnic, email, mobile, remark, createTime, updateTime)
         VALUES (2, 7788, 'WALLET', '03330361777', 'Farooq Ahmed ', 'UNKONWN', 'PK32JCMA2510923330361777', '42000-8022915-1', 'farooqsangi@gmail.com', '03330361777', '测试手工加入', 1710487081868, 1710487081868);

         // WALLET-EP
         INSERT INTO im.AccountReceivingBank (id, accountId, accountType, accountNo, accountName, bankName, iban, cnic, email, mobile, remark, createTime, updateTime)
         VALUES (3, 7788, 'WALLET', '03330361777', 'Farooq Ahmed', 'UNKONWN', 'PK58TMFB0000000077275216', '42000-8022915-1', 'farooqsangi@gmail.com', '03330361777', '测试手工加入', 1710487081868, 1710487081868);
         */
        BigDecimal amount = BigDecimal.valueOf(120);
        AssetWithdrawApplyResultModel model = walletAssetTransactionsService.withdrawApply(bankinfo(3L),amount,GlobalConst.PAYMENT_CHANNEL_GLOBALPAY);
        System.out.println("AssetWithdrawApplyResultModel "+ JSON.toJSONString(model));
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WalletAssetTransactions transaction = walletAssetTransactionsService.findByTrxNo(model.getTrxNo());
        walletAssetTransactionsService.withdrawQueryAndUpdate(transaction.getId(),transaction.getAccountId());
    }



    @Test
    public void withdrawAsset()
    {
        BigDecimal withdrawAmt = BigDecimal.valueOf(100);
        BigDecimal minAmt = BigDecimal.ZERO;
        BigDecimal feeRate = BigDecimal.ZERO;
        SysParameter parameter = sysParameterService.getParameterByName("withDrawMinAmount");
        if(null == parameter)
        {
            throw new BusinessException("请在系统参数中配置钱包提现最小金额");
        }
        minAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现最小金额:{}" , minAmt);
        parameter = sysParameterService.getParameterByName("withDrawFeeRate");
        if(null == parameter)
        {
            throw new BusinessException("请在系统参数中配置钱包提现费率");
        }
        feeRate = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现费率:{}" , feeRate);

        parameter = sysParameterService.getParameterByName("withDrawMaxAmount");
        if(null == parameter)
        {
            log.info("提现业务：请在系统参数中配置钱包提现单次最大金额：withDrawMaxAmount");
            throw new BusinessException("请在系统参数中配置钱包提现单次最大金额");
        }
        BigDecimal maxAmt = new BigDecimal(parameter.getValue());
        log.info("提现业务：钱包提现单次最大金额:{}" , maxAmt);
        RespWithdrawAsset ret = null;

        // 资产处理
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        if(null == asset)
        {
            ret = new RespWithdrawAsset(
                    accountId,
                    GlobalConst.CURRENCY_PKR,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    false,
                    maxAmt,
                    minAmt,
                    feeRate);
        }
        // 账户余额
        BigDecimal balance = asset.getBalance();
        // 冻结余额
        BigDecimal frozenBalance = asset.getFrozenBal();
        // 可用余额
        BigDecimal enableBalance = asset.getBalance().subtract(asset.getFrozenBal()).setScale(2,BigDecimal.ROUND_DOWN);
        // 最大可提
        BigDecimal maxCanWithdrawAmount = enableBalance;
        // 最小可提
        BigDecimal minCanWithdrawAmount = enableBalance;
        if(enableBalance.compareTo(minAmt)<0)
        {
            minCanWithdrawAmount = BigDecimal.ZERO;
            maxCanWithdrawAmount = BigDecimal.ZERO;
        }
        else if(enableBalance.compareTo(maxAmt)>0)
        {
            minCanWithdrawAmount = minAmt;
            maxCanWithdrawAmount = maxAmt;
        }
        else {
            minCanWithdrawAmount = minAmt;
            maxCanWithdrawAmount = enableBalance;
        }

        // 是否可以提现
        Boolean canWithdraw = true;
        if(enableBalance.compareTo(minAmt)<0)
        {
            canWithdraw = false;
        }
        ret = new RespWithdrawAsset(
                accountId,
                GlobalConst.CURRENCY_PKR,
                balance.setScale(2,BigDecimal.ROUND_DOWN),
                frozenBalance.setScale(2,BigDecimal.ROUND_DOWN),
                enableBalance.setScale(2,BigDecimal.ROUND_DOWN),
                maxCanWithdrawAmount.setScale(2,BigDecimal.ROUND_DOWN),
                minCanWithdrawAmount.setScale(2,BigDecimal.ROUND_DOWN),
                canWithdraw,
                maxAmt.setScale(2,BigDecimal.ROUND_DOWN),
                minAmt.setScale(2,BigDecimal.ROUND_DOWN),
                feeRate);

        System.out.println(JSONObject.toJSONString(ret));
    }

    @Test
    public void withdrawWithInfo()
    {
        BigDecimal amount = BigDecimal.valueOf(1);
        AccountReceivingBank bank = new AccountReceivingBank();
        BeanUtils.copyProperties(bankinfo(bankId),bank);
        bank.setId(null);
        bank.setAccountId(accountId);
        AssetWithdrawApplyResultModel model = walletAssetTransactionsService.withdrawApply(bank,amount,GlobalConst.PAYMENT_CHANNEL_GLOBALPAY);
        System.out.println("AssetWithdrawApplyResultModel "+ JSON.toJSONString(model));
    }


    @Test
    public void withdrawNotifyTest()
    {
       // HttpUtils.postWithJSON(HttpClients.createDefault(),"https://ckjqr.xiaomy.net/api/payment/globalpay/out/notify","ab=1&ac=1");
       String trxNo = "20240322131839080041";
        String status = "1";
        String model = walletAssetTransactionsService.depositNotify(trxNo,status,"");
        System.out.println("withdrawNotifyTest "+ model);
    }

    @Test
    public void withdrawQueryTest()
    {
       /* WalletAssetTransactions search = new WalletAssetTransactions();
        search.setTrxStatus(GlobalConst.STATUS_PENDING);
        search.setTrxType("withDraw");
        search.setAccountId(865057234063855616L);
        List<WalletAssetTransactions> list = walletAssetTransactionsService.findList(search);
        for (int i = 0; i < list.size(); i++) {
            WalletAssetTransactions withdraw = list.get(i);
            walletAssetTransactionsService.withdrawQueryAndUpdate(withdraw.getId(),withdraw.getAccountId());
        }*/
        walletAssetTransactionsService.withdrawQueryAndUpdate(871612029570519040L,865057234063855616L);
    }

    @Test
    public void  testConfig()
    {
        AccountReceivingBank bank = accountReceivingBankService.selectByPrimaryKey(1L);
        System.out.println(JSON.toJSONString(globalPayConfig));
        System.out.println(bank);
    }

    public static void testTransferAmt() {
        BigDecimal amount= BigDecimal.valueOf(103);
        BigDecimal feeRate = new BigDecimal("0.20");
        BigDecimal globalFeeRate = new BigDecimal("0.15");
        BigDecimal fee = amount.multiply(feeRate).setScale(2,BigDecimal.ROUND_DOWN);
        BigDecimal actAmt = (amount.subtract(fee))
                .divide((BigDecimal.ONE.subtract(globalFeeRate)),2,BigDecimal.ROUND_UP);
        System.out.println("金额"+amount.toPlainString());
        System.out.println("用户手续费"+fee.toPlainString());
        System.out.println("用户理论到账金额"+amount.subtract(fee));
        System.out.println("平台出账金额"+actAmt.toPlainString());
        BigDecimal gfee = actAmt.multiply(globalFeeRate).setScale(2,BigDecimal.ROUND_UP);
        System.out.println("验证用户到账金额"+actAmt.subtract(gfee).toPlainString());
    }

    @Test
    public void testBank()
    {
        WalletAssetTransactions transactions = walletAssetTransactionsService.selectByPrimaryKey(1L);
        transactions.setAccountId(7788L);
        transactions.setTrxAccountType("BANK");
        transactions.setTrxBankName("中国农业银行");
        transactions.setTrxMobile("18899999999");
        try
        {
            AccountReceivingBank bank = accountReceivingBankService.findByBankName(transactions.getAccountId(),transactions.getTrxAccountType(),transactions.getTrxBankName());
            if(null != bank)
            {
                if(
                        StringUtils.equalsIgnoreCase(transactions.getTrxAccountNo(),bank.getAccountNo())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxAccountName(),bank.getAccountName())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxIban(),bank.getIban())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxCnic(),bank.getCnic())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxEmail(),bank.getEmail())
                                && StringUtils.equalsIgnoreCase(transactions.getTrxMobile(),bank.getMobile())
                ) {
                    bank.setStatus(1);
                    bank.setUpdateTime(System.currentTimeMillis());
                    accountReceivingBankService.updateByPrimaryKeySelective(bank);
                    log.info("提现ID={},更新银行卡信息成功，要素匹配，提现记录={}，银行卡记录={}",transactions.getId(),JSONObject.toJSONString(transactions),JSONObject.toJSONString(bank));
                }
                else
                {
                    log.info("提现ID={},更新银行卡信息失败，要素不匹配，提现记录={}，银行卡记录={}",transactions.getId(),JSONObject.toJSONString(transactions),JSONObject.toJSONString(bank));
                }
            }

        }catch (Exception e)
        {
            log.error("提现确认后添加银行账号信息失败："+e.getMessage());
        }
    }

    @Test
    public void adjust()
    {
        // walletAssetTransactionsService.walletAssetAdjust(865057234063855616L,-1,BigDecimal.valueOf(101));
    }

    @Test
    public void getTotalWithDrawCurrDay()
    {
        System.out.println(walletAssetTransactionsService.getTotalWithDrawCurrDay(8650572340638556L, null));
        System.out.println(walletAssetTransactionsService.getTotalWithDrawCurrDay(null, "03330361777"));
    }
    

}