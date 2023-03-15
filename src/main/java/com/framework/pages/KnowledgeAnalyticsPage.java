package com.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.framework.generic.GenericHelper;

public class KnowledgeAnalyticsPage extends PageBase {

	
	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public KnowledgeAnalyticsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */

	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-stats-random__14wOJ']")
	@CacheLookup
	public WebElement  KnowledgeAnalyticsIcon;
	
	@FindBy(xpath = "//*[text()='Knowledge Analytics']")
	@CacheLookup
	public WebElement  knowledgeAnalyticsTitle;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_text__1etiB']")
	@CacheLookup
	public List<WebElement>  downvotedQuestion;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_root__1Vz5V SuggestedQuestions_item__1ZwBC QuestionSuggestionItem_clickable__1iOM6 QuestionSuggestionItem_selected__1TMXO']")
	@CacheLookup
	public WebElement  unassignedQuestion;
	
	@FindBy(xpath = "//*[@class='Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx']")
	@CacheLookup
	public WebElement answerQuestionButton;
	
	@FindBy(xpath = "//*[text()='Assign']")
	@CacheLookup
	public WebElement assignButton;
	
	@FindBy(xpath = "//*[@class='Editor_textArea__13FSe']")
	@CacheLookup
	public WebElement answerFields;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_color-secondary__1ATBQ']")
	@CacheLookup
	public WebElement groupByDropdowIcon;
	
	@FindBy(xpath = "//*[@class='QuestionCard_body__2XIWn']")
	@CacheLookup
	public WebElement assigneToOtherExpertSection;
	
	@FindBy(xpath = "//*[text()='No questions found']")
	@CacheLookup
	public List<WebElement> noQuestionFoundMgs;
	
	@FindBy(xpath = "//*[text()='Assign another expert']")
	@CacheLookup
	public WebElement AssignToAnotherExpertButton;
	
	@FindBy(xpath = "(//*[@class='QuestionItem_body__HRbuY'])[1]")
	@CacheLookup
	public WebElement downVotedQuestion;
	
	@FindBy(xpath = "((//*[@class='UserCard_body__MqeBn']))[2]")
	@CacheLookup
	public WebElement usersAssign;
	
	@FindBy(xpath = "(//*[@class='QuestionCard_title__2c1uI'])[1]")
	@CacheLookup
	public WebElement assignBy;
	
	@FindBy(xpath = "(//*[@class='QuestionCard_title__2c1uI'])[2]")
	@CacheLookup
	public WebElement assignTo;
	
	@FindBy(xpath = "(//*[@class='UserCard_body__265-8'])[1]")
	@CacheLookup
	public WebElement selfAssign;
	
	@FindBy(xpath = "//*[@class='BaseModal_root__xOf-9 AssignExpertToQuestionModal_root__Jqp4u']")
	@CacheLookup
	public WebElement AssignExpertPopUp;
	
	@FindBy(xpath = "//*[@class='ScrollableContainer_list__uXtsr']/*")
	@CacheLookup
	public  List<WebElement> downvotedQuestions;
	
	@FindBy(xpath = "//*[text()='Waiting for Answer']")
	@CacheLookup
	public WebElement switchToggle;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_root__1Vz5V SuggestedQuestions_item__1ZwBC QuestionSuggestionItem_clickable__1iOM6']")
	@CacheLookup
	public  List<WebElement> unassignedQuestions;
	
	@FindBy(xpath = "//*[@class='Chip_title__2c-BL']")
	@CacheLookup
	public WebElement knowledgeGapPersentage;
	
	@FindBy(xpath = "//*[@class='Chip_title__2c-BL']")
	@CacheLookup
	public WebElement knowledgeRecallPersentage;
	
	@FindBy(xpath = "//*[@class='EntityCard_title__274e-']")
	@CacheLookup
	public  List<WebElement> channels;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt']")
	@CacheLookup
	public WebElement backIcon;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_text__1etiB']")
	@CacheLookup
	public  List<WebElement> upVotedQuestion;
	
	@FindBy(xpath = "//*[text()='Skip']")
	@CacheLookup
	public WebElement skipButton;
	
	@FindBy(xpath = "(//*[@class='ListItem_item__W7-Sa ListItem_clickable__2xqMz'])[1]")
	@CacheLookup
	public WebElement SelfAssign;
	
	@FindBy(xpath = "//*[@class='KnowledgeAvatar_switchItem__3Timm']/*")
	@CacheLookup
	public WebElement downvotedQustion;
	
	
	
	
	

	
	
	
	
		
	
	
	
}
