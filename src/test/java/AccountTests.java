package test.java;
/**
 * Created by xAD-inc on 2/17/2015.
 */

import main.java.com.xadcentral.regression.Account;
import main.java.com.xadcentral.regression.BasicOps;
import main.java.com.xadcentral.regression.ExcelOps;
import main.java.com.xadcentral.regression.ReadConf;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class AccountTests {
    static BasicOps bigX;
    static Account ac;
    static ReadConf conf;

    private ExcelOps xls;
    private Boolean priorityFlag=false;
    private WebDriver driver;

    private String marketUS;
    private String marketUK;
    private String marketIndia;
    private String marketCanada;
    private String marketGermany;
    private String marketFrance;

    private String accountTypeDA;
    private String accountTypeDC;
    private String accountTypeDP;
    private String accountTypePC;
    private String accountTypePR;
    private String accountTypePCMR;
    private String accountTypeProg;

    private String accountDomainName;
    private String email;
    private String first_name;
    private String last_name;
    private String title;
    private String phone;

    @BeforeSuite
    public void setup() throws Exception {
        xls=new ExcelOps();
        ac = new Account();

        conf = new ReadConf();
        conf.readJson();

        bigX=new BasicOps();
        bigX.setup();
        driver = new ChromeDriver();
        bigX.logIn(driver);

        marketUS=conf.getMarketUS();
        marketUK=conf.getMarketUK();
        marketIndia=conf.getMarketIndia();
        marketCanada=conf.getMarketCanada();
        marketGermany=conf.getMarketGermany();
        marketFrance=conf.getMarketFrance();

        accountTypeDA=conf.getAccountTypeDA();
        accountTypeDC=conf.getAccountTypeDC();
        accountTypeDP=conf.getAccountTypeDP();
        accountTypePC=conf.getAccountTypePC();
        accountTypePR=conf.getAccountTypePR();
        accountTypePCMR=conf.getAccountTypePCMR();
        accountTypeProg=conf.getAccountTypeProg();

        accountDomainName=conf.getDomainName();
        email=conf.getAccountNewContactEmail();
        first_name=conf.getAccountNewContactFirstName();
        last_name=conf.getAccountNewContactLastName();
        title=conf.getNewContactTitle();
        phone=conf.getNewContactPhone();

        Dimension targetSize = new Dimension(1920, 1080);
        driver.manage().window().setSize(targetSize);

    }


    @AfterSuite
    public void tearDown() throws Exception {

        bigX.tearDown(driver);
        xls.saveChanges();


    }

    // Create a test account of type direct agency and market type UK

    @Test
    public void testAccountDA_UK() throws Exception {
        priorityFlag=xls.readPriority("testAccountDA_UK");
        if(priorityFlag) {

            System.out.println("TC1: Create an account of type Direct Agency and Market UK : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated DA_UK", accountDomainName, accountTypeDA, marketUK);
                xls.readAndWriteToSheet("testAccountDA_UK", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountDA_UK", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountDA_UK", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountDA_UK", "Fail");
                Assert.fail("Failed");
            }

            System.out.println("TC1: Create an account of type Direct Agency and Market UK : Completed");


        }

    }
    // Create a test account of type direct client and market type India

    @Test
    public void testAccountDC_India() throws Exception {
        priorityFlag=xls.readPriority("testAccountDC_India");

        if(priorityFlag) {

            System.out.println("TC2: Create an account of type Direct Client and Market India : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated DC_India", accountDomainName, accountTypeDC, marketIndia);
                xls.readAndWriteToSheet("testAccountDC_India", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountDC_India", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountDC_India", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountDC_India", "Fail");
                Assert.fail("Failed");

            }

            System.out.println("TC2: Create an account of type Direct Client and Market India : Completed");


        }

    }
    // Create a test account of type direct platform and market type US

    @Test
    public void testAccountDP_US() throws Exception {
        priorityFlag=xls.readPriority("testAccountDP_US");

        if(priorityFlag) {

            System.out.println("TC3: Create an account of type Direct Programmatic and Market US : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated DP_US", accountDomainName ,accountTypeDP, marketUS);
                xls.readAndWriteToSheet("testAccountDP_US", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountDP_US", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountDP_US", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountDP_US", "Fail");
                Assert.fail("Failed");

            }

            System.out.println("TC3: Create an account of type Direct Programmatic and Market US  : Completed");


        }

    }

    // Create a test account of type platform channel and market type Germany

    @Test
    public void testAccountPC_Germany() throws Exception {
        priorityFlag=xls.readPriority("testAccountPC_Germany");

        if(priorityFlag) {

            System.out.println("TC4: Create an account of type Platform Channel and Market Germany : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated PC_Germany", accountDomainName, accountTypePC, marketGermany);
                xls.readAndWriteToSheet("testAccountPC_Germany", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountPC_Germany", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountPC_Germany", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountPC_Germany", "Fail");
                Assert.fail("Failed");

            }

            System.out.println("TC4: Create an account of type Platform Channel and Market Germany : Completed");


        }

    }

    // Create a test account of type platform CMR and market type Canada


    @Test
    public void testAccountPCMR_Canada() throws Exception {
        priorityFlag=xls.readPriority("testAccountPCMR_Canada");

        if(priorityFlag) {

            System.out.println("TC5: Create an account of type Platform CMR and Market Canada : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated PCMR_Canada", accountDomainName, accountTypePCMR, marketCanada);
                xls.readAndWriteToSheet("testAccountPCMR_Canada", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountPCMR_Canada", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountPCMR_Canada", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountPCMR_Canada", "Fail");
                Assert.fail("Failed");

            }

            System.out.println("TC5: Create an account of type Platform CMR and Market Canada : Completed");

        }

    }


    // Create a test account of type platform reseller and market type Canada

    @Test
    public void testAccountPR_Canada() throws Exception {
        priorityFlag=xls.readPriority("testAccountPR_Canada");
        if(priorityFlag) {
            System.out.println("TC6: Create an account of type Platform Reseller and Market Canada : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated PR_Canada", accountDomainName, accountTypePR, marketCanada);
                xls.readAndWriteToSheet("testAccountPR_Canada","Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountPR_Canada", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountPR_Canada","Fail");
                Assert.fail("Failed");

            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountPR_Canada","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC6: Create an account of type Platform Reseller and Market Canada  : Completed");


        }

    }

    // Create a test account of type programmatic and market type France


    @Test
    public void testAccountProgrammatic_France() throws Exception {
        priorityFlag=xls.readPriority("testAccountProgrammatic_France");

        if(priorityFlag) {
            System.out.println("TC7: Create an account of type Platform Programmatic and Market France : Started");
            try {
                ac.createAccount_Manual(driver, "Automation generated Programmatic_France", accountDomainName, accountTypeProg, marketFrance);
                xls.readAndWriteToSheet("testAccountProgrammatic_France", "Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountProgrammatic_France", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountProgrammatic_France", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountProgrammatic_France", "Fail");
                Assert.fail("Failed");

            }

            System.out.println("TC7: Create an account of type Programmatic and Market France : Completed");

        }

    }

    @Test
    public void testAccountWithoutADomain() throws Exception {
        priorityFlag=xls.readPriority("testAccountWithoutADomain");

        if(priorityFlag) {
            System.out.println("Create an account without providing advertiser domain name : Started");
            try{
                ac.createAccount_Manual(driver,"New Account without advertiser domain","",accountTypeProg,marketUS);
            }
            catch (TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountWithoutADomain","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountWithoutADomain","Fail");
                Assert.fail("Failed");
            }
            catch(WebDriverException d){
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountWithoutADomain","Fail");
                Assert.fail("Failed");
            }

            System.out.println("Create an account without providing advertiser domain name : Started");
        }


    }

    @Test
    public void deleteAccount() throws Exception {
        String verifyText="Account doesn't exist";
        String actualText;
        priorityFlag=xls.readPriority("deleteAccount");

        if(priorityFlag) {
            System.out.println("TC8: Delete an account : Started");
            try {
                actualText=ac.deleteAccount(driver,"Elite","Automation del");
                if(actualText.equals(verifyText)) {
                    xls.readAndWriteToSheet("deleteAccount", "Pass");
                }
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("deleteAccount", "Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("deleteAccount", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("deleteAccount", "Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC8: Delete an account : Completed");

        }
    }

    @Test
    public void testAccountTimeZonePDT() throws Exception {
        priorityFlag=xls.readPriority("testAccountTimeZone");

        if(priorityFlag) {
            System.out.println("TC9: Test Account Timezone : Started");
            try {
                ac.selectTimezone(driver,"US","Pacific");
                xls.readAndWriteToSheet("testAccountTimeZone","Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountTimeZone","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountTimeZone","Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountTimeZone","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC9: Import Company Contacts : Completed");
        }

    }

    @Test
    public void testAccountAddNewContact() throws Exception {
        priorityFlag=xls.readPriority("testAccountAddNewContact");

        if(priorityFlag) {
            System.out.println("TC10: Test Account Add new contact : Started");
            try {
                ac.createNewUser(driver,email,first_name,last_name,title,phone);
                xls.readAndWriteToSheet("testAccountAddNewContact","Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddNewContact","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddNewContact","Fail");
                Assert.fail("Failed");
            }
            catch(WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddNewContact","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC10: Test Account Add new contact : Completed");
        }

    }

    @Test
    public void testAccountAddExistingUser() throws Exception {
        priorityFlag=xls.readPriority("testAccountAddExistingUser");

        if(priorityFlag) {
            System.out.println("TC10: Test Account Add Existing User : Started");
            try {
                ac.createNewUser(driver,email,first_name,last_name,title,phone);
                xls.readAndWriteToSheet("testAccountAddExistingUser","Pass");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddExistingUser","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddExistingUser","Fail");
                Assert.fail("Failed");
            }
            catch(WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testAccountAddExistingUser","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC10: Test Account Add Existing User : Completed");
        }

    }

    @Test
    public void testImportCompanyContacts() throws Exception {
        priorityFlag=xls.readPriority("testImportCompanyContacts");
        if(priorityFlag) {
            System.out.println("TC11: Import Company Contacts : Started");
            try {
                ac.importCompanyContacts(driver);
                xls.readAndWriteToSheet("testImportCompanyContacts", "Pass");
            }
            catch (TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("testImportCompanyContacts","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("testImportCompanyContacts","Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("testImportCompanyContacts","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC11: Import Company Contacts : Completed");
        }

    }


    @Test
    public void deleteCreatedAccount() throws Exception {
        priorityFlag=xls.readPriority("deleteCreatedAccount");
        if(priorityFlag) {
            System.out.println("TC12: Delete Created Account : Started");
            try {
                ac.deleteCreatedAccount(driver,"company","account");
                xls.readAndWriteToSheet("deleteCreatedAccount","Pass");
            }
            catch (TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("deleteCreatedAccount","Fail");
                Assert.fail("Failed");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("deleteCreatedAccount","Fail");
                Assert.fail("Failed");
            }
            catch(WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("deleteCreatedAccount","Fail");
                Assert.fail("Failed");
            }

        }
    }


}
