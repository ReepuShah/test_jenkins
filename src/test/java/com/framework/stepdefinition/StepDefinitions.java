 

package com.framework.stepdefinition;


import static com.framework.settings.ObjectRepo.browser;





import static com.framework.settings.ObjectRepo.driver;
import static com.framework.settings.ObjectRepo.test;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MulticastSocket;
import java.text.Format;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.apache.commons.mail.EmailException;
import org.apache.poi.hssf.util.HSSFColor.ORANGE;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.util.Units;
import org.bson.assertions.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.framework.SendMail.sendmail;
import com.framework.TestCases.InitiateTestParams;
import com.framework.browserconfig.BrowserType;
import com.framework.browserconfig.InitializeWebDriver;
import com.framework.configreader.ExcelReader;
import com.framework.elementhelper.ButtonHelper;
import com.framework.elementhelper.GenericElements;
import com.framework.elementhelper.TextBoxHelper;
import com.framework.exception.NoSuitableDriverFoundException;
import com.framework.generic.GenericHelper;
import com.framework.pages.AvatarPage;
import com.framework.pages.DataManagerLocatorPage;
import com.framework.pages.ExpertPage;
import com.framework.pages.HubbellHomePage;
import com.framework.pages.HubbellLoginPage;
import com.framework.pages.KnowledgeAnalyticsPage;
import com.framework.pages.ProfilePage;
import com.framework.pages.QuestionPage;
import com.framework.pages.ThemeLocatorPage;
import com.framework.reporting.ExtentReportHelper;
import com.framework.settings.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;

import freemarker.core.StringArraySequence;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class StepDefinitions {
	
	
	public static void LaunchBrowser() {
		try {
			System.out.println("Launching "+browser+" browser");
			InitializeWebDriver wd = new InitializeWebDriver();
			switch (browser) {
			  case "Chrome": 
				  wd.setUpDriver(BrowserType.Chrome);
				  test.log(LogStatus.INFO, "Chrome Launch successfully");
				  break;
			  case "Edge":
				  wd.setUpDriver(BrowserType.Edge);
				  test.log(LogStatus.INFO, "Edge Launch successfully");
				  break;
			  case "Firefox":
				  wd.setUpDriver(BrowserType.Firefox);
				  test.log(LogStatus.INFO, "FireFox Launch successfully");
				  break;
			  default:
				  ExtentReportHelper.logFailWithScreenshot("Unable to Launch driver");
				  throw new NoSuitableDriverFoundException(" Driver Not Found : " +
						  browser); } 
			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to launch Browser");
		}
	}
	
	public static void CloseApplication(){
		driver.quit();
		System.out.println("Application Closed");
		test.log(LogStatus.INFO, "Application Closed");
	}
	
	public static void Login() {
		try {
//			if(!ObjectRepo.Environment.contains("_Hubbell"))
//				ObjectRepo.Environment = ObjectRepo.Environment+"_Hubbell";
			driver.get(ObjectRepo.reader.getWebsite());
			test.log(LogStatus.INFO, "Hubbell Application Launch successfully");
//			HubbellLoginPage lp = new HubbellLoginPage(driver);
//			TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
//			TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
//			ButtonHelper.click(lp.btnLogin, "Login Button");
		}catch(Exception e) {
			test.log(LogStatus.ERROR, "Unable to login Hubbell Application");
			e.printStackTrace();
		}
		
	}
	
	public static void LoginApplication() throws Exception {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
		ButtonHelper.click(lp.btnLogin, "Login Button");
	}
	
	public static void AskQuestion() {
		try {
			HubbellHomePage hp = new HubbellHomePage(driver);
			Sheet QuestionSheet = ExcelReader.readQuestions();
			int rows = QuestionSheet.getLastRowNum();
			for(int i =1; i<=rows; i++) {
				try {
					TextBoxHelper.enterText(hp.txtSearch, "Ask Me Anything TextBox", QuestionSheet.getRow(i).getCell(0).toString());
					Thread.sleep(1000);
					hp.txtSearch.sendKeys(Keys.ENTER);
					driver.manage().timeouts()
					.implicitlyWait(ObjectRepo.reader.getWaitForAnswer(),
							TimeUnit.SECONDS);
					ButtonHelper.click(driver.findElement(By.xpath("//*[@value='allTab']")), "All tab");
					//String topAnswer = driver.findElement(By.xpath("//*[@class='topResponse container toggle']")).getText();
					//System.out.println(topAnswer);
					String topAnswer = QuestionSheet.getRow(i).getCell(2).toString();
					driver.manage().timeouts()
					.implicitlyWait(ObjectRepo.reader.getImplicitWait(),
							TimeUnit.SECONDS);
					if(QuestionSheet.getRow(i).getCell(1).toString().equalsIgnoreCase("Text"))
						ValidateTextAnswerInTop(topAnswer, Integer.parseInt(GenericHelper.getTestData("NumberOfAnswersToLook")));
					else if(QuestionSheet.getRow(i).getCell(1).toString().equalsIgnoreCase("Table"))
						ValidateTableAnswerInTop(topAnswer, Integer.parseInt(GenericHelper.getTestData("NumberOfAnswersToLook")));
					else if(QuestionSheet.getRow(i).getCell(1).toString().equalsIgnoreCase("Image"))
						ValidateImageAnswerInTop(topAnswer, Integer.parseInt(GenericHelper.getTestData("NumberOfAnswersToLook")));
				}catch(Exception e) {
					ExtentReportHelper.logFailWithScreenshot("Unable to find Answer/Question");

				}
			}
		}catch(Exception e) {
			test.log(LogStatus.ERROR, "Unable to Ask Question");
			e.printStackTrace();
		}
	}
	
	public static void ValidateTextAnswerInTop(String topAnswer, int NumberOfAnswersToLook){
		boolean flag = false;
		for(int j =1; j<=NumberOfAnswersToLook; j++) {
			//String tmpAns = driver.findElement(By.xpath("(//*[@ID='allTab']//*[@class='content'])["+j+"]")).getText();
			String tmpAns = driver.findElement(By.xpath("(//*[@ID='allTab']//*[@class='content'])["+j+"]")).getAttribute("innerHTML");
			tmpAns = tmpAns.replace("<strong>", "").replace("</strong>", "").replace("amp;", "")
					.replace("&nbsp;", " ")
					.replace(" <br>", "\n")
					.replace("<br>", "\n");
			if(topAnswer.trim().replace(" ", "").equals(tmpAns.trim().replace(" ", ""))) {
				flag = true;
				break;
			}
		}
		
		if(flag)
			test.log(LogStatus.PASS, "Answer is displayed in the top "+NumberOfAnswersToLook+" Answers");
		else
			ExtentReportHelper.logFailWithScreenshot("Answer is NOT displayed in the top "+NumberOfAnswersToLook+" Answers");

	}
	
	
	public static void ValidateTableAnswerInTop(String topAnswer, int NumberOfAnswersToLook){
		boolean flag = false;
		String[] rows = topAnswer.split(":");
		String ValidaionString="";
		for(int i=0; i<rows.length; i++) {
			String[] values = rows[i].split(",");
			for(int k=0; k<values.length; k++) {
				System.out.println(values[k]);
				if(k==0)
					ValidaionString=ValidaionString+values[k];
				else
					ValidaionString=ValidaionString+" "+values[k];
			}
			ValidaionString=ValidaionString+"\n";
		}
		System.out.println();
		System.out.println(ValidaionString);
		int l=1;
		for(int j =1; j<=NumberOfAnswersToLook; j++) {
			String tmpAns = driver.findElement(By.xpath("(//*[@class='display-table'])["+l+"]")).getText();
			l=l+2;
			System.out.println(tmpAns);
			if(tmpAns.trim().contains(ValidaionString.trim())) {
				flag = true;
				break;
			}
		}
		
		if(flag)
			test.log(LogStatus.PASS, "Answer is displayed in the top "+NumberOfAnswersToLook+" Answers");
		else
			ExtentReportHelper.logFailWithScreenshot("Answer is NOT displayed in the top "+NumberOfAnswersToLook+" Answers");
	}
	
	public static void ValidateImageAnswerInTop(String topAnswer, int NumberOfAnswersToLook){
		boolean flag = false;
		for(int j =1; j<=NumberOfAnswersToLook; j++) {
			String tmpAns = driver.findElement(By.xpath("//*[@class='visual-image-container']["+j+"]/img")).getAttribute("src");
			if(topAnswer.trim().equals(tmpAns.trim())) {
				flag = true;
				break;
			}
		}
		
		if(flag)
			test.log(LogStatus.PASS, "Answer is displayed in the top "+NumberOfAnswersToLook+" Answers");
		else
			ExtentReportHelper.logFailWithScreenshot("Answer is NOT displayed in the top "+NumberOfAnswersToLook+" Answers");

	}
	
	public static void LoginBasf() {
		ObjectRepo.Environment = ObjectRepo.Environment+"_basf";
		driver.get(ObjectRepo.reader.getWebsite());
		test.log(LogStatus.INFO, "basf application launch Successfully");
	}
	
	public static void EnterUserNameAndPassword() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
		
	}
	 
	public static void PressEnterKeys() throws Exception {
		Actions action = new Actions(driver);
		HubbellHomePage hp = new HubbellHomePage(driver);
		WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		if(element.isDisplayed()) {
			element.click();
		}
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public static void PressTabKeys() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
	}
	
	public static void VerifyEnterKeysWorkAsASubstitue() {
		String title = driver.getTitle();
		String expectedTitle = "Nesh";
		Assert.assertEquals(title, expectedTitle);
		System.out.println("hi iam here");
	}
	
	public static void VerifyAllTheFieldAppears() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.txtUserName,"UserName Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtPassword,"Password Field");
		GenericElements.ValidateElementIsDisplayed(lp.btnLogin,"Login Button");
		GenericElements.ValidateElementIsDisplayed(lp.btnSignUp,"Sign Up Button");
		GenericElements.ValidateElementIsDisplayed(lp.linkForgetPassowrd,"Forget Password Link");
		}
	
	
	public static void VerifyNeshIconIsDisplayOnHomeScreen(){
		HubbellHomePage lp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.iconNesh,"Nesh Icon");
	}
	
	public static void ClickOnPasswordForgetLink() throws Exception{
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		ButtonHelper.click(lp.linkForgetPassowrd, "Forget Password Link");
		
	}
	
	public static void EnterUserIdOnForgetPasswordPage() throws Exception{
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
	}
	
	public static void ClickOnSendButton() throws Exception{
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		ButtonHelper.click(lp.btnSend, "Send Button");
	}
	
	public static void VerifyPasswordForgetMessageIsDisplay() throws Exception{
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.passwordForgetMessage,"Password Forget Message");
	}
	
	public static void VerifyPasswordFieldMasksThePassword() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
		String Marksvalue = driver.findElement(By.xpath("//*[@name='password']")).getAttribute("value");
		assertEquals(Marksvalue, GenericHelper.getTestData("HubbellPassword"));
	}
	
	public static void ClickOnLoginButton() throws Exception {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		ButtonHelper.click(lp.btnLogin, "Login Button");
		}
	
	public static void VerifyLoginPageDoesNotAllowedAccessingPageMessageIsDisplay() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.errorMessage,"Error Message");
	}
	
	public static void VerifyTabButtonIsUsedToNevigateToNextField() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		Actions action = new Actions(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(GenericHelper.getTestData("HubbellPassword")).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.iconNesh,"Nesh Icon");
	}
	
	public static void EnterInvalidUserNameAndPassword() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));	
	}
	
	public static void VerifyErrorIconIsDisplay() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.errorMessage,"Error Message");
	}
	
	public static void EnterInvalidUserName() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
	}
	
	public static void EnterValidPassowrd() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
	}
	
	public static void EnterValidUserName() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("HubbellUsername"));
	}
	
	public static void EnterInvalidPassword() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("HubbellPassword"));
	}
	
	public static void ClickOnSignUpButton() throws Exception {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		ButtonHelper.click(lp.btnSignUp, "Sign Up Button");
	}
	
	public static void VerifyAllRequiredFieldSAppearsOnSignUpPage() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.txtInviteCode,"Invite Code Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtFirstName,"First Name Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtLastName,"Last Name Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtEmail,"Email Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtPassword,"Password Field");
		GenericElements.ValidateElementIsDisplayed(lp.SignUpButton,"Sign Up Field");
	}
	
	public static void ClickOnSignUpButtonOnSignUpPage() throws Exception {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		ButtonHelper.click(lp.SignUpButton, "SignUp Button");
	}
	
	public static void VerifyThatAllRequiredFieldsHaveARedMarkErrorMessageIsDisplay() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.inviteCodeErrorMessage,"Invite Code Field a Red Mark");
		GenericElements.ValidateElementIsDisplayed(lp.firstNameErrorMessage,"First Name Field a Red Mark");
		GenericElements.ValidateElementIsDisplayed(lp.lastNameErrorMessage,"Last Name Field a Red Mark");
		GenericElements.ValidateElementIsDisplayed(lp.emailErrorMessage,"Email Field a Red Mark");
		GenericElements.ValidateElementIsDisplayed(lp.passwordErrorMessage,"Password Field a Red Mark");
		
	}
	
	public static void EnterAllTheRequiredInformation() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtInviteCode, "Invite Code", GenericHelper.getTestData("InviteCode"));
		TextBoxHelper.enterText(lp.txtFirstName, "First Name", GenericHelper.getTestData("FirstName"));
		TextBoxHelper.enterText(lp.txtLastName, "Last Name", GenericHelper.getTestData("LastName"));
		TextBoxHelper.enterText(lp.txtEmail, "Email", GenericHelper.getTestData("EmailId"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("Password"));
	}
	public static void VerifySignUpSuccessfuly() {
		String title = driver.getTitle();
		String expectedTitle = "none";
		if(title.equals(expectedTitle)) {
			ObjectRepo.test.log(LogStatus.PASS,"Sign Up Successfully!");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"I Need Invite code");
			System.out.print("I need invite code!");
		}
          //	Pending............
		
	}
	
	public static void VerifySignUpPageLoadWithNoneOfTheDataIntoTheTextField() throws Exception {
		HubbellLoginPage lp = new HubbellLoginPage(driver);		
		GenericElements.ValidateTheNullValueOfTheElementsIsTheDisplay(lp.txtInviteCode, "Invite Code Text Field");
		GenericElements.ValidateTheNullValueOfTheElementsIsTheDisplay(lp.txtFirstName, "First Name Text Field");
		GenericElements.ValidateTheNullValueOfTheElementsIsTheDisplay(lp.txtLastName, "Last Name Text Field");
		GenericElements.ValidateTheNullValueOfTheElementsIsTheDisplay(lp.txtEmail, "Email Text Field");
		GenericElements.ValidateTheNullValueOfTheElementsIsTheDisplay(lp.txtPassword, "Password Text Field");
		}
	
	public static void EnterEmailIdWithoutAtTheRateSpecialSign() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtEmail, "Email", GenericHelper.getTestData("EmailId"));
	}
	
	public static void VerifyErrorMessageShownOnEmailFields(){
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.emailErrorMessage,"Email Field a Red Mark");
	}
	
	public static void EnterEmailIdWithOtherSpecialSign() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtEmail, "Email", GenericHelper.getTestData("EmailId"));
	}
	
	public static void ClickOnHomeNavigationIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.homeIcon, "Home Icon");
		}
	
	public static void VerifyHomepageIsDisplay() {
		GenericElements.VerifyPageURL("home","Home Page");
	}
	
	public static void ClickOnDataManagerNavigationIcon() throws Exception {
		DataManagerLocatorPage dmlp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dmlp.dataManagerIcon, "Data Manager Icon");
	}
	
	public static void VerifyDataManagerpageIsDisplay() {
		GenericElements.VerifyPageURL("upload","Data Manager Page");
	}
	
	public static void ClickOnLatestUpdateNavigationIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		}
	
	public static void VerifyLatestUpdatePageIsDisplay() {
		GenericElements.VerifyPageURL("updates","Latest Update Page");
	}
	
	public static void WriteAnyQuestions() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		element.click();
		TextBoxHelper.enterText(element, "Write Any Question", GenericHelper.getTestData("TextInputData"));
	}
	
	public static void ClickOnSearchButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.searchButton, "Search Button");
	}
	
	public static void VerifySearchedResultIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.searchedResult,"Searched Result");
	}
	
	public static void VerifyAnswerIsShowingRelatedSearchedQuery() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.relatedQuery.isEmpty()) {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer not Found!");
		}else {
			GenericElements.ValidateRelevantElementIsDisplayed(hp.relatedQuery,"hydrogen","Query");
		}
	}
	
	public static void VerifyProperAnswerIsShowingRelatedSearchedQuery() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.properAnswer,"Proper Searched Result");
	}
	
	public static void VerifySearchButtonEnabledWithoutEnteringAnyKeyword() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		try {
			if(hp.searchButton.isEnabled()) 
				ObjectRepo.test.log(LogStatus.FAIL,"Search Button is Enabled");
			else
				ObjectRepo.test.log(LogStatus.PASS, "Search Button is Disabled");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
		
	public static void ClickOnHelpIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.helpIcon, "Help Icon");
		Set<String> window = driver.getWindowHandles();
		Iterator<String> iterator = window.iterator();
		iterator.next();
		String childWindow = iterator.next();
		driver.switchTo().window(childWindow);
	}

	public static void VerifyAllRequiredOptionsAppearsInTheHelpPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.neshLogoIcon," Nesh Logo Icon");
		GenericElements.ValidateElementIsDisplayed(hp.findHelpAndServicestxtSearchIndex,"Help And Services Index");
		GenericElements.ValidateElementIsDisplayed(hp.reportABugField,"Report a bug");
		GenericElements.ValidateElementIsDisplayed(hp.suggestImprovementField,"Suggest improvement");
		GenericElements.ValidateElementIsDisplayed(hp.suggestANewFeatureField,"Suggest a new feature");
		GenericElements.ValidateElementIsDisplayed(hp.technicalSupportField,"Technical support");
		
	}
	
	public static void FindHelpANDServices() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		TextBoxHelper.enterText(hp.findHelpAndServicestxtSearchIndex, "Find Help And Services", GenericHelper.getTestData("TextInputData"));
	}
	
	public static void ClickOnSearchIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.searchIcon, "Search Icon");
	}
	
	public static void VerifyFindHelpAndServiceBoxWorksProperly() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.searchedHelpAndServices,"finded Help And Services");
	}
	
	public static void ClickOnReportABug() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.reportABugField, "Report A Bug");
	}
	
	public static void VerifyUserAbleToReportABug() throws InterruptedException {
		Thread.sleep(3000);
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.reportABugOption,"Report A Bug Option");
	}
	
	public static void ClickOnSuggestImprovement() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.suggestImprovementField, "Suggest Improvement");
	}
	
	public static void VerifyUserAbleToSuggestImprovement() throws InterruptedException {
		Thread.sleep(3000);
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.suggestImprovementOption,"Suggest Improvement Option");
	}
	
	public static void ClickOnSuggestANewFeature() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.suggestANewFeatureField, "Suggest a new feature");
	}
	
	public static void VerifyUserAbleToSuggestANewFeature() throws InterruptedException {
		Thread.sleep(3000);
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.suggestANewFeatureOption,"Suggest a new feature Option");
	}
	
	public static void ClickOnTechnicalSupport() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.technicalSupportField, "Technical support");
	}
	
	public static void VerifyUserAbleToTechnicalSupport() throws InterruptedException {
		Thread.sleep(3000);
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.technicalSupportOption,"Technical support Option");
	}
	
	public static void ClickOnLoginButtonOnHelpPage() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.loginButton, "Login Link Button");
	}
	
	public static void VerifyUserCanLoginWithTheirEmailIdToSearchForHelp() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.loginToHelpCenterPage," Login To Help Center Page");
	}
	
	public static void ClickOnNeshLogoButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.neshLogoIcon, "Nesh Logo Icon Button");
	}
	
	public static void VerifyNESHLogoRedirectToWelcomeTheNeshHelpCenterPage() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.neshLogoIcon,"The Nesh Help Center Page");
	}
	
	public static void ClickOnDataManagerIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.dataManagerIcon, "Data Manager Icon");
	}
	
	public static void VerifyAllRequiredOptionsAppearsOnDataManagerPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.filesOption,"Files Option");
		GenericElements.ValidateElementIsDisplayed(hp.webSitesOption,"Web Sites Option");
		GenericElements.ValidateElementIsDisplayed(hp.dataConnectorsOption,"Data Connectors Option");
		GenericElements.ValidateElementIsDisplayed(hp.powerUpsOption,"Power Ups Option");
		
	}
	
	public static void ClickOnFilesOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.filesOption, "Files Option");
	}
	
	public static void VerifyFilesOptionRedirectToNextRelatedToFilesPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/file","Related Files Page");
	}
	
	public static void ClickOnWebSitesOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.webSitesOption, "Web Sites Option");
	}
	
	public static void VerifyWebSitesOptionRedirectToNextRelatedToWebSitesPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/website","Related Web Sites Page");
	}
	
	public static void ClickOnDataConnectorOption() throws Exception {
		DataManagerLocatorPage dmlp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dmlp.dataConnectorsOption, "Data connectors Option");
	}
	
	public static void VerifyDataConnectorOptionRedirectToNextRelatedToDataConnectorPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/data-connector","Related Data connectors Page");
	}
	
	public static void ClickOnPowerUpsOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.powerUpsOption, "Power Ups Option");
	}
	
	public static void VerifyPowerUpsOptionRedirectToNextRelatedToPowerUpsPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/power-up","Related Power Ups Page");
	}
	
	public static void VerifyAllRequiredFilesOptionsAppearsOnFilePage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadFilesOption,"Upload Files Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadFAQsOption,"Upload FAQs Option");
		GenericElements.ValidateElementIsDisplayed(hp.emailFilesOption,"Email Files Option");
	
	}
	
	public static void ClickOnUploadFilesOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.uploadFilesOption, "Upload Files Option");
	}
	
	public static void VerifyUploadFilesOptionRedirectToNextRelatedToUploadFilesPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/file/manual","Related Upload Files Page");
	}
	
	public static void ClickOnUploadFAQsOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.uploadFAQsOption, "Upload FAQs Option");
	}
	
	public static void VerifyUploadFAQsOptionRedirectToNextRelatedToUploadFAQsPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/file/faq","Related Upload FAQs Page");
	}
	
	public static void ClickOnEmailFilesOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.emailFilesOption, "Email Files Option");
	}
	
	public static void VerifyEmailFilesOptionRedirectToNextRelatedToUploadEmailFilesPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/file/email","Related Email Files Page");
	}
	
	public static void VerifyAllRequiredUploadFilesOptionsAppearsOnUploadFilesPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadFileButton,"Upload Files Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search File Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadingSection,"File are Uploading Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"File are Uploaded Section Option");
	
	}
	
	public static void ClickOnUploadFileButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.uploadFileButton, "Upload File Button Option");
	}
	
	public static void VerifyUserAbleToUploadANewFile() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		GenericElements.ValidateElementIsDisplayed(hp.uploadFiles,"User is able to upload file");
	}
	
//	public static void ClickOnUploadYourFileSection() throws Exception {
//		HubbellHomePage hp = new HubbellHomePage(driver);
//		Thread.sleep(3000);
//		ButtonHelper.click(hp.uploadYourFiles, "Upload Your Files Section Option");
//	}
//	
//	
//	
//	public static void VerifyUserAbleToUploadANewFile() throws InterruptedException {
//		HubbellHomePage hp = new HubbellHomePage(driver);
//		Thread.sleep(5000);
//		GenericElements.ValidateElementIsDisplayed(hp.uploadMessage,"Successful Upload File Message");
//	}
	
	
	public static void ClickOnDataConnector() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.dataConnectorsOption, "Data Connector");
	}
	
	public static void VerifyAllDataConnectorIsDisplay() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/data-connector","All Data Connector");
	}
	
	public static void VerifyAllRequiredUploadFAQsOptionsAppearsOnUploadFAQsPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadFAQsButton,"Upload FAQs Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search FAQs Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadingSection,"FAQs are Uploading Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"FAQs are Uploaded Section Option");
	}
	
	public static void ClickOnUploadFAQsButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.uploadFAQsButton, "Upload FAQs Button Option");
	}
	
	
	public static void VerifyUserAbleToUploadANewFAQs() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadFiles,"User is able to  Upload FAQs");
	}
	
	public static void VerifyAllRequiredEmailFileOptionsAppearsOnEmailFilePage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadEmailFileButton,"Upload Email File Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search Email File Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadingSection,"Emails are Uploading Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"Emails are Uploaded Section Option");
	}
	
	public static void ClickOnUploadEmailButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.uploadEmailFileButton, "Upload Email Button Option");
	}
	
	public static void ClickOnUploadEmailSection() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sendEmailSection, "Upload Email Section");
	}
	
	public static void VerifyUserAbleToUploadANewEmailFile() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		GenericElements.ValidateElementIsDisplayed(hp.emailIDCopiedMessage,"Email Id Copied");
	}
	
	public static void VerifyAllRequiredWebSitesConnectingOptionsAppearsOnWebSitesConnectingPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.connectWebSiteButton,"Connect Web-Sites Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search Web-Sites Option");
		GenericElements.ValidateElementIsDisplayed(hp.connectingSection,"Web Sites are Connecting Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"Web Sites are Uploaded Section Option");
	}
	
	public static void ClickOnConnectWebSitesButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);;
		ButtonHelper.click(hp.connectWebSiteButton, "Connect Web Sites Button Option");
	}
	
	public static void EnterWebSiteLink() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		hp.linkInputBox.sendKeys("https://test-hubbell.hellonesh.io/upload/website");
		hp.siteMapsLinkInputBox.sendKeys("https://test-hubbell.hellonesh.io/upload/website");
	}
	public static void ClickOnSaveButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.saveBtn, "Save Button");
	}
	
	
	public static void VerifyWebsiteSuccessfulConnected() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.webSiteConnected,"Website Connected");
		
	}
	
	public static void VerifyAllRequiredDataConnectorsOptionsAppearsOnDataConnectorsPage() {
		DataManagerLocatorPage dmlp = new DataManagerLocatorPage(driver);
		String connectors[] = GenericHelper.getTestData("Connector").split(",");
		for(int i=0; i<dmlp.connectorsOption.size(); i++) {
		GenericElements.ValidateRelevantElementIsDisplayed(dmlp.connectorsOption,connectors[i],connectors[i]);
		}
		
//		String connectors[] = GenericHelper.getTestData("Connector").split(",");
//			for(int i=0; i<connectors.length; i++) {
//				GenericElements.VerifyDataFromExcel(hp.connectorsOption.get(i),connectors[i]);
//				
//			}
	}
	
	public static void ClickOnBlobStorage() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.blobStorageOption, "Blob Storage Option");
	}
	
	public static void VerifyBlobStorageOptionRedirectToNextRelatedToBlobStorageAccount() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/data-connector/blobstorage","Blob Storage Account");
		
	}
	
	public static void ClickOnSharePoint() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sharepointOption, "Share Point Option");
	}
	
	public static void VerifySharePointOptionRedirectToNextRelatedToSharePointPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/data-connector/sharepoint","Share Point Page");
		
	}
	
	public static void VerifyAllRequiredBlobStorageOptionsAppearsOnBlobStoragePage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.connectBlobStorageAccountButton,"Blob Storage Account Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search Blob Storage Account Option");
		GenericElements.ValidateElementIsDisplayed(hp.connectingSection,"Blob Storage Account are Connecting Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"Blob Storage Account are Uploaded Section Option");
	}
	
	public static void VerifyAllRequiredSharePointOptionsAppearsOnSharePointPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.sharePointAccountButton,"Share Point Account Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search Share Point Account Option");
		GenericElements.ValidateElementIsDisplayed(hp.connectingSection,"Share Point Account are Connecting Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"Share Point Account are Uploaded Section Option");
	}
	
	public static void ClickOnSharePointAccountButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.sharePointAccountButton, "Share Point Account Button");
	}
	
	public static void VerifyUserAbleToConnectSharePointAccount() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.connectSharePointPage,"Connect Share Point Acount Page");
	}
	
	public static void ClickOnPowerUps() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.powerUpsOption, "Power Ups");
	}
	
	public static void VerifyEDGAROptionIsVisible() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.edgerOption,"EDGAR Option");
	}
	
	public static void ClickOnEDGAROption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.edgerOption, "EDGAR Option");
	}
	
	public static void VerifyEDGAROptionRedirectToNextRelatedToEDGARPage() throws InterruptedException {
		GenericElements.VerifyPageURL("upload/power-up/sec_edgar","EDGAR Page");
		
	}
	
	public static void VerifyAllRequiredEDGAROptionsAppearsOnEDGARPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.edgerCONNECTButton," EDGAR Button Option");
		GenericElements.ValidateElementIsDisplayed(hp.searchOption,"Search EDGAR Account Option");
		GenericElements.ValidateElementIsDisplayed(hp.connectingSection,"EDGAR Account are Connecting Section Option");
		GenericElements.ValidateElementIsDisplayed(hp.uploadedSection,"EDGAR are Uploaded Section Option");
	}
	
	public static void ClickOnConnectEDGARButtonOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.edgerCONNECTButton, "Connect EDGAR Button Option");
	}
	
	public static void VerifyUserAbleToConnectEDGAR() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.connectEdgarPage,"Connect EDGAR Page");
	}
	
	public static void ClickOnProfileButton() throws Exception {
		ProfilePage pp = new ProfilePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(pp.profileButton,"Profile Button ");
		
	}
	
	public static void VerifySettingsButtonAndLogoutButtonIsDisplay() {
		ProfilePage pp = new ProfilePage(driver);
		GenericElements.ValidateElementIsDisplayed(pp.settingsButton,"Settings Button");
		GenericElements.ValidateElementIsDisplayed(pp.logOutButton,"LogOut Button");
	}
	
	public static void ClickOnSettingsButton() throws Exception {
		ProfilePage pp = new ProfilePage(driver);
		ButtonHelper.click(pp.settingsButton, "Settings Button ");
		
	}
	
	public static void VerifyUserAbleToProfileUpdate() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.profilePage,"Profile Page");
	}
	
	public static void ClickOnLogOutButton() throws Exception {
		ProfilePage pp = new ProfilePage(driver);
		ButtonHelper.click(pp.logOutButton, "LogOut Button ");
	}
	
	public static void VerifyUserAbleToLogOut() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.LogoutPage,"Logout Page");
	}
		
	public static void VerifySettingButtonRedirectToProfilePage() throws InterruptedException {
		GenericElements.VerifyPageURL("profile","ProfilePage");
		
	}
	
	public static void VerifyLogOutButtonWorkProperly() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		GenericElements.ValidateElementIsDisplayed(hp.LogoutPage,"Logout Page");
	}
	
	public static void ClickOncompareAllButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.compareAllButton, "Compare All Button");
	}
	
	public static void VerifyAnswersAreAppearInATableFormat() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.ComparingResult,"Compare Result");
	}
	
	public static void ClickOnGroupDropDownIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.groupDropDownIcon, "Group By Drop Down Icon");
	}
	
	public static void SelectSentimentGroupOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.sentimentGroupOption, "Sentiment Group Option");
	}
	
	public static void VerifyAnswersAreAppearInSentimentFunctionality() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.sentimentGroupOption,"Sentiment Grouping Format");
	}
	
	public static void ClickOnRelevanceDownIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.dropDownIcon, "Relevance Drop Down Icon");
	}
	
	public static void SelectZToARelevanceOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.zToARelevanceOption, "Z To A Relevance Option");
	}
	
	public static void VerifyAnswersAreAppearInRelevanceFormating() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.RelevanceFormating,"Relevance Formating");
	}
	
	public static void ClickOnFilterOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.filterOption, "Filter Option");
	}
	
	public static void VerifyAllFilterOptionDefaultChecked() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(2000);
		GenericElements.ValidateElementIsDisplayed(hp.sourceTypeFilterCheckBox,"Source Type Filter ");
		GenericElements.ValidateElementIsDisplayed(hp.sentimentFilterCheckBox,"Sentiment Filter ");
