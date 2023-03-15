package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class AvatarPage extends PageBase {
	private WebDriver driver;
	public AvatarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	
	@FindBy(xpath = "(//*[@class='Button-root_root__2hlPn Button-variant_contained__3zoUH Button-color_primary__H_f8o Button-size_large__3JqWL'])[1]")
	@CacheLookup
	public  WebElement createAvatarButton;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-dots__3N8lC EntityCard_menuTrigger__qIcvH Icon_clickable__2wcpS'])[1]")
	@CacheLookup
	public  WebElement threeDot;
	
	@FindBy(xpath = "(//*[@class='Tooltip_tip__1AmT_']//following::ul/*)[2]")
	@CacheLookup
	public  WebElement editOption;
	
	@FindBy(xpath = "(//*[@class='Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx'])[2]")
	@CacheLookup
	public  WebElement runDomainAdaptationButton;
	
	
	
	
	



}
