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

public class HubbellHomePage extends PageBase {

	private WebDriver driver;
	//WaitHelper wHelper = new WaitHelper(ObjectRepo.driver, null);
	

	public HubbellHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	/** Web Elements */

	@FindBy(name = "searchText")
	@CacheLookup
	public  WebElement txtSearch;
	
	
	@FindBy(name = "password")
	@CacheLookup
	public  WebElement txtPassword;
	
	@FindBy(xpath = "//*[@class='topResponse container toggle']")
	@CacheLookup
	public  WebElement topResponse;
	
	@FindBy(xpath = "//*[@value='allTab']")
	@CacheLookup
	public  WebElement tabAll;
	
	@FindBy(xpath = "//*[text()='NESH']")
	@CacheLookup
	public  WebElement iconNesh;
	
	@FindBy(xpath = "(//*[@href='/home'])[2]")
	@CacheLookup
	public  WebElement homeIcon;
	
	@FindBy(xpath = "//*[@class='Tooltip_wrapper__oyn_V Tooltip_position-right__1OIGt']")
	@CacheLookup
	public  WebElement homeLinkIcon;
	
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-upload-cloud__3_fZK']")
	@CacheLookup
	public  WebElement dataManagerIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-loader__25qn6']")
	@CacheLookup
	public  WebElement latestUpdateIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-question-mark__1A-kN Sidebar_helpButton__3K7Xw']")
	@CacheLookup
	public  WebElement helpIcon;
	
	@FindBy(xpath = "//*[@class='TextInput_input__fCq2t TextInput_withBefore__m3zLc TextInput_withAfter__iLW91']")
	@CacheLookup
	public  WebElement txtSearchIndex;
	
	@FindBy(xpath = "//*[text()='Search']")
	@CacheLookup
	public  WebElement searchButton;
	
	@FindBy(xpath = "(//*[@class='Filter_section__2MyHS'])[1]")
	@CacheLookup
	public  WebElement searchedResult;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']")
	@CacheLookup
	public  List<WebElement> relatedQuery;
	
	@FindBy(xpath = "//*[@class='AnswerSection_highlight__31LK- ']//following-sibling::*")
	@CacheLookup
	public  WebElement properAnswer;
	
	@FindBy(xpath = "//*[@class='sc-dnqmqq fsyrpy']")
	@CacheLookup
	public WebElement neshLogoIcon;
	
	@FindBy(name = "term")
	@CacheLookup
	public WebElement findHelpAndServicestxtSearchIndex;
	
	@FindBy(xpath = "//*[text()='Report a bug']")
	@CacheLookup
	public WebElement reportABugField;
	
	@FindBy(xpath = "//*[text()='Suggest improvement']")
	@CacheLookup
	public WebElement suggestImprovementField;
	
	@FindBy(xpath = "//*[text()='Suggest a new feature']")
	@CacheLookup
	public WebElement suggestANewFeatureField;
	
	@FindBy(xpath = "//*[text()='Technical support']")
	@CacheLookup
	public WebElement technicalSupportField;
	
	@FindBy(xpath = "//*[@class='Icon__IconWrapper-dyhwwi-0 hYflsA']")
	@CacheLookup
	public WebElement searchIcon;
	
	@FindBy(xpath = "//*[text()='Send a request about']")
	@CacheLookup
	public WebElement searchedHelpAndServices;
	
	@FindBy(xpath = "//*[text()='Report a bug']")
	@CacheLookup
	public WebElement reportABugOption;
	
	@FindBy(xpath = "//*[text()='Suggest improvement']")
	@CacheLookup
	public WebElement suggestImprovementOption;
	
	@FindBy(xpath = "//*[text()='Suggest a new feature']")
	@CacheLookup
	public WebElement suggestANewFeatureOption;
	
	@FindBy(xpath = "//*[text()='Technical support']")
	@CacheLookup
	public WebElement technicalSupportOption;
	
	@FindBy(xpath = "//*[@class='css-j8fq0c']")
	@CacheLookup
	public WebElement loginButton;
	
	@FindBy(xpath = "//*[text()='Log in to Help Center']")
	@CacheLookup
	public WebElement loginToHelpCenterPage;
	
	@FindBy(xpath = "//*[text()='Files']")
	@CacheLookup
	public WebElement filesOption;
	
	@FindBy(xpath = "//*[text()='Web Sites']")
	@CacheLookup
	public WebElement webSitesOption;
	
	@FindBy(xpath = "//*[text()='Data Connectors']")
	@CacheLookup
	public WebElement dataConnectorsOption;
	
	@FindBy(xpath = "//*[text()='Power Ups']")
	@CacheLookup
	public WebElement powerUpsOption;
	
	@FindBy(xpath = "//*[text()='Upload Files']")
	@CacheLookup
	public WebElement uploadFilesOption;
	
	@FindBy(xpath = "//*[text()='Upload FAQs']")
	@CacheLookup
	public WebElement uploadFAQsOption;
	
	@FindBy(xpath = "//*[text()='Email Files']")
	@CacheLookup
	public WebElement emailFilesOption;
	
	@FindBy(xpath = "//*[text()='UPLOAD file']")
	@CacheLookup
	public WebElement uploadFileButton;
	
	@FindBy(xpath = "//*[@class='TextInput_input__fCq2t TextInput_withBefore__m3zLc TextInput_withAfter__iLW91']")
	@CacheLookup
	public WebElement searchOption;
	
	@FindBy(xpath = "(//*[@class='Title_root__33NXB'])[1]")
	@CacheLookup
	public WebElement uploadingSection;
	
	@FindBy(xpath = "(//*[@class='Title_root__33NXB'])[2]")
	@CacheLookup
	public WebElement uploadedSection;
	
	@FindBy(xpath = "(//*[@class='Title_root__33NXB'])[1]")
	@CacheLookup
	public WebElement connectingSection;
	
	@FindBy(xpath = "//*[@class='DocumentDropZone_title__2fKPN']")
	@CacheLookup
	public WebElement uploadYourFiles;
	
	@FindBy(xpath = "//*[@class='ModalTitle_root__15BOH']")
	@CacheLookup
	public WebElement uploadFiles;
	
	@FindBy(xpath = "//*[text()='Compare All']")
	@CacheLookup
	public WebElement compareAllButton;
	
	@FindBy(xpath = "(//*[@class='CompareTable_container__3-mUp']/*/*/*)[1]")
	@CacheLookup
	public WebElement ComparingResult;
	
	@FindBy(xpath = "//*[text()='UPLOAD faq']")
	@CacheLookup
	public WebElement uploadFAQsButton;
	
	@FindBy(xpath = "//*[text()='Next']")
	@CacheLookup
	public WebElement nextButton;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Dropdown_arrow__2Bo33'])[1]")
	@CacheLookup
	public WebElement questionDownArrow;
	