//		GenericElements.ValidateElementIsDisplayed(hp.personFilterCheckBox,"Person Type Filter ");
		GenericElements.ValidateElementIsDisplayed(hp.organizationFilterCheckBox,"Organization Filter ");
		GenericElements.ValidateElementIsDisplayed(hp.locationFilterCheckBox,"Location Type Filter ");
		
	}
	
	public static void UncheckAllFilterOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		if(hp.clearOption.isDisplayed()) {
			ButtonHelper.click(hp.clearOption, "Source Type Filter Option");
		}else {
		ButtonHelper.click(hp.sourceTypeFilterCheckBox, "Source Type Filter Option");
		ButtonHelper.click(hp.sentimentFilterCheckBox, "Sentiment Filter Option");
		ButtonHelper.click(hp.personFilterCheckBox, "Person Filter Option");
		ButtonHelper.click(hp.organizationFilterCheckBox, "Organization Filter Option");
		ButtonHelper.click(hp.locationFilterCheckBox, "Location Filter Option");
		}
	}
	
	public static void ClickOnSourceTypeDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.sourceTypeFilterDownArrow, "Source Type Filter Down Arrow");
		
	}
	
	public static void ClickOnSourceTypeSubFilter() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sourceTypeSubFilterCheckBox, "SubFilter");
		
	}
	
	public static void ClickOnShowButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		WebElement element = GenericElements.getOldOrNewLocator(hp.showButton,hp.showBtn);
		ButtonHelper.click(element, "Show Button");
	}
	
	public static void VerifyOriginalAnswersShouldReAppear() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(2000);
		GenericElements.ValidateElementIsDisplayed(hp.answerTamplate,"Original Answer");
	}
	
	public static void ClearSearchBarIndex() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		hp.txtSearchIndex.clear();
	}
	
	public static void WriteAnyNewQuestions() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		TextBoxHelper.enterText(hp.searchOption, "New Questions", GenericHelper.getTestData("NewQuestions"));
	}
	
	public static void ClickOnLocationCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.locationFilterCheckBox, "Location Check Box");
	}
	
	public static void ClickOnLocatioonDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.locationFilterDownArrow, "Location Filter Down Arrow");
	}
	
	public static void ClickOnLocationSubFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.locationSubFilterCheckBOX, "LocationSub Filter Check Box");
	}
	
	public static void ClickOnCloseIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.closeIcon, "Close Icon");
	}
	
	public static void ClickOnSourceTypeCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sourceTypeFilterCheckBox, "Source Type Filter Check Box");
	}
	
	public static void ClickOnSentimentFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sentimentFilterCheckBox, "Sentiment Filter Check Box");
	}
	
	public static void ClickOnSentimentFilterDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sentimentFilterDownArrow, "Sentiment Filter Down Arrow");
	}
	
	public static void ClickOnSentimentSubFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.sentimentSubFilterCheckBOX, "Sentiment Sub Filter");
	}

	public static void ClickOnPersonFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.personFilterCheckBox, "Person Filter Check Box");
	}
	
	public static void ClickOnPersonFilterDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.personFilterDownArrow, "Person Filter Down Arrow");
	}
	
	public static void ClickOnPersonSubFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.personSubFilterCheckBOX, "Person Sub Filter");
	}
	
	public static void ClickOnOrganizationFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.organizationFilterCheckBox, "Organization Filter Check Box");
	}
	
	public static void ClickOnOrganizationFilterDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.organizationFilterDownArrow, "Organization Filter Down Arrow");
	}
	
	public static void ClickOnOrganizationSubFilterCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.OrganizationSubFilterCheckBOX, "Organization Sub Filter");
	}
	
	public static void VerifyNeshLogoIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.iconNesh,"Nesh Logo");
	}
	
	public static void ClickOnThumbUpResponseButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.thumbUpButton, "Thumb Up Button");
	}
	
	public static void VerifyResponseIsDisplay() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		GenericElements.ValidateElementIsDisplayed(hp.thumbResponce,"Thumb Responce");
	}

	
	public static void VerifyUserIsAbleToOpenTheLoginPageOnMobileScreen() {
		Dimension dimension = new Dimension(412, 732);
		driver.manage().window().setSize(dimension);
		int expectedResolution = 412;
		if(expectedResolution==dimension.getWidth()) {
			ObjectRepo.test.log(LogStatus.PASS, "Mobile Screen is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Mobile Screen is Not Display");
		}
	
	}
	
	public static void VerifyUserIsAbleToOpenTheLoginPageOnTabletScreen() {
		Dimension dimension = new Dimension(1024, 768);
		driver.manage().window().setSize(dimension);
		int expectedResolution = 1024;
		if(expectedResolution==dimension.getWidth()) {
			ObjectRepo.test.log(LogStatus.PASS, "Tablet Screen is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Tablet Screen is Not Display");
		}
	
	}
	
	public static void VerifyUserIsAbleToOpenTheLoginPageOnDesktopScreen() {
		driver.manage().window().maximize();
		int screenWidth =driver.manage().window().getSize().getWidth();
		int expectedResolution = 1552;
		if(expectedResolution==screenWidth) {
			ObjectRepo.test.log(LogStatus.PASS, "Desktop Screen is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Desktop Screen is Not Display");
		}
	
	}
	
	public static void HoverOnNavigationLink() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Actions action = new Actions(driver);
		action.moveToElement(hp.homeIcon).perform();
	}
	
	public static void VerifyCursorWillBeChangeOnNavigationMenu() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.homeLinkIcon,"Cursor Change");
	}
	
	public static void VerifyFactsetOptionIsDisplay() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		GenericElements.ValidateElementIsDisplayed(hp.factsetOption,"Factset Option");
	}
	
	public static void VerifyAdminAbleToAddNewCompany() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String title = driver.getTitle();
		String expectedTitle = "none";
		if(title.equals(expectedTitle)) {
			ObjectRepo.test.log(LogStatus.PASS,"Admin Able to add new company");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"I Need factet company code");
			System.out.print("I need invite code!");
			
			//pending ...
		}
	}
	
	public static void ClickOnFactsetOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.factsetOption,"Factset");
	}
	
	public static void ClickOnFactsetConnectButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(8000);
