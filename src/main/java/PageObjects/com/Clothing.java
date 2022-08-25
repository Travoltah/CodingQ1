package PageObjects.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Clothing {

    private WebDriver driver;

    public Clothing(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getWomenBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]"), 30);
    }

    public WebElement getDressesBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]"), 30);
    }

    public WebElement getEveningDressesBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]//a[contains(text(), \"Evening Dresses\")]"), 30);
    }

    public WebElement getAddToCartBtn() {
        return Utils.waitForElementPresence(driver, By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div/p/button/span"), 30);
    }

    public WebElement getAddedToCartModal() {
        return Utils.waitForElementPresence(driver, By.id("layer_cart"), 30);
    }

    public WebElement getContinueShopingBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//span[@title=\"Continue shopping\"]"), 30);
    }

    public WebElement getProceedToCheckoutBtn() {
        return Utils.waitToBeClickable(driver, By.xpath("//span[contains(text(), \"Proceed to checkout\")]"), 30);
    }

    public WebElement getSuccessfullyAddedMessage() {
        return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"), 30);
    }
    public WebElement getEveningDressProduct(int dressNum) {
        return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"center_column\"]/ul/li[" + dressNum + "]"), 30);
    }

    // CART ACTIONS ///////

    public WebElement getDressQty() {
        return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"quantity_wanted\"]"), 30);
    }
    public WebElement getDressSize() {
        return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"group_1\"]"), 30);
    }

    public WebElement getDressColor() {
        return Utils.waitForElementPresence(driver, By.id("color_24"), 30);
    }
    // Cart Product Details

    public WebElement getCartProductsQty() {
        return Utils.waitForElementPresence(driver, By.id("layer_cart_product_quantity"), 30);
    }

    public WebElement getCartShipingCost() {
        return Utils.waitForElementPresence(driver, By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[2]/span"), 30);
    }
    public WebElement getCartTotalPrice() { //SPAN[@id='layer_cart_product_price']
        return Utils.waitForElementPresence(driver, By.xpath("//span[@class=\"ajax_block_products_total\"]"), 30);
    }
    public WebElement getCartGrandTotalPrice() {
        return Utils.waitForElementPresence(driver, By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[3]/span"), 30);
    }
    public WebElement getCartDressColor_Size() {
        return Utils.waitForElementPresence(driver, By.xpath("//*[@id=\"layer_cart_product_attributes\"]"), 30);
    }

    public WebElement getmainMenu() {
        return Utils.waitForElementPresence(driver,By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li/div/div[1]/div/a[1]/img"), 30);
    }

    public WebElement getHomeButton() {
        return Utils.waitForElementPresence(driver,By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/ul/li/a/span"), 30);
    }

    public WebElement getPopularButton() {
        return Utils.waitForElementPresence(driver,By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/ul/li[1]/a"), 30);
    }


    public void setQuantity(int qnty) {
        WebElement prodquantity = this.getDressQty();
        prodquantity.clear();
        prodquantity.sendKeys(Integer.toString(qnty));
    }
    public void setDressSize(String size) {
        WebElement dressSize = this.getDressSize();
        Select dropdown = new Select(dressSize);
        //dropdown.deselectAll();
        dropdown.selectByVisibleText(size);
    }

}