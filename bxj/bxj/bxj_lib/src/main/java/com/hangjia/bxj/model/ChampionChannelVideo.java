package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChampionChannelVideo implements Serializable {
    private ChampionChannel channel;
    private List<ChampionVideo> videoList;

    public ChampionChannel getChannel() {
        return channel;
    }

    public void setChannel(ChampionChannel channel) {
        this.channel = channel;
    }

    public List<ChampionVideo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<ChampionVideo> videoList) {
        this.videoList = videoList;
    }
}