package com.jcarlsten

import fitlibrary.DoFixture
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.util.concurrent.TimeUnit

class WebFixture extends DoFixture {
    WebDriver webDriver = createWebDriver(new FirefoxProfile())
    static final int FIVE_SECOND_TIMEOUT = 5
    public static final String TAB = "\t"

    WebFixture() {

    }

    void navigateTo(String url) {
        webDriver.get(url)
    }

    void clicksElementById(String id) {
        webDriver.findElement(By.id(id)).click()
    }

    void clicksElementBytag(String tag) {
        webDriver.findElement(By.tagName(tag)).click()
    }

    void entersTextFor(String text, String elementName) {
        new WebDriverWait(webDriver, FIVE_SECOND_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)))
        WebElement element = webDriver.findElements(By.name(elementName)).find { it.isDisplayed() }
        element.click()
        element.clear()
        element.sendKeys(text)
        element.sendKeys(TAB)
    }

    String textForIs(String elementId){
        webDriver.findElement(By.id(elementId)).text
    }

    void closeBrowser(){
        webDriver.close()
    }


    private WebDriver createWebDriver(FirefoxProfile profile) {
        File firefoxPath = new File('./firefox/41.0/firefox.exe')
        WebDriver driver = new FirefoxDriver(new FirefoxBinary(firefoxPath), profile)

        driver.manage().window().setPosition(new Point(-2000, -2000))   //Move the window out of our view, it's annoying during the build.
        //use win + arrow keys to bring back when debugging.


        WebDriver.Timeouts timeouts = driver.manage().timeouts()
        timeouts.implicitlyWait(1, TimeUnit.SECONDS)
        timeouts.pageLoadTimeout(10, TimeUnit.SECONDS)
        timeouts.setScriptTimeout(10, TimeUnit.SECONDS)

        driver.manage().window().setSize(new Dimension(1920, 1080))
        driver
    }

}
