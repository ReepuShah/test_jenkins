package com.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class HubbellLoginPage extends PageBase {

	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public HubbellLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */

	@FindBy(name = "email")
	@CacheLookup
	public  WebElement txtUserName;

	
	@FindBy(name = "password")
	@CacheLookup
	public  WebElement txtPassword;
	
	@FindBy(xpath = "//*[@type='submit']")
	@CacheLookup
	public  WebElement btnLogin;
	
	@FindBy(xpath = "//*[text()='Sign Up']")
	@CacheLookup
	public  WebElement btnSignUp;
	
	@FindBy(xpath = "//*[text()='Forgot password?']")
	@CacheLookup
	public  WebElement linkForgetPassowrd;
	
	@FindBy(xpath = "//*[text()='Send ']")
	@CacheLookup
	public  WebElement btnSend;
	
	@FindBy(xpath = "((//*[@id='root']/*/*/*)[1]/*)[2]")
	@CacheLookup
	public  WebElement passwordForgetMessage;
	
	@FindBy(xpath = "(//*[@type='button'])[2]")
	@CacheLookup
	public  WebElement errorMessage;
	
	@FindBy(name = "inviteCode")
	@CacheLookup
	public  WebElement txtInviteCode;
	
	@FindBy(name = "firstName")
	@CacheLookup
	public  WebElement txtFirstName;
	
	@FindBy(name = "lastName")
	@CacheLookup
	public  WebElement txtLastName;
	
	@FindBy(name = "email")
	@CacheLookup
	public  WebElement txtEmail;
	
	@FindBy(xpath = "//*[@type='submit']")
	@CacheLookup
	public  WebElement SignUpButton;
	
	@FindBy(xpath = "(//*[@type='button'])[2]")
	@CacheLookup
	public  WebElement inviteCodeErrorMessage;
	
	@FindBy(xpath = "(//*[@type='button'])[3]")
	@CacheLookup
	public  WebElement firstNameErrorMessage;
	
	@FindBy(xpath = "(//*[@type='button'])[4]")
	@CacheLookup
	public  WebElement lastNameErrorMessage;
	
	@FindBy(xpath = "(//*[@type='button'])[2]")
	@CacheLookup
	public  WebElement emailErrorMessage;
	
	@FindBy(xpath = "(//*[@type='button'])[2]")
	@CacheLookup
	public  WebElement passwordErrorMessage;
	
	@FindBy(xpath = "//*[@class='BaseModal_root__xOf-9 SelectAvatarModal_root__3OgtB']")
	@CacheLookup
	public  WebElement avatarSelectOption;
	
	@FindBy(xpath = "(//*[text()='Market Research'])[1]")
	@CacheLookup
	public  WebElement marketResearchCard;
	
	@FindBy(xpath = "(//*[text()='Product Support'])[1]")
	@CacheLookup
	public  WebElement productSupportCard;
	
	@FindBy(xpath = "(//*[text()='Regs and Policies'])[1]")
	@CacheLookup
	public  WebElement regsAndPoliciesCard;
	
	@FindBy(xpath = "(//*[@class='SelectAvatarModal_header__1sBDo']/*)[2]")
	@CacheLookup
	public  WebElement avatarHeader;
	
	@FindBy(xpath = "//*[@name='jobTitle']")
	@CacheLookup
	public  WebElement txtjobTitle;
	
	@FindBy(xpath = "//*[@name='businessUnit']")
	@CacheLookup
	public  WebElement txtbusinessUnit;
	
	@FindBy(xpath = "(//*[@class='Tooltip_body__MTizS Tooltip_alignment-center__H9KAT Tooltip_color-primary__2W1-_ ErrorChip_errorMessage__2whPH']/*)[2]")
	@CacheLookup
	public  WebElement passwordErrorType;
	
	@FindBy(xpath = "//*[text()='Request access']//following-sibling::*")
	@CacheLookup
	public  WebElement requestMessage;
	
	@FindBy(xpath = "//*[@class='AvatarCardList_title__2xb5G']")
	@CacheLookup
	public WebElement auditAvatarOption;
	
	@FindBy(xpath = "(//*[@class='AnswerSection_highlight__31LK- '])[1]")
	@CacheLookup
	public WebElement highlightedSentance;
	
	@FindBy(xpath = "//*[@class='Button-root_root__2hlPn Button-variant_outlined__1l0WC Button-color_default__1lAQQ Button-size_large__3JqWL']")
	@CacheLookup
	public WebElement SSOButton;
	
	@FindBy(xpath = "//*[@type='email']")
	@CacheLookup
	public WebElement emailText;
	
	@FindBy(xpath = "//*[@id='idSIButton9']")
	@CacheLookup
	public WebElement nextButton;
	
	@FindBy(xpath = "//*[@id='i0118']")
	@CacheLookup
	public WebElement passwordText;
	
	@FindBy(xpath = "//*[@id='idSIButton9']")
	@CacheLookup
	public WebElement signButton;
	
	@FindBy(xpath = "//*[@id='idBtn_Back']")
	@CacheLookup
	public WebElement nobtn;
	
	@FindBy(xpath = "//*[@class='SignInForm_after__GPmyF']")
	@CacheLookup
	public WebElement forgotOption;
}
