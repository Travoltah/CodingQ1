package AutomationPractice.com;

import PageObjects.com.Clothing;
import PageObjects.com.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.com.LoginForm;
import PageObjects.com.MyAccount;

import javax.swing.*;
import java.util.Comparator;
import java.util.*;
//import automationpractice.com.pageObject.CreateAccountForm;
//import automationpractice.com.pageObject.SignInForm;
//import utils.EmailsGenerator;

public class LoginTest {

    private WebDriver driver;
    //private CreateAccountForm createAccountForm;
    private LoginForm signin;
    private MyAccount account;
    private Actions action;
    private Clothing clothes;



    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win33\\chromedriver.exe");
        driver = new ChromeDriver();

      //  createAccountForm = new CreateAccountForm(driver);
        signin = new LoginForm(driver);
        account = new MyAccount(driver);
        clothes = new Clothing(driver);
        action=new Actions(driver);

        String baseUrl = "http://automationpractice.com/index.php?controller=authentication";
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterClass
    public void closeAll() {
        account.getAccountLogout().click();
        driver.quit();
    }

    @Test(priority = 1)
    public void loginPage() {
        Assert.assertTrue(signin.getSignInForm().isDisplayed());
        Assert.assertTrue(signin.getSignInEmailField().isDisplayed());
        Assert.assertTrue(signin.getSignInPasswordField().isDisplayed());
        Assert.assertTrue(signin.getSignInBtn().isEnabled());
    }

    @Test(priority = 2)
    public void invalidCredentials() {

        signin.setEmailField("treykayz@email.com");
        signin.setPasswordField("Pass145");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());

