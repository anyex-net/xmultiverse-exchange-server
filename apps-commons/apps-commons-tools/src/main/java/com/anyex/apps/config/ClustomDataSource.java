package com.anyex.apps.config;

import com.zaxxer.hikari.HikariDataSource;
import com.anyex.apps.utils.EncryptUtils;

/**
 * 推展数据库连接
 * <p>File: ClustomDataSource.java </p>
 * <p>Title: ClustomDataSource </p>
 * <p>Description: ClustomDataSource </p>
 * <p>Copyright: Copyright (c) 2019-02-20</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ClustomDataSource extends HikariDataSource
{
    @Override
    public void setPassword(String password)
    {
        super.setPassword(EncryptUtils.desDecrypt(password));
    }
}
