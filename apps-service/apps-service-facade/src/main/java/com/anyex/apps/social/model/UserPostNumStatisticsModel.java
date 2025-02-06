package com.anyex.apps.social.model;

import lombok.Data;

/**
 * 用户帖子统计
 */
@Data
public class UserPostNumStatisticsModel {

    /**
     * 公开的帖子数量
     */
    private Integer totalPublicPostNum;

    /**
     * 全部帖子数量
     */
    private Integer totalAllPostNum;
}
