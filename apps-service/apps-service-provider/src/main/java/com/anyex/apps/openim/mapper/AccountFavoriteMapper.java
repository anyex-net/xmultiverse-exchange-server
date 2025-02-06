package com.anyex.apps.openim.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.openim.entity.AccountFavorite;

/**
 * 账户收藏 持久层接口
 * <p>File：AccountFavoriteMapper.java </p>
 * <p>Title: AccountFavoriteMapper </p>
 * <p>Description:AccountFavoriteMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountFavoriteMapper extends GenericMapper<AccountFavorite>
{

}
