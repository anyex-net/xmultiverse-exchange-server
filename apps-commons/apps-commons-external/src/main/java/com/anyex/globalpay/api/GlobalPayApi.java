package com.anyex.globalpay.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.globalpay.config.GlobalPayConfig;
import com.anyex.globalpay.util.GlobalPayUtil;

import java.util.HashMap;
import java.util.Map;

public class GlobalPayApi {
    /**
     * 文档地址
     * https://doc.globalpay.cloud/#/docs/%E5%AF%B9%E6%8E%A5%E8%AF%B4%E6%98%8E
     */
    static {
    }

    // 代收下单
    /**
     *
     参数
     参数名	必选	类型	说明
     mchOrderNo	是	string	商户订单号
     payType	是	string	支付类型 查看对接说明
     amount	是	string	金额（分）不需要小数
     currency	是	string	货币代码 查阅对接说明
     subject	是	string	商品标题
     body	是	string	商品描述
     notifyUrl	是	string	异步通知地址
     returnUrl	否	string	同步回调地址
     extParam	否	string	扩展参数 如：失败跳转地址
     name	否	String	客户姓名（巴基斯坦支付需要）
     customerCert	否	String	客户身份证号码（巴基斯坦支付需要）
     channelName	选项	String	easypaisa：0007 或 jazzcash：0008
     outType	否	String	印度支付：代付类型，IMPS、UPI
     ifsc	否	String	印度支付：受益人账户IFSC，IMPS代付时必填
     email	否	string	付款人邮箱 （巴基斯坦支付需要）
     phoneNumber	选填	string	客户电话 （巴基斯坦支付需要）
     firstName	否	string	付款人姓名
     lastName	否	string	付款人姓氏


     返回参数说明
     参数名	类型	说明
     code	int	0：响应成功 9999：响应失败
     msg	String	响应描述
     data	String	返回数据
     sign	String	签名
     data数据说明

     参数名	类型	说明
     payOrderId	String	平台订单号
     mchOrderNo	String	商户传入的订单号
     payData	String	支付链接地址
     payDataType	String	支付数据类型
     orderState	String	订单状态
     */
    public static JSONObject pay(GlobalPayConfig config,Map<String, Object> map) throws BusinessException {
        if(!map.containsKey("mchOrderNo")
                ||!map.containsKey("payType")
                ||!map.containsKey("amount")
                ||!map.containsKey("currency")
                ||!map.containsKey("subject")
                ||!map.containsKey("body")
        )
        {
            throw new BusinessException("请检查必传参数");
        }
        JSONObject resultJson = GlobalPayUtil.pay(config,map);
        return resultJson;
    }

    // 代收订单查询

    /**
     参数
     参数名	必选	类型	说明
     payOrderId	是	string	支付中心生成的订单号，与mchOrderNo二者传一即可
     mchOrderNo	是	string	商户生成的订单号，与payOrderId二者传一即可

     返回参数说明
     参数名	必填	类型	说明
     code	是	int	0：响应成功 9999：响应失败
     msg	是	string	错误原因
     data	是	object	返回下单数据,json格式数据
     sign	是	string	签名信息，data为空则不返回
     data数据格式

     参数名	必填	类型	说明
     payOrderId	是	string	平台支付系统订单号
     mchNo	是	string	商户号
     appId	是	object	应用ID
     mchOrderNo	是	string	商户传入的订单号
     amount	是	string	支付金额,单位：分
     mchFeeAmount	是	string	平台收取的手续费
     practicalMchFeeAmount	是	string	实际收取手续费 分
     currency	是	string	货币代码
     state	是	string	订单状态 0-订单生成 1-支付中 2-支付成功 3-支付失败 4-已撤销 5-已退款 6-订单关闭
     clientIp	否	string	客户端IP
     subject	是	string	商品标题
     body	是	string	商品描述
     extParam	否	string	多笔付款记录
     createdAt	是	string	订单创建时间,13位时间戳
     */
    public static JSONObject payOrderQuery(GlobalPayConfig config,Map<String, Object> map) throws BusinessException {
        if(!map.containsKey("payOrderId")
                && !map.containsKey("mchOrderNo")
        )
        {
            throw new BusinessException("请检查必传参数");
        }
        JSONObject resultJson = GlobalPayUtil.payOrderQuery(config,JSONObject.parseObject(JSON.toJSONString(map)));
        return resultJson;
    }

    // 代付下单

