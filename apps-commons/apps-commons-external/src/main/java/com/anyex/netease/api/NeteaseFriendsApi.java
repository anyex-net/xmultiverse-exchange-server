package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.Map;

public class NeteaseFriendsApi extends NeteaseApi {
    static
    {

    }


    /**
     * 添加好友
     * @param body
     * POST 请求中 Body 的设置如下：
     * 参数	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	添加好友的发起者accid
     * faccid	String	32	必填	lisi	添加好友的接收者accid
     * type	int	/	必填	1	1 直接加好友（无需对方同意）；2 请求加好友（需要对方同意）；3 同意加好友；4 拒绝加好友
     * msg	String	256	选填	我是xx	加好友对应的请求消息
     * serverex	String	256	选填	/	服务器端扩展字段
     * 此字段 client 端只读，server 端读写
     * @return
     * @throws BusinessException
     */
    public static JSONObject friendAdd(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("faccid")))
        {
            throw new BusinessException("faccid不能为空");
        }
        if(StringUtils.isBlank(body.get("type")))
        {
            throw new BusinessException("type不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.friendAddUrl,body));
        return obj;
    }

    /**
     * 修改好友信息
     * @param body
     * 参数	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	修改好友信息的用户accid
     * faccid	String	32	必填	lisi	被更新信息的用户accid
     * alias	String	128	选填	同事	给好友增加备注名，可设置为空字符串
     * ex	String	256	选填	/	修改ex字段，可设置为空字符串
     * serverex	String	256	选填	/	修改serverex字段，可设置为空字符串。此字段 client 端只读，server 端读写
     * @return
     * @throws BusinessException
     */
    public static JSONObject friendUpdate(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("faccid")))
        {
            throw new BusinessException("faccid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.friendUpdateUrl,body));
        return obj;
    }

    /**
     * 删除好友
     * @param body
     * 参数名称	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	删除好友的发起者accid
     * faccid	String	32	必填	lisi	被删除的好友accid
     * isDeleteAlias	boolean	/	选填	true	是否需要删除备注信息
     * false:不需要,true:需要
     * 默认为false
     * @return
     * @throws BusinessException
     */
    public static JSONObject friendDelete(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("faccid")))
        {
            throw new BusinessException("faccid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.friendDeleteUrl,body));
        return obj;
    }

    /**
     * 获取好友列表
     * @param body
     * 参数	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	查询好友列表的发起者accid
     * updatetime	Long	64	必填	1440037706987	更新时间戳，接口返回该时间戳之后有更新的好友列表
     * @return
     * 返回参数
     * 参数	类型	示例	描述
     * code	int	200	状态码
     * size	int	2	列表中的好友数量
     * friends	String	{"createtime":1440037706987,
     * "bidirection":true,
     * "faccid":"t2"}	更新的好友列表
     * @throws BusinessException
     */
    public static JSONObject friendGet(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.friendGetUrl,body));
        return obj;
    }

    /**
     * 获取好友关系
     * @param body
     * 参数	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	查询好友关系的发起者accid
     * faccid	String	32	必填	lisi	被查询好友关系的用户accid
     * @return
     * 返回参数
     * 参数
     * 类型
     * 示例	描述
     * code	int	200	状态码
     * friend	JASON	{"createtime": 1440037706987,"ex": null,"bidirection": true, "faccid": "t2", "serverex": null,"updatetime": 1440037706987}	好友信息，包括：
     * createtime：好友关系创建时间
     * ex：扩展字段
     * bidirection：双向好友标志
     * faccid：好友的云信IM账号
     * serverex：服务端扩展字段
     * updatetime：好友关系更新时间
     * @throws BusinessException
     */
    public static JSONObject riendGetByAccid(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("faccid")))
        {
            throw new BusinessException("faccid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.friendGetByAccidUrl,body));
        return obj;
    }

    /**
     * 设置黑名单/静音
     * @param body
     * 参数名称
     * 类型
     * 字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	用户账号accid
     * targetAcc	String	32	必填	lisi	被拉黑或被静音的账号accid
     * relationType	int	/	必填	1	本次操作的类型
     * 1 黑名单操作（拉黑）；2 设置静音操作
     * value	int	/	必填	0	操作值
     * 0 取消拉黑或静音；1 加入黑名单或静音
     * @return
     * {
     *    "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject userSetSpecialRelation(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("targetAcc")))
        {
            throw new BusinessException("targetAcc不能为空");
        }
        if(StringUtils.isBlank(body.get("relationType")))
        {
            throw new BusinessException("relationType不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userSetSpecialRelationUrl,body));
        return obj;
    }

    /**
     * 查看指定用户的黑名单和静音列表
     * @param body
     * 参数名称	类型	字符串长度上限	是否必填	示例	描述
     * accid	String	32	必填	zhangsan	用户账号accid
     * @return
     * 返回参数
     * 参数名称	类型	示例	描述
     * mutelist	String	["abc","cde"]	被静音的账号列表
     * blacklist	String	["abc","cde"]	被拉黑的账号列表
     * code	int	200	状态码
     * @throws BusinessException
     */
    public static JSONObject userListBlackAndMuteList(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.userListBlackAndMuteListUrl,body));
        return obj;
    }



}
