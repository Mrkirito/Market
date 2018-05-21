package cn.usst.market.po;

public class Chat {
	private Integer id;
	private String sender;
	private String receiver;
	private String sendDate;
	private String content;
	private Integer competitionId;
	
	
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getCompetitionId() {
		return competitionId;
	}
	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
