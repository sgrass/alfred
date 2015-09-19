package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.PrinterMapper;
import com.alfred.dao.RevenueCenterMapper;
import com.alfred.model.Printer;
import com.alfred.model.RevenueCenter;
import com.alfred.service.PrinterService;
import com.alfred.vo.PrinterGroupVO;

@Transactional
@Service("printerService")
public class PrinterServiceImpl implements PrinterService {

	private static Log log = LogFactory.getLog(PrinterServiceImpl.class);

	@Autowired
	@Qualifier("printerMapper")
	private PrinterMapper printerMapper = null;

	@Autowired
	@Qualifier("revenueCenterMapper")
	private RevenueCenterMapper revenueCenterMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		// 更改revenueCenter数据

		int res = 1;
		// TODO Auto-generated method stub
		updateRevenuePrinter(id);
		int flag = printerMapper.deleteByPrimaryKey(id);
		if (flag > 0) {
			res = 0;

		}

		return res;

	}

	@Override
	public int insert(Printer printer) {
			// TODO Auto-generated method stub
	    printerMapper.insert(printer);
		return printer.getId();
	}

	@Override
	public List<Printer> selectByParam(Printer printer) {
		// TODO Auto-generated method stub
		return printerMapper.selectByParam(printer);
	}

	@Override
	public int updateById(Printer printer) {
		int res = 1;
		int flag = printerMapper.updateById(printer);
		if (flag > 0) {
			res = 0;

		}
		return res;
	}

	@Override
	public Printer selectByPrinter(String printer, int id,int resId) {
		// TODO Auto-generated method stub
		Printer param = new Printer();
		param.setPrinterName(printer);
		param.setRestaurantId(resId);
		if (id != 0) {
			param.setId(id);
		}
		List<Printer> list = printerMapper.selectByPrinter(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private int updateRevenuePrinter(int printId) {

		RevenueCenter revenuecenter = new RevenueCenter();
		revenuecenter.setPrintId(printId);
		int flag = 0;
	    flag = revenueCenterMapper.updateByPrinter(revenuecenter);
		return flag;

	}

	@Override
	public List<Printer> selectByRestId(Integer companyId, Integer restaurantId) {
		Printer param = new Printer();
		param.setCompanyId(companyId);
		param.setRestaurantId(restaurantId);
		param.setType(CommonStatusConstant.IS_ACTIVE_DISABLE);
		return this.selectByParam(param);
	}

	@Override
	public List<PrinterGroupVO> selectPrinterGroup(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return printerMapper.selectPrinterGroup(map);
	}

}
