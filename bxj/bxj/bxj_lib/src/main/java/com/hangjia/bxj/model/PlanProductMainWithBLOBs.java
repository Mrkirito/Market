package com.hangjia.bxj.model;

public class PlanProductMainWithBLOBs extends PlanProductMain {
	private static final long serialVersionUID = 5548289019255789469L;

	private String bbrqy;

    private String tbrqy;

    private String mc;

    private String cpts;

    public String getBbrqy() {
        return bbrqy;
    }

    public void setBbrqy(String bbrqy) {
        this.bbrqy = bbrqy == null ? null : bbrqy.trim();
    }

    public String getTbrqy() {
        return tbrqy;
    }

    public void setTbrqy(String tbrqy) {
        this.tbrqy = tbrqy == null ? null : tbrqy.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getCpts() {
        return cpts;
    }

    public void setCpts(String cpts) {
        this.cpts = cpts == null ? null : cpts.trim();
    }
}