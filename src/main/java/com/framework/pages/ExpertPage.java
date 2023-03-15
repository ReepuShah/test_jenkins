package com.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.framework.generic.GenericHelper;

public class ExpertPage extends PageBase {

	
	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public ExpertPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */
	
	@FindBy(xpath = "(//*[text()='Market Research'])[1]")
	@CacheLookup
	public WebElement  marketResearchAvatar;
	
	@FindBy(xpath = "(//*[text()='Product Support'])[1]")
	@CacheLookup
	public WebElement  productSupportAvatar;
	
	@FindBy(xpath = "(//*[text()='Regs and Policies'])[1]")
	@CacheLookup
	public WebElement RegsandPoliciesAvatar;
	
	@FindBy(xpath = "//*[text()='Experts that have trained Nesh']")
	@CacheLookup
	public WebElement expertHeading;
	
	@FindBy(xpath = "//*[@class='Icon_icon__DouW- Icon_icon-arrow-down__h9YX7 SelectDropDown_arrow__ZGTGY']")
	@CacheLookup
	public WebElement avatarDropDwonIcon;
	
	@FindBy(xpath = "//*[@class='AvatarPicker_text__9FJxf']")
	@CacheLookup
	public WebElement avatarPicker;
	
	@FindBy(xpath = "//*[@class='AvatarPicker_item__C70Ga AvatarPicker_listItem__hMeJF']")
	@CacheLookup
	public List<WebElement> avatarUnselected;
	
	@FindBy(xpath = "//*[@class='RatingFrame_medal__rj3rK']//parent::*")
	@CacheLookup
	public List<WebElement> topThreeExpert;
	
	@FindBy(xpath = "//*[@class='ScoreBar_root__SdVJ2']")
	@CacheLookup
	public List<WebElement> score;
	
	@FindBy(xpath = "(//*[@class='ExpertsPage_body__R68N3']/*)[1]")
	@CacheLookup
	public WebElement totalResult;
	
	@FindBy(xpath = "//*[@class='EntityCard_title__SHMub']")
	@CacheLookup
	public List<WebElement> expertName;
	
	@FindBy(xpath = "//*[@class='ScoreBar_root__SdVJ2']")
	@CacheLookup
	public List<WebElement> scoreBar;
	
	@FindBy(xpath = "//*[text()='Business unit']")
	@CacheLookup
	public List<WebElement> businessUnit;
	
	@FindBy(xpath = "//*[text()='Job title']")
	@CacheLookup
	public List<WebElement> jobTitle;
	
	@FindBy(xpath = "//*[@class='EntityCard_list__WQFMJ']")
	@CacheLookup
	public List<WebElement> avatar;
	
	
	
	

	
	
	
	
		
	
	
	
}
