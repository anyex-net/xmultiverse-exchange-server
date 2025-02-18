package com.anyex.apps.utils;

/**
 * 风险限额
 */
public class RiskLimit
{

//    /**
//     * 获取USD最大可借计算
//     * @param amount
//     */
//    public static SpotLeverBorrowModel getMaxCanBorrow(Long stockinfoId, BigDecimal amount)
//    {
//        return getMaxCanBorrowV2(stockinfoId,amount);
//    }
//
//    /**
//     * USD最大可借反推净值N
//     * @param amount
//     */
//    public static BigDecimal getUsedNetValue(Long stockinfoId,BigDecimal amount)
//    {
//        return getUsedNetValueV2(stockinfoId,amount);
//    }
//
//    /**
//     * 强平提前比计算
//     * @param amount
//     * @return
//     */
//    public static BigDecimal prePercent(Long stockinfoId,BigDecimal amount)
//    {
//        return prePercentV2(stockinfoId,amount);
//    }
//
//    /**
//     * 获取USD最大可借计算-v2
//     * @param amount
//     */
//    private static SpotLeverBorrowModel getMaxCanBorrowV2(Long stockinfoId, BigDecimal amount)
//    {
//        SpotLeverBorrowModel entity = new SpotLeverBorrowModel();
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            entity.setLever(BigDecimal.ZERO);
//            entity.setMaxBorrowAmt(BigDecimal.ZERO);
//            return entity;
//        }
//        // <=1  return N*500
//        if(amount.compareTo(BigDecimal.valueOf(1))<=0)
//        {
//            BigDecimal maxAmt = amount.multiply(BigDecimal.valueOf(500));
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 1<N<=10 return 500+(N-1)*200
//        else if(amount.compareTo(BigDecimal.valueOf(1))>0 && amount.compareTo(BigDecimal.valueOf(10))<=0)
//        {
//
//            BigDecimal maxAmt = BigDecimal.valueOf(500).add(
//                (amount.subtract(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(200))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 10<N<=100 return 2300+(N-10)*100
//        else if(amount.compareTo(BigDecimal.valueOf(10))>0 && amount.compareTo(BigDecimal.valueOf(100))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(2300).add(
//                    (amount.subtract(BigDecimal.valueOf(10))).multiply(BigDecimal.valueOf(100))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 100<N<=1000 return 11300+(N-100)*50
//        else if(amount.compareTo(BigDecimal.valueOf(100))>0 && amount.compareTo(BigDecimal.valueOf(1000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(11300).add(
//                    (amount.subtract(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(50))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 1000<N<=10000 return 56300+(N-1000)*20
//        else if(amount.compareTo(BigDecimal.valueOf(1000))>0 && amount.compareTo(BigDecimal.valueOf(10000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(56300).add(
//                    (amount.subtract(BigDecimal.valueOf(1000))).multiply(BigDecimal.valueOf(20))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // >10000 return 236300+(N-10000)
//        else
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(236300).add(
//                    (amount.subtract(BigDecimal.valueOf(10000))).multiply(BigDecimal.valueOf(10))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//    }
//
//    /**
//     * 获取USD最大可借计算-v1
//     * @param amount
//     */
//    private static SpotLeverBorrowModel getMaxCanBorrowV1(Long stockinfoId,BigDecimal amount)
//    {
//        SpotLeverBorrowModel entity = new SpotLeverBorrowModel();
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            entity.setLever(BigDecimal.ZERO);
//            entity.setMaxBorrowAmt(BigDecimal.ZERO);
//            return entity;
//        }
//        // <=50  return N*500
//        if(amount.compareTo(BigDecimal.valueOf(50))<=0)
//        {
//            BigDecimal maxAmt = amount.multiply(BigDecimal.valueOf(500));
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 50<N<=200 return 25000+(N-50)*200
//        else if(amount.compareTo(BigDecimal.valueOf(50))>0 && amount.compareTo(BigDecimal.valueOf(200))<=0)
//        {
//
//            BigDecimal maxAmt = BigDecimal.valueOf(25000).add(
//                    (amount.subtract(BigDecimal.valueOf(50))).multiply(BigDecimal.valueOf(200))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 200<N<=1000 return 55000+(N-200)*100
//        else if(amount.compareTo(BigDecimal.valueOf(200))>0 && amount.compareTo(BigDecimal.valueOf(1000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(55000).add(
//                    (amount.subtract(BigDecimal.valueOf(200))).multiply(BigDecimal.valueOf(100))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 1000<N<=5000 return 135000+(N-1000)*50
//        else if(amount.compareTo(BigDecimal.valueOf(1000))>0 && amount.compareTo(BigDecimal.valueOf(5000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(135000).add(
//                    (amount.subtract(BigDecimal.valueOf(1000))).multiply(BigDecimal.valueOf(50))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 5000<N<=20000 return 335000+(N-5000)*20
//        else if(amount.compareTo(BigDecimal.valueOf(5000))>0 && amount.compareTo(BigDecimal.valueOf(20000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(335000).add(
//                    (amount.subtract(BigDecimal.valueOf(5000))).multiply(BigDecimal.valueOf(20))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 20000<N<=100000 return 635000+(N-20000)*10
//        else if(amount.compareTo(BigDecimal.valueOf(20000))>0 && amount.compareTo(BigDecimal.valueOf(100000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(635000).add(
//                    (amount.subtract(BigDecimal.valueOf(20000))).multiply(BigDecimal.valueOf(10))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 100000<N<=500000 return 1435000+(N-100000)*5
//        else if(amount.compareTo(BigDecimal.valueOf(100000))>0 && amount.compareTo(BigDecimal.valueOf(500000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(1435000).add(
//                    (amount.subtract(BigDecimal.valueOf(100000))).multiply(BigDecimal.valueOf(5))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // 500000<N<=2500000 return 3435000+(N-500000)*2
//        else if(amount.compareTo(BigDecimal.valueOf(500000))>0 && amount.compareTo(BigDecimal.valueOf(2500000))<=0)
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(3435000).add(
//                    (amount.subtract(BigDecimal.valueOf(500000))).multiply(BigDecimal.valueOf(2))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//        // >2500000 return 7435000+(N-2500000)
//        else
//        {
//            BigDecimal maxAmt =  BigDecimal.valueOf(7435000).add(
//                    (amount.subtract(BigDecimal.valueOf(2500000)))
//            );
//            BigDecimal lever = maxAmt.divide(amount,2,BigDecimal.ROUND_DOWN);
//            entity.setLever(lever);
//            entity.setMaxBorrowAmt(maxAmt);
//            return entity;
//        }
//    }
//
//
//    /**
//     * USD最大可借反推净值N-v2
//     * @param amount
//     */
//    private static BigDecimal getUsedNetValueV2(Long stockinfoId,BigDecimal amount)
//    {
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            return BigDecimal.ZERO;
//        }
//        // <=1  return N*500  = amount/500
//        if(amount.compareTo(BigDecimal.valueOf(500))<=0)
//        {
//            return amount.divide(BigDecimal.valueOf(500),4,BigDecimal.ROUND_DOWN);
//
//        }
//        // 1<N<=10 return 500+(N-1)*200 = (amount-500)/200+1
//        else if(amount.compareTo(BigDecimal.valueOf(500))>0 && amount.compareTo(BigDecimal.valueOf(2300))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(500));
//            return amount.divide(BigDecimal.valueOf(200),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(1));
//        }
//        // 10<N<=100 return 11300+(N-10)*100  = (amount-2300)/100+10
//        else if(amount.compareTo(BigDecimal.valueOf(2300))>0 && amount.compareTo(BigDecimal.valueOf(11300))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(2300));
//            return amount.divide(BigDecimal.valueOf(100),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(10));
//        }
//        // 100<N<=1000 return 56300+(N-100)*50 = (amount-11300)/50+100
//        else if(amount.compareTo(BigDecimal.valueOf(11300))>0 && amount.compareTo(BigDecimal.valueOf(56300))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(11300));
//            return amount.divide(BigDecimal.valueOf(50),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(100));
//        }
//        // 1000<N<=10000 return 56300+(N-1000)*20  = (amount-56300)/20+1000
//        else if(amount.compareTo(BigDecimal.valueOf(56300))>0 && amount.compareTo(BigDecimal.valueOf(236300))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(56300));
//            return amount.divide(BigDecimal.valueOf(20),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(1000));
//        }
//        // >10000 return 266300+(N-10000)*10 = (amount-236300)/10+10000
//        else
//        {
//            amount = amount.subtract(BigDecimal.valueOf(236300));
//            return amount.divide(BigDecimal.valueOf(10),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(10000));
//        }
//    }
//
//    /**
//     * USD最大可借反推净值N-v1
//     * @param amount
//     */
//    private static BigDecimal getUsedNetValueV1(Long stockinfoId,BigDecimal amount)
//    {
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            return BigDecimal.ZERO;
//        }
//        // <=50  return N*500
//        if(amount.compareTo(BigDecimal.valueOf(25000))<=0)
//        {
//            return amount.divide(BigDecimal.valueOf(500),4,BigDecimal.ROUND_DOWN);
//
//        }
//        // 50<N<=200 return 25000+(N-50)*200
//        else if(amount.compareTo(BigDecimal.valueOf(25000))>0 && amount.compareTo(BigDecimal.valueOf(55000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(25000));
//            return amount.divide(BigDecimal.valueOf(200),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(50));
//        }
//        // 200<N<=1000 return 55000+(N-200)*100
//        else if(amount.compareTo(BigDecimal.valueOf(55000))>0 && amount.compareTo(BigDecimal.valueOf(135000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(55000));
//            return amount.divide(BigDecimal.valueOf(100),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(200));
//        }
//        // 1000<N<=5000 return 135000+(N-1000)*50
//        else if(amount.compareTo(BigDecimal.valueOf(135000))>0 && amount.compareTo(BigDecimal.valueOf(335000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(135000));
//            return amount.divide(BigDecimal.valueOf(50),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(1000));
//        }
//        // 5000<N<=20000 return 335000+(N-5000)*20
//        else if(amount.compareTo(BigDecimal.valueOf(335000))>0 && amount.compareTo(BigDecimal.valueOf(635000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(335000));
//            return amount.divide(BigDecimal.valueOf(20),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(5000));
//        }
//        // 20000<N<=100000 return 635000+(N-20000)*10
//        else if(amount.compareTo(BigDecimal.valueOf(635000))>0 && amount.compareTo(BigDecimal.valueOf(1435000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(635000));
//            return amount.divide(BigDecimal.valueOf(10),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(20000));
//        }
//        // 100000<N<=500000 return 1435000+(N-100000)*5
//        else if(amount.compareTo(BigDecimal.valueOf(1435000))>0 && amount.compareTo(BigDecimal.valueOf(3435000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(1435000));
//            return amount.divide(BigDecimal.valueOf(5),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(100000));
//        }
//        // 500000<N<=2500000 return 3435000+(N-500000)*2
//        else if(amount.compareTo(BigDecimal.valueOf(3435000))>0 && amount.compareTo(BigDecimal.valueOf(7435000))<=0)
//        {
//            amount = amount.subtract(BigDecimal.valueOf(3435000));
//            return amount.divide(BigDecimal.valueOf(2),4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(500000));
//        }
//        // >2500000 return 7435000+(N-2500000)
//        else
//        {
//            amount = amount.subtract(BigDecimal.valueOf(7435000));
//            return amount.setScale(4,BigDecimal.ROUND_DOWN).add(BigDecimal.valueOf(2500000));
//        }
//    }
//
//    /**
//     * 强平提前比计算-v2
//     * @param amount
//     * @return
//     */
//    private static BigDecimal prePercentV2(Long stockinfoId,BigDecimal amount)
//    {
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            return BigDecimal.ZERO;
//        }
//        // <=500  return 0.05%
//        if(amount.compareTo(BigDecimal.valueOf(500))<=0)
//        {
//            return BigDecimal.valueOf(0.05).divide(BigDecimal.valueOf(100));
//        }
//        // 500<N<=2300 return 0.1%
//        else if(amount.compareTo(BigDecimal.valueOf(500))>0 && amount.compareTo(BigDecimal.valueOf(2300))<=0)
//        {
//            return BigDecimal.valueOf(0.1).divide(BigDecimal.valueOf(100));
//        }
//        // 2300<N<=11300 return 0.25%
//        else if(amount.compareTo(BigDecimal.valueOf(2300))>0 && amount.compareTo(BigDecimal.valueOf(11300))<=0)
//        {
//            return BigDecimal.valueOf(0.25).divide(BigDecimal.valueOf(100));
//        }
//        // 11300<N<=236300 return 0.5%
//        else if(amount.compareTo(BigDecimal.valueOf(11300))>0 && amount.compareTo(BigDecimal.valueOf(236300))<=0)
//        {
//            return BigDecimal.valueOf(0.5).divide(BigDecimal.valueOf(100));
//        }
//        // >236300 0.75%
//        else
//        {
//            return BigDecimal.valueOf(0.75).divide(BigDecimal.valueOf(100));
//        }
//    }
//
//    /**
//     * 强平提前比计算-v1
//     * @param amount
//     * @return
//     */
//    private static BigDecimal prePercentV1(Long stockinfoId,BigDecimal amount)
//    {
//        if(amount.compareTo(BigDecimal.ZERO)<=0)
//        {
//            return BigDecimal.ZERO;
//        }
//        // <=55000  return 0.15%
//        if(amount.compareTo(BigDecimal.valueOf(55000))<=0)
//        {
//            return BigDecimal.valueOf(0.15).divide(BigDecimal.valueOf(100));
//        }
//        // 55000<N<=135000 return 0.25%
//        else if(amount.compareTo(BigDecimal.valueOf(55000))>0 && amount.compareTo(BigDecimal.valueOf(135000))<=0)
//        {
//            return BigDecimal.valueOf(0.25).divide(BigDecimal.valueOf(100));
//        }
//        // 135000<N<=335000 return 0.5%
//        else if(amount.compareTo(BigDecimal.valueOf(135000))>0 && amount.compareTo(BigDecimal.valueOf(335000))<=0)
//        {
//            return BigDecimal.valueOf(0.5).divide(BigDecimal.valueOf(100));
//        }
//        // 335000<N<=635000 return 1%
//        else if(amount.compareTo(BigDecimal.valueOf(335000))>0 && amount.compareTo(BigDecimal.valueOf(635000))<=0)
//        {
//            return BigDecimal.valueOf(1).divide(BigDecimal.valueOf(100));
//        }
//        // 635000<N<=1435000 return 1.5%
//        else if(amount.compareTo(BigDecimal.valueOf(635000))>0 && amount.compareTo(BigDecimal.valueOf(1435000))<=0)
//        {
//            return BigDecimal.valueOf(1.5).divide(BigDecimal.valueOf(100));
//        }
//        // 1435000<N<=3435000 return 2%
//        else if(amount.compareTo(BigDecimal.valueOf(1435000))>0 && amount.compareTo(BigDecimal.valueOf(3435000))<=0)
//        {
//            return BigDecimal.valueOf(2).divide(BigDecimal.valueOf(100));
//        }
//        // 3435000<N<=7435000 return 2.5%
//        else if(amount.compareTo(BigDecimal.valueOf(3435000))>0 && amount.compareTo(BigDecimal.valueOf(7435000))<=0)
//        {
//            return BigDecimal.valueOf(2.5).divide(BigDecimal.valueOf(100));
//        }
//        // >7435000 3%
//        else
//        {
//            return BigDecimal.valueOf(3).divide(BigDecimal.valueOf(100));
//        }
//    }
}
