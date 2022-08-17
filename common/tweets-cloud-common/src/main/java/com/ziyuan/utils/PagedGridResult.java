package com.ziyuan.utils;

import java.util.List;

/**
 * @Title: PagedGridResult.java
 * @Package com.ziyuan.utils
 * @Description: Pagination response data
 */
public class PagedGridResult {

	private int page;            // current page
	private int total;            // total pages
	private long records;        // total records
	private List<?> rows;        // current page data in list

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
