package com.anyex.apps.account.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.AccountSignInInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 账户签到信息 持久层接口
 * <p>File：AccountSignInInfoMapper.java </p>
 * <p>Title: AccountSignInInfoMapper </p>
 * <p>Description:AccountSignInInfoMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountSignInInfoMapper extends GenericMapper<AccountSignInInfo>
{
    AccountSignInInfo findByAccountId(@Param("accountId") Long accountId);
}
