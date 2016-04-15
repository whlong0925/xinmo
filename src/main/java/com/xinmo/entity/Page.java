package com.xinmo.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Page<T> {

	private static Integer DEFAULT_PAGE_SIZE = 1;

	private Integer pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

	private Long start = 0L; // 当前页第一条数据在List中的位置,从0开始

	private Collection<T> data; // 当前页中存放的记录,类型一般为List

	private Long totalCount = 0L; // 总记录数

	private int pageNo = 1;
	
	private Map<String,Object> param;
	/**
	 * 构造方法，只构造空页
	 */

	public Page() {
		this(new Long(0), new Long(0), DEFAULT_PAGE_SIZE, new ArrayList<T>(0));
	}

	/**
	 * 默认构造方法
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public Page(Object pageNo, Object totalSize, Object pageSize,
			Collection<T> data) {
		if (pageSize != null) {
			this.pageSize = (Integer) pageSize;
		}
		if (pageNo != null) {
			Integer p = (Integer) pageNo;
			this.start = (p.longValue() - 1) * this.pageSize;
		}
		if (totalCount != null) {
			this.totalCount = Long.parseLong(totalSize.toString());
		}
		this.data = data;
	}

	public Page(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo").trim());//页码
		}
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize").trim());//每页显示几条	
		}
		Iterator<String> it = request.getParameterMap().keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String values[] = request.getParameterValues(key);
			if (values.length == 1) {
				map.put(key, values[0].trim());
			} else {
				map.put(key, values);
			}
		}
		this.start = (pageNo - 1L) * this.pageSize;
		map.put("start", start);
		map.put("pageSize", pageSize);
		this.param = map;
	}
	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Collection<?> getResult() {
		return data;
	}

	/**
	 * 设置数据集合
	 * 
	 * @param date
	 */
	public void setResult(Collection<T> date) {
		this.data = date;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获得此页的下一页页数
	 */
	public long getNextPageNo() {
		if (hasNextPage()) {
			return this.getCurrentPageNo() + 1;
		}

		return this.getCurrentPageNo();
	}

	/**
	 * 获得此页上一页的页数
	 */
	public long getPreviousPageNo() {
		if (hasPreviousPage()) {
			return this.getCurrentPageNo() - 1;
		}

		return this.getCurrentPageNo();
	}

	/**
	 * 
	 * 获取任一页第一条数据在数据集的位置
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}