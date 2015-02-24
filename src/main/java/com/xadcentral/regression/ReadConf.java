package main.java.com.xadcentral.regression;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


/**
 * Created by xAD-inc on 2/17/2015.
 */
public class ReadConf {

    private static String config;
    private static String stagingLogin;
    private static String priority;
    private static String stagingHome;
    private static String excelFilePath;
    private static String username;
    private static String password;
    private static String invalidUsername;
    private static String invalidPassword;
    private static String chromeDriverPath;

    private static String deleteCompanyName;
    private static String createCompanyName;
    private static String companyNameExistingUser;
    private static String editCompanyName;
    private static String companyNameEditedTo;

    private static String deleteContactEmail;
    private static String newContactEmail;
    private static String newContactFirstName;
    private static String newContactLastName;
    private static String newContactTitle;
    private static String newContactPhone;
    private static String existingUser;

    private static String accountName;
    private static String marketUS;
    private static String marketUK;
    private static String marketCanada;
    private static String marketIndia;
    private static String marketGermany;
    private static String marketFrance;

    private static String accountTypeDA;
    private static String accountTypeDC;
    private static String accountTypeDP;
    private static String accountTypePC;
    private static String accountTypePR;
    private static String accountTypePCMR;
    private static String accountTypeProg;

    private static String billingSource;

    private static String aDomainName;
    private static String createAccountName;
    private static String accountExistingUser;
    private static String accountNewContactFirstName;
    private static String accountNewContactLastName;
    private static String accountNewContactEmail;
    private static String accountNewContactTitle;
    private static String accountNewContactPhone;

    private static String createCampaignName;
    private static String campaignBudget;
    private static String paddingAmount;
    private static String campaignStartDate;
    private static String campaignEndDate;
    private static String primaryCategory;
    private static String secondaryCategory;
    private static String salesAgent;
    private static String accountManager;
    private static String opsRep;
    private static String salesforceNumber;
    private static String campaignADomain;
    private static String budgetPadding;


    public static void main (String[] args) {
        readJson();
    }

