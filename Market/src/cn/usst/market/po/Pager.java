package cn.usst.market.po;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {


	private static final long serialVersionUID = -1491063744331811441L;
	
	private int pageSize;//每页显示几条数据
	private int currentPage;//当前页的数据
	private int totalRecord;//总记录数
	private int totalPage; //总页数
	private List<T> dataList;//要显示的数据
	
	public Pager(){}
	
	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}
	
	public Pager(int pageNum,int pageSize,List<T> sourceList){
		if(sourceList==null)
		{
			return;
		}
		this.totalRecord=sourceList.size();
		
		this.pageSize=pageSize;
		
		this.totalPage=this.totalRecord/this.pageSize;
		if(this.totalRecord%this.pageSize!=0){
			this.totalPage++;
		}
		//当前第几页数据
		if(this.totalPage<pageNum){
			this.currentPage=this.totalPage;
		}else{
			this.currentPage=pageNum;
		}
		int fromIndex=this.pageSize*(this.currentPage-1);
		int toIndex;
		//可用多目运算符优化代码
		
		/*
		 * int toIndex=this.pageSize*this.currentPage>this.totalRecord?this.totalRecord:this.pageSize*this.currentPage
		 */
		if(this.pageSize*this.currentPage>this.totalRecord){
			toIndex=this.totalRecord;
		}else{
			toIndex=this.pageSize*this.currentPage;
		}
		this.dataList=sourceList.subList(fromIndex, toIndex);

	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
}
