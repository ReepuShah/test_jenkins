package com.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.framework.generic.GenericHelper;

public class ProfilePage extends PageBase {

	
	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public ProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */
	
	@FindBy(xpath = "(//*[@type='button'])[1]")
	@CacheLookup
	public WebElement profileButton;
	
	@FindBy(xpath = "//*[text()='Settings']")
	@CacheLookup
	public WebElement settingsButton;
	
	@FindBy(xpath = "//*[text()='Log out']")
	@CacheLookup
	public WebElement logOutButton;
	
	@FindBy(xpath = "//*[text()='Log Out']")
	@CacheLookup
	public WebElement logOutBtn;
	
	@FindBy(xpath = "//*[@class='Profile_footer__Utgzq']/*")
	@CacheLookup
	public WebElement editButton;
	
	@FindBy(xpath = "//*[@name='firstName']")
	@CacheLookup
	public WebElement firstNameField;
	
	@FindBy(xpath = "//*[@name='lastName']")
	@CacheLookup
	public WebElement lastNameField;
	
	@FindBy(xpath = "//*[@name='jobTitle']")
	@CacheLookup
	public WebElement jobTitleField;
	
	@FindBy(xpath = "//*[@name='businessUnit']")
	@CacheLookup
	public WebElement businessUniteField;
	
	@FindBy(xpath = "//*[text()='Save ']")
	@CacheLookup
	public WebElement saveButton;
	
	@FindBy(xpath = "//*[@class='TextInput_root__ovhL-']/*")
	@CacheLookup
	public List<WebElement> profile;
	
	@FindBy(xpath = "//*[@class='Chip_title__2c-BL']")
	@CacheLookup
	public List<WebElement> avatar;
	
	
	
	
	
	

	
	
	
	
		
	
	
	
}
