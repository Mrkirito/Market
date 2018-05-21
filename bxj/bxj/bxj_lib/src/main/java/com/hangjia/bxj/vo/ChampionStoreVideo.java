package com.hangjia.bxj.vo;

import com.hangjia.bxj.model.ChampionCollection;
import com.hangjia.bxj.model.ChampionModule;
import com.hangjia.bxj.model.ChampionVideo;

import java.io.Serializable;
import java.util.List;

public class ChampionStoreVideo implements Serializable {
    private Object fid;
    private ChampionVideo championVideo;

    public Object getFid() {
        return fid;
    }

    public void setFid(Object fid) {
        this.fid = fid;
    }

    public ChampionVideo getChampionVideo() {
        return championVideo;
    }

    public void setChampionVideo(ChampionVideo championVideo) {
        this.championVideo = championVideo;
    }
}