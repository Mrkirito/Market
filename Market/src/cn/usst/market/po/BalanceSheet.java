package cn.usst.market.po;

public class BalanceSheet {
    private Integer id;

    private Integer companyId;

    private Integer quarter;

    private Float huobi;

    private Float cunkuan;

    private Float lixiCollection;

    private Float cunhuo;

    private Float zichan;

    private Float daikuanEmergency;

    private Float lixiPay;

    private Float daikuanNormal;

    private Float guben;
    
    private Float capitalReserve;

    public Float getCapitalReserve() {
		return capitalReserve;
	}

	public void setCapitalReserve(Float capitalReserve) {
		this.capitalReserve = capitalReserve;
	}

	private Float liucun;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Float getHuobi() {
        return huobi;
    }

    public void setHuobi(Float huobi) {
        this.huobi = huobi;
    }

    public Float getCunkuan() {
        return cunkuan;
    }

    public void setCunkuan(Float cunkuan) {
        this.cunkuan = cunkuan;
    }

    public Float getLixiCollection() {
        return lixiCollection;
    }

    public void setLixiCollection(Float lixiCollection) {
        this.lixiCollection = lixiCollection;
    }

    public Float getCunhuo() {
        return cunhuo;
    }

    public void setCunhuo(Float cunhuo) {
        this.cunhuo = cunhuo;
    }

    public Float getZichan() {
        return zichan;
    }

    public void setZichan(Float zichan) {
        this.zichan = zichan;
    }

    public Float getDaikuanEmergency() {
        return daikuanEmergency;
    }

    public void setDaikuanEmergency(Float daikuanEmergency) {
        this.daikuanEmergency = daikuanEmergency;
    }

    public Float getLixiPay() {
        return lixiPay;
    }

    public void setLixiPay(Float lixiPay) {
        this.lixiPay = lixiPay;
    }

    public Float getDaikuanNormal() {
        return daikuanNormal;
    }

    public void setDaikuanNormal(Float daikuanNormal) {
        this.daikuanNormal = daikuanNormal;
    }

    public Float getGuben() {
        return guben;
    }

    public void setGuben(Float guben) {
        this.guben = guben;
    }

    public Float getLiucun() {
        return liucun;
    }

    public void setLiucun(Float liucun) {
        this.liucun = liucun;
    }
    
    public Float getLiuDongZiChan(){
    	return huobi+cunkuan+cunhuo;
    }
    
    public Float getTotalZiChan(){
    	return huobi+cunkuan+cunhuo+zichan;
    }
    
    public Float getLiuDongFuzhai(){
    	return daikuanEmergency+lixiPay;
    }
    
    public Float getTotalFuZhai(){
    	return getLiuDongFuzhai()+daikuanNormal;
    }
    
    public Float getOwner(){
    	return guben+liucun+capitalReserve;
    }
    
    public Float getOwnerAndFuZhai(){
    	return getTotalFuZhai()+getOwner();
    }
}