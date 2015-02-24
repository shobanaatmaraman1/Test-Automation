package main.java.com.xadcentral.regression;

/**
 * Created by xAD-inc on 2/17/2015.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasicOps {

    private static String stagingLogin;
    private static String username;
    private static String password;
    private static String chromeDriverPath;

    static ReadConf conf;

    public void setup() {
        conf = new ReadConf();
        conf.readJson();

        chromeDriverPath=conf.getChromeDriverPath();

        System.setProperty("webdriver.chrome.driver",chromeDriverPath);

    }

    public void tearDown(WebDriver driver) {
        driver.quit();
    }

    // Log into xAd Central with the right credentials

    public void logIn(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        conf = new ReadConf();
        conf.readJson();
        username=conf.getUsername();
        password=conf.getPassword();

        stagingLogin=conf.getLogInPage();


        driver.get(stagingLogin);
        WebElement userIDEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[1]/div[1]/div[2]/input")));

        userIDEle.sendKeys(username);
        WebElement passwordEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[1]/div[2]/div[2]/input")));

        passwordEle.sendKeys(password);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/form/div[3]/input")));
        submit.click();

    }

    public String loginWithCredentials(WebDriver driver,String username,String password) {
        String welcomeText=null;
        conf = new ReadConf();
        conf.readJson();

        stagingLogin=conf.getLogInPage();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get(stagingLogin);
        WebElement userIDEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[1]/div[1]/div[2]/input")));
        userIDEle.sendKeys(username);
        WebElement passwordEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/div[1]/div[2]/div[2]/input")));

        passwordEle.sendKeys(password);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/form/div[3]/input")));
        submit.click();

        WebElement loginResponse=wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
        welcomeText=loginResponse.getText();

        return welcomeText;

    }

    public String signOut(WebDriver driver)  {
        WebDriverWait wait=new WebDriverWait(driver,10);
        String signOutPath="logoff";
        WebElement signOutElement=wait.until(ExpectedConditions.elementToBeClickable(By.className(signOutPath)));
        signOutElement.click();

        return driver.getCurrentUrl();

    }


}
