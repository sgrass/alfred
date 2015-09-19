/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package com.cx.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FromHowTo {
  
  public static Object getCellValue(Cell cell,Class clazz){
    String name=clazz.getSimpleName();
    if("String".equals(name)){
        return cell.getStringCellValue();
    }
    if("Integer".equals(name)){
      return cell.getNumericCellValue();
  }
    return null;
}

	public static void main(String[] args) throws Exception {
		 Class[] clazz=new Class[]{String.class,String.class,String.class,String.class,Integer.class,String.class,String.class};
		 InputStream stream=new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\menu.xlsx"));
		 Workbook wb=new XSSFWorkbook(stream);
		 Sheet sheet=wb.getSheetAt(0);
     int rows=sheet.getLastRowNum();
     int cells=sheet.getRow(0).getPhysicalNumberOfCells();
     for (int i = 0; i < rows; i++) {
    	 Row row=sheet.getRow(i+1);
         for (int j = 0; j < cells; j++) {
        	 Cell cell=row.getCell(j);
             Object obj = null;
             if (cell != null){
            	 obj=getCellValue(cell, clazz[j]);
             }
             switch (j) {
             case 0:
            	 	System.out.println("000000000-----"+obj);
                 break;
             case 1:
            	 System.out.println("1111111111111"+obj);
                 break;
             default:
                 break;
             }
         }
     }
	}
}
