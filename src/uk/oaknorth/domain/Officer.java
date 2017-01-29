package uk.oaknorth.domain;

import java.util.List;
import java.util.Map;

public class Officer {
	private List<Map<String,Object>> items;
	private int items_per_page;
	private String kind;
	private int start_index;
	private int total_results;
	private int page_number;
	public int getPage_number() {
		return page_number;
	}
	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}
	public List<Map<String, Object>> getItems() {
		return items;
	}
	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}
	public int getItems_per_page() {
		return items_per_page;
	}
	public void setItems_per_page(int items_per_page) {
		this.items_per_page = items_per_page;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getStart_index() {
		return start_index;
	}
	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}
	public int getTotal_results() {
		return total_results;
	}
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
	
}