	@FindBy(xpath = "//*[text()='Column A']")
	@CacheLookup
	public WebElement columnAOption;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Dropdown_arrow__2Bo33'])[2]")
	@CacheLookup
	public WebElement answerDownArrow;
	
	@FindBy(xpath = "//*[text()='Save']")
	@CacheLookup
	public WebElement saveButton;
	
	
	@FindBy(xpath = "//*[text()='Save ']")
	@CacheLookup
	public WebElement saveBtn;

	@FindBy(xpath = "//*[text()='UPLOAD email']")
	@CacheLookup
	public WebElement uploadEmailFileButton;
	
	@FindBy(xpath = "//*[@class='UploadEmail_info__14h8O']")
	@CacheLookup
	public WebElement sendEmailSection;
	
	@FindBy(xpath = "//*[text()='Email copied to clipboard']")
	@CacheLookup
	public WebElement emailIDCopiedMessage;
	
	@FindBy(xpath = "//*[text()='CONNECT web site']")
	@CacheLookup
	public WebElement connectWebSiteButton;
	
	@FindBy(name ="name")
	@CacheLookup
	public WebElement linkInputBox;
	
	@FindBy(xpath = "//*[@id='0']")
	@CacheLookup
	public WebElement siteMapsLinkInputBox;
	
	
	@FindBy(xpath = "//*[text()='Website successfully added to connect']")
	@CacheLookup
	public WebElement webSiteConnected;
	
	
	
	@FindBy(xpath = "//*[text()='Website successfully added to connect']")
	@CacheLookup
	public WebElement webSiteAddedMessage;
	
//	@FindBy(xpath = "//*[@class='SourceTypeLink_root__3C0AI']")
//	@CacheLookup
//	public List<WebElement> connectorsOption;
	
	
	@FindBy(xpath = "//*[@class='SourceTypeLink_root__3C0AI']/h4")
	@CacheLookup
	public List<WebElement> connectorsOption;
	
	@FindBy(xpath = "//*[text()='Blob Storage']")
	@CacheLookup
	public WebElement blobStorageOption;
	
	@FindBy(xpath = "//*[text()='Sharepoint']")
	@CacheLookup
	public WebElement sharepointOption;
	
	@FindBy(xpath = "//*[text()='CONNECT blob storage account']")
	@CacheLookup
	public WebElement connectBlobStorageAccountButton;
	
	@FindBy(xpath = "//*[text()='CONNECT sharepoint account']")
	@CacheLookup
	public WebElement sharePointAccountButton;
	
	@FindBy(xpath = "//*[text()='Connect to Sharepoint']")
	@CacheLookup
	public WebElement connectSharePointPage;
	
	@FindBy(xpath = "//*[text()='EDGAR']")
	@CacheLookup
	public WebElement edgerOption;
	
	@FindBy(xpath = "//*[text()='CONNECT edgar']")
	@CacheLookup
	public WebElement edgerCONNECTButton;
	
	@FindBy(xpath = "//*[text()='Adding company to Edgar']")
	@CacheLookup
	public WebElement connectEdgarPage;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-cross__3t7sZ BaseModal_closeButton__3nb62 Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement iconClose;
	
	@FindBy(xpath = "//*[@class='Profile_root__2wkPE']")
	@CacheLookup
	public WebElement profilePage;
	
	@FindBy(xpath = "//*[@class='ModalTitle_root__15BOH']")
	@CacheLookup
	public WebElement LogoutPage;

	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_color-secondary__1ATBQ'])[2]")
	@CacheLookup
	public WebElement groupDropDownIcon;
	
	@FindBy(xpath = "(//*[text()='Sentiment'])[1]")
	@CacheLookup
	public WebElement sentimentGroupOption;
	
	@FindBy(xpath = "(//*[text()='Sentiment'])[3]")
	@CacheLookup
	public WebElement sentimentGrouping;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_color-secondary__1ATBQ'])[3]")
	@CacheLookup
	public WebElement dropDownIcon;
	
	@FindBy(xpath = "(//*[@class='SelectItem_root__3Z0GP'])[2]")
	@CacheLookup
	public WebElement zToARelevanceOption;
	
	@FindBy(xpath = "(//*[@class='TriggerButton_label__Ubo8C'])[2]")
	@CacheLookup
	public WebElement RelevanceFormating;
	
	@FindBy(xpath = "(//*[@class='TriggerButton_label__Ubo8C'])[3]")
	@CacheLookup
	public WebElement filterOption;
	
	@FindBy(xpath = "//*[text()='Clear all']")
	@CacheLookup
	public WebElement clearOption;
	
	@FindBy(xpath = "//*[text()='Source Type']//preceding-sibling::*")
	@CacheLookup
	public WebElement sourceTypeFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Sentiment']//preceding-sibling::*")
	@CacheLookup
	public WebElement sentimentFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Person']//preceding-sibling::*")
	@CacheLookup
	public WebElement personFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Organization']//preceding-sibling::*")
	@CacheLookup
	public WebElement organizationFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Location']//preceding-sibling::*")
	@CacheLookup
	public WebElement locationFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Source Type']//following-sibling::*")
	@CacheLookup
	public WebElement sourceTypeFilterDownArrow;

	@FindBy(xpath = "//*[text()='Sentiment']//following-sibling::*")
	@CacheLookup
	public WebElement sentimentFilterDownArrow;
	
	@FindBy(xpath = "//*[text()='Person']//following-sibling::*")
	@CacheLookup
	public WebElement personFilterDownArrow;
	
	@FindBy(xpath = "//*[text()='Organization']//following-sibling::*")
	@CacheLookup
	public WebElement organizationFilterDownArrow;
	
	@FindBy(xpath = "//*[text()='Location']//following-sibling::*")
	@CacheLookup
	public WebElement locationFilterDownArrow;
	
	@FindBy(xpath = "//*[text()='investor_presentation']//preceding-sibling::*")
	@CacheLookup
	public WebElement sourceTypeSubFilterCheckBox;
	
	@FindBy(xpath = "//*[text()='Show']")
	@CacheLookup
	public WebElement showButton;
	
	@FindBy(xpath = "//*[@class='Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx']")
	@CacheLookup
	public WebElement showBtn;
	
	@FindBy(xpath = "//*[@class='AnswerSection_text__3mBkk']")
	@CacheLookup
	public List<WebElement> answerSection;
	
	@FindBy(xpath = "//*[@class='AnswerSection_text__3mBkk']")
	@CacheLookup
	public WebElement answerTamplate;
	
	@FindBy(xpath = "(//*[@class='Checkbox_wrapper__mZ86L'])[6]")
	@CacheLookup
	public WebElement locationSubFilterCheckBOX;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-cross__3t7sZ FilterSidebar_closeButton__3FBD- Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement closeIcon;

