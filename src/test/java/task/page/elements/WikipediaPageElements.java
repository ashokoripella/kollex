package task.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaPageElements {
	@FindBy(xpath = "//input[@id='searchInput']")
	public WebElement searchArticle;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement searchSubmitButton;
}
