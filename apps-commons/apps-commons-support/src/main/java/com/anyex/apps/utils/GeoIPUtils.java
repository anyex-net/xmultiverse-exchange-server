package com.anyex.apps.utils;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * GeoIPUtils 介绍
 * <p>File：GeoIPUtils.java </p>
 * <p>Title: GeoIPUtils </p>
 * <p>Description:GeoIPUtils </p>
 * <p>Copyright: Copyright (c) May 2018/2/10 </p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
public class GeoIPUtils
{
    private static LookupService       cl;
    
    private volatile static GeoIPUtils singleton;
    
    public static GeoIPUtils getInstance()
    {
        if (singleton == null)
        {
            synchronized (GeoIPUtils.class)
            {
                if (singleton == null)
                {
                    singleton = new GeoIPUtils();
                    singleton.init();
                }
            }
        }
        return singleton;
    }
    
    /**
    * 初始化，传入qqwry.dat的全路径
    * @throws IOException
    */
    public void init()
    {
        ClassPathResource resource = new ClassPathResource("GeoLiteCity.dat");
        try
        {
            InputStream inputStream = resource.getInputStream();
            File tempFile = File.createTempFile("GeoLiteCity", "dat");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            cl = new LookupService(tempFile, LookupService.GEOIP_MEMORY_CACHE);
        }
        catch (IOException e)
        {
            String file = GeoIPUtils.class.getResource("/GeoLiteCity.dat").toString().substring(5);
            // String file = "/Users/Playguy/Documents/Workspace/bitms-parent/bitms-trade/src/main/resources/GeoLiteCity.dat";
            try
            {
                cl = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            }
            catch (IOException e1)
            {
                log.error(e.getLocalizedMessage());
            }
        }
    }
    
    public LookupService getCl()
    {
        return cl;
    }
    
    /**
     * 取国家信息
     * @param ip
     * @return
     */
    public Location getLocation(String ip)
    {
        Location location;
        try
        {
            location = cl.getLocation(ip);
        }
        catch (Exception var4)
        {
            return null;
        }
        return location;
    }
    
    /**
     * 取国家信息
     * @param ip
     * @return
     */
    public String getCountryCode(String ip)
    {
        Location location = cl.getLocation(ip);
        return null == location ? "" : location.countryCode;
    }
    
//    public static void main(String[] args)
//    {
//        GeoIPUtils ipUtils = getInstance();
//        LookupService cl = ipUtils.getCl();
//        Location location = cl.getLocation("54.250.188.199");
//        System.out.println(location.countryName + location.city);
//        location = cl.getLocation("13.124.173.161");
//        System.out.println(location.countryName + location.city);
//    }
}
