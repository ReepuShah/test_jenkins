package com.framework.pages;

import static com.framework.settings.ObjectRepo.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ThemeLocatorPage extends PageBase {
	

	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public ThemeLocatorPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/** Web Elements */

	@FindBy(xpath = "(//*[@class='AvatarCardItem_root__3uQgq'])[1]")
	@CacheLookup
	public  WebElement exploreAndAskCard;
	
	@FindBy(xpath = "//*[text()='Next']")
	@CacheLookup
	public  WebElement nextButton;
	
	@FindBy(xpath = "//*[@class='TextInput_input__fCq2t TextInput_withBefore__m3zLc TextInput_withAfter__iLW91']")
	@CacheLookup
	public  WebElement searchBar;
	
	@FindBy(xpath = "//*[text()='Suggested questions for you']")
	@CacheLookup
	public  WebElement suggestionsField;
	
	@FindBy(xpath = "//*[text()='Popular questions']")
	@CacheLookup
	public  WebElement popularquestionsField;
	
	@FindBy(xpath = "//*[text()='My questions']")
	@CacheLookup
	public  WebElement myQuestionsField;
	
	@FindBy(xpath = "//*[text()='Person']")
	@CacheLookup
	public  WebElement personTab;
	
	@FindBy(xpath = "//*[text()='Person']")
	@CacheLookup
	public  WebElement themeTab;
	
	@FindBy(xpath = "(//*[@class='ImageCard_root__2FvFc ImageCard_theme-secondary__tzV1p ImageCard_clickable__2McHq'])[1]")
	@CacheLookup
	public  WebElement topicCard;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']/*/h4")
	@CacheLookup
	public  List<WebElement> topPassages;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_root__1Vz5V SuggestionsSidebar_listItem__1Hxzz QuestionSuggestionItem_clickable__1iOM6']")
	@CacheLookup
	public  List<WebElement> suggestionQuestion;
	
	@FindBy(xpath = "//*[@class='ImageCard_title__3xtrK']//parent::*/*/*[@class='Chip_title__2c-BL']")
	@CacheLookup
	public  List<WebElement> NERTag;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK']//h3)[1]")
	@CacheLookup
	public WebElement topicName;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK']//h3)")
	@CacheLookup
	public List<WebElement> allTopic;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__LrpL+']//*//h4")
	@CacheLookup
	public List<WebElement> changedPassages;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK']//h3)[1]")
	@CacheLookup
	public WebElement updatedtopicName;
	
	@FindBy(xpath = "//*[text()='Topic Trend']")
	@CacheLookup
	public  WebElement topicTrend;
	
	@FindBy(xpath = "//*[text()='Top Passages']")
	@CacheLookup
	public  WebElement topPassage;
	
	@FindBy(xpath = "//*[@class='TabsCollection_itemContainer__s9-3I']")
	@CacheLookup
	public  List<WebElement> themes;
	
	@FindBy(xpath = "//*[@class='Radio_root__2dmgP Radio_size-medium__1m2Bh AvatarCardItem_radioButton__3VBjx']")
	@CacheLookup
	public  WebElement radioButton;
	
	@FindBy(xpath = "(//*[@class='ImageCard_root__2FvFc ImageCard_theme-primary__2ADmd'])[1]")
	@CacheLookup
	public WebElement topicCards;
	
	@FindBy(xpath = "//*[@class='Chip_root__39ywK Chip_variant-rounded__wlMyu Chip_color-blue-solid__3whSc']//*[@class='Chip_title__2c-BL']")
	@CacheLookup
	public  List<WebElement> themeName;
	
	@FindBy(xpath = "//*[@class='Chip_root__6GLp8 Chip_variant-rounded__mM55D Chip_color-blue-solid__X8uG8']//*[@class='Chip_title__5+pg8']")
	@CacheLookup
	public  List<WebElement> allThemes;
	
	@FindBy(xpath = "(//*[text()='Environmental'])[1]")
	@CacheLookup
	public WebElement environmentalTopicTab;
	
	@FindBy(xpath = "(//*[@class='ImageCard_root__-qoiM ImageCard_theme-secondary__h96BK ImageCard_clickable__8iY9V'])[1]")
	@CacheLookup
	public WebElement environmentalTopic;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__LrpL+']//*//h4")
	@CacheLookup
	public List<WebElement> passageName;
	
	@FindBy(xpath = "(//*[@class='ResultsCard_answerContainer__15nKY']//*[@class='AnswerSection_title__2A-DN'])[1]")
	@CacheLookup
	public WebElement updatedpassageName;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK'])[1]")
	@CacheLookup
	public  WebElement TopicName;

	@FindBy(xpath = "(//*[text()='Product Support']//parent::*[@class='AvatarCardItem_root__3uQgq'])[1]")
	@CacheLookup
	public  WebElement productSupport;
	
	@FindBy(xpath = "//*[text()='Environmental']")
	@CacheLookup
	public  WebElement environmentTab;
	
	@FindBy(xpath = "(//*[@class='QuestionSuggestionItem_text__1etiB'])[1]")
	@CacheLookup
	public  WebElement suggestedQuestion;
	
	@FindBy(xpath = "//*[@class='TrendingChart_root__3kIsZ']")
	@CacheLookup
	public  WebElement trendingDataChart;
	
	@FindBy(xpath = "(//*[@class='AvatarCardItem_root__3uQgq'])[4]")
	@CacheLookup
	public  WebElement auditCard;
	
	@FindBy(xpath = "(//*[@class='Table_column__Hn3m5'])[4]")
	@CacheLookup
	public  WebElement sourcePassage;
	
	@FindBy(xpath = "//*[@class='TrendingChart_root__3kIsZ']")
	@CacheLookup
	public  WebElement trendingChart;
	
	@FindBy(xpath = "//*[@class='Title_root__33NXB']")
	@CacheLookup
	public  WebElement connectedFile;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt'])[1]")
	@CacheLookup
	public WebElement backIcon;
	
	

}
