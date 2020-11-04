package task.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMapPageElements {
	
	@FindBy (xpath = "//input[@id='searchboxinput']")
	public WebElement enterCoordinates;
	@FindBy (xpath = "//span[contains(text(),'Grünheide, 15537 Grünheide (Mark)')]")
	public WebElement LocationAddress;
	
}
