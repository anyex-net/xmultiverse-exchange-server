package com.anyex.apps.social.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsActivity;

/**
 * 社交活动 持久层接口
 * <p>File：SnsActivityMapper.java </p>
 * <p>Title: SnsActivityMapper </p>
 * <p>Description:SnsActivityMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsActivityMapper extends GenericMapper<SnsActivity>
{

}
