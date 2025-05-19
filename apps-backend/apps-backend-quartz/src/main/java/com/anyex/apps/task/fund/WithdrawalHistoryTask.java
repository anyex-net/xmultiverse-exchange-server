package com.anyex.apps.task.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.service.WithdrawalHistoryService;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.RedisLock;
import com.anyex.wallet.XMWalletApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 提现历史调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class WithdrawalHistoryTask
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private WithdrawalHistoryService withdrawalHistoryService;

    /**
     * 提现历史调度
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 */13 * * * ?")
    public void withdrawalHistoryTask() throws RuntimeException
    {
        log.info("提现历史调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:withdrawalHistoryTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                // 6. 获取交易列表
                // tx_type的值：DEPOSIT传回充值列表，WITHDRAW传回提现列表，其他值传回所有列表
                JSONObject jsonObjectResp = XMWalletApi.get_tx_list("WITHDRAW");
                log.info("get_tx_list jsonObjectResp:{}", jsonObjectResp);
                // get_tx_list jsonObjectResp:{"code":0,"data":[],"signature":"c9f1687f554e37ddea67fe6efdf651687036afc9e99a7043abd8e9ea7c36f11c","message":"Success"}
                // get_tx_list jsonObjectResp:{"code":0,"data":[{"tx_status":"completed","coin_no":"b5c70cd1-5bdc-4783-beba-f515bd3581ad","amount":"1.100000","request_no":"0f61a212-86ff-4e2a-a543-4a0c796608bd","chain_code":"ETH","tx_hash":"0xb1e9ee372e44124a806bb0a84450d2d306f7c8474e9b854446a654ac8458b9e5","from":"","to":"0xDb6B16F3381CC3482bE7ca2EDCC465d0c0aCD6e1","tx_type":"WITHDRAW","coin_code":"USDTF","user_no":"85d94e47-157d-4f57-bd60-ce07d9b6ac35"}],
                //                          "signature":"791cc120f32be32f84f99024048025a98f8d8b1706f3ba49567575106ff24b0a","message":"Success"}
                if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
                    JSONArray jsonArray = jsonObjectResp.getJSONArray("data");
                    if(null != jsonArray && jsonArray.size() > 0)
                    {
                        WithdrawalHistory withdrawalHistorySearch = new WithdrawalHistory();
                        User userSearch = new User();
                        for(int i=0; i<jsonArray.size(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            log.info("get_tx_list jsonArray index {} : {}", i+1, jsonObject);
                            // get_tx_list jsonArray index 1 : {"tx_status":"completed","coin_no":"b5c70cd1-5bdc-4783-beba-f515bd3581ad","amount":"1.100000","request_no":"0f61a212-86ff-4e2a-a543-4a0c796608bd","chain_code":"ETH","tx_hash":"0xb1e9ee372e44124a806bb0a84450d2d306f7c8474e9b854446a654ac8458b9e5","from":"","to":"0xDb6B16F3381CC3482bE7ca2EDCC465d0c0aCD6e1","tx_type":"WITHDRAW","coin_code":"USDTF","user_no":"85d94e47-157d-4f57-bd60-ce07d9b6ac35"}
                            withdrawalHistorySearch.setTransId(jsonObject.getString("request_no"));
                            WithdrawalHistory withdrawalHistoryDB = withdrawalHistoryService.selectOne(withdrawalHistorySearch);
                            if(null != withdrawalHistoryDB){
                                userSearch.setRemark(jsonObject.getString("user_no"));
                                User userDB = userService.selectOne(userSearch);
                                if(null != userDB){
                                    log.info("userDB:{}", userDB);
                                    withdrawalHistoryDB.setState("completed".equals( jsonObject.getString("tx_status")) ? "exported":"exportfail");
                                    withdrawalHistoryDB.setUpdateTime(System.currentTimeMillis());
                                    log.info("待更新withdrawalHistoryDB:{}", withdrawalHistoryDB);
//                                    withdrawalHistoryService.updateByPrimaryKeySelective(withdrawalHistoryDB);
//                                    //
//                                    // 推送交易状态
//                                    jsonObjectResp = XMWalletApi.push_tx_status(jsonObject.getString("request_no"));
//                                    log.info("push_tx_status jsonObjectResp:{}", jsonObjectResp);
//                                    // push_tx_status jsonObjectResp:{"code":0,"signature":"ea19a979f4afcaafafef882a7a6e546ef6f8947565550437d2644c819ae47cb3","message":"Success"}
//                                    if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//                                        JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
//                                        log.info("push_tx_status jsonObjectData data: {}", jsonObjectData);
//                                    } else {
//                                        log.error("push_tx_status jsonObjectResp data: {}", jsonObjectResp);
//                                    }
                                } else {
                                    log.error("对应的用户不存在，暂时无法执行，请检查！");
                                }
                            } else {
                                log.error("对应的提现记录不存在，无需执行！");
                            }
                        }
                    }
                } else {
                    log.error("获取提现交易列表 异常：error={}", jsonObjectResp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("提现历史调度 异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("提现历史调度 异常: error={}", "分布式锁获取失败");
        }
        log.info("提现历史调度 结束任务");
    }
}