        signin.setEmailField("email@email.email");
        signin.setPasswordField("ggt856");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());

        signin.setEmailField("treykayz@gmail.com");
        signin.setPasswordField("Pass145");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());

    }

    @Test(priority = 3)
    public void loginWithoutCredentials() {
        signin.setEmailField("");
        signin.setPasswordField("look3344");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getEmailRequiredError().isDisplayed());

        signin.setEmailField("mtkay@gmail.com");
        signin.setPasswordField("");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getPasswordRequiredError().isDisplayed());

        signin.setEmailField("");
        signin.setPasswordField("");
        signin.getSignInBtn().click();

        Assert.assertTrue(signin.getEmailRequiredError().isDisplayed());
    }

    @Test(priority = 4)
    public void emailFormat() {
        signin.setEmailField("email");
        signin.getSignInPasswordField().click();

        Assert.assertTrue(signin.getEmailHighlightedRed().isDisplayed());

        signin.setEmailField("email@email");
        signin.getSignInPasswordField().click();

        Assert.assertTrue(signin.getEmailHighlightedRed().isDisplayed());

        signin.setEmailField("email@email.email");
        signin.getSignInPasswordField().click();

        Assert.assertTrue(signin.getEmailHighlightedGreen().isDisplayed());
    }

    @Test(priority = 5)
    public void successfulLogin() {
        signin.setEmailField("testautomationmfs@gmail.com");
        signin.setPasswordField("TestAutomation@123");
        signin.getSignInBtn().click();

        Assert.assertTrue(account.successfullyCreatedAccount().isDisplayed());
    }

    @Test(priority = 6)
    public void listPopular() {
        signin.setEmailField("testautomationmfs@gmail.com");
        signin.setPasswordField("TestAutomation@123");
        signin.getSignInBtn().click();


        //Click Home Button
        clothes.getHomeButton().click();

        // Click Popular
        clothes.getPopularButton().click();

        // Identify the list of products and prices
        List<WebElement> vs = driver.findElements(By.xpath("//DIV[@class='product-container']/self::DIV"));
        ArrayList<String> obtainedList = new ArrayList<>();

        for (WebElement product : vs) {
            if (product.getText().isBlank()==false) {
                obtainedList.add(product.getText());;
            }
        }

        String[] lines = new String[0];
        ArrayList<String[]> listOfStringArrays = new ArrayList<String[]>();

        Collections.sort(obtainedList);
        for(String s:obtainedList) {

            if (s != "") {
                lines = (s.split("[\r\n]+"));
                listOfStringArrays.add(new String[] {lines[0].toString(),lines[1].toString()});
            }
        }

        System.out.println("***********List of apparels under PUPOLAR category**********");

        Collections.sort(listOfStringArrays,new Comparator<String[]>() {
            public int compare(String[] strings, String[] otherStrings) {
                return strings[1].compareTo(otherStrings[1]);
            }
        });
        for (String[] sa : listOfStringArrays) {
            System.out.println(Arrays.toString(sa));
        }
    }

    @Test(priority = 7)
    public void AddToCart() throws Exception {
        signin.setEmailField("testautomationmfs@gmail.com");
        signin.setPasswordField("TestAutomation@123");
        signin.getSignInBtn().click();

        Assert.assertTrue(account.successfullyCreatedAccount().isDisplayed());

        //scroll Page Up
        Actions actuP = new Actions(driver);
        actuP.sendKeys(Keys.PAGE_UP).build().perform(); //Page Down
        Thread.sleep(5000);

        // Add evening dress to cart
        action.moveToElement(clothes.getDressesBtn()).perform();

        Assert.assertTrue(clothes.getEveningDressesBtn().isDisplayed());

        action.moveToElement(clothes.getEveningDressesBtn()).perform();
        clothes.getEveningDressesBtn().click();


        //Select size as M
        WebElement SizeCheckbx=driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_2\"]"));
        boolean isSelected = SizeCheckbx.isSelected();

       //performing click operation if element is not checked
        if(isSelected == false) {
            SizeCheckbx.click();
        }
        Thread.sleep(5000);

        //Select Color
        driver.findElement(By.id("layered_id_attribute_group_24")).click();
        Thread.sleep(5000);


        //scroll page down
        Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
        Thread.sleep(5000);

        // Slider Price Range
        WebElement PriceRange=driver.findElement(By.xpath("//*[@id=\"layered_price_slider\"]"));
        Thread.sleep(2000);
        int xCoord = PriceRange.getLocation().getX();
        System.out.println("*........ XCood is:----"+xCoord);
        Actions builder = new Actions(driver);
        builder.moveToElement(PriceRange)
                .click()
                .dragAndDropBy
                        (PriceRange,-150, 0)
                .build()
                .perform();

        Thread.sleep(2000);


        //Hover on displayed Dress Item
        //Create object 'action' of an Actions class
        Actions action = new Actions(driver);
        //moving to the main menu and then sub menu and clicking on it using object of the Actions class
        action.moveToElement(clothes.getmainMenu()).moveToElement(
                driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li/div/div[2]/div[2]/a[2]/span"))).click().build().perform();

        Thread.sleep(8000);
        Assert.assertTrue(clothes.getDressColor().isDisplayed());
        //Thread.sleep(5000);
        Assert.assertTrue(clothes.getDressQty().isDisplayed());
        Assert.assertTrue(clothes.getAddToCartBtn().isDisplayed());

        //select Dress item Quantity (3)
        clothes.setQuantity(3);
        Thread.sleep(5000);

        //select Dress item Size (M)
        clothes.setDressSize("M");
        Thread.sleep(5000);

        //set Dress Color (Pink)
        clothes.getDressColor().click();
        Thread.sleep(5000);


        String mainWindowHandle = driver.getWindowHandle();

        //Click Add To Cart
        action.click(clothes.getAddToCartBtn()).build().perform();

        // to get the list of all window handles after the new tab
        Set<String> windowHandles = driver.getWindowHandles();

        // switch to new opened tab
        Iterator<String> itr = windowHandles.iterator();
        while (itr.hasNext()) {
            String childWindowHandle = itr.next();
            // to skip the handle of our main window and switch to new one
            if (!mainWindowHandle.equalsIgnoreCase(childWindowHandle))
                driver.switchTo().window(childWindowHandle);

        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"layer_cart_product_quantity\"]")));


        // Verifying product attributes of items added to cart
        Assert.assertEquals(clothes.getCartProductsQty().getText(), "3");
        Assert.assertEquals(clothes.getCartDressColor_Size().getText(), "Pink, M");
        Assert.assertEquals(clothes.getCartTotalPrice().getText(), "$152.97");
        Assert.assertEquals(clothes.getCartShipingCost().getText(), "$2.00");
        Assert.assertEquals(clothes.getCartGrandTotalPrice().getText(), "$154.97");

        //Print out the Cart Product Details
        System.out.println("*****************Cart Product Details*****************");
        System.out.println("Product Quantity:......... " +clothes.getCartProductsQty().getText());
        System.out.println("Dress Size:........ " +clothes.getCartDressColor_Size().getText().toString().split(",")[1]);
        System.out.println("Dress Color:......... " +clothes.getCartDressColor_Size().getText().toString().split(",")[0]);
        System.out.println("Total Product Cost:......... " +clothes.getCartTotalPrice().getText());
        System.out.println("Shipping Cost:........ " +clothes.getCartShipingCost().getText());
        System.out.println("Total Cost (Total Product Cost + Shipping Cost):........ " +clothes.getCartGrandTotalPrice().getText());

        WebElement ClosePopup = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/span"));
        ClosePopup.click();


        driver.switchTo().window(mainWindowHandle);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@title=\"Log me out\"]")));

    }

}
