package com.anyex.netease.model;

import java.util.List;

public class GroupInfoModel {

    /**
     * tname : aa
     * announcement : aa
     * owner : v4
     * maxusers : 50
     * joinmode : 1
     * tid : 3083
     * intro : test
     * size : 3
     * custom :
     * mute : true
     * createtime : 1506652312445
     * updatetime : 1521082125400
     * admins : ["v1"]
     * members : ["v1","v2"]
     */

    private String tname;
    private String announcement;
    private String owner;
    private int maxusers;
    private int joinmode;
    private Long tid;
    private String intro;
    private int size;
    private String custom;
    private boolean mute;
    private long createtime;
    private long updatetime;
    private List<String> admins;
    private List<String> members;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public int getJoinmode() {
        return joinmode;
    }

    public void setJoinmode(int joinmode) {
        this.joinmode = joinmode;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
