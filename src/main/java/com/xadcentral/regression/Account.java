package main.java.com.xadcentral.regression;

/**
 * Created by xAD-inc on 2/17/2015.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Created by xad on 8/8/14.
 */
public class Account  {

    static ReadConf conf;
    static Company co;
    String accountURL;

    public Account(){
        conf=new ReadConf();
        co=new Company();

    }

    public void goToAccount(WebDriver driver, String company, String account) throws Exception {
        WebDriverWait wait=new WebDriverWait(driver,10);
        co.goToCompany(driver, company);
        String accountXPath = "//*[@id=\"workspaces\"]/div/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[contains(text(),'" + account + "')][1]";
        WebElement accountEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountXPath)));
        accountEle.click();
    }

    public void goToAccountDirectly(WebDriver driver,String account) {
        WebDriverWait wait=new WebDriverWait(driver,10);
        String searchSelector = "keyword";
        WebElement searchSelEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(searchSelector)));
        searchSelEle.sendKeys(account);
        // Hit RETURN after entering company name
        searchSelEle.sendKeys(Keys.RETURN);

        String accountXPath = "//*[@id=\"workspaces\"]/div/div/div/div[2]/div[2]/div[1]/div/div/div/table/tbody/tr/td[contains(text(), '"+account+"')][1]";
        // Click on that company
        WebElement compEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountXPath)));
        compEle.click();

    }

    public void createAccount_Manual(WebDriver driver, String account_name, String domainName , String accountType , String marketType) throws Exception{
        WebDriverWait wait=new WebDriverWait(driver,10);
        // Click on Create Account
        WebDriver driver1=co.goToCompany(driver,"Elite (test 2)");
        String account_element="//*[@id=\"workspaces\"]/div/div/div/div[2]/div/div[1]/button/span";
        WebElement createAccount=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(account_element)));
        createAccount.click();

        // Enter Account name
        String account_name_input="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[1]/div[2]/input";
        WebElement accountName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(account_name_input)));
        accountName.sendKeys(account_name);

        // Enter domain name
        String ad_domain_input="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[4]/div[2]/input";
        WebElement aDomain=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ad_domain_input)));
        aDomain.sendKeys(domainName);

        // Enter Account Type
        selectAccountType(driver, accountType);

        // Enter Market Type

        selectMarket(driver, marketType);

        addBillingSource(driver,conf.getBillingSource());

        // Click on create account
        accountURL=driver.getCurrentUrl();

        String create_account_element="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement accCreate=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(create_account_element)));
        accCreate.click();

    }

    // Select Account Type - manual or feed - uploads for later

    public void selectAccountType(WebDriver driver, String accountType){
        String accountTypePath= "//*[@data-propname='acType']";
        Select accountTypeSelector=new Select(driver.findElement(By.xpath(accountTypePath)));
        accountTypeSelector.selectByVisibleText(accountType);

    }

    // choose from  6 market types ( US, UK, India, Canada, France, Germany)

    public void selectMarket(WebDriver driver, String marketType){

        String marketPath="//*[@data-propname='market']";
        Select marketTypeSelector=new Select(driver.findElement(By.xpath(marketPath)));
        marketTypeSelector.selectByVisibleText(marketType);
    }

    // Choose timezone(selections available for US and Canada
    public void selectTimezone(WebDriver driver, String country, String timezone) {
        String  timezonePath="//*[@data-propname='timezone']";
        Select timezoneSelector=new Select(driver.findElement(By.xpath(timezonePath)));
        timezoneSelector.selectByVisibleText(timezone);
    }

    // Select from Search or display ads
    public void adType(WebDriver driver, String adTypes ) throws Exception{
        String adTypePath="//*[@id=\"workspaces\"]/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[1]/div[4]/div[2]/select";
        Select adTypeSelection=new Select(driver.findElement(By.xpath(adTypePath)));
        adTypeSelection.selectByVisibleText(adTypes);

    }

    // Choose from existing users
    public void addExistingUser(WebDriver driver, String existingUser){
        String existingUserPath="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/div/div/div/div/div/button[2]/span";
        Select existingUserSelection=new Select(driver.findElement(By.xpath(existingUserPath)));
        existingUserSelection.selectByVisibleText(existingUser);

    }

    public void editAccount(WebDriver driver, String companyName,String accountName) throws Exception{
        WebDriverWait wait=new WebDriverWait(driver,10);
        // Go to account
        goToAccount(driver,companyName,accountName);
        // Go to Edit Account
        String accountEditPath="//*[@id=\"workspaces\"]/div/div/div/div[1]/div/div[2]/span/button";
        String accountEditSelector=".edit";
        WebElement editAccountElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountEditPath)));
        editAccountElement.click();


    }

    // Manually add a new user at the account level(with required fields email, first name, last name, title, phone number)

    public void createNewUser( WebDriver driver, String email_input, String first_name, String last_name, String title, String phone_number) throws Exception {
        WebDriverWait wait=new WebDriverWait(driver,10);

        // Edit account
        editAccount(driver,conf.getCreateCompanyName(),conf.getAccountName());
        // Click on Add Contact
        System.out.println("Click on Add Contact");

        String add_contact_element="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div[3]/div/div/div/div/div/button[1]/span";
        String add_contact_selector="button.green:nth-child(1)";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(add_contact_element))).click();

        // Enter email
        System.out.println("Enter Email");
        String email_element=".form-left > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)";
        WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(email_element)));
        email.sendKeys(email_input);

        //Enter first name
        System.out.println("Enter first name");
        String first_name_element=".form-left > div:nth-child(4) > div:nth-child(2) > input:nth-child(1)";
        WebElement firstName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(first_name_element)));
        firstName.sendKeys(first_name);

        // Enter last name
        System.out.println("Enter last name");
        String last_name_element=".form-left > div:nth-child(5) > div:nth-child(2) > input:nth-child(1)";
        WebElement lastName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(last_name_element)));
        lastName.sendKeys(last_name);

        // Enter title
        System.out.println("Enter title");
        String title_element=".form-right > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)";
        WebElement titleElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(title_element)));
        titleElement.sendKeys(title);

        // Enter phone number
        System.out.println("Enter phone");
        String phone_element=".form-right > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)";
        WebElement phone=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(phone_element)));
        phone.sendKeys(phone_number);

        // Click on Create contact
        System.out.println("Create contact");
        String create_contact="div.form-foot:nth-child(4) > div:nth-child(1) > div:nth-child(1) > center:nth-child(1) > button:nth-child(1)";
        String createContactPath="//*[@id=\"bodyDiv\"]/div[5]/div/div[4]/div/div/center/button[1]";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(create_contact))).click();

    }

    // Import Contacts from Company to Account
    public void importCompanyContacts(WebDriver driver){
        WebDriverWait wait=new WebDriverWait(driver,10);
        String importPath="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/div/div/div/div/div/button[3]";
        WebElement importElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(importPath)));

        importElement.click();
    }

    public void deleteAccountContact(WebDriver driver) throws Exception {
        // Click on contact to be deleted
        WebDriverWait wait=new WebDriverWait(driver,10);
        //
        String deleteContactPath="";
        WebElement deleteContactElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteContactPath)));
        deleteContactElement.click();

        // Click on delete
        String deletePath="//*[@id=\"bodyDiv\"]/div[5]/div/div[4]/div/div/center/button[2]";
        WebElement deleteElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deletePath)));
        deleteElement.click();

        // Confirm Deletion
        String deleteConfirmPath="/html/body/div[3]/div[2]/div[3]/div/div/center/button[1]";
        WebElement deleteConfirmElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteConfirmPath)));
        deleteConfirmElement.click();

    }

    public void addBillingSource(WebDriver driver, String billingAccount) throws Exception{
        // Click on Add billing source

        WebDriverWait wait=new WebDriverWait(driver,10);
        String addBillingSourcePath="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/div[2]/div/div/div/div[1]/button/span";
        WebElement addBillingSourceElement= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addBillingSourcePath)));
        addBillingSourceElement.click();

        // Fetch list of billing accounts
        // Currently the base billing source can only be DART

        String fetchPath="//*[@id=\"bodyDiv\"]/div[7]/div/div[2]/div[1]/div[2]/button";
        WebElement fetchElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(fetchPath)));
        fetchElement.click();

        // Wait for list to be fetched
        Thread.sleep(7000);

        // Select billing account from list
        String billingAccountSelectPath="//*[@id=\"bodyDiv\"]/div[7]/div/div[2]/div[5]/div[2]/select";
        Select billingAccountSelector=new Select(driver.findElement(By.xpath(billingAccountSelectPath)));
        billingAccountSelector.selectByVisibleText(billingAccount);

        // Add billing account
        String billingSource="//*[@id=\"bodyDiv\"]/div[7]/div/div[3]/div/div/center/button[1]";
        WebElement billingSourceElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(billingSource)));
        billingSourceElement.click();


    }

    /*
        Make changes to an account, and then click update
     */

    public void updateAccount(WebDriver driver) {
        WebDriverWait wait=new WebDriverWait(driver,10);
        String updateAccountPath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement updateAccount=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updateAccountPath)));
        updateAccount.click();
    }


    // Delete a particular account by going to the company, and then going to the account

    public String deleteAccount(WebDriver driver, String company_name, String account_name) throws Exception{
        WebDriverWait wait=new WebDriverWait(driver,10);
        String accountURL,alertText;

        // Go to company and then go to account
        accountURL=driver.getCurrentUrl();

        editAccount(driver,company_name,account_name);
        // Click on Delete
        String deleteAccountPath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[3]";
        WebElement deleteAccountElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteAccountPath)));
        deleteAccountElement.click();
        // Click on OK
        String confirmPath="/html/body/div[2]/div[2]/div[3]/div/div/center/button[1]";
        WebElement confirmElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmPath)));
        confirmElement.click();
        Thread.sleep(2000);
        // Check if account got deleted
        driver.get(accountURL);
        Thread.sleep(2000);
        // Verify that 'account doesn't exist' alert comes up, and switch navigation to alert
        Alert alert = driver.switchTo().alert();
        alertText=alert.getText();
        // Accept the alert by clicking OK in the dialog box
        alert.accept();
        return alertText;
    }

    public String deleteCreatedAccount(WebDriver driver,String companyName,String accountName) throws Exception {

        WebDriverWait wait=new WebDriverWait(driver,10);
        String alertText;
        //accountURL=driver.getCurrentUrl();
        // Go to company and then go to edit particular account
        driver.get(accountURL);
        editAccount(driver,companyName,accountName);
        // Click on Delete
        String deleteAccountPath="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[3]";
        WebElement deleteAccountElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteAccountPath)));
        deleteAccountElement.click();
        // Click on OK
        String confirmPath="/html/body/div[2]/div[2]/div[3]/div/div/center/button[1]";
        WebElement confirmElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmPath)));
        confirmElement.click();
        Thread.sleep(2000);
        // Check if account got deleted
        driver.get(accountURL);
        Thread.sleep(2000);
        // Check for 'account doesn't exist' alert, and switch navigation to alert
        Alert alert=driver.switchTo().alert();

        alertText=alert.getText();
        // Accept the alert by clicking OK in the dialog box
        alert.accept();
        editAccount(driver,"","");
        return alertText;

    }

}