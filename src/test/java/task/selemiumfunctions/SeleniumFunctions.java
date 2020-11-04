package task.selemiumfunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumFunctions extends task.utils.FileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumFunctions.class);
	public static WebDriver webDriver;
	public String url = "https://google.de";
	private WebElement element;
	protected Map<String, String> expectedDataMap = new HashMap<String, String>();
	protected String browser = "firefox";

	public void openBrowser(String browser) {
		this.browser = browser;
		LOGGER.info("Starting Browser: " + browser);
		if (browser.toLowerCase().contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\champa\\eclipse-workspace1\\kollex.automation\\geckodriver.exe");
			webDriver = new FirefoxDriver();
			webDriver.manage().window().maximize();
		} else if (browser.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\champa\\eclipse-workspace1\\kollex.automation\\chromedriver.exe");
			webDriver = new ChromeDriver();
		} else if (browser.toLowerCase().contains("internetexplorer")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\champa\\eclipse-workspace1\\kollex.automation\\IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
		}
		webDriver.get(url);
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LOGGER.info("Browser " + browser + " Successfully Invoked ");
	}

	public void setUrl(String url) {

		if (webDriver != null) {
			LOGGER.info("Navigating to url " + url);
			webDriver.navigate().to(url);
		} else
			LOGGER.info("No URL to Navigate");
	}

	public void closeBrowser() {

		if (webDriver != null) {
			LOGGER.info("close browser");
			webDriver.quit();
		} else
			LOGGER.info("No Browser to Close");
	}

	public boolean checkElementIsVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (ElementNotVisibleException e) {
			return false;
		}
	}

	public void moveToElementAndClick(WebElement webElement) {

		try {
			System.out.println("Click on element");
			new Actions(webDriver).moveToElement(webElement).click().build().perform();
		} catch (ElementNotVisibleException e) {
			scrollToElement(webElement);
			webElement.click();
		}
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void click(WebElement element, String name) throws Exception {

		try {
			LOGGER.info("Verify that " + name + " is clicked");
			checkElementIsVisible(element);
			element.click();
		} catch (StaleElementReferenceException ex) {
			element.click();
		} catch (ElementClickInterceptedException e) {
			moveToElementAndClick(element);
		} catch (Exception e) {
			LOGGER.info("Failed to click on " + name);
			throw (e);
		}
	}

	public void clearAndSendKey(WebElement fieldName, String data) {

		LOGGER.info("Verify text box is cleared and enter value ");
		clearTextBoxValue(fieldName);
		fieldName.sendKeys(data);

	}

	public void clearTextBoxValue(WebElement Elememt) {

		LOGGER.info("Verify clear text box value");
		Elememt.clear();

	}

	public void pressEnter() {

		final Actions builder = new Actions(this.webDriver);
		builder.sendKeys(Keys.RETURN).build().perform();

	}

	public void moveToElement(WebElement webElement) {

		try {
			System.out.println("Click on element");
			new Actions(webDriver).moveToElement(webElement);
		} catch (ElementNotVisibleException e) {
			scrollToElement(webElement);
		}
	}

	public String ScrollToElementAndGetText(String elementXpath) {

		WebElement element = webDriver.findElement(By.xpath(elementXpath));
		try {
			new Actions(webDriver).moveToElement(element).build();
		} catch (ElementNotVisibleException e) {
			scrollToElement(element);
		}
		return element != null ? element.getText() : null;
	}

	public String findWebElementAndGetText(String elementXpath) {
		WebElement element = webDriver.findElement(By.xpath(elementXpath));
		return element != null ? element.getText() : null;
	}

	protected static void takeScreenshotAndSave(String fileName) throws Exception {

		// *** Converting web driver object to capture Screenshot
		TakesScreenshot screenShot = ((TakesScreenshot) webDriver);
		File SrcFile = screenShot.getScreenshotAs(OutputType.FILE);
		// saving all the images in file location
		String filePath = System.getProperty("user.dir") + "\\screenshots\\";
		File DestFile = new File(filePath + fileName);
		FileUtils.copyFile(SrcFile, DestFile);

	}

	protected String scrollByVisibleElement(String xpathElement) {
		element = webDriver.findElement(By.xpath(xpathElement));
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		// This will scroll the page Horizontally till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
		return element != null ? element.getText() : null;
	}

	protected void readExpectedDataFromCSV() {
		File csvFile = new File(getFileLocation());
		String line = null;

		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(getFileLocation()));
			while ((line = inputReader.readLine()) != null) { // create instance using line.
				expectedDataMap.put(line.substring(0, line.indexOf(";")), line.substring(line.indexOf(";") + 1));
			}
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + getFileLocation());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}