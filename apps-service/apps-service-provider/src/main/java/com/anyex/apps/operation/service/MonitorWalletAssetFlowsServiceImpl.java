/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.operation.mapper.MonitorWalletAssetFlowsMapper;
import com.anyex.apps.common.entity.SysParameter;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.operation.model.MonitorWalletAssetFlowsResultModel;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.operation.entity.MonitorWalletAssetFlows;

import java.util.List;

/**
 * 钱包资产流水监控 服务实现类
 * <p>File：MonitorWalletAssetFlowsServiceImpl.java </p>
 * <p>Title: MonitorWalletAssetFlowsServiceImpl </p>
 * <p>Description:MonitorWalletAssetFlowsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class MonitorWalletAssetFlowsServiceImpl extends GenericServiceImpl<MonitorWalletAssetFlows> implements MonitorWalletAssetFlowsService
{
    protected MonitorWalletAssetFlowsMapper monitorWalletAssetFlowsMapper;

    @Autowired
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @Autowired(required = false)
    private SysParameterService sysParameterService;

    @Autowired(required = false)
    public MonitorWalletAssetFlowsServiceImpl(MonitorWalletAssetFlowsMapper monitorWalletAssetFlowsMapper)
    {
        super(monitorWalletAssetFlowsMapper);
        this.monitorWalletAssetFlowsMapper = monitorWalletAssetFlowsMapper;
    }

    @Override
    public MonitorWalletAssetFlows findByAccountIdAndCurrency(Long accountId, String currency) {
        return monitorWalletAssetFlowsMapper.findByAccountIdAndCurrency(accountId, currency);
    }

    @Override
    public MonitorWalletAssetFlowsResultModel monitorWalletAssetFlows(Long accountId, String currency, Long startTime, Long endTime) {
        return monitorWalletAssetFlowsMapper.monitorWalletAssetFlows(accountId, currency, startTime, endTime);
    }

    @Override
    public void monitorWalletAssetFlowsTask() {
        SysParameter parameter = sysParameterService.getParameterByName("SystemTradeSwitch");
        if(StringUtils.equalsIgnoreCase(parameter.getValue(),"OFF"))
        {
            log.error("系统交易开关(SystemTradeSwitch)关闭，跳过此次检查。");
            return;
        }
        Long startTime = 0L;
        Long endtime = System.currentTimeMillis();
        List<Account> accountList = accountService.selectAll();
        StringBuffer msg = new StringBuffer("<b>账户资金流水异常:</b><br />");
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            MonitorWalletAssetFlows monitorWalletAssetFlows = findByAccountIdAndCurrency(account.getId(), GlobalConst.CURRENCY_PKR);
            if(null == monitorWalletAssetFlows)
            {
                MonitorWalletAssetFlowsResultModel ret = monitorWalletAssetFlows(account.getId(), GlobalConst.CURRENCY_PKR,startTime,endtime);
                monitorWalletAssetFlows = new MonitorWalletAssetFlows();
                monitorWalletAssetFlows.setId(SerialnoUtils.buildPrimaryKey());
                monitorWalletAssetFlows.setAccountId(account.getId());
                monitorWalletAssetFlows.setCurrency(GlobalConst.CURRENCY_PKR);
                monitorWalletAssetFlows.setUpdateTime(startTime);
                monitorWalletAssetFlows.setCreateTime(endtime);
                monitorWalletAssetFlows.setLastMonitorTime(endtime);
                // 监控异常
                if(ret != null && ret.getErrCnt()>0)
                {
                    monitorWalletAssetFlows.setMonitorStatus(false);
                    monitorWalletAssetFlows.setUpdateTime(startTime);
                    monitorWalletAssetFlows.setRemark(ret.getErrMsg().split(",")[0]);
                    log.error("账户id：{},昵称：{},资金流水监控异常,ERROR MSG:{}",account.getId(),account.getEmail(),ret.toString());
                    msg.append("账户id：");
                    msg.append(account.getId());
                    msg.append("<br />");
                    msg.append(" 邮箱：");
                    msg.append(account.getEmail());
                    msg.append("<br />");
                    msg.append(" 异常内容：");
                    msg.append(ret.getErrMsg());
                    msg.append("<br /><br />");
                    // account.setStatus(1);
                    // accountService.updateByPrimaryKey(account);
                    monitorWalletAssetFlowsMapper.insert(monitorWalletAssetFlows);
                    msgRecordService.sendSysAlermEmail("zhangchunxi@titlisfin.com", "报警邮件-账户资金流水异常", msg.toString());
                    parameter.setValue("OFF");
                    log.info("关闭系统交易开关(SystemTradeSwitch)");
                    sysParameterService.updateByPrimaryKey(parameter);
                    break;
                }
                // 监控正常
                else
                {
                    monitorWalletAssetFlows.setMonitorStatus(true);
                    monitorWalletAssetFlows.setRemark("监控正常");
                    monitorWalletAssetFlows.setUpdateTime(endtime);
                    monitorWalletAssetFlowsMapper.insert(monitorWalletAssetFlows);
                }

            }
            else
            {
                startTime = monitorWalletAssetFlows.getUpdateTime();// updatetime记录监控时间段的开始时间
                MonitorWalletAssetFlowsResultModel ret = monitorWalletAssetFlows(account.getId(), GlobalConst.CURRENCY_PKR, startTime, endtime);
                // 监控异常
                if(ret != null && ret.getErrCnt()>0)
                {
                    monitorWalletAssetFlows.setMonitorStatus(false);
                    monitorWalletAssetFlows.setUpdateTime(startTime);
                    monitorWalletAssetFlows.setRemark(ret.getErrMsg().split(",")[0]);
                    log.error("账户id：{},昵称：{},资金流水监控异常,ERROR MSG:{}",account.getLat(),account.getAccountName(),ret.toString());
                    msg.append("账户id：");
                    msg.append(account.getId());
                    msg.append("<br />");
                    msg.append(" 邮箱：");
                    msg.append(account.getEmail());
                    msg.append("<br />");
                    msg.append(" 异常内容：");
                    msg.append(ret.getErrMsg());
                    msg.append("<br /><br />");
                    // account.setStatus(1);
                    // accountService.updateByPrimaryKey(account);
                    msgRecordService.sendSysAlermEmail("zhangchunxi@titlisfin.com", "报警邮件-账户资金流水异常", msg.toString());
                    parameter.setValue("OFF");
                    log.info("关闭系统交易开关(SystemTradeSwitch)");
                    sysParameterService.updateByPrimaryKey(parameter);
                    monitorWalletAssetFlows.setLastMonitorTime(endtime);
                    monitorWalletAssetFlows.setUpdateTime(startTime);
                    monitorWalletAssetFlowsMapper.updateByPrimaryKey(monitorWalletAssetFlows);
                    break;
                }
                // 监控正常
                else
                {
                    if(monitorWalletAssetFlows.getMonitorStatus()) {
                        monitorWalletAssetFlows.setMonitorStatus(true);
                        monitorWalletAssetFlows.setRemark("监控正常");
                        monitorWalletAssetFlows.setLastMonitorTime(endtime);
                        monitorWalletAssetFlows.setUpdateTime(endtime);
                        monitorWalletAssetFlowsMapper.updateByPrimaryKey(monitorWalletAssetFlows);
                    }
                    else
                    {
                        // 之前就存在問題
                        log.error("账户id：{},昵称：{},资金流水监控异常,ERROR MSG:{}",account.getId(),account.getEmail(),monitorWalletAssetFlows.getRemark());
                    }
                }
            }
        }
    }
}
