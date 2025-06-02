package com.anyex.apps.task.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.base.entity.Instruments;
import com.anyex.apps.base.service.InstrumentsService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.entity.UserInvite;
import com.anyex.apps.user.entity.UserRebate;
import com.anyex.apps.user.service.UserRebateService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.utils.ValidateUtils;
import com.anyex.exchange.viabtc.api.ViabtcTradeApi;
import com.anyex.exchange.viabtc.req.ReqTradeOrderFinished;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户返佣记录调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class UserRebateTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    InstrumentsService instrumentsService;

    @Autowired(required = false)
    UserRebateService userRebateService;

    /**
     * 用户返佣记录调度
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 */2 * * * ?")
    public void userRebateTask() throws RuntimeException
    {
        log.info("用户返佣记录调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:userRebateTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock())
        {
            try
            {
                List<User> listAllUser = userService.selectAll();
                if(null!=listAllUser && listAllUser.size() > 0)
                {
                    UserRebate userRebateSearch = new UserRebate();
                    for(int i=0; i<listAllUser.size(); i++)
                    {
                        //
                        if(StringUtils.isNotEmpty(listAllUser.get(i).getReferralCode()) && ValidateUtils.isNumber(listAllUser.get(i).getReferralCode()) )
                        {
                            // 所有交易对产品列表
                            List<Instruments> listInstruments = instrumentsService.selectAll();
                            //
                            for(int j=0; j<listInstruments.size(); j++)
                            {
                                // 查询C交易核心的实时数据
                                ReqTradeOrderFinished reqTradeOrderFinished = new ReqTradeOrderFinished();
                                reqTradeOrderFinished.setUserId(listAllUser.get(i).getId());
                                // reqTradeOrderFinished.setMarket("ETHUSDT"); // 先写死后面要增加交易对字段
                                reqTradeOrderFinished.setMarket(listInstruments.get(j).getBaseCcy()+listInstruments.get(j).getQuoteCcy());
                                reqTradeOrderFinished.setStartTime(0);
                                reqTradeOrderFinished.setEndTime(0);
                                reqTradeOrderFinished.setOffset(0);
                                reqTradeOrderFinished.setLimit(100);
                                reqTradeOrderFinished.setSide(0);
                                log.info("tradeOrderFinished reqTradeOrderFinished:{}", reqTradeOrderFinished);
                                JSONObject jsonObjectTradeOrderFinished = ViabtcTradeApi.tradeOrderFinished(reqTradeOrderFinished);
                                log.info("tradeOrderFinished respJson:{}", jsonObjectTradeOrderFinished);
                                if(null != jsonObjectTradeOrderFinished)
                                {
                                    JSONArray jsonArray = jsonObjectTradeOrderFinished.getJSONObject("result").getJSONArray("records");
                                    if(null != jsonArray && jsonArray.size() > 0)
                                    {
                                        for(int k=0; k<jsonArray.size(); k++)
                                        {
                                            JSONObject jsonObject = jsonArray.getJSONObject(k);
                                            log.info("tradeOrderFinished jsonArray index {} : {}", k+1, jsonObject);
                                            // {"ftime":1740383257.8356941,"side":2,"amount":"2","taker_fee":"0.001","deal_stock":"0.19550342","source":"web","type":2,"market":"BIEXBCH","price":"0","maker_fee":"0","ctime":1740383257.835686,"id":7,"deal_fee":"0.00019550342","user":1,"deal_money":"1.9999999866"}
                                            //
                                            //
                                            userRebateSearch.setInviteeId(jsonObject.getLong("user"));
                                            userRebateSearch.setSymbol(listInstruments.get(j).getBaseCcy()+listInstruments.get(j).getQuoteCcy()); // 需要加交易对 与 交易方向
                                            userRebateSearch.setTradeSide(jsonObject.getString("side"));
                                            userRebateSearch.setTradeId(jsonObject.getLong("id"));
                                            UserRebate userRebateDB = userRebateService.selectOne(userRebateSearch);
                                            //
                                            if(null!=userRebateDB){
                                                log.info("已存在用户返佣记录 userRebateDB:{}", userRebateDB);
                                            } else {
                                                try
                                                {
                                                    User userDB = userService.findByUnid(Long.valueOf(listAllUser.get(i).getReferralCode()));
                                                    if(null != userDB){
                                                        UserRebate userRebateNew = new UserRebate();
                                                        userRebateNew.setInviterId(userDB.getId());
                                                        userRebateNew.setInviteeId(listAllUser.get(i).getId());
                                                        userRebateNew.setTradeId(jsonObject.getLong("id"));
                                                        userRebateNew.setSymbol(listInstruments.get(j).getBaseCcy()+listInstruments.get(j).getQuoteCcy());
                                                        userRebateNew.setTradeSide(jsonObject.getString("side")); // 交易方向
                                                        userRebateNew.setPriceUSDT(jsonObject.getBigDecimal("price")); // 行情价格
                                                        if(userRebateNew.getTradeSide().equals("2")){ // 卖出
                                                            userRebateNew.setTradeAmount(jsonObject.getBigDecimal("deal_stock").multiply(userRebateNew.getPriceUSDT()));
                                                            userRebateNew.setFeeAmount(jsonObject.getBigDecimal("deal_fee").multiply(userRebateNew.getPriceUSDT()));
                                                        } else { // 买入
                                                            userRebateNew.setTradeAmount(jsonObject.getBigDecimal("deal_money"));
                                                            userRebateNew.setFeeAmount(jsonObject.getBigDecimal("deal_fee"));
                                                        }
                                                        userRebateNew.setRebateRate(BigDecimal.ZERO);
                                                        userRebateNew.setRebateAmount(BigDecimal.ZERO);
                                                        userRebateNew.setStatus("pending");
                                                        userRebateNew.setSettleDate(new Date());
                                                        userRebateNew.setCreateTime(System.currentTimeMillis());
                                                        log.info("不已存在用户返佣记录 需要新插入用户返佣记录 userRebateNew:{}", userRebateNew);
                                                        userRebateService.insert(userRebateNew);
                                                    }
                                                } catch (BusinessException be) {
                                                    log.error("错误:{}", be.getLocalizedMessage());
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户返佣记录调度 异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("用户返佣记录调度 异常: error={}", "分布式锁获取失败");
        }
        log.info("用户返佣记录调度 结束任务");
    }
}
