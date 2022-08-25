package PageObjects.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MyAccount {

    private WebDriver driver;

    public MyAccount(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement successfullyCreatedAccount() {
        return Utils.waitForElementPresence(driver, By.xpath("//p[contains(text(), \"Welcome to your account.\")]"), 30);
    }

    public WebElement getAccountLogout() {
        return Utils.waitToBeClickable(driver, By.xpath("//a[@title=\"Log me out\"]"), 30);
    }

}
