package com.nhuquynh.Bai26_ParallelExecutionPOM.testcases;

import com.nhuquynh.Bai26_ParallelExecutionPOM.pages.CustomerPage;
import com.nhuquynh.Bai26_ParallelExecutionPOM.pages.DashboardPage;
import com.nhuquynh.Bai26_ParallelExecutionPOM.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;


    @Test
    public void testAddNewCustomer(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        String customerName = "Công ty YHL 110226";
        customerPage.verifyNavigateToCustomerPage();
        int berofeTotal = customerPage.getCustomersTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(customerName);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(customerName);
        //đang trong detail nên phải click về trang customer
        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomersTotal();
        Assert.assertEquals(afterTotal, berofeTotal + 1, "The total customer beforeTotal and afterTotal not match ");
    }

    @Test
    public void testAddNewCustomer_searchInTable(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        String customerName = "Công ty YHL 11:02";

        customerPage.verifyNavigateToCustomerPage();
        int berofeTotal = customerPage.getCustomersTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(customerName);

        //search and check customer name in table
        customerPage.searchAndCheckCustomerInTable(customerName);
        customerPage.clickFirstItemCustomer();

        //verify data of new customer in profile page
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(customerName);

        //compare total customer
        customerPage.clickMenuCustomer();
        int afterTotal = customerPage.getCustomersTotal();
        Assert.assertEquals(afterTotal, berofeTotal + 1, "The total customer beforeTotal and afterTotal not match ");

    }

}
