package com.alfred.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.BohHoldSettlementMapper;
import com.alfred.dao.CardsSettlementMapper;
import com.alfred.dao.CashInOutMapper;
import com.alfred.dao.InventoryRawMaterialMapper;
import com.alfred.dao.InventoryRecipeMaterialMapper;
import com.alfred.dao.NetsSettlementMapper;
import com.alfred.dao.NonChargableSettlementMapper;
import com.alfred.dao.OrderBillMapper;
import com.alfred.dao.OrderDetailMapper;
import com.alfred.dao.OrderDetailTaxMapper;
import com.alfred.dao.OrderMapper;
import com.alfred.dao.OrderModifierMapper;
import com.alfred.dao.OrderSplitMapper;
import com.alfred.dao.PaymentMapper;
import com.alfred.dao.PaymentSettlementMapper;
import com.alfred.dao.ReceiveMsgLogMapper;
import com.alfred.dao.ReportDaySalesMapper;
import com.alfred.dao.ReportDayTaxMapper;
import com.alfred.dao.ReportHourlyMapper;
import com.alfred.dao.ReportPluDayItemMapper;
import com.alfred.dao.ReportPluDayModifierMapper;
import com.alfred.dao.RoundAmountMapper;
import com.alfred.dao.UserTimeSheetMapper;
import com.alfred.dao.VoidSettlementMapper;
import com.alfred.model.BohHoldSettlement;
import com.alfred.model.CardsSettlement;
import com.alfred.model.CashInOut;
import com.alfred.model.InventoryRawMaterial;
import com.alfred.model.InventoryRecipeMaterial;
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
import com.alfred.model.ReceiveMsgLog;
import com.alfred.model.ReportDaySales;
import com.alfred.model.ReportDayTax;
import com.alfred.model.ReportHourly;
import com.alfred.model.ReportPluDayItem;
import com.alfred.model.ReportPluDayModifier;
import com.alfred.model.RoundAmount;
import com.alfred.model.UserTimeSheet;
import com.alfred.model.VoidSettlement;
import com.alfred.service.ReceiveMsgLogService;
import com.alfred.util.JsonUtil;
import com.alfred.vo.OrderParseVO;
import com.alfred.vo.ReportParseVO;

@Transactional
@Service("receiveMsgLogService")
public class ReceiveMsgLogServiceImpl implements ReceiveMsgLogService {

	@Autowired
	@Qualifier("receiveMsgLogMapper")
	private ReceiveMsgLogMapper receiveMsgLogMapper = null;
	
	@Autowired
	@Qualifier("orderMapper")
	private OrderMapper orderMapper = null;
	
	@Autowired
	@Qualifier("orderDetailMapper")
	private OrderDetailMapper orderDetailMapper = null;
	
	@Autowired
	@Qualifier("orderBillMapper")
	private OrderBillMapper orderBillMapper = null;
	
	@Autowired
	@Qualifier("orderDetailTaxMapper")
	private OrderDetailTaxMapper orderDetailTaxMapper = null;
	
	@Autowired
	@Qualifier("orderModifierMapper")
	private OrderModifierMapper orderModifierMapper = null;
	
	@Autowired
	@Qualifier("orderSplitMapper")
	private OrderSplitMapper orderSplitMapper = null;
	
	@Autowired
	@Qualifier("reportHourlyMapper")
	private ReportHourlyMapper reportHourlyMapper = null;
	
	
	@Autowired
	@Qualifier("paymentMapper")
	private PaymentMapper paymentMapper = null;
	
	
	@Autowired
	@Qualifier("paymentSettlementMapper")
	private PaymentSettlementMapper paymentSettlementMapper = null;
	
	
	@Autowired
	@Qualifier("cardsSettlementMapper")
	private CardsSettlementMapper cardsSettlementMapper = null;
	
	
	@Autowired
	@Qualifier("netsSettlementMapper")
	private NetsSettlementMapper netsSettlementMapper = null;
	
	@Autowired
	@Qualifier("nonChargableSettlementMapper")
	private NonChargableSettlementMapper nonChargableSettlementMapper = null;
	
	@Autowired
	@Qualifier("voidSettlementMapper")
	private VoidSettlementMapper voidSettlementMapper = null;
	
	@Autowired
	@Qualifier("bohHoldSettlementMapper")
	private BohHoldSettlementMapper bohHoldSettlementMapper = null;
	
	
	@Autowired
	@Qualifier("reportDaySalesMapper")
	private ReportDaySalesMapper reportDaySalesMapper = null;
	
