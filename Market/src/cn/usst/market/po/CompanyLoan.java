package cn.usst.market.po;

public class CompanyLoan {
	 private Integer id;

	    private Integer companyId;

	    private Integer quarter;

	    private Float loanLast;

	    private Float getNum;

	    private Float returnNum;

	    private Float lilv;

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

		public Float getLoanLast() {
			return loanLast;
		}

		public void setLoanLast(Float loanLast) {
			this.loanLast = loanLast;
		}

		public Float getGetNum() {
			return getNum;
		}

		public void setGetNum(Float getNum) {
			this.getNum = getNum;
		}

		public Float getReturnNum() {
			return returnNum;
		}

		public void setReturnNum(Float returnNum) {
			this.returnNum = returnNum;
		}

		public Float getLilv() {
			return lilv;
		}

		public void setLilv(Float lilv) {
			this.lilv = lilv;
		}
}