package cn.usst.market.po;

import java.io.Serializable;

public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageNow = 1; // ��ǰҳ��  
	  
    private int pageSize = 6; // ÿҳ��ʾ��¼������  
  
    private int totalCount; // �ܵļ�¼����  
  
    private int totalPageCount; // �ܵ�ҳ��  
  
    @SuppressWarnings("unused")  
    private int startPos; // ��ʼλ�ã���0��ʼ  
  
    @SuppressWarnings("unused")  
    private boolean hasFirst;// �Ƿ�����ҳ  
  
    @SuppressWarnings("unused")  
    private boolean hasPre;// �Ƿ���ǰһҳ  
  
    @SuppressWarnings("unused")  
    private boolean hasNext;// �Ƿ�����һҳ  
  
    @SuppressWarnings("unused")  
    private boolean hasLast;// �Ƿ������һҳ  
      
    /** 
     * ͨ�����캯�� ����  �ܼ�¼��  ��  ��ǰҳ 
     * @param totalCount 
     * @param pageNow 
     */  
    public Page(int totalCount, int pageNow) {  
        this.totalCount = totalCount;  
        this.pageNow = pageNow;  
    }  
      
    /** 
     * ȡ����ҳ������ҳ��=�ܼ�¼��/��ҳ�� 
     * @return 
     */  
    public int getTotalPageCount() {  
        totalPageCount = getTotalCount() / getPageSize();  
        return (totalCount % pageSize == 0) ? totalPageCount  
                : totalPageCount + 1;  
    }  
  
    public void setTotalPageCount(int totalPageCount) {  
        this.totalPageCount = totalPageCount;  
    }  
  
    public int getPageNow() {  
        return pageNow;  
    }  
  
    public void setPageNow(int pageNow) {  
        this.pageNow = pageNow;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getTotalCount() {  
        return totalCount;  
    }  
  
    public void setTotalCount(int totalCount) {  
        this.totalCount = totalCount;  
    }  
    /** 
     * ȡ��ѡ���¼�ĳ�ʼλ�� 
     * @return 
     */  
    public int getStartPos() {  
        return (pageNow - 1) * pageSize;  
    }  
  
    public void setStartPos(int startPos) {  
        this.startPos = startPos;  
    }  
  
    /** 
     * �Ƿ��ǵ�һҳ 
     * @return 
     */  
    public boolean isHasFirst() {  
        return (pageNow == 1) ? false : true;  
    }  
  
    public void setHasFirst(boolean hasFirst) {  
        this.hasFirst = hasFirst;  
    }  
    /** 
     * �Ƿ�����ҳ 
     * @return 
     */  
    public boolean isHasPre() {  
        // �������ҳ����ǰһҳ����Ϊ����ҳ�Ͳ��ǵ�һҳ  
        return isHasFirst() ? true : false;  
    }  
  
    public void setHasPre(boolean hasPre) {  
        this.hasPre = hasPre;  
    }  
    /** 
     * �Ƿ�����һҳ 
     * @return 
     */  
    public boolean isHasNext() {  
        // �����βҳ������һҳ����Ϊ��βҳ�����������һҳ  
        return isHasLast() ? true : false;  
    }  
  
    public void setHasNext(boolean hasNext) {  
        this.hasNext = hasNext;  
    }  
    /** 
     * �Ƿ���βҳ 
     * @return 
     */  
    public boolean isHasLast() {  
        // ����������һҳ����βҳ  
        return (pageNow == getTotalCount()) ? false : true;  
    }  
  
    public void setHasLast(boolean hasLast) {  
        this.hasLast = hasLast;  
    }  

}
