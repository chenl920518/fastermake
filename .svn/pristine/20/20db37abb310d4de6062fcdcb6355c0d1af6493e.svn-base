package com.cbt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbt.entity.ReadExcelVO;

/**
 * 读取Excel
 * 
 * @author polo
 */
public class ReadExcelUtils {
	private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private String gen;

	public ReadExcelUtils(String filepath) {
		if (filepath == null) {
			return;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {
			InputStream is = new FileInputStream(filepath);
			if (".xls".equals(ext)) {
				wb = new HSSFWorkbook(is);
				gen = "xls";
			} else if (".xlsx".equals(ext)) {
				wb = new XSSFWorkbook(is);
				gen = "xlsx";
			} else {
				wb = null;
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 * @author polo
	 */
	public String[] readExcelTitle() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = row.getCell(i).getCellFormula();
		}
		return title;
	}

	/**
	 * 读取Excel数据内容 （用于塑料件报价单读取）
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @author polo
	 */
	public List<ReadExcelVO> readExcelPlasticContent()
			throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		List<ReadExcelVO> content = new ArrayList<ReadExcelVO>();

		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 获取产品单价所在列		
		int col_unit_price = 0;
		// 获取模具所在列	
		int col_mold_price = 0;
		//获取备注所在列
		int col_remark = 0;

		for (int i = 0; i < rowNum-1; i++) {
			row = sheet.getRow(i);
			ReadExcelVO readExcelVO = new ReadExcelVO();

			for (int j = 0; j < colNum+1; j++) {
				Object obj = null;
				try {
					obj = getCellFormatValue(row.getCell(j));
					if (obj != null) {
						String str = obj.toString();
						if (str.contains("单价")) {
							col_unit_price = j;
						}
						if (str.contains("模具")) {
							col_mold_price = j;
						}
						if (str.contains("备注")) {
							col_remark = j;
						}
					}
				} catch (Exception ex) {
					 System.out.println("数据存在错误 ；定位:行"+i+" 列:"+j);
					 ex.printStackTrace();
				}
			}

			// 找出当前产品单价所在列数，取值
		    Object obj1 = null;
			Object obj2 = null;
			Object obj3 = null;
			if (col_unit_price != 0) {
				obj1 = getCellFormatValue(row.getCell(col_unit_price));
				if (obj1 != null && !"".equals(obj1)) {
					boolean flag = isNumeric(obj1.toString());
					if (flag) {
						readExcelVO.setUnitPrice(obj1.toString());		
						
						// 获取模具单价所在列数值
						if (col_mold_price != 0) {
							obj2 = getCellFormatValue(row.getCell(col_mold_price));
							if (obj2 != null  && !"".equals(obj2)) {
								boolean flag1 = isNumeric(obj2.toString());
								if (flag1) {
									readExcelVO.setMoldPrice(obj2.toString());
								}
							}
						}
						// 获取模具单价所在列数值
						if (col_remark != 0) {
							obj3 = getCellFormatValue(row.getCell(col_remark));
							if (obj3 != null  && !"".equals(obj3)) {
									readExcelVO.setRemark(obj3.toString());
							}
						}
						
					}
				}
			}

			

			

			
			//判断excel获取价格对象是否为空，为空则忽略
			if(obj1 != null && !"".equals(obj1) && isNumeric(obj1.toString())){
				content.add(readExcelVO);
			}

			
			
		}
		return content;
	}

	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 * @author polo
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带带时分秒的：2013-7-10
					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字

					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**  
	 * 判断字符串是否为数字  
	 *   
	 * @param str  
	 * @return  
	 */  
	public static boolean isNumeric(String str) {  
	    Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");  
	    Matcher isNum = pattern.matcher(str);  
	    if (!isNum.matches()) {  
	        return false;  
	    }  
	    return true;  
	}  

	
	/** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    } 
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws InvalidFormatException,
			Exception {

		ReadExcelUtils excelReader = new ReadExcelUtils("D:\\台州轩祥报价单SHS19436.xlsx");
		 List<ReadExcelVO> map = excelReader.readExcelPlasticContent();
		System.out.println(map);
	}
}
