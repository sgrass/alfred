package com.alfred.dao;

import com.alfred.model.PrinterGroup;
import java.util.List;

public interface PrinterGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrinterGroup printerGroup);

    List<PrinterGroup> selectByParam(PrinterGroup printerGroup);

    PrinterGroup selectByPrimaryKey(Integer id);

    int updateById(PrinterGroup printerGroup);
    
    int deleteGroupId(PrinterGroup printerGroup);
    
}