package task.kollex.tests;

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

public class FirefoxBrowseerTestCase extends SeleniumFunctions {
	@BeforeClass
	public void testSetUp() {
		openBrowser("firefox");
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
		// get Logistics Data		
		scrollToElement(gigaberlinpageElements.logistics);
		Assert.assertEquals(expectedDataMap.get("Logistics"),gigaBerlinWikiUtils.validateLogisticsData());
		// get siteConcerns data
		scrollToElement(gigaberlinpageElements.siteConcerns);
		Assert.assertEquals(expectedDataMap.get("SiteConcerns"),gigaBerlinWikiUtils.validateSiteConcernsData());
		//google maps
//		((JavascriptExecutor)webDriver).executeScript("window.open('https://maps.google.de','_blank');");		
		webDriver.navigate().to("https://maps.google.de");
		GoogleMapPageElements googleMapPageElement = PageFactory.initElements(webDriver, GoogleMapPageElements.class);
		GoogleMapUtils googleMapUtils = new  GoogleMapUtils(webDriver);
		 //enter location coordinates
		googleMapUtils.setLoaction(coordinateValues);
		Thread.sleep(10000);
		Assert.assertEquals(googleMapUtils.getLoactionDetails(), expectedDataMap.get("LocationDetails"));
	}
	@AfterClass
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
		System.out.println("screenshots location is " + System.getProperty("user.dir"));
	}

}
