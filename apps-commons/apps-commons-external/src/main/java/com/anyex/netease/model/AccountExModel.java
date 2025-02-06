package com.anyex.netease.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户扩展字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountExModel {

    Integer  members;// 好友数量
    Integer  state ;//默认 0 头像左上角点的状态 0：公开 ； 1 ：同组可见 ；2：好友；3 ：自己可见

    @JsonProperty("post_state")
    Integer  postState ;// = 0 默认 1：档案我的 2：叽咕我的 3：播发我的
}
