package com.framework.TestCases;

import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.framework.SendMail.sendmail;
import com.framework.configreader.ExcelReader;
import com.framework.configreader.PropertyFileReader;
import com.framework.reporting.ExtentReportHelper;
import com.framework.settings.ObjectRepo;
import com.framework.stepdefinition.StepDefinitions;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/


public class TestCases {
	
	@BeforeSuite
	public void setUp() {
		System.out.println("Setting Up Test Suite");
		ExtentReportHelper.setUpReport();
		ObjectRepo.reader = new PropertyFileReader();
	}
	
	@AfterSuite
	public static void endReport() throws EmailException {
		ExtentReportHelper.endReport();
		Sheet Runmgr = ExcelReader.readInstance();
		String SendReport = Runmgr.getRow(1).getCell(3).toString();
		if(SendReport.equals("Yes")) {
			sendmail.sendmailWithAttachmentFile();
		}
		ObjectRepo.reader=null;
		System.out.println("Test Suite Execution Complete");

	}
	
	@Test
	public static void Hubbell_Validate_Top_Answer() {
		InitiateTestParams.ExecuteTestCase("Hubbell_Validate_Top_Answer");
	}
	
	@Test
	public static void Verify_The_Enter_Key_WorksAs_A_Substitue() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Enter_Key_WorksAs_A_Substitue");
	}
	
	@Test
	public static void Verify_All_The_Field_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_The_Field_Appears");
	}
	
	@Test
	public static void Verify_The_Login_Page_Works_With_Valid_Credentails() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Login_Page_Works_With_Valid_Credentails");
	}
	
	@Test
	public static void Verify_Forgot_Password_Link () {
		InitiateTestParams.ExecuteTestCase("Verify_Forgot_Password_Link");
	}
	
	@Test
	public static void Verify_Password_Field_Masks_The_Password () {
		InitiateTestParams.ExecuteTestCase("Verify_Password_Field_Masks_The_Password");
	}
	
	@Test
	public static void Verify_Login_Page_Does_Not_Allowed_Accessing_Page_Without_Login_in() {
		InitiateTestParams.ExecuteTestCase("Verify_Login_Page_Does_Not_Allowed_Accessing_Page_Without_Login_in");
	}
	
	@Test
	public static void Verify_Tab_Button_Is_Used_To_Nevigate_To_Next_Field() {
		InitiateTestParams.ExecuteTestCase("Verify_Tab_Button_Is_Used_To_Nevigate_To_Next_Field");
	}
	
	@Test
	public static void Verify_Invalid_Credentials_Are_Rejected() {
		InitiateTestParams.ExecuteTestCase("Verify_Invalid_Credentials_Are_Rejected");
	}
	
	@Test
	public static void Verify_If_One_The_Information_Is_Filled_Wrong() {
		InitiateTestParams.ExecuteTestCase("Verify_If_One_The_Information_Is_Filled_Wrong");
	}
	
	@Test
	public static void Verify_If_Both_Fields_Are_Blank() {
		InitiateTestParams.ExecuteTestCase("Verify_If_Both_Fields_Are_Blank");
	}
	
	@Test
	public static void Verify_If_Username_Field_Is_Blank() {
		InitiateTestParams.ExecuteTestCase("Verify_If_Username_Field_Is_Blank");
	}
	
	@Test
	public static void Verify_If_Password_Field_Is_Blank() {
		InitiateTestParams.ExecuteTestCase("Verify_If_Password_Field_Is_Blank");
	}
	
	@Test
	public static void Verify_If_Password_Is_Wrong_And_Username_Is_Correct() {
		InitiateTestParams.ExecuteTestCase("Verify_If_Password_Is_Wrong_And_Username_Is_Correct");
	}
	
	@Test
	public static void Verify_All_Required_Field_Appears_On_Sign_Up_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Field_Appears_On_Sign_Up_Page");
	}
	
	@Test
	public static void Verify_That_All_Required_Fields_Have_A_Red_Mark() {
		InitiateTestParams.ExecuteTestCase("Verify_That_All_Required_Fields_Have_A_Red_Mark");
	}
	
	@Test
	public static void Verify_Enter_Key_Functionality_For_Sign_Up_Button_Works_As_A_Substitue() {
		InitiateTestParams.ExecuteTestCase("Verify_Enter_Key_Functionality_For_Sign_Up_Button_Works_As_A_Substitue");
	}
	
	@Test
	public static void Verify_SignUp_Page_Load_With_None_Of_The_Data_Into_The_Text_Field() {
		InitiateTestParams.ExecuteTestCase("Verify_SignUp_Page_Load_With_None_Of_The_Data_Into_The_Text_Field");
	}
	
	@Test
	public static void Verify_Email_id_Text_Does_Not_Contains_AtTheRate_Special_Sign() {
		InitiateTestParams.ExecuteTestCase("Verify_Email_id_Text_Does_Not_Contains_AtTheRate_Special_Sign");
	}
	
	@Test
	public static void Verify_Email_id_Text_field_with_Other_Special_Sign() {
		InitiateTestParams.ExecuteTestCase("Verify_Email_id_Text_field_with_Other_Special_Sign");
	}
	
	@Test
	public static void Verify_The_SignUp_Button_Without_Filling_Up_Any_Data_On_The_SignUp_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_The_SignUp_Button_Without_Filling_Up_Any_Data_On_The_SignUp_Page");
	}
	
	@Test
	public static void Verify_Home_Navigation(){
		InitiateTestParams.ExecuteTestCase("Verify_Home_Navigation");
	}
	
	@Test
	public static void Verify_Data_Manager_Navigation(){
		InitiateTestParams.ExecuteTestCase("Verify_Data_Manager_Navigation");
	}
	
	
	@Test
	public static void Verify_Latest_Update_Navigation(){
		InitiateTestParams.ExecuteTestCase("Verify_Latest_Update_Navigation");
	}
	
	@Test
	public static void Verify_Logo_Appears_In_The_Each_Navigation_Page_Of_The_Website(){
		InitiateTestParams.ExecuteTestCase("Verify_Logo_Appears_In_The_Each_Navigation_Page_Of_The_Website");
	}
	
	@Test
	public static void Verify_Click_On_Search_Button() {
		InitiateTestParams.ExecuteTestCase("Verify_Click_On_Search_Button");
	}
	
	@Test
	public static void Verify_Answer_is_Showing_Related_Shearched_Query() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_is_Showing_Related_Shearched_Query");
	}
	
	@Test
	public static void Verify_Search_Button_Without_Entering_Any_Keyword() {
		InitiateTestParams.ExecuteTestCase("Verify_Search_Button_Without_Entering_Any_Keyword");
	}
	
	@Test
	public static void Verify_Proper_Answer_is_Showing_Related_Shearched_Query() {
		InitiateTestParams.ExecuteTestCase("Verify_Proper_Answer_is_Showing_Related_Shearched_Query");
	}
	
	@Test
	public static void Verify_All_Required_Options_Appears_In_The_Help_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Options_Appears_In_The_Help_Page");
	}
	
	@Test
	public static void Verify_Find_Help_And_Service_Box_Works_Properly() {
		InitiateTestParams.ExecuteTestCase("Verify_Find_Help_And_Service_Box_Works_Properly");
	}
	
	@Test
	public static void Verify_Report_A_Bug_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Report_A_Bug_Option");
	}
	
	@Test
	public static void Verify_Suggest_Improvement_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Suggest_Improvement_Option");
	}
	
	@Test
	public static void Verify_Suggest_A_New_Feature_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Suggest_A_New_Feature_Option");
	}
	
	@Test
	public static void Verify_Technical_Support_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Technical_Support_Option");
	}
	
	@Test
	public static void Verify_Log_In_Link_Button() {
		InitiateTestParams.ExecuteTestCase("Verify_Log_In_Link_Button");
	}
	
	@Test
	public static void Verify_Nesh_Logo_Button() {
		InitiateTestParams.ExecuteTestCase("Verify_Nesh_Logo_Button");
	}
	
	@Test
	public static void Verify_All_Required_Options_Appears_On_Data_Manager_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Options_Appears_On_Data_Manager_Page");
	}
	
	@Test
	public static void Verify_Files_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Files_Option");
	}
	
	@Test
	public static void Verify_Web_Sites_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Web_Sites_Option");
	}
	
	@Test
	public static void Verify_Data_Connectors_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Data_Connectors_Option");
	}

	@Test
	public static void Verify_Power_Ups_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Power_Ups_Option");
	}
	
	@Test
	public static void Verify_All_Required_Files_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Files_Options_Appears");
	}
	
	@Test
	public static void Verify_Upload_Files_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Upload_Files_Option");
	}

	@Test
	public static void Verify_Upload_FAQs_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Upload_FAQs_Option");
	}
	
	@Test
	public static void Verify_Email_Files_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Email_Files_Option");
	}
	
	@Test
	public static void Verify_All_Required_Upload_Files_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Upload_Files_Options_Appears");
	}
	
	@Test
	public static void Verify_Upload_File_Button_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Upload_File_Button_Option");
	}
	
	
	@Test
	public static void Verify_All_Data_Connectors_Are_Available() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Data_Connectors_Are_Available");
	}
	
	@Test
	public static void Verify_All_Required_Upload_FAQa_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Upload_FAQa_Options_Appears");
	}
	
	@Test
	public static void Verify_Upload_FAQs_Button_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Upload_FAQs_Button_Option");
	}
	
	@Test
	public static void Verify_All_Required_Email_File_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Email_File_Options_Appears");
		
	}
	
	@Test
	public static void Verify_Upload_Email_Button_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Upload_Email_Button_Option");	
	}
	
	@Test
	public static void Verify_All_Required_Web_Sites_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Web_Sites_Options_Appears");
		
	}
	
	@Test
	public static void Verify_User_IS_Able_To_Connect_Web_Site() {
		InitiateTestParams.ExecuteTestCase("Verify_User_IS_Able_To_Connect_Web_Site");
	}
	
	@Test
	public static void Verify_All_Required_Data_Connector_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Data_Connector_Options_Appears");
	}
	
	@Test
	public static void Verify_Blob_Storage_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Blob_Storage_Option");
	}
	
	@Test
	public static void Verify_Share_Point_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Share_Point_Option");
	}
	
	@Test
	public static void Verify_All_Required_Blob_Storage_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Blob_Storage_Options_Appears");
	}
	
	@Test
	public static void Verify_All_Required_Share_Point_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Share_Point_Options_Appears");
	}
	
	@Test
	public static void Verify_Connect_Share_point_Account_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Connect_Share_point_Account_Option");
	}
	
	@Test
	public static void Verify_All_Required_Power_Ups_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_Power_Ups_Options_Appears");
	}
	
	@Test
	public static void VerifyEDGARoption() {
		InitiateTestParams.ExecuteTestCase("VerifyEDGARoption");
	}
	
	@Test
	public static void Verify_All_Required_EDGAR_Options_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Required_EDGAR_Options_Appears");
	}

	@Test
	public static void Verify_Connect_EDGAR_Button_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_Connect_EDGAR_Button_Option");
	}
	
	
	@Test
	public static void Verify_Setting_And_LogOut_Options_Will_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_Setting_And_LogOut_Options_Will_Appears");
	}
	
	@Test
	public static void Verify_All_The_Details_Of_User_Will_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_All_The_Details_Of_User_Will_Appears");
	}
	
	@Test
	public static void Verify_Logout_Option_Work_Properly() {
		InitiateTestParams.ExecuteTestCase("Verify_Logout_Option_Work_Properly");
	}
	
	@Test
	public static void Verify_Compare_All_Default_Functionality_Appears() {
		InitiateTestParams.ExecuteTestCase("Verify_Compare_All_Default_Functionality_Appears");
	}
	
	@Test
	public static void Verify_Compare_All_Functionality_Appears_With_Group_By_Functionality() {
		InitiateTestParams.ExecuteTestCase("Verify_Compare_All_Functionality_Appears_With_Group_By_Functionality");
	}
	
	@Test
	public static void Verify_Compare_All_Functionality_Appears_With_Relevance_Functionality() {
		InitiateTestParams.ExecuteTestCase("Verify_Compare_All_Functionality_Appears_With_Relevance_Functionality");
	}
	
	
	@Test
	public static void Verify_All_The_Filters_Are_Checked_On_By_Default() {
		InitiateTestParams.ExecuteTestCase("Verify_All_The_Filters_Are_Checked_On_By_Default");
	}
	
	@Test
	public static void Verify_Answers_Displayed_On_The_Screen_Should_Exactly_Match_To_The_Filter_Applied() {
		InitiateTestParams.ExecuteTestCase("Verify_Answers_Displayed_On_The_Screen_Should_Exactly_Match_To_The_Filter_Applied");
	}
	
	
	@Test
	public static void Reapplying_Filters_On_A_Filtered_Answer() {
		InitiateTestParams.ExecuteTestCase("Reapplying_Filters_On_A_Filtered_Answer");
	}
	
	
	@Test
	public static void Verify_Default_Checked_On_Of_The_Filters_When_Asked_A_New_Query() {
		InitiateTestParams.ExecuteTestCase("Verify_Default_Checked_On_Of_The_Filters_When_Asked_A_New_Query");
	}
	
	
	@Test
	public static void Verify_Filtering_Query_Results_Using_Location() {
		InitiateTestParams.ExecuteTestCase("Verify_Filtering_Query_Results_Using_Location");
	}
	
	
	@Test
	public static void Verify_Filtering_Query_Results_Using_Source_Type() {
		InitiateTestParams.ExecuteTestCase("Verify_Filtering_Query_Results_Using_Source_Type");
	}
	
	@Test
	public static void Verify_Filtering_Query_Results_Using_Sentiment() {
		InitiateTestParams.ExecuteTestCase("Verify_Filtering_Query_Results_Using_Sentiment");
	}
	
	@Test
	public static void Verify_Filtering_Query_Results_Using_Person() {
		InitiateTestParams.ExecuteTestCase("Verify_Filtering_Query_Results_Using_Person");
	}
	
	@Test
	public static void Verify_Filtering_Query_Results_Using_Organization() {
		InitiateTestParams.ExecuteTestCase("Verify_Filtering_Query_Results_Using_Organization");
	}
	
	@Test
	public static void Verify_Successfully_Saving_A_Response_With_Thumbs_Up_And_Down() {
		InitiateTestParams.ExecuteTestCase("Verify_Successfully_Saving_A_Response_With_Thumbs_Up_And_Down");
	}
	
	@Test
	public static void Verify_The_Resolution_Of_The_Login_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Resolution_Of_The_Login_Page");
	}
	
	@Test
	public static void Verify_The_Cursor_Will_Be_Change_On_The_Navigation_Menu() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Cursor_Will_Be_Change_On_The_Navigation_Menu");
	}
	
	@Test
	public static void Verify_Admin_Is_Able_To_Add_A_New_Company() {
		InitiateTestParams.ExecuteTestCase("Verify_Admin_Is_Able_To_Add_A_New_Company");
	}
	 
	@Test
	public static void Verify_Text_Relevant_Results_Show_Up() {
		InitiateTestParams.ExecuteTestCase("Verify_Text_Relevant_Results_Show_Up");
	}
	
	@Test
	public static void Verify_Image_Relevant_Results_Show_Up() {
		InitiateTestParams.ExecuteTestCase("Verify_Image_Relevant_Results_Show_Up");
	}
	
	@Test
	public static void Verify_Better_Understanding_Of_Questions_For_Better_Results() {
		InitiateTestParams.ExecuteTestCase("Verify_Better_Understanding_Of_Questions_For_Better_Results");
	}
	
	@Test
	public static void Verify_Understanding_Questions_Asked_To_Nesh () {
		InitiateTestParams.ExecuteTestCase("Verify_Understanding_Questions_Asked_To_Nesh");
	}
	
	
	@Test
	public static void Verify_Understanding_Questions () {
		InitiateTestParams.ExecuteTestCase("Verify_Understanding_Questions");
	}
		
	@Test
	public static void Verify_Better_Understanding_Questions () {
		InitiateTestParams.ExecuteTestCase("Verify_Better_Understanding_Questions");
	}
	
	@Test
	public static void Verify_Understanding_Questions_For_Better_Results() {
		InitiateTestParams.ExecuteTestCase("Verify_Understanding_Questions_For_Better_Results");
	}
	
	@Test
	public static void Verify_Understanding_Questions_For_Precise_Results() {
		InitiateTestParams.ExecuteTestCase("Verify_Understanding_Questions_For_Precise_Results");
	}
	
	@Test
	public static void Verify_Understanding_Questions_Using_NLU_Model() {
		InitiateTestParams.ExecuteTestCase("Verify_Understanding_Questions_Using_NLU_Model");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Transcript_Text_In_The_Text_Tab_UI() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Transcript_Text_In_The_Text_Tab_UI");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Transcript_Text_In_Documented_Date_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Transcript_Text_In_Documented_Date_Format");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Investor_Presentation_Text_In_The_Text_Tab_UI() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Investor_Presentation_Text_In_The_Text_Tab_UI");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Investor_Presentation_In_The_Image_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Investor_Presentation_In_The_Image_Format");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Investor_Presentation_In_Documented_Date_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Investor_Presentation_In_Documented_Date_Format");
	}

	@Test
	public static void Verify_Answer_Appear_From_Regulatory_Filing_Text_In_The_Text_Tab_UI() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Regulatory_Filing_Text_In_The_Text_Tab_UI");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_Regulatory_Filing_In_The_Image_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Regulatory_Filing_In_The_Image_Format");
	}

	@Test
	public static void Verify_Answer_Appear_From_Regulatory_filing_In_Documented_Date_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_Regulatory_filing_In_Documented_Date_Format");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_SEC_Filing_Text_In_The_Text_Tab_UI() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_SEC_Filing_Text_In_The_Text_Tab_UI");
	}
	
	@Test
	public static void Verify_Answer_Appear_From_SEC_Filing_In_The_Image_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_SEC_Filing_In_The_Image_Format");
	}
	
	
	@Test
	public static void Verify_Answer_Appear_From_SEC_filing_In_Documented_Date_Format() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Appear_From_SEC_filing_In_Documented_Date_Format");
	}
	
	@Test
	public static void Verify_Two_Different_Channels_Are_Available_At_Search_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_Two_Different_Channels_Are_Available_At_Search_Section");
	}
	
	@Test
	public static void Verify_Response_Of_The_Sales_Growth_Channels() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Of_The_Sales_Growth_Channels");
	}
		
	@Test
	public static void Verify_Response_Of_The_Market_Research_Channel() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Of_The_Market_Research_Channel");
	}
	
	@Test
	public static void Verify_The_Answer_Is_Appearing_From_Uploaded_File() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Answer_Is_Appearing_From_Uploaded_File");
	}
	
	@Test
	public static void Verify_The_Company_And_The_Organization_Options_Is_Appear_Separately() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Company_And_The_Organization_Options_Is_Appear_Separately");
	}
	
	@Test
	public static void Verify_The_Company_Name_Of_The_Bank_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Company_Name_Of_The_Bank_Is_Display");
	}
	
	@Test
	public static void Verify_The_Organization_Name_Of_The_Bank_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Organization_Name_Of_The_Bank_Is_Display");
	}
	
	@Test
	public static void Verify_Presented_Nesh_Answers_Is_Latest_Contant() {
		InitiateTestParams.ExecuteTestCase("Verify_Presented_Nesh_Answers_Is_Latest_Contant");
	}
	
	@Test
	public static void Verify_The_Latest_And_Historical_Content_Should_Be_Appear() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Latest_And_Historical_Content_Should_Be_Appear");
	}
	
	@Test
	public static void Verify_The_Latest_And_Historical_Content_Answer_Should_Be_Appear_Of_Wroge_Asked_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Latest_And_Historical_Content_Answer_Should_Be_Appear_Of_Wroge_Asked_Question");
	}
	
	@Test
	public static void Verify_Every_Occurrence_Of_The_Word_In_The_Question_Not_Highlighted_In_The_Answer() {
		InitiateTestParams.ExecuteTestCase("Verify_Every_Occurrence_Of_The_Word_In_The_Question_Not_Highlighted_In_The_Answer");
	}
	
	@Test
	public static void Verify_User_Count_And_Thumbnails_Should_Be_Visible() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Count_And_Thumbnails_Should_Be_Visible");
	}
		
	@Test
	public static void Verify_Relevant_Answer_Is_Diaplay() {
		InitiateTestParams.ExecuteTestCase("Verify_Relevant_Answer_Is_Diaplay");
	}
	
	@Test
	public static void Verify_Summary_Of_Answer_Is_Appear() {
		InitiateTestParams.ExecuteTestCase("Verify_Summary_Of_Answer_Is_Appear");
	}
	
	@Test
	public static void Verify_Answer_Are_Still_Appearing_After_Switching_All_Channels_To_Market_Research() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Are_Still_Appearing_After_Switching_All_Channels_To_Market_Research");
	}
	
	@Test
	public static void Verify_Answer_Are_Still_Appearing_After_Switching_All_Channels_To_Sales_Growth() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Are_Still_Appearing_After_Switching_All_Channels_To_Sales_Growth");
	}
	
	@Test
	public static void Verify_Energy_Channel_Are_Avaialble_In_Factset() {
		InitiateTestParams.ExecuteTestCase("Verify_Energy_Channel_Are_Avaialble_In_Factset");
	}
	
	@Test
	public static void Verify_Banking_Channel_Are_Avaialble_Under_The_Channel_In_Factset() {
		InitiateTestParams.ExecuteTestCase("Verify_Banking_Channel_Are_Avaialble_Under_The_Channel_In_Factset");
	}
	
	@Test
	public static void Verify_User_Is_Able_To_Switching_The_channels() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Is_Able_To_Switching_The_channels");
	}
	
	@Test
	public static void Verify_Answer_Is_Appearing_From_BTU_For_Factset_User() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Is_Appearing_From_BTU_For_Factset_User");
	}
	
	@Test
	public static void Verify_Answer_Is_Appearing_From_Gas_Basis_Outlook_For_Factset_User() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Is_Appearing_From_Gas_Basis_Outlook_For_Factset_User");
	}
	
	@Test
	public static void Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero");
	}
	
	@Test
	public static void Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero");
	}
	
	@Test
	public static void Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero_In_Factset() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero_In_Factset");
	}

	@Test
	public static void Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero_In_Factset() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero_In_Factset");
	}
	
	@Test
	public static void Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero_In_Hubbell() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Not_Glowing_When_Reponse_Is_Zero_In_Hubbell");
	}
	
	@Test
	public static void Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero_In_Hubbell() {
		InitiateTestParams.ExecuteTestCase("Verify_Response_Box_Is_Glowing_When_Reponse_Is_Grater_Then_Zero_In_Hubbell");
	}
	
	@Test
	public static void Verify_Uploaded_Files_Are_Visible_In_Factset() {
		InitiateTestParams.ExecuteTestCase("Verify_Uploaded_Files_Are_Visible_In_Factset");
	}
	
	@Test
	public static void Verify_NER_Tags_Is_Visible_In_The_Summary_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_NER_Tags_Is_Visible_In_The_Summary_Section");
	}
	
	@Test
	public static void Verify_Content_Extracted_Under_Sales_Growth_From_The_New_Data_Uploaded() {
		InitiateTestParams.ExecuteTestCase("Verify_Content_Extracted_Under_Sales_Growth_From_The_New_Data_Uploaded");
	}
	
	@Test
	public static void Verify_Content_Not_Extracted_Under_Market_Research_From_The_New_Data_Uploaded() {
		InitiateTestParams.ExecuteTestCase("Verify_Content_Not_Extracted_Under_Market_Research_From_The_New_Data_Uploaded");
	}
	
	@Test
	public static void Verify_Content_Extracted_Under_ALL_Option_From_New_Data_Uploaded_And_Old_Data() {
		InitiateTestParams.ExecuteTestCase("Verify_Content_Extracted_Under_ALL_Option_From_New_Data_Uploaded_And_Old_Data");
	}
	
	@Test
	public static void Verify_Extracted_Answers_Are_Relevant_Information_in_Hubbell_Sales_Growth_Channel() {
		InitiateTestParams.ExecuteTestCase("Verify_Extracted_Answers_Are_Relevant_Information_in_Hubbell_Sales_Growth_Channel");
	}
	
	@Test
	public static void Verify_Answers_Obtained_Are_From_BTU_Upstream_Outlook() {
		InitiateTestParams.ExecuteTestCase("Verify_Answers_Obtained_Are_From_BTU_Upstream_Outlook");
	}
	
	@Test
	public static void Verify_Toggle_Is_Available() {
		InitiateTestParams.ExecuteTestCase("Verify_Toggle_Is_Available");
	}
	
	@Test
	public static void Verify_Channels_And_Users_Are_Visible_In_Active_Knowledge_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_Channels_And_Users_Are_Visible_In_Active_Knowledge_Section");
	}
	
	@Test
	public static void Verify_Channels_And_Users_Are_Visible_In_Knowledge_Gaps_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_Channels_And_Users_Are_Visible_In_Knowledge_Gaps_Section");
	}
	
	@Test
	public static void Verify_Information_Are_Same_Of_Active_Knowledge_And_Knowledge_Gaps() {
		InitiateTestParams.ExecuteTestCase("Verify_Information_Are_Same_Of_Active_Knowledge_And_Knowledge_Gaps");
	}
	
	@Test
	public static void Verify_Result_Is_Appearing_From_Long_Term_Gas_Outlook() {
		InitiateTestParams.ExecuteTestCase("Verify_Result_Is_Appearing_From_Long_Term_Gas_Outlook");
	}
	
	@Test
	public static void Verify_UpVoted_Answers_Are_Appearing_At_The_Top() {
		InitiateTestParams.ExecuteTestCase("Verify_UpVoted_Answers_Are_Appearing_At_The_Top");
	}
	
	@Test
	public static void Verify_The_Answers_Are_Obtained_From_The_Deloitte_Documents() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Answers_Are_Obtained_From_The_Deloitte_Documents");
	}
	
	@Test
	public static void Verify_The_Answers_Are_Obtained_From_Oil_Market_Outlook() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Answers_Are_Obtained_From_Oil_Market_Outlook");
	}
	
	@Test
	public static void Verify_Best_Answer_Is_Appear_At_The_Top_In_The_List_And_Also_Appear_Summary() {
		InitiateTestParams.ExecuteTestCase("Verify_Best_Answer_Is_Appear_At_The_Top_In_The_List_And_Also_Appear_Summary");
	}
	
	@Test
	public static void Verify_Newest_Answer_Is_Appear_At_The_Top_In_The_List() {
		InitiateTestParams.ExecuteTestCase("Verify_Newest_Answer_Is_Appear_At_The_Top_In_The_List");
	}
	
	@Test
	public static void Verify_Relevant_Response_Summary_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_Relevant_Response_Summary_Is_Display");
	}
	
	@Test
	public static void Verify_Upvoted_Answer_Appear_At_The_Top_For_Mutliple_Users_But_Answer_Is_Not_Active_For_Another_Users() {
		InitiateTestParams.ExecuteTestCase("Verify_Upvoted_Answer_Appear_At_The_Top_For_Mutliple_Users_But_Answer_Is_Not_Active_For_Another_Users");
	}
	
	@Test
	public static void Verify_Multiple_Relevant_Response_Highlight_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_Multiple_Relevant_Response_Highlight_Display");
	}
	
	@Test
	public static void Verify_Multiple_Relevant_Answer_Highlight_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_Multiple_Relevant_Answer_Highlight_Display");
	}
	
	@Test
	public static void Verify_Expected_And_Relevant_Answer_For_The_Question_Appear_At_The_Top() {
		InitiateTestParams.ExecuteTestCase("Verify_Expected_And_Relevant_Answer_For_The_Question_Appear_At_The_Top");
	}
	
	@Test
	public static void Verify_Shorted_Group_by_Sentiments_Answers_Are_Appear() {
		InitiateTestParams.ExecuteTestCase("Verify_Shorted_Group_by_Sentiments_Answers_Are_Appear");
	}
	
	@Test
	public static void Verify_Knowledge_Gap_Percentage_Is_Visible() {
		InitiateTestParams.ExecuteTestCase("Verify_Knowledge_Gap_Percentage_Is_Visible");
	}
	
	@Test
	public static void Verify_List_Of_Questions_Are_Visible_In_The_Market_Research() {
		InitiateTestParams.ExecuteTestCase("Verify_List_Of_Questions_Are_Visible_In_The_Market_Research");
	}
	
	@Test
	public static void Verify_Upvote_Count_Is_Visible_Next_To_The_Upvote_Arrow() {
		InitiateTestParams.ExecuteTestCase("Verify_Upvote_Count_Is_Visible_Next_To_The_Upvote_Arrow");
	}
	
	@Test
	public static void Verify_Highlited_Text_Is_Readable() {
		InitiateTestParams.ExecuteTestCase("Verify_Highlited_Text_Is_Readable");
	}
	
	@Test
	public static void Verify_By_Myself_Option_is_Visible() {
		InitiateTestParams.ExecuteTestCase("Verify_By_Myself_Option_is_Visible");
	}
	
	@Test
	public static void Verify_By_Myself_Option_Is_Migrate_To_Question_Answer_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_By_Myself_Option_Is_Migrate_To_Question_Answer_Page");
	}
	
	@Test
	public static void Verify_User_is_able_To_See_and_Select_The_Question_They_Had_Like_To_Answer() {
		InitiateTestParams.ExecuteTestCase("Verify_User_is_able_To_See_and_Select_The_Question_They_Had_Like_To_Answer");
	}
	
	
	@Test
	public static void Verify_Answer_Able_To_Be_Written_Is_Plain_Numbered_And_Bulleted_Text() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Able_To_Be_Written_Is_Plain_Numbered_And_Bulleted_Text");
	}
	
	@Test
	public static void Verify_If_User_Submits_The_Answer_To_The_Question_Then_Question_Appear_In_The_Answered_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_If_User_Submits_The_Answer_To_The_Question_Then_Question_Appear_In_The_Answered_Tab");
	}
	
	
	@Test
	public static void Verify_Answer_Is_Editable() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Is_Editable");
	}
	
	@Test
	public static void Verify_Expert_Answer_Is_Appear_With_The_Expert_Name() {
		InitiateTestParams.ExecuteTestCase("Verify_Expert_Answer_Is_Appear_With_The_Expert_Name");
	}
	
	@Test
	public static void Verify_Nesh_Understand_EPS_Are_Companies_And_PE_Is_Private_Equity() {
		InitiateTestParams.ExecuteTestCase("Verify_Nesh_Understand_EPS_Are_Companies_And_PE_Is_Private_Equity");
	}
	
	@Test
	public static void Verify_Each_Basin_Have_Its_Own_Unique_Summary() {
		InitiateTestParams.ExecuteTestCase("Verify_Each_Basin_Have_Its_Own_Unique_Summary");
	}
	
	@Test
	public static void Verify_Product_Support_Channel_Is_Vissible_And_Answers_Come_From_Relevant_Documents() {
		InitiateTestParams.ExecuteTestCase("Verify_Product_Support_Channel_Is_Vissible_And_Answers_Come_From_Relevant_Documents");
	}
	
	@Test
	public static void Verify_Answer_Is_Appearing_Table_Screenshots_Format_Instead_Of_Formateed_Text() {
		InitiateTestParams.ExecuteTestCase("Verify_Answer_Is_Appearing_Table_Screenshots_Format_Instead_Of_Formateed_Text");
	}
	
	@Test
	public static void Verify_images_expand_When_Users_Click_On_The_Card_Link() {
		InitiateTestParams.ExecuteTestCase("Verify_images_expand_When_Users_Click_On_The_Card_Link");
	}

	
	@Test
	public static void Verify_Upvote_Counting_Is_Appear_When_Users_Click_On_The_Upvote_Icon() {
		InitiateTestParams.ExecuteTestCase("Verify_Upvote_Counting_Is_Appear_When_Users_Click_On_The_Upvote_Icon");
	}

	@Test
	public static void Verify_Related_Answer_Is_Appearing_With_The_Apostrophes_Quesion() {
		InitiateTestParams.ExecuteTestCase("Verify_Related_Answer_Is_Appearing_With_The_Apostrophes_Quesion");
	}
	
	@Test
	public static void Verify_Market_Research_Product_Support_And_RegsAndPolicies_Channels_Cards_Are_Display_On_The_Channels_Page_Or_Knowledge_Analytics_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_Market_Research_Product_Support_And_RegsAndPolicies_Channels_Cards_Are_Display_On_The_Channels_Page_Or_Knowledge_Analytics_Page");
	}
	
	
	@Test
	public static void Verify_Previously_Asked_Questions_Should_Populate_The_List() {
		InitiateTestParams.ExecuteTestCase("Verify_Previously_Asked_Questions_Should_Populate_The_List");
	}
	
	
	@Test
	public static void Verify_Shorted_Option_Not_Change_Until_Changed_Again() {
		InitiateTestParams.ExecuteTestCase("Verify_Shorted_Option_Not_Change_Until_Changed_Again");
	}
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------- New UI-------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------
	
	
	
	@Test
	public static void Verify_Three_Different_Suggestions_Catagories_Is_Display_Inside_The_Search_Bar() {
		InitiateTestParams.ExecuteTestCase("Verify_Three_Different_Suggestions_Catagories_Is_Display_Inside_The_Search_Bar");
	}
	
	@Test
	public static void Verify_Top_Passages_Portion_does_Have_Three_OR_More_For_That_Topic() {
		InitiateTestParams.ExecuteTestCase("Verify_Top_Passages_Portion_does_Have_Three_OR_More_For_That_Topic");
	}
	
	@Test
	public static void Verify_There_Are_Five_Randomly_Questions_Appear_In_The_Suggested_Questions_Field() {
		InitiateTestParams.ExecuteTestCase("Verify_There_Are_Five_Randomly_Questions_Appear_In_The_Suggested_Questions_Field");
	}
	
	
	@Test
	public static void Verify_Other_Themes_And_NER_Tags_Are_Appear_Inside_Recommanded_Topic() {
		InitiateTestParams.ExecuteTestCase("Verify_Other_Themes_And_NER_Tags_Are_Appear_Inside_Recommanded_Topic");
	}
	
	@Test
	public static void Verify_Topic_Page_Contains_Topic_Trend_And_Top_Passage_Heading() {
		InitiateTestParams.ExecuteTestCase("Verify_Topic_Page_Contains_Topic_Trend_And_Top_Passage_Heading");
	}
	
	@Test
	public static void Verify_Radio_Button_Is_Display_Inside_Explore_And_Audit_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_Radio_Button_Is_Display_Inside_Explore_And_Audit_Section");
	}
	
	@Test
	public static void Verify_All_Themes_Name_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Themes_Name_Is_Display");
	}
	
	@Test
	public static void Verify_All_Data_Connectors_is_Arranged_Alphabetically_Format_In_Data_Manager_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Data_Connectors_is_Arranged_Alphabetically_Format_In_Data_Manager_Page");
	}
	
	@Test
	public static void Verify_All_Topic_Cards_Highlited_With_Purpul_Color() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Topic_Cards_Highlited_With_Purpul_Color");
	}
	
	
	@Test
	public static void Verify_Select_Avatar_Option_Is_Display_Before_Asking_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_Select_Avatar_Option_Is_Display_Before_Asking_Question");
	}
	
	@Test
	public static void Verify_Favorites_Page_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_Favorites_Page_Is_Display");
	}
	
	@Test
	public static void Verify_Next_Button_Is_Vissible_In_1728X1117_Screen_Resolution() {
		InitiateTestParams.ExecuteTestCase("Verify_Next_Button_Is_Vissible_In_1728X1117_Screen_Resolution");
	}
	
	
	@Test
	public static void Verify_That_Cards_grid_Gap_Are_Equal() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Cards_grid_Gap_Are_Equal");
	}
	
	
	@Test
	public static void Verify_Knowledge_Analytics_Title_is_Display_On_Top_Of_The_Knowledge_Analytics_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_Knowledge_Analytics_Title_is_Display_On_Top_Of_The_Knowledge_Analytics_Page");
	}
	
	@Test
	public static void Verify_Passages_And_Image_Tables_Are_Returned_Together_And_Ranked_In_One_List() {
		InitiateTestParams.ExecuteTestCase("Verify_Passages_And_Image_Tables_Are_Returned_Together_And_Ranked_In_One_List");
	}
	
	
	@Test
	public static void Verify_The_Answer_Obtained_From_The_Document() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Answer_Obtained_From_The_Document");
	}
	
	@Test
	public static void Verify_All_Topics_Are_Included_In_All_Theme_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Topics_Are_Included_In_All_Theme_Tab");
	}
	
	@Test
	public static void Verify_Topics_Are_Change_When_Other_Avatars_Were_Selected() {
		InitiateTestParams.ExecuteTestCase("Verify_Topics_Are_Change_When_Other_Avatars_Were_Selected");
	}
	
	@Test
	public static void Verify_Passages_Are_Change_When_Other_Avatars_Were_Selected() {
		InitiateTestParams.ExecuteTestCase("Verify_Passages_Are_Change_When_Other_Avatars_Were_Selected");
	}
	
	@Test
	public static void Verify_Topic_Are_Disappear_When_Other_Avatars_Were_Selected() {
		InitiateTestParams.ExecuteTestCase("Verify_Topic_Are_Disappear_When_Other_Avatars_Were_Selected");
	}
	
	@Test
	public static void Verify_The_Answers_Will_Be_Change_When_Switch_To_Sales_Avater() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Answers_Will_Be_Change_When_Switch_To_Sales_Avater");
	}
	
	@Test
	public static void Verify_There_Are_Three_Card_Display_Market_Research_Product_Support_And_Regs_And_Policies() {
		InitiateTestParams.ExecuteTestCase("Verify_There_Are_Three_Card_Display_Market_Research_Product_Support_And_Regs_And_Policies");
	}
	
	@Test
	public static void Verify_Recommended_Topic_And_Other_Suggested_Option_Would_be_Change_When_Switch_Other_Avatar() {
		InitiateTestParams.ExecuteTestCase("Verify_Recommended_Topic_And_Other_Suggested_Option_Would_be_Change_When_Switch_Other_Avatar");
	}
	

	@Test
	public static void Verify_All_Themes_Are_Still_When_Channel_Is_Changed() {
		InitiateTestParams.ExecuteTestCase("Verify_All_Themes_Are_Still_When_Channel_Is_Changed");
	}
	
	@Test
	public static void Verify_Themes_Are_Not_Highlighted_With_Purpule_Colour() {
		InitiateTestParams.ExecuteTestCase("Verify_Themes_Are_Not_Highlighted_With_Purpule_Colour");
	}
	
	@Test
	public static void Verify_The_Top_Passages_Are_Relevant_Of_The_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Top_Passages_Are_Relevant_Of_The_Question");
	}
	
	@Test
	public static void Verify_Recommended_Topics_Are_Appearing_On_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_Recommended_Topics_Are_Appearing_On_The_Search_Result_Page");
	}
	
	@Test
	public static void Verify_Recommended_Topics_Passages_Are_Appearing_After_Searching_Results() {
		InitiateTestParams.ExecuteTestCase("Verify_Recommended_Topics_Passages_Are_Appearing_After_Searching_Results");
	}
	
	@Test
	public static void Verify_The_Topics_Name_Is_Display_Under_The_Insights() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Topics_Name_Is_Display_Under_The_Insights");
	}
	
	@Test
	public static void Verify_The_Summary_Is_Not_Regenrating_Of_The_Answer() {
		InitiateTestParams.ExecuteTestCase("Verify_The_Summary_Is_Not_Regenrating_Of_The_Answer");
	}
	
	@Test
	public static void Verify_Search_Result_Page_Is_Not_Refreshing() {
		InitiateTestParams.ExecuteTestCase("Verify_Search_Result_Page_Is_Not_Refreshing");
	}
	
	@Test
	public static void Verify_First_Letter_Is_Capital_Of_Topic() {
		InitiateTestParams.ExecuteTestCase("Verify_First_Letter_Is_Capital_Of_Topic");
	}
	
	@Test
	public static void Verify_Avatar_Selection_Page_Is_Vissible_When_User_Login_First_Time() {
		InitiateTestParams.ExecuteTestCase("Verify_Avatar_Selection_Page_Is_Vissible_When_User_Login_First_Time");
	}
	
	@Test
	public static void Verify_Summary_Is_Generated_When_User_Asked_Question_From_Home_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_Summary_Is_Generated_When_User_Asked_Question_From_Home_Tab");
	}
	
	@Test
	public static void Verify_Summary_And_Answer_Is_Highlighting_On_The_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_Summary_And_Answer_Is_Highlighting_On_The_Result_Page");
	}
	
	@Test
	public static void Verify_Downvoted_Question_Is_Display_in_Knowledge_Analytics() {
		InitiateTestParams.ExecuteTestCase("Verify_Downvoted_Question_Is_Display_in_Knowledge_Analytics");
	}
	
	@Test
	public static void Verify_Top_Five_Answer_Is_Display_In_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_Top_Five_Answer_Is_Display_In_The_Search_Result_Page");
	}
	
	
	@Test
	public static void Verify_Other_Answer_has_been_Vissible_When_Click_On_Show_Other_Answer_Button() {
		InitiateTestParams.ExecuteTestCase("Verify_Other_Answer_has_been_Vissible_When_Click_On_Show_Other_Answer_Button");
	}
	
	@Test
	public static void Verify_Only_Top_Five_Answer_has_been_Vissible_When_Click_On_Hide_Answers_Link() {
		InitiateTestParams.ExecuteTestCase("Verify_Only_Top_Five_Answer_has_been_Vissible_When_Click_On_Hide_Answers_Link");
	}
	
	@Test
	public static void Verify_Generated_Answers_Hold_Are_Passages_Tables_And_Images_Together() {
		InitiateTestParams.ExecuteTestCase("Verify_Generated_Answers_Hold_Are_Passages_Tables_And_Images_Together");
	}
	
	@Test
	public static void Verify_Suggested_Question_have_Relevent_Answer_In_Product_Support_Avatar() {
		InitiateTestParams.ExecuteTestCase("Verify_Suggested_Question_have_Relevent_Answer_In_Product_Support_Avatar");
	}
	
	@Test
	public static void Verify_Shorted_Relevent_Answer_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_Shorted_Relevent_Answer_Is_Display");
	}
	
	@Test
	public static void Verify_Generated_Top_Answer_Appearing_From_Related_Latest_Upload_Document() {
		InitiateTestParams.ExecuteTestCase("Verify_Generated_Top_Answer_Appearing_From_Related_Latest_Upload_Document");
	}
	
	
	@Test
	public static void Verify_That_Channels_Are_Rename_To_Avatars_Every_Where_On_Nesh() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Channels_Are_Rename_To_Avatars_Every_Where_On_Nesh");
	}
	
	
	@Test
	public static void Verify_User_Is_Able_To_Search_Answer_WithOut_Clicking_Outside_Of_The_Searchbox() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Is_Able_To_Search_Answer_WithOut_Clicking_Outside_Of_The_Searchbox");
	}
	
	
	@Test
	public static void Verify_That_Recommanded_Topics_Generated_When_User_Ask_Any_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Recommanded_Topics_Generated_When_User_Ask_Any_Question");
	}
	
	@Test
	public static void Verify_That_Three_Recommanded_Topics_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Three_Recommanded_Topics_Is_Display");
	}
	
	@Test
	public static void Verify_User_Can_Answer_That_Question_Assigned_To_Them() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Answer_That_Question_Assigned_To_Them");
	}
	
	@Test
	public static void Verify_User_Can_See_Only_Expert_Name_When_Question_Assign_To_Another() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_See_Only_Expert_Name_When_Question_Assign_To_Another");
	}
	
	@Test
	public static void Verify_User_Can_Answered_The_Question_And_That_Question_Moved_To_Answered_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Answered_The_Question_And_That_Question_Moved_To_Answered_Tab");
	}
	
	@Test
	public static void Verify_That_Assign_By_And_Assign_To_Field_Updated_When_User_Assign_To_Other() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Assign_By_And_Assign_To_Field_Updated_When_User_Assign_To_Other");
	}
	
	@Test
	public static void Verify_That_Expert_Can_Answer_That_Question_By_Himself() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Can_Answer_That_Question_By_Himself");
	}
	
	@Test
	public static void Verify_That_Question_Is_Assigned_To_Selected_Expert() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Question_Is_Assigned_To_Selected_Expert");
	}
	
	@Test
	public static void Verify_Trend_Data_Is_vissible_Under_Topic_Trend() {
		InitiateTestParams.ExecuteTestCase("Verify_Trend_Data_Is_vissible_Under_Topic_Trend");
	}
	
	@Test
	public static void Verify_Pop_Up_Appears_To_Assign_The_Question_To_Self_Or_An_Expert() {
		InitiateTestParams.ExecuteTestCase("Verify_Pop_Up_Appears_To_Assign_The_Question_To_Self_Or_An_Expert");
	}
	
	@Test
	public static void Verify_That_When_User_Self_Assigned_Any_Question_Then_That_Land_On_Waiting_For_Answer_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_User_Self_Assigned_Any_Question_Then_That_Land_On_Waiting_For_Answer_Tab");
	}
	
	@Test
	public static void Verify_That_Images_And_Tables_Is_Not_Display_At_The_Top() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Images_And_Tables_Is_Not_Display_At_The_Top");
	}
	
	@Test
	public static void Verify_Duplicate_Images_Are_Not_Available() {
		InitiateTestParams.ExecuteTestCase("Verify_Duplicate_Images_Are_Not_Available");
	}
	
	@Test
	public static void Verify_That_Answers_Submitted_By_The_Experts_Coming_On_Top() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Answers_Submitted_By_The_Experts_Coming_On_Top");
	}

	@Test
	public static void Verify_That_Expert_Answers_Are_Comming_With_Expert_Name() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Answers_Are_Comming_With_Expert_Name");
	}
	
	@Test
	public static void Verify_That_Generated_Answers_Are_Related_To_The_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Generated_Answers_Are_Related_To_The_Question");
	}

	@Test
	public static void Verify_That_Expert_Can_Answer_Submit_And_Decline() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Can_Answer_Submit_And_Decline");
	}
	
	@Test
	public static void Verify_That_Question_Is_Appearing_In_The_Search_Bar_When_Research_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Question_Is_Appearing_In_The_Search_Bar_When_Research_Question");
	}
	
	@Test
	public static void Verify_That_When_User_Click_On_Back_Link_He_Still_On_That_Particular_Answer() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_User_Click_On_Back_Link_He_Still_On_That_Particular_Answer");
	}
	
	@Test
	public static void Verify_That_Top_Answer_Is_Display_In_The_Text() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Top_Answer_Is_Display_In_The_Text");
	}
	
	@Test
	public static void Verify_That_Job_Title_And_Business_Unit_Fields_Are_Available_On_Sign_Up_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Job_Title_And_Business_Unit_Fields_Are_Available_On_Sign_Up_Page");
	}
	
	@Test
	public static void Verify_That_Only_One_Image_Answer_Is_Showing_In_The_Top_Answer() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Only_One_Image_Answer_Is_Showing_In_The_Top_Answer");
	}
	
	@Test
	public static void Verify_That_Provided_Expert_Answer_Is_Display_In_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Provided_Expert_Answer_Is_Display_In_The_Search_Result_Page");
	}
	
	@Test
	public static void Verify_That_Answer_Are_Changing_When_Filter_Applied() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Answer_Are_Changing_When_Filter_Applied");
	}
	
	@Test
	public static void Verify_Knowledge_Gap_Percentage_Zero_Showing_When_DownVoted_Question_Has_Been_Answered() {
		InitiateTestParams.ExecuteTestCase("Verify_Knowledge_Gap_Percentage_Zero_Showing_When_DownVoted_Question_Has_Been_Answered");
	}

	@Test
	public static void Verify_HTML_Tags_Are_Not_Display_In_The_Summary() {
		InitiateTestParams.ExecuteTestCase("Verify_HTML_Tags_Are_Not_Display_In_The_Summary");
	}
	

	@Test
	public static void Verify_Audit_Report_Is_Displayed_On_The_Search_Page_When_Select_It() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Report_Is_Displayed_On_The_Search_Page_When_Select_It");
	}
	
	@Test
	public static void Verify_Audit_Option_Is_Off_When_Deselect_It() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Option_Is_Off_When_Deselect_It");
	}
	
	@Test
	public static void Verify_Audit_Report_Is_Displayed_On_The_Topic_Page_When_Select_It() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Report_Is_Displayed_On_The_Topic_Page_When_Select_It");
	}
	
	@Test
	public static void Verify_HTML_Tags_Are_Not_Display_In_The_Topic_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_HTML_Tags_Are_Not_Display_In_The_Topic_Page");
	}
	
	@Test
	public static void Verify_That_Summary_Is_Display_Of_Those_Top_Passages() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Summary_Is_Display_Of_Those_Top_Passages");
	}
	
	@Test
	public static void Verify_That_User_User_Can_Edit_Profile() {
		InitiateTestParams.ExecuteTestCase("Verify_That_User_User_Can_Edit_Profile");
	}
	
	@Test
	public static void Verify_That_Selected_Media_Type_Filter_Related_Answer_Is_Appearing_In_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Selected_Media_Type_Filter_Related_Answer_Is_Appearing_In_The_Search_Result_Page");
	}
	  
	
	@Test
	public static void Verify_HTML_Tags_Are_Not_Display_In_The_Audit_Report_Table_Under_Source_Passage() {
		InitiateTestParams.ExecuteTestCase("Verify_HTML_Tags_Are_Not_Display_In_The_Audit_Report_Table_Under_Source_Passage");
	}
	
	@Test
	public static void Verify_User_Can_Select_The_A_Reason_Why_They_Selected_Down_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Select_The_A_Reason_Why_They_Selected_Down_Thumb");
	}
	
	@Test
	public static void Verify_User_Can_Give_The_A_Reason_Why_They_Selected_Down_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Give_The_A_Reason_Why_They_Selected_Down_Thumb");
	}
	
	@Test
	public static void Verify_That_User_Can_Dismiss_Feedback_Window_If_They_Choose_Not_To_Provide_Feedback_Of_Down_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_That_User_Can_Dismiss_Feedback_Window_If_They_Choose_Not_To_Provide_Feedback_Of_Down_Thumb");
	}
	
	@Test
	public static void Verify_User_Can_Select_The_A_Reason_Why_They_Selected_Up_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Select_The_A_Reason_Why_They_Selected_Up_Thumb");
	}
	
	@Test
	public static void Verify_User_Can_Give_The_A_Reason_Why_They_Selected_Up_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_User_Can_Give_The_A_Reason_Why_They_Selected_Up_Thumb");
	}
	
	@Test
	public static void Verify_That_User_Can_Dismiss_Feedback_Window_If_They_Choose_Not_To_Provide_Feedback_Of_Up_Thumb() {
		InitiateTestParams.ExecuteTestCase("Verify_That_User_Can_Dismiss_Feedback_Window_If_They_Choose_Not_To_Provide_Feedback_Of_Up_Thumb");
	}
	   
	
	@Test
	public static void Verify_Audit_Report_Is_Generating_Under_Avatar_Market_Research_Channel() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Report_Is_Generating_Under_Avatar_Market_Research_Channel");
	}
	
	@Test
	public static void Verify_Audit_Report_Is_Generating_Under_Avatar_Product_Support_Channel() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Report_Is_Generating_Under_Avatar_Product_Support_Channel");
	}
	
	@Test
	public static void Verify_Audit_Report_Is_Generating_Under_Avatar_Regs_And_Policies_Channel() {
		InitiateTestParams.ExecuteTestCase("Verify_Audit_Report_Is_Generating_Under_Avatar_Regs_And_Policies_Channel");
	}
	
	@Test
	public static void Verify_That_Suggestions_Is_Appearing_When_User_Aksed_Queary() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Suggestions_Is_Appearing_When_User_Aksed_Queary");
	}
	
	@Test
	public static void Verify_That_Knowledge_Analytics_Page_Is_Working () {
		InitiateTestParams.ExecuteTestCase("Verify_That_Knowledge_Analytics_Page_Is_Working");
	}
	
	@Test
	public static void Verify_Downvoted_Question_Is_Display_in_Avatar_Channels_Under_Knowledge_Analytics() {
		InitiateTestParams.ExecuteTestCase("Verify_Downvoted_Question_Is_Display_in_Avatar_Channels_Under_Knowledge_Analytics");
	}
	
	@Test
	public static void Verify_That_Media_Type_Grouping_Is_Working () {
		InitiateTestParams.ExecuteTestCase("Verify_That_Media_Type_Grouping_Is_Working");
	}
	
	@Test
	public static void Verify_That_Knowledge_Recall_Percentage_Are_Showing_On_The_Avatars_Channel () {
		InitiateTestParams.ExecuteTestCase("Verify_That_Knowledge_Recall_Percentage_Are_Showing_On_The_Avatars_Channel");
	}
	
	@Test
	public static void Verify_That_Up_Voted_Question_Is_Showing_in_The_Avatar_Channel () {
		InitiateTestParams.ExecuteTestCase("Verify_That_Up_Voted_Question_Is_Showing_in_The_Avatar_Channel");
	}
	
	@Test
	public static void Verify_That_Even_User_Skiped_Reason_Then_Also_Question_is_Up_Or_Down_Voting() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Even_User_Skiped_Reason_Then_Also_Question_is_Up_Or_Down_Voting");
	}
	
	@Test
	public static void Verify_That_Audit_Report_Is_Generating_For_Questions_On_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_Report_Is_Generating_For_Questions_On_The_Search_Result_Page");
	}
	
	@Test
	public static void Verify_That_Audit_Report_Is_Generating_For_Theme_Topic_On_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_Report_Is_Generating_For_Theme_Topic_On_The_Search_Result_Page");
	}
	
	@Test
	public static void Verify_That_Explore_And_Ask_And_Audit_Avatar_Colunm_Is_Display_Under_Audit_Report() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Explore_And_Ask_And_Audit_Avatar_Colunm_Is_Display_Under_Audit_Report");
	}
	
	@Test
	public static void Verify_That_Google_Drive_Account_Added_Successfully() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Google_Drive_Account_Added_Successfully");
	}
	
	@Test
	public static void Verify_That_Help_Page_Contain_Multiple_Fields() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Help_Page_Contain_Multiple_Fields");
	}
	
	@Test
	public static void Verify_That_Registration_Fields_is_Display_in_Sequence() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Registration_Fields_is_Display_in_Sequence");
	}
	
	@Test
	public static void Verify_That_Reset_Button_Is_Not_Present_On_The_Search_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Reset_Button_Is_Not_Present_On_The_Search_Result_Page");
	}
	
	@Test
	public static void Verify_That_Connect_Edgar_Feature_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Connect_Edgar_Feature_Is_Working");
	}
	
	@Test
	public static void Verify_That_When_Click_On_Show_Other_Answer_Button_Then_Its_Not_Affect_For_Other_Tab() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_Click_On_Show_Other_Answer_Button_Then_Its_Not_Affect_For_Other_Tab");
	}
	
	@Test
	public static void Verify_That_When_User_Voted_The_Answer_Then_Vote_Thumb_Is_Not_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_User_Voted_The_Answer_Then_Vote_Thumb_Is_Not_Display");
	}
	
	@Test
	public static void Verify_That_Change_Profile_Icon_Is_Valid() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Change_Profile_Icon_Is_Valid");
	}
	
	@Test
	public static void Verify_That_Help_Icon_Redirects_To_On_Help_Service_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Help_Icon_Redirects_To_On_Help_Service_Page");
	}
	
	@Test
	public static void Verify_That_All_Detail_Are_Showing_On_Expert_Card(){
		InitiateTestParams.ExecuteTestCase("Verify_That_All_Detail_Are_Showing_On_Expert_Card");
	}
	
	@Test
	public static void Verify_That_Meta_Data_Is_Display_Of_Query(){
		InitiateTestParams.ExecuteTestCase("Verify_That_Meta_Data_Is_Display_Of_Query");
	}
	
	@Test
	public static void Verify_That_User_Is_Able_To_Remove_The_File_From_Uploaded_File(){
		InitiateTestParams.ExecuteTestCase("Verify_That_User_Is_Able_To_Remove_The_File_From_Uploaded_File");
	}
	
	@Test
	public static void Verify_That_Generated_Metadata_Result_Are_Expected_And_Correct(){
		InitiateTestParams.ExecuteTestCase("Verify_That_Generated_Metadata_Result_Are_Expected_And_Correct");
	}
	
	@Test
	public static void Verify_That_Generated_Top_Answer_Is_appearing_From_Data_Connector(){
		InitiateTestParams.ExecuteTestCase("Verify_That_Generated_Top_Answer_Is_appearing_From_Data_Connector");
	}
	
	@Test
	public static void Verify_That_File_Is_Showing_in_Folder_Are_Connecting_Section_Goes_To_Folder_Are_Connected_Section(){
		InitiateTestParams.ExecuteTestCase("Verify_That_File_Is_Showing_in_Folder_Are_Connecting_Section_Goes_To_Folder_Are_Connected_Section");
	}
	
	@Test
	public static void Verify_That_Expert_Answer_Is_Showing_On_The_Top_Of_Answer_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Answer_Is_Showing_On_The_Top_Of_Answer_Page");
	}

	@Test
	public static void Verify_That_The_Nesh_User_Is_Able_To_Remove_Files_From_Uploaded_File() {
		InitiateTestParams.ExecuteTestCase("Verify_That_The_Nesh_User_Is_Able_To_Remove_Files_From_Uploaded_File");
	}
	
	@Test
	public static void Verify_That_The_Question_Is_Assigned_To_Selected_Expert() {
		InitiateTestParams.ExecuteTestCase("Verify_That_The_Question_Is_Assigned_To_Selected_Expert");
	}
	
	@Test
	public static void Verify_That_Data_Connectors_Card_Is_Not_Dispay_inside_The_Files_Card() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Data_Connectors_Card_Is_Not_Dispay_inside_The_Files_Card");
	}
	
	@Test
	public static void Verify_That_Post_Qustion_Assigned_To_An_Expert() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Post_Qustion_Assigned_To_An_Expert");
	}
	
	@Test
	public static void Verify_That_Removing_Any_File_Not_Lead_To_User_Logout() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Removing_Any_File_Not_Lead_To_User_Logout");
	}
	
	@Test
	public static void Verify_That_When_User_Select_Avatar_From_Drop_Down_Associated_Section_Which_Related_Files_Are_Showning_In_Associated_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_User_Select_Avatar_From_Drop_Down_Associated_Section_Which_Related_Files_Are_Showning_In_Associated_Section");
	}
	
	@Test
	public static void Verify_That_File_Summary_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_That_File_Summary_Is_Display");
	}
	
	@Test
	public static void Verify_That_Remove_And_Cancel_Button_Is_Not_Available_In_The_Folder() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Remove_And_Cancel_Button_Is_Not_Available_In_The_Folder");
	}
	
	@Test
	public static void Verify_That_Only_One_Reason_Is_Selecting_At_A_Time_Of_Voting_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Only_One_Reason_Is_Selecting_At_A_Time_Of_Voting_Question");
	}
	 
	@Test
	public static void Verify_That_Avatar_Selection_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Avatar_Selection_Is_Working");
	}
	
	@Test
	public static void Verify_That_Cancel_Button_Is_Not_Available_When_Uploading_File() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Cancel_Button_Is_Not_Available_When_Uploading_File");
	}
	
	@Test
	public static void Verify_That_Graph_Data_is_Showing() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Graph_Data_is_Showing");
	}
	
	@Test
	public static void Verify_That_Share_Point_Successfuly_Connected() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Share_Point_Successfuly_Connected");
	}
	
	@Test
	public static void Verify_That_Source_Link_Is_Clickable_And_Page_Is_Not_Reloaded() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Source_Link_Is_Clickable_And_Page_Is_Not_Reloaded");
	}
	
	@Test
	public static void Verify_That_Vertical_Scroll_Is_Present() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Vertical_Scroll_Is_Present");
	}
	
	@Test
	public static void Verify_That_Asked_Question_Is_Appering_In_My_Question_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Asked_Question_Is_Appering_In_My_Question_Section");
	}
	
	@Test
	public static void Verify_That_Password_Criteria_Work_As_Expected() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Password_Criteria_Work_As_Expected");
	}
	
	@Test
	public static void Verify_That_Result_Page_Summary_Contain_Relevant_Passage() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Result_Page_Summary_Contain_Relevant_Passage");
	}
	
	@Test
	public static void Verify_That_Top_Passege_Is_Coming_From_Google_Drive_Data_Connector() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Top_Passege_Is_Coming_From_Google_Drive_Data_Connector");
	}
	
	@Test
	public static void Verify_That_Suggested_Question_Is_Coming_From_Google_Drive_Data_Connector() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Suggested_Question_Is_Coming_From_Google_Drive_Data_Connector");
	}
	
	@Test
	public static void Verify_That_Mail_Contain_Bold_Text() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Mail_Contain_Bold_Text");
	}
		
	@Test
	public static void Verify_That_Avatar_Page_Is_Not_Showing_When_Refresh_The_Avatar_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Avatar_Page_Is_Not_Showing_When_Refresh_The_Avatar_Page");
	}
	
	@Test
	public static void Verify_That_Latest_Update_Menu_Option_Is_Last_Option_Of_Navbar() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Latest_Update_Menu_Option_Is_Last_Option_Of_Navbar");
	}
	
	@Test
	public static void Verify_That_Post_Question_To_An_Expert_Option_Is_Disable_When_Once_Time_Posted_Question() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Post_Question_To_An_Expert_Option_Is_Disable_When_Once_Time_Posted_Question");
	}
	
	@Test
	public static void Verify_That_Scroll_is_Still_On_Same_Topic_Card_Which_was_Clicked() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Scroll_is_Still_On_Same_Topic_Card_Which_was_Clicked");
	}
	
	@Test
	public static void Verify_That_Create_Avatar_Button_Is_Display_At_The_Avatar_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Create_Avatar_Button_Is_Display_At_The_Avatar_Page");
	}
	
	@Test
	public static void Verify_That_Edit_Avatar_Option_Is_Available_In_The_Avatar_Card() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Edit_Avatar_Option_Is_Available_In_The_Avatar_Card");
	}
	
	@Test
	public static void Verify_That_Run_Domain_Adaptation_Button_Is_Disable() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Run_Domain_Adaptation_Button_Is_Disable");
	}
	
	@Test
	public static void Verify_That_Share_Option_Is_Disable() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Share_Option_Is_Disable");
	}
	
	@Test
	public static void Verify_That_Files_Are_Conect_imedently_Which_Size_Is_Lessthan_5_KB() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Files_Are_Conect_imedently_Which_Size_Is_Lessthan_5_KB");
	}
	
	@Test
	public static void Verify_That_When_Audit_And_Decision_Show_Up_That_They_Are_Collapsed_By_Default() {
		InitiateTestParams.ExecuteTestCase("Verify_That_When_Audit_And_Decision_Show_Up_That_They_Are_Collapsed_By_Default");
	}
	
	@Test
	public static void Verify_That_User_Able_To_Expend_Audit_And_Decision_Fields() {
		InitiateTestParams.ExecuteTestCase("Verify_That_User_Able_To_Expend_Audit_And_Decision_Fields");
	}
	
	@Test
	public static void Verify_That_Audit_And_Decision_Fields_Collapsed_By_Default_On_Page_Refresh() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_And_Decision_Fields_Collapsed_By_Default_On_Page_Refresh");
	}
	
	@Test
	public static void Verify_That_Market_Research_Option_Is_Present_On_Both_Avatar_Selection_Page_And_Home_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Market_Research_Option_Is_Present_On_Both_Avatar_Selection_Page_And_Home_Page");
	}
	
	@Test
	public static void Verify_That_MetaData_Returns_Expected_Date() {
		InitiateTestParams.ExecuteTestCase("Verify_That_MetaData_Returns_Expected_Date");
	}
	
	@Test
	public static void Verify_That_Request_Access_Is_Sending_On_Email() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Request_Access_Is_Sending_On_Email");
	}
	
	@Test
	public static void Verify_That_All_Web_Site_Files_Are_Connected_And_Associated_To_An_Avatar() {
		InitiateTestParams.ExecuteTestCase("Verify_That_All_Web_Site_Files_Are_Connected_And_Associated_To_An_Avatar");
	}
	
	@Test
	public static void Verify_That_Audit_Avatar_Option_Is_Not_Available_In_The_Avatar_Picker_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_Avatar_Option_Is_Not_Available_In_The_Avatar_Picker_Page");
	}
	
	@Test
	public static void Verify_That_Audit_Avatar_Option_Is_Not_Available_On_Home_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_Avatar_Option_Is_Not_Available_On_Home_Page");
	}
	
	@Test
	public static void Verify_That_Search_Bar_Option_Is_Not_Available_On_the_Data_Manager_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Search_Bar_Option_Is_Not_Available_On_the_Data_Manager_Page");
	}
	
	@Test
	public static void Verify_That_Generated_Summary_Contain_Key_Extractions_Of_Passages() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Generated_Summary_Contain_Key_Extractions_Of_Passages");
	}
	
	@Test
	public static void Verify_That_Pagination_is_Working_On_WebSite_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Pagination_is_Working_On_WebSite_Page");
	}
	
	@Test
	public static void Verify_That_Search_Option_is_Working_On_WebSite_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Search_Option_is_Working_On_WebSite_Page");
	}
	
	@Test
	public static void Verify_That_Pagination_is_Working_On_File_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Pagination_is_Working_On_File_Page");
	}
	
	@Test
	public static void Verify_That_Search_Option_is_Working_On_UploadFile_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Search_Option_is_Working_On_UploadFile_Page");
	}
	
	@Test
	public static void Verify_That_Small_Medium_Large_Toggle_Button_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Small_Medium_Large_Toggle_Button_Is_Working");
	}
	
	@Test
	public static void Verify_That_User_Is_Able_To_Login_With_SSO_Account() {
		InitiateTestParams.ExecuteTestCase("Verify_That_User_Is_Able_To_Login_With_SSO_Account");
	}
	
	@Test
	public static void Verify_That_Summary_Feedback_Option_Is_Appear() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Summary_Feedback_Option_Is_Appear");
	}
	
	@Test
	public static void Verify_That_Thumb_Up_and_Down_Feedback_option_Removed_From_Search_Result() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Thumb_Up_and_Down_Feedback_option_Removed_From_Search_Result");
	}
	
	@Test
	public static void Verify_That_Post_An_Expert_Button_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Post_An_Expert_Button_Is_Working");
	}
	
	@Test
	public static void Verify_That_Expert_Answer_Is_Showing_On_The_Search_result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Answer_Is_Showing_On_The_Search_result_Page");
	}
	
	@Test
	public static void Verify_That_Files_Are_Uploading() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Files_Are_Uploading");
	}
	
	@Test
	public static void Verify_That_Filter_Are_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Filter_Are_Working");
	}
	
	@Test
	public static void Verify_That_Old_Filter_Feature_Is_Available() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Old_Filter_Feature_Is_Available");
	}
	
	@Test
	public static void Verify_That_Answer_Will_Be_Changed_After_Apply_The_Filter() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Answer_Will_Be_Changed_After_Apply_The_Filter");
	}
	
	@Test
	public static void Verify_That_Voting_Icon_Is_Not_Display_Once_User_Submits_Their_Feedback_For_Summary() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Voting_Icon_Is_Not_Display_Once_User_Submits_Their_Feedback_For_Summary");
	}
	
	@Test
	public static void Verify_That_Client_Filter_Are_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Client_Filter_Are_Working");
	}
	
	@Test
	public static void Verify_That_Text_Bubble_Is_Not_Showing_Too_Bright() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Text_Bubble_Is_Not_Showing_Too_Bright");
	}
	
	@Test
	public static void Verify_That_Decision_Option_Is_Available_On_Result_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Decision_Option_Is_Available_On_Result_Page");
	}  
	
	@Test
	public static void Verify_That_Previous_Button_Is_Working_Of_Pagination() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Previous_Button_Is_Working_Of_Pagination");
	} 
	
	@Test
	public static void Verify_That_Upload_File_Button_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Upload_File_Button_Is_Working");
	} 
	
	@Test
	public static void Verify_That_Avatar_Name_Is_Display_In_Camel_Case_Word() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Avatar_Name_Is_Display_In_Camel_Case_Word");
	} 
	
	@Test
	public static void Verify_That_Post_To_Expert_Button_Is_Clickable() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Post_To_Expert_Button_Is_Clickable");
	} 
	
	@Test
	public static void Verify_That_Clicking_Between_Questions_Do_Not_Close_The_Search_Bar() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Clicking_Between_Questions_Do_Not_Close_The_Search_Bar");
	} 
	
	@Test
	public static void Verify_That_Avatar_Selection_PopUp_Show_Every_Time_The_User_Login() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Avatar_Selection_PopUp_Show_Every_Time_The_User_Login");
	} 
	
	@Test
	public static void Verify_That_Upvoted_Answer_Is_Showing_At_The_Top() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Upvoted_Answer_Is_Showing_At_The_Top");
	} 
	
	@Test
	public static void Verify_That_Enter_Button_Is_Working_With_Chinook_Avatar() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Enter_Button_Is_Working_With_Chinook_Avatar");
	} 

	@Test
	public static void Verify_That_Card_And_Tree_View_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Card_And_Tree_View_Is_Display");
	} 
	
	@Test
	public static void Verify_That_Nesh_Bubble_Text_Show_Different_For_Normal_Question_And_Low_Confidence_Qustion() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Nesh_Bubble_Text_Show_Different_For_Normal_Question_And_Low_Confidence_Qustion");
	} 
	
	@Test
	public static void Verify_That_Open_Extras_Panel_Is_Display_And_There_Are_Multiple_Option_In_The_Panel() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Open_Extras_Panel_Is_Display_And_There_Are_Multiple_Option_In_The_Panel");
	} 
	
	@Test
	public static void Verify_That_Open_Extras_Panel_Is_Display() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Open_Extras_Panel_Is_Display");
	} 
	
	@Test
	public static void Verify_That_Graph_View_Is_Display_On_The_Home_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Graph_View_Is_Display_On_The_Home_Page");
	} 
	
	@Test
	public static void Verify_That_Expert_Page_Have_Experts_That_Have_Trained_Nesh_Heading() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Page_Have_Experts_That_Have_Trained_Nesh_Heading");
	} 
	
	@Test
	public static void Verify_That_The_User_Can_Select_Avatar_On_Expert_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_The_User_Can_Select_Avatar_On_Expert_Page");
	} 
	
	@Test
	public static void Verify_That_The_Top_Three_Experts_Are_Shown_More_Prominently() {
		InitiateTestParams.ExecuteTestCase("Verify_That_The_Top_Three_Experts_Are_Shown_More_Prominently");
	} 
	
	@Test
	public static void Verify_That_Uploaded_File_Can_Be_Associated() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Uploaded_File_Can_Be_Associated");
	}
	
	@Test
	public static void Verify_That_Blank_Cell_Not_Available_In_The_Popular_Questions_And_My_Questions_Section() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Blank_Cell_Not_Available_In_The_Popular_Questions_And_My_Questions_Section");
	}
	
	@Test
	public static void Verify_That_File_Created_Date_Filter_Option_Is_Working() {
		InitiateTestParams.ExecuteTestCase("Verify_That_File_Created_Date_Filter_Option_Is_Working");
	}
	
	@Test
	public static void Verify_That_Tree_View_Is_Display_On_The_Home_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Tree_View_Is_Display_On_The_Home_Page");
	}
	
	@Test
	public static void Verify_That_Clicking_On_The_Empty_Space_Above_The_Home_Button_Does_Not_Lead_To_The_Home_Page() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Clicking_On_The_Empty_Space_Above_The_Home_Button_Does_Not_Lead_To_The_Home_Page");
	}
	
	@Test
	public static void Verify_That_Asked_Question_Is_Not_Available_On_The_Search_Bar_When_Recommended_Topic_Is_Clicked() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Asked_Question_Is_Not_Available_On_The_Search_Bar_When_Recommended_Topic_Is_Clicked");
	}
	
	@Test
	public static void Verify_That_There_Is_Space_Available_Between_Password_Field_and_Forgot_Password_Option() {
		InitiateTestParams.ExecuteTestCase("Verify_That_There_Is_Space_Available_Between_Password_Field_and_Forgot_Password_Option");
	}
	
	@Test
	public static void Verify_That_Audit_Report_Option_Is_Available_on_The_Open_Extras_Panel() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Audit_Report_Option_Is_Available_on_The_Open_Extras_Panel");
	}
	
	@Test
	public static void Verify_That_Files_Can_Be_Associate_With_Avatar() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Files_Can_Be_Associate_With_Avatar");
	}
	
	@Test
	public static void Verify_That_Expert_Cards_Design_Are_Same() {
		InitiateTestParams.ExecuteTestCase("Verify_That_Expert_Cards_Design_Are_Same");
	}
	
	
	 
}  



