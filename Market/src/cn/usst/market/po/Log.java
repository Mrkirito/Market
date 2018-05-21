package cn.usst.market.po;

public class Log {
	private Integer id;
	private String content;
	private String operateDate;
	private Integer memberId;
	private Integer quarter;
	private Integer submitTag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public Integer getSubmitTag() {
		return submitTag;
	}
	public void setSubmitTag(Integer submitTag) {
		this.submitTag = submitTag;
	}
	public Log(String content, Integer memberId, Integer quarter) {
		this.content = content;
		this.memberId = memberId;
		this.quarter = quarter;
	}
	
	public Log(){
		
	}
}
