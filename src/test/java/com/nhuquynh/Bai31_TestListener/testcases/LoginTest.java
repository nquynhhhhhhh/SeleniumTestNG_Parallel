package com.nhuquynh.Bai31_TestListener.testcases;

import com.nhuquynh.Bai31_TestListener.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.dataproviders.DataProviderFactory;
import com.nhuquynh.helpers.CaptureHelper;
import com.nhuquynh.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) throws AWTException {
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cũng sẽ mang giá trị
        loginPage.loginCRM(email,password);
        loginPage.verifyLoginSuccess();
    }

    //@Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String password){
        CaptureHelper.startRecord("loginSuccessRecord");
        loginPage = new LoginPage();
        loginPage.loginCRM(email,password);
        loginPage.verifyLoginSuccess();
    }

    //@Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashTable(Hashtable< String, String > data){
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password") );
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void loginFailWithEmailInvalid(){
        CaptureHelper.startRecord("loginFailWithEmailInvalid");
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin123@example.com","123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 3)
    public void loginFailWithPassInvalid(){
        CaptureHelper.startRecord("loginFailWithPassInvalid");
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","111");
        loginPage.verifyLoginFail();
    }

    @Test(priority = 4)
    public void loginFailWithEmailNull(){
        CaptureHelper.startRecord("loginFailWithEmailNull");
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void loginFailWithPassNull(){
        CaptureHelper.startRecord("loginFailWithPassNull");
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test(priority = 6)
    public void loginFailWithNullFields(){
        CaptureHelper.startRecord("loginFailWithNullFields");
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
