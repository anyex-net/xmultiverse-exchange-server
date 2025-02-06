package com.anyex.apps.account.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.AccountSignInDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 账户签到明细 持久层接口
 * <p>File：AccountSignInDetailMapper.java </p>
 * <p>Title: AccountSignInDetailMapper </p>
 * <p>Description:AccountSignInDetailMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountSignInDetailMapper extends GenericMapper<AccountSignInDetail>
{
    AccountSignInDetail findByAccountIdAndSignInDate(@Param("accountId") Long accountId, @Param("signinDate") String signinDate);
}
