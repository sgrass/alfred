package com.alfred.vo;

import java.math.BigDecimal;

public class SessionOrder {
	
	private BigDecimal firsePercent;
	private BigDecimal secoundPercent;
	private BigDecimal thirdPercent;
	private BigDecimal total;
	public BigDecimal getFirsePercent() {
		return firsePercent;
	}
	public BigDecimal getSecoundPercent() {
		return secoundPercent;
	}
	public BigDecimal getThirdPercent() {
		return thirdPercent;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setFirsePercent(BigDecimal firsePercent) {
		this.firsePercent = firsePercent;
	}
	public void setSecoundPercent(BigDecimal secoundPercent) {
		this.secoundPercent = secoundPercent;
	}
	public void setThirdPercent(BigDecimal thirdPercent) {
		this.thirdPercent = thirdPercent;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
