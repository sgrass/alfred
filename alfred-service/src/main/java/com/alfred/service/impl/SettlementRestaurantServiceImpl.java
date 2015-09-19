package com.alfred.service.impl;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.SettlementRestaurantMapper;
import com.alfred.model.SettlementRestaurant;
import com.alfred.service.SettlementRestaurantService;


@Transactional
@Service("settlementRestaurantService")
public class SettlementRestaurantServiceImpl implements SettlementRestaurantService{

	private static Log log = LogFactory.getLog(SettlementRestaurantServiceImpl.class);
	@Autowired
	@Qualifier("settlementRestaurantMapper")
	private SettlementRestaurantMapper settlementRestaurantMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SettlementRestaurant settlementRestaurant) {
		// TODO Auto-generated method stub
		return settlementRestaurantMapper.insert(settlementRestaurant);
	}

	@Override
	public List<SettlementRestaurant> selectByParam(
			SettlementRestaurant settlementRestaurant) {
		// TODO Auto-generated method stub
		return settlementRestaurantMapper.selectByParam(settlementRestaurant);
	}

	@Override
	public SettlementRestaurant selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(SettlementRestaurant settlementRestaurant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bachInsert(String[] media, String[] adjuestMent, String reasons,
			String remarks,int restaurantId,String general,String otherPayment) {
			//删除餐厅里面所有绑定的东西
			// TODO Auto-generated method stub
			settlementRestaurantMapper.deleteByRestaurantId(restaurantId);
			if(general!=null&&!general.equals("")){
				SettlementRestaurant settlementRestaurant = new SettlementRestaurant();
				settlementRestaurant.setRestaurantId(restaurantId);
				settlementRestaurant.setType(CommonStatusConstant.CASH_TYPE);
				settlementRestaurantMapper.insert(settlementRestaurant);
			}
			if (media != null && media.length > 0  ) {
				//执行数据插入
				for (String id : media) {
					SettlementRestaurant tem = new SettlementRestaurant();
					tem.setMediaId(Integer.parseInt(id.trim()));
					tem.setRestaurantId(restaurantId);
					tem.setType(CommonStatusConstant.MEDIA_TYPE);
					settlementRestaurantMapper.insert(tem);
				}

			}
			if (adjuestMent != null && adjuestMent.length > 0  ) {
				//执行数据插入
				for (String id : adjuestMent) {
					SettlementRestaurant tem = new SettlementRestaurant();
					tem.setAdjustmentsId(Integer.parseInt(id.trim()));
					tem.setRestaurantId(restaurantId);
					tem.setType(CommonStatusConstant.ADJUEST_TYPE);
					//log.info(id.trim()+"--"+id.trim().equals("2000")+"--"+id.trim().equals("105"));
					if (id.trim().equals("2000")) {
						tem.setRemarks(reasons);
					}
					if (id.trim().equals("105")) {
						tem.setRemarks(remarks);
					}
					settlementRestaurantMapper.insert(tem);
				}

			}
			if(otherPayment!=null&&!otherPayment.equals("")){
				String other[]=otherPayment.split(",");
				for(String ot:other){
					SettlementRestaurant tem = new SettlementRestaurant();
					tem.setRestaurantId(restaurantId);
					tem.setType(CommonStatusConstant.OTHER_TYPE);
					tem.setRemarks(ot);
					settlementRestaurantMapper.insert(tem);
				}
			}
			
			
		return 0;
	}
 
}
