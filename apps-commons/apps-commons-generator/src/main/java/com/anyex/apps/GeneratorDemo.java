package com.anyex.apps;

/**
 * GeneratorDemo Introduce
 * <p>File：GeneratorDemo.java </p>
 * <p>Title: GeneratorDemo </p>
 * <p>Description:GeneratorDemo </p>
 * <p>Copyright: Copyright (c) 17/6/16</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GeneratorDemo
{
    public static void main(String[] args) throws Exception
    {
        GeneratorFile file = new GeneratorFile();
         file.generateCRUDByTables(new String[]{
//                 "Currencies", "Instruments", "InstTradeFee", "UserInstTradeFee"
                 "InstrumentsCurrency",
//                 "User", "UserCertKyc",
//                 "Balances", "BalancesTransHistory", "DepositAddress", "DepositTransHistory", "WithdrawalHistory"
//                 "RwaCertInstSpvPromoter", "RwaCertInstInvestor", "RwaBalances", "RwaBalancesTransHistory",
//                 "RwaInstSpvCompany", "RwaInstSpvProduct", "RwaInstSpvProductPurchase", "RwaInstSpvProductDividend", "RwaInstSpvProductRedemption"
//                 "AppDownloadInfo", "AppActivationInfo"
//                 "RoleInfoTest","ROLEINFO", "sys_config1", "SYS_CONFIG",
//                 "Information","NewsInfo",
//                 "Notice","Praise",
//                 "Reply","Video",
//                 "AccountQuotation"
         });

        System.out.println(System.currentTimeMillis());
    }
}
