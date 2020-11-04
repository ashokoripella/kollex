package task.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import task.page.elements.GigaBerlinPageElements;
import task.selemiumfunctions.SeleniumFunctions;

public class GigaBerlinWikipediaUtiils extends SeleniumFunctions {

	public StringBuilder logisticsActualText = new StringBuilder();
	public StringBuilder siteConcernsText = new StringBuilder();

	GigaBerlinPageElements gigaBerlinPageElements = PageFactory.initElements(webDriver, GigaBerlinPageElements.class);

	public GigaBerlinWikipediaUtiils(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getCoordinates() {
		String loacationCoordinates = gigaBerlinPageElements.coordinates.getText();
		try {
			takeScreenshotAndSave("GigaBerlinLocationCoordinates.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Location Coordinates are:" + loacationCoordinates);
		return loacationCoordinates;

	}

	public String validateLogisticsData() {
		logisticsActualText.append(gigaBerlinPageElements.logisticsData1.getText());
		logisticsActualText.append(gigaBerlinPageElements.logisticsData2.getText());
		return logisticsActualText.toString();
	}

	public String validateSiteConcernsData() {
		siteConcernsText.append(gigaBerlinPageElements.siteConcernsData1.getText());
		siteConcernsText.append(gigaBerlinPageElements.siteConcernsData2.getText());
		siteConcernsText.append(gigaBerlinPageElements.siteConcernsData3.getText());
		siteConcernsText.append(gigaBerlinPageElements.siteConcernsData4.getText());
		siteConcernsText.append(gigaBerlinPageElements.siteConcernsData5.getText());
		return siteConcernsText.toString();
	}
}
