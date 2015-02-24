package main.java.com.xadcentral.regression;

/**
 * Created by xAD-inc on 2/17/2015.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by xad on 8/8/14.
 */
public class Company {


    private static String stagingHome;
    static ReadConf conf;


    public Company() {
        conf = new ReadConf();
        conf.readJson();

        stagingHome=conf.getHomePage();

    }

    // Go to a particular company

    public WebDriver goToCompany(WebDriver driver, String company) throws InterruptedException {

        System.out.println("Go to Company: Started");
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get(stagingHome);
        // Find search text box and enter company name
        String searchSelector = "keyword";
        WebElement searchSelEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(searchSelector)));
        searchSelEle.sendKeys(company);
        // Hit RETURN after entering company name
        searchSelEle.sendKeys(Keys.RETURN);

        Thread.sleep(3000);
        // From the results, choose the company matching the text
        String companyXPath = "//*[@id=\"workspaces\"]/div/div/div/div[2]/div[2]/div[1]/div/div/div/table/tbody/tr/td[contains(text(), '"+company+"')][1]";
        // Click on that company
        WebElement compEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(companyXPath)));
        compEle.click();
        Thread.sleep(2000);
        System.out.println("Go to Company: Finished");

        return driver;

    }

    // Create a new company with existing user as a company contact

    public void createCompanyExistingUsers(WebDriver driver, String company_name, String existingUser) throws Exception {

        Thread.sleep(2000);
        driver.get(stagingHome);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // Click on Create New Company

        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;

        String company_button="//*[@id=\"workspaces\"]/div/div/div/div[2]/button/span";
        String company_selector="//*[@data-cmd='cmdShowCompanyCreate']";
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(company_selector))).click();

        // Find company name element
        Thread.sleep(2000);
        String company_name_input = "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[2]/div/div/div[2]/input";
        WebElement companyName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(company_name_input)));
        companyName.sendKeys(company_name);

        addExistingUserCompany(driver, existingUser);

        // Create company
        Thread.sleep(2000);
        String create_company="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        String createCompanyPath="//*[@data-action='createCompany']";

        WebElement createCompany=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createCompanyPath)));

        createCompany.click();

        // Click on Create Account to make sure company was created
        String accountPath="//*[@id=\"workspaces\"]/div/div/div/div[2]/div/div[1]/button/span";
        WebElement account= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountPath)));
        account.click();




    }

    // Create a new company and manually add a new user with email, first name, last name, title and phone number

    public void createCompanyNewUser(WebDriver driver, String company_name, String email, String first_name, String last_name, String title, String phone) throws Exception {

        Thread.sleep(3000);
        driver.get(stagingHome);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.navigate().refresh();
        // Click on Create New Company
        Thread.sleep(5000);
        String companyPath="//*[@data-cmd='cmdShowCompanyCreate']";
        // Click on Create New Company
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String company_button="//*[@id=\"workspaces\"]/div/div/div/div[2]/button/span";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(companyPath))).click();

        // Find company name element
        Thread.sleep(3000);
        String company_name_input = "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[2]/div/div/div[2]/input";
        WebElement companyName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(company_name_input)));
        companyName.sendKeys(company_name);

        addNewContactCompany(driver, email, first_name, last_name, title, phone);

        // Create company
        Thread.sleep(3000);
        String create_company="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        String createCompanyPath="//*[@data-action='createCompany']";
        WebElement createCompany=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createCompanyPath)));
        createCompany.click();

        // Click on Create Account to make sure company was created
        String accountPath="//*[@id=\"workspaces\"]/div/div/div/div[2]/div/div[1]/button/span";
        WebElement account= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountPath)));
        account.click();

    }


    // Edit an existing company's name

    public void editCompanyName(WebDriver driver, String company_name , String edited_name) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        System.out.println("Go to company");
        // Go to company
        goToCompany(driver, company_name);
        driver.navigate().refresh();

        // Click on Edit Company
        System.out.println("Click on Edit company");
        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();


        System.out.println("Edit company name");
        // Find company name element
        Thread.sleep(3000);
        String company_name_input = "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[2]/div/div/div[2]/input";
        WebElement companyName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(company_name_input)));
        companyName.clear();
        companyName.sendKeys(edited_name);

        // click on update
        String updatePath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement updateElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updatePath)));
        updateElement.click();


    }
    // Adding an existing user to a company that already exists by editing it

    public void editCompanyAddExistingContact(WebDriver driver, String company_name, String contact) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        System.out.println("Go to company");
        WebDriver driver1=goToCompany(driver, company_name);
        driver1.navigate().refresh();

        // Click on Edit Company
        System.out.println("Click on Edit company");
        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();
        System.out.println("Add Existing User");
        addExistingUserCompany(driver1, contact);

        // click on update
        System.out.println("Click on Update");
        String updatePath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        Thread.sleep(3000);
        WebElement updateElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updatePath)));
        updateElement.click();

    }
    // Add a new contact to an existing company by editing it

    public void editCompanyAddNewContact(WebDriver driver, String company_name, String email, String first_name, String last_name, String title, String phone) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        System.out.println("Go to company");
        goToCompany(driver, company_name);
        driver.navigate().refresh();

        System.out.println("Click on Edit company");
        // Click on Edit Company
        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();

        // Add new contact

        System.out.println("Adding new contact");
        addNewContactCompany(driver, email, first_name, last_name, title, phone);

        // click on update
        System.out.println("Click on Update");
        String updatePath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement updateElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updatePath)));
        updateElement.click();


    }
    // Delete an existing company

    public String deleteCompany(WebDriver driver, String company_name) throws Exception{

        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("http://staging.xadcentral.com:8080/#Home");
        // Go to particular company
        System.out.println("Go to company");
        WebDriver driver1=goToCompany(driver, company_name);

        // Save companyURL to check if company is accessible after deletion
        String companyURL=driver.getCurrentUrl();
        System.out.println(companyURL);

        // Click on Edit Company
        System.out.println("Cick on Edit company");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();

        // Click on Delete Company from the edit page
        System.out.println("Click on Delete Company");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String deletePath=".delete";
        WebElement deleteComp=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(deletePath)));
        deleteComp.click();

        // Confirm action
        System.out.println("Confirm Deletion");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.wait(10);
        String confirmPath="/html/body/div[2]/div[2]/div[3]/div/div/center/button[1]";
        WebElement confirm=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmPath)));
        confirm.click();

        Thread.sleep(3000);
        System.out.println("Try to access delete company by URL");
        driver1.get(companyURL);
        Thread.sleep(3000);
        System.out.println("Verify text displayed on pop up alert");

        // Switch navigation to alert that pops up when trying to access deleted company
        Alert alert=driver1.switchTo().alert();
        String alertText=alert.getText();
        System.out.println(alertText);

        // Verify alert text, "Company doesn't exist"
        alert.accept();
        System.out.println("Click on OK, from pop up alert");
        return alertText;


    }

    // Add a new contact with required fields

    public void addNewContactCompany(WebDriver driver, String email_input, String first_name, String last_name, String title_input, String phone_number ) throws Exception{

        WebDriverWait wait = new WebDriverWait(driver, 30);
        // Click on Add Contact
        Thread.sleep(2000);
        System.out.println("Click on Add Contact");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String add_contact_element="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[3]/div/div/div/div/div/button[1]/span";
        String add_contact_selector="button.green:nth-child(1)";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(add_contact_element))).click();

        // Entering values for required fields
        // Enter email
        Thread.sleep(2000);
        System.out.println("Enter Email");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String email_element=".form-left > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)";
        WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(email_element)));
        email.sendKeys(email_input);

        //Enter first name
        Thread.sleep(3000);
        System.out.println("Enter first name");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String first_name_element=".form-left > div:nth-child(4) > div:nth-child(2) > input:nth-child(1)";
        WebElement firstName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(first_name_element)));
        firstName.sendKeys(first_name);

        // Enter last name
        Thread.sleep(3000);
        System.out.println("Enter last name");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String last_name_element=".form-left > div:nth-child(5) > div:nth-child(2) > input:nth-child(1)";
        WebElement lastName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(last_name_element)));
        lastName.sendKeys(last_name);

        // Enter title
        Thread.sleep(3000);
        System.out.println("Enter title");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String title_element=".form-right > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)";
        WebElement title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(title_element)));
        title.sendKeys(title_input);

        // Enter phone number
        Thread.sleep(3000);
        System.out.println("Enter phone");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String phone_element=".form-right > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)";
        WebElement phone=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(phone_element)));
        phone.sendKeys(phone_number);

        // Create contact
        Thread.sleep(3000);
        System.out.println("Create contact");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String create_contact="div.form-foot:nth-child(4) > div:nth-child(1) > div:nth-child(1) > center:nth-child(1) > button:nth-child(1)";
        String createContactPath="//*[@id=\"bodyDiv\"]/div[5]/div/div[4]/div/div/center/button[1]";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(create_contact))).click();

    }

    // Add company contact from existing users

    public void addExistingUserCompany(WebDriver driver, String userName) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        // Click on Add Existing User
        String existing_user="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[3]/div/div/div/div/div/button[2]/span";
        Thread.sleep(2000);
        System.out.println("Click on Add existing user");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(existing_user))).click();

        //Select from list of existing users
        Thread.sleep(2000);
        System.out.println("Select from list of existing users");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String existingEntries="//*[@id=\"bodyDiv\"]/div[6]/div[1]/select";
        Select existingUserSelect=new Select(driver.findElement(By.xpath(existingEntries)));
        Thread.sleep(2000);

        existingUserSelect.selectByVisibleText(userName);
        Thread.sleep(2000);

        // Testing for blank user email entries that previously existed in the database

        System.out.println("If user name is blank, click cancel after trying to find blank user entries ");
        if(userName=="") {
            driver.findElement(By.xpath("//*[@id=\"bodyDiv\"]/div[6]/div[2]/div/div/center/button[2]")).click();

        }
        // click on add contact
        System.out.println("Add contact");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String add_contact="//*[@id=\"bodyDiv\"]/div[6]/div[2]/div/div/center/button[1]";
        WebElement addContact=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(add_contact)));
        addContact.click();

    }

    // Delete a company's contact

    public void deleteCompanyContact(WebDriver driver, String company, String contactEmail) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        //driver.get("http://staging.xadcentral.com:8080/#Home");
        System.out.println("Go to Company");
        WebDriver driver1=goToCompany(driver,company);

        // Click on Edit Company
        System.out.println("Edit Company");
        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();

        //  Look for the contact to be deleted
        System.out.println("Find contact to be deleted");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String contactPath= "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[3]/div/div/div/div/table/tbody/tr/td[1][contains(text(),'" + contactEmail + "')][1]";
        String contact="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[3]/div/div/div/div/table/tbody/tr[3]/td[1]/span";
        WebElement deletedContact=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(contact)));
        deletedContact.click();

        // Click on delete contact
        System.out.println("Deleting the contact");
        Thread.sleep(2000);
        // driver1.manage().timeouts().implicitlyWait(10, SECONDS);
        String deleteContactPath="//*[@id=\"bodyDiv\"]/div[5]/div/div[4]/div/div/center/button[2]";
        WebElement deleteContact=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteContactPath)));
        deleteContact.click();

        Thread.sleep(2000);
        System.out.println("Confirm deletion of contact");
        // Confirm action
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/div/center/button[1]"))).click();

        // click on update
        Thread.sleep(2000);
        System.out.println("Update changes to company");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String updatePath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement updateElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updatePath)));
        updateElement.click();



    }

    // Deleted companies created for testing

    public void deleteCreatedCompany(WebDriver driver, String companyName ) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //driver.get("http://staging.xadcentral.com:8080/#Home");
        conf.getHomePage();
        System.out.println("Go to Company");
        WebDriver driver1=goToCompany(driver,companyName);

        // Click on Edit Company
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Edit Company");
        String editPath=".edit";
        WebElement compEdit=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editPath)));
        compEdit.click();

        // Click on Delete Company from the edit page
        System.out.println("Click on Delete Company");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String deletePath=".delete";
        WebElement deleteComp=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(deletePath)));
        deleteComp.click();

        // Confirm action
        System.out.println("Confirm Deletion");
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String confirmPath="/html/body/div[2]/div[2]/div[3]/div/div/center/button[1]";
        WebElement confirm=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmPath)));
        confirm.click();

    }


}


