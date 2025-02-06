/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import com.anyex.apps.openim.model.ConversationLimitModel;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.openim.entity.ConversationLimit;
import com.anyex.apps.openim.mapper.ConversationLimitMapper;

/**
 * 会话限制 服务实现类
 * <p>File：ConversationLimitServiceImpl.java </p>
 * <p>Title: ConversationLimitServiceImpl </p>
 * <p>Description:ConversationLimitServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ConversationLimitServiceImpl extends GenericServiceImpl<ConversationLimit> implements ConversationLimitService
{
    protected ConversationLimitMapper conversationLimitMapper;

    @Autowired
    OpenImApiService openImApiService;

    @Autowired(required = false)
    public ConversationLimitServiceImpl(ConversationLimitMapper conversationLimitMapper)
    {
        super(conversationLimitMapper);
        this.conversationLimitMapper = conversationLimitMapper;
    }

    @Override
    public ConversationLimitModel canChartCheck(String conversationID, String fromUserId, String toUserId) {
        ConversationLimitModel model = new ConversationLimitModel();
        ConversationLimit from = new ConversationLimit();
        from.setConversationId(conversationID);
        from.setUserId(fromUserId);
        from = conversationLimitMapper.selectOne(from);
        if(null == from)
        {
            model.setCurrentUserStatus(true);
            model.setAllUserStatus(false);
        }
        else
        {
            model.setCurrentUserStatus((from.getStatus() == 1 ? true : (from.getMsgSeq()<3?true:false)));
            ConversationLimit to = new ConversationLimit();
            to.setConversationId(conversationID);
            to.setUserId(fromUserId);
            to = conversationLimitMapper.selectOne(to);
            model.setAllUserStatus((from.getStatus() == 1 && to.getStatus() ==1));
        }
        return model;
    }

    @Override
    public void canChartRecord(String conversationID, String fromUserId, String toUserId) {

        ConversationLimit from = new ConversationLimit();
        from.setConversationId(conversationID);
        from.setUserId(fromUserId);
        from = conversationLimitMapper.selectOne(from);
        if(null == from)
        {
            // 首次记录两人关系
            from = new ConversationLimit();
            from.setConversationId(conversationID);
            from.setFromUserId(fromUserId);
            from.setToUserId(toUserId);
            from.setUserId(fromUserId);
            from.setMsgSeq(1);
            from.setStatus(0);
            from.setCreateTime(System.currentTimeMillis());
            from.setUpdateTime(System.currentTimeMillis());
            from.setId(SerialnoUtils.buildPrimaryKey());
            conversationLimitMapper.insert(from);

            ConversationLimit to = new ConversationLimit();
            to.setConversationId(conversationID);
            to.setFromUserId(fromUserId);
            to.setToUserId(toUserId);
            to.setUserId(toUserId);
            to.setMsgSeq(1);
            to.setStatus(1);
            to.setCreateTime(System.currentTimeMillis());
            to.setUpdateTime(System.currentTimeMillis());
            to.setId(SerialnoUtils.buildPrimaryKey());
            conversationLimitMapper.insert(to);
        }
        else
        {
            if(StringUtils.equalsAnyIgnoreCase(fromUserId,from.getFromUserId()))
            {
                from.setMsgSeq(from.getMsgSeq()+1);
                conversationLimitMapper.updateByPrimaryKey(from);
            }
            else
            {
                ConversationLimit to = new ConversationLimit();
                to.setConversationId(conversationID);
                to.setUserId(toUserId);
                to = conversationLimitMapper.selectOne(to);
                to.setStatus(1);
                conversationLimitMapper.updateByPrimaryKey(to);
            }
        }
    }


}
