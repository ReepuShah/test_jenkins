package com.framework.pages;

import static com.framework.settings.ObjectRepo.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DataManagerLocatorPage extends PageBase {
	

	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public DataManagerLocatorPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	/** Web Elements */

	@FindBy(xpath = "//*[@href='/upload']")
	@CacheLookup
	public  WebElement dataManagerIcon;
	
	@FindBy(xpath = "//*[text()='Data Connectors']")
	@CacheLookup
	public WebElement dataConnectorsOption;
	
	@FindBy(xpath = "//*[@class='SourceTypeLink_root__3C0AI']/h4")
	@CacheLookup
	public List<WebElement> connectorsOption;
	
	@FindBy(xpath = "//*[@href='/upload/data-connector/google-drive']")
	@CacheLookup
	public WebElement googleDriveDataConnector;
	
	@FindBy(xpath = "//*[text()='CONNECT google-drive account']")
	@CacheLookup
	public WebElement ConnectGoogleDriveAccountButton;
	
	@FindBy(xpath = "//*[@id='client_id']")
	@CacheLookup
	public WebElement clientId;
	
	@FindBy(xpath = "//*[@id='client_secret']")
	@CacheLookup
	public WebElement clientSecret;
	
	@FindBy(xpath = "//*[text()='Connect ']")
	@CacheLookup
	public WebElement connectButton;
	
	@FindBy(xpath = "(//*[@class='Title_root__33NXB'])[2]")
	@CacheLookup
	public WebElement uploadedFileWithOutAssociatedToAvatar;
	
	@FindBy(xpath = "(//*[@class='Title_root__33NXB'])[1]")
	@CacheLookup
	public WebElement uploadedFileWithAssociatedToAvatar;
	
	@FindBy(xpath = "((//*[@class='Sources_container__UdBAx'])[2]//*[@class='Icon_icon__2Atss Icon_icon-dots__3N8lC DocumentUploadCard_menuTrigger__eHwlS Icon_clickable__2wcpS'])[1]")
	@CacheLookup
	public WebElement uploadedFileWithOutAssociatedToAvatarThreeDotIcon;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-dots__3N8lC ContextMenu_menuTrigger__1qRwb Icon_clickable__2wcpS'])[1]")
	@CacheLookup
	public WebElement uploadedFileWithAssociatedToAvatarThreeDotIcon;
	
	@FindBy(xpath = "((//*[@class='Sources_container__UdBAx'])[3]//*[@class='Icon_icon__2Atss Icon_icon-dots__3N8lC DocumentUploadCard_menuTrigger__eHwlS Icon_clickable__2wcpS'])[1]")
	@CacheLookup
	public WebElement uploadedFileWithAssociatedToAvatarThreeDotIconProd;
	
	@FindBy(xpath = "//*[text()='Remove']")
	@CacheLookup
	public WebElement fileRemoveButton;
	
	@FindBy(xpath = "//*[@class='TriggerButton_root__3y7cc Sources_controlButton__OSJdY Sources_dropdown__2K33Y']")
	@CacheLookup
	public WebElement associatedDropDownIcon;
	
	@FindBy(xpath = "//*[@class='TriggerButton_label__Ubo8C']")
	@CacheLookup
	public WebElement associatedAvatar;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__DouW- Icon_icon-dots__j3MZJ ContextMenu_menuTrigger__duwXn Icon_clickable__zx4Rp'])[1]")
	@CacheLookup
	public WebElement threeDotIcon;
	
	@FindBy(xpath = "//*[text()='Folders']")
	@CacheLookup
	public WebElement folderButton;
	
	@FindBy(xpath = "//*[@class='Button_root__1hsq2 Button_variant-slim__1RLk0 Button_color-default__PCuwD ContextMenu_menuItem__1qQX3']")
	@CacheLookup
	public List<WebElement> options;
	
	@FindBy(xpath = "(((//*[text()='Sharepoint'])[1]//parent::*)[1]//parent::*)[1]")
	@CacheLookup
	public WebElement sharePointDataConnector;
	
	@FindBy(xpath = "(//*[text()=' are connected and associated to Avatar']//parent::*)[1]")
	@CacheLookup
	public WebElement associatedWebsite;
	
	@FindBy(xpath = "(//*[text()='Search Bar')[1]")
	@CacheLookup
	public List<WebElement> searchBar;
	
	@FindBy(xpath = "//*[@class='BaseDataSourceCard_container__3AFiS']")
	@CacheLookup
	public List<WebElement>website;
	
	@FindBy(xpath = "(//*[@class='PaginatedGrid_root__JmR+x'])//*[@class='next']//preceding-sibling::*")
	@CacheLookup
	public List<WebElement> lastPage;
	
	@FindBy(xpath = "//*[@class='BaseDataSourceCard_title__30rL1']")
	@CacheLookup
	public List<WebElement> conectedFileName;
	
	@FindBy(xpath = "//*[@class='TextInput_input__pKOrF TextInput_withBefore__3sbEV']")
	@CacheLookup
	public WebElement searchField;
	
	@FindBy(xpath = "(//*[@class='Sources_container__UdBAx'])[2]//*[@class='BaseDataSourceCard_container__3AFiS']//h4")
	@CacheLookup
	public WebElement upoadedFileName;
	
	@FindBy(xpath = "//*[@class='Icon_icon__DouW- Icon_icon-cross__U2A4S']")
	@CacheLookup
	public WebElement closeIcon;
	
	@FindBy(xpath = "(//*[@class='ProgressBar_value__PX1f4'])[1]")
	@CacheLookup
	public WebElement uploadingproccess;
	
	@FindBy(xpath = "(//*[@rel='prev'])")
	@CacheLookup
	public WebElement prevBtn;
	
	@FindBy(xpath = "(//*[@class='Title_root__eU152'])[3]//*")
	@CacheLookup
	public WebElement associatedUploadFileSection;
	
	@FindBy(xpath = "(//*[@class='Title_root__eU152'])[3]")
	@CacheLookup
	public WebElement associatedFolderSection;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