//		JavascriptExecutor js = ((JavascriptExecutor)driver);
//		js.executeScript("arguments[0].click();",hp.factsetConnectButton);
		ButtonHelper.clickJSExecutorElement(hp.factsetConnectButton,"Factset Connect Button");
	}
	
	public static void EnterFactsetCode() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomString = "";
		int length = 4;
		Random rand = new Random();
		char[] text = new char[length];
		for(int i=0; i<length; i++) {
			text[i] = character.charAt(rand.nextInt(character.length()));
		}
		for(int i=0; i<text.length; i++) {
			randomString += text[i];
		}
		Thread.sleep(5000);
		System.out.println(randomString);
		TextBoxHelper.enterText(hp.factsetCodeBox, "random word", randomString);
	}
	
	public static void VerifyUserIsAbleToAddFactsetNewCompany() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.addedMessage,"Factset Added Message");
	}
	
	
	public static void ClickOnDownArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.documentTypeDownArrow, "Down Arrow");
	}
	
	public static void ClickOnInvestorPresentationCheckBox() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.investorPresentationCheckBox, "investor Presentation CheckBox");
	}
	

	public static void ClickOnAddFactsetButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.addFactsetButton, "add Factset Button");
	}
	
	
	
	public static void ClickOnTextOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.textOption, " Text Option");
	}
	
	public static void VerifyTextOptionReletiveResultsIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.relevantSwitchButton,"Text Reletive Results");
	}
	
	public static void ClickOnImageOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.textOption, " Text Option");
	}
	
	public static void VerifyImageOptionReletiveResultsIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.relevantSwitchButton,"Image Reletive Results");
	}
	
	public static void ClickOnInsightButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(7000);
		ButtonHelper.click(hp.insightsButton, "Insights Button");
	}
	
	
	public static void VerifyBankORBanksWordIsNotDisplay() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		String answerText =  hp.entitiesContains.getText();
		 if (answerText.contains("bank")||answerText.contains("banks")) {
			 ObjectRepo.test.log(LogStatus.FAIL, "bank/banks Word Is Appear In The Suggested Section");
		 } else {
			 ObjectRepo.test.log(LogStatus.PASS, "bank/banks Word Is Not Appear In The Suggested Section");
		 }
	}
	
	public static void VerifyAnswerAppearFromTranscriptTextInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.transcriptOption,"Transcript Text");
	}
	
	public static void VerifyAnswerAppearFromTranscriptTextWithDatedFormatInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.datedFormat,"Dated Format");
	}
	
		
	
	public static void VerifyAnswerAppearFromInvestorPresentationTextInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.presentationOption,"Investor Presentation Text");
	}
	
	
	
	public static void ClickOnImageTab() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.clickJSExecutorElement(hp.imageTab,"Image Tab");
	}
	
	public static void VerifyAnswerAppearInTheImageFormatFromInvestorPresentation() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String imagesCount=hp.imageCount.getText();
		int count=Integer.parseInt(imagesCount); 
		if(count!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Images Available");
			GenericElements.ValidateElementIsDisplayed(hp.imageSection,"Image");
		}
		ObjectRepo.test.log(LogStatus.PASS,"Images not Available");
		
	}

	
	public static void VerifyAnswerAppearFromInvestorPresentationTextWithDatedFormatInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.datedFormat,"Dated Format");
	}
	
	
	public static void VerifyAnswerAppearFromRegulatoryFilingTextInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.RegulatoryOption,"Regulatory Filing Text");
	
	}
	
	
	public static void VerifyAnswerAppearInTheImageFormatFromRegulatoryFiling() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String imagesCount=hp.imageCount.getText();
		int count=Integer.parseInt(imagesCount); 
		if(count!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Images Available");
			GenericElements.ValidateElementIsDisplayed(hp.imageSection,"Image");
		}
		ObjectRepo.test.log(LogStatus.PASS,"Images not Available");
	}

	
	public static void VerifyAnswerAppearFromRegulatoryFilingTextWithDatedFormatInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.datedFormat,"Dated Format");
	}
	
	public static void VerifyAnswerAppearFromSECFilingTextInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.SECOption,"SEC Filing Text");
	}
	
	public static void VerifyAnswerAppearInTheImageFormatFromSECFiling() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String imagesCount=hp.imageCount.getText();
		int count=Integer.parseInt(imagesCount); 
		if(count!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Images Available");
			GenericElements.ValidateElementIsDisplayed(hp.imageSection,"Image");
		}
		ObjectRepo.test.log(LogStatus.PASS,"Images not Available");
		
	}
	
	
	public static void VerifyAnswerAppearFromSECFilingTextWithDatedFormatInTheTextTabUI() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.datedFormat,"Dated Format");
	}
	
	public static void ClickOnChannelIconAtSearchSection() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		WebElement element = GenericElements.getOldOrNewLocator(hp.channelDownIcon,hp.dropDownChannelIcon);
		Thread.sleep(3000);
		ButtonHelper.clickJSExecutorElement(element,"Channel Icon");
	}
	
	public static void VerifyTwoDifferentChannelsAreAvailable () {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.marketResearchChannel,"Market Research Channel");
		GenericElements.ValidateElementIsDisplayed(hp.salesGrowthChannel,"Sales Growth Channel");
	}
	
	public static void ClickOnSalesGrowthChannel() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(10000);
		ButtonHelper.click(hp.salesGrowthChannel,"Sales Growth Channel");
	}
	
	
	
	public static void VerifyAnswerIsNotAppearRelatedSalesGrowthChannel() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		GenericElements.ValidateElementIsDisplayed(hp.answerNotFoundImage,"Answer Not Found Image");
	}
	
	public static void ClickOnMarketResearchChannel() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(10000);
		ButtonHelper.click(hp.marketResearchChannel,"Market Channel");
	}
	
	public static void VerifyAnswerIsAppearRelatedMarketResearchChannel() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		GenericElements.ValidateElementIsDisplayed(hp.answerRelatedMarketResearch,"Market Research Answer");
	}
	
	public static void VerifyAnswerIsAppearFromUploadedFile(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.uploadedAnswer,"Uploaded File Answer");
	}
	
	public static void ClickOnGroupDownIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(10000);
		ButtonHelper.clickJSExecutorElement(hp.groupDownIcon,"Group Down Icon");
	}
	
	public static void VerifyTheCompanyAndTheOrganizationOptionsIsAppearSeparately(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.companyGroup,"Company Group");
		GenericElements.ValidateElementIsDisplayed(hp.organizationGroup,"Organization Group");
	}
	
	public static void SelectCompanyOptionFromGroupSection() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.companyGroup,"Company Option");
	}
	
	public static void VerifyComapnyNameOfBankIsDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.BankNamesSection,"Company Name Of Bank");
	}
	
	public static void SelectOrganizationOptionFromGroupSection() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.organizationGroup,"Organization Option");
	}
	
	public static void VerifyOrganizationNameOfBankIsDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.OrganizationNamesSection,"Organization Name Of Bank");
	}
	
	public static void VerifyLatestContantDataIsAppear(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		LocalDate today = LocalDate.now();
		String sepratedate = hp.dateSection.getText();
		String Parts[] = sepratedate.split("-");
		
		String yy = Parts[0];
		String mm = Parts[1];
		String dd = Parts[2];
		int year = Integer.parseInt(yy);
		int month = Integer.parseInt(mm); 
		int date = Integer.parseInt(dd); 
		LocalDate localdate = LocalDate.of(year,month,date);
		Period period = Period.between(localdate, today);
		int Month_day  = period.getMonths();
	    if(Month_day<6) {
	    	ObjectRepo.test.log(LogStatus.PASS,"Latest Contant Data Is Appear");
	    }else {
	    	ObjectRepo.test.log(LogStatus.FAIL,"Latest Contant Data Is not Appear");
	    }
	}
	
	public static void VerifyHistoricalContentDataIsAppear(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		String actual = hp.historicalContaint.getTagName();

		if(actual.equals("span")) {
			ObjectRepo.test.log(LogStatus.PASS,"Historical Contant Data Is Appear");
		}else {
	    	ObjectRepo.test.log(LogStatus.FAIL,"Historical Contant Data Is not Appear");
	    }
	}
	
	public static void VerifyEveryOcurrenceOfTheWordInTheQuestionNotBeHighlitedSentanceOfAnswer(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		String ele = hp.highlightedSentance.getText();
		List<WebElement> totalEle=driver.findElements(By.xpath("//*[@class='AnswerSection_highlight__31LK- ']"));
		for(WebElement e : totalEle){
			if(ele.contains("ev")) {
	    		ObjectRepo.test.log(LogStatus.FAIL,"Ocurrence Of The Word is Appear in the Highlited Sentance");
	    	}else {
	    		ObjectRepo.test.log(LogStatus.PASS,"Ocurrence Of The Word is not Appear in the Highlited Sentance");
			} 
		}
	}
	
	
	public static void ClickOnChannelIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.channelIcon,"Channel Icon");
	}
	
	public static void VerifyUserCountIsDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		String value = hp.userCount.getText();	
		String v1[] = value.split("\\s"); //for white-space removing
		String realvalue= v1[0];
		int integer_value = Integer.parseInt(realvalue);
		String typeName = ((Object)integer_value).getClass().getSimpleName();
		if(typeName.equals("Integer")) {
			ObjectRepo.test.log(LogStatus.PASS,"User Count Number Is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"User Count Number Is not Display");
		}
		
	}
	
	public static void VerifyThumbNailsIconIsDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.userThumbnails,"User Thumbnails");
	}
	

	public static void VerifyRelevantAnswerIsDiaplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		int sizeOfSection = hp.answerSection.size();
		System.out.println(sizeOfSection);
		for(WebElement el :hp.answerSection) {
			String answerTamplate= el.getText();
			if(answerTamplate.contains("finance")){
				ObjectRepo.test.log(LogStatus.PASS,"Relevant Answer Is Display");
				break;
			}
		}
		
	}
	
	public static void VerifySummaryOfAnswerIsAppear(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.summarySection,"Summary Of Answer");
	}
	
	public static void VerifyAnswerAreObtainedUnderAllChannels(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.allChannels,"All Channels");
	}
	
	public static void SwitchChannelsTypeToMarketResearch() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.marketResearchChannel,"Market Research");
	}
	
	public static void VerifyAnswerAreStillAppearingAfterSwitchingAllChannelsToMarketResearch() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.answerRelatedMarketResearch,"Answer");
	}
	
	public static void SwitchChannelsTypeToSalesGrowth() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.salesGrowthChannel,"Sales Growth");
	}
	
	public static void VerifyAnswerAreStillAppearingAfterSwitchingAllChannelsToSalesGrowth() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.answersSalesGrowth,"Answer");
	}
	

	public static void VerifyEnergyChannelIsDiplay() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.energyChannel,"Energy Channel");
	}
	
	public static void VerifyBankingChannelIsDiplay() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.bankingChannel,"Banking Channel");
	}
    
	public static void ClickOnEnergyChannel() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.energyChannel,"Energy Channel");
	}
	
	public static void VerifyUserIsAbleToSwitchTheChannelsAllToEnergyChannel() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.energyChannel,"Energy Channel");
	}
	
	public static void VerifyEnergyChannelRelatedAnswerAppears() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.RelatedAnswerSection,"Energy Channel Related Answer");
	}
	
	public static void ClickOnBankingChannel() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.bankingChannel,"Banking Channel");
	}
	
	public static void VerifyUserIsAbleToSwitchTheChannelsEnergyChannelToBanking() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.bankingChannel,"Banking Channel");
	}
	
	public static void VerifyBankingChannelRelatedAnswerAppears() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.RelatedAnswerSection,"Banking Channel Related Answer");
	}
	
	public static void VerifyAnswerIsAppearingFromBTU() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answerTitle,"BTU_ANALYTICS_HENRY_HUB_OUTLOOK","BTU Henry Hub Outlook");
	}
	
	public static void VerifyAnswerIsAppearingFromGasBasis() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answerTitle,"BTU_ANALYTICS_GAS_BASIS","GAS BASIS");
	}
	
	public static void CheckResponseCount() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		int text_value =Integer.parseInt(hp.tableResponseCount.getText()); 
		int table_value =Integer.parseInt(hp.textResponseCount.getText()); 
		int image_value =Integer.parseInt(hp.imageResponseCount.getText()); 
		
		if(text_value!=0&&table_value!=0&&image_value!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Response Count Is Grater Then Zero");
		}else {
			ObjectRepo.test.log(LogStatus.PASS,"Response Count Is Zero");
		}
	
	}
	
	public static void VerifyResponseBoxIsNotGlowing() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		try {
			if(hp.notGlowingResponseBox.isDisplayed()) {
				ObjectRepo.test.log(LogStatus.PASS,"Response Box Is Not Glowing");
			}else{
				ObjectRepo.test.log(LogStatus.FAIL,"Response Box Is Glowing");
			}
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found ");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		 }
		
	}
	
	public static void VerifyResponseBoxIsGlowing() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		try {
			if(hp.glowingResponseBox.isDisplayed()) {
				ObjectRepo.test.log(LogStatus.PASS,"Response Box Is Glowing");
			}else{
				ObjectRepo.test.log(LogStatus.FAIL,"Response Box Is Not Glowing");
			}
			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found ");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		 }
	
		
		
	}
	
	
	public static void VerifyAllUploadedFilesAreDisplay() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.showAllDownArrow,"Show ALL Down Arrow");
		if(hp.uploadedFiles.isEmpty()) {
			ObjectRepo.test.log(LogStatus.FAIL,"Files are not Uploaded");
		}else {
			for (WebElement el :hp.uploadedFiles) {
				String ele = el.getText();
				ObjectRepo.test.log(LogStatus.PASS,ele+"Is Uploaded");
			}
		}
	}
	
	public static void VerifyNERTagsIsDisplayInTheSummarySection() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.tagsNER,"NER Tags");
	}
	
	public static void VerifyAnswerIsDisplayUnderSalesGrowth() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String value = hp.totalAnswer.getText();
		String[] value1= value.split("\\s");  //white space removing
		System.out.println(value1[0]);
		int total_answer =Integer.parseInt(value1[0]); // converting  string [] to integer
		
		if(total_answer!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Answer Is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer Is Display");
		}
	}
	
	public static void VerifyContentExtractedUnderSalesGrowthFromTheNewDataUploaded(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		for(WebElement el :hp.fileName) {
			String fileName = el.getText();
			 System.out.println(fileName);
			 String maindirpath = "src\\main\\resources\\uploadedDocument\\installationmanuals";
				File maindir = new File(maindirpath);
				 if (maindir.exists() && maindir.isDirectory()) {
					 File arr[] = maindir.listFiles();
					 for(File f:arr) {
						 System.out.println(f.getName());
						 String updatedFilename = f.getName();
						 updatedFilename = updatedFilename.replaceAll(".pdf", "");
						 System.out.println(updatedFilename);
						 if(updatedFilename.equals(fileName)) {
							 ObjectRepo.test.log(LogStatus.PASS,"File Name Is Display From Updated Folder");
							 break;
						 }
					 }
				 }else {
					 ObjectRepo.test.log(LogStatus.FAIL,"File Name is not Exist");
				 }
			break;	 
		}
	}
	
	public static void VerifyAnswerIsDisplayUnderMarketResearch() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String value = hp.totalAnswer.getText();
		String[] value1= value.split("\\s");  //white space removing
		System.out.println(value1[0]);
		int total_answer =Integer.parseInt(value1[0]); // converting  string [] to integer
		
		if(total_answer!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Answer Is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer Is Display");
		}
	}
	
	public static void VerifyContentNotExtractedUnderMarketResearchFromTheNewDataUploaded(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		for(WebElement el :hp.fileName) {
			String fileName = el.getText();
			 System.out.println(fileName);
			 String maindirpath = "src\\main\\resources\\uploadedDocument\\installationmanuals";
				File maindir = new File(maindirpath);
				 if (maindir.exists() && maindir.isDirectory()) {
					 File arr[] = maindir.listFiles();
					 for(File f:arr) {
						 System.out.println(f.getName());
						 String updatedFilename = f.getName();
						 updatedFilename = updatedFilename.replaceAll(".pdf", "");
						 System.out.println(updatedFilename);
						 if(updatedFilename.equals(fileName)) {
							 ObjectRepo.test.log(LogStatus.FAIL,"File Name Is Display From Updated Folder");
							 break;
						 }
					 }
				 }else {
					 ObjectRepo.test.log(LogStatus.FAIL,"File Name is not Exist");
				 }
			 ObjectRepo.test.log(LogStatus.PASS,fileName+"  Not Matched From Updated File");
		}
	}
	
	public static void VerifyAnswerIsDisplayUnderAllOption() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String value = hp.totalAnswer.getText();
		String[] value1= value.split("\\s");  //white space removing
		System.out.println(value1[0]);
		int total_answer =Integer.parseInt(value1[0]); // converting  string [] to integer
		
		if(total_answer!=0) {
			ObjectRepo.test.log(LogStatus.PASS,"Answer Is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer Is Display");
		}
	}

	
	public static void VerifyContentExtractedUnderAllOptionFromNewDataUploadedAndOldData(){
		
		HubbellHomePage hp = new HubbellHomePage(driver);
		String updatedFilename = null;
		int index = 1;
		for (WebElement e :hp.FilesName) {
			System.out.println(hp.FilesName.size());
			System.out.println(index);
			String uploadedFileName= e.getText();
			System.out.println(uploadedFileName);
			 String maindirpath = "src\\main\\resources\\uploadedDocument\\installationmanuals";
				File maindir = new File(maindirpath);
				 if (maindir.exists() && maindir.isDirectory()) {
					 File arr[] = maindir.listFiles();
					 for(File f:arr) {
						 System.out.println(f.getName());
						 updatedFilename = f.getName();
						 updatedFilename = updatedFilename.replaceAll(".pdf", "");
						 System.out.println(updatedFilename);
						 if(updatedFilename.equals(uploadedFileName)) {
							 ObjectRepo.test.log(LogStatus.PASS,"Answer is come from new uploaded document");
							 break;
						 }
					 }
				 }else {
					 ObjectRepo.test.log(LogStatus.FAIL,"File Name is not Exist");
					 break;
				 }
				 
				 if(updatedFilename.equals(uploadedFileName)) {
					 break;
				 }
				 
				 if(index == hp.FilesName.size()) {
					 ObjectRepo.test.log(LogStatus.FAIL,"Answer is not come from relevant document");
				 }
				 
				 index++;
		}
	}
		
	
	
	public static void VerifyRelevantAnswerIsAppearingUnderHubbellSalesGrowthChannels() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answer,"electrical ","");
	}
	
	public static void VerifyAnswerAnswersObtainedAreFromBTUUpstreamOutlook(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answerTitle,"BTU_ANALYTICS_UPSTREAM_OUTLOOK","BTU Upstream Outlook");
	}
	
	public static void ClickOnKnowledgeAnalyticsIcon() throws Exception {
		KnowledgeAnalyticsPage knowledge = new KnowledgeAnalyticsPage(driver);
		ButtonHelper.click(knowledge.KnowledgeAnalyticsIcon, "Knowldge Analytics Icon");
	}
	
	public static void VerifyToggleSwitchIsAvailable() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.toggleIcon,"Toggle Icon");
	}
	
	public static void ClickOnActiveKnowledge() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.activeKnowledge, "Active Knowledge");
	}
	
	public static void VerifyChannelsAndUsersAreVisible() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.marketReSearchChannel,"Market Research Channel");
		GenericElements.ValidateElementIsDisplayed(hp.Users,"Users");
	}
	
	public static void ClickOnKnowledgeGaps() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.KnowledgeGaps, "Knowledge Gaps");
	}
	
	public static List<String> GetInformationFromActiveKnowledge() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String Channel = hp.marketReSearchChannel.getText();
		String Users = hp.Users.getText();
		return Arrays.asList(Channel,Users);
	}
	
	public static List<String> GetInformationFromKnowledgeGaps() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.KnowledgeGaps, "Knowledge Gaps");
		String Channel = hp.marketReSearchChannel.getText();
		String Users = hp.Users.getText();
		return Arrays.asList(Channel,Users);
	}
	
	public static void VerifyInformationAreSame() throws Exception {
		if(GetInformationFromKnowledgeGaps().equals(GetInformationFromActiveKnowledge())) {
			 ObjectRepo.test.log(LogStatus.PASS,"Both Information are Same");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL,"Both Information are Not Same");
		}
	}
	
	public static void VerifyAnswerIsAppearingFromLongTermGasOutlook() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answerTitle,"BTU_ANALYTICS_LONG_TERM_GAS_OUTLOOK","BTU ANALYTICS LONG TERM GAS OUTLOOK");
	}
	
	public static void VerifyUpVotedAnswersAreAppearingAtTheTop() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.upVotedIcon,"UpVoted Answer");
	}
	
	public static void VerifyAnswerIsAppearingFromTheDeloitteDocuments() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.relatedUploadedAnswer.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Answers Are Appearing From The Deloitte Documents");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Answers Are Not Appearing From The Deloitte Documents");
		}
	}
	
	public static void VerifyAnswerIsAppearingFromOilMarketOutlook() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answerTitle,"BTU_ANALYTICS_OIL_MARKET_OUTLOOK","BTU ANALYTICS OIL MARKET OUTLOOK");
	}
	
	public static void VerifyBestAnswerIsAppearAtTheTopPosition() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		int Total_Answer = hp.fileName.size();
		String total = Integer.toString(Total_Answer);
	    String Top_positions = hp.fileName.get(0).getText();
		if( hp.relatedUploadedAnswer.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Total" +"'"+total+"'"+"List Of Answers Are Appear" );	
			ObjectRepo.test.log(LogStatus.PASS,"At The Top Position Answer is "+"'"+Top_positions+"'");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"List Of Answers Are Not Appearing" );
		}
			
	}
	
	public static void VerifyNewestAnswerIsAppearAtTheTopPosition() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		LocalDate today = LocalDate.now();
		String sepratedate = hp.newestAnswer.getText();
		String Parts[] = sepratedate.split("-");
		String yy = Parts[0];
		String mm = Parts[1];
		String dd = Parts[2];
		int year = Integer.parseInt(yy);
		int month = Integer.parseInt(mm); 
		int date = Integer.parseInt(dd); 
		LocalDate localdate = LocalDate.of(year,month,date);
		Period period = Period.between(localdate, today);
		int Month  = period.getMonths();
		if(Month<6) {
			ObjectRepo.test.log(LogStatus.PASS,"Newest Answer Is Appear At The Top Position");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Newest Answer Is not Appear At The Top Position");
		}
	}
	
	public static void VerifyRelevantResponseSummaryIsDisplay() throws InterruptedException{
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		for(WebElement ele :hp.summaryOfResponse) {
			String ele_text = ele.getText();
			if(ele_text.contains("takeaway")) {
				ObjectRepo.test.log(LogStatus.PASS,"Question Related Answer is display");
				break;
			}
			
		}
			
	}

	public static void ClickOnMoveUpArrow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(2000);
	    ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		ButtonHelper.click(hp.moveUpArrow, "Move Up Arrow");
	}
	
	public static String GetWebElement() {
		HubbellHomePage hp = new HubbellHomePage(driver);
        String Filename =  hp.FilesName.get( hp.FilesName.size() - 1).getText();
        System.out.println(hp.FilesName.size() - 1);
        System.out.println(Filename);
        return Filename;
    }
	
	public static void VerifyUpvotedAnswerAppearAtTheTopForMutlipleUsersButAnswerIsNotActiveForAnotherUsers() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ProfilePage pp = new ProfilePage(driver);
		String getUpvotedAnswer = GetWebElement();
		System.out.println(getUpvotedAnswer);
		ClickOnProfileButton();
		ClickOnLogOutButton();
		ButtonHelper.click(pp.logOutBtn, "Log Out Button");
		ButtonHelper.click(hp.iconClose, "Close Icon");
		Thread.sleep(2000);
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "User Name", GenericHelper.getTestData("EmailId"));
		TextBoxHelper.enterText(lp.txtPassword, "Password", GenericHelper.getTestData("Password"));
		ButtonHelper.click(lp.btnLogin, "Login Button");
		TextBoxHelper.enterText(hp.txtSearchIndex, "Write Any Question", GenericHelper.getTestData("TextInputData"));
		ButtonHelper.click(hp.searchButton, "Search Button");
		
		List<String> all_elements_text=new ArrayList<>(hp.fileName.size());
   
		for (int i=0; i<=hp.fileName.size(); i++) {
			all_elements_text.add(hp.fileName.get(i).getText());
			
			if(all_elements_text.contains(getUpvotedAnswer)) {
				int indexvalue = all_elements_text.indexOf(getUpvotedAnswer);
				System.out.println(indexvalue);
				int TotalUpvotedIndex = hp.upvoteCount.size();
			    if(indexvalue<=TotalUpvotedIndex && hp.VotedCount.isDisplayed()) {
			    	ObjectRepo.test.log(LogStatus.PASS,"Answer is appear at the Top List and Answer already voted");
			    	break;
			    } else {
		    		ObjectRepo.test.log(LogStatus.FAIL,"Answer is not appear at the Top List");
		    		break;
			    }
			}
			
		}
	}
	
	public static void VerifyMultipleAnswerisHighlightedAppear(){
		List<WebElement> totalEle=driver.findElements(By.xpath("//*[@class='AnswerSection_highlight__31LK- ']"));
		int totalSize = totalEle.size();
		
		for(WebElement ele : totalEle){
			int indexsize = totalEle.indexOf(ele);
			if(ele.getText().contains("Plan")) {
	    		ObjectRepo.test.log(LogStatus.PASS,"Total:"+totalSize+"Related Answers are highlight Appear at the Response Screen");
	    		break;
	    	}else if(totalSize==indexsize){
	    		ObjectRepo.test.log(LogStatus.FAIL,"Responses are not Appear");
			} 
		}
	}
	
	public static void VerifyExpectedAndRelevantAnswerForTheQuestionAppearAtTheTop(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		List<WebElement> totalEle=hp.topAnswer;
		for(WebElement ele : totalEle){
			int indexsize = totalEle.indexOf(ele);
			if(ele.getText().contains("Mountain Valley") && indexsize==0 ) {
	    		ObjectRepo.test.log(LogStatus.PASS,"Expected And Relevant Answer For The Question Appear At The Top Position::"+(indexsize+1));
	    		break;
	    	}else{
	    		ObjectRepo.test.log(LogStatus.FAIL,"Responses are not Appear");
			} 
		}
	}
	
	public static void VerifyKnowledgeGapPercentageIsVisible() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.knowledgeGapPercentage,"Knowledge Gap Percentage");
	}
	
	public static void VerifyListOfQuestionsAreVisible() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String listOfQuestion= hp.availableQuestion.getText();
		int listofTotalQuestion = Integer.parseInt(listOfQuestion);
		System.out.println((((Object)listofTotalQuestion).getClass().getSimpleName()));
		if(hp.availableQuestion.isDisplayed()) {
    		ObjectRepo.test.log(LogStatus.PASS,"Total List of Question is"+ " "+listofTotalQuestion);
    	}else{
    		ObjectRepo.test.log(LogStatus.FAIL,"List Of Question is not visible");
		} 
	}
	
	public static void VerifyUpvoteCountisVisibleNextToTheUpvoteArrow() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.VotedCount.isDisplayed()) {
			for(WebElement el : hp.upvoteCount) {
				int TotalCountOfUpvote = Integer.parseInt(el.getText());
				ObjectRepo.test.log(LogStatus.PASS,"Total Upvote Count Is"+ " "+TotalCountOfUpvote);
				break;
			}
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer is not Upvote");
		}
	}
	
	public static void VerifyHighlitedTextIsReadable() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		System.out.println(hp.answerTextArea.getText());
		Actions act = new Actions(driver);
		act.doubleClick(hp.answerTextArea).build().perform();
		if(hp.answerTextArea.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Hilighted text is Readable");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Hilighted text is not Readable");
		}
	}

	public static void selectdownvotedQuestion() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String listOfQuestion= hp.availableQuestion.getText();
		int listofTotalQuestion = Integer.parseInt(listOfQuestion);
		if(listofTotalQuestion>0) {
			hp.downvotedQuestion.click();
			hp.answerQuestionButton.click();
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"downvoted Question is not available");
		}
	}
	
	public static void VerifyMySelfOptionIsDisplay() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.myselfOption,"My Self Option");
	}
	 
	public static void ClickOnMySelfOptionCheckBox() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.myselfOptionCheckBox, "My Self Option Check Box");
	}
	
	public static void ClickOnAssignButton() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		try {
			if(hp.assignButton.isDisplayed()) {
				ButtonHelper.click(hp.assignButton, "Assign Button");
			}
		}catch (Exception e) {
			ButtonHelper.click(hp.assignBtn, "Assign Button");
		}
	}
	
	public static void VerifyQuestionAnswerPageIsDisplay() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.questionAnswerPage,"Question Answer Page");
	}
	 
	public static void VerifyUserIsAbleToSeeAndSelectTheQuestionTheyHadLikeToAnswer() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.questionAnswerPage,"Question Answer Page");
		if(hp.downvotedQuestions.isDisplayed()) {
			ButtonHelper.click(hp.downvotedQuestions, "Down Voted Questions");
			ObjectRepo.test.log(LogStatus.PASS,"User is able to see and select the question");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"User is not able to see and select the question");
		}
	}
	
	public static void VerifyAnswerAbleToBeWrittenPlainAndNumberedAndBulletedText() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
	    GenericElements.ValidateElementIsDisplayed(hp.answerTextField,"Plain Text Field");
	    GenericElements.ValidateElementIsDisplayed(hp.bulletIcon,"Bullet Field");
	    GenericElements.ValidateElementIsDisplayed(hp.NumberedIcon,"Number Field");
	}
	
	public static void EnterTheAnswerToTheQuestion() {
		QuestionPage qp = new QuestionPage(driver);
		TextBoxHelper.enterText(qp.answerTextField, "Answer", GenericHelper.getTestData("Answer"));
	}
	

	public static void ClickOnSubmitButton() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
	    int btn	= hp.submitbtn.size();
		if(btn==1) {
			for(WebElement ele : hp.submitbtn) {
				ButtonHelper.click(ele, "Submit Button");
				break;
			}
		}else {
			ButtonHelper.click(hp.submitButton, "Submit Button");
		}
	}
	
	public static void VerifyQuestionAppearInTheAnsweredTab() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		boolean value = hp.answeredTab.isDisplayed();
		WebElement ele =  driver.findElement(By.xpath("(//*[text()='“"+GenericHelper.getTestData("TextInputData")+"“'])[1]"));
		boolean ElementValue = ele.isDisplayed(); 
		if(value&&ElementValue==true) {
			ObjectRepo.test.log(LogStatus.PASS, "Question Is moved in the answered Tab");
		}else {
			ObjectRepo.test.log(LogStatus.PASS, "Question Is not moved in the answered Tab");
		}
	}
	
	public static void ClickOnQuestionsIcon() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.QuestionsIcon, "Questions Icon");
	}
	
	public static void ClickOnAnsweredTab() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.answeredTab, "Answered Tab");
	}
	
	public static void ClickOnEditButton() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		ButtonHelper.click(hp.editButton, "Edit Button");
	}
	
	public static void VerifyAnswerIsEditable() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String edittext= "try";
		TextBoxHelper.enterText(hp.answerTextField, "Answer", edittext);
		String updatedText =hp.answerTextField.getText();
		if(edittext.equals(updatedText)) {
			ObjectRepo.test.log(LogStatus.PASS,"Answer Is Editable");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL,"Answer Is not Editable");
		}
	}
	
	public static void VerifyExpertAnswerIsAppearWithTheExpertName() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
	    GenericElements.ValidateElementIsDisplayed(hp.expertName,"Expert Name");
	}
	
	public static void VerifyNeshUnderstandEPsAreCompaniesAndPEIsPrivateEquity(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		List<WebElement> totalEle=hp.topAnswer;
		for(WebElement ele : totalEle){
			if(ele.getText().contains("private equity")) {
	    		ObjectRepo.test.log(LogStatus.PASS,"Nesh Understand EPs Are Companies And PEIs Private Equity");
	    		break;
	    	}else{
	    		ObjectRepo.test.log(LogStatus.FAIL,"Responses are not Appear");
			} 
		}
	}
	
	
	public static void ClickOnBasinOption() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.basin, "Basin");
		Thread.sleep(3000);
	}
	
	public static void VerifyEachBasinHaveItsOwnUniqueSummary() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.searchButton, "button");
		List<WebElement> totalEle=hp.basinOption;
		System.out.println(totalEle.size());
		for(int i = 1; i<=totalEle.size(); i++) {
			System.out.println("(//*[@class='TabsCollection_items__BKUu6']/*)["+i+"]");
			WebElement ele =driver.findElement(By.xpath("(//*[@class='TabsCollection_items__BKUu6']/*)["+i+"]"));
			String BasinTab = ele.getText();
			ButtonHelper.click(ele, BasinTab);
			Thread.sleep(3000);
			if(hp.summarySection.isDisplayed()){
				ObjectRepo.test.log(LogStatus.PASS,"Summary Of "+BasinTab+"is Display");
				break;//pn
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,"Summary Of "+BasinTab+"is not Display");
			}
		}
	}
	
	public static void VerifyProductSupportChannelsIsVissible(){
		HubbellHomePage hp = new HubbellHomePage(driver);
	    GenericElements.ValidateElementIsDisplayed(hp.productSupportChannels,"Product Support Channels");
	}
	
	public static void VerifyAnswersComeFromRelevantDocument(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		String updatedFilename = null;
		int index = 1;
		for (WebElement e :hp.FilesName) {
			System.out.println(hp.FilesName.size());
			System.out.println(index);
			String uploadedFileName= e.getText();
			System.out.println(uploadedFileName);
			String maindirpath = "src\\main\\resources\\uploadedDocument\\relevantDocument";
			File maindir = new File(maindirpath);
				if (maindir.exists() && maindir.isDirectory()) {
					 File arr[] = maindir.listFiles();
					 for(File f:arr) {
						 System.out.println(f.getName());
						 updatedFilename = f.getName();
						 updatedFilename = updatedFilename.replaceAll(".pdf", "");
						 System.out.println(updatedFilename);
						 if(updatedFilename.equals(uploadedFileName)) {
							 ObjectRepo.test.log(LogStatus.PASS,"Answer is come from relevant document");
							 break;
						 }
					 }
				 }else {
					 ObjectRepo.test.log(LogStatus.FAIL,"File Name is not Exist");
					 break;
				 }
				 
				 if(updatedFilename.equals(uploadedFileName)) {
					 break;
				 }
				 
				 if(index == hp.FilesName.size()) {
					 ObjectRepo.test.log(LogStatus.FAIL,"Answer is not come from relevant document");
				 }
				 
				 index++;
		}
	}
	
	public static void SwitchToggleIconToTextToTable() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.tableFormat, "Table Format");
	}
	
	public static void VerifyAnswersAreAppearingInImageTableFormat(){
		HubbellHomePage hp = new HubbellHomePage(driver);
	    GenericElements.ValidateElementIsDisplayed(hp.imageFormat,"Screenshot Table Format");
	}
	
	public static void ClickOnCardLink() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.cardLink, "Card Link");
	}
	
	public static void VerifyimagesexpandWhenUsersClickOnTheCardLink(){
		HubbellHomePage hp = new HubbellHomePage(driver);
	    GenericElements.ValidateElementIsDisplayed(hp.expandScreenshot,"Expanded Screenshot");
	}

	public static void ClickOnUpvoteIcon() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.upvoteIcon, "Upvote Icon");
	}
	
	public static void VerifyUpvotingCountingIsDisplayOnThePage() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String TotalUpvoteCount = hp.VotedCount.getText();
	    GenericElements.ValidateElementIsDisplayed(hp.VotedCount,TotalUpvoteCount+" "+"Upvoting Count");
	    hp.upvotedIcon.click();
	}
	
	public static void VerifyRelatedAnswerIsAppearingWithTheAppearingQuestion() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.RelatedAnswerSection,"Appearing Question Related Answer");
	}
	
	public static void VerifyMarketResearchAndProductSupportAndRegsAndPoliciesChannelsCardsAreDisplayOnTheChannelsPageOrKnowledgeAnalyticsPage() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.marketResearchChannel,"market Research Channels Cards");
		GenericElements.ValidateElementIsDisplayed(hp.productSupportChannels,"product Support Channels Cards");
		GenericElements.ValidateElementIsDisplayed(hp.regsAndPoliciesChannels,"Regs And Policies Channels Cards");
	}
	
	public static void VerifyPreviouslyAskedQuestionsPopulateInTheList() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.searchPopulateList,"Previously Asked Questions");
	}
	
	
	public static void SelectNewestOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.newestOption, "Newest Option");
		
	}
		
	public static void ClickOnAnswerSourceLink() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.questionList, "Anser Source Link");
	}
	
	public static void ClickOnBackButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.backButtonLink, "Back Button Link");
	}
	
	public static void VerifyShotedOptionNotChangeUntilchangedAgain() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		String shoted = hp.shoteOption.getText();
		if(shoted.equals("Newest")) {
			ObjectRepo.test.log(LogStatus.PASS,shoted +" Is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,shoted +" Is Not Displayed");
		}

	}
	
	
//	----------------------------------------------New UI---------------------------------------------------
	
	public static void SelectExploreAndAksOption() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		String channelsName =GenericHelper.getTestData("Channel");
		WebElement element =driver.findElement(By.xpath("(//*[text()='"+channelsName+"'])[1]"));
		ButtonHelper.click(element, channelsName);
		
		
//		ButtonHelper.click(themepage.exploreAndAskCard, "Explore And Ask Card");
		
	}
	
	public static void ClickOnNextButton() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.TAB).build().perform();
