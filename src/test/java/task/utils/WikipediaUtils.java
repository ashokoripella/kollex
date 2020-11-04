package task.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import task.page.elements.WikipediaPageElements;
import task.selemiumfunctions.SeleniumFunctions;

public class WikipediaUtils extends SeleniumFunctions {
//
//	@FindBy(xpath = "//input[@id='searchInput']")
//	WebElement searchArticle;
//	@FindBy(xpath = "//button[@type='submit']")
//	WebElement searxhSubmitButton;

	public WikipediaUtils(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public String setArticle(String pArticle) {
		WikipediaPageElements wikipediapageelements = PageFactory.initElements(webDriver, WikipediaPageElements.class);
		wikipediapageelements.searchArticle.sendKeys(pArticle);
		try {
			takeScreenshotAndSave("wikipedia_page.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		pressEnter();
		return pArticle;
	}

	public void searchForArticle(String pArticle) {
		clearAndSendKey(webDriver.findElement(By.xpath("//input[@id='searchInput']")), "Giga Berlin");
		try {
			takeScreenshotAndSave("Entering_Giga_Berlin.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pressEnter();
		try {
			takeScreenshotAndSave("Results_for_Giga_Berlin.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
