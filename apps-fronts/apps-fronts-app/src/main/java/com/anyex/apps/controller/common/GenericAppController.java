//package com.anyex.apps.controller.common;
//
//import com.anyex.apps.bean.GenericController;
//import com.maxmind.geoip.Location;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * GenericAppController
// * <p>File：GenericAppController.java</p>
// * <p>Title: GenericAppController</p>
// * <p>Description: GenericAppController</p>
// * <p>Copyright: Copyright (c) 2019/10/23</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//public class GenericAppController extends GenericController
//{
//    @Autowired(required = false)
//    private AccountOpRecordNoSqlService accountOpRecordNoSqlService;
//
//    /**
//     * 保存操作日志
//     * @param principal
//     * @param opType 操作类型 login:登录，setting:安全设置，default:默认
//     * @param content
//     */
//    public void saveOperationLogs(UserPrincipal principal, String opType, String content)
//    {
//        try
//        {
//            if (null == principal) principal = OnLineUserUtils.getPrincipal();
//            HttpServletRequest request = ServletsUtils.getRequest();
//            AccountOpRecord accountOpRecord = new AccountOpRecord();
//            accountOpRecord.setSystemName(GlobalConst.PROJECT_NAME);
//            accountOpRecord.setAccountId(principal.getId());
//            accountOpRecord.setAccountName(principal.getUserName());
//            accountOpRecord.setOpType(opType);
//            accountOpRecord.setContent(content);
//            accountOpRecord.setUrl(request.getRequestURI());
//            accountOpRecord.setIpAddr(NetworkUtils.getIpAddr(request));
//            accountOpRecord.setCreateDate(CalendarUtils.getCurrentLong());
//            if (null != accountOpRecord.getIpAddr())
//            {
//                String regionName = "Unknown address";
//                String[] ipArray = accountOpRecord.getIpAddr().split(",");
//                for (String ip : ipArray)
//                {
//                    Location location = GeoIPUtils.getInstance().getLocation(ip);
//                    if (null != location)
//                    {
//                        regionName = new StringBuilder(location.countryName).append("|").append(location.city).toString();
//                    }
//                    break;
//                }
//                accountOpRecord.setRegionName(regionName);
//            }
//            accountOpRecordNoSqlService.insert(accountOpRecord);
//        }
//        catch (RuntimeException e)
//        {
//            e.printStackTrace();
//            log.error("账户操作日志记录失败:{}", e.getCause());
//        }
//        finally
//        {
////            Long endTime = System.currentTimeMillis() + 86400000;
////            StringBuffer key = new StringBuffer(CacheConst.POLICY_PERFIX).append("uplocktime_Widthdraw_").append(principal.getId());
////            RedisUtils.putObject(key.toString(), endTime, CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
//        }
//    }
//}
