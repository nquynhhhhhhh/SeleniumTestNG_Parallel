package com.nhuquynh.Bai28_Excel_File_Data.pages;

import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomerPage extends BasePage {


    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");
    private By tabCustomerDetails = By.xpath("//a[normalize-space()='Customer Details']/preceding::hi4");

    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVAT = By.xpath("//input[@id='vat']");
    private By inputPhoneNumber = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroup = By.xpath("//button[@data-id='groups_in[]']");
    private By labelGroup = By.xpath("//label[normalize-space()='Groups']");
    private By addGroup = By.xpath("//div[@class='input-group-btn']");
    private By inputSearchGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By itemVIP = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//span[normalize-space()='VIP']");
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By inputSearchCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//input");
    private By notFoundCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//li[@class='no-results']");
    private By itemUSD = By.xpath("//button[@data-id='default_currency']/following-sibling::div//span[contains(normalize-space(),'USD')]");
    private By dropdownLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemVietnamese = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By itemVietnamCountry = By.xpath("//button[@data-id='country']/following-sibling::div//span[normalize-space()='Vietnam']");
    private By buttonSaveAndCreate = By.xpath("//button[normalize-space()='Save and create contact']");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By errorCompany = By.xpath("//p[@id='company-error']");
    private By itemCustomerFirst = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a");
//    private By itemCustomerYen = By.xpath("//table[@id='clients']/tbody/tr/td/a[normalize-space()='Yên']");
    private By headerCustomerDetailPage = By.xpath("//h4[normalize-space()='Profile']");
    //4 total in customer list
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");


    public void verifyNavigateToCustomerPage(){
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerPage),"The customer header page not dissplay.");
        Assert.assertEquals(WebUI.getElementText(headerCustomerPage),"Customers Summary", "The cusomer header page not match");
    }

    public void clickButtonAddNewCustomer(){
        WebUI.clickElement(buttonAddNewCustomer);
    }

    public void submitDataForNewCustomer(int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Customer");


        WebUI.setText(inputCompany, excelHelper.getCellData("Customer_Name",row));
        WebUI.setText(inputVAT, excelHelper.getCellData("VAT",row));
        WebUI.setText(inputPhoneNumber, excelHelper.getCellData("Phone",row));
        WebUI.setText(inputWebsite,excelHelper.getCellData("Website",row));

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = DriverManager.getDriver().findElement(labelGroup);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        WebUI.clickElement(dropdownGroup);
        WebUI.setText(inputSearchGroup,"VIP");
        WebUI.clickElement(itemVIP);
        WebUI.clickElement(dropdownGroup);

        WebUI.sleep(1);
        WebUI.clickElement(dropdownLanguage);
        WebUI.clickElement(itemVietnamese);
        WebUI.clickElement(dropdownLanguage);

        WebUI.sleep(1);
        WebUI.setText(inputAddress,"HCM");
        WebUI.setText(inputCity,"HCM");
        WebUI.setText(inputState,"Q1");
        WebUI.setText(inputZip,"7000");

        WebUI.sleep(1);
        WebUI.clickElement(dropdownCountry);
        WebUI.setText(inputSearchCountry,"Vietnam");
        WebUI.clickElement(itemVietnamCountry);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);

    }

    public void verifyNavigateToCustomerDetailPage(){
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerDetailPage),"The customer detail header page not dissplay.");
        WebUI.assertEquals(WebUI.getElementText(headerCustomerDetailPage),"Profile", "The cusomer detail header page not match");
    }

    public void verifyAddNewCustomerSuccess(int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Customer");

        //verify alert message

        //verify data
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputCompany).getAttribute("value"),excelHelper.getCellData("Customer_Name",row),"The Company Name not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputVAT).getAttribute("value"),excelHelper.getCellData("VAT",row),"The VAT value not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputPhoneNumber).getAttribute("value"),excelHelper.getCellData("Phone",row),"The Phone Number not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputWebsite).getAttribute("value"),excelHelper.getCellData("Website",row),"The Webside not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownGroup).getAttribute("title"),"VIP","The Group not match");

    }

    public void searchAndCheckCustomerInTable(int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Customer");

        clickMenuCustomer();
        WebUI.setText(inputSearchCustomer,excelHelper.getCellData("Customer_Name",row));
        WebUI.sleep(2);
        String customerNameInTable = WebUI.getElementText(itemCustomerFirst);
        System.out.println(customerNameInTable);
        WebUI.assertEquals(customerNameInTable,excelHelper.getCellData("Customer_Name",row),"The customer name in table not match");
    }

    public void clickFirstItemCustomer(){
        WebUI.clickElement(itemCustomerFirst);
    }

    public int getCustomersTotal(){
        String totalString = WebUI.getElementText(totalCustomers);
        System.out.println("getCustomersTotal: " + totalString);
        return Integer.parseInt(totalString);
    }



}
