package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportDaySales {
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

    private Long totalBalancePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName == null ? null : restaurantName.trim();
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public BigDecimal getItemSales() {
        return itemSales;
    }

    public void setItemSales(BigDecimal itemSales) {
        this.itemSales = itemSales;
    }

    public Integer getItemSalesQty() {
        return itemSalesQty;
    }

    public void setItemSalesQty(Integer itemSalesQty) {
        this.itemSalesQty = itemSalesQty;
    }

    public BigDecimal getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(BigDecimal discountPer) {
        this.discountPer = discountPer;
    }

    public Integer getDiscountPerQty() {
        return discountPerQty;
    }

    public void setDiscountPerQty(Integer discountPerQty) {
        this.discountPerQty = discountPerQty;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getDiscountQty() {
        return discountQty;
    }

    public void setDiscountQty(Integer discountQty) {
        this.discountQty = discountQty;
    }

    public BigDecimal getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(BigDecimal discountAmt) {
        this.discountAmt = discountAmt;
    }

    public BigDecimal getFocItem() {
        return focItem;
    }

    public void setFocItem(BigDecimal focItem) {
        this.focItem = focItem;
    }

    public Integer getFocItemQty() {
        return focItemQty;
    }

    public void setFocItemQty(Integer focItemQty) {
        this.focItemQty = focItemQty;
    }

    public BigDecimal getFocBill() {
        return focBill;
    }

    public void setFocBill(BigDecimal focBill) {
        this.focBill = focBill;
    }

    public Integer getFocBillQty() {
        return focBillQty;
    }

    public void setFocBillQty(Integer focBillQty) {
        this.focBillQty = focBillQty;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Integer getCashQty() {
        return cashQty;
    }

    public void setCashQty(Integer cashQty) {
        this.cashQty = cashQty;
    }

    public BigDecimal getNets() {
        return nets;
    }

    public void setNets(BigDecimal nets) {
        this.nets = nets;
    }

    public Integer getNetsQty() {
        return netsQty;
    }

    public void setNetsQty(Integer netsQty) {
        this.netsQty = netsQty;
    }

    public BigDecimal getVisa() {
        return visa;
    }

    public void setVisa(BigDecimal visa) {
        this.visa = visa;
    }

    public Integer getVisaQty() {
        return visaQty;
    }

    public void setVisaQty(Integer visaQty) {
        this.visaQty = visaQty;
    }

    public BigDecimal getMc() {
        return mc;
    }

    public void setMc(BigDecimal mc) {
        this.mc = mc;
    }

    public Integer getMcQty() {
        return mcQty;
    }

    public void setMcQty(Integer mcQty) {
        this.mcQty = mcQty;
    }

    public BigDecimal getAmex() {
        return amex;
    }

    public void setAmex(BigDecimal amex) {
        this.amex = amex;
    }

    public Integer getAmexQty() {
        return amexQty;
    }

    public void setAmexQty(Integer amexQty) {
        this.amexQty = amexQty;
    }

    public BigDecimal getJbl() {
        return jbl;
    }

    public void setJbl(BigDecimal jbl) {
        this.jbl = jbl;
    }

    public Integer getJblQty() {
        return jblQty;
    }

    public void setJblQty(Integer jblQty) {
        this.jblQty = jblQty;
    }

    public BigDecimal getUnionPay() {
        return unionPay;
    }

    public void setUnionPay(BigDecimal unionPay) {
        this.unionPay = unionPay;
    }

    public Integer getUnionPayQty() {
        return unionPayQty;
    }

    public void setUnionPayQty(Integer unionPayQty) {
        this.unionPayQty = unionPayQty;
    }

    public BigDecimal getDiner() {
        return diner;
    }

    public void setDiner(BigDecimal diner) {
        this.diner = diner;
    }

    public Integer getDinerQty() {
        return dinerQty;
    }

    public void setDinerQty(Integer dinerQty) {
        this.dinerQty = dinerQty;
    }

    public BigDecimal getHoldld() {
        return holdld;
    }

    public void setHoldld(BigDecimal holdld) {
        this.holdld = holdld;
    }

    public Integer getHoldldQty() {
        return holdldQty;
    }

    public void setHoldldQty(Integer holdldQty) {
        this.holdldQty = holdldQty;
    }

    public BigDecimal getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(BigDecimal totalCard) {
        this.totalCard = totalCard;
    }

    public Integer getTotalCardQty() {
        return totalCardQty;
    }

    public void setTotalCardQty(Integer totalCardQty) {
        this.totalCardQty = totalCardQty;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public Integer getTotalCashQty() {
        return totalCashQty;
    }

    public void setTotalCashQty(Integer totalCashQty) {
        this.totalCashQty = totalCashQty;
    }

    public BigDecimal getTotalBillVoid() {
        return totalBillVoid;
    }

    public void setTotalBillVoid(BigDecimal totalBillVoid) {
        this.totalBillVoid = totalBillVoid;
    }

    public Integer getTotalBillVoidQty() {
        return totalBillVoidQty;
    }

    public void setTotalBillVoidQty(Integer totalBillVoidQty) {
        this.totalBillVoidQty = totalBillVoidQty;
    }

    public BigDecimal getTotalVoid() {
        return totalVoid;
    }

    public void setTotalVoid(BigDecimal totalVoid) {
        this.totalVoid = totalVoid;
    }

    public Integer getTotalVoidQty() {
        return totalVoidQty;
    }

    public void setTotalVoidQty(Integer totalVoidQty) {
        this.totalVoidQty = totalVoidQty;
    }

    public BigDecimal getNettSales() {
        return nettSales;
    }

    public void setNettSales(BigDecimal nettSales) {
        this.nettSales = nettSales;
    }

    public Integer getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(Integer totalBills) {
        this.totalBills = totalBills;
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    public Integer getFirstReceipt() {
        return firstReceipt;
    }

    public void setFirstReceipt(Integer firstReceipt) {
        this.firstReceipt = firstReceipt;
    }

    public Integer getLastReceipt() {
        return lastReceipt;
    }

    public void setLastReceipt(Integer lastReceipt) {
        this.lastReceipt = lastReceipt;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getPersonQty() {
        return personQty;
    }

    public void setPersonQty(Integer personQty) {
        this.personQty = personQty;
    }

    public Long getTotalBalancePrice() {
        return totalBalancePrice;
    }

    public void setTotalBalancePrice(Long totalBalancePrice) {
        this.totalBalancePrice = totalBalancePrice;
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