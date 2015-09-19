package com.alfred.vo;

import java.util.List;
public class MainMuenTree {
	
	private String label;
	private String id;
	private List<ItemTree> children;
	public String getLabel() {
		return label;
	}
	public String getId() {
		return id;
	}
	public List<ItemTree> getChildren() {
		return children;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setId(String id) {
		this.id = "m_"+id;
	}
	public void setChildren(List<ItemTree> children) {
		this.children = children;
	}
	
	
		
	


}