//		action.sendKeys(Keys.TAB).build().perform();
//		action.sendKeys(Keys.ENTER).build().perform();
		ButtonHelper.click(themepage.nextButton, "Next Button");
	}
	
	
	public static void ClickOnSearchBar() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		ButtonHelper.click(themepage.searchBar, "Search Bar");
	}
	
	public static void VerifyThreeDifferentSuggestionsCatagoriesIsDisplayInsideTheSearchBar() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		GenericElements.ValidateElementIsDisplayed(themepage.suggestionsField,"suggestions Field");
		GenericElements.ValidateElementIsDisplayed(themepage.popularquestionsField,"Popular Questions Field");
		GenericElements.ValidateElementIsDisplayed(themepage.myQuestionsField,"My Questions Field");
		
	}

	public static void ClickOnPersonThemeTab() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		ButtonHelper.click(themepage.personTab, "Person Tab");
	}
	
	public static void ClickOnThemeTab() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		ButtonHelper.click(themepage.themeTab, "Theme Tab");
	}
	
	public static void ClickOnTopicCard() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		ButtonHelper.click(themepage.topicCard, "Topic Card");
	}
	
	public static void VerifyMinimumThreeOrMoreTopicIsDisplayInsideTopPassagesPortion() {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		int totalTopic =themepage.topPassages.size();
		if(totalTopic >= 3) {
			for(WebElement e: themepage.topPassages) {
				String topic = e.getText();
				ObjectRepo.test.log(LogStatus.PASS,"Topic Name is : "+topic);
			}
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Topic Less Than : 3");
		}
		
	}
		
		public static void VerifyMinimumFiveQuestionIsDisplayInsideTheSuggestionQuestionField() {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			int suggestionQuestion =themepage.suggestionQuestion.size();
			if(suggestionQuestion >= 5) {
				ObjectRepo.test.log(LogStatus.PASS,"Total Suggestion Question is : "+ suggestionQuestion);
				for(WebElement e: themepage.suggestionQuestion) {
					String Question = e.getText();
					ObjectRepo.test.log(LogStatus.PASS,"Suggestion Question is : "+Question);
				}
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,"Suggestion Question : 5");
			}
		}
			
			
		public static void VerifyOtherThemesAndNerTagsAreAppearInsideRecommandedTopicField() {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			int indexNum = 0;
				for(WebElement e: themepage.NERTag) {
					if(e.isDisplayed()) {
						String NERTagName = e.getText();
						ArrayList<String> arrayNERTag = new ArrayList<String>();
						arrayNERTag.add("Environment");
						arrayNERTag.add("Social");
						arrayNERTag.add("Governance");
						arrayNERTag.add("Person");
						arrayNERTag.add("Organization");
						arrayNERTag.add("Location");
						indexNum++;
						System.out.println(indexNum);
						for(int j=0; j<arrayNERTag.size(); j++) {
							System.out.println(arrayNERTag.get(j));
							if(NERTagName.equals(arrayNERTag.get(j))) {
								ObjectRepo.test.log(LogStatus.PASS,NERTagName +" Tag Is Display ");
								String indexnumber=String.valueOf(indexNum);
								WebElement theme = driver.findElement(By.xpath("(//*[@class='ImageCard_title__3xtrK']//h3)["+indexnumber+"]"));
								String themeName = theme.getText();
								ObjectRepo.test.log(LogStatus.PASS,themeName +" Theme Is Display ");
								break;
							}
							else if(j+1==arrayNERTag.size()) {
								ObjectRepo.test.log(LogStatus.FAIL,NERTagName +" Tag Is Not Display ");
							}
						}
					}else {
						ObjectRepo.test.log(LogStatus.FAIL, e.getText() +" Tag Is Not Display ");
					}
					
				}
		}
	
		public static void VerifyTopicTrendAndTopPassageHeadingIsDisplay() throws Exception {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			GenericElements.ValidateElementIsDisplayed(themepage.topicTrend,"Top Trend Heading");
			GenericElements.ValidateElementIsDisplayed(themepage.topPassage,"Top Passage Heading");
		}
	
	
		public static void VerifyRadioButtonIsDisplayInsideExploreAndAudit() throws Exception {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			GenericElements.ValidateElementIsDisplayed(themepage.radioButton,"Radio");
		}
		
	
		public static void VerifyAllThemeListIsDisplay() {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			for(WebElement element : themepage.themes) {
				String elementName  = element.getText();
				GenericElements.ValidateElementIsDisplayed(element,elementName+" Theme");
			}
		}
		
		public static void VerifyAllDataConnectorsIsArrangedAlphabeticallyOrder() throws Exception {
			DataManagerLocatorPage dmlp = new DataManagerLocatorPage(driver);
			
			List<String> actualList = new ArrayList<String>();
			for(WebElement element : dmlp.connectorsOption) {
				String dataConnectorName = element.getText();
				actualList.add(dataConnectorName);
			}
			
			List<String> tempList = new ArrayList<String>();
			tempList.addAll(actualList);
			Collections.sort(tempList);//Ascending order
			if(actualList.equals(tempList)) {
				ObjectRepo.test.log(LogStatus.PASS, "Data Connectors Is Arranged Alphabetically Order");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, "Data Connectors Is Not Arranged Alphabetically Order");
			}
			
		}
		
		public static void VerifyAllCardIsHighlightedWithPurpalColor() {
			 HubbellHomePage hp = new HubbellHomePage(driver);
			    String purpleColour= "#8854f7";
			    Actions actions = new Actions(driver);
			    actions.moveToElement(hp.highlightColor).build().perform();
				String actualcolor  = hp.highlightColor.getCssValue("color");
				System.out.println(actualcolor); 
			    actualcolor = Color.fromString(actualcolor).asHex();
				if(actualcolor.equals(purpleColour)) {
					ObjectRepo.test.log(LogStatus.PASS,"Topic card Highlighted with Purple colour" );
				}else {
					ObjectRepo.test.log(LogStatus.FAIL,"Topic card Not Highlighted with Purple colour" );
				}
				
				// note :: this functionality removed in the webpage
		}
		
		public static void VerifyAvatarSelectOptionIsDisplayBeforeAskingQuestion() throws Exception {
			HubbellLoginPage lp = new HubbellLoginPage(driver);
			GenericElements.ValidateElementIsDisplayed(lp.avatarSelectOption,"Avatar Select Option");
		}
		
		public static void ClickOnFavoritesButton() throws Exception{
			HubbellHomePage hp = new HubbellHomePage(driver);
			ButtonHelper.click(hp.favoritesIcon, "favorites Button ");
			
		}
		
		public static void VerifyFavoritesPageIsDisplay() throws InterruptedException {
			GenericElements.VerifyPageURL("favorites","Favorites Page");
			
		}
		
		public static void VerifyNextButtonIsVissibleIn1728X1117ScreenResolution() throws InterruptedException {
			
				HubbellHomePage hp = new HubbellHomePage(driver);
				Dimension dimension = new Dimension(1728, 1117);
				driver.manage().window().setSize(dimension);
				
				int expectedResolutionWidth = 1728;
				int expectedResolutionHight = 1117;
				int width = dimension.getWidth();
				int height = dimension.getHeight();
				if(expectedResolutionWidth==width && expectedResolutionHight==height) {
					if(hp.nextButton.isDisplayed()) {
						ObjectRepo.test.log(LogStatus.PASS, "Next Button Is Vissible In 1728X1117 Screen Resolution");
					}else {
						ObjectRepo.test.log(LogStatus.FAIL, "Next Button Is Not Vissible In 1728X1117 Screen Resolution");
					}
					
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, "Next Button Is Not Vissible In 1728X1117 Screen Resolution");
				}
		}
		
		
		public static void VerifyThatCardsGridGapAreEquals() {
			HubbellHomePage hp = new HubbellHomePage(driver);
			String ExpectedGridRowGap = "32px";
			String ExpectedGridColumnGap = "32px";
			String actualGridColumnGap  = hp.gridGap.getCssValue("column-gap").toString();
			String actualGridRowGap  = hp.gridGap.getCssValue("row-gap").toString();
			assertEquals(ExpectedGridRowGap, actualGridRowGap);
			if(ExpectedGridRowGap.equals(actualGridRowGap) && ExpectedGridColumnGap.equals(actualGridRowGap)) {
				ObjectRepo.test.log(LogStatus.PASS, "Topic Card Grid Gaps are Equal");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, "Topic Card Grid Gaps are Not Equal");
			}
		}
		
		
		public static void VerifyKnowledgeknowledgeAnalyticsTitleIsDisplay() throws Exception {
			KnowledgeAnalyticsPage lp = new KnowledgeAnalyticsPage(driver);
			GenericElements.ValidateElementIsDisplayed(lp.knowledgeAnalyticsTitle,"Knowledge Analytics Title");
		}
		
		public static void VerifyTopPassagesAndImagesISDisplay() {
			HubbellHomePage hp = new HubbellHomePage(driver);
			
			for(WebElement el : hp.fileName) {
				if(el.isDisplayed()) {
					String text = el.getText();
					ObjectRepo.test.log(LogStatus.PASS, "Top Passages : "+text);
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, "Passages Not Display");
				}
			}
			for(WebElement el : hp.textImages) {
				if(el.isDisplayed()) {
					ObjectRepo.test.log(LogStatus.PASS, "Top Passages And Images Display");
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, "Passages And Images is Not Display");
				}
			}
		}
		
	  public static void SelectProductSupportchannel() throws Exception {
		  GenericHelper.selectoptionfromdropDown("Product Support");
	  }
		
	  
	  public static void VerifyTheAnswerObtainedFromTheDocumentAndAlsoObtainedFromImages() throws Exception {
		  HubbellHomePage hp = new HubbellHomePage(driver);
		  
		  for(WebElement element : hp.fileName) {
			  String documentName = element.getText();
			  if(element.isDisplayed()) {
				  ObjectRepo.test.log(LogStatus.PASS, "Document Name Is : " + documentName);
				  ButtonHelper.click(element, documentName);
				  ButtonHelper.click(hp.imageSwitchButton, "Image Switched Button");
				  Thread.sleep(2000);
				  GenericElements.ValidateElementIsDisplayed(hp.screenShortImage,"Document Screenshot Image");
				  break;
			  }else {
				  ObjectRepo.test.log(LogStatus.FAIL, "Document Name Is Not Displayed");
			  }
		  }
	  }
	  
	  public static ArrayList<String> get_ListOfAllThemeName() throws InterruptedException {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ArrayList<String> arrayNERTaglist = new ArrayList<String>();
		  String NERTagName;
		  Thread.sleep(5000);
		  for(WebElement e: themepage.themeName) {
				NERTagName = e.getText();
				arrayNERTaglist.add(NERTagName);
			}
		  System.out.println(arrayNERTaglist.size());
		  return arrayNERTaglist;
	  }
	  

			
	  public static void VerifyAllTopicsAreIncludedInAllThemeTab() throws InterruptedException {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ArrayList<String> list =get_ListOfAllThemeName();
		  System.out.println(list.size());
		  ArrayList<String> expectedThemelist = new ArrayList<String>();
		  ArrayList<String> updatedlist =  GenericElements.removeDuplicates(list);
		  System.out.println("set size : "+updatedlist.size());
		  for (WebElement element : themepage.allThemes) {
			  String ThemesName =  element.getText();
			  expectedThemelist.add(ThemesName);
		  }
		  ArrayList<String> matchList = new ArrayList<String>();
		  for(int i=0; i<expectedThemelist.size(); i++){
			 
			  for(int j=0; j<updatedlist.size(); j++) {
				 if(expectedThemelist.get(i).equals(updatedlist.get(j))) {
					 matchList.add(updatedlist.get(j));
					 continue;
				 }
			  }
		  }
		
		  if(matchList.equals(expectedThemelist)) {
			  ObjectRepo.test.log(LogStatus.PASS, "All Themes are included in All Theme Tab");
		  }else {
			  ObjectRepo.test.log(LogStatus.FAIL, "All Themes are not included in All Theme Tab");
		  }
		
		}
	  
	  public static void VerifyTopicsAreChangeWhenOtherAvatarsWereSelected() throws Exception {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  HubbellHomePage hp = new HubbellHomePage(driver);
		  String text = themepage.topicName.getText();
		  hp.dropDownChannelIcon.click();
//		  ClickOnChannelIconAtSearchSection();
		  GenericHelper.selectoptionfromdropDown("Product Support");
		  ButtonHelper.click(hp.searchButtonIcon,"Search");
		  Thread.sleep(2000);
		  String latusText = themepage.updatedtopicName.getText();
		  if(text.equals(latusText)) {
			  ObjectRepo.test.log(LogStatus.FAIL, "Topic Name Is not Changed");
		  }else {
			  ObjectRepo.test.log(LogStatus.PASS, "Topic Name Changed");
		  }
		 
	  }
	  
	  public static void ClickOnEnvironmentalThemeTab() throws Exception{
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ButtonHelper.click(themepage.environmentalTopicTab, "Environmental Topic Tab");
			
	  }
	  
	  public static void ClickOnEnvironmentalTopic() throws Exception{
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			ButtonHelper.click(themepage.environmentalTopic, "Environmental Topic");
	  }
	  
	  public static ArrayList<String> GetChangedPassages() {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ArrayList<String>list = new ArrayList<String>();
		  for(WebElement ele : themepage.changedPassages) {
			  String element = ele.getText();
			  list.add(element);
		  }
		return list;
	  }
	  
	 
	  
	  public static void VerifyPassagesAreChangeWhenOtherAvatarsWereSelected() throws Exception {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ArrayList<String> list = new ArrayList<String>();
		  ArrayList<String> passagesList = new ArrayList<String>();
		  
		  for(WebElement el : themepage.passageName) {
			  String element  = el.getText();
			  list.add(element);
		  }
		  ClickOnDropDownChannelIcon();
		  GenericHelper.selectoptionfromdropDown("Product Support");
//		  ButtonHelper.click(hp.searchButtonIcon,"Search");
		  Thread.sleep(2000);
		  passagesList = GetChangedPassages();
		  
		  for (int i= 0; i<list.size(); i++) {
			  System.out.println("old list is ::  "+list.get(i));
		  }
		  
		  for (int i= 0; i<passagesList.size(); i++) {
			  System.out.println("new list is ::  "+passagesList.get(i));
		  }
		  
		  
		  if(list.equals(passagesList)) {
			  ObjectRepo.test.log(LogStatus.FAIL, "Passages Name Is not Changed");
		  }else {
			  ObjectRepo.test.log(LogStatus.PASS, "Passages Name Changed");
		  }
		 
	  }
	  
	  public static ArrayList<String> GetChangedTopic() throws InterruptedException {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  ArrayList<String>list = new ArrayList<String>();
		  for(WebElement ele : themepage.allTopic) {
			  String element = ele.getText();
			  list.add(element);
		  }
		return list;
	  }
	  
	  public static void VerifyTopicAreDisappearWhenOtherAvatarsWereSelected() throws Exception {
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  HubbellHomePage hp = new HubbellHomePage(driver);
		  ArrayList<String> list = new ArrayList<String>();
		  ArrayList<String> topiclist = new ArrayList<String>();
		  Thread.sleep(2000);
		  for(WebElement ele : themepage.allTopic) {
			  String element = ele.getText();
			  list.add(element);
		  }
		  try {
			  ClickOnDropDownChannelIcon();
			  GenericHelper.selectoptionfromdropDown("Product Support");
			  ButtonHelper.click(hp.searchButtonIcon,"Search");
			  topiclist =  GetChangedTopic();
			  if(list.size()==topiclist.size()) {
				  if(list.equals(topiclist)) {
					  ObjectRepo.test.log(LogStatus.FAIL, "Same Topic is appear"); 
				  }else {
					  ObjectRepo.test.log(LogStatus.PASS, "Defferent Topic is appear"); 
				  }
				  
			  }else {
				  ObjectRepo.test.log(LogStatus.PASS, "Defferent Topic is appear");  
			  }
		  }catch (Exception e) {
			  ObjectRepo.test.log(LogStatus.FAIL, "Not Found Element"); 
		}
		  
	  }
	  
	 public static void ClickOnDropDownChannelIcon() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.dropDownChannelIcon, "drop Down ChannelIcon");
	 }
	 
	 public static void SelectSalesGrowthAvatar() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 GenericHelper.selectoptionfromdropDown("Sales Growth");
		 ButtonHelper.click(hp.searchButtonIcon,"Search");
	 }
	 
	
	 public static void VerifyAnswerWouldBeChangeWhenSwitchToSaleAvatar() throws Exception {
		  HubbellHomePage hp = new HubbellHomePage(driver);
			 ArrayList<String> fileList = new ArrayList<String>();
			 System.out.println("previeus size :"+hp.answerFiles.size());
			 for(WebElement element : hp.answerFiles ) {
				 String text = element.getText();
				 fileList.add(text);
			 }
		  
			ClickOnDropDownChannelIcon();
			SelectSalesGrowthAvatar();
		  
		  ArrayList<String> latestFile = new ArrayList<String>();
		  for(WebElement element : hp.UpdatedAnswerFiles ) {
				 String text = element.getText();
				 latestFile.add(text);
			 }
		  
		  if(fileList.equals(latestFile)) {
			  ObjectRepo.test.log(LogStatus.FAIL, "Answer Is not Changed");
		  }else {
			  ObjectRepo.test.log(LogStatus.PASS, "Answer Name Changed");
		  }
		 
	 }
	 
	 public static void VerifyThreeCardIsDisplayedOnSelectAvatarPage() throws Exception {
			HubbellLoginPage lp = new HubbellLoginPage(driver);
			GenericElements.ValidateElementIsDisplayed(lp.marketResearchCard,"Market Research Card");
			GenericElements.ValidateElementIsDisplayed(lp.productSupportCard,"Product Support Card");
			GenericElements.ValidateElementIsDisplayed(lp.regsAndPoliciesCard,"Regs And Policies Card");
	 }
	  
		
	 public static void ClickOnSearchBox() throws Exception {
			HubbellHomePage hp = new HubbellHomePage(driver);
			WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
			ButtonHelper.click(element,"Search Box");
	 }
	 
	 public static void VerifyRecommendedTopicWouldbeChangeWhenSwitchOtherAvatar() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String topic = hp.recommendedTopic.getText();
		 String suggestedQuestions = hp.suggestedQuestions.getText();
		 String popularQuestions = hp.popularQuestions.getText();
		 String totalMyQuestions = hp.myQuestions.getText();
		 String vaule = String.valueOf(totalMyQuestions);
		 System.out.println(topic);
		 ArrayList<String> previouslist= new ArrayList<String>();
		 
		 previouslist.add(topic);
		 previouslist.add(suggestedQuestions);
		 previouslist.add(popularQuestions);
		 previouslist.add(vaule);
		 
		 
		 
		 ClickOnDropDownChannelIcon();
		 GenericHelper.selectoptionfromdropDown("Product Support");
		 ButtonHelper.click(hp.searchButtonIcon,"Search");
		 ClickOnSearchBox();
		 
		 
		 String latesttopic = hp.updatedRecommendedTopic.getText();
		 String updatedSuggestedQuestions = hp.updatedSuggestedQuestions.getText();
		 String updatedPopularQuestions = hp.updatedPopularQuestions.getText();
		 String totalUpdatedmyQuestions = hp.updatedmyQuestions.getText();
		 String updatedVaule = String.valueOf(totalUpdatedmyQuestions);
		 
		 ArrayList<String> latestlist= new ArrayList<String>();
		 latestlist.add(latesttopic);
		 latestlist.add(updatedSuggestedQuestions);
		 latestlist.add(updatedPopularQuestions);
		 latestlist.add(updatedVaule);
		 
		 ArrayList<String> DataName= new ArrayList<String>();
		 DataName.add("Recommended Topic");
		 DataName.add("Suggested Questions");
		 DataName.add("Popular Questions");
		 DataName.add("My Questions");
		 
		 for(int i = 0; i<latestlist.size(); i++) {
			 Object previousdata = previouslist.get(i);
			 Object latestdata = latestlist.get(i);
			 
			 if(previousdata.equals(latestdata)) {
				  ObjectRepo.test.log(LogStatus.FAIL, DataName.get(i) +"is not changed");
			  }else {
				  ObjectRepo.test.log(LogStatus.PASS, DataName.get(i) +"is changed");
			  }
			 
		 }
	 }
	 
	 public static int CheckThemeSize() {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			int themeSize = themepage.allThemes.size();
			return themeSize;
			 
		 }
		
	 public static void VerifyTopicsAreStillUnderAllThemeIfWhenChangedchannels() throws Exception {
		  ArrayList<String> themeList = new ArrayList<String>();
		  ArrayList<String> themeType = new ArrayList<String>();
		  boolean flag = false;
		  themeList.add("Environmental");
		  themeList.add("Social");
		  themeList.add("Governance");
		  themeList.add("Person");
		  themeList.add("Organization");
		  themeList.add("Location");
		  
		  ClickOnDropDownChannelIcon();
		  GenericHelper.selectoptionfromdropDown("Product Support");
		  
		  for(int i =0; i<themeType.size(); i++) {
			 WebElement ele = driver.findElement(By.xpath("//*[@type='button'] [text()='"+themeType.get(i)+"']"));
			 ButtonHelper.click(ele, ele.getText());
			 if(CheckThemeSize()>0) {
				flag = true;
			 }else {
				flag = false;
				break;
			 }
		}
			
		if(flag == true) {
			ObjectRepo.test.log(LogStatus.PASS, "Topics are Display Under the Theme" );
	    }else {
			ObjectRepo.test.log(LogStatus.FAIL, "Topic are not Display Under the theme" );
		}
	 }
	 
	 
	 public static void VerifyAllCardIsNotHighlightedWithPurpalColor() {
		    HubbellHomePage hp = new HubbellHomePage(driver);
			String purpleColour= "#8854f7";
			Actions actions = new Actions(driver);
			actions.moveToElement(hp.highlightColor).build().perform();
			String actualcolor  = hp.highlightColor.getCssValue("color");
			System.out.println(actualcolor); 
		    actualcolor = Color.fromString(actualcolor).asHex();
			if(actualcolor.equals(purpleColour)) {
				ObjectRepo.test.log(LogStatus.FAIL,"Topic card Highlighted with Purple colour" );
			}else {
				ObjectRepo.test.log(LogStatus.PASS,"Topic card Not Highlighted with Purple colour" );
			}
		
	 }
	 

	 
	 
	 public static void VerifyTopAnswersAreAppearingFromRelevantPassages() {
		    HubbellHomePage hp = new HubbellHomePage(driver);
		    
		    ArrayList<String> relevantWorldList = new ArrayList<String>();
		    ArrayList<String> answerarrayList = new ArrayList<String>();

		    relevantWorldList.add("biodegradable");
		    relevantWorldList.add("wastes");
		    relevantWorldList.add("examples");
		    
		    for(WebElement element : hp.topPassages ) {
		    	String answerText = element.getText();
		    	answerarrayList.add(answerText);
		    }
		    
		    for(int i = 0; i<answerarrayList.size(); i++) {
		    	boolean value = false;
		    	for(int j =0; j<relevantWorldList.size(); j++) {
		    		if(answerarrayList.get(i).contains(relevantWorldList.get(j))) {
		    			value = true;
		    			break;
		    		}
		    	}
			     if(value==true) {
			    	 ObjectRepo.test.log(LogStatus.PASS,"Relevant Passage is Display");
			    	 break;
			     }

			     else if (i+1==answerarrayList.size()) {
		    			ObjectRepo.test.log(LogStatus.FAIL,"Relevant Passage is Not Display");
		    	 }
		    }
	 }
	 
	 
	 public static void VerifyRecommendedTopicsAreAppearingOnTheSearchResultPage() {
		    HubbellHomePage hp = new HubbellHomePage(driver);
		    if(hp.recommendedTopicSection.isDisplayed()) {
		    	ObjectRepo.test.log(LogStatus.INFO,"Recommended Topic Section Display");
		    	for(WebElement element : hp.recommendedTopicName) {
		    		String text = element.getText();
		    		ObjectRepo.test.log(LogStatus.PASS,"Recommended Topic is :" + text );
		    	}
		    	
		    }else {
		    	ObjectRepo.test.log(LogStatus.FAIL,"Recommended Topic is not Display");
		    }
	 }
	 
	 public static void ClickOnRecommendedTopic() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.recommendedTopic, "Recommended Topic");
		
	 }
	 
	 
	 public static void VerifyRecommendedTopicsPassagesAreAppearingAfterSearchingResult() throws InterruptedException {
		 ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		 Thread.sleep(3000);
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy(0,800)", "");
		 Thread.sleep(3000);
		 
		 for(WebElement el : themepage.passageName) {
			 GenericElements.ValidateElementIsDisplayed(el, el.getText()+" Passages");
		 }
		 
		
		 
	 }
	 
	 public static void VerifyTheTopicsNameIsDisplayUnderTheInsights() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 if(hp.insightsSection.isDisplayed()) {
			 ObjectRepo.test.log(LogStatus.INFO,"Topic name is display Insights Section Display");
			 for(WebElement element : hp.insightsSectionTopic) {
				 String text = element.getText();
				 ObjectRepo.test.log(LogStatus.PASS,"Insights Topic is :" + text );
			 }
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Topic name is not display Insights Section Display");
		 }
	 }
	 
	 public static void VerifySummaryIsNotRegenrating() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 Thread.sleep(8000);
		 String summaryText = hp.summaryPart.getText();
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy(0,500)", "");
		 ClickOnAnswerSourceLink();
		 ClickOnBackButton();
		 String repeatText = hp.repeatSummaryPart.getText();
		 if(summaryText.equals(repeatText)) {
			 ObjectRepo.test.log(LogStatus.PASS,"Answer Summary is not regenrated");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Answer Summary is regenrated");
		 }
	 }
	 
	 public static void VerifySearchResultPageIsNotRefreshed() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ArrayList<String> filelist = new ArrayList<String>();
		 ArrayList<String> updatedfilelist = new ArrayList<String>();
		 for(WebElement el : hp.fileSourceLink) {
			 String filName  = el.getText();
			 filelist.add(filName);
		 }
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy(0,500)", "");
		 ClickOnAnswerSourceLink();
		 ClickOnBackButton();
		 Thread.sleep(5000);
		 for(WebElement el : hp.updatedFileSourceLink) {
			 String filName  = el.getText();
			 updatedfilelist.add(filName);
		 }
		 if(filelist.equals(updatedfilelist)==true) {
			 ObjectRepo.test.log(LogStatus.PASS,"Search Result Page Is Not Refreshed");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Search Result Page Is Refreshed");
		 }
	 }
	 
	 public static void VerifyFirstLetterIsCapitalOfTopic() {
		 ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		 
		 String topicName = themepage.topicName.getText();
		
		 char[] ch = new char[topicName.length()];
		 
		 for(int i = 0; i < topicName.length(); i++) {
			 ch[i] = topicName.charAt(i);
		 }
		 String CapitalLetter  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 char[] Letter = new char[CapitalLetter.length()];
				 
		 
		 for(int i = 0; i < CapitalLetter.length(); i++) {
			 Letter[i] = CapitalLetter.charAt(i);
		 }	
		 for(char charLetter : Letter)
			 if(ch[0]==charLetter) {
				 ObjectRepo.test.log(LogStatus.PASS,"First Letter Is Capital Of Topic");
				 break;
			 }
			 else if(charLetter== Letter[25]) {
				 ObjectRepo.test.log(LogStatus.FAIL,"First Letter Is Not Capital Of Topic");
			 }
	 }
	 
	 
	 public static void VerifyAvatarSelectionPageIsVissibleWhenUserLoginFirstTime() throws InterruptedException {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 Thread.sleep(3000);
		 String avatarHeader = lp.avatarHeader.getText();
		 if(avatarHeader.contains("Avatar")) {
			 ObjectRepo.test.log(LogStatus.PASS,"Avatat Selection Page Is Vissible");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Avatat Selection Page Is Not Vissible"); 
		 }
		 
	 }
	 
	 public static void VerifySummaryIsGeneratedWhenUserAskedQuestionFromTheHomeTab() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 if(hp.activeHomeTab.isDisplayed()&&hp.summaryPart.isDisplayed()) {
			 ObjectRepo.test.log(LogStatus.PASS,"Summary Is Vissible");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Summary Is Not Vissible");
		 }
	 }
	 
	 public static void VerifySomeWordIsHighlightedOfSummaryAndAnswer() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String highlightedColor = "#ffffff";
		 for (WebElement element : hp.highlightedWord) {
			 String text = element.getText();
			 String actualcolor = element.getCssValue("color");
			 System.out.println(actualcolor); 
			 actualcolor = Color.fromString(actualcolor).asHex();
			 if(highlightedColor.equals(actualcolor)) {
				 ObjectRepo.test.log(LogStatus.PASS, "Highlighted Word is :: "+text);
			 }else {
				 ObjectRepo.test.log(LogStatus.FAIL,"Highlighted Word is not Vissible");
			 }
		 }
	 }
	 
	 
	 public static void ClickOnDownvotedIcon() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.downvotedThumbIcon, "Downvote Thumb Icon");
	 }
	 
	 public static void VerifyDownvotedQuestionIsPresentInTheKnowledgeAnalytics() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		 String searchedquestion = element.getAttribute("value");
		 ClickOnDownvotedIcon();
		 ClickOnSkipButton();
		 ClickOnKnowledgeAnalyticsIcon();
		 ClickOnMarketResearchChannel();
		 
		 ArrayList<String> downvotedQuestionList = new ArrayList<String>();
		 for(WebElement ele : kp.downvotedQuestion) {
			 String downvotedQuestion = ele.getText();
			 downvotedQuestionList.add(downvotedQuestion);
		 }
		 
		 for(int i=0; i<downvotedQuestionList.size(); i++) {
			 String actualQuestion = downvotedQuestionList.get(i);
			 if(searchedquestion.equals(actualQuestion)) {
				 ObjectRepo.test.log(LogStatus.PASS, "Downvoted Questions Is display in Knowledge Analytics");
				 break;
			 }
			 else if(i+1==downvotedQuestionList.size()) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Downvoted Questions Is not display in Knowledge Analytics");
			 }
		 }
	 }
	 
	 public static void VerifyTopAnswerIsDisplay() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 int totalAnswer = hp.fileName.size();
		 if(totalAnswer==5) {
			 ObjectRepo.test.log(LogStatus.PASS, "Top Five Answer Is Display");
			 ArrayList<String> AnswerList = new ArrayList<String>();
			 for(WebElement element : hp.fileName) {
				String Answer = element.getText();
				AnswerList.add(Answer);
			 }
			 
			 for(int i=1; i==AnswerList.size(); i++) {
				 String text = AnswerList.get(i);
				 ObjectRepo.test.log(LogStatus.INFO, "Top "+i+" :: "+text);
			 }
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Top Five Answer Is Not Display"); 
		 }
		
	 }
	 
	 public static void ClickOnShowOtherAnswer() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ((JavascriptExecutor)driver).executeScript("scroll(0,500)");
		 ButtonHelper.click(hp.showOtherAnswerButton, "Show Other Answer Button");
	 }
	 
	 public static int getTotalAnswer() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 int totalAnswer = hp.fileName.size();
		 return totalAnswer;
	 }
	 
	 
	 public static void VerifyOtherAnswerIsDisplayWhenClickOnShowOtherButton() throws Exception{
		 Thread.sleep(5000);;
		 int beforeAnswerList = getTotalAnswer();
		 ClickOnShowOtherAnswer();
		 Thread.sleep(2000);
		 int  afterAnswerList = getTotalAnswer();
		 if(beforeAnswerList<afterAnswerList) {
			 ObjectRepo.test.log(LogStatus.PASS, "More Other Answer Is Display");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "More Other Answer Is Not Display");
		 }
		 
	 }
	 
	 public static void ClickOnHideAnswerLink() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.hideAnswer, "Hide Answer Link");
	 }
	 
	 public static void VerifyTopAnswerIsDisplayWhenClickOnHideAnswerLink() throws Exception{
		 Thread.sleep(10000);
		 int beforeAnswerList = getTotalAnswer();
		 ClickOnHideAnswerLink();
		 int  afterAnswerList = getTotalAnswer();
		 if(beforeAnswerList>afterAnswerList) {
			 ObjectRepo.test.log(LogStatus.PASS, "Top Answer Is Display");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Top Answer Is Not Display");
		 }
		 
	 }
	 
	 public static void ClickOnLoadAnotherAnswerButton() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
			ButtonHelper.click(hp.loadAnotherAnswerButton, "Load Another Answer Button");
	 }
	 
	 public static int GetTotalFileSize() throws InterruptedException {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 Thread.sleep(3000);
		 int totalotheranswer = hp.totalOtherAnswer.size();
		 return totalotheranswer;
	 }
	 
	 
	 public static void VerifyGeneratedAnswersAreHoldPassagesImagesAndTablesTogether() throws Exception{
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ClickOnShowOtherAnswer();
		 String TotalAnswer = hp.otherAnswerHeading.getText();
		 String[] strArray = null;  
		 strArray = TotalAnswer.split(" ");  
		 int totalsize =Integer.parseInt(strArray[1]);
		 
		 while(GetTotalFileSize()<totalsize) {
			 ClickOnLoadAnotherAnswerButton();
			 System.out.println(GetTotalFileSize());
		 }
		 GenericElements.ValidateElementIsDisplayed(hp.passages,"Passages");
		 GenericElements.ValidateElementIsDisplayed(hp.images,"Images");
		 GenericElements.ValidateElementIsDisplayed(hp.tables,"Tables");
	 }
	 
	 
	 public static void SelectProductSupportCard() throws Exception {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			ButtonHelper.click(themepage.productSupport, "Product Support Card");
			
	 }
	 
	 public static void ClickOnEnvironmentTab() throws Exception {
			ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
			ButtonHelper.click(themepage.environmentTab, "Environment Tab");
	 }
	 
	 public static void SelectSuggestedQuestion() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		ButtonHelper.click(themepage.suggestedQuestion, "Suggested Question");
	 }
	 
	 public static void VerifyTheAnswersAreAppearingFromRelevantQuestion() {
		    HubbellHomePage hp = new HubbellHomePage(driver);
		    WebElement ele = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
			String searchedSuggestedQuestion = ele.getAttribute("value");
			
			String str[] = searchedSuggestedQuestion.split(" ");   //converting String to arraylist
		    List<String> al = new ArrayList<String>();
			al = Arrays.asList(str);
			System.out.println(al.get(0));
			 
			 String elementText = hp.passages.getText();
			 int i=0;
			  while(i<al.size()) {
				  if(elementText.contains(al.get(i))) {
						 ObjectRepo.test.log(LogStatus.PASS, "Answers Are Appearing From Relevant Suggested Question");
						 break;
				      }
					 else if(i+1==al.size()) {
						 ObjectRepo.test.log(LogStatus.FAIL, "Answers Are not Appearing From Relevant Suggested Question");
				      }
				  i++;
			  }
	 }
	 
	 public static void ClickOnShortingDropDownIcon() throws Exception {
			HubbellHomePage hp = new HubbellHomePage(driver);
			ButtonHelper.click(hp.shortedDropDownIcon, "Dorp Down Icon");
	 }
	 
	 public static void SelectOptionFromDropDown(WebElement element , String name) throws Exception {
		 ButtonHelper.click(element, name);
	 }
	 
	 public static void VerifyAnswerIsAppearingFromShortingRelevent() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String text = null;
		 
		 text = hp.shortedfield.getText();
		 if(text.equals("Relevancy")) {
			 GenericElements.ValidateElementIsDisplayed(hp.fileSource,text +" Relevent Answer");
		 }
		
		 ClickOnShortingDropDownIcon();
		 SelectOptionFromDropDown(hp.newestOption, "Newest");
		 
		 text = hp.shortedfield.getText();
		 if(text.equals("Newest")) {
			 GenericElements.ValidateElementIsDisplayed(hp.fileSource,text +" Relevent Answer");
		 }
		 
		 ClickOnShortingDropDownIcon();
		 SelectOptionFromDropDown(hp.oldestOption, "Oldest");
		 text = hp.shortedfield.getText();
		 if(text.equals("Oldest")) {
			 GenericElements.ValidateElementIsDisplayed(hp.fileSource,text +" Relevent Answer");
		 }
	 }
	 
	 public static int getTotalPages() {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		 ArrayList<String> txtlist = new ArrayList<String>();
		 for(WebElement element : dp.lastPage) {
			 txtlist.add(element.getText());
		 }
		String txt = txtlist.get(txtlist.size()-1);
		int totalPages = Integer.parseInt(txt);
		return totalPages;
		 
	 }
	 
	 
	 public static void VerifyGeneratedTopAnswerApperingFromLatestUploadedDocument() throws Exception{
		 HubbellHomePage hp = new HubbellHomePage(driver);
		
		 ArrayList<String> AnswerList = new ArrayList<String>();
		 ArrayList<String> DocumentList = new ArrayList<String>();
		 
//		 String matchName = null;
		 
		 for(WebElement ele : hp.fileSourceLink) {
			 String elementText = ele.getText();
			 AnswerList.add(elementText);
		 }
		 ClickOnDataManagerNavigationIcon();
		 ClickOnFilesOption();
		 ClickOnUploadFilesOption();
		 Thread.sleep(5000);
		 int afterTotalUploadedFile = getTotalUploadedFile(hp.uploadedSection);
		 System.out.println(afterTotalUploadedFile);
		 DocumentList = ButtonHelper.clickPaginationButton(getTotalPages(),"(//*[@class='Sources_container__UdBAx'])[2]//*[@class='BaseDataSourceCard_container__3AFiS']//h4");
		 
		 for(int i=4; i<AnswerList.size(); i++) {
			 for(int j=0; j<DocumentList.size(); j++) {
				 if(AnswerList.get(i).equals(DocumentList.get(j))) {
					 System.out.println("Pass");
				 }
				 
			 }
			 if(i==AnswerList.size()-1) {
				 System.out.println("FAIL");
				 break;
			 }
			 
		 }
		 
		 
//		 ButtonHelper.click(hp.showAllDownArrow,"Show ALL Down Arrow");
		 
//			if(hp.uploadedFiles.isEmpty()) {
//					ObjectRepo.test.log(LogStatus.FAIL,"Files are not Uploaded");
//			}else {
//				for (WebElement el :hp.uploadedFiles) {
//					 String documentname = el.getText();
//					 System.out.println(documentname);
//					 DocumentList.add(documentname);
//					}
//				
//				for(int i=0; i<AnswerList.size(); i++) {
//					String filename = AnswerList.get(i);
//					filename = filename.replace(".PDF","");
//					int value = DocumentList.size();
//					for(int j =0; j<value; j++) {
//						String documentName = DocumentList.get(j);
//						documentName = documentName.replace(".pdf","");
//						System.out.println(DocumentList.size()-j);
//						System.out.println(j+" "+filename);
//						System.out.println(j+" "+documentName);
//						
//						if(filename.equals(documentName)){
//							matchName = documentName;
//							ObjectRepo.test.log(LogStatus.PASS,"Top Answer Appearing from Latest Uploaded Document");
//							break;
//						}
//					  }
//					
//					if(matchName!=null) {
//						break;
//					}
//					else if(i+1==AnswerList.size()) {
//						ObjectRepo.test.log(LogStatus.FAIL,"document Not Found");
//					}
//			     }
//		}
		
	 }
	 
	 public static void VerifyAnswersAreDisplay() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 GenericElements.ValidateElementIsDisplayed( hp.searchedresult, "Answer");
		 
	 }
	 
	 
	 public static void VerifyThatChannelsAreRenameToAvatarsEveryWhereOnNesh() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String expectedBackgourndColour = "#8854f7";
		 String actualcolor  = hp.homeIcon.getCssValue("background-color");
			System.out.println(actualcolor); 
		    actualcolor = Color.fromString(actualcolor).asHex();
		    
		    if(expectedBackgourndColour.equals(actualcolor)) {
		    	
		    	Actions action = new Actions(driver);
				action.moveToElement(hp.avatarChannelsIcon).perform();
		    	String text = hp.avatarChannels.getText();
		    	
		    	if(hp.avatarChannels.isDisplayed()) {
		    		if(text!="Channel") {
		    			ObjectRepo.test.log(LogStatus.PASS,"Channels Are Rename To Avatars On Home Page");
		    		}else {
		    			ObjectRepo.test.log(LogStatus.FAIL,"Channels Are Not Rename To Avatars On Home Page");
		    		}
		    	}
		    }
	 }
	 
	 public static void VerifyThatRecommandedTopicsGeneratedWhenUserAskAnyQuestion() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 GenericElements.ValidateElementIsDisplayed( hp.recommendedTopicSection, "Recommanded Topics");
		
	 }
	 
	 public static void RewriteAnyQuestions() {
			HubbellHomePage hp = new HubbellHomePage(driver);
			WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
			TextBoxHelper.enterText(element, "Rewrite Question", GenericHelper.getTestData("NewQuestions"));
	}
		 
	 public static void VerifyThatThreeRecommandedTopicsIsDisplay() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 int totalRecommendedTopic = hp.recommendedTopicName.size();
		 if(totalRecommendedTopic==3) {
			 ObjectRepo.test.log(LogStatus.PASS,"Three Recommended topic is display");
			 for(WebElement ele : hp.recommendedTopicName) {
				 String textValue = ele.getText();
				 GenericElements.ValidateElementIsDisplayed( ele, textValue);
			 }
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Three Recommended Topic Not is display");
		 }
		
	 }
	 
	 public static void ClickGroupByDownIcon() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 Thread.sleep(5000);
		 ButtonHelper.click(kp.groupByDropdowIcon, "Group BY Drown Icon");
	 }
	 
	 public static void SelectAssigneToOtherExperts() throws Exception {
		 GenericHelper.selectoptionfromdropDown("Assigned to other Experts");
	 }
	 
	 public static void ClickOnAssignToAnotherExpertButton() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 ButtonHelper.click(kp.AssignToAnotherExpertButton, "Assign To Another Expert Button");
		 
	 }
	 
	 public static void AssignQuestionHimself() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 ButtonHelper.click(kp.selfAssign, "Self");
		 
	 }
	
	 
	 public static void VerifyUserCanAnswerThatQuestionAssignToThem() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 WebElement ele =  driver.findElement(By.xpath("//*[text()='"+GenericHelper.getTestData("TextInputData")+"']"));
		 WebElement user =  driver.findElement(By.xpath("(//*[text()='"+GenericHelper.getTestData("TextInputData")+"']//parent::*)[1]"));
		 boolean value = ele.isEnabled();
		 System.out.println("value is "+value);
		 if(value==true) {
			 ClickOnQuestionsIcon();
			 int size;
			 try {
				 size= kp.noQuestionFoundMgs.size();
			 }catch(Exception e) {
				 size= kp.noQuestionFoundMgs.size();
			 }
		    if(size!= 0) {
		    	ClickGroupByDownIcon();
		    	SelectAssigneToOtherExperts();
		    	SelectQuestion();
		    	ClickOnAssignToAnotherExpertButton();
		    	AssignQuestionHimself();
		    	ClickOnAssignButton();
				 GenericElements.ValidateElementIsDisplayed(kp.answerFields, "User Can Do Answer, Field");
		    }else{
		    	 GenericElements.ValidateElementIsDisplayed(kp.answerFields, "User Can Do Answer, Field");
		    }
			
		 }else {
			 ButtonHelper.click(ele, ele.getText());
			 ClickOnAnswerQuestionButton();
			 ButtonHelper.click(user, user.getText());
			 ClickOnAssignButton();
			 GenericElements.ValidateElementIsDisplayed(kp.answerFields, "User Can Do Answer, Field");
		 }
	 }
	 
	 public static void VerifyUserCanSeeOnlyAntherExpertName() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 GenericElements.ValidateElementIsDisplayed(kp.assigneToOtherExpertSection, "Only other Assign Expert Name");
	 }
	 
	 
	 public static String SelectQuestion() throws Exception {
		 WebElement ele =  driver.findElement(By.xpath("(//*[text()='“"+GenericHelper.getTestData("TextInputData")+"“'])[1]"));
		 String question = ele.getText();
		 ButtonHelper.click(ele,"Selected Downvoted Question");
		return question;
	 }
	 
	 public static void SelectDownVotedQuestion() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 ButtonHelper.click(kp.downVotedQuestion,"Selected Downvoted Question");
	 }
	 
	 public static void AssignToOtherUser() throws Exception {
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 ButtonHelper.click(kp.usersAssign,"Other User");
	 }
	 
	 
	public static void VerifyThatAssignByFieldAndAssignToFieldUpdatedDisplay() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
	    String assignBy = kp.assignBy.getText();
	    String assignTo = kp.assignTo.getText();
	    
	    if(kp.assigneToOtherExpertSection.isDisplayed()) {
	    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignBy+" Field is Display");
	    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignTo+" Field is Display");
	    }else {
	    	ObjectRepo.test.log(LogStatus.FAIL,"Not Assigne To Other Expert Section");
	    }
	}
	
	
	public static void VerifyThatAssignByFieldUpdated() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
	    String assignBy = kp.assignBy.getText();
	    if(kp.assigneToOtherExpertSection.isDisplayed()) {
	    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignBy+" Field is Display");
	    }else {
	    	ObjectRepo.test.log(LogStatus.FAIL,"Not Assigne To Himself Expert Section");
	    }
	}

	
	public static boolean ClickOnDownVotedQuestion() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		WebElement elementQuestion =  driver.findElement(By.xpath("//*[text()='"+GenericHelper.getTestData("TextInputData")+"']"));
		String expectedQuestion = elementQuestion.getText();
		boolean value = false;
		ArrayList<String> list = new ArrayList<String>();
		 for(WebElement el : kp.downvotedQuestions) {
			String question = el.getText();
			list.add(question);
			if(question.equals(expectedQuestion)) {
			  value = true;
			  ButtonHelper.click(elementQuestion,"Down Voted Question");
			  break;
			 }
			else if(list.size()==kp.downvotedQuestions.size()) {
				value = false;
				break;
			}
		 }
		 
		 return value;
	}
	
	public static void ClickOnAnswerQuestionButton() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.answerQuestionButton,"Answer Question Button");
	}
	
	
	public static void VerifyThatQuestionIsAssignedToSelectedExpert() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		boolean value = ClickOnDownVotedQuestion();
		if(value==true) {
			ClickOnAnswerQuestionButton();
			AssignToOtherUser();
			ClickOnAssignButton();
			String assignBy = kp.assignBy.getText();
			String assignTo = kp.assignTo.getText();
			    if(kp.assigneToOtherExpertSection.isDisplayed()) {
			    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignBy+" Field is Display");
			    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignTo+" Field is Display");
			    }else {
			    	ObjectRepo.test.log(LogStatus.FAIL,"Not Assigne To Other Expert Section");
			    }
		}else {
			ClickOnQuestionsIcon();
			ClickGroupByDownIcon();
			SelectAssigneToOtherExperts();
			SelectQuestion();
			String assignBy = kp.assignBy.getText();
			String assignTo = kp.assignTo.getText();
			 if(kp.assigneToOtherExpertSection.isDisplayed()) {
			    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignBy+" Field is Display");
			    	ObjectRepo.test.log(LogStatus.PASS,"Assign By :"+assignTo+" Field is Display");
			    }else {
			    	ObjectRepo.test.log(LogStatus.FAIL,"Not Assigne To Other Expert Section");
			    }
		}
	}

	
	public static void VerifyTrendDataIsVissibleUnderTopicTrendData() throws Exception {
		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		GenericElements.ValidateElementIsDisplayed(themepage.trendingDataChart,"Trending Data Chart");
	}
	
	public static void VerifyAssignToExpertPopUpIsDisplay() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		boolean value = ClickOnDownVotedQuestion();
		try{ 
			if(value==true) {
				ClickOnAnswerQuestionButton();
				GenericElements.ValidateElementIsDisplayed(kp.AssignExpertPopUp,"Assign Expert PopUp");
			}
			else if(value==false) {
				ClickOnQuestionsIcon();
				QuestionPage qp = new QuestionPage(driver);
				String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
				int size = qp.waitingForAnswerQuestionList.size();
				ArrayList<String>list = new ArrayList<String>();
					for(WebElement el : qp.waitingForAnswerQuestionList) {
						String question = el.getText();
						list.add(question);
					}
					
					for(int i=0; i<list.size(); i++) {
						if(expectedQuestion.equals(list.get(i))) {
							driver.findElement(By.xpath("(//*[text()='"+expectedQuestion+"'])[1]")).click();
							ObjectRepo.test.log(LogStatus.FAIL,"Questions Have Already Assigned To HimSelf");
							break;
						}
						else if(list.size()==size){
							ClickGroupByDownIcon();
							SelectAssigneToOtherExperts();
							driver.findElement(By.xpath("(//*[text()='"+expectedQuestion+"'])[1]")).click();
							ClickOnAssignToAnotherExpertButton();
							GenericElements.ValidateElementIsDisplayed(kp.AssignExpertPopUp,"Assign Expert PopUp");
						}
					}
			}
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	public static void VerifyAssignToExpertPopUpScreenIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.AssignExpertPopUp,"Assign Expert PopUp");
	}
	
	public static void SearchQuestionInTheList() throws Exception {
		QuestionPage qp = new QuestionPage(driver);
		String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
		int size = qp.waitingForAnswerQuestionList.size();
		ArrayList<String> list = new ArrayList<String>();
			for(WebElement el : qp.waitingForAnswerQuestionList) {
				String question = el.getText();
				list.add(question);
			}
			for(int i=0; i<list.size(); i++) {
				
				if(expectedQuestion.equals(list.get(i))) {
					WebElement el =driver.findElement(By.xpath("(//*[text()='expectedQuestion'])[1]"));
					ButtonHelper.click(el, "question");
					break;
				}
				else if(list.size()==size){
					ClickGroupByDownIcon();
					SelectAssigneToOtherExperts();
					driver.findElement(By.xpath("(//*[text()='expectedQuestion'])[1]")).click();
					ClickOnAssignToAnotherExpertButton();
					AssignQuestionHimself();
					ClickOnAssignButton();
				}
			}
			
			
	}
	
	
	public static void VerifyThatSelfAssignedQuestionIsApperingInWaitingForAnswerTab() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		
		try {
			boolean value = ClickOnDownVotedQuestion();
			
			if(value==true) {
				ClickOnAnswerQuestionButton();
				AssignQuestionHimself();
				ClickOnAssignButton();
				String question = SelectQuestion();
				String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
				String switchToggleText = kp.switchToggle.getText();
				
				if(switchToggleText.equals("Waiting for Answer")) {
					if(question.equals(expectedQuestion)) {
						ObjectRepo.test.log(LogStatus.PASS, "Downvoted Question Appear In The Waiting For Answer Tab");
					}else {
						ObjectRepo.test.log(LogStatus.FAIL, "Downvoted Question not Appear In The Waiting For Answer Tab");
					}
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, "Waiting For Answer Tab not switched!");
				}
				
				
			}else {
				ClickOnQuestionsIcon();
				String question = SelectQuestion();
				String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
				String switchToggleText = kp.switchToggle.getText();
				if(switchToggleText.equals("Waiting for Answer")) {
					if(question.equals(expectedQuestion)) {
						ObjectRepo.test.log(LogStatus.PASS, "Downvoted Question Appear In The Waiting For Answer Tab");
					}else {
						ClickGroupByDownIcon();
						SelectAssigneToOtherExperts();
						SelectQuestion();
						ClickOnAssignToAnotherExpertButton();
						AssignQuestionHimself();
						ClickOnAssignButton();
						if(question.equals(expectedQuestion)) {
							ObjectRepo.test.log(LogStatus.PASS, "Downvoted Question Appear In The Waiting For Answer Tab");
						}else {
							ObjectRepo.test.log(LogStatus.FAIL, "Downvoted Question not Appear In The Waiting For Answer Tab");
						}
					}
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, "Waiting For Answer Tab not switched!");
				}
			}
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	
	public static void VerifyImagesAndTablesIsNotDisplayAtTheTop() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		int tablelAnswer = hp.tableAnswer.size();
		int imageAnswer = hp.imageAnswer.size();
		if(tablelAnswer==0 && imageAnswer==0) {
			ObjectRepo.test.log(LogStatus.PASS, "Tables And Images are not showing at The Top");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Tables And Images are showing at The Top");
		}
	}
	
	 public static int getTotalOtherAnswer() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String totalAnswer = hp.OtherAnswer.getText();
		 String str[] = totalAnswer.split(" ");
			List<String> al = new ArrayList<String>();
			al = Arrays.asList(str);
			String StringValue =al.get(1);
			System.out.println(StringValue);
			int i=Integer.parseInt(StringValue); 
		 return i;
	 }
	
	public static void VerifyDuplicateImagesAreNotAvailable()throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ClickOnShowOtherAnswer();
		
		String TotalAnswer = hp.otherAnswerHeading.getText();
		String[] strArray = null;  
		strArray = TotalAnswer.split(" ");  
		int totalsize =Integer.parseInt(strArray[1]);
		
		while(GetTotalFileSize()<totalsize) {
			ClickOnLoadAnotherAnswerButton();
			System.out.println(GetTotalFileSize());
		}
		
		ArrayList<String> fileName = new ArrayList<String>();
		System.out.println(hp.imagesCards.size());
		
		for(WebElement el : hp.imagesCards) {
			fileName.add(el.getText());
			System.out.println(el.getText());
		}
		
		boolean flag = false;
		
		for(int i=0; i<fileName.size(); i++) {
			for(int j=i+1; j<fileName.size(); j++) {
				if(fileName.get(i).equals(fileName.get(j))) {
					System.out.println(fileName.get(j));
					flag = true ;
				}
			}
	    }
		
		if(flag==false) {
			ObjectRepo.test.log(LogStatus.PASS, "There is no Dupliacte Image Answer");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "There is Dupliacte Image Answer");
		}
		
	}
	
	
	public static void VerifyThatAnswersSubmittedByTheExpertsComingOnTop() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement ele : hp.fileName) {
			String filename = ele.getText();
			list.add(filename);
		}
		
		for(int i=0; i<list.size(); i++) {
			int position = i+1;
			if(position<hp.fileName.size()) {
				if(list.get(i).equals("EXPERT ANSWER")) {
					ObjectRepo.test.log(LogStatus.PASS, "Expert Answer found at The Top Position "+position);
					break;
				}
				else if(position==list.size()) {
					ObjectRepo.test.log(LogStatus.FAIL, "Expert Answer Not Found At Top");
					break;
				}
			}
			
		}
		
	}
	
	
	public static void VerifyExpertAnswerIsDisplayWithExpertName() {
		
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> expertlist = new ArrayList<String>();
		for(WebElement ele : hp.fileName) {
			String filename = ele.getText();
			list.add(filename);
		}
		
		for(WebElement ele : hp.exertName) {
			String name = ele.getText();
			expertlist.add(name);
		}
		
		for(int i=0; i<list.size(); i++) {
			int position = i+1;
			if(position<hp.fileName.size()) {
				if(list.get(i).equals("EXPERT ANSWER")) {
					ObjectRepo.test.log(LogStatus.PASS, "Expert Answer found at The Top Position "+position);
					ObjectRepo.test.log(LogStatus.PASS, "Expert Name is "+expertlist.get(i));
					break;
				}
				else if(position==list.size()) {
					ObjectRepo.test.log(LogStatus.FAIL, "Expert Answer Not Found");
					break;
				}
			}
		}
	}
	
	public static void SelectRegsAndPoliciesAvatar() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
    	ButtonHelper.click(hp.regsAndPoliciesChannels, "Regs And Policies");
	}
	
	public static void VerifyGeneratedAnswerAreRelatedToTheQuestion() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateRelevantElementIsDisplayed(hp.answer,"spill","vessel");
	}
	
	public static void ClickOnRegsAndPoliciesChannelCard() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(10000);
		ButtonHelper.click(hp.regsAndPoliciesChannels,"Regs And Policies Channel");
	}
	
	public static void VerifyExpertCanAnswerSubmitAndDecline() {
		QuestionPage qp = new QuestionPage(driver);
		try {
			boolean value = ClickOnDownVotedQuestion();
			if(value==true) {
				ClickOnAnswerQuestionButton();
				AssignQuestionHimself();
				ClickOnAssignButton();
				SelectQuestion();
				GenericElements.ValidateElementIsDisplayed(qp.declineButton,"Expert Can Decline The Question");
				GenericElements.ValidateElementIsDisplayed(qp.submitButton,"Expert Can Submit The Answer");
				
			}else {
				ClickOnQuestionsIcon();
				String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
				ArrayList<String> qestionList = new ArrayList<String>();
				for(WebElement ele:qp.waitingForAnswerQuestion) {
					String question = ele.getText();
					qestionList.add(question);
				}
				
				for(int i = 0; i<qestionList.size(); i++) {
					String question = qestionList.get(i);
					int j= i+1;
					if(question.equals(expectedQuestion)) {
						GenericElements.ValidateElementIsDisplayed(qp.declineButton,"Expert Can Decline The Question");
						GenericElements.ValidateElementIsDisplayed(qp.submitButton,"Expert Can Submit The Answer");
						break;
					}
								
					else if(j==qestionList.size()){
						ClickGroupByDownIcon();
						SelectAssigneToOtherExperts();
						SelectQuestion();
						ClickOnAssignToAnotherExpertButton();
						AssignQuestionHimself();
						ClickOnAssignButton();
						if(question.equals(expectedQuestion)) {
							GenericElements.ValidateElementIsDisplayed(qp.declineButton,"Expert Can Decline The Question");
							GenericElements.ValidateElementIsDisplayed(qp.submitButton,"Expert Can Submit The Answer");
							break;
						}
					}
				}
				
			}
			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}
	
	
	public static void VerifyThatQuestionIsAppearingInTheSearchBarWhenResearchQuestion() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		RewriteAnyQuestions();
		PressEnterKeys();
		
		WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		String question= element.getAttribute("value");
		String expectedQuestion = GenericHelper.getTestData("TextInputData");
		
		if(question.equals(expectedQuestion)) {
			ObjectRepo.test.log(LogStatus.PASS, "Question is display in the search bar");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL, "Question is not display in the search bar");
		}
		
	}
	
	
	public static void VerifyThatWhenUserClickOnBackLinkHeStillOnThatParticularAnswer() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		int totalAnswer = hp.FilesName.size();
		int randomNumber = (int)(Math.random()*(totalAnswer-1+1)+1);  
		String text = null;
		ArrayList<WebElement> element = new  ArrayList<WebElement>(); 
		for(WebElement el : hp.FilesName) {
			element.add(el);
		}
		
		for(int i=0; i<element.size(); i++) {
			int size = 1+i;
			if(randomNumber==size) {
				WebElement el = element.get(i);
				text = el.getText();
				ButtonHelper.click(el, "File Sourse Link");
				break;
			}
			else if(size==element.size()) {
				ObjectRepo.test.log(LogStatus.FAIL, "File sourse not found");
			}
			
		}
		
		ClickOnBackButton();
		WebElement el = driver.findElement(By.xpath("//*[text()='"+text+"']"));
    	if(el.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS, "It's Still On Particular Answer");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "It's not Still On Particular Answer");
		}
	}
	
	public static void VerifyTopAnswerIsDisplayInTheText() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> element = new  ArrayList<String>(); 
		for(WebElement el : hp.topPassages) {
			String text = el.getText();
			element.add(text);
		}
		String textvalue = element.get(0);
		
		if(textvalue.getClass().getSimpleName().equals("String")) {
			ObjectRepo.test.log(LogStatus.PASS, "Top Answer Is Text");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Top Answer Is not Text");
		}
		
	}
	
	
	public static void VerifyThatJobTitleAndBusinessUnitFieldsAreAvailableOnSignUpPage() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.txtjobTitle,"Job Title Field");
		GenericElements.ValidateElementIsDisplayed(lp.txtbusinessUnit,"Bussiness Unit Field");
	}
	
	public static void VerifyThatOnlyOneImageIsShowingInTheTop() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		int size = hp.fileName.size();
		if(size==5) {
			ObjectRepo.test.log(LogStatus.INFO, "Top Answer "+size+"is displayed");
			GenericElements.ValidateElementIsDisplayed(hp.images,"Image Answer");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL, "Top Answer Is not Displayed");
		}
	}
	
	
	public static void VerifyThatExpertAnswerIsDisplayInTheResultPage() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement ele : hp.fileName) {
			String filename = ele.getText();
			list.add(filename);
		}
		
		for(int i=0; i<list.size(); i++) {
			int position = i+1;
			if(position<hp.fileName.size()) {
				if(list.get(i).equals("EXPERT ANSWER")) {
					ObjectRepo.test.log(LogStatus.PASS, "Expert Answer Is Dispaly In The Result Page");
					break;
				}
				else if(position==list.size()) {
					ObjectRepo.test.log(LogStatus.FAIL, "Expert Answer Is Not Dispaly In The Result Page");
					break;
				}
			}
			
		}
	}
	
	public static void selectDate(String fromDate,String toDate) throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		hp.calenderField.click();
		hp.calenderField.sendKeys(fromDate+toDate);
	}
	
	public static void ApplyDateFilter() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.dateFilter,"Date Filter");
