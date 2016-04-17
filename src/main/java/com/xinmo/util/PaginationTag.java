package com.xinmo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xinmo.entity.Page;

public class PaginationTag<T> extends TagSupport {
	private static final long serialVersionUID = -930833808400179710L;
	private Page<T> pageObj;
	private String hrefLink;
	/**接收分页返回值的节点ID*/
	private String contentId;
	/**分页提交是否是ajax方式*/
	private boolean isAjax;
	/** 总记录数 */
	private Integer total;

	/** 当前页码 */
	private Integer num;

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
			sb.append("<div class='pagination'><ul>");
			if (pageObj.hasPreviousPage()) {
				sb.append("<li><a href='javascript:void(0);' onClick=\"goPage('"
						+ pageObj.getPreviousPageNo()
						+ "');return false;\"> Prev</a>");
			} else {
				sb.append("<li><a href='#'>Prev</a></li>");
			}
			for (int i = 0; i < pages.size(); i++) {
				if (pages.get(i).equals(pageObj.getCurrentPageNo())) {
					sb.append("<li class='active'><a href='#'>" + pages.get(i)
							+ "</a></li>");
				} else {
					sb.append("<li><a href='javascript:void(0);' onClick=\"goPage('"
							+ pages.get(i)
							+ "');return false;\">"
							+ pages.get(i) + "</a></li>");
				}
			}
			if (pageObj.hasNextPage()) {
				sb.append("<li><a href='javascript:void(0);' onClick=\"goPage('"
						+ pageObj.getNextPageNo()
						+ "');return false;\">Next </a></li>");
			} else {
				sb.append("<li><a href='#'>Next</a></li>");
			}
			sb.append("</ul></div></form>");
			buildSubmitJs(sb);
		}
		try {
			JspWriter out = pageContext.getOut();
			out.write(sb.toString());
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	private void buildSubmitJs(StringBuilder sb) {
		sb.append("<script type='text/javascript'>").append("function goPage(num){document.getElementById('_pageNo').value=num;");
		if(isAjax){
			sb.append("$.ajax({type: 'POST', url:'")
			.append(hrefLink)
			.append("',")
			.append(" data:$('#_pageForm').serialize(),")
			.append(" success: function(data) { $('")
			.append(contentId)
			.append("').html(data);  } });}");
		}else{
			sb.append("document.getElementById('_pageForm').submit();}");
		}
		sb.append("</script>");
		System.out.println(sb.toString());
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
		if (pageObj == null || pageObj.getParam() == null) {
			return "";
		}
		Set<String> keys = pageObj.getParam().keySet();
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = keys.iterator();
		String oneKey;
		while (iterator.hasNext()) {
			oneKey = iterator.next();
			Object val = pageObj.getParam().get(oneKey);
			if (val != null && !val.toString().equals("")) {
				sb.append("<input type='hidden' name='").append(oneKey)
						.append("' value='").append(val).append("'>");
			}
		}
		return sb.toString();
	}
	public void setHrefLink(String hrefLink) {
		this.hrefLink = hrefLink;
	}
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public boolean getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(boolean isAjax) {
		this.isAjax = isAjax;
	}

	
	
}