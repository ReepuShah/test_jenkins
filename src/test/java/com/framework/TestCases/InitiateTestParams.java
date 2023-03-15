package com.framework.TestCases;

import static com.framework.settings.ObjectRepo.driver;
import static com.framework.settings.ObjectRepo.test;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.SystemOutLogger;

import com.framework.reporting.ExtentReportHelper;
import com.framework.stepdefinition.StepDefinitions;
import com.framework.browserconfig.InitializeWebDriver;
import com.framework.configreader.ExcelReader;
import com.framework.settings.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class InitiateTestParams {
//	private static List<String> activatedTest = new ArrayList<String>();

	public static void ExecuteTestCase(String TCName) {
		try {
			int Itr = InitiateTestCase(TCName);
			for(int i=1; i<=Itr; i++) {
				ExecuteTestCase(TCName, i);
			}
			TearDownTestCase();
		}catch(Exception e) {
		}
	}
	
	public static int InitiateTestCase(String TCName) {
			
		try {
//			activatedTest = new ArrayList<String>();
//			System.out.println("Initializing Test Case: "+TCName);
			Sheet RunMgr = ExcelReader.readRunManager();
			Sheet Runmgr = ExcelReader.readInstance();
			String sheetName = RunMgr.getSheetName().toString();
			int RunMgrRowNo = ExcelReader.searchExcel(System.getProperty("user.dir")+"/RunManager.xlsm", sheetName , 1, TCName);
			String TCID= RunMgr.getRow(RunMgrRowNo).getCell(0).toString();
			System.out.println("Initializing Test Case: "+TCID+"_"+TCName);
			String environment = Runmgr.getRow(1).getCell(1).toString();
			Row row = RunMgr.getRow(RunMgrRowNo);
			if(environment.equals("Prod")) {
				System.out.println(row.getCell(5).getStringCellValue());
				if(row.getCell(5).getStringCellValue().equalsIgnoreCase("Yes")) {
					ObjectRepo.Environment = environment;
				}
			}else {
		    	ObjectRepo.Environment = environment;
		    }
			ObjectRepo.browser = RunMgr.getRow(RunMgrRowNo).getCell(4).toString();	 
			
			String instanceName = Runmgr.getRow(1).getCell(0).toString();
			switch(instanceName) {
				case "Hubbell":
				
					 if(row.getCell(6).getStringCellValue().equalsIgnoreCase("Yes")){
							ObjectRepo.Instance = instanceName;
					 }else {
						 System.out.println("Please Switch Hubbell Instance No to Yes! ");
							
					 }
				break;
			    	
				case "Demo":
						 if(row.getCell(7).getStringCellValue().equalsIgnoreCase("Yes")){
								ObjectRepo.Instance = instanceName;
						 }else {
							 System.out.println("Please Switch Demo Instance No to Yes! ");
								
						 }
			    break;
					
				case "Factset":
						 if(row.getCell(8).getStringCellValue().equalsIgnoreCase("Yes")){
								ObjectRepo.Instance = instanceName;
						 }else {
							 System.out.println("Please Switch Factset Instance No to Yes! ");
							
						 }
			   break;
			   
			  case "IEEE":
					 if(row.getCell(9).getStringCellValue().equalsIgnoreCase("Yes")){
							ObjectRepo.Instance = instanceName;
					 }else {
						 System.out.println("Please Switch Demo Instance No to Yes! ");
							
					 }
		      break;
			 default:
				System.out.println("Out of Instance");
		  }
			
		
		
			
			int itr =Integer.parseInt(RunMgr.getRow(RunMgrRowNo).getCell(3).toString());
			RunMgr =null;
			return itr;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public static void TearDownTestCase() {
		try {
			ObjectRepo.browser = null;
			ObjectRepo.Environment =  null;
			ObjectRepo.driver=null;
			InitializeWebDriver wd = new InitializeWebDriver();
			wd.tearDownDriver();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ExecuteTestCase(String TCName, int itr) {
		try {
			Sheet Runmgr = ExcelReader.readInstance();
			Sheet RunMgr = ExcelReader.readRunManager();
			String sheetName = RunMgr.getSheetName().toString();
			
			int RunMgrRowNo = ExcelReader.searchExcel(System.getProperty("user.dir")+"/RunManager.xlsm",sheetName , 1, TCName);
			String TCID= RunMgr.getRow(RunMgrRowNo).getCell(0).toString();
			
			
			System.out.println("Running Test Case: "+TCID+"_"+TCName);
			if(itr>1)
				ExtentReportHelper.startTest(TCID+"_"+TCName+"_itr"+itr);
			else
				ExtentReportHelper.startTest(TCID+"_"+TCName);
			String project = Runmgr.getRow(1).getCell(2).toString();
			
			Sheet testdatasheet = ExcelReader.readTestDate();
			
			
			if(project.equals("Old")) {
				System.out.println(project);
				Sheet testflowsheet = ExcelReader.readTestFlow();
				int rowCounttd = testdatasheet.getLastRowNum()-testflowsheet.getFirstRowNum();
				ObjectRepo.testDataVariables = testdatasheet.getRow(0);
				for (int k = 1; k <= rowCounttd+1; k++) {
					Row testdatarow = testdatasheet.getRow(k);
					if(testdatarow.getCell(0).getStringCellValue().equalsIgnoreCase(TCName)
							&& testdatarow.getCell(1).getStringCellValue().equalsIgnoreCase(Integer.toString(itr))){
						ObjectRepo.testData = testdatarow;
						break;
					}
				}
				testdatasheet=null;
				
				int rowCounttf = testflowsheet.getLastRowNum()-testflowsheet.getFirstRowNum();
				StepDefinitions sf = new StepDefinitions();
				for (int k = 1; k <= rowCounttf+1; k++) {
					Row testcaserow = testflowsheet.getRow(k);
					if(testcaserow.getCell(0).getStringCellValue().equalsIgnoreCase(TCName)) {
						for (int l = 1; l < testcaserow.getLastCellNum(); l++) {
							try {
								String functionName = testcaserow.getCell(l).getStringCellValue();
								System.out.println(functionName);
								if(!functionName.equals("")) {
									Class<? extends StepDefinitions> cls = sf.getClass();
									try {
										Method methodcall1 = cls.getDeclaredMethod(functionName);
										methodcall1.invoke(sf);
									}catch (Exception e) {
										System.out.println("Unable to find function");
										ObjectRepo.test.log(LogStatus.FAIL, "Unable to Find Function");
									}
								}
							}catch(Exception e) {
								System.out.println("Finished Test Case");
								break;
							}
							
						}
						ExtentReportHelper.endTest();
						break;
					}
				}
				testflowsheet=null;
			}else {
				System.out.println(project);
				Sheet newtestflowsheet = ExcelReader.readNewTestFlow();
				int rowCounttd = testdatasheet.getLastRowNum()-newtestflowsheet.getFirstRowNum();
				ObjectRepo.testDataVariables = testdatasheet.getRow(0);
				for (int k = 1; k <= rowCounttd+1; k++) {
					Row testdatarow = testdatasheet.getRow(k);
					if(testdatarow.getCell(0).getStringCellValue().equalsIgnoreCase(TCName)
							&& testdatarow.getCell(1).getStringCellValue().equalsIgnoreCase(Integer.toString(itr))){
						ObjectRepo.testData = testdatarow;
						break;
					}
				}
				testdatasheet=null;
				int rowCounttf = newtestflowsheet.getLastRowNum()-newtestflowsheet.getFirstRowNum();
				StepDefinitions sf = new StepDefinitions();
				for (int k = 1; k <= rowCounttf+1; k++) {
					Row testcaserow = newtestflowsheet.getRow(k);
					if(testcaserow.getCell(0).getStringCellValue().equalsIgnoreCase(TCName)) {
						for (int l = 1; l < testcaserow.getLastCellNum(); l++) {
							try {
								String functionName = testcaserow.getCell(l).getStringCellValue();
								System.out.println(functionName);
								if(!functionName.equals("")) {
									Class<? extends StepDefinitions> cls = sf.getClass();
									try {
										Method methodcall1 = cls.getDeclaredMethod(functionName);
										methodcall1.invoke(sf);
									}catch (Exception e) {
										System.out.println("Unable to find function");
										ObjectRepo.test.log(LogStatus.FAIL, "Unable to Find Function");
									}
								}
							}catch(Exception e) {
								System.out.println("Finished Test Case");
								break;
							}
						}
						ExtentReportHelper.endTest();
						break;
					}
				}
				newtestflowsheet=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}



}
