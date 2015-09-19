package com.alfred.vo;

import java.util.List;

import com.alfred.model.CashInOut;
import com.alfred.model.ReportDaySales;
import com.alfred.model.ReportDayTax;
import com.alfred.model.ReportHourly;
import com.alfred.model.ReportPluDayItem;
import com.alfred.model.ReportPluDayModifier;
import com.alfred.model.UserTimeSheet;

public class ReportParseVO {
	
	private ReportDaySales reportDaySales;
	
	private List<ReportDayTax> reportDayTaxs;
	
	private List<ReportPluDayItem> reportPluDayItems;
	
	private List<ReportPluDayModifier> reportPluDayModifiers;
	
	private List<ReportHourly> reportHourlys;
	
	private List<CashInOut> cashInOuts;

	private List<UserTimeSheet> userTimeSheets;

	public ReportDaySales getReportDaySales() {
		return reportDaySales;
	}

	public void setReportDaySales(ReportDaySales reportDaySales) {
		this.reportDaySales = reportDaySales;
	}

	public List<ReportDayTax> getReportDayTaxs() {
		return reportDayTaxs;
	}

	public void setReportDayTaxs(List<ReportDayTax> reportDayTaxs) {
		this.reportDayTaxs = reportDayTaxs;
	}

	public List<ReportPluDayItem> getReportPluDayItems() {
		return reportPluDayItems;
	}

	public void setReportPluDayItems(List<ReportPluDayItem> reportPluDayItems) {
		this.reportPluDayItems = reportPluDayItems;
	}

	public List<ReportPluDayModifier> getReportPluDayModifiers() {
		return reportPluDayModifiers;
	}

	public void setReportPluDayModifiers(List<ReportPluDayModifier> reportPluDayModifiers) {
		this.reportPluDayModifiers = reportPluDayModifiers;
	}

	public List<ReportHourly> getReportHourlys() {
		return reportHourlys;
	}

	public void setReportHourlys(List<ReportHourly> reportHourlys) {
		this.reportHourlys = reportHourlys;
	}

	public List<CashInOut> getCashInOuts() {
		return cashInOuts;
	}

	public void setCashInOuts(List<CashInOut> cashInOuts) {
		this.cashInOuts = cashInOuts;
	}

	public List<UserTimeSheet> getUserTimeSheets() {
		return userTimeSheets;
	}

	public void setUserTimeSheets(List<UserTimeSheet> userTimeSheets) {
		this.userTimeSheets = userTimeSheets;
	}
	
}
