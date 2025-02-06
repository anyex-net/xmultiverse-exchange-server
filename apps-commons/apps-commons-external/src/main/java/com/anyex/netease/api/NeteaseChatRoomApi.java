package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.HashMap;
import java.util.Map;

public class NeteaseChatRoomApi extends NeteaseApi {
    static
    {

    }

    /**
     创建聊天室
     * 参数	类型	必填	说明
     * creator	String	是	聊天室属主的 IM 账号（accid），最大长度 32 字符
     * name	String	是	聊天室名称，最大长度 128 字符
     * announcement	String	否	聊天室公告，最大长度 4096 字符
     * broadcasturl	String	否	直播地址，最大长度 1024 字符
     * ext	String	否	扩展字段，最大长度 4096 字符
     * queuelevel	Integer	否	队列管理权限。0：所有人都有权限变更队列，1：只有主播管理员才能操作变更。默认 0
     * bid	String	否	反垃圾业务 ID，JSON 字符串，{"textbid":"","picbid":""}，若不填则使用原来的反垃圾配置
     * delayClosePolicy	Integer	否	聊天室定时关闭方式。0：不开启定时关闭，1：固定时间关闭（不管聊天室中是否还有用户），2：空闲关闭（等聊天室中没有用户后固定时间关闭）
     * 聊天室定时关闭功能，需要先开通后才能使用。如未开通，请先联系商务经理或技术支持开通该功能。
     * 仅该功能开通后创建的聊天室支持定制关闭。开通前创建的聊天室不支持定时关闭。
     * delaySeconds	Long	否	聊天室定时关闭时间
     * 若设置 delayClosePolicy=1 或 2，同时未传定时时间，则采用应用默认的定时时间（2*3600 秒）
     * inOutNotification	Integer	否	是否关闭人员进出聊天室事件通知
     * 0：关闭；1：开启（默认）
     *
     * {"code":200,"info":{"name":"kssj_1001","accid":"kssj_1001","token":"kssj_1001"}}
     */
    public static JSONObject chatroomCreate(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("creator")))
        {
            throw new BusinessException("creator不能为空");
        }
        if(StringUtils.isBlank(body.get("name")))
        {
            throw new BusinessException("name不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.chatroomCreateUrl,body));
        return obj;
    }


    public static void test() throws BusinessException{

        Map<String, String> map = new HashMap<String, String>();
        map.put("creator","kssj_1001");
        map.put("name","第一个聊天室");
        System.out.println(chatroomCreate(map));


    }

}
