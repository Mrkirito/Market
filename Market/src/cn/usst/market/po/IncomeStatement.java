package cn.usst.market.po;

public class IncomeStatement {
    private Integer id;

    private Integer companyId;

    private Integer quarter;

    private Float yingyeIncome;

    private Float lixiIncome;

    private Float yingyeCost;

    private Float fankuan;

    private Float yanfa;

    private Float guanggao;

    private Float salerCost;

    private Float salescenterCost;

    private Float salescenterWebCost;

    private Float baogao;

    private Float huoyun;

    private Float kucun;

    private Float excessCapacity;

    private Float zhejiu;

    private Float netmarketCost;

    private Float lixiCost;

    private Float techIncome;

    private Float qitaIncome;

    private Float techCost;

    private Float qitaCost;

    private Float taxCost;

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

    public Float getYingyeIncome() {
        return yingyeIncome;
    }

    public void setYingyeIncome(Float yingyeIncome) {
        this.yingyeIncome = yingyeIncome;
    }

    public Float getLixiIncome() {
        return lixiIncome;
    }

    public void setLixiIncome(Float lixiIncome) {
        this.lixiIncome = lixiIncome;
    }

    public Float getYingyeCost() {
        return yingyeCost;
    }

    public void setYingyeCost(Float yingyeCost) {
        this.yingyeCost = yingyeCost;
    }

    public Float getFankuan() {
        return fankuan;
    }

    public void setFankuan(Float fankuan) {
        this.fankuan = fankuan;
    }

    public Float getYanfa() {
        return yanfa;
    }

    public void setYanfa(Float yanfa) {
        this.yanfa = yanfa;
    }

    public Float getGuanggao() {
        return guanggao;
    }

    public void setGuanggao(Float guanggao) {
        this.guanggao = guanggao;
    }

    public Float getSalerCost() {
        return salerCost;
    }

    public void setSalerCost(Float salerCost) {
        this.salerCost = salerCost;
    }

    public Float getSalescenterCost() {
        return salescenterCost;
    }

    public void setSalescenterCost(Float salescenterCost) {
        this.salescenterCost = salescenterCost;
    }

    public Float getSalescenterWebCost() {
        return salescenterWebCost;
    }

    public void setSalescenterWebCost(Float salescenterWebCost) {
        this.salescenterWebCost = salescenterWebCost;
    }

    public Float getBaogao() {
        return baogao;
    }

    public void setBaogao(Float baogao) {
        this.baogao = baogao;
    }

    public Float getHuoyun() {
        return huoyun;
    }

    public void setHuoyun(Float huoyun) {
        this.huoyun = huoyun;
    }

    public Float getKucun() {
        return kucun;
    }

    public void setKucun(Float kucun) {
        this.kucun = kucun;
    }

    public Float getExcessCapacity() {
        return excessCapacity;
    }

    public void setExcessCapacity(Float excessCapacity) {
        this.excessCapacity = excessCapacity;
    }

    public Float getZhejiu() {
        return zhejiu;
    }

    public void setZhejiu(Float zhejiu) {
        this.zhejiu = zhejiu;
    }

    public Float getNetmarketCost() {
        return netmarketCost;
    }

    public void setNetmarketCost(Float netmarketCost) {
        this.netmarketCost = netmarketCost;
    }

    public Float getLixiCost() {
        return lixiCost;
    }

    public void setLixiCost(Float lixiCost) {
        this.lixiCost = lixiCost;
    }

    public Float getTechIncome() {
        return techIncome;
    }

    public void setTechIncome(Float techIncome) {
        this.techIncome = techIncome;
    }

    public Float getQitaIncome() {
        return qitaIncome;
    }

    public void setQitaIncome(Float qitaIncome) {
        this.qitaIncome = qitaIncome;
    }

    public Float getTechCost() {
        return techCost;
    }

    public void setTechCost(Float techCost) {
        this.techCost = techCost;
    }

    public Float getQitaCost() {
        return qitaCost;
    }

    public void setQitaCost(Float qitaCost) {
        this.qitaCost = qitaCost;
    }

    public Float getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(Float taxCost) {
        this.taxCost = taxCost;
    }
    
    public Float getYingYeTotalIncome(){
    	return yingyeIncome+lixiIncome;
    }
    
    public Float getYingYeTotalCost(){
    	return yingyeCost+fankuan+yanfa+guanggao+salerCost+salescenterCost+
    			salescenterWebCost+baogao+huoyun+kucun+excessCapacity+zhejiu+netmarketCost;
    }
    
    public Float getTotalLiRun(){
    	return getYingYeTotalIncome()-getYingYeTotalCost()+techIncome+qitaIncome-techCost-qitaCost;
    }
    
    public Float getJingLiRun(){
    	return getTotalLiRun()-taxCost;
    }
}