package com.anyex.apps.task.user;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.entity.UserInvite;
import com.anyex.apps.user.service.UserInviteService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户邀请记录调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class UserInviteTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    UserInviteService userInviteService;

    /**
     * 用户邀请记录调度
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void userInviteTask() throws RuntimeException
    {
        log.info("用户邀请记录调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:userInviteTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                List<User> listAllUser = userService.selectAll();
                if(null!=listAllUser && listAllUser.size() > 0){
                    UserInvite userInviteSearch = new UserInvite();
                    for(int i=0; i<listAllUser.size(); i++){
                        //
                        if(StringUtils.isNotEmpty(listAllUser.get(i).getReferralCode())){
                            //
                            userInviteSearch.setInviteeId(listAllUser.get(i).getId());
                            UserInvite userInviteDB = userInviteService.selectOne(userInviteSearch);
                            //
                            if(null!=userInviteDB){
                                log.info("已存在邀请记录 userInviteDB:{}", userInviteDB);
                            } else {
                                try
                                {
                                    User userDB = userService.findByUnid(Long.valueOf(listAllUser.get(i).getReferralCode()));
                                    if(null != userDB){
                                        log.info("不已存在邀请记录 需要新插入邀请记录");
                                        UserInvite userInviteNew = new UserInvite();
                                        userInviteNew.setInviterId(userDB.getId());
                                        userInviteNew.setInviteeId(listAllUser.get(i).getId());
                                        userInviteNew.setInviteType("0");
                                        userInviteNew.setInviteCodeUsed(listAllUser.get(i).getReferralCode());
                                        userInviteNew.setIsValid(1);
                                        userInviteNew.setCreateTime(System.currentTimeMillis());
                                        log.info("不已存在邀请记录 需要新插入邀请记录 userInviteNew:{}", userInviteNew);
                                        userInviteService.insert(userInviteNew);
                                    }
                                } catch (BusinessException be) {
                                    log.error("错误:{}", be.getLocalizedMessage());
                                    continue;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户邀请记录调度异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("用户邀请记录调度异常: error={}", "分布式锁获取失败");
        }
        log.info("用户邀请记录调度 结束任务");
    }
}
