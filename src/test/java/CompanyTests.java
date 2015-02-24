package test.java;
/**
 * Created by xAD-inc on 2/17/2015.
 */

import main.java.com.xadcentral.regression.Company;
import main.java.com.xadcentral.regression.ExcelOps;
import main.java.com.xadcentral.regression.ReadConf;
import main.java.com.xadcentral.regression.BasicOps;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by xad on 8/11/14.
 */

public class CompanyTests {
    static BasicOps bigX;
    static Company co;
    static ReadConf conf;

    private ExcelOps xls;
    private Boolean priorityFlag=false;
    private WebDriver driver;
    private String deleteCompanyName;
    private String createCompanyName;
    private String companyNameExistingUser;
    private String editCompanyName;
    private String companyNameEditedTo;

    private String deleteContactEmail;
    private String newContactEmail;
    private String newContactFirstName;
    private String newContactLastName;
    private String newContactTitle;
    private String newContactPhone;
    private String existingUser;


    @BeforeSuite
    public void setupClass() throws Exception {
        xls=new ExcelOps();
        co = new Company();

        conf = new ReadConf();
        conf.readJson();

        deleteCompanyName=conf.getDeleteCompanyName();
        createCompanyName=conf.getCreateCompanyName();
        companyNameExistingUser=conf.getCompanyNameExistingUser();
        editCompanyName=conf.getEditCompanyName();
        companyNameEditedTo=conf.getEditedCompanyName();
        deleteContactEmail=conf.getDeleteContactEmail();
        newContactEmail=conf.getNewContactEmail();
        newContactFirstName=conf.getNewContactFirstName();
        newContactLastName=conf.getNewContactLastName();
        newContactTitle=conf.getNewContactTitle();
        newContactPhone=conf.getNewContactPhone();
        existingUser=conf.getExistingUser();


        bigX=new BasicOps();
        bigX.setup();
        driver = new ChromeDriver();
        bigX.logIn(driver);

        Dimension targetSize = new Dimension(1920, 1080);

        driver.manage().window().setSize(targetSize);

    }

    @AfterSuite
    public void tearDown() throws Exception {
        //co.deleteCreatedCompany(driver,createCompanyName);
        //co.deleteCreatedCompany(driver,companyNameExistingUser);

        bigX.tearDown(driver);
        xls.saveChanges();

    }

