
package com.anyex.apps.social.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsActivity;
import com.anyex.apps.social.mapper.SnsActivityMapper;

/**
 * 社交活动 服务实现类
 * <p>File：SnsActivityServiceImpl.java </p>
 * <p>Title: SnsActivityServiceImpl </p>
 * <p>Description:SnsActivityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsActivityServiceImpl extends GenericServiceImpl<SnsActivity> implements SnsActivityService
{
    protected SnsActivityMapper snsactivityMapper;

    @Autowired(required = false)
    public SnsActivityServiceImpl(SnsActivityMapper snsactivityMapper)
    {
        super(snsactivityMapper);
        this.snsactivityMapper = snsactivityMapper;
    }
}
