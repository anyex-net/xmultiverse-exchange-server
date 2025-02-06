package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;
import com.anyex.netease.model.MsgAttachModel;

import java.util.HashMap;
import java.util.Map;

public class NeteaseMsgAttachApi extends NeteaseApi {
    static
    {

    }

    /**
     发送自定义系统通知
     参数	类型	必须	说明
     from	String	是	发送者accid，用户帐号，最大32字符，APP内唯一
     msgtype	int	是	0：点对点自定义通知，1：群消息自定义通知，其他返回414
     to	String	是	msgtype=0 时需填入接收系统通知的用户的的云信 IM 账号（accid），msgtype=1 时需填入接收系统通知的群的 ID（即 tid），最大 32 字符
     attach	String	是	自定义系统通知的具体内容，开发者组装的字符串，建议 JSON 格式，最大长度 4096 字符
     pushcontent	String	否	推送文案，最长 500 个字符。更多推送说明请参见 推送配置参数详解。
     若未设置 pushcontent 字段，则不会触发推送服务，但会将 payload 字段内容下发给客户端。
     payload	String	否	推送对应的 payload，必须是 JSON 格式，不能超过 2048 字符。更多说明请参见 推送 payload 配置
     sound	String	否	如果有指定推送，此属性指定为客户端本地的声音文件名，长度不要超过 30 个字符，如果不指定，会使用默认声音
     save	int	否	只能传入 1 或 2，1 表示只发在线，2 表示会存离线，传入其他值将报错（状态码：414）。默认会存离线
     option	String	否	发消息时特殊指定的行为选项，JSON 格式，可用于指定该自定义系统通知是否计入未读数等特殊行为。option 中字段如果不填，则自动使用默认值
     option 示例：
     {"badge":false,"needPushNick":false,"route":false}
     字段说明：
     badge：该系统通知是否需要计入到未读计数中，默认 true
     needPushNick: 推送文案是否需要带上昵称，不设置该参数时默认 false (注意默认值与sendMsg.action 接口的 needPushNick 有别)
     route: 该消息是否需要抄送至您指定的应用服务器；默认true (需要应用开通消息抄送功能)
     isForcePush	boolean	否	发自定义系统通知时，是否强制推送，默认 false
     forcePushContent	String	否	发自定义系统通知时，强制推送的文案，最长 500 个字符
     forcePushAll	boolean	否	发群自定义系统通知时，强推列表是否为群里除发送者外的所有有效成员，默认 false
     forcePushList	String	否	发群自定义系统通知时的强推列表，格式为 JSONArray，示例：["accid1","accid2"]，列表内最多 100 个用户
     env	String	否	系统通知需要抄送到的环境的名称，对应您在云信控制台中配置的自定义抄送的环境名称（如下图），最大 32 个字符
     自定义抄送环境.png
     *
     * {"code":200,"info":{"name":"kssj_1001","accid":"kssj_1001","token":"kssj_1001"}}
     */
    private static JSONObject sendAttachMsg(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("from")))
        {
            throw new BusinessException("from不能为空");
        }
        if(StringUtils.isBlank(body.get("msgtype")))
        {
            throw new BusinessException("msgtype不能为空");
        }
        if(StringUtils.isBlank(body.get("to")))
        {
            throw new BusinessException("to不能为空");
        }
        if(StringUtils.isBlank(body.get("attach")))
        {
            throw new BusinessException("attach不能为空");
        }
        if(StringUtils.isBlank(body.get("pushcontent")))
        {
            throw new BusinessException("pushcontent不能为空");
        }
        body.put("save", "2"); // 离线的可以补发
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.sendAttachMsgUrl,body));
        return obj;
    }


    /**
     * 发送群推送消息
     * @param fromAccId 发送者云信ID
     * @param tid 云信群ID
     * @param attach 自定义系统通知的具体内容 JSON格式
     * @param pushcontent 推送文案
     * @return
     * @throws BusinessException
     */
    public static JSONObject sendGroupAttachMsg(String fromAccId, String tid, MsgAttachModel attach, String pushcontent) throws BusinessException
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("from",fromAccId);
        map.put("msgtype","1"); // 1群 0点对点
        map.put("to",tid);
        if(attach != null)map.put("attach",JSONObject.toJSONString(attach));
        map.put("pushcontent",pushcontent);
        return sendAttachMsg(map);
    }

    /**
     * 发送群推送消息
     * @param fromAccId 发送者云信ID
     * @param toAccId 接收者云信ID
     * @param attach 自定义系统通知的具体内容 JSON格式
     * @param pushcontent 推送文案
     * @return
     * @throws BusinessException
     */
    public static JSONObject sendPointAttachMsg(String fromAccId,String toAccId,MsgAttachModel attach,String pushcontent) throws BusinessException
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("from",fromAccId);
        map.put("msgtype","0"); // 1群 0点对点
        map.put("to",toAccId);
        if(attach != null)map.put("attach",JSONObject.toJSONString(attach));
        map.put("pushcontent",pushcontent);
        return sendAttachMsg(map);
    }

    // 测试用例
    public static void test(String args[]) throws BusinessException{
        // 发送点对点推送消息
        MsgAttachModel attach = new MsgAttachModel(0,"你的帖子被点赞了","张三","李四",1L,2L);
        System.out.println(sendPointAttachMsg("18058280976","18667826620",attach,"点对点发送，详细内容请看attach字段"));
        System.out.println("================");
        // 发送群推送消息
        attach = new MsgAttachModel(1,"建群成功","张三","群名称",null,null);
        System.out.println(sendGroupAttachMsg("18058280976","19150849838",attach,"群消息发送，详细内容请看attach字段"));
    }

}
