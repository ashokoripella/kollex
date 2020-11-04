"#Kollex  Task" 
# Kollex Task
## Table of contents
* [Introduction](#intoduction)
* [Technologies](#technologies)
* [Given Scenario](#givenscenario)
* [Approach](#approach)
* [Project Structure](#projectstructure)

## Introduction
This Project is for "Kollex Automation Task" and used as a Maven Project. Maven is a powerful project management tool that is based on POM (project object model). It is used for projects build, dependency and documentation. It simplifies the build process. All steps were explained in seperate sections. This project was build and then comitted using Git. 
## Technologies Used
* Testing Framework-: TestNG version 6.8.9
* OperatingSystem---: Windows 8.1
* Browsers----------: Chrome(Version 86.0.4240.111), Firefox(82.0.2)
* IDE---------------: Eclipse
* Programing--------: Java(version  10.0.2)
* Automation Tool---: Selenium
## Given Scenario
*    From    www.google.com ,   your   automation   should   take   you   to    www.wikipedia.com .   
*    From   there,   it   should   find   the   article   “Giga   Berlin”   using   the   search   box.  
*    Your   automation   should   find   the   Coordinates   of   the   location   and   the   following   data: Logistics,   Site   concerns.  
*    The   automation   should   open   a   new   tab   to   check   Google   Maps   with   the   location.    

## Approach
Page Object Model: (POM) is a design pattern, popularly used in test automation that creates Object Repository for web UI elements. The advantage of the model is that it reduces code duplication and improves test maintenance.
* A better approach to script maintenance is to create a separate class file which would find web elements. This class can be reused in all the scripts using that element. In future, if there is a change in the web element, we need to make the change in just 1 class file.
* Written all the WebElements in seperate Package(task.page.elements) with respective to their class(Eg: WikipediaPageElements--for wikipedia page). So for every page their elements are written in their respective classes. It helps make the code more readable, maintainable, and reusable.
* Here we are dealing the pages (WikipediaPage, GigaBerlinPage, GoogleMapsPage).
* For initialization of Page objects or to instantiate the Page object itself Page Factory is used.
* The concept is separation of Page Object Repository and Test Methods.
## Code Changes For Below Changes
### For avoidng duplicate lines of code  we can use below steps for changing the browser(eg: Firefox,Chrome). 
* The are two test cases written to show how exacly we work (POM) design. Just the driver(chrome or firefox) is different in the class rest of the script is same.
* @BeforeClass
*	public void testSetUp() {
*		openBrowser("chrome");//we can change to Firefox here itself so it will run on firefox driver.
*		readExpectedDataFromCSV();
*	}
### For saving screenshots in specific folders
* For screenshots all screen shots are saving in a single folder to increase its redudancy line 178: in seleniumFunctions (String filePath = System.getProperty("user.dir") + "\\screenshots\\"+browser;) and parse the broswer as paramaeter so that it will save in their respective folders in scrennshots.

## Execution
* first clean the project using "MVN clean"
* Now build the project  "mvn vlean install" or use "mvn vlean install -DskipTests" to skip test execution
* Now update project
* Test case can be run as "runastestnG"

## Project Structure
* task.page.elemets(package)---All pageElement Classes are written in this package.
* task.kollex.tests(package)---All TestCases are written here.
* task.utils(package)----------All page Utils(Page Methods) and File Utils(Csv File) are written here.
* task.seleniumfunctions(package)-All Selenium Funcetions(methods) written here.
* screenshots(Folder)----------All screeenshots are saved in this folder after each step.
* expectedData.csv-(src/test/resources)-------Expected data is stored in csv file location 
## Coverd BonusPoints
* Make the tests work with multiple browsers  (Chrome,  Safari,  etc.)
-->chrome and firefox browsers are used.
* Make sure that your automation documents (Via  Screenshots)   every   step   taken.
-->All screenshots are saved in a Folder named screenshots. when ever we run the test case every time it will take the screenshots
* Manage to copy the relevant info into a CSV file.
-->Expected data is stored in the csv file(src/test/resources)"expectedData.csv".



