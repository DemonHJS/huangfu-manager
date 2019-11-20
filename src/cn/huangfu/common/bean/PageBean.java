package cn.huangfu.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean {

	private Integer page;
	private Integer size;
	private Long total;
	private List<?> data;
	private transient String columns;
	private transient String fromWheres;
	private transient String groupOrOrders = "";
	private Map<String, Object> others = new HashMap<>();
	private boolean pageStatus = true;
	
	public PageBean() {
		super();
	}

	public PageBean(Integer page, Integer size, Long total, List<?> data) {
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

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getFromWheres() {
		return fromWheres;
	}

	public void setFromWheres(String fromWheres) {
		this.fromWheres = fromWheres;
	}

	public String getGroupOrOrders() {
		return groupOrOrders;
	}

	public void setGroupOrOrders(String groupOrOrders) {
		this.groupOrOrders = groupOrOrders;
	}

	public Map<String, Object> getOthers() {
		return others;
	}

	public void setOthers(Map<String, Object> others) {
		this.others = others;
	}

	public int getFirst() {
		return (this.page - 1) * this.size;
	}

	public String getSql() {
		return "select " + " " + columns + " " + fromWheres + " " + groupOrOrders;
	}

	public String getCountSql() {
		if(groupOrOrders.toLowerCase().indexOf("group")>-1||columns.toLowerCase().indexOf("distinct")>-1){
			return "select count(1) from (select " + " " + columns + " " + fromWheres + " " + groupOrOrders + ")";
		}else {
			return "select count(1) " + fromWheres;
		}
	}

	public void put(String key, Object value) {
		others.put(key, value);
	}

	public Page toPage() {
		return new Page(page, size, total, data);
	}

	public boolean isPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(boolean pageStatus) {
		this.pageStatus = pageStatus;
	}

	
	
}
