package com.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.framework.generic.GenericHelper;

public class QuestionPage extends PageBase {

	
	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public QuestionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */
	@FindBy(xpath = "//*[@class='QuestionItem_body__HRbuY']/div")
	@CacheLookup
	public List<WebElement>  waitingForAnswerQuestion;
	
	@FindBy(xpath = "//*[text()='Decline']")
	@CacheLookup
	public WebElement declineButton;
	
	@FindBy(xpath = "//*[text()='Submit']")
	@CacheLookup
	public WebElement submitButton;
	
	@FindBy(xpath = "//*[@class='QuestionItem_chips__MyT6L']//following-sibling::div")
	@CacheLookup
	public List<WebElement> waitingForAnswerQuestionList ;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_root__1Vz5V SuggestedQuestions_item__1ZwBC QuestionSuggestionItem_clickable__1iOM6 QuestionSuggestionItem_selected__1TMXO']")
	@CacheLookup
	public WebElement unassignedQuestion;
	
	@FindBy(xpath = "//*[@class='Editor_textArea__13FSe']")
	@CacheLookup
	public WebElement answerFields;
	
	@FindBy(xpath = "//*[@class='notranslate public-DraftEditor-content']")
	@CacheLookup
	public WebElement answerTextField;
	
	
	
	
	

	
	
	
	
		
	
	
	
}
