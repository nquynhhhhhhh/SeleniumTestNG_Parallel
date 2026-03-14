package com.nhuquynh.Bai28_Excel_File_Data.testcases;

import com.nhuquynh.Bai28_Excel_File_Data.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test(priority = 1)
    public void loginSuccess(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",1),
                excelHelper.getCellData("Password",1)
        );
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void loginFailWithEmailInvalid(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",2),
                excelHelper.getCellData("Password",2)
        );
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 3)
    public void loginFailWithPassInvalid(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",3),
                excelHelper.getCellData("Password",3)
        );
        loginPage.verifyLoginFail();
    }

    @Test(priority = 4)
    public void loginFailWithEmailNull(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",4),
                excelHelper.getCellData("Password",4)
        );
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void loginFailWithPassNull(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",5),
                excelHelper.getCellData("Password",5)
        );
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test(priority = 6)
    public void loginFailWithNullFields(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx","Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(
                excelHelper.getCellData("Email",6),
                excelHelper.getCellData("Password",6)
        );
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
