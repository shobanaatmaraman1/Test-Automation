package test.java;

import main.java.com.xadcentral.regression.BasicOps;
import main.java.com.xadcentral.regression.ReadConf;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by xAD-inc on 2/19/2015.
 */
public class BasicOpsTests {
    static BasicOps bigX;
    static ReadConf readConf;

    private WebDriver driver;

    private static String loginPage;
    private static String username;
    private static String password;

    private static String invalid_username;
    private static String invalid_password;

    private static String welcomeText=null;
    private static String verifyText="Welcome to xAd Central! Use the search box above to start!";
    private static String logOutPage=null;
    private static String logOutPageText="http://ec2-54-196-26-194.compute-1.amazonaws.com:8080/";

    @BeforeSuite
    public void setup() throws Exception {

        readConf=new ReadConf();
        bigX=new BasicOps();
        bigX.setup();
        driver=new ChromeDriver();
        username=readConf.getUsername();
        password=readConf.getPassword();
        invalid_username=readConf.getInvalidUsername();
        invalid_password=readConf.getInvalidPassword();
        loginPage=readConf.getLogInPage();
    }

    @AfterSuite
    public void tearDown() throws Exception {

        bigX.tearDown(driver);
        //xls.saveChanges();

    }

    @Test
    public void testLoginResponse() throws Exception {
        try {
            welcomeText = bigX.loginWithCredentials(driver, username, password);
            if (verifyText.equals(welcomeText)) {
                System.out.println(welcomeText);
            }
        }
        catch (ElementNotVisibleException v) {
            v.printStackTrace();
            Assert.fail("Fail");
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            Assert.fail("Fail");

        } catch (org.openqa.selenium.TimeoutException t) {
            t.printStackTrace();
            Assert.fail("Fail");

        } catch (WebDriverException d) {
            d.printStackTrace();
            Assert.fail("Fail");
        }

    }

    @Test
    public void testInvalidUsernameLoginResponse() throws Exception {

        try {

            welcomeText=bigX.loginWithCredentials(driver,invalid_username,password);
            if (verifyText.equals(welcomeText)) {
                Assert.fail("Failed");
            }
        }
        catch (ElementNotVisibleException v) {
            v.printStackTrace();
            System.out.println("Pass");
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            System.out.println("Pass");

        } catch (org.openqa.selenium.TimeoutException t) {
            t.printStackTrace();
            System.out.println("Pass");

        } catch (WebDriverException d) {
            d.printStackTrace();
            System.out.println("Pass");

        }
    }

    @Test
    public void testInvalidPasswordLoginResponse() throws Exception {

        try {
        welcomeText=bigX.loginWithCredentials(driver,username,invalid_password);

            if (verifyText.equals(welcomeText)) {
                Assert.fail("Failed");
            }
        }
        catch (ElementNotVisibleException v) {
            v.printStackTrace();
            System.out.println("Pass");
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            System.out.println("Pass");

        } catch (org.openqa.selenium.TimeoutException t) {
            t.printStackTrace();
            System.out.println("Pass");

        } catch (WebDriverException d) {
            d.printStackTrace();
            System.out.println("Pass");

        }

    }

    @Test
    public void logoutTestAfterLogin() throws Exception {
        try{
            bigX.logIn(driver);
            bigX.signOut(driver);
            if(logOutPageText.equals(logOutPage)) {
                System.out.println("Pass");
            }
        }
        catch (ElementNotVisibleException v) {
            v.printStackTrace();
            Assert.fail("Fail");

        } catch (NoSuchElementException n) {
            n.printStackTrace();
            Assert.fail("Fail");

        } catch (org.openqa.selenium.TimeoutException t) {
            t.printStackTrace();
            Assert.fail("Fail");

        } catch (WebDriverException d) {
            d.printStackTrace();
            Assert.fail("Fail");

        }

    }




}
