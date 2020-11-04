package task.kollex.tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import task.page.elements.GigaBerlinPageElements;
import task.page.elements.GoogleMapPageElements;
import task.page.elements.WikipediaPageElements;
import task.selemiumfunctions.SeleniumFunctions;
import task.utils.GigaBerlinWikipediaUtiils;
import task.utils.GoogleMapUtils;
import task.utils.WikipediaUtils;

public class ChromeBrowserTestCase extends SeleniumFunctions {
	@BeforeClass
	public void testSetUp() {
		openBrowser("chrome");
		readExpectedDataFromCSV();
	}
	
	@Test
	public void automationTask() throws Exception {

		webDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Cookie ck = new Cookie("CONSENT", "YES+DE.de+V14+BX");
		webDriver.manage().addCookie(ck);
		Thread.sleep(3000);
		takeScreenshotAndSave("google_page.png");
		webDriver.navigate().to("https://wikipedia.com");
		takeScreenshotAndSave("wikipedia_page.png");

		// object for wikipedia
		WikipediaPageElements wikipediapageelements = PageFactory.initElements(webDriver, WikipediaPageElements.class);
		WikipediaUtils wikiPage = new WikipediaUtils(webDriver);
		wikiPage.setArticle(expectedDataMap.get("Article"));

		// Giga Berlin Wiki page
		GigaBerlinWikipediaUtiils gigaBerlinWikiUtils = new GigaBerlinWikipediaUtiils(webDriver);
		GigaBerlinPageElements gigaberlinpageElements = PageFactory.initElements(webDriver, GigaBerlinPageElements.class);
		// get Location Coordinates.
		String coordinateValues = gigaBerlinWikiUtils.getCoordinates();
		Assert.assertEquals(expectedDataMap.get("Coordinates"), coordinateValues);
		// check Logistics Data		
		scrollToElement(gigaberlinpageElements.logistics);
		takeScreenshotAndSave("LogisticsData.png");
		Assert.assertEquals(expectedDataMap.get("Logistics"),gigaBerlinWikiUtils.validateLogisticsData());
		// check siteConcerns data
		scrollToElement(gigaberlinpageElements.siteConcerns);
		takeScreenshotAndSave("siteConcernsData.png");
		Assert.assertEquals(expectedDataMap.get("SiteConcerns"),gigaBerlinWikiUtils.validateSiteConcernsData());
		Thread.sleep(4000);
		//googlemaps
		((JavascriptExecutor)webDriver).executeScript("window.open('https://maps.google.de','_blank');");
		Thread.sleep(7000);
		ArrayList<String> tabs = new ArrayList<String> (webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs.get(1));
		takeScreenshotAndSave("GoogleMaps.png");
		GoogleMapPageElements googleMapPageElement = PageFactory.initElements(webDriver, GoogleMapPageElements.class);
		GoogleMapUtils googleMapUtils = new  GoogleMapUtils(webDriver);
		 //enter location coordinates
		googleMapUtils.setLoaction(coordinateValues);
		takeScreenshotAndSave("EnterLocationInGoogleMaps.png");
		Thread.sleep(10000);
		Assert.assertEquals(googleMapUtils.getLoactionDetails(), expectedDataMap.get("LocationDetails"));
		takeScreenshotAndSave("MapResults.png");
	}
	@AfterClass
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
		System.out.println("screenshots location is " + System.getProperty("user.dir"));
	}
}
