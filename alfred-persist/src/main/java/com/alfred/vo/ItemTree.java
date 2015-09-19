package com.alfred.vo;

import java.util.List;

public class ItemTree {
	private String label;
	private String id;
	private List<ItemDetailTree> children;
	public String getLabel() {
		return label;
	}
	public String getId() {
		return id;
	}
	public List<ItemDetailTree> getChildren() {
		return children;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setId(String id) {
		this.id = "i_"+id;
	}
	public void setChildren(List<ItemDetailTree> children) {
		this.children = children;
	}
	

	
	
	
}