	@FindBy(xpath = "(//*[@class='Checkbox_wrapper__mZ86L'])[3]")
	@CacheLookup
	public WebElement sentimentSubFilterCheckBOX;
	
	@FindBy(xpath = "(//*[@class='Checkbox_root__1p1rI Checkbox_big__k19pT FilterByOptions_checkbox__3nk8_'])[1]")
	@CacheLookup
	public WebElement personSubFilterCheckBOX;
	
	@FindBy(xpath = "(//*[@class='Checkbox_wrapper__mZ86L'])[4]")
	@CacheLookup
	public WebElement OrganizationSubFilterCheckBOX;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-thumbs-up__1SatF VoteController_icon__3-pKx Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement thumbUpButton;
	
	@FindBy(xpath = "//*[@class='AlertPopupTemplate_message__2j-y5']")
	@CacheLookup
	public WebElement thumbResponce;
	
	@FindBy(xpath = "//*[text()='FactSet']")
	@CacheLookup
	public WebElement factsetOption;
	
	@FindBy(xpath = "//*[@class='BaseModal_root__IVR+6 AssignExpertToQuestionModal_root__g9xmK']")
	@CacheLookup
	public WebElement AssignExpertPopUp;
	
//	@FindBy(className ="Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx UploadHeader_uploadButton__18Q0B")
//	@CacheLookup
//	public WebElement factsetConnectButton;
//	
	@FindBy(xpath = "(//*[@type='button'])[3]")
	@CacheLookup
	public WebElement factsetConnectButton;
	
	@FindBy(xpath = "//*[@CLASS='TextInput_input__pKOrF TextInput_withAfter__W6nFG']")
	@CacheLookup
	public WebElement factsetCodeBox;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt FilterDropdown_arrow__3Vwgu']")
	@CacheLookup
	public WebElement documentTypeDownArrow;
	
	@FindBy(xpath = "//*[text()='Investor Presentation']")
	@CacheLookup
	public WebElement investorPresentationCheckBox;
	
	@FindBy(xpath = "//*[text()='Add Factset ']")
	@CacheLookup
	public WebElement addFactsetButton;
	
	@FindBy(xpath = "//*[text()='New FactSet successfully added']")
	@CacheLookup
	public WebElement addedMessage;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[1]")
	@CacheLookup
	public WebElement textOption;
	
	@FindBy(xpath = "(//*[@class='Switch_item__2bJhs Switch_checked__1PC6O'])[2]")
	@CacheLookup
	public WebElement relevantSwitchButton;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[3]")
	@CacheLookup
	public WebElement ImageOption;
	
	@FindBy(xpath = "(//*[@class='Label_root__2PwHZ EntriesAndSuggestions_label__1zDS9'])[1]")
	@CacheLookup
	public WebElement entitiesContains;
	
	@FindBy(xpath = "//*[@class='EntriesAndSuggestionsDropdown_trigger__2tjLh']")
	@CacheLookup
	public WebElement insightsButton;
	
	@FindBy(xpath = "(//*[text()='transcript'])[1]")
	@CacheLookup
	public WebElement transcriptOption;
	
	@FindBy(xpath = "(((//*[@class='ResultsCard_result__11JS5']/*)[2]/*)[1]/*)[2]")
	@CacheLookup
	public WebElement datedFormat;
	
	@FindBy(xpath = "(//*[@class='Chip_root__39ywK Chip_color-secondary__3XoAu'])[3]")
	@CacheLookup
	public WebElement presentationOption;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[3]")
	@CacheLookup
	public WebElement imageTab;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[3]/*")
	@CacheLookup
	public WebElement imageCount;
	
	@FindBy(xpath = "//*[@class='ResultList_cards__32eTx']")
	@CacheLookup
	public WebElement imageSection;
		
	@FindBy(xpath = "(//*[text()='regulatory-filing'])[1]")
	@CacheLookup
	public WebElement RegulatoryOption;
	
	@FindBy(xpath = "((//*[@class='ResultsCard_chips__2a2bq'])[1]/*)[3]")
	@CacheLookup
	public WebElement SECOption;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_color-secondary__1ATBQ'])[1]")
	@CacheLookup
	public WebElement channelDownIcon;
	
	@FindBy(xpath = "//*[text()='Market Research']")
	@CacheLookup
	public WebElement marketResearchChannel;
	
	@FindBy(xpath = "(//*[@class='SelectItem_root__3Z0GP'])[3]")
	@CacheLookup
	public WebElement salesGrowthChannel;
	
	@FindBy(xpath = "(//*[@class='ResultList_root__3yj8l']/*/*)[1]")
	@CacheLookup
	public WebElement answerNotFoundImage;
	
	@FindBy(xpath = "//*[@class='ResultContainer_root__2FddM ResultList_result__3ewpg']")
	@CacheLookup
	public WebElement answerRelatedMarketResearch;
	
	@FindBy(xpath = "//*[@class='ResultList_list__17Yzk']")
	@CacheLookup
	public WebElement uploadedAnswer;
	
	@FindBy(xpath = "(//*[@class='TriggerButton_after__3xfEa']/*)[1]")
	@CacheLookup
	public WebElement groupDownIcon;
	
	@FindBy(xpath = "//*[text()='Company']")
	@CacheLookup
	public WebElement companyGroup;
		
	@FindBy(xpath = "//*[text()='Organization']")
	@CacheLookup
	public WebElement organizationGroup;
	
	
	
	@FindBy(xpath = "//*[@class='TabsCollection_scrollableContainer__24B6U']")
	@CacheLookup
	public WebElement BankNamesSection;
	
	@FindBy(xpath = "//*[@class='TabsCollection_scrollableContainer__24B6U']")
	@CacheLookup
	public WebElement OrganizationNamesSection;
	
	@FindBy(xpath = "(//*[@class='Chip_root__39ywK Chip_color-secondary__3XoAu'])[2]")
	@CacheLookup
	public WebElement dateSection;
	
	@FindBy(xpath = "((//*[@class='AnswerSection_text__3mBkk'])/*/*/*)[3]")
	@CacheLookup
	public WebElement historicalContaint;
	
	@FindBy(xpath = "//*[@class='AnswerSection_highlight__ir92+ ']")
	@CacheLookup
	public WebElement highlightedSentance;
	
