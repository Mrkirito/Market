package com.hangjia.bxj.vo;

import com.hangjia.bxj.model.ChampionModule;
import com.hangjia.bxj.model.ChampionVideo;

import java.io.Serializable;
import java.util.List;

public class ChampionModuleVideo implements Serializable {

    private ChampionModule championModule;
    private List<ChampionVideo> videoList;

    public ChampionModuleVideo() {}

    public ChampionModuleVideo(ChampionModule championModule, List<ChampionVideo> videoList) {
        this.championModule = championModule;
        this.videoList = videoList;
    }

    public ChampionModule getChampionModule() {
        return championModule;
    }

    public void setChampionModule(ChampionModule championModule) {
        this.championModule = championModule;
    }

    public List<ChampionVideo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<ChampionVideo> videoList) {
        this.videoList = videoList;
    }
}