package com.gdcp.dqs.query;

/**
 * MongoDB查询是否排序常量 （在mongo中，-1-倒序  1-正序）
 */
public enum MongoSortConstant {
	
	/**正序*/
	ASC_SORT(1),
	
	/**倒序*/
	DESC_SORT(-1);
	
	private int sortValue;
	
	private MongoSortConstant(int sortValue) {
		this.sortValue = sortValue;
	}
	
	/**
	 * 获取排序的值（在mongo中，-1-倒序  1-正序）
	 * @return
	 */
	public int getSortKey() {
		return this.sortValue;
	}
}
