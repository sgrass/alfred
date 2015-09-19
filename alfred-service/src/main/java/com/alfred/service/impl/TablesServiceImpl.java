package com.alfred.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.TablesMapper;
import com.alfred.model.Tables;
import com.alfred.service.TablesService;
@Transactional
@Service("tablesService")
public class TablesServiceImpl implements TablesService{

private static Log log = LogFactory.getLog(TablesServiceImpl.class);

	@Autowired
	@Qualifier("tablesMapper")
	private TablesMapper tablesMapper = null;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		//删除实际把IS_ACTIVE设置为-1
		Tables t=new Tables();
		t.setId(id);
		t.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		int res=1;
		int flag = tablesMapper.updateById(t);
		if(flag>0){
			res=0;	
		}
		return res;
	}

	@Override
	public int insert(Tables tables) {
		// TODO Auto-generated method stub
		tablesMapper.insert(tables);
		return tables.getId();
	}

	@Override
	public List<Tables> selectByParam(Tables tables) {
		// TODO Auto-generated method stub
		return tablesMapper.selectByParam(tables);
	}

	@Override
	public int updateById(Tables tables) {
		int res=1;
		int flag = tablesMapper.updateById(tables);
		if(flag>0){
			res=0;
		}
		return res;
	}

	@Override
	public Tables selectByTable(Tables tables) {
		// TODO Auto-generated method stub
		List<Tables> list;
		 list = tablesMapper.selectByTable(tables);
		if (list != null && list.size() > 0) {
				return list.get(0);
		   }
		return null;
	}

	@Override
	public Tables selectByPrimaryKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tablesMapper.selectByPrimaryKey(id);
	}

	

}