	@Autowired
	@Qualifier("reportDayTaxMapper")
	private ReportDayTaxMapper reportDayTaxMapper = null;
	
	@Autowired
	@Qualifier("reportPluDayItemMapper")
	private ReportPluDayItemMapper reportPluDayItemMapper = null;
	
	@Autowired
	@Qualifier("reportPluDayModifierMapper")
	private ReportPluDayModifierMapper reportPluDayModifierMapper = null;
	
	@Autowired
	@Qualifier("roundAmountMapper")
	private RoundAmountMapper roundAmountMapper = null;
	
	@Autowired
	@Qualifier("cashInOutMapper")
	private CashInOutMapper cashInOutMapper = null;
	
	@Autowired
	@Qualifier("userTimeSheetMapper")
	private UserTimeSheetMapper userTimeSheetMapper = null;
	
	@Autowired
	@Qualifier("inventoryRecipeMaterialMapper")
	private InventoryRecipeMaterialMapper inventoryRecipeMaterialMapper = null;
	
	@Autowired
	@Qualifier("inventoryRawMaterialMapper")
	private InventoryRawMaterialMapper inventoryRawMaterialMapper = null;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLog.setSysCreateTime(new Date());
		receiveMsgLog.setSysUpdateTime(new Date());
		return receiveMsgLogMapper.insert(receiveMsgLog);
	}

	@Override
	public List<ReceiveMsgLog> selectByParam(ReceiveMsgLog receiveMsgLog) {
		return receiveMsgLogMapper.selectByParam(receiveMsgLog);
	}

	@Override
	public ReceiveMsgLog selectByPrimaryKey(Integer id) {
		return receiveMsgLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLog.setSysUpdateTime(new Date());
		return receiveMsgLogMapper.updateById(receiveMsgLog);
	}

	@Override
	public void parseOrder() throws Exception {
		ReceiveMsgLog param = new ReceiveMsgLog();
		param.setMsgType(CommonStatusConstant.RECEIVE_TYPE_ORDER);
		param.setStatus(CommonStatusConstant.NO_PROCESS);
		List<ReceiveMsgLog> list =  this.selectByParam(param);
		for (ReceiveMsgLog rml : list) {
			
			ReceiveMsgLog rmlUpdate = new ReceiveMsgLog();
			rmlUpdate.setId(rml.getId());
			rmlUpdate.setStatus(CommonStatusConstant.PROCESS_ERROR);
			rmlUpdate.setSysUpdateTime(new Date());
			this.updateById(rmlUpdate);
			
			Map<String, Class> classMap = new HashMap<String, Class>();
	    classMap.put("order", Order.class);
	    
	    classMap.put("roundAmount", RoundAmount.class);
	    
	    classMap.put("orderBill", OrderBill.class);
	    classMap.put("orderDetails", OrderDetail.class);
	    classMap.put("orderSplits", OrderSplit.class);
	    classMap.put("orderModifiers", OrderModifier.class);
	    classMap.put("orderDetailTaxs", OrderDetailTax.class);
	    
	    classMap.put("payment", Payment.class);
	    classMap.put("paymentSettlements", PaymentSettlement.class);
	    
	    classMap.put("cardsSettlements", CardsSettlement.class);
	    classMap.put("netsSettlements", NetsSettlement.class);
	    classMap.put("nonChargableSettlements", NonChargableSettlement.class);
	    classMap.put("bohHoldSettlements", BohHoldSettlement.class);
	    classMap.put("voidSettlements", VoidSettlement.class);
	    
	     
			OrderParseVO opv = JsonUtil.toBean(rml.getDataJson(), OrderParseVO.class, classMap);
			
			
			//处理订单相关 表数据
			Order order = opv.getOrder();
			order.setId(null);
			order.setSysCreateTime(new Date());
			order.setSysUpdateTime(new Date());
			orderMapper.insert(order);
			
			//处理订单金额的四舍五入记录
			RoundAmount ra = opv.getRoundAmount();
			ra.setId(null);
			ra.setOrderId(order.getId());
			ra.setSysCreateTime(new Date());
			ra.setSysUpdateTime(new Date());
			roundAmountMapper.insert(ra);
			
			//拆单的数据
			Map<Integer, Integer> orderSplitIdMap = new HashMap<Integer, Integer>();
			List<OrderSplit> orderSplitList = opv.getOrderSplits();
			if (orderSplitList!= null && orderSplitList.size() > 0) {
				for (OrderSplit os : orderSplitList) {
					Integer originOrderSplitId = os.getId();
					os.setId(null);
					os.setOrderId(order.getId());
					os.setSysCreateTime(new Date());
					os.setSysUpdateTime(new Date());
					orderSplitMapper.insert(os);
					orderSplitIdMap.put(originOrderSplitId, os.getId());
				}
			}
			
			//订单凭条的数据
			OrderBill orderBill = opv.getOrderBill();
			if (orderBill != null) {
				orderBill.setId(null);
				orderBill.setOrderId(order.getId());
				orderBill.setOrderSplitId(orderSplitIdMap.get(orderBill.getOrderSplitId()));
				orderBill.setSysCreateTime(new Date());
				orderBill.setSysUpdateTime(new Date());
				orderBillMapper.insert(orderBill);
			}
			
			//订单详情的数据
			Map<Integer, Integer> orderDetailIdMap = new HashMap<Integer, Integer>();
			List<OrderDetail> orderDetailList = opv.getOrderDetails();
			if (orderDetailList!= null && orderDetailList.size() > 0) {
				for (OrderDetail od : orderDetailList) {
					Integer orderDetailId = od.getId();
					od.setId(null);
					od.setOrderId(order.getId());
					od.setOrderSplitId(orderSplitIdMap.get(od.getOrderSplitId()));
					od.setSysCreateTime(new Date());
					od.setSysUpdateTime(new Date());
					orderDetailMapper.insert(od);
					orderDetailIdMap.put(orderDetailId, od.getId());
					
					/**
					 * 计算库存使用量 更新库存当前数量
					 * 1、根据itemId 查询菜是否有和进销存进行关联
					 * 2、计算使用数量*菜的数量=消耗原材料总量
					 * 3、更新rawMaterial当前总份数
					 */
					InventoryRecipeMaterial rmParam = new InventoryRecipeMaterial();
					rmParam.setItemId(od.getItemId());
					rmParam.setRestaurantId(rml.getRestaurantId());
					rmParam.setType(0);
					rmParam.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					List<InventoryRecipeMaterial> rmList = inventoryRecipeMaterialMapper.selectByParam(rmParam);
					if (rmList != null && rmList.size() > 0) {
						InventoryRecipeMaterial inventoryRecipeMaterial = rmList.get(0);
						InventoryRawMaterial material = new InventoryRawMaterial();
						material.setId(inventoryRecipeMaterial.getMaterialId());
						material.setQuantityCurrent(inventoryRecipeMaterial.getUseQty().multiply(new BigDecimal(od.getItemNum())));
						inventoryRawMaterialMapper.updateSubQtyById(material);
					}
				}
			}
			
			//订单详情的税的数据
			List<OrderDetailTax> orderDetailTaxList = opv.getOrderDetailTaxs();
			if (orderDetailTaxList!= null && orderDetailTaxList.size() > 0) {
				for (OrderDetailTax odt : orderDetailTaxList) {
					odt.setId(null);
					odt.setOrderId(order.getId());
					odt.setOrderDetailId(orderDetailIdMap.get(odt.getOrderDetailId()));
					odt.setSysCreateTime(new Date());
					odt.setSysUpdateTime(new Date());
					orderDetailTaxMapper.insert(odt);
				}
			}
			
			//菜的配料的数据
			List<OrderModifier> orderModifierList = opv.getOrderModifiers();
			if (orderModifierList!= null && orderModifierList.size() > 0) {
				for (OrderModifier om : orderModifierList) {
					om.setId(null);
					om.setOrderId(order.getId());
					om.setOrderDetailId(orderDetailIdMap.get(om.getOrderDetailId()));
					om.setSysCreateTime(new Date());
					om.setSysUpdateTime(new Date());
					orderModifierMapper.insert(om);
					
					/**
					 * 计算库存使用量 更新库存当前数量
					 * 1、根据modifierId 查询配料是否有和进销存进行关联
					 * 2、计算使用数量*配料的数量=消耗原材料总量
					 * 3、更新rawMaterial当前总份数
					 */
					InventoryRecipeMaterial mmParam = new InventoryRecipeMaterial();
					mmParam.setModifierId(om.getId());
					mmParam.setRestaurantId(rml.getRestaurantId());
					mmParam.setType(1);
					mmParam.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					List<InventoryRecipeMaterial> mmList = inventoryRecipeMaterialMapper.selectByParam(mmParam);
					if (mmList != null && mmList.size() > 0) {
						InventoryRecipeMaterial inventoryRecipeMaterial = mmList.get(0);
						InventoryRawMaterial material = new InventoryRawMaterial();
						material.setId(inventoryRecipeMaterial.getMaterialId());
						material.setQuantityCurrent(inventoryRecipeMaterial.getUseQty().multiply(new BigDecimal(om.getModifierNum())));
						inventoryRawMaterialMapper.updateSubQtyById(material);
					}
				}
			}
			
			
			//处理支付相关 表数据
			
			//支付记录
			Payment payment = opv.getPayment();
			if (payment != null) {
				payment.setId(null);
				payment.setOrderId(order.getId());
				payment.setOrderSplitId(orderSplitIdMap.get(orderBill.getOrderSplitId()));
				payment.setSysCreateTime(new Date());
				payment.setSysUpdateTime(new Date());
				paymentMapper.insert(payment);
			}
			
			//支付详情的数据
			Map<Integer, Integer> paymentSettIdMap = new HashMap<Integer, Integer>();
			List<PaymentSettlement> paymentSettList = opv.getPaymentSettlements();
			if (paymentSettList!= null && paymentSettList.size() > 0) {
				for (PaymentSettlement ps : paymentSettList) {
					Integer paymentSettId = ps.getId();
					ps.setId(null);
					ps.setPaymentId(payment.getId());
					ps.setSysCreateTime(new Date());
					ps.setSysUpdateTime(new Date());
					paymentSettlementMapper.insert(ps);
					paymentSettIdMap.put(paymentSettId, ps.getId());
				}
			}
			
			//赊账的数据
			List<BohHoldSettlement> bohList = opv.getBohHoldSettlements();
			if (bohList!= null && bohList.size() > 0) {
				for (BohHoldSettlement bhs : bohList) {
					bhs.setId(null);
					bhs.setOrderId(order.getId());
					bhs.setPaymentId(payment.getId());
					bhs.setPaymentSettId(paymentSettIdMap.get(bhs.getPaymentSettId()));
					bhs.setSysCreateTime(new Date());
					bhs.setSysUpdateTime(new Date());
					bohHoldSettlementMapper.insert(bhs);
				}
			}
			
			//免费的数据
			List<NonChargableSettlement> ncsList = opv.getNonChargableSettlements();
			if (ncsList!= null && ncsList.size() > 0) {
				for (NonChargableSettlement ncs : ncsList) {
					ncs.setId(null);
					ncs.setPaymentId(payment.getId());
					ncs.setPaymentSettId(paymentSettIdMap.get(ncs.getPaymentSettId()));
					ncs.setSysCreateTime(new Date());
					ncs.setSysUpdateTime(new Date());
					nonChargableSettlementMapper.insert(ncs);
				}
			}
			
			//卡支付的数据
			List<CardsSettlement> cardList = opv.getCardsSettlements();
			if (cardList!= null && cardList.size() > 0) {
				for (CardsSettlement card : cardList) {
					card.setId(null);
					card.setPaymentId(payment.getId());
					card.setPaymentSettId(paymentSettIdMap.get(card.getPaymentSettId()));
					card.setSysCreateTime(new Date());
					card.setSysUpdateTime(new Date());
					cardsSettlementMapper.insert(card);
				}
			}
			
			List<NetsSettlement> netsList = opv.getNetsSettlements();
			if (netsList!= null && netsList.size() > 0) {
				for (NetsSettlement nets : netsList) {
					nets.setId(null);
					nets.setPaymentId(payment.getId());
					nets.setPaymentSettId(paymentSettIdMap.get(nets.getPaymentSettId()));
					nets.setSysCreateTime(new Date());
					nets.setSysUpdateTime(new Date());
					netsSettlementMapper.insert(nets);
				}
			}
			
			//退单的数据
			List<VoidSettlement> voidList = opv.getVoidSettlements();
			if (voidList!= null && voidList.size() > 0) {
				for (VoidSettlement vs : voidList) {
					vs.setId(null);
					vs.setPaymentId(payment.getId());
					vs.setPaymentSettId(paymentSettIdMap.get(vs.getPaymentSettId()));
					vs.setSysCreateTime(new Date());
					vs.setSysUpdateTime(new Date());
					voidSettlementMapper.insert(vs);
				}
			}
			
			
			rmlUpdate.setStatus(CommonStatusConstant.PROCESSED);
			this.updateById(rmlUpdate);
			
		}
		
	}

	@Override
	public void parseReport() throws Exception {
		ReceiveMsgLog param = new ReceiveMsgLog();
		param.setMsgType(CommonStatusConstant.RECEIVE_TYPE_REPORT);
		param.setStatus(CommonStatusConstant.NO_PROCESS);
		List<ReceiveMsgLog> list =  this.selectByParam(param);
		
		for (ReceiveMsgLog rml : list) {
			ReceiveMsgLog rmlUpdate = new ReceiveMsgLog();
			rmlUpdate.setId(rml.getId());
			rmlUpdate.setStatus(CommonStatusConstant.PROCESS_ERROR);
			rmlUpdate.setSysUpdateTime(new Date());
			this.updateById(rmlUpdate);
			
			
			Map<String, Class> classMap = new HashMap<String, Class>();
	    classMap.put("reportDaySales", ReportDaySales.class);
	    classMap.put("reportDayTaxs", ReportDayTax.class);
	    classMap.put("reportPluDayItems", ReportPluDayItem.class);
	    classMap.put("reportPluDayModifiers", ReportPluDayModifier.class);
	    classMap.put("reportHourlys", ReportHourly.class);
	    classMap.put("cashInOuts", CashInOut.class);
	    classMap.put("userTimeSheets", UserTimeSheet.class);
	    
	     
	    ReportParseVO rpv = JsonUtil.toBean(rml.getDataJson(), ReportParseVO.class, classMap);
	    
	    //每天的销售报表数据
	    ReportDaySales reportDaySales = rpv.getReportDaySales();
	    reportDaySales.setId(null);
	    reportDaySalesMapper.insert(reportDaySales);
	    
	    
	    //每天的税收报表数据
	    List<ReportDayTax> reportDayTaxList = rpv.getReportDayTaxs();
			if (reportDayTaxList!= null && reportDayTaxList.size() > 0) {
				for (ReportDayTax rdt : reportDayTaxList) {
					rdt.setId(null);
					rdt.setDaySalesId(reportDaySales.getId());
					reportDayTaxMapper.insert(rdt);
				}
			}
			
			//每天卖出的菜报表数据
			List<ReportPluDayItem> reportPluDayItemList = rpv.getReportPluDayItems();
			if (reportPluDayItemList!= null && reportPluDayItemList.size() > 0) {
				for (ReportPluDayItem rpi : reportPluDayItemList) {
					rpi.setId(null);
					reportPluDayItemMapper.insert(rpi);
				}
			}
			
			//每天卖出的配料的报表数据
			List<ReportPluDayModifier> reportPluDmList = rpv.getReportPluDayModifiers();
			if (reportPluDmList!= null && reportPluDmList.size() > 0) {
				for (ReportPluDayModifier rpm : reportPluDmList) {
					rpm.setId(null);
					reportPluDayModifierMapper.insert(rpm);
				}
			}
			
			//小时报表数据
			List<ReportHourly> rhList = rpv.getReportHourlys();
			if (rhList!= null && rhList.size() > 0) {
				for (ReportHourly rh : rhList) {
					rh.setId(null);
					reportHourlyMapper.insert(rh);
				}
			}
			
			List<CashInOut> cashList = rpv.getCashInOuts();
			if (cashList != null && cashList.size() > 0) {
				for (CashInOut cash : cashList) {
					cash.setId(null);
					cash.setSysCreateTime(new Date());
					cashInOutMapper.insert(cash);
				}
			}
			
			List<UserTimeSheet> utsList = rpv.getUserTimeSheets();
			if (utsList != null && utsList.size() > 0) {
				for (UserTimeSheet uts : utsList) {
					userTimeSheetMapper.insert(uts);
				}
			}
			
			
			rmlUpdate.setStatus(CommonStatusConstant.PROCESSED);
			this.updateById(rmlUpdate);
		}
	}

	@Override
	public void test() throws Exception {
		ReceiveMsgLog param = new ReceiveMsgLog();
		param.setMsgType(CommonStatusConstant.RECEIVE_TYPE_REPORT_HOURLY);
		param.setStatus(CommonStatusConstant.NO_PROCESS);
		List<ReceiveMsgLog> list =  this.selectByParam(param);
		
		for (ReceiveMsgLog rml : list) {
			ReceiveMsgLog rmlUpdate = new ReceiveMsgLog();
			rmlUpdate.setId(rml.getId());
			rmlUpdate.setStatus(CommonStatusConstant.PROCESS_ERROR);
			rmlUpdate.setSysUpdateTime(new Date());
			this.updateById(rmlUpdate);
			
			Map<String, Class> classMap = new HashMap<String, Class>();
	    classMap.put("reportHourlys", ReportHourly.class);
	    
	     
	    
	    rmlUpdate.setStatus(CommonStatusConstant.PROCESSED);
			this.updateById(rmlUpdate);
		}
	}

}
