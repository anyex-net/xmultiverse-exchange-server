package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.Map;

public class NeteaseUserCardApi extends NeteaseApi {
    static
    {

    }

    /**
     * 获取用户名片
     * @param body
     * 参数	类型	必须	说明
     * accids	Array of strings	是	用户帐号 ID 列表，例如：["id1", "id2", "id3"]。格式错误会返回 414 参数错误。
     * 一次最多查询 200 个账号。
     * muteStatus	Boolean	否	是否返回功能模块的禁言状态，默认为 false
     * @return
     * 返回参数
     * 参数	说明
     * code	状态码，200 表示请求成功
     * uinfos	查询的用户信息列表，JSON
     * uinfos 中的参数说明
     *
     * 参数	说明
     * accid	云信 IM 账号
     * name	昵称
     * icon	头像
     * sign	签名
     * email	邮箱
     * birth	生日
     * mobile	手机号码
     * ex	自定义扩展信息
     * gender	性别
     * valid	是否有效
     * mute	是否全局禁言
     * muteP2P	账号在单聊模块中是否被禁言
     * muteQChat	账号在群组模块中是否被禁言
     * muteTeam	账号在群组模块中是否被禁言
     * muteRoom	账号在聊天室模块中是否被禁言
     * @throws BusinessException
     */
    public static JSONObject userInfos(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accids")))
        {
            throw new BusinessException("accids不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userInfosUrl,body));
        return obj;
    }


    /**
     * 更新用户名片
     * @param body
     * 参数	类型	必须	说明
     * accid	String	是	用户帐号，最大长度32字符，必须保证一个APP内唯一
     * name	String	否	用户昵称，最大长度64字符，可设置为空字符串
     * icon	String	否	用户头像 URL，最大长度1024字节，可设置为空字符串
     * sign	String	否	用户签名，最大长度256字符，可设置为空字符串
     * email	String	否	用户email，最大长度64字符，可设置为空字符串
     * birth	String	否	用户生日，最大长度16字符，可设置为空字符串
     * mobile	String	否	用户mobile，最大长度32字符
     * 非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)，可设置为空字符串
     * gender	int	否	用户性别，0表示未知，1表示男，2表示女，其它会报参数错误
     * ex	String	否	用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串，也可以设置为空字符串
     * bid	String	否	反垃圾业务ID，JSON字符串，{"textbid":"","picbid":""}，若不填则使用原来的反垃圾配置
     * @return
     * {
     *   "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject userUpdateInfos(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userUpdateInfosUrl,body));
        return obj;
    }



}