    /**
     参数
     参数名	必选	类型	说明
     ifCode	是	String	ACardpay 固定值
     mchOrderNo	是	string	商户订单号
     amount	是	long	金额（分）不需要小数
     currency	是	string	货币代码 [查看对接说明-货币代码]
     bankName	是	string	巴基斯坦支付需要
     accountNo	是	string	收款账号[巴基斯坦支付：填手机号，提高代付速度]
     accountName	是	string	收款姓名
     customerCert	否	string	客户身份证号码（巴基斯坦支付需要）
     customerEmail	否	string	客户邮箱（巴基斯坦支付需要）
     customerIBAN	否	string	客户银行iban(巴基斯坦支付需要）
     phoneNumber	否	String	客户手机号（巴基斯坦支付需要）
     accountType	是	String	BANK or WALLET
     clientIp	否	string	客户端IP
     transferDesc	否	string	备注信息
     notifyUrl	否	string	异步通知地址
     extraParam	否	string	扩展参数 部分场景必填 查看=>附录>代付特殊场景


     返回参数说明
     参数名	类型	说明
     code	int	0：响应成功 9999：响应失败
     msg	string	响应描述
     data	object	响应数据
     sign	string	签名值
     data数据

     参数名	类型	说明
     transferId	string	转账订单号
     mchOrderNo	string	商户转账订单号
     state	string	转账状态 0-订单生成 1-转账中 2-转账成功 3-转账失败 4-转账关闭
     amount	long	转账金额 单位：分
     accountNo	string	收款人账号
     accountName	string	收款人姓名
     errCode	string	渠道错误码
     errMsg	string	渠道错误描述
     */
    public static JSONObject transaction(GlobalPayConfig config,Map<String, Object> map) throws BusinessException {
        if(!map.containsKey("mchOrderNo")
                || !map.containsKey("amount")
                || !map.containsKey("currency")
                // || !map.containsKey("bankName")
                || !map.containsKey("accountNo")
                || !map.containsKey("accountName")
        )
        {
            throw new BusinessException("请检查必传参数");
        }
        JSONObject resultJson = GlobalPayUtil.transaction(config,JSONObject.parseObject(JSON.toJSONString(map)));
        return resultJson;
    }

    // 代付订单查询
    /**
     参数
     参数名	必选	类型	说明
     transferId	是	string	平台订单号，与mchOrderNo二者传一即可
     mchOrderNo	是	string	商户订单号，与transferId二者传一即可

     返回参数说明
     参数名	必填	类型	说明
     code	是	int	0：响应成功 9999：响应失败
     msg	是	string	错误原因
     data	是	object	返回下单数据,json格式数据
     sign	是	string	签名信息，data为空则不返回
     data数据格式

     参数名	必填	类型	说明
     transferId	是	string	平台订单号
     mchNo	是	string	商户号
     appId	是	object	应用ID
     mchOrderNo	是	string	商户订单号
     amount	是	long	支付金额,单位：分
     accountNo	是	string	收款人账号
     accountName	是	string	收款人姓名
     errMsg	否	string	渠道错误描述
     errCode	否	string	渠道错误码
     currency	是	string	货币代码
     state	是	int	订单状态 0-订单生成 1-支付中 2-支付成功 3-支付失败 4-已撤销 5-已退款 6-订单关闭
     transferDesc	是	string	转账描述
     createdAt	是	long	订单创建时间,13位时间戳
     */
    public static JSONObject transactionOrderQuery(GlobalPayConfig config,Map<String, Object> map) throws BusinessException {
        if(!map.containsKey("transferId")
                && !map.containsKey("mchOrderNo")
        )
        {
            throw new BusinessException("请检查必传参数");
        }
        JSONObject resultJson = GlobalPayUtil.transactionOrderQuery(config,JSONObject.parseObject(JSON.toJSONString(map)));
        return resultJson;
    }


    public static void main(String[] args) {
        // 订单查询
        Map<String, Object> map = new HashMap<>();
        map.clear();
        map.put("payOrderId", "P1580448586512277505");
        map.clear();
        map.put("mchNo", "M1708853858");
        map.put("appId", "65db0a62e4b03b0017c3e237");
       // map.put("mchOrderNo", "20240330060297769466");
        map.put("payOrderId", "20240330060297769466");
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign",GlobalPayUtil.getSign(map,"rxl85zk8x0dwtdsp90evv3pzrh1x22fmuh7r7g7qzj7snw7uo0u4qn1b8v9un3ooeqxlqu7zymmu6ezor8wn1it9rtxaii62hsvlaoz193wxf6hy9ylbq32sp6v62q3d"));
        GlobalPayUtil.doPost(map,"https://payserver.globalpay.cloud/api/pay/query");


        // 代收
        map.clear();
        map.put("mchOrderNo", System.currentTimeMillis());
        // 支付类型   4 原生 api 方式
        map.put("payType", "4");
        map.put("amount", "10000");
        map.put("bankPayType", "0");
        map.put("currency", "NGN");
        map.put("subject", "转账测试");
        map.put("body", "这是转账的测试测试测试");
        map.put("returnUrl", "");
        map.put("extParam", "");
        map.put("email", "");
        map.put("firstName", "");
        map.put("lastName", "");
        //pay(map);

        // 代付
        map.clear();
        map.put("mchNo", "M1708853858");
        map.put("appId", "65db0a62e4b03b0017c3e237");
        map.put("mchOrderNo", System.currentTimeMillis());
        map.put("amount", "1");
        map.put("currency", "PKR");
        map.put("accountNo", "12427950358303");
        map.put("accountName", "SHI ZHENLING");
        map.put("notifyUrl", "https://ckjqr.xiaomy.net/api/payment/globalpay/out/notify");
        map.put("bankNumber", "PK60HABB0012427950358303");
        map.put("transferDesc", "转账测试");
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign",GlobalPayUtil.getSign(map,"rxl85zk8x0dwtdsp90evv3pzrh1x22fmuh7r7g7qzj7snw7uo0u4qn1b8v9un3ooeqxlqu7zymmu6ezor8wn1it9rtxaii62hsvlaoz193wxf6hy9ylbq32sp6v62q3d"));
        // GlobalPayUtil.doPost(map,"https://payserver.globalpay.cloud/api/transferOrder");

        // 代付查询
        map.clear();
        //        map.put("transferId", "T1580487497875808258");
        map.put("mchOrderNo", "1665652557434");
       // transactionOrderQuery(map);
    }


}
