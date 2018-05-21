package cn.usst.market.po;

/**
 * @author 陈立阳
 * @date 2018/5/10 22:52
 */
public class StateOfSign {
    private int isSubmit;
    private int companyId;
    private int quarter;

    public int getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(int isSubmit) {
        this.isSubmit = isSubmit;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }
}
