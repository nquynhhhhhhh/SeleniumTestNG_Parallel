package com.nhuquynh.Bai29_DataProvider.testcases;

import com.nhuquynh.Bai29_DataProvider.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.dataproviders.DataProviderFactory;
import com.nhuquynh.helpers.PropertiesHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest {
    //nếu có 10 bộ data mà muốn chạy parallel thì tách ra 2 data provider, 2 TC mỗi cái 5 bộ

    LoginPage loginPage;
    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cũng sẽ mang giá trị
        loginPage.loginCRM(email,password);
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String password){
        loginPage = new LoginPage();
        loginPage.loginCRM(email,password);
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashTable(Hashtable< String, String > data){
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password") );
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void loginFailWithEmailInvalid(){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin123@example.com","123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void loginFailWithPassInvalid(){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","111");
        loginPage.verifyLoginFail();
    }

    @Test
    public void loginFailWithEmailNull(){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void loginFailWithPassNull(){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test
    public void loginFailWithNullFields(){
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
