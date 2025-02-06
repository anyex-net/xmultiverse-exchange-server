package com.anyex.apps.exception;

/**
 * 工作流异常
 * note: 1.记录变更 by WangXiao
 *
 * @author WangXiao
 * @date 2023/02/15 15:13
 **/
public class ActivitiException extends RuntimeException {
    /**
     * 带报错信息的构造方法
     * @param message 错误消息
     * @author wangxiao
     * @date 2023/02/15 15:17
     */
    public ActivitiException(String message){
        super(message);
    }
}
