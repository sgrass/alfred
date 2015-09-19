package com.alfred.dao;

import com.alfred.model.Printer;
import com.alfred.vo.PrinterGroupVO;

import java.util.HashMap;
import java.util.List;

public interface PrinterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printer printer);

    List<Printer> selectByParam(Printer printer);
    
    List<Printer> selectByPrinter(Printer printer);

    Printer selectByPrimaryKey(Integer id);

    int updateById(Printer printer);
    
    List<PrinterGroupVO> selectPrinterGroup(HashMap<String, Object> map);
    
}