//		ButtonHelper.click(hp.dateCalenderDownIcon,"Calender Down Icon");
		String fromDate ="07142022";
	    String toDate ="07182022";
		selectDate(fromDate,toDate);
	}
	
	public static void VerifyFilterAppliedAnswerIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String count = hp.filteredAnswer.getText();
		int totalCount = Integer.parseInt(count);
		
		if(totalCount>0) {
			for(WebElement el : hp.fileName) {
				GenericElements.ValidateElementIsDisplayed(el,"filtered Answer");
				break;
			}
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Filter Not applied");
		}
	}
		
	public static float GetKnowledageGapPersentage() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		String gapText=kp.knowledgeGapPersentage.getText();
		gapText = gapText.replace("KNOWLEDGE GAP ", "").replace("%", "");
		
		float gap = Float.parseFloat(gapText);  
		return gap;
	}
	
	
	public static void VerifyKnowledageGapPercentageIsZeroShowingWhenDownvotedQuestionAnswered() throws Exception {
		KnowledgeAnalyticsPage kp= new KnowledgeAnalyticsPage(driver);
		float gap = GetKnowledageGapPersentage();
		if(gap==0) {
			String text = kp.downvotedQustion.getText();
			int totalQues = Integer.parseInt(text);
			if(totalQues==0) {
				ObjectRepo.test.log(LogStatus.PASS, "Tatal Gap"+gap);
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, "Knowledage Gap Percentage Is not Zero Showing");
			}
		}else {
			ObjectRepo.test.log(LogStatus.PASS, "There are Unanswered Question Available and Gap is ::"+gap);
		}
		