    public static void readJson() {
        try {
            Scanner in = new Scanner(new FileReader("src/config/configuration.json"));
            String json = "";
            String line = "";

            while (in.hasNextLine()) {
                line = in.nextLine();
                line = line.trim();
                json = json + line;
            }
            in.close();
            config = json;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        parseJson();
    }

    public static void parseJson() {

        JSONObject json = (JSONObject) JSONSerializer.toJSON(config);
        JSONArray serviceData = json.getJSONArray("general");
        JSONObject data = serviceData.getJSONObject(0);

        JSONArray companyData=json.getJSONArray("company");
        JSONObject company = companyData.getJSONObject(0);

        JSONArray accountData=json.getJSONArray("account");
        JSONObject account=accountData.getJSONObject(0);

        // Parse values for general

        setPriority(data.getString("priority"));
        setLogInPage(data.getString("stagingLogin"));
        setHomePage(data.getString("stagingHome"));
        setExcelFilePath(data.getString("excelFilePath"));
        setUsername(data.getString("username"));
        setPassword(data.getString("password"));
        setInvalidUsername("invalidUsername");
        setInvalidPassword("invalidPassword");
        setChromeDriverPath(data.getString("chromeDriverPath"));

        // Parse values for company

        setDeleteCompanyName(company.getString("deleteCompanyName"));
        setEditCompanyName(company.getString("editCompanyName"));
        setEditedCompanyName(company.getString("companyNameEditedTo"));
        setCreateCompanyName(company.getString("createCompanyName"));
        setCompanyNameExistingUser(company.getString("companyNameExistingUser"));
        setDeleteContactEmail(company.getString("deleteContactEmail"));
        setNewContactEmail(company.getString("newContactEmail"));
        setNewContactFirstName(company.getString("newContactFirstName"));
        setNewContactLastName(company.getString("newContactLastName"));
        setNewContactTitle(company.getString("newContactTitle"));
        setNewContactPhone(company.getString("newContactPhone"));
        setExistingUser(company.getString("existingUser"));

        // Parse values for account
        setAccountName(account.getString("createAccountName"));
        setAccountTypeDA(account.getString("accountTypeDA"));
        setAccountTypeDC(account.getString("accountTypeDC"));
        setAccountTypeDP(account.getString("accountTypeDP"));
        setAccountTypePC(account.getString("accountTypePC"));
        setAccountTypePCMR(account.getString("accountTypePR"));
        setAccountTypePR(account.getString("accountTypePCMR"));
        setAccountTypeProg(account.getString("accountTypeProg"));

        setMarketUS(account.getString("marketUS"));
        setMarketUK(account.getString("marketUK"));
        setMarketIndia(account.getString("marketIndia"));
        setMarketCanada(account.getString("marketCanada"));
        setMarketGermany(account.getString("marketGermany"));
        setMarketFrance(account.getString("marketFrance"));

        setBillingSource(account.getString("billingSource"));

        setDomainName(account.getString("aDomainName"));

        // Parse values for campaign
        setCreateCampaignName("create camp");
        setCampaignBudget("19");
        setCampaignADomain("yahoo.com");
        setCampaignStartDate("2014-12-12");
        setCampaignEndDate("2015-04-04");
        setBudgetPadding("1");
        setPrimaryCategory("Art");
        setSecondaryCategory("Banks");
        setSalesAgent("satmaram");
        setAccountManager("satmaram");
        setOpsRep("satmaram");

    }
    // General config Details

    public String getLogInPage() {
        return stagingLogin;
    }
    private static void setLogInPage (String loginURL) {
        stagingLogin = loginURL;
    }

    public String getHomePage() { return stagingHome; }
    private static void setHomePage (String homePage) { stagingHome = homePage; }

    public String getExcelFilePath() { return excelFilePath ;}
    private static void setExcelFilePath (String filePath) { excelFilePath=filePath; }

    public String getUsername() { return username; }
    private static void setUsername(String username1) { username= username1; }

    public String getInvalidUsername() {return invalidUsername; }
    private static void setInvalidUsername(String invalidUsername1) {invalidUsername=invalidUsername1; }

    public String getInvalidPassword() { return invalidPassword; }
    private static void setInvalidPassword(String invalidPassword1) {invalidPassword=invalidPassword1; }

    public String getPassword() { return password; }
    private static void setPassword(String password1) { password= password1; }

    public String getChromeDriverPath() {return chromeDriverPath; }
    private static void setChromeDriverPath(String driverPath ) { chromeDriverPath=driverPath; }

    public String getPriority() {
        return priority;
    }
    private static void setPriority(String priority1) {
        priority=priority1;
    }

    // Company config Details

    public String getDeleteCompanyName() { return deleteCompanyName; }
    private static void setDeleteCompanyName(String deleteCompany) { deleteCompanyName=deleteCompany; }

    public String getEditCompanyName() { return editCompanyName; }
    private static void setEditCompanyName(String editCompany) { editCompanyName=editCompany; }

    public String getEditedCompanyName() { return companyNameEditedTo; }
    private static void setEditedCompanyName(String editedName) {companyNameEditedTo=editedName; }

    public String getCreateCompanyName() { return createCompanyName; }
    private static void setCreateCompanyName(String createCompany) { createCompanyName=createCompany; }

    public String getCompanyNameExistingUser() { return companyNameExistingUser; }
    private static void setCompanyNameExistingUser(String companyName) { companyNameExistingUser=companyName; }

    public String getDeleteContactEmail() { return deleteContactEmail; }
    private static void setDeleteContactEmail(String deleteContact) { deleteContactEmail=deleteContact; }

    public String getNewContactEmail() { return newContactEmail; }
    private static void setNewContactEmail(String newEmail) { newContactEmail=newEmail; }

    public String getNewContactFirstName() { return newContactFirstName; }
    private static void setNewContactFirstName(String newFirstName) { newContactFirstName=newFirstName; }

    public String getNewContactLastName() { return newContactLastName; }
    private static void setNewContactLastName(String newLastName) { newContactLastName=newLastName; }

    public String getNewContactTitle() { return newContactTitle; }
    private static void setNewContactTitle(String newTitle) { newContactTitle=newTitle; }

    public String getNewContactPhone() { return newContactPhone; }
    private static void setNewContactPhone(String newPhone) { newContactPhone=newPhone; }

    public String getExistingUser() { return existingUser; }
    private static void setExistingUser(String existingUser1) { existingUser=existingUser1; }

    // Account config details

    public String getAccountName() { return accountName; }
    private static void setAccountName(String accountName1) { accountName=accountName1; }

    public String getAccountTypeDA() { return accountTypeDA; }
    private static void setAccountTypeDA(String accountTypeDA1) { accountTypeDA=accountTypeDA1; }

    public String getAccountTypeDC() { return accountTypeDC; }
    private static void setAccountTypeDC(String accountTypeDC1) { accountTypeDC=accountTypeDC1; }

    public String getAccountTypeDP() { return accountTypeDP; }
    private static void setAccountTypeDP(String accountTypeDP1) {accountTypeDP=accountTypeDP1; }

    public String getAccountTypePC() { return accountTypePC; }
    private static void setAccountTypePC(String accountTypePC1) { accountTypePC=accountTypePC1; }

    public String getAccountTypePR() { return  accountTypePR; }
    private static void setAccountTypePR(String accountTypePR1) { accountTypePR=accountTypePR1; }

    public String getAccountTypePCMR() {return accountTypePCMR; }
    private static void setAccountTypePCMR(String accountTypePCMR1) { accountTypePCMR=accountTypePCMR1; }

    public String getAccountTypeProg() { return accountTypeProg; }
    private static void setAccountTypeProg(String accountTypeProg1) { accountTypeProg=accountTypeProg1; }

    public String getMarketUS() { return marketUS; }
    private static void setMarketUS(String marketUS1) { marketUS=marketUS1; }

    public String getMarketUK() { return marketUK; }
    private static void setMarketUK(String marketUK1) { marketUK=marketUK1; }

    public String getMarketCanada() { return marketCanada; }
    private static void setMarketCanada(String marketCanada1) {marketCanada=marketCanada1; }

    public String getMarketIndia() { return marketIndia; }
    private static void setMarketIndia(String marketIndia1) {marketIndia=marketIndia1; }

    public String getMarketGermany() { return marketGermany; }
    private static void setMarketGermany(String marketGermany1) {marketGermany=marketGermany1; }

    public String getMarketFrance() { return marketFrance; }
    private static void setMarketFrance(String marketFrance1) { marketFrance=marketFrance1; }


    public String getBillingSource() { return billingSource; }
    private static void setBillingSource(String billingSource1) { billingSource=billingSource1;
    }

    public String getDomainName() { return aDomainName; }
    private static void setDomainName(String aDomainName1) { aDomainName=aDomainName1; }

    public String getAccountExistingUser() { return accountExistingUser; }
    private static void setAccountExistingUser(String accountExistingUser1 ) { accountExistingUser=accountExistingUser1; }

    public String getAccountNewContactFirstName() { return accountNewContactFirstName; }
    private static void setAccountNewContactFirstName(String accountNewContactFirstName1) { accountNewContactFirstName=accountNewContactFirstName1; }

    public String getAccountNewContactLastName() { return accountNewContactLastName; }
    private static void setAccountNewContactLastName(String accountNewContactLastName1) { accountNewContactLastName=accountNewContactLastName1; }

    public String getAccountNewContactEmail() { return  accountNewContactEmail; }
    private static void setAccountNewContactEmail(String accountNewContactEmail1) { accountNewContactEmail=accountNewContactEmail1; }

    public String getAccountNewContactTitle() { return accountNewContactTitle; }
    private static void setAccountNewContactTitle(String accountNewContactTitle1) { accountNewContactTitle=accountNewContactTitle1; }

    public String getAccountNewContactPhone() { return accountNewContactPhone; }
    private static void setAccountNewContactPhone(String accountNewContactPhone1) {accountNewContactPhone=accountNewContactPhone1; }

    // Campaign config details

    public String getCreateCampaignName() { return createCampaignName; }
    private static void setCreateCampaignName(String createCampaignName1) { createCampaignName=createCampaignName1; }

    public String getCampaignBudget() { return campaignBudget; }
    private static void setCampaignBudget(String campaignBudget1) { campaignBudget=campaignBudget1; }

    public String getPaddingAmount() { return paddingAmount; }
    private static void setPaddingAmount(String paddingAmount1) { paddingAmount=paddingAmount1; }

    public String getCampaignStartDate() { return campaignStartDate; }
    private static void setCampaignStartDate(String campaignStartDate1) { campaignStartDate=campaignStartDate1; }

    public  String getCampaignEndDate() { return campaignEndDate; }
    private static void setCampaignEndDate(String campaignEndDate1) { campaignEndDate=campaignEndDate1; }

    public String getPrimaryCategory() { return primaryCategory; }
    private static void setPrimaryCategory(String primaryCategory1) { primaryCategory=primaryCategory1; }

    public String getSecondaryCategory() { return secondaryCategory; }
    private static void setSecondaryCategory(String secondaryCategory1) { secondaryCategory=secondaryCategory1; }

    public String getSalesAgent() { return salesAgent; }
    private static void setSalesAgent(String salesAgent1) { salesAgent=salesAgent1; }

    public String getAccountManager() { return accountManager; }
    private static void setAccountManager(String accountManager1) { accountManager=accountManager1; }

    public String getOpsRep() { return opsRep; }
    private static void setOpsRep(String opsRep1) { opsRep=opsRep1; }

    public String getSalesforceNumber() { return salesforceNumber; }
    private static void setSalesforceNumber(String salesforceNumber1) { salesforceNumber=salesforceNumber1; }

    public String getCampaignADomain() { return campaignADomain; }
    private static void setCampaignADomain(String campaignADomain1) { campaignADomain = campaignADomain1; }

    public String getBudgetPadding() { return budgetPadding; }
    private static void setBudgetPadding(String budgetPadding1) { budgetPadding=budgetPadding1; }



}