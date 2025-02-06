package com.anyex.netease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息推送实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgAttachModel {

    // 消息类型 0点对点  1群  默认点对点
    private Integer type = 0;

    // 消息内容
    private String content;

    // 消息发送者昵称
    private String senderNick;

    // 接收者昵称或群名称
    private String receiverNick;

    // 业务主ID
    private Long bizId; // 帖子ID等

    // 业务子ID
    private Long bizSubId; // 点赞帖子对应的评论ID等
}