//	@FindBy(xpath = "(//*[@class='NavItem_icon__3WPT-'])[2]")
//	@CacheLookup
//	public WebElement channelIcon;
	
	@FindBy(xpath = "((//*[@class='ChannelCard_row__2N46S'])/*)[1]")
	@CacheLookup
	public WebElement userCount;
	
	@FindBy(xpath = "//*[@class='UsersBubbles_root__WXGC2']")
	@CacheLookup
	public WebElement userThumbnails;
	
	@FindBy(xpath = "//*[@class='SummaryCard_text__3Zmqa']")
	@CacheLookup
	public WebElement summarySection;
	
	@FindBy(xpath = "//*[text()='All channels']/*[@class='Icon_icon__2Atss Icon_icon-done__vMbIZ Icon_color-primary__6Da0E']")
	@CacheLookup
	public WebElement allChannels;
	
	@FindBy(xpath = "//*[@class='ResultContainer_root__2FddM ResultList_result__3ewpg']")
	@CacheLookup
	public WebElement answersSalesGrowth;
	
	@FindBy(xpath = "//*[text()='Energy']")
	@CacheLookup
	public WebElement energyChannel;
	
	@FindBy(xpath = "//*[text()='Banking']")
	@CacheLookup
	public WebElement bankingChannel;
	
	@FindBy(xpath = "//*[@class='ResultContainer_root__2FddM ResultList_result__3ewpg']")
	@CacheLookup
	public WebElement RelatedAnswerSection;

	@FindBy(xpath = "//*[@class='Chip_root__39ywK Chip_color-secondary__3XoAu']")
	@CacheLookup
	public List<WebElement> BTU;
	
	@FindBy(xpath = "//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix']")
	@CacheLookup
	public List<WebElement> answerTitle;
	
	@FindBy(xpath = "(//*[@class='Switch_container__3l170'])[2]/*")
	@CacheLookup
	public WebElement notGlowingResponseBox;
	
	@FindBy(xpath = "//*[@class='Switch_container__3l170 Switch_highlight__2-mkA']")
	@CacheLookup
	public WebElement glowingResponseBox;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[1]/*")
	@CacheLookup
	public WebElement textResponseCount;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[2]/*")
	@CacheLookup
	public WebElement tableResponseCount;
	
	@FindBy(xpath = "(//*[@class='Filter_switchItem__10nDb'])[2]/*")
	@CacheLookup
	public WebElement imageResponseCount;
	
	@FindBy(xpath = "//*[@class='DocumentUploadCard_title__2sEib']")
	@CacheLookup
	public List<WebElement> uploadedingFiles;
	
	@FindBy(xpath = "(//*[@class='Sources_container__UdBAx'])[3]//*[@class='BaseDataSourceCard_title__30rL1']")
	@CacheLookup
	public List<WebElement> uploadedFiles;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt'])[2]")
	@CacheLookup
	public WebElement showAllDownArrow;
	
	@FindBy(xpath = "//*[@class='SummaryCard_highlight__1oHzZ ']")
	@CacheLookup
	public WebElement tagsNER;
	
	@FindBy(xpath = "//*[@class='ResultList_title__2Ntls']/*")
	@CacheLookup
	public WebElement totalAnswer;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__LrpL+']//*//h4")
	@CacheLookup
	public List<WebElement> fileName;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']//*//h4")
	@CacheLookup
	public List<WebElement> fileNameUpdated;
	
	@FindBy(xpath = "//*[@class='Table_root__2g7ov AnswerSection_table__3uB24']")
	@CacheLookup
	public List<WebElement> tableAnswer;
	
	@FindBy(xpath = "//*[@class='AnswerSection_img__2pRnU']")
	@CacheLookup
	public List<WebElement> imageAnswer;
	
	@FindBy(xpath = "//*[@class='AnswerSection_text__3mBkk']")
	@CacheLookup
	public List<WebElement> answer;

	@FindBy(xpath = "//*[@class='Switch_item__2bJhs Switch_checked__1PC6O']")
	@CacheLookup
	public WebElement toggleIcon;
	
	@FindBy(xpath = "//*[text()='Active Knowledge']")
	@CacheLookup
	public WebElement activeKnowledge;
	
	@FindBy(xpath = "//*[@class='ChannelCard_title__3NME-']")
	@CacheLookup
	public WebElement marketReSearchChannel;
	
	@FindBy(xpath = "((//*[@class='ChannelCard_row__2N46S'])[1]/*)[1]")
	@CacheLookup
	public WebElement Users;
	
	@FindBy(xpath = "//*[text()='Knowledge Gaps']")
	@CacheLookup
	public WebElement KnowledgeGaps;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-big-arrow-up-filled__y3d-_ AnswerControl_controlButton__29jr8 Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement upVotedIcon;
	
	@FindBy(xpath = "//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix']")
	@CacheLookup
	public WebElement relatedUploadedAnswer;
	
	@FindBy(xpath = "(//*[@class='Chip_root__39ywK Chip_color-secondary__3XoAu'])[1]")
	@CacheLookup
	public WebElement newestAnswer;
	
	@FindBy(xpath = "//*[@class='SummaryCard_text__3Zmqa']/*/*/*")
	@CacheLookup
	public List<WebElement> summaryOfResponse;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-big-arrow-up__2C5Bl AnswerControl_controlButton__29jr8 Icon_clickable__2wcpS'])[6]")
	@CacheLookup
	public WebElement moveUpArrow;
	
	@FindBy(xpath = "(//*[@class='Tooltip_root__2X3ua Tooltip_justify-center__3tYAX AnswerControl_tooltip__1zRhA'])[1]")
	@CacheLookup
	public WebElement VotedCount;
	
	@FindBy(xpath = "//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix']")
	@CacheLookup
	public List<WebElement> FilesName;
	
	@FindBy(xpath = "//*[@class='AnswerSection_root__3RgSr']")
	@CacheLookup
	public List<WebElement> topAnswer;
	
	@FindBy(xpath = "//*[@class='Chip_root__39ywK Chip_variant-rounded__wlMyu Chip_color-green__3Olg1 ChannelCard_status__2LdTD']")
	@CacheLookup
	public WebElement  knowledgeGapPercentage;
	
	@FindBy(xpath = "//*[@class='KnowledgeChannel_switchItem__3iaKz']/*")
	@CacheLookup
	public WebElement  availableQuestion;
	
	@FindBy(xpath = "//*[@class='Tooltip_root__2X3ua Tooltip_justify-center__3tYAX AnswerControl_tooltip__1zRhA']")
	@CacheLookup
	public List<WebElement> upvoteCount;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/h4/span")
	@CacheLookup
	public WebElement answerTextArea;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-question-mark__1A-kN Icon_color-secondary__1ATBQ'])[1]")
	@CacheLookup
	public WebElement downvotedQuestion;
	
	@FindBy(xpath = "//*[@class='Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx']")
	@CacheLookup
	public WebElement answerQuestionButton;
	
	@FindBy(xpath = "//*[text()='By Myself']")
	@CacheLookup
	public WebElement myselfOption;

	@FindBy(xpath = "//*[@class='Checkbox_wrapper__mZ86L']")
	@CacheLookup
	public WebElement myselfOptionCheckBox;
	
	@FindBy(xpath = "//*[text()='Assign']")
	@CacheLookup
	public WebElement assignButton;
	
	@FindBy(xpath = "//*[text()='Assign ']")
	@CacheLookup
	public WebElement assignBtn;
	
	@FindBy(xpath = "//*[@class='Questions_header__2glvV']")
	@CacheLookup
	public WebElement questionAnswerPage;
	
	@FindBy(xpath = "((//*[@class='QuestionItem_chips__MyT6L'])[1]/following-sibling::*)[1]")
	@CacheLookup
	public WebElement downvotedQuestions;
	
	@FindBy(xpath = "//*[@class='notranslate public-DraftEditor-content']")
	@CacheLookup
	public WebElement answerTextField;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-format_list_bulleted__1EfSt']")
	@CacheLookup
	public WebElement bulletIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-format_list_numbered__2x-4k']")
	@CacheLookup
	public WebElement NumberedIcon;
	
	@FindBy(xpath = "//*[text()='Submit']")
	@CacheLookup
	public WebElement submitButton;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-questions-chat__2LtHL']")
	@CacheLookup
	public WebElement QuestionsIcon;
	
	@FindBy(xpath = "//*[text()='Answered']")
	@CacheLookup
	public WebElement answeredTab;
		
	@FindBy(xpath = "//*[text()='Edit']")
	@CacheLookup
	public WebElement editButton;
	
	@FindBy(xpath = "//*[@class='Avatar_root__owf-a']")
	@CacheLookup
	public WebElement expertName;
	
	@FindBy(xpath = "(//*[@class='Tooltip_root__2X3ua Tooltip_justify-end__2S0kA'])[3]")
	@CacheLookup
	public WebElement downvotedIcon;
	
	@FindBy(xpath = "(//*[@class='SelectItem_root__3Z0GP'])[6]")
	@CacheLookup
	public WebElement basin;
	
	@FindBy(xpath = "//*[@class='TabsCollection_items__BKUu6']/*")
	@CacheLookup
	public List<WebElement> basinOption;
	
	@FindBy(xpath = "//*[@class='TabsCollection_items__BKUu6']/*")
	@CacheLookup
	public WebElement basinOptions;
	
	@FindBy(xpath = "//*[text()='Product Support']")
	@CacheLookup
	public WebElement productSupportChannels;
	
	@FindBy(xpath = "//*[text()='Table']")
	@CacheLookup
	public WebElement tableFormat;
	
	@FindBy(xpath = "//*[@class='ResultContainer_root__2FddM ResultList_result__3ewpg']")
	@CacheLookup
	public WebElement imageFormat;
	
	@FindBy(xpath = "//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix']")
	@CacheLookup
	public WebElement cardLink;
	
	@FindBy(xpath = "//*[@class='ExtendedResult_title__2SHnc']")
	@CacheLookup
	public WebElement expandScreenshot;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-big-arrow-up__2C5Bl AnswerControl_controlButton__29jr8 Icon_clickable__2wcpS'])[1]")
	@CacheLookup
	public WebElement upvoteIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-big-arrow-up-filled__y3d-_ AnswerControl_controlButton__29jr8 Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement upvotedIcon;
	
	@FindBy(xpath = "//*[text()='Regs and Policies']")
	@CacheLookup
	public WebElement regsAndPoliciesChannels;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-users__1QFWD']")
	@CacheLookup
	public WebElement channelIcon;
	
	@FindBy(xpath = "//*[@class='SearchInput_suggestions__9z8N7']")
	@CacheLookup
	public WebElement searchPopulateList;
	
	@FindBy(xpath = "(//*[@class='SelectItem_root__3Z0GP'])[2]")
	@CacheLookup
	public WebElement newestOption;
	
	@FindBy(xpath = "(//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix'])[1]")
	@CacheLookup
	public WebElement questionList;
	
	@FindBy(xpath = "//*[@class='Button-root_root__2hlPn Button-variant_slim__f5emQ Button-color_default__1lAQQ Button-size_large__3JqWL SecondaryLayout_backButton__3vv2R']")
	@CacheLookup
	public WebElement backButtonLink;
	
	@FindBy(xpath = "(//*[@class='TriggerButton_label__Ubo8C'])[2]")
	@CacheLookup
	public WebElement shoteOption;
	
	// New UI Locator
	
	@FindBy(xpath = "(//*[@class='Sidebar_link__1QAG8'])[1]")
	@CacheLookup
	public WebElement favoritesIcon;
	
	@FindBy(xpath = "//*[@class='ResultsCard_body__1DSee']")
	@CacheLookup
	public List<WebElement> textImages;
	
	@FindBy(xpath = "//*[@class='AnswerOriginalView_imageContainer__2n6r-']/*")
	@CacheLookup
	public WebElement screenShortImage;
	
	@FindBy(xpath = "//*[@class='Switch_item__2bJhs']")
	@CacheLookup
	public WebElement imageSwitchButton;
	
	@FindBy(xpath = "//*[@class='Icon_icon__DouW- Icon_icon-arrow-down__h9YX7 SelectDropDown_arrow__ZGTGY']")
	@CacheLookup
	public WebElement dropDownChannelIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-search__dzZin Icon_color-secondary__1ATBQ SearchInput_icon__1luiN']")
	@CacheLookup
	public WebElement searchButtonIcon;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']/*/h4")
	@CacheLookup
	public List<WebElement> answerFiles;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']/*/h4")
	@CacheLookup
	public List<WebElement> UpdatedAnswerFiles;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK'])[1]")
	@CacheLookup
	public WebElement recommendedTopic;
	
	@FindBy(xpath = "(//*[@class='ImageCard_title__3xtrK'])[1]")
	@CacheLookup
	public WebElement updatedRecommendedTopic;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[1]/*)[2]/*)[1]")
	@CacheLookup
	public WebElement suggestedQuestions;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[1]/*)[2]/*)[1]")
	@CacheLookup
	public WebElement updatedSuggestedQuestions;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[2]/*)[2]/*)[1]")
	@CacheLookup
	public WebElement popularQuestions;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[2]/*)[2]/*)[1]")
	@CacheLookup
	public WebElement updatedPopularQuestions;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[3]/*)[2]/*)")
	@CacheLookup
	public WebElement myQuestions;
	
	@FindBy(xpath = "(((//*[@class='QuestionsList_root__3b-hr QuestionsList_direction-column__3rjHG QuestionSuggestions_list__345EL'])[3]/*)[2]/*)")
	@CacheLookup
	public WebElement updatedmyQuestions;
	
	@FindBy(xpath = "(//*[@class='ImageCard_root__2FvFc ImageCard_theme-secondary__tzV1p ImageCard_clickable__2McHq'])[1]")
	@CacheLookup
	public WebElement highlightColor;
	
	@FindBy(xpath = "//*[@class='AnswerSection_title__2A-DN AnswerSection_clickable__3euix']//parent::div//parent::div//*[@class='AnswerSection_text__3mBkk']")
	@CacheLookup
	public List<WebElement> topPassages;
	
	@FindBy(xpath = "//*[text()='Recommended topic']")
	@CacheLookup
	public WebElement recommendedTopicSection;
	
	@FindBy(xpath = "//*[@class='ImageCard_title__3xtrK']//h3")
	@CacheLookup
	public List<WebElement> recommendedTopicName;
	
	@FindBy(xpath = "//*[text()='Insights']")
	@CacheLookup
	public WebElement insightsSection;
	
	@FindBy(xpath = "//*[@class='InsightItem_question__1SvZ6']")
	@CacheLookup
	public List<WebElement> insightsSectionTopic;
	
	@FindBy(xpath = "(//*[@class='AnswerSection_text__yp2Ue'])[1]")
	@CacheLookup
	public WebElement summaryPart;
	
	@FindBy(xpath = "(//*[@class='AnswerSection_text__3mBkk'])[1]")
	@CacheLookup
	public WebElement repeatSummaryPart;
	
	@FindBy(xpath = "(//*[@class='MixedResult_body__3lFhO'])[2]//*[@class='AnswerSection_header__2GvF2']/h4")
	@CacheLookup
	public List<WebElement> fileSourceLink;
	
	@FindBy(xpath = "(//*[@class='MixedResult_body__3lFhO'])[2]//*[@class='AnswerSection_header__2GvF2']/h4")
	@CacheLookup
	public List<WebElement> updatedFileSourceLink;
	
	@FindBy(xpath = "//*[@class='NavItem_root__WNXe- NavItem_active__3yV-P Sidebar_navItem__mOPkc']")
	@CacheLookup
	public WebElement activeHomeTab;

	@FindBy(xpath = "//*[@class='AnswerSection_highlight__31LK- ']")
	@CacheLookup
	public List<WebElement> highlightedWord;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-thumbs-down__3hky3 Icon_color-default__cGkyg Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement downvotedThumbIcon;
	
	@FindBy(xpath = "//*[@class='MixedResult_footer__ifiD6']/*")
	@CacheLookup
	public WebElement showOtherAnswerButton;
	
	@FindBy(xpath = "//*[text()='Hide Answers']")
	@CacheLookup
	public WebElement hideAnswer;
	
	@FindBy(xpath = "//*[@class='Button-root_root__1sB-5 Button-variant_outlined__05o-q Button-color_default__fY5J+ Button-size_large__EoHFJ']")
	@CacheLookup
	public WebElement loadAnotherAnswerButton;
	
	@FindBy(xpath = "((//*[@class='MixedResult_header__Hfw-F'])[3]/*)[1]")
	@CacheLookup
	public WebElement otherAnswerHeading;
	
	@FindBy(xpath = "(//*[@class='MixedResult_body__p6xhn'])[3]/*")
	@CacheLookup
	public List<WebElement> totalOtherAnswer;
	
	@FindBy(xpath = "(//*[@class='AnswerSection_image__LpXD4']//img)[1]")
	@CacheLookup
	public WebElement images;
	
	@FindBy(xpath = "//*[@class='Table_root__Ola+X Table_withBorder__MxNIq AnswerSection_table__K+NBW']")
	@CacheLookup
	public WebElement tables;
	
	@FindBy(xpath = "(//*[@class='ResultsCard_answerContainer__LrpL+']//*[@class='AnswerSection_text__yp2Ue'])[1]")
	@CacheLookup
	public WebElement passages;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_color-secondary__1ATBQ'])[2]")
	@CacheLookup
	public WebElement shortedDropDownIcon;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']//h4")
	@CacheLookup
	public WebElement fileSource;
	
	@FindBy(xpath = "(//*[@class='SelectItem_root__3Z0GP'])[3]")
	@CacheLookup
	public WebElement oldestOption;
	
	@FindBy(xpath = "((//*[@class='TriggerButton_root__3y7cc'])[1]/*)[2]")
	@CacheLookup
	public WebElement shortedfield;
	
	@FindBy(xpath = "//*[@class='Tooltip_wrapper__oyn_V Tooltip_position-right__1OIGt']/*")
	@CacheLookup
	public WebElement avatarChannels;
	
	@FindBy(xpath = "(//*[@class='NavItem_icon__3WPT-'])[2]")
	@CacheLookup
	public WebElement avatarChannelsIcon;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']")
	@CacheLookup
	public WebElement searchedresult;
	
	@FindBy(xpath = "//*[text()='Other 24 Answers']")
	@CacheLookup
	public WebElement OtherAnswer;
	
	@FindBy(xpath = "//*[@class='AnswerSection_image__36iRH']//following-sibling::*")
	@CacheLookup
	public List<WebElement> imagesCards;
	
	@FindBy(xpath = "//*[@CLASS='TopicTiles_body__L9g9o']")
	@CacheLookup
	public WebElement gridGap;
	
	@FindBy(xpath = "//*[@class='ResultsCard_expert__JvBFV ResultsCard_chips__v4HZn']//P")
	@CacheLookup
	public List<WebElement> exertName;
	
	@FindBy(xpath = "//*[text()='Date']")
	@CacheLookup
	public WebElement dateFilter;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-arrow-down__3-KAt Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement dateCalenderDownIcon;
	
	@FindBy(xpath = "//*[@class='DatePicker_input__1YzTp']")
	@CacheLookup
	public WebElement calenderField;
	
	@FindBy(xpath = "//*[@class='TriggerButton_counter__1mnVa']")
	@CacheLookup
	public WebElement filteredAnswer;
	
	@FindBy(xpath = "//*[text()='Audit report']")
	@CacheLookup
	public WebElement auditReport;
	
	@FindBy(xpath = "//*[@class='PassageList_root__1Zlsu']//*[@class='AnswerSection_text__3mBkk']")
	@CacheLookup
	public List<WebElement> topPassage;
	
	@FindBy(xpath = "//*[text()='Media Type']//following-sibling::*")
	@CacheLookup
	public WebElement mediaTypeDropDownIcon;
	
	@FindBy(xpath = "(//*[@class='AnswerFeedbackModal_card__1FT1R'])[1]")
	@CacheLookup
	public WebElement reasons;
	
	@FindBy(xpath = "//*[text()='Submit ']")
	@CacheLookup
	public List<WebElement> submitbtn;
	
	@FindBy(xpath = "//*[@class='AlertPopupTemplate_message__2j-y5']")
	@CacheLookup
	public WebElement votedMgs;
	
	@FindBy(xpath = "(//*[@class='AnswerFeedbackModal_card__1FT1R'])[5]")
	@CacheLookup
	public WebElement txtreasons;
	
	@FindBy(xpath = "(//*[@class='AnswerFeedbackModal_card__1FT1R'])[4]")
	@CacheLookup
	public WebElement txtReasonsUp;
	
	@FindBy(xpath = "//*[@name='custom_input']")
	@CacheLookup
	public WebElement txtReasonsField;
	
	@FindBy(xpath = "//*[@class='ModalTitle_root__15BOH']")
	@CacheLookup
	public List<WebElement> feedbackForm;
	
	@FindBy(xpath = "(//*[@class='Tooltip_root__2X3ua Tooltip_justify-end__2S0kA'])[1]")
	@CacheLookup
	public WebElement upVoteThumb;
	
	@FindBy(xpath = "//*[@class='QuestionItem_text__1mL_N']")
	@CacheLookup
	public List<WebElement> latestSuggestions;
	
	@FindBy(xpath = "//*[@class='SelectItem_root__3Z0GP']")
	@CacheLookup
	public List<WebElement> groupBy;
	
	@FindBy(xpath = "//*[@class='Filter_switchItem__10nDb']//span")
	@CacheLookup
	public List<WebElement> filesize;
	
	@FindBy(xpath = "//*[@class='Switch_item__2bJhs Switch_checked__1PC6O Switch_clickable__2UIW3']/*")
	@CacheLookup
	public WebElement switchedField;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']/*/h4/following-sibling::*")
	@CacheLookup
	public WebElement textAnswer;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-cross__3t7sZ AlertPopupTemplate_closeButton__reXa9 Icon_clickable__2wcpS']")
	@CacheLookup
	public WebElement closeIconPopUp;
	
	@FindBy(xpath = "((//*[@class='Table_container__2fejN']//tr)[1]//*[@class='Table_column__Hn3m5']//p)[1]")
	@CacheLookup
	public WebElement exploreAndAskAvatarColunm;
	
	@FindBy(xpath = "((//*[@class='Table_container__2fejN']//tr)[1]//*[@class='Table_column__Hn3m5']//p)[2]")
	@CacheLookup
	public WebElement auditAvatarColunm;
	
	@FindBy(xpath = "//*[@class='sc-iQtOjA eQomna']")
	@CacheLookup
	public List<WebElement> neshHelpSection;
	
	@FindBy(xpath = "//*[@class='sc-drMfKT cxKvYY sc-bEjcJn fSxTIP']")
	@CacheLookup
	public WebElement needToRaiseField;
	
	@FindBy(xpath = "//*[@class='sc-hIVACf gFyEZL']")
	@CacheLookup
	public List<WebElement> raiseRequestFields;
	
	@FindBy(xpath = "//*[@class='TextInput_input__pKOrF']")
	@CacheLookup
	public List<WebElement> signUpFields;
	
	@FindBy(xpath = "//*[text()='Reset']")
	@CacheLookup
	public List<WebElement> resetButton;
	
	@FindBy(xpath = "//*[@class='ModalTitle_root__15BOH']")
	@CacheLookup
	public WebElement addingCompanyToEdgar;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']")
	@CacheLookup
	public List<WebElement> searchedAnswer;
	
	@FindBy(xpath = "//*[@class='LevitatingVoteController_controlContainer__1p-98']")
	@CacheLookup
	public List<WebElement> votingPopUp;
	
	@FindBy(xpath = "//*[@class='UserAvatar_avatarEdit__1lcSF']")
	@CacheLookup
	public WebElement changeProfileIcon;
	
	@FindBy(xpath = "//*[@href='/expert']")
	@CacheLookup
	public WebElement expertIcon;
	
	@FindBy(xpath = "//*[text()='Metadata']")
	@CacheLookup
	public WebElement metadataSection;
	
	@FindBy(xpath = "//*[@class='ResultsCard_chips__2a2bq']//*[text()='data-connector']")
	@CacheLookup
	public WebElement dataConnectorData;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']//h4")
	@CacheLookup
	public List<WebElement> answertitle;
	
	@FindBy(xpath = "//*[text()='Post to expert']")
	@CacheLookup
	public WebElement postQuestionButton;
	
	@FindBy(xpath = "(//*[@class='ListItem_item__W7-Sa ListItem_clickable__2xqMz'])[1]//*[@class='UserCard_body__MqeBn']//h3")
	@CacheLookup
	public WebElement expert;
	
	@FindBy(xpath = "//*[@class='AlertPopupTemplate_message__2j-y5']")
	@CacheLookup
	public WebElement questionAssignMessage;
	
	@FindBy(xpath = "//*[@class='BadgeWrapper_root__2yl2d']//following-sibling::*")
	@CacheLookup
	public List<WebElement> allCard;
	
	@FindBy(xpath = "//*[text()='Show File Summary']")
	@CacheLookup
	public WebElement showFileSummaryLink;
	
	@FindBy(xpath = "//*[@class='ContentBox_content__2PI3X']")
	@CacheLookup
	public WebElement fileSummarySection;
	
	@FindBy(xpath = "//*[@class='AnswerFeedbackModal_card__1FT1R']")
	@CacheLookup
	public List<WebElement> radioFeedback;
	
	@FindBy(xpath = "(//*[@type='radio'])[1]")
	@CacheLookup
	public WebElement radioButton;
	
	@FindBy(xpath = "(//*[@class='BaseDataSourceCard_container__3AFiS'])[1]")
	@CacheLookup
	public WebElement uploadingfile;
	
	@FindBy(xpath = "((//*[@class='QuestionsList_list__TjV6V QuestionSuggestions_listContainer__JH3bc'])[3]//*//*)[1]")
	@CacheLookup
	public WebElement myQuestionSection;
	
	@FindBy(xpath = "((//*[@class='QuestionsList_list__TjV6V QuestionSuggestions_listContainer__JH3bc'])[3]//*//*)[1]")
	@CacheLookup
	public List<WebElement> myQuestionCell;
	
	@FindBy(xpath = "((//*[@class='QuestionsList_list__TjV6V QuestionSuggestions_listContainer__JH3bc'])[2]//*//*)[1]")
	@CacheLookup
	public List<WebElement> popularQuestionCell;
	
	@FindBy(xpath = "//*[@class='ResultsCard_answerContainer__15nKY']/*/h4/following-sibling::*")
	@CacheLookup
	public List<WebElement> passageAnswer;
	
	@FindBy(xpath = "((((//*[@class='ResultsCard_chips__2a2bq']//*[text()='data-connector'])[1]//parent::*)[1]//parent::*)[1]//parent::*//preceding-sibling::*)[1]")
	@CacheLookup
	public WebElement passageAnswerDataConnector;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestionItem_text__1etiB']")
	@CacheLookup
	public List<WebElement> suggestedQuestionList;
	
	@FindBy(xpath = "//*[@class='BaseModal_root__xOf-9 SelectAvatarModal_root__3OgtB']")
	@CacheLookup
	public List<WebElement> avatarPage ;
	
	@FindBy(xpath = "//*[@class='NavItem_icon__3WPT-']")
	@CacheLookup
	public List<WebElement>allMenuOption;
	
	@FindBy(xpath = "//*[@class='Tooltip_body__MTizS Tooltip_alignment-center__H9KAT Tooltip_color-primary__2W1-_ Sidebar_tooltipList__37556']")
	@CacheLookup
	public WebElement menuType;
	
	@FindBy(xpath = "(//*[@class='LevitatingVoteController_controlContainer__1p-98']/*)[2]")
	@CacheLookup
	public List<WebElement> postToAnExpertButton;
	
	@FindBy(xpath = "(//*[@class='NavItem_root__WNXe- Sidebar_navItem__mOPkc'])[1]")
	@CacheLookup
	public WebElement avatarNavigationIcon;
	
	@FindBy(xpath = "(//*[@class='Icon_icon__2Atss Icon_icon-share__2Hu7L'])[1]//parent::*")
	@CacheLookup
	public WebElement shareIcon;
	
	@FindBy(xpath = "//*[text()='Audit report']/*")
	@CacheLookup
	public WebElement auditDownIcon;
	
	@FindBy(xpath = "//*[text()='Decisions']/*")
	@CacheLookup
	public WebElement decisionsDownIcon;
	
	@FindBy(xpath = "//*[text()='Audit report']//following-sibling::*")
	@CacheLookup
	public WebElement auditContent;
	
	@FindBy(xpath = "//*[text()='Decisions']//following-sibling::*")
	@CacheLookup
	public WebElement decisionContent;
	
	@FindBy(xpath = "(//*[text()='Metadata']//following-sibling::*//*)[3]")
	@CacheLookup
	public WebElement metaData;
	
	@FindBy(xpath = "//*[@class='QuestionsList_title__1ieqH']")
	@CacheLookup
	public WebElement auditAvatarOption;
	
	@FindBy(xpath = "//*[@class='Switch_container__3l170 Switch_size-small__3Ki4-']/*")
	@CacheLookup
	public List<WebElement>summarySizeToggle;
	
	@FindBy(xpath = "//*[@class='List_root__2XQRm List_gutter-0__1cAu2 List_direction-row__2EDdp List_align-center__1IS_v List_justify-initial__OTyqS List_overflow-auto__1aegl']")
	@CacheLookup
	public WebElement summaryFeedBack;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-thumbs-up__1SatF VoteController_icon__3-pKx Icon_clickable__2wcpS']")
	@CacheLookup
	public List<WebElement> ThumbFeedbackOption;

	@FindBy(xpath = "//*[@class='Button-root_root__1sB-5 Button-variant_contained__fe7h- Button-color_primary__qB7yo Button-size_large__EoHFJ LevitatingVoteController_capsule__FvIL2']")
	@CacheLookup
	public WebElement postToExpertBtn;
	
	@FindBy(xpath = "(//*[@class='UserCard_body__MqeBn'])[1]")
	@CacheLookup
	public WebElement Expert;
	
	@FindBy(xpath = "//*[@class='Button-root_root__1sB-5 Button-variant_outlined__05o-q Button-color_primary__qB7yo Button-size_tiny__DnEQm']")
	@CacheLookup
	public WebElement filterButton;
	
	@FindBy(xpath = "//*[@class='ListItem_item__W7-Sa ListItem_clickable__2xqMz']")
	@CacheLookup
	public List<WebElement> fileExtensions;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-laughing-face__38VOF Icon_color-default__cGkyg SummaryVoteController_icon__1lWxT']")
	@CacheLookup
	public WebElement smileIcon;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-laughing-face__38VOF Icon_color-default__cGkyg SummaryVoteController_icon__1lWxT']")
	@CacheLookup
	public List<WebElement> feedbackFace;
	
	@FindBy(xpath = "(//*[@class='Radio_root__2dmgP Radio_size-medium__1m2Bh'])[1]")
	@CacheLookup
	public WebElement feedbackIcon;
	
	@FindBy(xpath = "(//*[@class='Chip_title__2c-BL'])[1]")
	@CacheLookup
	public WebElement clientFilter;
	
	@FindBy(xpath = "//*[@class='ComicBubble_bubble__3EdEP']")
	@CacheLookup
	public WebElement notBright;
	
	@FindBy(xpath = "//*[text()='Decision']")
	@CacheLookup
	public List<WebElement> decisionOtion;
	
	@FindBy(xpath = "//*[@class='BaseModal_root__xOf-9 AssignExpertToQuestionModal_root__Jqp4u']")
	@CacheLookup
	public WebElement postQuestionPage;
	
	@FindBy(xpath = "//*[@class='QuestionSuggestions_root__3wCHD']")
	@CacheLookup
	public WebElement spaceBtQues;
	
	@FindBy(xpath = "//*[@class='Tooltip_root__2X3ua Tooltip_justify-center__3tYAX AnswerControl_upvoteCounter__dcZFn']")
	@CacheLookup
	public List<WebElement>upvotedCount;
	
	@FindBy(xpath = "//*[@class='Icon_icon__2Atss Icon_icon-big-arrow-up__2C5Bl']")
	@CacheLookup
	public List<WebElement> moveUp;
	
	@FindBy(xpath = "//*[@class='Switch_container__9SF1e Switch_size-big__7UF7y Home_tabs__xLFeg']//*[@type='button']")
	@CacheLookup
	public List<WebElement> viewMode;
	
	@FindBy(xpath = "//*[@class='Icon_icon__DouW- Icon_icon-rocket__Xw1+k']")
	@CacheLookup
	public List<WebElement> openExtras;
	
	@FindBy(xpath = "//*[@class='ContentBox_root__jn-o3 ContentBox_color-secondary__dWf2a SearchExtras_root__3-I0g']")
	@CacheLookup
	public WebElement openExtrasPanel;
	
	@FindBy(xpath = "//*[@class='CollapseHeader_root__GmG7y CollapseHeader_clickable__liVtc AnswerSection_collapsableHeader__P4wlL']")
	@CacheLookup
	public List<WebElement> openExtrasOption;
	
	@FindBy(xpath = "//*[@class='ComicBubble_body__gBPjC']")
	@CacheLookup
	public WebElement bubbleText;
	
	@FindBy(xpath = "//*[@class='Sidebar_header__1VC9l']")
	@CacheLookup
	public WebElement emptySpace;
	
	@FindBy(xpath = "(//*[@class='QuestionsList_list__TjV6V QuestionSuggestions_cardListContainer__QPDAg']//*[@class='ImageCard_root__-qoiM ImageCard_theme-secondary__h96BK ImageCard_clickable__8iY9V QuestionSuggestions_cardItem__utJB+'])[1]")
	@CacheLookup
	public WebElement searchRecommendedTopic;
	
	@FindBy(xpath = "//*[@class='TextInput_input__fCq2t TextInput_withBefore__m3zLc TextInput_withAfter__iLW91']")
	@CacheLookup
	public  WebElement searchBar;
	
	
	
	
	
	
	
	
	
}
