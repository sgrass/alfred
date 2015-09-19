package com.alfred.vo;

import java.math.BigDecimal;
public class OrderCharts {
	
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal total;
    private String time;
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public String getTime() {
		return time;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
    
    
	
	
	
	

}
