package com.nhuquynh.Bai26_ParallelExecutionPOM.testcases;

import com.nhuquynh.Bai26_ParallelExecutionPOM.pages.DashboardPage;
import com.nhuquynh.Bai26_ParallelExecutionPOM.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest{
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void checkDashboardTotal(){
        loginPage = new LoginPage();
//        loginPage.loginCRM(); //chỉ login
//        dashboardPage = new DashboardPage(driver);

        dashboardPage = loginPage.loginCRM();
        dashboardPage.verifyInvoicesAwaitingPayment("1 / 3");

    }
}
