package com.nhuquynh.Bai32_Log4j2.testcases;

import com.nhuquynh.Bai32_Log4j2.pages.DashboardPage;
import com.nhuquynh.Bai32_Log4j2.pages.LoginPage;
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
