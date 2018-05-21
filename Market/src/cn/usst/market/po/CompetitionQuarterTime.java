package cn.usst.market.po;

public class CompetitionQuarterTime {
	private int id;
	
	private int quarterNumber;
	
	private String startTime;
	
	private String endTime;
	
	private int competitionId;
	
	private String isAllowOverdueSubmit;

	public String getIsAllowOverdueSubmit() {
		return isAllowOverdueSubmit;
	}

	public void setIsAllowOverdueSubmit(String isAllowOverdueSubmit) {
		this.isAllowOverdueSubmit = isAllowOverdueSubmit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuarterNumber() {
		return quarterNumber;
	}

	public void setQuarterNumber(int quarterNumber) {
		this.quarterNumber = quarterNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	
}