//		String beforegapPercentage=GetKnowledageGapPersentage();
//		boolean value = ClickOnDownVotedQuestion();
//		if(value==true) {
//			ClickOnAnswerQuestionButton();
//			AssignQuestionHimself();
//			ClickOnAssignButton();
//			EnterTheAnswerToTheQuestion();
//			ClickOnSubmitButton();
//			ClickOnKnowledgeAnalyticsIcon();
//			ClickOnMarketResearchChannel();
//			String afterPercentage=GetKnowledageGapPersentage();
//			if(beforegapPercentage!=afterPercentage) {
//				ObjectRepo.test.log(LogStatus.PASS, "Knowledage Gap Percentage Is "+afterPercentage+" Showing");
//			}else {
//				ObjectRepo.test.log(LogStatus.FAIL, "Knowledage Gap Percentage Is "+afterPercentage+" Showing");
//			}
//		}
//		else if(value==false) {
//			ClickOnQuestionsIcon();
//			SearchQuestionInTheList();
//			EnterTheAnswerToTheQuestion();
//			ClickOnSubmitButton();
//			ClickOnKnowledgeAnalyticsIcon();
//			ClickOnMarketResearchChannel();
//			String afterPercentage=GetKnowledageGapPersentage();
//			if(beforegapPercentage!=afterPercentage) {
//				ObjectRepo.test.log(LogStatus.PASS, "Knowledage Gap Percentage Is "+afterPercentage+" Showing");
//			}else {
//				ObjectRepo.test.log(LogStatus.FAIL, "Knowledage Gap Percentage Is "+afterPercentage+" Showing");
//			}
//		}
	}
	
	public static void VerifyHtmlTagsAreNotDisplayInTheSummary() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String text = hp.summaryPart.getText();
		String htmlTags[] = GenericHelper.getTestData("Data").split(",");
		for(int i=0; i<htmlTags.length; i++) {
			if(text.contains(htmlTags[i])) {
				ObjectRepo.test.log(LogStatus.FAIL, "Html Tags Is Displayed "+ "Tags is ::"+htmlTags[i]);
			}
			else if(i==htmlTags.length-1) {
				ObjectRepo.test.log(LogStatus.PASS, "Html Tags Is not Displayed");
			}
		}
	}
	
	
	public static void SelectAuditOption() throws Exception {
//		ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		String channelsName =GenericHelper.getTestData("Channel");
		WebElement element =driver.findElement(By.xpath("(//*[@class='AvatarCardList_cardList__q438b'])[2]//*[text()='"+channelsName+"']"));
		ButtonHelper.click(element, channelsName);
//		ButtonHelper.click(themepage.auditCard, "Audit Card");
		
	}
	
	public static void VerifyAuditReportIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.auditReport,"Audit Report");
	}
	
	
	public static void VerifyAuditOptionIsOffWhenDeselectIt() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ClickOnDropDownChannelIcon();
		driver.findElement(By.xpath("(//*[@class='SearchHeader_item__1MbYU SearchHeader_listItem__3MkrF'])[4]")).click();
	    ButtonHelper.click(hp.searchButtonIcon,"Search");
