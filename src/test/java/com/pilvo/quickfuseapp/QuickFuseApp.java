package com.pilvo.quickfuseapp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by lokesh.sharma03 on 21/08/19.
 */
public class QuickFuseApp {

    WebDriver driver;

    public QuickFuseApp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@id='link-create']")
    private WebElement linkCreate;

    @FindBy(xpath = "//div[@id='intro-dialog-cont']//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']")
    private WebElement buttonPane;

    @FindBy(xpath = "//a[@id='add-page']")
    private WebElement addPage;

    @FindBy(xpath = "//form[@class='unsubmittable']//input[@name='name']")
    private WebElement name;

    @FindBy(xpath = "//div[@id='create-dialog']/following::button[1]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='accordion']//a[contains (text(), 'Messaging')]")
    private WebElement messagingText;

    @FindBy(xpath = "//li[contains (., 'Send an SMS')]")
    private WebElement sendAnSMS;

    @FindBy(xpath = "//*[@id='module-1']//div[text()='Start']")
    private WebElement start;


    public void createNewApp() throws InterruptedException {

        try {
            linkCreate.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='intro-dialog-cont']//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']")));

            buttonPane.click();
            //Click Get Started
            addPage.click();
            Thread.sleep(4000);
            name.sendKeys("TestPage");
            Thread.sleep(4000);
            submitButton.click();
            Thread.sleep(4000);

            //Click Messaging
            messagingText.click();
            TimeUnit.SECONDS.sleep(3);


            Point p = sendAnSMS.getLocation();
            System.out.println(p.getX() + "," + p.getY());


            Point p1 = start.getLocation();
            System.out.println(p1.getX() + "," + p1.getY());

            //Move Send an SMS
            Actions actions = new Actions(driver);
            WebElement source = driver.findElement(By.xpath("//li[contains (., 'Send an SMS')]//span"));
            WebElement destination = driver
                    .findElement(By.xpath("//*[@id='module-1']//div[text()='Start']"));

            //Drop Send an SMS to Board
            actions.clickAndHold(source).moveToElement(destination, -100, 110).release().build().perform();
            ;

            //Wait for sec for module to be loaded on screen with an arrow on top
            TimeUnit.SECONDS.sleep(5);

            //Assign new Source and Destnation for connecting start and send an SMS module
            source = driver.findElement(By.xpath(
                    "//*[text()='Start']//ancestor::div[contains (@id, \"module-1\")]//div[contains (@class, 'draggable')]")); //Source -   //*[text()='Start']//ancestor::div[contains (@id, "module-1")]//div[contains (@class, 'draggable')]  --  //--//*[@id="module-1"]//div[contains (@class, 'draggable')]
            destination = driver.findElement(By.xpath(
                    "//div[text()='Send an SMS']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'droppable')]")); //Destination - //div[text()='Send an SMS']//ancestor::div[contains (@id,"module")]//div[contains (@class, 'droppable')]

            //Connect Start module > Send and SMS Module
            actions.clickAndHold(source).moveToElement(destination).release().build().perform();

            //wait for a sec to join to get connected
            TimeUnit.SECONDS.sleep(5);

            //Put Send an Email Module on Board just next to Send an SMS Module
            source = driver.findElement(By.xpath("//li[contains (., 'Send an Email')]//span"));
            destination = driver.findElement(By.xpath("//div[text()='Send an SMS']//ancestor::div[contains (@id,\"module\")]"));

            //Drag and Drop third Module : Sending an Email - on board
            actions.clickAndHold(source).moveToElement(destination, 290, 130).release().build().perform();

            //wait for module to get loaded on screen
            TimeUnit.SECONDS.sleep(5);

            //Assign new Source and Destnation for connecting 'Send an SMS' and 'Send an Email' module
            source = driver.findElement(By.xpath(
                    "//*[text()='Send an SMS']//ancestor::div[contains (@id, \"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-e')]"));
            destination = driver.findElement(
                    By.xpath("//div[text()='Send an Email']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'droppable')]"));
            actions.clickAndHold(source).moveToElement(destination).release().build().perform();

            //Wait for connection to be succesful
            TimeUnit.SECONDS.sleep(5);

            //Click Basic Tab for getting Hang Up or exit - //*[@id='accordion']//a[contains (text(), 'Basic')]
            driver.findElement(By.xpath("//*[@id='accordion']//a[contains (text(), 'Basic')]")).click();

            //Wait for animation to complete
            TimeUnit.SECONDS.sleep(5);

            //Put Hang up or Exit button on board - //li[contains (., 'Hang Up or Exit')]//span
            source = driver.findElement(By.xpath("//li[contains (., 'Hang Up or Exit')]//span"));
            destination = driver.findElement(By.xpath(
                    "//div[text()='Send an SMS']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]"));
            actions.clickAndHold(source).moveToElement(destination, -100, 50).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

            //Connect - Send an SMS with Exit App (Module-4)
            source = driver.findElement(By.xpath(
                    "//div[text()='Send an SMS']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]"));// //Source - //div[text()='Send an SMS']//ancestor::div[contains (@id,"module")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]
            destination = driver.findElement(By.xpath(
                    "//div[text()='Exit App']//ancestor::div[contains (@id,\"module-4\")]//div[contains (@class, 'droppable')]"));//		//Destination with - //div[text()='Exit App']//ancestor::div[contains (@id,"module-4")]//div[contains (@class, 'droppable')]
            actions.clickAndHold(source).moveToElement(destination).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

            //Put Hang up or Exit button on board - //li[contains (., 'Hang Up or Exit')]//span
            source = driver.findElement(By.xpath("//li[contains (., 'Hang Up or Exit')]//span"));
            destination = driver.findElement(By.xpath(
                    "//div[text()='Send an Email']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]"));
            actions.clickAndHold(source).moveToElement(destination, -100, -100).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

            //Connect - Send an SMS with Exit App (Module-4)
            source = driver.findElement(By.xpath(
                    "//div[text()='Send an Email']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]"));// //Source - //div[text()='Send an SMS']//ancestor::div[contains (@id,"module")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]
            destination = driver.findElement(By.xpath(
                    "//div[text()='Exit App']//ancestor::div[contains (@id,\"module-5\")]//div[contains (@class, 'droppable')]"));//		//Destination with - //div[text()='Exit App']//ancestor::div[contains (@id,"module-4")]//div[contains (@class, 'droppable')]
            actions.clickAndHold(source).moveToElement(destination).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

            //Put Hang up or Exit button on board - //li[contains (., 'Hang Up or Exit')]//span
            source = driver.findElement(By.xpath("//li[contains (., 'Hang Up or Exit')]//span"));
            destination = driver.findElement(By.xpath(
                    "//div[text()='Send an Email']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-e')]"));
            actions.clickAndHold(source).moveToElement(destination, +35, -10).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

            //Connect - Send an SMS with Exit App (Module-4)
            source = driver.findElement(By.xpath(
                    "//div[text()='Send an Email']//ancestor::div[contains (@id,\"module\")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-e')]"));// //Source - //div[text()='Send an SMS']//ancestor::div[contains (@id,"module")]//div[contains (@class, 'draggable') and contains(@class,'syn-node-attached-w')]
            destination = driver.findElement(By.xpath(
                    "//div[text()='Exit App']//ancestor::div[contains (@id,\"module-6\")]//div[contains (@class, 'droppable')]"));//		//Destination with - //div[text()='Exit App']//ancestor::div[contains (@id,"module-4")]//div[contains (@class, 'droppable')]
            actions.clickAndHold(source).moveToElement(destination).release().build().perform();
            TimeUnit.SECONDS.sleep(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
