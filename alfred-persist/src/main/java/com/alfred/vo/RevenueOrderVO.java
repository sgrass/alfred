package com.alfred.vo;

import java.math.BigDecimal;

public class RevenueOrderVO {
	
	private String revName;
    private BigDecimal total;
	public String getRevName() {
		return revName;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setRevName(String revName) {
		this.revName = revName;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

    

}