//	    GenericElements.ValidateElementIsNotDisplayed(hp.auditReport,"Audit Report");
	    boolean value = GenericElements.IsElementPresent(hp.auditReport);
	    	if(value==false) {
	    		ObjectRepo.test.log(LogStatus.PASS,"Audit Option is Off");
	    	}else {
	    		ObjectRepo.test.log(LogStatus.FAIL,"Audit Option is not Off");
	    	}
	}
	
	
	public static void VerifyHtmlTagsAreNotDisplayInTheTopicPage() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		String htmlTags[] = GenericHelper.getTestData("Data").split(",");
		Thread.sleep(3000);
		for(WebElement el : hp.answerSection) {
			String text = el.getText();
			list.add(text);
		}
		System.out.println(list.size());
		
		for(int i = 0; i<list.size(); i++) {
			for(int j=0; j<htmlTags.length; j++) {
				if(list.get(i).contains(htmlTags[j])) {
					ObjectRepo.test.log(LogStatus.FAIL, "Html Tags Is Displayed "+ "Tags is ::"+htmlTags[i]);
					break;
				}
			}
		 if(i==list.size()-1) {
			ObjectRepo.test.log(LogStatus.PASS, "Html Tags Is not Displayed");
		 }
		}
	}
	
	
	public static void VerifyThatUserCanEditProfile() throws Exception {
		ProfilePage pp = new ProfilePage(driver);
		ButtonHelper.click(pp.editButton, "Edit Button");
		int randomvalue = GenericHelper.generateRamdomNumber(1,99);
		String firstname = "Reepu"+randomvalue;
		String lasttname = "Shah"+randomvalue;
		String jobtitle = "Q/A"+randomvalue;
		String businessUnite = "Nesh"+randomvalue;
		TextBoxHelper.enterText(pp.firstNameField, firstname, firstname);
		TextBoxHelper.enterText(pp.lastNameField, lasttname, lasttname);
		TextBoxHelper.enterText(pp.jobTitleField, jobtitle, jobtitle);
		TextBoxHelper.enterText(pp.businessUniteField, businessUnite, businessUnite);
		ButtonHelper.click(pp.saveButton, "Save Button");
		
		ArrayList<String> listprofile = new ArrayList<String>();
		listprofile.add(firstname);
		listprofile.add(lasttname);
		listprofile.add(jobtitle);
		listprofile.add(businessUnite);
		
		ArrayList<String> updatedProfilelist = new ArrayList<String>();
		for (WebElement el :pp.profile) {
			String value = el.getAttribute("value");
			updatedProfilelist.add(value);
		}
		
		for(int i= 0; i<updatedProfilelist.size(); i++) {
			if(listprofile.get(i).equals(updatedProfilelist.get(i))) {
				ObjectRepo.test.log(LogStatus.PASS, "Edited : " +listprofile.get(i));
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, listprofile.get(i)+"is not Edited");
			}
			
		}
	}
	
	public static void VerifyHtmlTagsAreNotDisplayInTheAuditTableUnderSourcePassage() throws InterruptedException {
		try {
			ThemeLocatorPage theme = new ThemeLocatorPage(driver);
			String htmlTags[] = GenericHelper.getTestData("Data").split(",");
			Thread.sleep(3000);
			String text = theme.sourcePassage.getText();
			for(int i=0; i<htmlTags.length; i++) {
				if(text.contains(htmlTags[i])) {
					ObjectRepo.test.log(LogStatus.FAIL, "Html Tags Is Displayed "+ "Tags is ::"+htmlTags[i]);
					break;
				}
			 if(i==htmlTags.length-1) {
				ObjectRepo.test.log(LogStatus.PASS, "Html Tags Is not Displayed");
			 }
			}
		}catch (Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Audit report has not data");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
		
	}
	
	public static void VerifySummaryIsDisplayOfTopThosePassages() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(8000);
		String SummaryText = hp.summaryPart.getText();
		ArrayList<String> passageList = new ArrayList<String>();
		String[] strArray = null;  
		
		for(WebElement el : hp.topPassage) {
			passageList.add(el.getText());
		}
		
		boolean status = false;
		
		for(int i=0; i<passageList.size(); i++) {
			String text=passageList.get(i);
			strArray = text.split(" ");  
			for(int j=0; j<strArray.length; j++) {
				String txt = strArray[j];
				 if(SummaryText.contains(txt)&&txt!="") {
					 ObjectRepo.test.log(LogStatus.PASS, "Summary Is display of Top Passage");
					 status = true;
					 break;
				 }
				 else if(i+1==passageList.size()) {
					 ObjectRepo.test.log(LogStatus.FAIL, "Summary Is not display of Top Passage");
					 break;
				 }
			}
		   if(status==true) {
				break;
			} else {
				continue;
			}
			
		}
	}
	
	
	public static void ClickOnMediaTypeDropDownIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.mediaTypeDropDownIcon, "Media Type Drop Down Icon");
	}
	
	public static void SelectImageFilterOption() throws Exception {
		GenericElements.SelectFilterCheckBox("Image");
	}
	
	public static void VerifySelectedMediaTypeFilterRelatedAnswerIsAppearingInTheSearchResultPage() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		GenericElements.ValidateElementIsDisplayed(hp.images,"Filtered Images Answer");
	}
	
	public static void SelectReason() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.reasons, "reason");
	}
	
	public static void WriteAReasonForDownVote() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.txtreasons, "Other");
		TextBoxHelper.enterText(hp.txtReasonsField, "Write A Reason", "I wasn't able to find create answers");
	}
	
	public static void WriteAReasonForUpVote() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.txtReasonsUp, "Other");
		TextBoxHelper.enterText(hp.txtReasonsField, "Write A Reason", "I was able to find create answers");
	}
	
	public static void VerifyThatUserCanSelectTheAReasonWhyTheySelectedDownThumb() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(2000);
		GenericElements.ValidateElementIsDisplayed(hp.votedMgs,"Down Voted Reason Message");
	}
	
	public static void VerifyThatUserCanDismissFeedbackWindowIfTheyChooseNotToProvideFeedback() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.iconClose, "Close Icon");
		int eleSize = hp.feedbackForm.size();
		if(eleSize<1) {
			ObjectRepo.test.log(LogStatus.PASS, "User Can Dismiss Feedback Window If They Choose Not To Provide Feedback");
		}else {
				ObjectRepo.test.log(LogStatus.FAIL, "User Can't Dismiss Feedback Window If They Choose Not To Provide Feedback");
		}
	}
	
    public static void ClickOnUpvotedIcon() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.upVoteThumb, "Upvote Thumb Icon");
	}
	
	public static void VerifyThatUserCanSelectTheAReasonWhyTheySelectedUpThumb() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(2000);
		GenericElements.ValidateElementIsDisplayed(hp.votedMgs,"Down Voted Reason Message");
	}
	
	public static void ClickOnFeedBackCloseWindow() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.iconClose, "Close Icon");
	}
	
	public static void VerifyThatSuggestionsIsAppearingWhenUserAksedQueary() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String>suggestionsList = new ArrayList<String>();
		for(WebElement ele : hp.latestSuggestions) {
			suggestionsList.add(ele.getText());
		}
		if(0<hp.latestSuggestions.size()) {
			ObjectRepo.test.log(LogStatus.INFO, "Latest Suggestions Queary is Appearing When User Aksed Queary");
			for(int i =0; i<hp.latestSuggestions.size(); i++) {
				ObjectRepo.test.log(LogStatus.PASS, "Latest Suggestions Queary is : "+suggestionsList.get(i));
			}
		}else {
			ObjectRepo.test.log(LogStatus.PASS, "Latest Suggestions Queary is not display");
		}
	}
	
	public static void ClickOnBackIcon() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		ButtonHelper.click(kp.backIcon, "Back Icon");
	}
	
	public static boolean getKnowledgeAnalytics() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		boolean value =  kp.knowledgeAnalyticsTitle.isDisplayed();
		return value;
	}
	
	
	
	public static void VerifyThatKnowledgeAnalyticsPageIsWorking() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement ele :kp.channels) {
			list.add(ele.getText());
		}
	    for(int i = 0; i<kp.channels.size(); i++) {
	    	Thread.sleep(3000);
	    	WebElement element = driver.findElement(By.xpath("//*[text()='"+list.get(i)+"']"));
	    	if(element.isDisplayed()) {
	    		element.click();
	    		ClickOnBackIcon();
	    		if(i+1==kp.channels.size()) {
	    			ObjectRepo.test.log(LogStatus.PASS, "Knowledge Analytics Page Is Working");
	    		}
	    		else if(getKnowledgeAnalytics()==true) {
	    			continue;
	    		}else {
	    			ObjectRepo.test.log(LogStatus.FAIL, "Knowledge Analytics Page Is not Working");
	    			break;
	    		}
	    		
	    	}else {
	    		ObjectRepo.test.log(LogStatus.FAIL, list.get(i)+"not availble");
	    	}
	    }
	}
	
	 public static void VerifyDownvotedQuestionIsDisplayInTheAvatarChannelsUnderKnowledgeAnalytics() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		 WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		 String searchedquestion = element.getAttribute("value");
		 ClickOnKnowledgeAnalyticsIcon();
		 ClickOnMarketResearchChannel();
		 ArrayList<String> downvotedQuestionList = new ArrayList<String>();
		 for(WebElement ele : kp.downvotedQuestion) {
			 String downvotedQuestion = ele.getText();
			 downvotedQuestionList.add(downvotedQuestion);
			 
		 }
		 for(int i=0; i<downvotedQuestionList.size(); i++) {
			 String actualQuestion = downvotedQuestionList.get(i);
			 if(searchedquestion.equals(actualQuestion)) {
				 ObjectRepo.test.log(LogStatus.PASS, "Downvoted Questions Is display in Knowledge Analytics");
				 break;
			 }
			 else if(i+1==downvotedQuestionList.size()) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Downvoted Questions Is not display in Knowledge Analytics");
			 }
		 }
	 }
	 
	public static void SelectMediaTypeGroupOption() throws Exception {
		GenericElements.selectByGroup("Media Type");
	}
	
	public static String GetSwitchedField() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		return hp.switchedField.getText();
	}
	
	public static String GetTypeOfAnswer() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		return hp.textAnswer.getAttribute("class");
	}
	
	
	
	public static void VerifyThatMediaTypeGroupingIsWorking() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String>listTab= new ArrayList<>();
		listTab.add("Text");
		listTab.add("Table");
		listTab.add("Image");
		String[] strArray = null;  
		for(int i =0; i<listTab.size(); i++) {
			if(i<listTab.size()) {
				Thread.sleep(5000);
				WebElement element = driver.findElement(By.xpath("(//*[text()='"+listTab.get(i)+"'])[1]"));
				element.click();
				String text = listTab.get(i);
				String textexpected = GetSwitchedField();
				if(textexpected.contains(text)) {
					ObjectRepo.test.log(LogStatus.PASS, text+" Media Type group is working");
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, text+" Media Type group is not working");
					break;
				}
			}
		}
	}
	
	public static void VerifyThatKnowledgeRecallPercentageAreShowingOnTheAvatarsCard() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		if(kp.knowledgeRecallPersentage.isDisplayed()) {
			String recall=kp.knowledgeRecallPersentage.getText();
			String recallpercentage = recall.replace("KNOWLEDGE RECALL ", "").replace("%", "");
			ObjectRepo.test.log(LogStatus.PASS, "Knowledge Recall : "+recallpercentage + " Is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Knowledge Recall Percentage Is not displayed");
		}
	}
	
	public static void VerifyThatUpVotedQuestionIsShowinginTheAvatarChannel() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		
		ArrayList<String> questionList = new ArrayList<String>();
		for(WebElement el : kp.upVotedQuestion ) {
			questionList.add(el.getText());
		}
		
		for(int i=0; i<questionList.size(); i++) {
			String expectedQuestion = GenericHelper.getTestData("TextInputData");
			String question=questionList.get(i);
			if(expectedQuestion.equals(question)) {
				ObjectRepo.test.log(LogStatus.PASS, "Upvoted Question Is Showing In The Avatar Channel Under Active Knowledge");
				break;
			}
			else if(i+1==questionList.size()) {
				ObjectRepo.test.log(LogStatus.FAIL, "Upvoted Question Is not Showing In The Avatar Channel Under Active Knowledge");
			}
		}
		
	}
	
	public static void ClickOnSkipButton() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		ButtonHelper.click(kp.skipButton, "Skip Button");
	}
	
	public static void ClickOnCloseIconPopUp() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.closeIconPopUp, "Close Icon");
	}
	
	public static void VerifyThatExploreAndAskAndAuditAvatarColunmIsDisplayUnderAudit_Report(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		String exploreAndAskAvatarColunmName = hp.exploreAndAskAvatarColunm.getText();
		String auditAvatarColunmName = hp.auditAvatarColunm.getText();
		String exploreAndAskAvatarChannelsName =GenericHelper.getTestData("Channel");
		String auditAvatarChannelsName =GenericHelper.getTestData("Channel");
		
		if(exploreAndAskAvatarColunmName.equals(exploreAndAskAvatarChannelsName)&&auditAvatarColunmName.equals(auditAvatarChannelsName)) {
			ObjectRepo.test.log(LogStatus.PASS, "Explore and Ask Avatar Colunm and Audit Avatar Colunm Is display Under Audit Report");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Explore and Ask Avatar Colunm and Audit Avatar Colunm Is not display Under Audit Report");
		}
	}
	
	public static void ClickOnGoogleDriveDataConnector() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.googleDriveDataConnector, "Google Drive DataConnector");
	}
	
	public static void ClickOnConnectGoogleDriveAccountButton() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.ConnectGoogleDriveAccountButton, "Connect Google Drive Account Button");
	}
	
	public static void EnterClientIdAndClientSecret() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		TextBoxHelper.enterText(dp.clientId, "Client Id", "881215898851-qgsh5mm4ll9sj8l9rt6cka96akci2hg0.apps.googleusercontent.com");
		TextBoxHelper.enterText(dp.clientSecret, "Client Secret", "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");
	}
	
	public static void ClickOnConnectButton() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.connectButton, "Connect Button");
	}
	
	
	public static void VerifyAllHelpSectionIsdisplay() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String Sections[] = GenericHelper.getTestData("Data").split(",");
		for(int i=0; i<hp.neshHelpSection.size(); i++) {
		GenericElements.ValidateRelevantElementIsDisplayed(hp.neshHelpSection,Sections[i],Sections[i]);
		}
	}
	
	public static void ClickOnNeedToRaiseRequestField() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.needToRaiseField, "Raise Request");
	}
	
	public static void VerifyAllRaiseRequestSectionIsdisplay() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(3000);
		String Section[] = GenericHelper.getTestData("Data1").split(",");
		for(int i=0; i<hp.raiseRequestFields.size(); i++) {
		GenericElements.ValidateRelevantElementIsDisplayed(hp.raiseRequestFields,Section[i],Section[i]);
		}
	}
	
	public static void VerifySignUpAllFieldIsDisplayInSequence() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String FiedName[] = GenericHelper.getTestData("Data").split(",");
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement el : hp.signUpFields) {
			String fieldName = el.getAttribute("placeholder");
			list.add(fieldName);
		}
		
		for(int i=0; i<hp.signUpFields.size(); i++) {
			if(list.get(i).equals(FiedName[i])) {
				ObjectRepo.test.log(LogStatus.PASS,FiedName[i]+" Field is display");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,FiedName[i]+" Field is not display");
			}
		}
	}
	
	public static void VerifyThatResetButtonIsNotPresentOnTheSearchResultPage(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		int size = hp.resetButton.size();
		if(1>size) {
			ObjectRepo.test.log(LogStatus.PASS,"Reset Button Is Not Present On The Search Result Page");	
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Reset Button Is Present On The Search Result Page");
		}
	}
	
	public static void VerifyThatConnectEdgarFeatureIsWorking(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.addingCompanyToEdgar,"Adding Company To Edgar");
	}
	
	 public static int getTotalResult() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 int totalAnswer = hp.searchedAnswer.size();
		 return totalAnswer;
	 }
	
	public static void VerifyThatWhenClickOnShowOtherAnswerButtonThenItsNotAffectForOtherTab() throws InterruptedException{
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String>listTab= new ArrayList<>();
		listTab.add("Text");
		listTab.add("Table");
		listTab.add("Image");
		
		for(int i =1; i<listTab.size(); i++) {
			if(i<listTab.size()) {
				Thread.sleep(8000);
				WebElement element = driver.findElement(By.xpath("//*[text()='"+listTab.get(i)+"']"));
				element.click();
				int totalAnswer = getTotalResult();
				if(totalAnswer<6) {
					ObjectRepo.test.log(LogStatus.PASS,listTab.get(i)+" Tab Is not effected");
				}else {
					ObjectRepo.test.log(LogStatus.FAIL,listTab.get(i)+" Tab Is effected");
				}
			}
		}
	}
	
	public static void VerifyThatWhenUserVotedTheAnswerThenVoteThumbIsNotDisplay() throws Exception{
		HubbellHomePage hp = new HubbellHomePage(driver);
		VerifyThatUserCanSelectTheAReasonWhyTheySelectedDownThumb();
		ClickOnDataManagerNavigationIcon();
		ClickOnHomeNavigationIcon();
		if(hp.votingPopUp.size()<1) {
			ObjectRepo.test.log(LogStatus.PASS,"Voting PopUp Is not Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Voting PopUp Is Display");
		}
	}
	
	public static void VerifyThatChangeProfileIconIsValid(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.changeProfileIcon,"Profile Icon");
	}
	
	public static void ClickOnExpertNavigationIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.expertIcon, "Expert Icon");
	}
	
	public static void VerifyThatAllProfileDetailsAreShowingOnExpertCard() throws Exception {
		ProfilePage pp = new ProfilePage(driver);
		ExpertPage ep = new ExpertPage(driver);
		ClickOnProfileButton();
		ClickOnSettingsButton();
		ButtonHelper.click(pp.editButton, "Edit Button");
		ArrayList<String> Profilelist = new ArrayList<String>();
		for (WebElement el :pp.profile) {
			String value = el.getAttribute("value");
			Profilelist.add(value);
		}
		ClickOnExpertNavigationIcon();
		String expertName = driver.findElement(By.xpath("//*[text()='"+Profilelist.get(0)+" "+Profilelist.get(1)+"']")).getText();
		String expertBusinessUnit = driver.findElement(By.xpath("(((//*[text()='"+Profilelist.get(0)+" "+Profilelist.get(1)+"']//parent::*//parent::*//parent::*//parent::*[@class='EntityCard_section__VZC22']/following-sibling::*)[1]//*[@class='Pair_root__EP-E5'])[1]//*)[2]")).getText();
		String expertJobTitle = driver.findElement(By.xpath("(((//*[text()='"+Profilelist.get(0)+" "+Profilelist.get(1)+"']//parent::*//parent::*//parent::*//parent::*[@class='EntityCard_section__VZC22']/following-sibling::*)[1]//*[@class='Pair_root__EP-E5'])[2]//*)[2]")).getText();
		String expertJobTitleBusiness = expertJobTitle +", "+ expertBusinessUnit;
		String expectedName = Profilelist.get(0)+" "+Profilelist.get(1);
		String expectedJobTitleBusinessUnit = Profilelist.get(2)+","+" "+Profilelist.get(3);
		
		if(expectedName.equals(expertName) && expectedJobTitleBusinessUnit.equals(expertJobTitleBusiness)) {
			ObjectRepo.test.log(LogStatus.PASS,"Expert Name, Job Title and Business Unit Is Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Expert Name, Job Title and Business Unit not Display");
		}
		List<WebElement> AvatarList =driver.findElements(By.xpath("(//*[text()='"+Profilelist.get(0)+" "+Profilelist.get(1)+"']//parent::*//parent::*//parent::*//parent::*[@class='EntityCard_section__VZC22']/following-sibling::*)[2]//*//*//*"));
		
		for(WebElement el :AvatarList) {
			GenericElements.ValidateElementIsDisplayed(el,el.getText());
		}
	}
	
	public static void VerifyThatMetadataSectionIsDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.metadataSection,"Metadata Section");
	}
	
	public static void VerifyThatMetadataIsDisplayOfQuery(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.metadataSection,"MetaData");
	}
	
	
	public static int getTotalUploadedFile(WebElement element) {
		String text = element.getText();
		String[] arr = null;
		arr = text.split(" ");
		String totalfile = arr[0];
		int totalUploadedFile = Integer.parseInt(totalfile);
		return totalUploadedFile;
	}
	
	public static void VerifyThatUserIsAbleToRemoveTheFileFromUploadedFile() throws Exception{
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		WebElement elementThreeDot = GenericElements.getTestOrProdLocator(dp.uploadedFileWithAssociatedToAvatarThreeDotIcon,dp.uploadedFileWithAssociatedToAvatarThreeDotIconProd);
		int beforetotalUploadedFile = getTotalUploadedFile(dp.uploadedFileWithOutAssociatedToAvatar);
		
		if(beforetotalUploadedFile!=0) {
			ButtonHelper.click(elementThreeDot, "Three dot Icon");
			ButtonHelper.click(dp.fileRemoveButton, "Remove Button");
			Thread.sleep(8000);
			int afterTotalUploadedFile = getTotalUploadedFile(dp.uploadedFileWithOutAssociatedToAvatar);
			
			if(beforetotalUploadedFile!=afterTotalUploadedFile) {
				ObjectRepo.test.log(LogStatus.PASS,"User Is Able To Remove The File From Uploaded File");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,"User Is Not Able To Remove The File From Uploaded File");
			}
		}else {
			int beforetotalUploadedFile1 = getTotalUploadedFile(dp.uploadedFileWithAssociatedToAvatar);
			
			if(beforetotalUploadedFile1!=0) {
				ButtonHelper.click(elementThreeDot, "Three dot Icon");
				ButtonHelper.click(dp.fileRemoveButton, "Remove Button");
				Thread.sleep(8000);
				int afterTotalUploadedFile1 = getTotalUploadedFile(dp.uploadedFileWithAssociatedToAvatar);
				
				if(beforetotalUploadedFile1!=afterTotalUploadedFile1) {
					ObjectRepo.test.log(LogStatus.PASS,"User Is Able To Remove The File From Uploaded File");
				}else {
					ObjectRepo.test.log(LogStatus.FAIL,"User Is Not Able To Remove The File From Uploaded File");
				}
			}
		}
		
		
	}
	
	public static void VerifyThatMetadataResultIsCorrectDisplay(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.metadataSection,"Metadata Result");
	}
	
	public static void VerifyThatGeneratedTopAnswerIsAppearingFromDataConnector(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		try {
			if(hp.dataConnectorData.isDisplayed()) 
				ObjectRepo.test.log(LogStatus.PASS,"Top Answer is Coming From Data Connector");
			else
				ObjectRepo.test.log(LogStatus.FAIL, "Top Answer is not Coming From Data Connector");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found Top Answer From Data Connector");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	
	
	public static void SelectDownVotedQuestionFromKnowledgeAnalytics() throws Exception {
		WebElement ele = driver.findElement(By.xpath("(//*[text()='"+GenericHelper.getTestData("TextInputData")+"']//parent::*)[1]"));
		boolean assigne = GenericElements.isDownVotedQuestionAssigned(GenericHelper.getTestData("TextInputData"));
		if(assigne==false) {
			ButtonHelper.click(ele, "Down Voted Question");
		}else {
			System.out.println("Download Question Already Assigned!");
		}
	}
	
	public static void UserSelfAssigneQuestion() throws Exception {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		ButtonHelper.click(kp.SelfAssign, "Self Assign User");
	}
	
	public static void VerifyThatExpertAnswerIsShowingOnTheTopOfAnswerPage() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ClickOnHomeNavigationIcon();
		WriteAnyQuestions();
		PressEnterKeys();
		WebElement ele =(WebElement)hp.answertitle.get(0);
		if(ele.getText().equals("EXPERT ANSWER")) {
			ObjectRepo.test.log(LogStatus.PASS,"Expert answer is display at the top");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Expert answer is not display at the top");
		}
	}
	
	public static void ClickOnPostQuestionToAnExpertButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.postQuestionButton, "Post Question To An Expert Button");
	}
	
	public static void SelectExpert() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.expert, hp.expert.getText());
	}
	
	public static void VerifyThatDataConnectorCardIsNotDisplayInsideFileCard() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<>();
		for(WebElement el : hp.allCard) {
			String cardName  = el.getText();
			list.add(cardName);
		}
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals("Data Connectors")) {
				ObjectRepo.test.log(LogStatus.FAIL,"Data Connectors Card Is Display Inside File card");
				break;
			}else {
				if(i==list.size()-1) {
					ObjectRepo.test.log(LogStatus.PASS,"Data Connectors Card Is not Display Inside File card");
				}
			}
		}
	}
	
	
	public static void VerifyThatRemovingAnyFileNotLeadToUserLogout() throws Exception{
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		WebElement elementThreeDot = GenericElements.getTestOrProdLocator(dp.uploadedFileWithAssociatedToAvatarThreeDotIcon,dp.uploadedFileWithAssociatedToAvatarThreeDotIconProd);
		int beforetotalUploadedFile = getTotalUploadedFile(dp.uploadedFileWithOutAssociatedToAvatar);
		
		if(beforetotalUploadedFile!=0) {
			ButtonHelper.click(elementThreeDot, "Three dot Icon");
			ButtonHelper.click(dp.fileRemoveButton, "Remove Button");
			Thread.sleep(10000);
			int afterTotalUploadedFile = getTotalUploadedFile(dp.uploadedFileWithOutAssociatedToAvatar);
			
			if(beforetotalUploadedFile!=afterTotalUploadedFile) {
				String expectedUrl = "https://test-demo.hellonesh.io/upload/file/manual";
				String url = driver.getCurrentUrl();
				if(expectedUrl.equals(url)) {
					ObjectRepo.test.log(LogStatus.PASS,"Removing Any File Not Lead To User Logout");
				}else {
					ObjectRepo.test.log(LogStatus.FAIL,"Removing Any File Lead To User Logout");
				}
				
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,"User Is Not Able To Remove The File From Uploaded File");
			}
		}else {
			int beforetotalUploadedFile1 = getTotalUploadedFile(dp.uploadedFileWithAssociatedToAvatar);
			
			if(beforetotalUploadedFile1!=0) {
				ButtonHelper.click(elementThreeDot, "Three dot Icon");
				ButtonHelper.click(dp.fileRemoveButton, "Remove Button");
				Thread.sleep(10000);
				int afterTotalUploadedFile1 = getTotalUploadedFile(dp.uploadedFileWithAssociatedToAvatar);
				
				if(beforetotalUploadedFile1!=afterTotalUploadedFile1) {
					String expectedUrl = "https://test-demo.hellonesh.io/upload/file/manual";
					String url = driver.getCurrentUrl();
					if(expectedUrl.equals(url)) {
						ObjectRepo.test.log(LogStatus.PASS,"Removing Any File Not Lead To User Logout");
					}else {
						ObjectRepo.test.log(LogStatus.FAIL,"Removing Any File Lead To User Logout");
					}
					
				}else {
					ObjectRepo.test.log(LogStatus.FAIL,"User Is Not Able To Remove The File From Uploaded File");
				}
			}
		}
	}
	
	public static void ClickOnDropDownIconInAssociatedSection() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.associatedDropDownIcon, "Drop Down Icon");
	}
	
	public static String SelectAssociatedAvatar() throws Exception {
		String avatar = GenericHelper.getTestData("Channel");
		WebElement element = driver.findElement(By.xpath("//*[text()='"+avatar+"']"));
		ButtonHelper.click(element, avatar);
		return avatar;
	}
	
	
	public static void VerifyThatAssociatedFilesAreShowingRelatedAvatar() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		String avatar = SelectAssociatedAvatar();
		String selectedAvatar = dp.associatedAvatar.getText();
		int totalFile = getTotalUploadedFile(dp.uploadedFileWithAssociatedToAvatar);
		if(avatar.equals(selectedAvatar)) { 
			ObjectRepo.test.log(LogStatus.PASS,avatar +" Associated "+totalFile+" Files Are Display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,avatar +" Associated Files Are Not Display");
		}
	}
	
	public static void ClickOnShowFileSummaryLink() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.showFileSummaryLink, hp.showFileSummaryLink.getText());
	}
	
	public static void VerifyThatFileSummaryIsDisplay() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.fileSummarySection,"File Summary Section");
	}
	
	public static void ClickOnThreeDotIcon() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.threeDotIcon,"Three Dot Icon");
	}
	
	public static void SelectFolder() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.folderButton,"Folder");
	}
	
	public static void VerifyThatRemoveAndCancelButtonIsNotAvailableInTheFolder() {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ArrayList<String> AllOption = new ArrayList<String>();
		String text="Cancel";
		String text2="Remove";
		for(WebElement el:dp.options) {
			String option = el.getText();
			AllOption.add(option);
		}
		for(int i=0; i<AllOption.size(); i++) {
			if(AllOption.get(i).equals(text)||AllOption.get(i).equals(text2)) {
				ObjectRepo.test.log(LogStatus.FAIL,text+" and "+text2+" Is Available in the folder Option");
				break;
			}
			else if(i+1==AllOption.size()){
				ObjectRepo.test.log(LogStatus.PASS,text+" and "+text2+" Is not Available in the folder Option");
			}
		}
	}
	
	public static void VerifyThatOnlyOneReasonIsSelectingAtATimeOfVotingQuestion() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> dataCheck = new ArrayList<String>();
		for(WebElement el : hp.radioFeedback) {
			String attribute = el.getAttribute("data-checked");
			dataCheck.add(attribute);
		}
		int totalAtt=0;
		
		for(int i=0; i<dataCheck.size(); i++) {
			String data = dataCheck.get(i);
			if(data.equals("true")) {
				totalAtt = totalAtt+1;
			}
		}
		if(totalAtt==1) {
			ObjectRepo.test.log(LogStatus.PASS,"Only One Reason Is Selecting At A Time Of Voting Question");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Multiple Reason Is Selecting At A Time Of Voting Question");
		}
	}
	
	public static void VerifyThatAvatarSelectionIsWorking() throws Exception {
		String channelName = "Product Support";
		GenericHelper.selectoptionfromdropDown(channelName);
		WebElement el = driver.findElement(By.xpath("((//*[text()='"+channelName+"'])[2]//parent::*)[1]"));
		String text = el.getAttribute("data-selected");
		if(text.equals("true")) {
			ObjectRepo.test.log(LogStatus.PASS,"Avatar Selection Is Working");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Avatar Selection Is not Working");
		}
	}
	
	public static void UploadFile() throws Exception {
//		HubbellHomePage hp = new HubbellHomePage(driver);
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
		chooseFile.sendKeys("C:\\Users\\STELCO\\Desktop\\Nesh_Automation\\automationtests\\src\\main\\resources\\uploadedDocument\\relevantDocument\\brochure_Aluminium-Coil_203_en_LR_final.pdf");
//		chooseFile.click();
//		Robot rb = new Robot();
//		
//	    // copying File path to Clipboard
//	    StringSelection str = new StringSelection("C:\\Users\\STELCO\\Desktop\\Nesh_Automation\\automationtests\\src\\main\\resources\\uploadedDocument\\relevantDocument\\brochure_Aluminium-Coil_203_en_LR_final.pdf");
//	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
//	 
//	     // press Contol+V for pasting
//	     rb.keyPress(KeyEvent.VK_CONTROL);
//	     rb.keyPress(KeyEvent.VK_V);
//	 
//	    // release Contol+V for pasting
//	    rb.keyRelease(KeyEvent.VK_CONTROL);
//	    rb.keyRelease(KeyEvent.VK_V);
////	 
//	    // for pressing and releasing Enter
//	    rb.keyPress(KeyEvent.VK_ENTER);
//	    rb.keyRelease(KeyEvent.VK_ENTER);
//	    Thread.sleep(1000);
		ButtonHelper.click(dp.closeIcon, "close Icon");
	}
	
	public static void VerifyThatCancelButtonIsnotVissiblehWhenUploadingFiles(){
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.uploadingfile.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Cancel Button Is Not Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Cancel Button Is Displayed");
		}
	}
	
	public static void VerifyThatTredingChartIsShowing(){
		 ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		 GenericElements.ValidateElementIsDisplayed(themepage.trendingChart,"Trending Chart");
	}
	
	public static void ClickOnSharePointDataConnector() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ButtonHelper.click(dp.sharePointDataConnector, "Sahre Point DataConnector");
	}
	
	 public static void VerifyThatSharePointConnected(){
		  ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		  String[] strArray = null;
		  String text = themepage.connectedFile.getText();
		  strArray = text.split(" ");  
		  int totalfile = Integer.parseInt(strArray[0]);
		  if(totalfile!=0) {
			  ObjectRepo.test.log(LogStatus.PASS,"SharePoint is Connected");
		  }else {
			  ObjectRepo.test.log(LogStatus.FAIL,"SharePoint is not Connected");
		  }
	 }
	 
	 public static boolean IsSourceLinkClickable() {
		 boolean event = false;
		 try {
			 ClickOnAnswerSourceLink();
			 event = true;
		 } catch (Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL,"Source Link Is not Clickable");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		 }
		 return event;
	 }
	 
	 public static void VerifyThatSourceLinkIsClickableAndPageisNotReloaded() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String SourceLink = hp.questionList.getText();
		 if(IsSourceLinkClickable()==true) {
			 ObjectRepo.test.log(LogStatus.PASS,"Source Link Is Clickable");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Source Link Is not Clickable");
		 }
		 ClickOnBackButton();
		 String Sourcelink = driver.findElement(By.xpath("(//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix'])[1]")).getText();
		  if(SourceLink.equals(Sourcelink)) {
			  ObjectRepo.test.log(LogStatus.PASS,"Passages Page is Not Reloaded");
		  }else {
			  ObjectRepo.test.log(LogStatus.FAIL,"Passages Page is Reloaded");
		  }
	 }
	 
	 public static void VerifyThatVerticallyScrollIsPresent() {
		 JavascriptExecutor javascript = (JavascriptExecutor) driver;
		 Boolean b1 = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		 if(b1==true) {
			 ObjectRepo.test.log(LogStatus.PASS,"Vertical Scroll Is present");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Vertical Scroll is not present");
		 }
	 }
	 
	 public static void VerifyThatAskedQuestionIsAppearingInTheMyQuestionSection() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ClickOnHomeNavigationIcon();
		 String askedquestion =GenericHelper.getTestData("TextInputData");
		 WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		 element.click();
		 Thread.sleep(5000);
		 String actualQuestion = hp.myQuestionSection.getText();
		 if(askedquestion.equals(actualQuestion)) {
			ObjectRepo.test.log(LogStatus.PASS,"Asked Question Is Appearing In The My Question Section");
		 }else {
			ObjectRepo.test.log(LogStatus.FAIL,"Asked Question Is Not Appearing In The My Question Section");
		 }
	 }
	 
	 public static void VerifyThatPasswordCriteriaWorkAsExpected() throws Exception {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 Actions action = new Actions(driver);
		 String[] passwordList = {"reepu", "reepukumar", "ReepuKumar","ReepuKumar1","Reepukumar1@"}; 
		 
		 for(int i=0; i<passwordList.length; i++) {
			 lp.txtPassword.sendKeys(passwordList[0]);
			 WebElement element =  driver.findElement(By.xpath("//*[@class='IntroLayout_intro__2OkfX']"));
			 action.moveToElement(element).click().perform();
			 if(lp.passwordErrorMessage.isDisplayed()) {
				 action.moveToElement(lp.passwordErrorMessage).perform();
				 Thread.sleep(5000);
				 WebElement passwordErrorType = driver.findElement(By.xpath("(//*[@class='Tooltip_body__MTizS Tooltip_alignment-center__H9KAT Tooltip_color-primary__2W1-_ ErrorChip_errorMessage__2whPH']/*)[2]"));
				 if(passwordErrorType.isEnabled()) {
					 String errorType = passwordErrorType.getText();
					 ObjectRepo.test.log(LogStatus.PASS,errorType + " Message Displayed");
				 }
			 }
			 else if(i<passwordList.length) {
				 ObjectRepo.test.log(LogStatus.PASS, "Password Criteria Work As Expected");
				 break;
			 }else {
				 ObjectRepo.test.log(LogStatus.PASS,"Password Criteria not Work As Expected");
				 break;
			 }
		 }
	 }
	 
	 public static void VerifyThatSearchPageSummaryContainRelevantPassage() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String text = hp.summaryPart.getText();
		 String[] strArray = null;  
		 strArray = text.split(" ");
		 ArrayList<String> ansList = new ArrayList<String>();
		 for(WebElement el : hp.passageAnswer) {
			 String passageAnswer = el.getText();
			 ansList.add(passageAnswer);
		 }
		 for(int i=0; i<ansList.size(); i++) {
			 boolean validation = false;
			 for(int j=0; j<strArray.length; j++) {
				 if(ansList.get(i).contains(strArray[j])) {
					 ObjectRepo.test.log(LogStatus.PASS, "Result Page Summary Contain Relevant Passage");
					 validation=true;
					 break;
				 }
			 }
			 if(validation==true) {
				 break;
			 }
			 else if(i+1 == ansList.size()) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Result Page Summary Not Contain Relevant Passage");
				 break;
			 }
		 }
	 }
	
	 public static void VerifyThatPassageIsCommingFromDataConnector() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 GenericElements.ValidateElementIsDisplayed(hp.dataConnectorData,"Data Connector");
	 }
	 
	 public static void VerifyThatSuggestedQuestionIsComingFromGoogleDriveDataConnector() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String text = hp.passageAnswerDataConnector.getText();
		 String [] strarray = null;
		 strarray = text.split(" ");
		 ArrayList<String> suggestedQuestionList = new ArrayList<String>();
		 for (WebElement el : hp.suggestedQuestionList) {
			 suggestedQuestionList.add(el.getText());
		 }
		 for(int i =0; i<suggestedQuestionList.size(); i++) {
			 boolean check = false;
			 for(int j=0; j<strarray.length; j++) {
				 if(suggestedQuestionList.get(i).contains(strarray[j])) {
					 ObjectRepo.test.log(LogStatus.PASS, "Suggetsed Question Is Coming From Google Driver Data Connector");
					 check=true;
					 break;
				 }
			 }
			 if(check==true) {
				 break;
			 }
			 else if(i+1==suggestedQuestionList.size()) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Suggetsed Question Is not Coming From Google Driver Data Connector");
				 break;
			 }
		 }
	 }
	
	 public static void VerifyThatMailContainBoldText() throws Exception {
			HubbellHomePage hp = new HubbellHomePage(driver);
			Thread.sleep(5000);
			GenericElements.ValidateElementIsDisplayed(hp.questionAssignMessage,hp.questionAssignMessage.getText());
	}
	 
	public static void VerifyThatAvatarIsNotShowingWhenRefreshAvatarPage() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 driver.navigate().refresh();
		 
		 if(hp.avatarPage.size()==0) {
			 ObjectRepo.test.log(LogStatus.PASS, "Avatar Page Is not Showing");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Avatar Page Is not Showing");
		 }
	 }
	 
	 public static void VerifyThatLatestUpdatesIsLastOptionOfNavbar() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 Actions action = new Actions(driver);
		 ArrayList<String> allMenuOption = new ArrayList<String>();
		 
		 for(WebElement el :hp.allMenuOption) {
			 action.moveToElement(el).perform();
			 WebElement element = driver.findElement(By.xpath("//*[@class='Tooltip_body__MTizS Tooltip_alignment-center__H9KAT Tooltip_color-primary__2W1-_ Sidebar_tooltipList__37556']"));
			 if(element.isDisplayed()) {
				String menuOption =  element.getText();
				allMenuOption.add(menuOption);
			 }
		 }
		 
		 if(allMenuOption.get(allMenuOption.size()-1).equals("Latest Updates")) {
			 ObjectRepo.test.log(LogStatus.PASS, "Latest Updates Option is last Option of Navbar");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Latest Updates Option is not last Option of Navbar");
		 }
	 }
	 
	 public static void VerifyThatPostToAnExpertOptionIsDisableWhenOnceTimePostedQuestion() throws InterruptedException {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 Thread.sleep(5000);
		 if(hp.postToAnExpertButton.size()==0) {
			 ObjectRepo.test.log(LogStatus.PASS, "Post to an Expert Button Is not Display");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Post to an Expert Button Is Display");
		 }
	 }
	 
	 public static void VerifyThatQuestionIsAssignedToSelectedExpertName() throws Exception {
			HubbellHomePage hp = new HubbellHomePage(driver);
			Thread.sleep(5000);
			GenericElements.ValidateElementIsDisplayed(hp.questionAssignMessage,hp.questionAssignMessage.getText());
			driver.get("https://mail.google.com/");
	 }
	 
	 public static void VerifyThatScrollIsStillOnSameTopicCardWhichWasClicked() throws Exception {
		 ThemeLocatorPage themepage = new ThemeLocatorPage(driver);
		 ButtonHelper.click(themepage.backIcon, "Back Icon");
		 if(themepage.topicCard.isDisplayed()) {
			 ObjectRepo.test.log(LogStatus.PASS, "Scroll is Still on Same  Topic Crad");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Scroll is not Still on Same  Topic Crad");
		 }
	 }
	 
	 public static void ClickOnAvatarNavigationIcon() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 ButtonHelper.click(hp.avatarNavigationIcon, "Avatar Navigation");
	 }
	 
	 public static void VerifyThatCreateAvatarButtonIsDisplayAtTheAvatarPage() {
		 AvatarPage ap = new AvatarPage(driver);
		 GenericElements.ValidateElementIsDisplayed(ap.createAvatarButton,"Create Avatar Button");
	 }
	 
	 public static void ClickOnThreeDotIconOfAvatarCard() throws Exception {
		 AvatarPage ap = new AvatarPage(driver);
		 ButtonHelper.click(ap.threeDot, "Three Dot's Icon");
	 }
	 
	 public static void VerifyThatEditOptionIsAvailableInTheAvatarCard() {
		 AvatarPage ap = new AvatarPage(driver);
		 if(ap.editOption.getText().equals("Edit")) {
			 ObjectRepo.test.log(LogStatus.PASS, "Edit Option Is Display");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "SEdit Option Is not Display");
		 }
	 }
	 
	 public static void VerifyThatRunDomainAdaptationButtonIsDisable() {
		 AvatarPage ap = new AvatarPage(driver);
		 boolean bl =ap.runDomainAdaptationButton.isEnabled();
		 if(bl==false) {
			 ObjectRepo.test.log(LogStatus.PASS, "Run Domain Adaptation Button Is Disable");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Run Domain Adaptation Button Is  Enable");
		 }
	 }
	 
	 
	 public static void VerifyThatShareOptionIsDisable() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 boolean bl =hp.shareIcon.isEnabled();
		 if(bl==false) {
			 ObjectRepo.test.log(LogStatus.PASS, "Share Option Is Disable");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Share Option Is Enable");
		 }
	 }
	 
	 public static void VerifyThatAuditAndDecisionFieldsAreAppearingCollapsedByDefault() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 try {
			 WebDriverWait wait = new WebDriverWait(driver,60);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Audit report']/*")));
			 
			 if(hp.auditDownIcon.isDisplayed() && hp.decisionsDownIcon.isDisplayed()) {
				 String cl1 = hp.auditDownIcon.getAttribute("class");
				 String cl2 = hp.decisionsDownIcon.getAttribute("class");
				 
				 if(cl1.equals("Icon_icon__2Atss Icon_icon-arrow-down__3-KAt") &&cl2.equals("Icon_icon__2Atss Icon_icon-arrow-down__3-KAt")) {
					 ObjectRepo.test.log(LogStatus.PASS, "Audit and decisions Fields are default Collapsed");
				 }else {
					 ObjectRepo.test.log(LogStatus.FAIL, "Audit and decisions Fields are not default Collapsed");
				 }
			 }
			
		} catch (Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	 }
	 
	 
	 public static void VerifyThatUserAbleToExpendAuditAndDecisionFields() throws Exception {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Audit report']/*")));
		 
		 ButtonHelper.click(hp.auditDownIcon, "Audit Down Icon");
		 GenericElements.ValidateElementIsDisplayed(hp.auditContent,"Audit Content is Display");
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Decisions']/*")));
		 ButtonHelper.click(hp.decisionsDownIcon, "Decision Down Icon");
		 GenericElements.ValidateElementIsDisplayed(hp.decisionContent,"Decision Content is Display");
	 }
	 
	 public static void RefreshThePage() {
		 driver.navigate().refresh();
		 ObjectRepo.test.log(LogStatus.INFO, "Refresh Page");
	 }
	 
	 public static void MarketResearchIsDisplayOnAvatarSelectionPage() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 if(hp.marketResearchChannel.isDisplayed()) {
			 ObjectRepo.test.log(LogStatus.PASS, "Market Reasearch Page is Display On Avatar Page");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Market Reasearch Page is not Display On Avatar Page");
		 }
	 }
	 
	 
	 public static void MarketResearchIsDisplayOnHomePage() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 if(hp.marketResearchChannel.isDisplayed()) {
			 ObjectRepo.test.log(LogStatus.PASS, "Market Reasearch Page is Display On Home Page");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Market Reasearch Page is not Display On Home Page");
		 }
	 }
	 
	 public static void VerifyThatMarketResearchOptionIsPresentOnBothAvatarSelectionPageAndHomePage() throws Exception {
		  MarketResearchIsDisplayOnAvatarSelectionPage();
		 SelectExploreAndAksOption();
		 ClickOnNextButton();
		 MarketResearchIsDisplayOnHomePage();
	 }
	 
	 
	 public static void VerifyThatMetadataReturnsExpectedDate() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String[] strArray1 = null; 
		 String metadata =  hp.metaData.getText();
		 String query =GenericHelper.getTestData("TextInputData");
		 strArray1 = query.split(" ");
		 
		 ArrayList<String> value1 = new ArrayList<String>();
		 
		 for(int i=0; i<strArray1.length; i++) {
			 value1.add(strArray1[i]);
		 }
		 
		 for(int i=0; i<value1.size(); i++) {
			if(metadata.contains(value1.get(i))) {
				ObjectRepo.test.log(LogStatus.PASS, "Expected Date is Display In The MetaData Section");
				break;
			}
			if(i+1==value1.size()) {
				ObjectRepo.test.log(LogStatus.FAIL, "Expected Date is not Display In The MetaData Section");
			}
		 }
	 }
	 
	 public static void NavigateToTheRequestAccessPage() {
		 if(GenericElements.getEnvironment().equals("QA")) {
			 driver.get("https://test-demo.hellonesh.io/request-access");
		 }else {
			 driver.get("https://demo.hellonesh.io/request-access");
		 }
		 
	 }
	 
	 public static void EnterEmailId() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		TextBoxHelper.enterText(lp.txtUserName, "Email", GenericHelper.getTestData("HubbellUsername"));
	 }
	 
	 public static void VerifyThatAccessRequestSuccessfulSent() {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 GenericElements.ValidateElementIsDisplayed(lp.requestMessage,"Request Sent Message");
	 }
	 
	 
	 public static void VerifyThatAllWebSitesAreConnectedAndAssociatedToAnAvatar() {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		 String text = dp.associatedWebsite.getText();
		 String[] strArray = null;  
		 strArray = text.split(" ");
		 
		 int totalConnectedWebsites = Integer.parseInt(strArray[0]);
		 
		 if(totalConnectedWebsites>0) {
			 ObjectRepo.test.log(LogStatus.PASS, "All WebSites Are Connected And Associated To An Avatar");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "All WebSites Are not Connected And Associated To An Avatar");
		 }
	 }
	 
	 
	 public static void VerifyThatAuditAvatarOptionIsNotAvailableInTheAvatarPickerPage() {
		 try {
			 HubbellLoginPage lp = new HubbellLoginPage(driver);
			 String text = lp.auditAvatarOption.getText();
			 if(text.contains("Audit")) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Audit Avatar Option is Display");
			 }else {
				 ObjectRepo.test.log(LogStatus.PASS, "Audit Avatar option is Not Display");
			 }
		} catch (Exception e) {
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
			ObjectRepo.test.log(LogStatus.FAIL, e.getMessage());
		}
	 }
	 
	 public static void VerifyThatAuditAvatarOptionIsNotAvailableInTheAvatarPickerHomePage() {
		 try {
			 HubbellHomePage hp = new HubbellHomePage(driver);
			 String text = hp.auditAvatarOption.getText();
			 if(text.contains("Audit")) {
				 ObjectRepo.test.log(LogStatus.FAIL, "Audit Avatar Option is Display");
			 }else {
				 ObjectRepo.test.log(LogStatus.PASS, "Audit Avatar option is Not Display");
			 }
		} catch (Exception e) {
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
			ObjectRepo.test.log(LogStatus.FAIL, e.getMessage());
		}
	 }
	 
	 public static void VerifyThatSearchBarOptionIsNotPresentInTheDataManagerPage() {
		 try {
			 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
			 int size = dp.searchBar.size();
			 if(size==0) {
				 ObjectRepo.test.log(LogStatus.PASS, "Search Bar Option is not Display");
			 }else {
				 ObjectRepo.test.log(LogStatus.FAIL, "Search Bar Option is Display");
			 }
		} catch (Exception e) {
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
			ObjectRepo.test.log(LogStatus.FAIL, e.getMessage());
		}
	 }
	 
	 public static void VerifyThatGeneratedSummaryContainKeyExtractionsOfPassages() throws InterruptedException {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String marked = hp.highlightedSentance.getText();
		 String summary = hp.summaryPart.getText();
		 String arr[]=null;
		 arr = marked.split(" ");
		 ArrayList<String> list = new ArrayList<String>();
		 for(int i=0; i<arr.length; i++) {
			 list.add(arr[i]);
		 }		
		 for(int i=0; i<list.size(); i++) {
			 if(summary.contains(list.get(i))) {
					ObjectRepo.test.log(LogStatus.PASS, "Summary Contain Key Extractions Of Passages");
					break;
			 }
			else if(i+1==list.size()) {
				ObjectRepo.test.log(LogStatus.FAIL, "Summary Contain not Key Extractions Of Passages");
			}
		 }
	 }
	 
	 public static List<WebElement> getWebElement(String xpath) {
		  List<WebElement> el = driver.findElements(By.xpath(xpath));
		  return el;
	 }
	 
	 public static void VerifyThatPaginationIsWorkingOnWebSitePage() throws Exception {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		 ArrayList<String> txtlist = new ArrayList<String>();
		 int totalpages =dp.lastPage.size();
		 if(totalpages>0){
			 for(WebElement element : dp.lastPage) {
				 txtlist.add(element.getText());
			 }
			 String txt = txtlist.get(txtlist.size()-1);
			 int totalPages = Integer.parseInt(txt); 
			 int totalConnectedWebsite =0; 
			
			 for(int i=1; i<=totalPages; i++) {
				 List<WebElement> el = getWebElement("(//*[@class='Sources_container__UdBAx'])[2]//*[@class='BaseDataSourceCard_container__3AFiS']");
				 int totalweb = el.size();
				 totalConnectedWebsite = totalConnectedWebsite + totalweb;
					String term =Integer.toString(i+1);
					if(term.equals("13")) {
						break;
					}
				    WebElement btn = driver.findElement(By.xpath("//*[@aria-label='Page "+term+"']"));
					ButtonHelper.click(btn, btn.getText());
			 }
			 int totalWebsite = getTotalUploadedFile(dp.uploadedFileWithOutAssociatedToAvatar);
			
			 if(totalWebsite==totalConnectedWebsite) {
				ObjectRepo.test.log(LogStatus.PASS, "Pagination is Working On Website Page");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, "Pagination is not Working on Website Page");
			} 
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "There is no Pagination");
		 }
		 
	 }
	 
	 public static void VerifyThatSearchOptionIsWorkingOnWebsitePage() throws InterruptedException {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		 
		 ArrayList<String> listall = new ArrayList<String>();
		 
		 for(WebElement element : dp.conectedFileName) {
			 listall.add(element.getText());
		 }
		 int index = (int)(Math.random()*(listall.size()-0+1)+0);
		 
		 String text = listall.get(index);
		 TextBoxHelper.enterText(dp.searchField, "",text);
		 Thread.sleep(5000);
		 String searchText = driver.findElement(By.xpath("(//*[@class='BaseDataSourceCard_title__30rL1'])[1]")).getText();
		 
		 if(text.equals(searchText)) {
			 ObjectRepo.test.log(LogStatus.PASS, "Search Option is Working On Website Page");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Search Option is not Working On Website Page");
		 }
	 }
	 
	 public static void VerifyThatPaginationIsWorkingOnWebFilePage() throws Exception {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		 ArrayList<String> txtlist = new ArrayList<String>();
		 for(WebElement element : dp.lastPage) {
			 txtlist.add(element.getText());
		 }
		String txt = txtlist.get(txtlist.size()-1);
		int totalPages = Integer.parseInt(txt); 
		int totalConnectedfile =0; 
		
		for(int i=1; i<=totalPages; i++) {
			 List<WebElement> el = getWebElement("(//*[@class='PaginatedGrid_row__+zDPJ'])[3]//*[@class='BaseDataSourceCard_root__3Tv5K']");
			 int totalweb = el.size();
			 totalConnectedfile = totalConnectedfile + totalweb;
			 String term =Integer.toString(i+1);
				
				if(term.equals(Integer.toString(totalPages+1) )) {
					break;
				}
			    WebElement btn = driver.findElement(By.xpath("(//*[@aria-label='Page "+term+"'])"));
				ButtonHelper.click(btn, btn.getText());
		 }
		int totalfile = getTotalUploadedFile(driver.findElement(By.xpath("(//*[@class='Title_root__eU152'])[3]")));
		if(totalfile==totalConnectedfile) {
			ObjectRepo.test.log(LogStatus.PASS, "Pagination is Working On File Page");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL, "Pagination is not Working on File Page");
		}
	 }
	 
	 public static void VerifyThatSearchOptionIsWorkingOnUploadFilePage() throws InterruptedException {
		 DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);

		 ArrayList<String> listall = new ArrayList<String>();
		 
		 for(WebElement element : dp.conectedFileName) {
			 listall.add(element.getText());
		 }
		 int index = (int)(Math.random()*(listall.size()-0+1)+0);
		 
		 String text = listall.get(index);
		 TextBoxHelper.enterText(dp.searchField, "",text);
		 Thread.sleep(5000);
		 String searchText = driver.findElement(By.xpath("(//*[@class='BaseDataSourceCard_title__30rL1'])[1]")).getText();
		 
		 if(text.equals(searchText)) {
			 ObjectRepo.test.log(LogStatus.PASS, "Search Option is Working On UploadFile Page");
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "Search Option is not Working On UploadFile Page");
		 }
	 }
	 
	 public static String[] getTotalWordOfSummary() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 String Summarytxt =  hp.summaryPart.getText();
		 
		 String[] strArray = null;  
		 strArray = Summarytxt.split(" ");
		return strArray;
	 }
	 
	 public static void VerifyThatSmallMediumLargeToggleButtonIsWorking() throws InterruptedException {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 int List;
		 int valueList[] = new int[3];
		 int size = hp.summarySizeToggle.size();
		 size= size-1;
		 for(int i = 0; i<size;i++) {
			WebElement el = hp.summarySizeToggle.get(i);
			el.click();
			Thread.sleep(5000);
			String [] wordList = getTotalWordOfSummary();
			List = wordList.length; 
			valueList[i] = List;
		 }
		 
		 if(valueList[0] <= valueList[1]) {
			 if(valueList[1]<=valueList[2]) {
				 ObjectRepo.test.log(LogStatus.PASS, "Summary Toggle Button Is Working");
			 }else {
				 ObjectRepo.test.log(LogStatus.FAIL, "Summary Toggle Button Is not Working");
			 }
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL, "Summary Toggle Button Is not Working");
		 }
	 }
	 
	 public static void ClickOnSSOButton() throws Exception {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 ButtonHelper.click(lp.SSOButton, "SSO Button");
	 }
	 
	 public static void EnterUsername() {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 TextBoxHelper.enterText(lp.emailText, "Email", GenericHelper.getTestData("HubbellUsername"));
	 }
	 
	 public static void ClickOnNextBtn() throws Exception {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 ButtonHelper.click(lp.nextButton, "Next Button");
	 }
	 
	 public static void EnterPassword() {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 TextBoxHelper.enterText(lp.passwordText, "Password", GenericHelper.getTestData("HubbellPassword"));
	 }
	 
	 public static void ClickOnSignButton() throws Exception {
		 HubbellLoginPage lp = new HubbellLoginPage(driver);
		 ButtonHelper.click(lp.signButton, "Sign Button");
		 ButtonHelper.click(lp.nobtn, "no Button");
	 }
	 
	 public static void VerifyUserIsAbleToLoginWithSSOAccount() {
		 String expectedUrl = "https://test-demo.hellonesh.io/home";
			String url = driver.getCurrentUrl();
			if(expectedUrl.equals(url)) {
				ObjectRepo.test.log(LogStatus.PASS,"User Is Able To Login With SSO Account");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,"User Is Not Able To Login With SSO Account");
			}
	 }
	 
	 public static void VerifyThatSummaryFeedbackOptionIsAppear() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 GenericElements.ValidateElementIsDisplayed(hp.summaryFeedBack,"Summary Feed Back Face Icon");
	 }
	 
	 public static void VerifyThatThumbUpDownFeedbackOptionRemoved() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 if(hp.ThumbFeedbackOption.size()==0) {
			 ObjectRepo.test.log(LogStatus.PASS,"Thumb Up and Down Feedback Option Removed");
		 }else {
			 ObjectRepo.test.log(LogStatus.FAIL,"Thumb Up and Down Feedback Option didn't Removed");
		 }
	 }
	 
	 public static void VerifyThatPostAnExpertButtonIsWorking() throws Exception {
			HubbellHomePage hp = new HubbellHomePage(driver);
			Thread.sleep(5000);
			GenericElements.ValidateElementIsDisplayed(hp.questionAssignMessage,hp.questionAssignMessage.getText());
	 }
	 
	 
	 public static void ExpertCanAnswerOfAnssignedQuestion() throws Exception {
		 QuestionPage qp = new QuestionPage(driver);
		 String expectedQuestion = "“"+GenericHelper.getTestData("TextInputData")+"“";
		 WebElement element =  driver.findElement(By.xpath("//*[text()='"+expectedQuestion+"']"));
		 ButtonHelper.click(element, "Question Selected");
		 TextBoxHelper.enterText(qp.answerTextField, "Answer", "Green hydrogen is becoming a key component in bringing about energy transition and ensuring a sustainable future");
	 }
	 
	 public static void RewriteSameQuestions() {
		 HubbellHomePage hp = new HubbellHomePage(driver);
		 WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		 TextBoxHelper.enterText(element, "Rewrite Question", GenericHelper.getTestData("TextInputData"));
	}
	 
	public static void VerifyThatExpertAnswerIsShowingInTheTopOfAnswer() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement ele : hp.fileName) {
			String filename = ele.getText();
			list.add(filename);
		}
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals("EXPERT ANSWER")) {
				ObjectRepo.test.log(LogStatus.PASS,"Expert answer is display in the top answer");
				break;
			}
			else if (i+1==list.size()) {
				ObjectRepo.test.log(LogStatus.FAIL,"Expert answer is not display in the top answer");
			}
		}
	}
	
	public static void ClickOnPostToExpertButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.postToExpertBtn, "Post To Expert Button");
	}

	public static void VerifyThatFilesAreUploading() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		GenericElements.ValidateElementIsDisplayed(dp.uploadingproccess,dp.uploadingproccess.getText());
	}
		
	public static void ClickOnFilterButton() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		hp.txtSearchIndex.click();
		ButtonHelper.click(hp.filterButton, "Filter Button");
	}
	
	public static WebElement ChooseFiltertype(String name) {
		WebElement el =driver.findElement(By.xpath("//*[text()='"+name+"']"));
		return el;
	}
	
	public static void SelectExtension(String name) throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		for(WebElement el : hp.fileExtensions) {
			String exten = el.getText();
			if(exten.equals(name)) {
				ButtonHelper.click(el, name);
				break;
			}
		}
	}
	
	public static void ApplyFileExtensionFilter() throws Exception {
		WebElement el =ChooseFiltertype(GenericHelper.getTestData("Data"));
		ButtonHelper.click(el, el.getText());
		SelectExtension(GenericHelper.getTestData("Data1"));
	}
	
	public static void VerifyThatFiltersAreWorking() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String expecteddataformate = GenericHelper.getTestData("Data1");
		expecteddataformate=expecteddataformate.toUpperCase();
		System.out.println(expecteddataformate);
		ArrayList<String> list = new ArrayList<String>(); 
		for(WebElement el : hp.fileName) {
			list.add(el.getText());
		}
		
		for (int i=0; i<list.size(); i++) {
			String text = list.get(i);
			int index = text.lastIndexOf('.');
			if(index > 0) {
			      String extension = text.substring(index + 1);
			      if(extension.equals(expecteddataformate)) {
			    	  ObjectRepo.test.log(LogStatus.PASS,"Filter option is working");
			      }else {
			    	  ObjectRepo.test.log(LogStatus.FAIL,"Filter option is not working");
			      }
			    }
		}
		
	}
	
	public static void VerifyThatOldFiltereatureIsNotAvailable() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.filterButton,"New Filter");
	}

	
	public static void VerifyThatAnswerWillBeChangedAfterApplyFilter() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list1 = new ArrayList<String>();
		for(WebElement el : hp.fileName) {
			list.add(el.getText());
		}
		ClickOnFilterButton();
		ApplyFileExtensionFilter();
		ClickOnSearchButton();
		
		for(WebElement el : hp.fileNameUpdated) {
			list1.add(el.getText());
		}
		
		if(list.equals(list1)==true) {
			ObjectRepo.test.log(LogStatus.FAIL,"Answer have not changed");
		}else {
			ObjectRepo.test.log(LogStatus.PASS,"Answer have changed");
		}
	}
	
	public static void SelectSmileIconForVoteTheSummary() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.smileIcon, "Smile Icon");
	}
	
	public static void SelectFeedbackOption() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.feedbackIcon, "Feedback");
	}
	
	public static void VerifyFeedbackIconIsNotDisplayOnceUserSubmitsTheirFeedbackForSummary() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		if(hp.feedbackFace.size()==0) {
			ObjectRepo.test.log(LogStatus.PASS,"Feedback icon is not display");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Feedback icon is display");
		}
	}
	
	public static void VerifyThatClientFilterIsWorking() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String actual = hp.clientFilter.getText();
		actual = actual.replace("Client: ", "");
		String expected = GenericHelper.getTestData("Data1");
		if(actual.equals(expected)) {
			ObjectRepo.test.log(LogStatus.PASS,"Client Filter is working");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Client Filter is not working");
		}
	}
	
	public static void VerifyThatTextBubleIsNotTooBright() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.notBright.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Text Bubble is not too bright");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL,"Text Bubble is too bright");
		}
	}
	
	public static void VerifyThatDecisionOptionIsNotShowing() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		if(hp.decisionOtion.size()==0) {
			ObjectRepo.test.log(LogStatus.PASS,"Decision Option is not showing");
		}else{
			ObjectRepo.test.log(LogStatus.FAIL,"Decision Option is showing");
		}
	}
	
	public static void VerifyThatPreviousButtonIsWorkingOfPagination() throws Exception {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		ArrayList<String> txtlist = new ArrayList<String>();
		 int totalpages = dp.lastPage.size()-1;
		 if(totalpages>0){
			 for(WebElement element : dp.lastPage) {
				 txtlist.add(element.getText());
			 }
			 String txt = txtlist.get(txtlist.size()-1);
			 int totalPages = Integer.parseInt(txt); 
			 ButtonHelper.click(dp.prevBtn, "Previous Button");
			 
			 if(totalpages==totalPages) {
				 ObjectRepo.test.log(LogStatus.PASS,"Previous Button is working fine");
			 }else {
				 ObjectRepo.test.log(LogStatus.FAIL,"Previous Button is not working fine");
			 }
		 }else {
			 ObjectRepo.test.log(LogStatus.PASS, "There is no Pagination");
		 }
		
	}
	
	public static void VerifyThatAvatatNameIsDsiplayInCamelCaseWord() {
		KnowledgeAnalyticsPage kp = new KnowledgeAnalyticsPage(driver);
		for(WebElement el : kp.channels) {
			String text = el.getText();
			char[] chr = text.toCharArray();
			if(Character.isUpperCase(chr[0])&&Character.isLowerCase(chr[1])) {
				ObjectRepo.test.log(LogStatus.PASS,text+" is Displayed in Format");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL,text+" is not Displayed in Format");
			}
		}
	}
	
	public static void VerifyThatPostToExpertButtonIsClickable() throws InterruptedException {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		if(hp.postQuestionPage.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Post To Expert Button Is Clickable");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Post To Expert Button Is not Clickable");
		}
	}
	
	public static void VerifyThatClickingBetweenQuestionsDoNotCloseTheSearchBar() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ButtonHelper.click(hp.spaceBtQues, "Space Between Question");
		GenericElements.ValidateElementIsDisplayed(hp.spaceBtQues,"Search bar");
	}
	
	public static void VerifyThatAvatarSelectionPoupIsDisplayEveryTimeLogin(){
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		GenericElements.ValidateElementIsDisplayed(lp.avatarSelectOption,"Avatar Selcetion Page");
	}
	
	public static void VerifyThatUpvotedAnswerIsShowingAtTheTop() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		if(hp.upvotedCount.size()!=0) {
			for(WebElement el : hp.upvotedCount) {
				String txt = el.getText();
				int count = Integer.parseInt(txt);
				list.add(count);
			}
			
			if(list.size()==1) {
				ObjectRepo.test.log(LogStatus.PASS,"Hightest upvoted Answer is on the top");
			}
			else if (list.size()>=2) {
				for(int i=0; i<list.size(); i++) {
					for(int j=1; j<list.size(); j++) {
						if(list.get(i)>=list.get(j)) {
							if(i==list.size()-1) {
								ObjectRepo.test.log(LogStatus.PASS,"Hightest upvoted Answer is on the top");
								break;
							}
						}else {
							ObjectRepo.test.log(LogStatus.FAIL,"Highest upvoted answer is not on the top");
						}
					}
				}
				
			}
			
			
		}else {
			ObjectRepo.test.log(LogStatus.PASS,"There is no upvoted answer for this question");
		}
	}
	
	public static boolean PressEnterKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		return true;
		
	}

	public static void VerifyThatEnterButtonIsWorking() {
		if(PressEnterKey()==true) {
			ObjectRepo.test.log(LogStatus.PASS,"Enter Key Pressed");
		}else {
			ObjectRepo.test.log(LogStatus.PASS,"Enter Key did not Press");
		}
	}
	
	public static void VerifyThatCardAndTreeViewIsDisplayed() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<WebElement> webList = new ArrayList<WebElement>();
		String expectedClass = "Switch_item__2bJhs Switch_checked__1PC6O Switch_clickable__2UIW3";
		for(WebElement el : hp.viewMode) {
			webList.add(el);
		}
		
		for(int i=0; i<webList.size(); i++) {
			ButtonHelper.click(webList.get(i), webList.get(i).getText());
			 String actualClass = webList.get(i).getAttribute("class");
			 if(expectedClass.equals(actualClass)) {
				 ObjectRepo.test.log(LogStatus.PASS,webList.get(i).getText()+"view is Displayed");
				 if(i==1) {
					 break;
				 }
			 }else {
				 ObjectRepo.test.log(LogStatus.FAIL,webList.get(i).getText()+"view is not Displayed");
			 }
		}
	}
	
	public static String GetNeshBubbleText() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String text = hp.bubbleText.getText();
		return text;
	}
	
	public static void VerifyThatNeshBubbleTextIsDifferentForNormalQuestionAndLowConfidenceQuestion() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		WebElement element = GenericElements.getOldOrNewLocator(hp.txtSearchIndex,hp.searchOption);
		TextBoxHelper.enterText(element, "Normal Question", GenericHelper.getTestData("TextInputData"));
		PressEnterKeys();
		String bubbleTextWithNormalQuestion = GetNeshBubbleText();
		WriteAnyNewQuestions();
		PressEnterKeys();
		String bubbleTextWithLowConfidenceQuestion = GetNeshBubbleText();
		
		if(bubbleTextWithNormalQuestion.equals(bubbleTextWithLowConfidenceQuestion)) {
			ObjectRepo.test.log(LogStatus.FAIL,"Nesh bubble text is not different for both type of question");
		}else {
			 ObjectRepo.test.log(LogStatus.PASS,"Nesh bubble text is different for both type of question");
		}
	}
	
	
	public static void ClickOnOpenExtrasIcon() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<WebElement> webList = new ArrayList<WebElement>();
		int rand = GenericHelper.generateRamdomNumber(0, webList.size());
		for(WebElement el : hp.openExtras) {
			webList.add(el);
		}
		ButtonHelper.click(webList.get(rand), "Open Extras");
	}
	
	public static void VerifyThatThereAreMultipleOptionInTheOpenExtrasPanel() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.openExtrasPanel,"Open Extras Panel");
		
		for(WebElement el : hp.openExtrasOption) {
			ObjectRepo.test.log(LogStatus.PASS,el.getText() +" view is Displayed");
		}
	}
	
	public static void VerifyThatSideOpenExtrasPanelIsDisplayed() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		GenericElements.ValidateElementIsDisplayed(hp.openExtrasPanel,"Open Extras Panel");
	}
	
	public static void VerifyThatGraphViewIsDisplay() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement el : hp.viewMode) {
			ButtonHelper.click(el, el.getText());
			list.add(el.getText());
		}
		boolean flag = false;
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals("Graph")) {
				flag = true;
				break;
			}
		}
		
		if(flag==true) {
			ObjectRepo.test.log(LogStatus.PASS," Graph view is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL," Graph view is not Displayed");
		}
	}
	
	public static void VerifyThatExpertPageHaveTrainedNeshHeading() {
		ExpertPage ep = new ExpertPage(driver);
		GenericElements.ValidateElementIsDisplayed(ep.expertHeading,"Trained Nesh Heading");
	}
	
	public static String PickedAvatar() {
		ExpertPage ep = new ExpertPage(driver);
		return ep.avatarPicker.getText();
	}
	
	public static void VerifyThatTheUserCanSelectOtherAvatarOnTheExpertPage() throws Exception {
		ExpertPage ep = new ExpertPage(driver);
		ArrayList<WebElement> weblist = new ArrayList<WebElement>();
		String def_avatar = PickedAvatar();
		ButtonHelper.click(ep.avatarDropDwonIcon, "Drop Dwon Icon");
		
		for(WebElement el : ep.avatarUnselected) {
			String attribute =el.getAttribute("data-selected");
			if(attribute.equals("false")) {
				weblist.add(el);
			}
		}
		int rand = GenericHelper.generateRamdomNumber(0, weblist.size()-1);
		
		ButtonHelper.click(weblist.get(rand), weblist.get(rand).getText());
		
		String changed_avatar = PickedAvatar();
		
		if(!def_avatar.equals(changed_avatar)) {
			ObjectRepo.test.log(LogStatus.PASS,"User Can Select Avatar");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"User Can't Select Avatar");
		}
	}
	
	
	public static void VerifyThatTheTopThreeExpertsAreShownMoreProminentlyAndScoreIsDisplayedForEachExpert() {
		ExpertPage ep = new ExpertPage(driver);
		
		if(ep.topThreeExpert.size()==3) {
			ObjectRepo.test.log(LogStatus.PASS,"There are Top Three Expert is displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"There aren't Top Three Expert is displayed");
		}
		
		String expect= ep.totalResult.getText();
		
		String str [] = expect.split(" ") ;
		System.out.println(str[0]);
		
		int expectedResult = Integer.parseInt(str[0]);
		
		if(ep.score.size()==expectedResult) {
			ObjectRepo.test.log(LogStatus.PASS,"Expert Scrore Is Displayed For Each Expert");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Expert Scrore Is not Displayed For Each Expert");
		}
		
	}
	
	public static void VerifyThatUploadedFileCanBeAssociated() {
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		if(dp.associatedUploadFileSection.isDisplayed()) {
			ObjectRepo.test.log(LogStatus.PASS,"Uploaded File Can Be Associated");
		}else {
			ObjectRepo.test.log(LogStatus.PASS,"Uploaded File Can't Be Associated");
		}
	}
	
	public static void VerifyThatBlankCellIsNotAvailableInThePopularQuestionsAndMyQuestionsSection() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> popularList = new ArrayList<String>();
		ArrayList<String> myQuestionList = new ArrayList<String>();
		
		for(WebElement el : hp.popularQuestionCell) {
			popularList.add(el.getText());
		}
		
		for(WebElement el : hp.myQuestionCell) {
			myQuestionList.add(el.getText());
		}
		
		boolean flag1 = false, flag2 = false;
		
		for(int i=0; i<popularList.size(); i++) {
			if(popularList.get(i).length()==0) {
				flag1 = true;
				break;
			}
		}
		
		for(int i=0; i<myQuestionList.size(); i++) {
			if(myQuestionList.get(i).length()==0) {
				flag2 = true;
				break;
			}
		}
		
		
		if(flag1==false && flag2==false ) {
			ObjectRepo.test.log(LogStatus.PASS,"There is no blank cell available in the Popular Question and My Question Section");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"There is blank cell available in the Popular Question and My Question Section");
		}
	}
	
	public static void ApplyFilter() throws Exception {
		WebElement el =ChooseFiltertype(GenericHelper.getTestData("Data"));
		ButtonHelper.click(el, el.getText());
	}
	
	public static void EnterPeriod() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		driver.findElement(By.xpath("//*[@placeholder='Select period...']")).click();
		driver.findElement(By.xpath("//*[@placeholder='Select period...']")).sendKeys("0102202301242023");
	}
	
	public static void VerifyThatCreatedDateFilterIsWorking() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String expectUrl = "https://test-demo.hellonesh.io/home/search";
		String actualUrl = driver.getCurrentUrl();
		if(expectUrl.equals(actualUrl)) {
			ObjectRepo.test.log(LogStatus.PASS,"Created Date Filter Is Working");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Created Date Filter Is not Working");
		}
	}
	
	public static void VerifyThatTreeViewIsDisplay() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement el : hp.viewMode) {
			ButtonHelper.click(el, el.getText());
			list.add(el.getText());
		}
		boolean flag = false;
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals("Tree")) {
				flag = true;
				break;
			}
		}
		
		if(flag==true) {
			ObjectRepo.test.log(LogStatus.PASS," Tree view is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL," Tree view is not Displayed");
		}
	}
	
	
	public static void VerifyThatClickingOnTheEmptySpaceAboveTheHomeButtonDoesNotLeadToTheHomePage() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		ClickOnDataManagerNavigationIcon();
		String url = driver.getCurrentUrl();
		ButtonHelper.click(hp.emptySpace, "empty space");
		String accutalurl = driver.getCurrentUrl();
		
		if(url.equals(accutalurl)) {
			ObjectRepo.test.log(LogStatus.PASS,"Empty Space Doesn't Lead To The Home Page");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Empty Space Lead To The Home Page");
		}
	}

	public static void VerifyThatThereIsSpaceAvailableBetweenPasswordFieldandForgotPasswordOption() {
		HubbellLoginPage lp = new HubbellLoginPage(driver);
		String value = lp.forgotOption.getCssValue("margin-left");
		value = value.replace("px", "");
		int space = Integer.parseInt(value);
		if(space>0) {
			ObjectRepo.test.log(LogStatus.PASS,"Space is Available Between Password Field and Forgot Password Option");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Space is not Available Between Password Field and Forgot Password Option");
		}
	}
	
	public static void VerifyThatThereIsAuditReportOptionIsAvailableOnTheOpenExtrasPanel() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		boolean flag = false;
		
		for(WebElement el : hp.openExtrasOption) {
			if(el.getText().equals("Audit report")) {
				flag = true;
				break;
			}
		}
		
		if(flag== true) {
			ObjectRepo.test.log(LogStatus.PASS,"Audit report option is available");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Audit report option is not available");
		}
	}
	
	public static void ClickOnSearchRecommendedTopic() throws Exception {
		HubbellHomePage hp = new HubbellHomePage(driver);
		Thread.sleep(5000);
		ButtonHelper.click(hp.searchRecommendedTopic, "Recommended Topic");
	}
	
	public static void VerifyThatAskedQuestionIsNotAvailableOnTheSearchbar() {
		HubbellHomePage hp = new HubbellHomePage(driver);
		String text = hp.searchBar.getText();
		
		if(text.length()==0) {
			ObjectRepo.test.log(LogStatus.PASS,"Asked Question is not available on the search bar");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,"Asked Question is available on the search bar");
		}
	}
	
	public static void VerifyThatFolderCanBeAssociateWithAvatar(){
		DataManagerLocatorPage dp = new DataManagerLocatorPage(driver);
		try {
			Thread.sleep(5000);
			String str = dp.associatedFolderSection.getText();
			String strArray[] = str.split(" ");
			
			if(dp.associatedFolderSection.isDisplayed()) {
				ObjectRepo.test.log(LogStatus.PASS,"Folder Can Be Associate with Avatar ");
				ObjectRepo.test.log(LogStatus.PASS,"Associated Folder :"+strArray[0]);
			}
				
			else
				ObjectRepo.test.log(LogStatus.FAIL,"Folder Can't Be Associate with Avatar ");
			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Click Button");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}
	
	public static void VerifyThatExpertCardsDesignAreSame(){
		ExpertPage ep = new ExpertPage(driver);
		try {
			Thread.sleep(5000);
			String str= ep.totalResult.getText();
			String strArray[] = str.split(" ");
			
			int expectedCard = Integer.parseInt(strArray[0]);
			int cardName = ep.expertName.size();
			int scoreBar = ep.scoreBar.size();
			int businessUnit = ep.businessUnit.size();
			int jobTitle = ep.jobTitle.size();
			int avatar = ep.avatar.size();
			
			int[] intArray = new int[5];
			
			intArray [0] =cardName;
			intArray [1] =scoreBar;
			intArray [2] =businessUnit;
			intArray [3] =jobTitle;
			intArray [4] =avatar;
			boolean flag = false;
			
			for(int i = 0; i<intArray.length; i++) {
				if(expectedCard==intArray[i]) {
					flag = true;
				}else {
					flag = false;
					break;
				}
			}
			
			if(flag==true) {
				ObjectRepo.test.log(LogStatus.PASS, "All Expert Card's Design are Same");
			}else {
				ObjectRepo.test.log(LogStatus.FAIL, "All Expert Card's Design aren't Same");
			}

			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Click Button");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}
	
}
	
