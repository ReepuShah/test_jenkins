package com.framework.Runner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.framework.configreader.ExcelReader;
import com.framework.settings.ObjectRepo;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class RunTimeTransformer implements IAnnotationTransformer {
	
	  private List<String> activatedTest = new ArrayList<String>();

	  public RunTimeTransformer() throws Exception {
	      activatedTest = new ArrayList<String>();
		  Sheet runmgrsheet = ExcelReader.readRunManager();
		  Sheet Runmgr = ExcelReader.readInstance();
		  String build = Runmgr.getRow(1).getCell(2).toString();
			
		
				int rowCount = runmgrsheet.getLastRowNum()-runmgrsheet.getFirstRowNum();
				for (int i = 1; i < rowCount+1; i++) {
					Row row = runmgrsheet.getRow(i);
						if(row.getCell(2).getStringCellValue().equalsIgnoreCase("Yes")){
							activatedTest.add(row.getCell(1).getStringCellValue());
							}
				}
	
}   
	  
		
	  
	  public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		  if(activatedTest.contains(testMethod.getName())) {
		  		annotation.setEnabled(true);
		  		//annotation.setInvocationCount(5);
		  	}else {
		  		annotation.setEnabled(false);
		  	}
	  }

}
