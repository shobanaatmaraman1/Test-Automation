package test.java;

import main.java.com.xadcentral.regression.BasicOps;
import main.java.com.xadcentral.regression.Campaign;
import main.java.com.xadcentral.regression.ReadConf;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by xAD-inc on 2/23/2015.
 */
public class CampaignTests {
    static BasicOps bigX;
    static Campaign camp;
    static ReadConf readConf;
    private WebDriver driver;


    @BeforeSuite
    public void setup() throws Exception {
        bigX = new BasicOps();
        camp = new Campaign();
        readConf = new ReadConf();
        readConf.readJson();

        bigX = new BasicOps();
        bigX.setup();
        driver = new ChromeDriver();
        bigX.logIn(driver);

        Dimension targetSize = new Dimension(1920, 1080);
        driver.manage().window().setSize(targetSize);

    }

    @AfterSuite
    public void tearDown() throws Exception {

        bigX.tearDown(driver);
        //xls.saveChanges();


    }

    @Test
    public void camapignCreationTest() throws Exception {
        try {
            camp.createCampaign(driver);
        } catch (org.openqa.selenium.TimeoutException t) {
            t.printStackTrace();

            Assert.fail("Failed");
        } catch (NoSuchElementException n) {
            n.printStackTrace();

            Assert.fail("Failed");
        } catch (WebDriverException d) {
            d.printStackTrace();
            Assert.fail("Failed");
        }

    }


}
