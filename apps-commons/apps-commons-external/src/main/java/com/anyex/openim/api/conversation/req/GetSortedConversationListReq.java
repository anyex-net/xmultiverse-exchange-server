package com.anyex.openim.api.conversation.req;

import lombok.Data;
import com.anyex.openim.base.RequestPagination;

import java.util.List;

@Data
public class GetSortedConversationListReq {
    private String userID;
    private List<String> conversationIDs;
    private RequestPagination pagination = new RequestPagination();
}
