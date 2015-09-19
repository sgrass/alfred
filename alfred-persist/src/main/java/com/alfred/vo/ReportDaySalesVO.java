package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ReportDaySalesVO {

	private Integer id;

	private Integer restaurantId;

	private String restaurantName;

	private Integer revenueId;

	private String revenueName;

	private Date businessDate;

	private BigDecimal itemSales;

	private Integer itemSalesQty;

	private BigDecimal discountPer;

	private Integer discountPerQty;

	private BigDecimal discount;

	private Integer discountQty;

	private BigDecimal discountAmt;

	private BigDecimal focItem;

	private Integer focItemQty;

	private BigDecimal focBill;

	private Integer focBillQty;

	private BigDecimal totalSales;

	private BigDecimal cash;

	private Integer cashQty;

	private BigDecimal nets;

	private Integer netsQty;

	private BigDecimal visa;

	private Integer visaQty;

	private BigDecimal mc;

	private Integer mcQty;

	private BigDecimal amex;

	private Integer amexQty;

	private BigDecimal jbl;

	private Integer jblQty;

	private BigDecimal unionPay;

	private Integer unionPayQty;

	private BigDecimal diner;

	private Integer dinerQty;

	private BigDecimal holdld;

	private Integer holdldQty;

	private BigDecimal totalCard;

	private Integer totalCardQty;

	private BigDecimal totalCash;

	private Integer totalCashQty;

	private BigDecimal totalBillVoid;

	private Integer totalBillVoidQty;

	private BigDecimal totalVoid;

	private Integer totalVoidQty;

	private BigDecimal nettSales;

	private Integer totalBills;

	private Integer openCount;

	private Integer firstReceipt;

	private Integer lastReceipt;

	private BigDecimal totalTax;

	private Integer orderQty;

	private Integer personQty;

	public Integer getId() {
		return id;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public BigDecimal getItemSales() {
		return itemSales;
	}

	public Integer getItemSalesQty() {
		return itemSalesQty;
	}

	public BigDecimal getDiscountPer() {
		return discountPer;
	}

	public Integer getDiscountPerQty() {
		return discountPerQty;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public Integer getDiscountQty() {
		return discountQty;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public BigDecimal getFocItem() {
		return focItem;
	}

	public Integer getFocItemQty() {
		return focItemQty;
	}

	public BigDecimal getFocBill() {
		return focBill;
	}

	public Integer getFocBillQty() {
		return focBillQty;
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public Integer getCashQty() {
		return cashQty;
	}

	public BigDecimal getNets() {
		return nets;
	}

	public Integer getNetsQty() {
		return netsQty;
	}

	public BigDecimal getVisa() {
		return visa;
	}

	public Integer getVisaQty() {
		return visaQty;
	}

	public BigDecimal getMc() {
		return mc;
	}

	public Integer getMcQty() {
		return mcQty;
	}

	public BigDecimal getAmex() {
		return amex;
	}

	public Integer getAmexQty() {
		return amexQty;
	}

	public BigDecimal getJbl() {
		return jbl;
	}

	public Integer getJblQty() {
		return jblQty;
	}

	public BigDecimal getUnionPay() {
		return unionPay;
	}

	public Integer getUnionPayQty() {
		return unionPayQty;
	}

	public BigDecimal getDiner() {
		return diner;
	}

	public Integer getDinerQty() {
		return dinerQty;
	}

	public BigDecimal getHoldld() {
		return holdld;
	}

	public Integer getHoldldQty() {
		return holdldQty;
	}

	public BigDecimal getTotalCard() {
		return totalCard;
	}

	public Integer getTotalCardQty() {
		return totalCardQty;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public Integer getTotalCashQty() {
		return totalCashQty;
	}

	public BigDecimal getTotalBillVoid() {
		return totalBillVoid;
	}

	public Integer getTotalBillVoidQty() {
		return totalBillVoidQty;
	}

	public BigDecimal getTotalVoid() {
		return totalVoid;
	}

	public Integer getTotalVoidQty() {
		return totalVoidQty;
	}

	public BigDecimal getNettSales() {
		return nettSales;
	}

	public Integer getTotalBills() {
		return totalBills;
	}

	public Integer getOpenCount() {
		return openCount;
	}

	public Integer getFirstReceipt() {
		return firstReceipt;
	}

	public Integer getLastReceipt() {
		return lastReceipt;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public Integer getPersonQty() {
		return personQty;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setItemSales(BigDecimal itemSales) {
		this.itemSales = itemSales;
	}

	public void setItemSalesQty(Integer itemSalesQty) {
		this.itemSalesQty = itemSalesQty;
	}

	public void setDiscountPer(BigDecimal discountPer) {
		this.discountPer = discountPer;
	}

	public void setDiscountPerQty(Integer discountPerQty) {
		this.discountPerQty = discountPerQty;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setDiscountQty(Integer discountQty) {
		this.discountQty = discountQty;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public void setFocItem(BigDecimal focItem) {
		this.focItem = focItem;
	}

	public void setFocItemQty(Integer focItemQty) {
		this.focItemQty = focItemQty;
	}

	public void setFocBill(BigDecimal focBill) {
		this.focBill = focBill;
	}

	public void setFocBillQty(Integer focBillQty) {
		this.focBillQty = focBillQty;
	}

	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public void setCashQty(Integer cashQty) {
		this.cashQty = cashQty;
	}

	public void setNets(BigDecimal nets) {
		this.nets = nets;
	}

	public void setNetsQty(Integer netsQty) {
		this.netsQty = netsQty;
	}

	public void setVisa(BigDecimal visa) {
		this.visa = visa;
	}

	public void setVisaQty(Integer visaQty) {
		this.visaQty = visaQty;
	}

	public void setMc(BigDecimal mc) {
		this.mc = mc;
	}

	public void setMcQty(Integer mcQty) {
		this.mcQty = mcQty;
	}

	public void setAmex(BigDecimal amex) {
		this.amex = amex;
	}

	public void setAmexQty(Integer amexQty) {
		this.amexQty = amexQty;
	}

	public void setJbl(BigDecimal jbl) {
		this.jbl = jbl;
	}

	public void setJblQty(Integer jblQty) {
		this.jblQty = jblQty;
	}

	public void setUnionPay(BigDecimal unionPay) {
		this.unionPay = unionPay;
	}

	public void setUnionPayQty(Integer unionPayQty) {
		this.unionPayQty = unionPayQty;
	}

	public void setDiner(BigDecimal diner) {
		this.diner = diner;
	}

	public void setDinerQty(Integer dinerQty) {
		this.dinerQty = dinerQty;
	}

	public void setHoldld(BigDecimal holdld) {
		this.holdld = holdld;
	}

	public void setHoldldQty(Integer holdldQty) {
		this.holdldQty = holdldQty;
	}

	public void setTotalCard(BigDecimal totalCard) {
		this.totalCard = totalCard;
	}

	public void setTotalCardQty(Integer totalCardQty) {
		this.totalCardQty = totalCardQty;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public void setTotalCashQty(Integer totalCashQty) {
		this.totalCashQty = totalCashQty;
	}

	public void setTotalBillVoid(BigDecimal totalBillVoid) {
		this.totalBillVoid = totalBillVoid;
	}

	public void setTotalBillVoidQty(Integer totalBillVoidQty) {
		this.totalBillVoidQty = totalBillVoidQty;
	}

	public void setTotalVoid(BigDecimal totalVoid) {
		this.totalVoid = totalVoid;
	}

	public void setTotalVoidQty(Integer totalVoidQty) {
		this.totalVoidQty = totalVoidQty;
	}

	public void setNettSales(BigDecimal nettSales) {
		this.nettSales = nettSales;
	}

	public void setTotalBills(Integer totalBills) {
		this.totalBills = totalBills;
	}

	public void setOpenCount(Integer openCount) {
		this.openCount = openCount;
	}

	public void setFirstReceipt(Integer firstReceipt) {
		this.firstReceipt = firstReceipt;
	}

	public void setLastReceipt(Integer lastReceipt) {
		this.lastReceipt = lastReceipt;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public void setPersonQty(Integer personQty) {
		this.personQty = personQty;
	}

	public Integer getRevenueId() {
		return revenueId;
	}

	public void setRevenueId(Integer revenueId) {
		this.revenueId = revenueId;
	}

	public String getRevenueName() {
		return revenueName;
	}

	public void setRevenueName(String revenueName) {
		this.revenueName = revenueName;
	}

	
}
