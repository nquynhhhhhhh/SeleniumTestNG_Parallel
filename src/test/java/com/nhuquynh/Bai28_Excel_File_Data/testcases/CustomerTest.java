package com.nhuquynh.Bai28_Excel_File_Data.testcases;

import com.nhuquynh.Bai28_Excel_File_Data.pages.CustomerPage;
import com.nhuquynh.Bai28_Excel_File_Data.pages.DashboardPage;
import com.nhuquynh.Bai28_Excel_File_Data.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;


    @Test
    public void testAddNewCustomer(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Customer");

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        customerPage.verifyNavigateToCustomerPage();
        int berofeTotal = customerPage.getCustomersTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(2);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(2);
        //đang trong detail nên phải click về trang customer
        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomersTotal();
        Assert.assertEquals(afterTotal, berofeTotal + 1, "The total customer beforeTotal and afterTotal not match ");

        excelHelper.setCellData("Passed","Status",2);
    }

    @Test
    public void testAddNewCustomer_searchInTable(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Customer");

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        customerPage.verifyNavigateToCustomerPage();
        int berofeTotal = customerPage.getCustomersTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(2);

        //search and check customer name in table
        customerPage.searchAndCheckCustomerInTable(2);
        customerPage.clickFirstItemCustomer();

        //verify data of new customer in profile page
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(2);

        //compare total customer
        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomersTotal();
        Assert.assertEquals(afterTotal, berofeTotal + 1, "The total customer beforeTotal and afterTotal not match ");

    }

}