    @Test
    public void duplicateCompanyName() throws Exception {
        priorityFlag=xls.readPriority("duplicateCompanyName");


        if(priorityFlag) {
            System.out.println("TC1 : Duplicate Company Name : Started ");
            System.out.println("Automation Test Company - Duplicate Company Names");
            try {
                co.createCompanyExistingUsers(driver, "QA LAX", "keri.culp@xad.com");

            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("duplicateCompanyName", "Pass");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("duplicateCompanyName","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC1 : Duplicate Company Name : Completed");

        }
    }



    @Test
    public void addNewCompanyContact_WithoutEmail() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_WithoutEmail");


        if (priorityFlag) {
            System.out.println("TC2 : Manually add a contact without email : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Without Email", "", "q", "a", "qa", "1234567890");

            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutEmail","Fail");
                Assert.fail("Failed");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutEmail","Fail");
                Assert.fail("Failed");
            }

            catch (WebDriverException d) {
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutEmail","Pass");
                d.printStackTrace();
            }
            System.out.println("TC2 : Manually add a contact with no email : Completed");
        }


    }

    @Test
    public void addNewCompanyContact_WithoutFirstName() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_WithoutFirstName");


        if (priorityFlag) {
            System.out.println("TC3 : Manually add a contact without first name : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Without First Name", "qa", "", "a", "qa", "1234567890");

            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutFirstName","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutFirstName", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutFirstName","Pass");

            }
            System.out.println("TC3 : Manually add a contact without first name : Completed");


        }


    }

    @Test
    public void addNewCompanyContact_WithoutLastName() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_WithoutLastName");

        if (priorityFlag) {
            System.out.println("TC4 : Manually add a contact without last name : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Without Last Name", "qa", "q", "", "qa", "1234567890");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutLastName","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutLastName", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutLastName","Pass");

            }
            System.out.println("TC4 : Manually add a contact without last name : Completed");
        }

    }

    @Test
    public void addNewCompanyContact_WithoutTitle() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_WithoutTitle");

        if (priorityFlag) {
            System.out.println("TC5 : Manually add a contact without title : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Without Title ", "qa", "q", "a", "", "1234567890");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutTitle","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutTitle", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutTitle","Pass");

            }
            System.out.println("TC5 : Manually add a contact without title : Completed");

        }

    }

    @Test
    public void addNewCompanyContact_WithoutPhone() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_WithoutPhone");

        if (priorityFlag) {
            System.out.println("TC6 : Manually add a contact without phone number : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Without Phone", "qa", "q", "a", "qa", "");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutPhone","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutPhone", "Fail");
                Assert.fail("Failed");
            }
            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_WithoutPhone","Pass");

            }
            System.out.println("TC6 : Manually add a contact without phone number : Completed");
        }

    }

    @Test
    public void addNewCompanyContact_DuplicateEmail() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_DuplicateEmail");

        if (priorityFlag) {
            System.out.println("TC7 : Manually add a contact with duplicate email address : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Duplicate Email address", "satmaram", "q", "a", "qa", "1234567890");
            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_DuplicateEmail","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_DuplicateEmail", "Fail");
                Assert.fail("Failed");
            }

            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_DuplicateEmail","Pass");

            }
            System.out.println("TC7 : Manually add a contact with duplicate email address : Completed");
        }


    }

    @Test
    public void addNewCompanyContact_InvalidPhone() throws Exception {
        priorityFlag=xls.readPriority("addNewCompanyContact_InvalidPhone");

        if (priorityFlag) {
            System.out.println("TC8 : Manually add a contact with invalid phone number : Started");
            try {
                co.createCompanyNewUser(driver, "Automation Test Company - New User Invalid Phone number ", "qa", "q", "a", "qa", "12345");

            }
            catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_InvalidPhone","Fail");
                Assert.fail("Failed");
            }
            catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_InvalidPhone", "Fail");
                Assert.fail("Failed");
            }

            catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact_InvalidPhone","Pass");

            }
            System.out.println("TC8 : Manually add a contact with invalid phone number : Completed");
        }

    }


    @Test
    public void deleteCompanyContact() throws Exception {
        priorityFlag=xls.readPriority("deleteCompanyContact");


        if (priorityFlag) {
            System.out.println("TC 9 : Delete an existing contact : Started");
            try {
                co.deleteCompanyContact(driver, "UI", deleteContactEmail);

                xls.readAndWriteToSheet("deleteCompanyContact","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("deleteCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("deleteCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("deleteCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("deleteCompanyContact","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC 9 : Delete an existing contact : Completed");
        }



    }


    @Test
    public void addNewCompanyContact() throws Exception {

        priorityFlag=xls.readPriority("addNewCompanyContact");
        if (priorityFlag) {
            System.out.println("TC10 : Manually add a contact : first name, last name, title, phone number : Started");
            try {
                co.createCompanyNewUser(driver, createCompanyName, newContactEmail, newContactFirstName, newContactLastName, newContactTitle, newContactPhone);
                xls.readAndWriteToSheet("addNewCompanyContact","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addNewCompanyContact","Fail");
                Assert.fail("Failed");

            }
            System.out.println("TC10 : Manually add a contact : first name, last name, title, phone number : Completed");
        }


    }


    @Test
    public void addExistingCompanyContact_blankUserEntries() throws Exception {

        priorityFlag=xls.readPriority("addExistingCompanyContact_blankUserEntries");
        if (priorityFlag) {
            System.out.println("TC11 : Add Existing Contact - Blank Entries in Existing User : Started");
            try {
                co.createCompanyExistingUsers(driver, "Automation Test Company - Blank Entries in Existing User", "");

            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("addExistingCompanyContact_blankUserEntries", "Pass");

            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addExistingCompanyContact_blankUserEntries", "Pass");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addExistingCompanyContact_blankUserEntries", "Pass");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addExistingCompanyContact_blankUserEntries","Pass");

            }
            System.out.println("TC11 : Add Existing Contact - Blank Entries in Existing User: Completed");
        }




    }

    @Test
    public void addExistingUserCompany() throws Exception {

        priorityFlag=xls.readPriority("addExistingUserCompany");
        if (priorityFlag) {
            System.out.println("TC12 :  Add existing contact- Regular Entries : Started");
            try {
                co.createCompanyExistingUsers(driver, companyNameExistingUser, existingUser);
                xls.readAndWriteToSheet("addExistingUserCompany","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("addExistingUserCompany","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("addExistingUserCompany","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("addExistingUserCompany","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("addExistingUserCompany","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC12 :  Add existing contact- Regular Entries : Completed");
        }

    }

    @Test
    public void editCompanyName() throws Exception {

        priorityFlag=xls.readPriority("editCompanyName");
        if (priorityFlag) {
            System.out.println("TC13: Edit Company Name : Started");
            try {
                co.editCompanyName(driver, editCompanyName, companyNameEditedTo);
                xls.readAndWriteToSheet("editCompanyName","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("editCompanyName","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("editCompanyName","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("editCompanyName","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("editCompanyName","Fail");
                Assert.fail("Failed");

            }
            System.out.println("TC13: Edit Company Name : Completed");


        }


    }

    @Test
    public void editCompanyNewContact() throws Exception {

        priorityFlag=xls.readPriority("editCompanyNewContact");

        if (priorityFlag) {
            System.out.println("TC14: Edit Company and add New User : Started");
            try {
                co.editCompanyAddNewContact(driver, "Elite", "c c c", "e", "n", "c", "1234567890");
                xls.readAndWriteToSheet("editCompanyNewContact","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("editCompanyNewContact","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("editCompanyNewContact","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("editCompanyNewContact","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("editCompanyNewContact","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC14: Edit Company and add New User : Completed");

        }


    }


    @Test
    public void editCompanyExistingContact() throws Exception {

        priorityFlag=xls.readPriority("editCompanyExistingContact");

        if (priorityFlag) {
            System.out.println("TC15: Edit Company and add Existing User : Started");
            try {
                co.editCompanyAddExistingContact(driver, "Elite Media (Test Accounts)", "ajay.mishra@xad.com");
                xls.readAndWriteToSheet("editCompanyExistingContact","Pass");
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("editCompanyExistingContact","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("editCompanyExistingContact","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("editCompanyExistingContact","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("editCompanyExistingContact","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC15: Edit Company and add Existing User : Completed");
        }

    }

    @Test
    public void deleteCompany() throws Exception {
        String alert="Company doesn't exist";

        priorityFlag=xls.readPriority("deleteCompany");
        if (priorityFlag) {
            System.out.println("TC16: Delete an existing company : Started");
            try {
                String alertText=co.deleteCompany(driver, deleteCompanyName);
                if(alertText.equals(alert)) {
                    xls.readAndWriteToSheet("deleteCompany", "Pass");
                }
                else{
                    xls.readAndWriteToSheet("deleteCompany", "Fail");
                }
            } catch (ElementNotVisibleException v) {
                v.printStackTrace();
                xls.readAndWriteToSheet("deleteCompany","Fail");
                Assert.fail("Failed");
            } catch (NoSuchElementException n) {
                n.printStackTrace();
                xls.readAndWriteToSheet("deleteCompany","Fail");
                Assert.fail("Failed");
            } catch (org.openqa.selenium.TimeoutException t) {
                t.printStackTrace();
                xls.readAndWriteToSheet("deleteCompany","Fail");
                Assert.fail("Failed");
            } catch (WebDriverException d) {
                d.printStackTrace();
                xls.readAndWriteToSheet("deleteCompany","Fail");
                Assert.fail("Failed");
            }
            System.out.println("TC16: Delete an existing company : Completed");

        }



    }





}


