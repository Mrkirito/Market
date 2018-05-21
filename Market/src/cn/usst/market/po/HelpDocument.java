package cn.usst.market.po;

public class HelpDocument {
	private int id;
	private String titleLevelOne;
	private String titleLevelTwo;
	private String content;
	private int size;//非数据库属性列，表示一级标题无重复的个数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleLevelOne() {
		return titleLevelOne;
	}
	public void setTitleLevelOne(String titleLevelOne) {
		this.titleLevelOne = titleLevelOne;
	}
	public String getTitleLevelTwo() {
		return titleLevelTwo;
	}
	public void setTitleLevelTwo(String titleLevelTwo) {
		this.titleLevelTwo = titleLevelTwo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
