package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.HashMap;
import java.util.Map;

public class NeteaseUserApi extends NeteaseApi {
    static
    {

    }

    /**
     注册云信IM账号
     登录 IM 前，需注册云信 IM 账号。
     本文介绍如何通过云信服务端 API 创建 IM 账号，以及相关的常见问题。
     * 参数名称	类型	字符串长度上限	是否必选	示例	描述
     * accid	String	32	必选	"123456"	云信 IM 账号，必须保证唯一性。若涉及字母，传参时请一律小写处理。只允许字母、数字、半角下划线_、@、半角点以及半角-。请注意以此接口返回结果中的accid为准。
     * token	String	128	选填	"abcdef"	用户账号对应的登录密钥token。如果未指定，云信会自动生成token，并在创建成功后返回。
     * name	String	64	选填	"zhangsan"	用户昵称
     * props	String	1024	选填	{"k":"v"}	该参数已不建议使用。
     * icon	String	1024	选填	"https://netease/xxx.png"	用户头像 URL
     * sign	String	256	选填	"Hello World"	用户签名
     * email	String	64	选填	"xxx@163.com"	用户邮箱地址
     * birth	String	16	选填	"xxxx-xx-xx"	用户生日
     * mobile	String	32	选填	"+852-xxxxxxxx"	用户手机号码，非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)
     * gender	int	/	选填	2	用户性别，0-未知，1-男，2-女。其它会报参数错误。
     * ex	String	1024	选填	{"level":1}	用户资料扩展字段，建议封装成JSON。
     *
     * {"code":200,"info":{"name":"kssj_1001","accid":"kssj_1001","token":"kssj_1001"}}
     */
    public static JSONObject userCreate(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userCreateUrl,body));
        return obj;
    }


    /**
     * 刷新token  不指定
     * @param body
     * 参数名称	类型	字符串长度上限	是否必选	示例	描述
     * accid	String	32	必选	"123456"	待刷新的云信账号
     * @return
     * "Content-Type": "application/json; charset=utf-8"
     * {
     *     "code": 200,
     *     "info": {
     *         "accid": "zhangsan",
     *         "token": "07b9ee85767990779707af4030******"
     *     }
     * }
     * @throws BusinessException
     */
    public static JSONObject userToken(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userrefreshTokenUrl,body));
        return obj;
    }

    /**
     * 封禁账户
     * @param body
     * 参数名称	类型	字符串长度上限	是否必选	示例	描述
     * accid	String	32	必选	"123456"	待封禁的云信账号
     * needkick	Boolean	/	选填	true	是否踢掉被禁用户，默认 false，不踢。如果设置为 true，那么该账号在被封禁的同时被踢出登录
     * 如果封禁时未踢出该账号，且该账号处于登录状态，则当前登录状态不受影响，仍然可以收发消息。即封禁是禁止登录，而不是禁止收发消息。
     * kickNotifyExt	String	256	选填	{"k":"v"}	该操作的扩展字段，可透传至客户端SDK（版本至少为v7.7.0）
     * needUnbindPushToken	Boolean	/	选填	true	是否解绑推送 token，默认 false，不解绑。若解绑，那么该账号所有设备不会收到推送（包括 IM 推送和圈组推送）
     * @return {"code":200}
     * @throws BusinessException
     */
    public static JSONObject userBlock(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userBlockUrl,body));
        return obj;
    }

    /**
     * 账号全局禁言(禁言，解禁)
     * @param body
     * 参数	类型	必须	说明
     * accid	String	是	用户帐号，最大长度 32 位字符
     * mute	Boolean	是	是否全局禁言：
     * true：全局禁言，false：取消全局禁言
     * mute = true，即表示该账号在所有功能模块都处于禁言状态
     * @return {"code":200}
     * @throws BusinessException
     */
    public static JSONObject userMude(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("mute")))
        {
            throw new BusinessException("mute不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userMuteUrl,body));
        return obj;
    }

    /**
     * 账号功能模块禁言
     * @param body
     * 参数	类型	必须	说明
     * accid	String	是	用户帐号，最大长度 32 位字符
     * muteP2P	Boolean	否	是否在单聊模块中禁言该账号
     * true：禁言，false：取消禁言
     * muteTeam	Boolean	否	是否在群组模块中禁言该账号
     * true：禁言，false：取消禁言
     * muteRoom	Boolean	否	是否在聊天室模块中禁言该账号
     * true：禁言，false：取消禁言
     * muteQChat	Boolean	否	是否在圈组模块中禁言该账号
     * true：禁言，false：取消禁言
     * @return
     * {"code":200,"data":{"muteTeam":false,"muteRoom":false,"muteP2P":false,"muteQChat":false}}
     * @throws BusinessException
     */
    public static JSONObject userMuteModule(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userMuteModuleUrl,body));
        return obj;
    }

    /**
     *设置移动端是否需要推送(桌面端在线时)
     * @param body
     * 参数	类型	必须	说明
     * accid	String	是	用户的云信 IM 帐号
     * donnopOpen	String	是	桌面端在线时，移动端是否不推送：
     * true：移动端不需要推送，false：移动端需要推送
     * @return
     * @throws BusinessException
     */
    public static JSONObject usersetDonnop(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("donnopOpen")))
        {
            throw new BusinessException("donnopOpen不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.usersetDonnopUrl,body));
        return obj;
    }

    /**
     * 批量查询账号在线状态
     * @param body
     * 参数名称	类型	必填	描述
     * accids	Array of strings	是	需要查询的用户帐号 ID 列表，一次最多查询 100 个账号。
     * 例如：["id1", "id2", "id3"]，格式错误会返回 414 参数错误。
     * @return
     * {"code":200,"data":{"invalidAccids":[],"status":{}}}
     * @throws BusinessException
     */
    public static JSONObject userOnlineStatus(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accids")))
        {
            throw new BusinessException("accids不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userOnlineStatusUrl,body));
        return obj;
    }

    public static void test() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("accid","kssj_1001");
        map.put("token","kssj_1001");
        map.put("name","kssj_1001");
        //System.out.println(userCreate(map));

        map.clear();
        map.put("accid","kssj_1001");
        //System.out.println(userToken(map));

        map.clear();
        map.put("accid","kssj_1001");
        //System.out.println(userBlock(map));

        map.clear();
        map.put("accid","kssj_1001");
        map.put("mute","false");
        //System.out.println(userMude(map));

        map.clear();
        map.put("accid","kssj_1001");
        map.put("muteQChat","false");
        // System.out.println(userMuteModule(map));

        map.clear();
        map.put("accid","kssj_1001");
        map.put("donnopOpen","false");
        // System.out.println(usersetDonnop(map));

        map.clear();
        map.put("accids","['kssj_1001']");
        // System.out.println(userOnlineStatus(map));
    }

}
