package com.xinmo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xinmo.entity.Page;

public class PaginationTag<T> extends TagSupport {
	private static final long serialVersionUID = -930833808400179710L;
	/** 处理分页的action path */
	private Page<T> pageObj;
	private String hrefLink;

	public void setHrefLink(String hrefLink) {
		this.hrefLink = hrefLink;
	}

	/** 页面数 */
	private Integer total;

	/** 当前页码 */
	private Integer num;

	/** 其他参数,key为键value为值。 */
	private Map<String, Object> otherParams;

	public void setOtherParams(Map<String, Object> otherParams) {
		this.otherParams = otherParams;
	}

	public void setPageObj(Page<T> pageObj) {
		this.pageObj = pageObj;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public int getPreviousPage() {
		if (num - 1 <= 0) {
			return 1;
		} else {
			return (num - 1);
		}
	}

	public int getNextPage() {
		if (num + 1 >= total) {
			return total;
		} else {
			return (num + 1);
		}
	}

	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		List<Long> pages = getPagedList();// 取得要显示的页码！
		if (pageObj == null) {
			return EVAL_PAGE;
		}
		if (pages.size() > 0) {
			sb.append("<form action='")
					.append(hrefLink)
					.append("' method='post' id='_pageForm'><input type='hidden' name='pageNo' id='_pageNo'/>")
					.append(initParam());
			if (pageObj.hasPreviousPage()) {
				sb.append("<a href='javascript:void(0);' onClick=\"goPage('"
						+ pageObj.getPreviousPageNo()
						+ "');return false;\"> <  Prev</a>");
			} else {
				sb.append("<span class='disabled'> <  Prev</span>");
			}
			for (int i = 0; i < pages.size(); i++) {
				if (pages.get(i).equals(pageObj.getCurrentPageNo())) {
					sb.append("<span class='current'>" + pages.get(i)
							+ "</span>");
				} else {
					sb.append("<a href='javascript:void(0);' onClick=\"goPage('"
							+ pages.get(i)
							+ "');return false;\">"
							+ pages.get(i) + "</a>");
				}
			}
			if (pageObj.hasNextPage()) {
				sb.append("<a href='javascript:void(0);' onClick=\"goPage('"
						+ pageObj.getNextPageNo()
						+ "');return false;\">Next  > </a>");
			} else {
				sb.append("<span class='disabled'>Next  > </span>");
			}
			sb.append("</form><script type='text/javascript'>function goPage(num){document.getElementById('_pageNo').value=num;document.getElementById('_pageForm').submit();}</script>");
		}
		try {
			JspWriter out = pageContext.getOut();
			out.write(sb.toString());
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}

	}

	// 得到要显示的页数。
	private List<Long> getPagedList() {
		List<Long> pages = new ArrayList<Long>();
		long start = 1;
		long end = 0;
		if (this.pageObj == null) {
			pages.add((long) 0);
			return pages;
		}
		if (this.pageObj.getTotalPageCount() <= 10) {
			start = 1;
			end = this.pageObj.getTotalPageCount();
		} else {
			if (this.pageObj.getCurrentPageNo() < 6) {
				start = 1;
				end = 10;
			} else if (this.pageObj.getCurrentPageNo() + 5 <= this.pageObj
					.getTotalPageCount()) {
				start = this.pageObj.getCurrentPageNo() - 4;
				end = this.pageObj.getCurrentPageNo() + 5;
			} else {
				start = this.pageObj.getTotalPageCount() - 9;
				end = this.pageObj.getTotalPageCount();
			}
		}
		for (long i = start; i <= end; i++) {
			pages.add(Long.valueOf(i));
		}
		return pages;
	}

	// 将传进来的参数拼成隐藏的input
	private String initParam() {
		if (otherParams == null) {
			return "";
		}
		Set<String> keys = otherParams.keySet();
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = keys.iterator();
		String oneKey;
		while (iterator.hasNext()) {
			oneKey = iterator.next();
			Object val = otherParams.get(oneKey);
			if (val != null && !val.toString().equals("")) {
				sb.append("<input type='hidden' name='").append(oneKey)
						.append("' value='").append(val).append("'>");
			}
		}
		return sb.toString();
	}
}