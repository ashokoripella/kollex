package task.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GigaBerlinPageElements {
	
	//**Coordinates
	@FindBy(xpath = "//span[@id='coordinates']//span[@class='plainlinks nourlexpansion']//span[@style='white-space: nowrap;']//a[@class='external text']//span[@class='geo-default']//span[@class='geo-dec'][contains(text(),'52.4°N 13.8°E')]")
	public WebElement coordinates;

	//**Logistics
	@FindBy(xpath = "//span[@id='Logistics']")
	public WebElement logistics;
	@FindBy(xpath = "//body[@class='mediawiki ltr sitedir-ltr mw-hide-empty-elt ns-0 ns-subject mw-editable page-Giga_Berlin rootpage-Giga_Berlin skin-vector action-view skin-vector-legacy']/div[@id='content']/div[@id='bodyContent']/div[@id='mw-content-text']/div[@class='mw-parser-output']/p[18]")
	public WebElement logisticsData1;
	@FindBy(xpath = "//p[contains(text(),'Some of the reasons for choosing Brandenburg were ')]")
	public WebElement logisticsData2;

	// siteConcerns
	@FindBy(xpath = "//span[@id='Site_concerns']")
	public WebElement siteConcerns;	
	@FindBy(xpath = "//p[contains(text(),'The project is subject to a number of concerns, in')]")
	public WebElement siteConcernsData1;
	@FindBy(xpath = "//p[contains(text(),'A group of neighbouring municipalities formed in D')]")
	public WebElement siteConcernsData2;
	@FindBy(xpath = "//p[contains(text(),'(some American) are commonly found in German groun')]")
	public WebElement siteConcernsData3;
	@FindBy(xpath = "//body[@class='mediawiki ltr sitedir-ltr mw-hide-empty-elt ns-0 ns-subject mw-editable page-Giga_Berlin rootpage-Giga_Berlin skin-vector action-view skin-vector-legacy']/div[@id='content']/div[@id='bodyContent']/div[@id='mw-content-text']/div[@class='mw-parser-output']/p[23]")
	public WebElement siteConcernsData4;
	@FindBy(xpath = "//body[@class='mediawiki ltr sitedir-ltr mw-hide-empty-elt ns-0 ns-subject mw-editable page-Giga_Berlin rootpage-Giga_Berlin skin-vector action-view skin-vector-legacy']/div[@id='content']/div[@id='bodyContent']/div[@id='mw-content-text']/div[@class='mw-parser-output']/p[24]")
	public WebElement siteConcernsData5;
}
