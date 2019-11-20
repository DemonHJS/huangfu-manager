package cn.huangfu.common.bean;

import java.util.List;

public class Page {
	private Integer page;
	private Integer size;
	private Long total;
	private List<?> data;

	public Page() {
		super();
	}

	public Page(Integer page, Integer size, Long total, List<?> data) {
		super();
		this.page = page;
		this.size = size;
		this.total = total;
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
