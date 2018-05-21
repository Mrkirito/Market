package cn.usst.market.po;

public class CompanyProduct {
    private Integer id;

    private Integer companyId;

    private Integer quarter;
    
    private String name;

    private String detail;

    private Integer cost;
    
    private String type;
    
   

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public Integer getShengChanCost(int num,int cost){
		int costSum=0;
		if(num<=100){
			costSum=(int)(cost*330/(100*Math.log(5)));
		}else if(100<num&&num<=300){
			costSum=(int)(2*cost*330/(num*Math.log(5)));
		}
		else if(300<num&&num<=500){
			costSum=(int)(2.25*cost/Math.log((float)num/(float)65));
		}else if(500<num&&num<=1000){
			costSum=(int)(2.4*cost/Math.log((float)num/(float)65));
		}else if(1000<num&&num<=5000){
			costSum=(int)(3*cost/Math.log((float)num/(float)65));
		}
		return costSum;
	}
    
}