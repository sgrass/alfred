package com.alfred.vo;

import java.util.List;

import com.alfred.model.BohHoldSettlement;
import com.alfred.model.CardsSettlement;
import com.alfred.model.NetsSettlement;
import com.alfred.model.NonChargableSettlement;
import com.alfred.model.Order;
import com.alfred.model.OrderBill;
import com.alfred.model.OrderDetail;
import com.alfred.model.OrderDetailTax;
import com.alfred.model.OrderModifier;
import com.alfred.model.OrderSplit;
import com.alfred.model.Payment;
import com.alfred.model.PaymentSettlement;
import com.alfred.model.RoundAmount;
import com.alfred.model.VoidSettlement;

public class OrderParseVO {
	
	private Order order;

	private RoundAmount roundAmount;
	
	private OrderBill orderBill;
	
	private List<OrderDetail> orderDetails;
	
	private List<OrderSplit> orderSplits;
	
	private List<OrderModifier> orderModifiers;
	
	private List<OrderDetailTax> orderDetailTaxs;
	
	private Payment payment;
	
	private List<PaymentSettlement> paymentSettlements;
	
	private List<CardsSettlement> cardsSettlements;
	
	private List<NetsSettlement> netsSettlements;
	
	private List<NonChargableSettlement> nonChargableSettlements;
	
	private List<BohHoldSettlement> bohHoldSettlements;
	
	private List<VoidSettlement> voidSettlements;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderBill getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBill orderBill) {
		this.orderBill = orderBill;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<OrderSplit> getOrderSplits() {
		return orderSplits;
	}

	public void setOrderSplits(List<OrderSplit> orderSplits) {
		this.orderSplits = orderSplits;
	}

	public List<OrderModifier> getOrderModifiers() {
		return orderModifiers;
	}

	public void setOrderModifiers(List<OrderModifier> orderModifiers) {
		this.orderModifiers = orderModifiers;
	}

	public List<OrderDetailTax> getOrderDetailTaxs() {
		return orderDetailTaxs;
	}

	public void setOrderDetailTaxs(List<OrderDetailTax> orderDetailTaxs) {
		this.orderDetailTaxs = orderDetailTaxs;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<PaymentSettlement> getPaymentSettlements() {
		return paymentSettlements;
	}

	public void setPaymentSettlements(List<PaymentSettlement> paymentSettlements) {
		this.paymentSettlements = paymentSettlements;
	}

	public List<CardsSettlement> getCardsSettlements() {
		return cardsSettlements;
	}

	public void setCardsSettlements(List<CardsSettlement> cardsSettlements) {
		this.cardsSettlements = cardsSettlements;
	}

	public List<NetsSettlement> getNetsSettlements() {
		return netsSettlements;
	}

	public void setNetsSettlements(List<NetsSettlement> netsSettlements) {
		this.netsSettlements = netsSettlements;
	}

	public List<NonChargableSettlement> getNonChargableSettlements() {
		return nonChargableSettlements;
	}

	public void setNonChargableSettlements(List<NonChargableSettlement> nonChargableSettlements) {
		this.nonChargableSettlements = nonChargableSettlements;
	}

	public List<BohHoldSettlement> getBohHoldSettlements() {
		return bohHoldSettlements;
	}

	public void setBohHoldSettlements(List<BohHoldSettlement> bohHoldSettlements) {
		this.bohHoldSettlements = bohHoldSettlements;
	}

	public List<VoidSettlement> getVoidSettlements() {
		return voidSettlements;
	}

	public void setVoidSettlements(List<VoidSettlement> voidSettlements) {
		this.voidSettlements = voidSettlements;
	}

	public RoundAmount getRoundAmount() {
		return roundAmount;
	}

	public void setRoundAmount(RoundAmount roundAmount) {
		this.roundAmount = roundAmount;
	}
	
  
}
