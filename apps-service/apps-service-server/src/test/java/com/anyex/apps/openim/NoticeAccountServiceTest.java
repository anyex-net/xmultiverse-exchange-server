package com.anyex.apps.openim;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.junit.Assert.assertEquals;
import com.google.common.collect.Maps;
import com.anyex.openim.api.msg.vo.OfflinePushInfo;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.entity.NoticeAccount;
import com.anyex.apps.openim.service.NoticeAccountService;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.openim.api.conversation.resp.GetSortedConversationListResp;
import com.anyex.openim.api.msg.req.SendMsgReq;
import com.anyex.openim.api.msg.resp.PullMessageBySeqsResp;
import com.anyex.openim.api.msg.resp.SendMsgResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class NoticeAccountServiceTest extends BaseServiceImplTest {

    @Autowired
    NoticeAccountService noticeAccountService;

    @Autowired
    OpenImApiService openImApiService;


    @Test
    public void search()
    {
        Pagination pagination = new Pagination(1,10);
        NoticeAccount search = new NoticeAccount();
        //search.setUserId("137000000");
        search.setNickname("xiger");
        noticeAccountService.search(pagination,search);

    }

    @Test
    public void getHistoryMsg()
    {
        String userId = "10095";
        String conversationId = "si_10088_10095";

       /* GetSortedConversationListResp c =  openImApiService. getConversation( userId,  conversationId);
        System.out.println(JSONObject.toJSONString(c));



        PullMessageBySeqsResp ret =  openImApiService.getHistoryMsg(userId, conversationId);
        System.out.println(JSONObject.toJSONString(ret));*/

        SendMsgReq seq = new SendMsgReq();
        seq.setRecvID("10087");
        seq.setSendID("10095");
        seq.setGroupID("");
        seq.setSenderNickname("Yahya Sariya");
        seq.setSenderFaceURL("");
        seq.setSenderPlatformID(1);
        Map<String,Object> map = new HashMap<String,Object>();

        JSONObject map2 = new JSONObject();
        map2.put("customType","http://www.baidu.com");
        map2.put("data","http://www.baidu.com");
        map2.put("description","http://www.baidu.com");
        map2.put("extension","http://www.baidu.com");

        JSONObject m = new JSONObject();
        m.put("customElem",JSONObject.toJSON(map2));


        map.put("notificationType", 101);
        seq.setContent(map);
        seq.setContentType(1201);
        seq.setSessionType(1);
        System.out.println(JSONObject.toJSONString(seq));



        SendMsgResp r =  openImApiService.sendMessage(seq);
        System.out.println(JSONObject.toJSONString(r));
    }


}
