package task.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import task.page.elements.GoogleMapPageElements;
import task.selemiumfunctions.SeleniumFunctions;

public class GoogleMapUtils extends SeleniumFunctions {

	public GoogleMapUtils(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setLoaction(String pArticle) {
		GoogleMapPageElements googlePageElements = PageFactory.initElements(webDriver, GoogleMapPageElements.class);
		googlePageElements.enterCoordinates.sendKeys(pArticle);
		pressEnter();
	}

	public String getLoactionDetails() {
		GoogleMapPageElements googlePageElements = PageFactory.initElements(webDriver, GoogleMapPageElements.class);
		String finalLocation = googlePageElements.LocationAddress.getText();

		try {
			takeScreenshotAndSave("GoogleMapsFinalLocation.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalLocation;
	}
}
