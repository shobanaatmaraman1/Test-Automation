package main.java.com.xadcentral.regression;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xAD-inc on 2/18/2015.
 */
public class Campaign {
    static ReadConf conf;
    static Company co;
    static Account ac;
    String accountURL;
    private static String startDate;
    private static String endDate="";
    private static String budgetAmount="12";
    private static String primaryCategory="Testing";
    private static String sales="";
    private static String accountMgr="";
    private static String ops="";

    public Campaign(){
        startDate="2015-02-23";
        endDate="2015-03-30";

        conf=new ReadConf();
        co=new Company();
        ac=new Account();
    }

    public void createCampaign(WebDriver driver,String account) throws Exception{
        WebDriverWait wait=new WebDriverWait(driver,10);
        ac.goToAccountDirectly(driver, "da manual");

        String createCampaignPath="//*[@id=\"workspaces\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/button";
        WebElement createCampaign=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createCampaignPath)));
        createCampaign.click();

        String campaignNamePath="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[1]/div[2]/input";
        WebElement campaignNameElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(campaignNamePath)));
        campaignNameElement.sendKeys("NEW NEW");

        // Enter start and end dates
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dates(startDate, endDate);

        // Enter Campaign Budget
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String budget_input="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[2]/div[2]/input";
        WebElement budget=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(budget_input)));
        budget.sendKeys(budgetAmount);

        // if user wants to select ASAP pacing

        // Enter primary category
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        setPrimaryCategory(primaryCategory);


        // Enter sales agent name
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        selectSalesAgent(sales);

        // Enter account manager name
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        selectAccountManager(accountMgr);

        // Enter ops rep name
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        selectOpsRep(ops);

        // Create Campaign
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String create_campaign="//*[@id=\"workspaces\"]/div/div/div/div[4]/center/button[1]";
        WebElement finishCampaign=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(create_campaign)));
        finishCampaign.click();

    }

    // Enter start and end dates

    public void dates(WebDriver driver,String startDate, String endDate) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 120);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String startDatePicker=".field+ .cmc-field .hasDatepicker";
        String endDatePicker=".cmc-field+ .cmc-field .hasDatepicker";

        WebElement startDateElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(startDatePicker)));
        startDateElement.clear();
        startDateElement.sendKeys(startDate);
        WebElement endDateElement=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(endDatePicker)));
        endDateElement.clear();
        endDateElement.sendKeys(endDate);

    }

    // Choose ASAP Pacing instead of the default EVEN pacing

    public void asapPacing(WebDriver driver) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 120);
        // ASAP mode
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        String asapModePath = "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[4]/div[2]/div[2]/input";
        WebElement asapElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(asapModePath)));
        asapElement.click();

    }

    // Choose primary category ( autocomplete input)

    public void setPrimaryCategory(WebDriver driver, String primary_category) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 120);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String primary_category_path= "//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[1]/div[5]/div[2]/input[2]";
        WebElement category = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(primary_category_path)));
        // Enter primary category
        category.sendKeys(primary_category);
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> listItems = driver.findElements(By.cssSelector("#ui-id-1"));
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        listItems.get(0).click();
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    //Select xAd Sales Agent from Dropdown

    public void selectSalesAgent(WebDriver driver, String sales_agent_name) throws Exception{
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String sales_agent_entries="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[2]/div[1]/div[2]/select";
        Select salesSelect=new Select(driver.findElement(By.xpath(sales_agent_entries)));
        salesSelect.selectByVisibleText(sales_agent_name);

    }

    // Select xAd Account Manager from Dropdown

    public void selectAccountManager(WebDriver driver, String account_manager_name) throws Exception{
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String account_manager_entries="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[2]/div[2]/div[2]/select";
        Select accountSelect=new Select(driver.findElement(By.xpath(account_manager_entries)));
        accountSelect.selectByVisibleText(account_manager_name);

    }
    // Select xAd Ops Rep from Dropdown

    public void selectOpsRep(WebDriver driver, String opsRep_name) throws Exception{
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String ad_ops_entries="//*[@id=\"workspaces\"]/div/div/div/div[3]/div/div/table/tbody/tr/td[2]/div[3]/div[2]/select";
        Select adOpsSelect=new Select(driver.findElement(By.xpath(ad_ops_entries)));
        adOpsSelect.selectByVisibleText(opsRep_name);


    }

    }


}
