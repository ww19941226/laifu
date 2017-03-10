package com.laifu.module.entity;

import java.util.List;

public class PageData {
	private int pageSize;

	private int limit;

	private int pageNo;

	private int pageCount;

	private long recordCount;

	private int firstResult;

	private List<?> data;

	private int start;
	
	private Object attachInfo;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public PageData() {
		this.pageNo = 1;
		this.pageSize = 20;
		this.recordCount = 0;
		this.pageCount = 0;
	}

	public PageData(int pageSize) {
		this.pageNo = 1;
		this.pageSize = pageSize;
		this.recordCount = 0;
		this.pageCount = 0;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.pageCount = (int) (recordCount % pageSize == 0 ? recordCount
				/ pageSize : recordCount / pageSize + 1);
		this.recordCount = recordCount;
	}

	public PageData(int pageSize, int pageNo, int recordCount) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.pageCount = recordCount % pageSize == 0 ? recordCount / pageSize
				: recordCount / pageSize + 1;
		this.recordCount = recordCount;
	}

	public int getFirstResult() {
		if (pageNo <= 1)
			return 0;
		this.firstResult = (pageNo - 1) * pageSize;
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		if (start == 0)
			this.pageNo = 1;
		else {
			this.pageNo = start % this.getPageSize() == 0 ? start
					/ this.getPageSize() + 1 : start / this.getPageSize() + 2;
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Object getAttachInfo() {
		return attachInfo;
	}

	public void setAttachInfo(Object attachInfo) {
		this.attachInfo = attachInfo;
	}

}
