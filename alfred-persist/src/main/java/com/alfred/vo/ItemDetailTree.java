package com.alfred.vo;

public class ItemDetailTree {
	private String label;
	private Integer id;
	private String itemDesc;
	private Integer isPack;
	private Integer isTakeout;
	private  Integer price;
	
	public String getLabel() {
		return label;
	}
	public Integer getId() {
		return id;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public Integer getIsPack() {
		return isPack;
	}
	public Integer getIsTakeout() {
		return isTakeout;
	}
	public Integer getPrice() {
		return price;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public void setIsPack(Integer isPack) {
		this.isPack = isPack;
	}
	public void setIsTakeout(Integer isTakeout) {
		this.isTakeout = isTakeout;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
