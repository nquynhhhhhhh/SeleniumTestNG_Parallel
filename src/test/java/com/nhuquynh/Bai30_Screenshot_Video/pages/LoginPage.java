package com.nhuquynh.Bai30_Screenshot_Video.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.CaptureHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    private By errorMessage1 = By.xpath("(//div[contains(@class,'alert-danger')])[1]");
    private By errorMessage2 = By.xpath("(//div[contains(@class,'alert-danger')])[2]");


      public DashboardPage loginCRM() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.setText(inputEmail, "admin@example.com");
        WebUI.setText(inputPassword, "123456");
        WebUI.clickElement(buttonLogin);
        verifyLoginSuccess(); //khi login thì check luôn có succes kh

        return new DashboardPage();
    }

    public void loginCRM(String email, String password) { //chạy automation login, verify là 2 hàm trên
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        CaptureHelper.screenshot("stepNavigateToURL");
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        CaptureHelper.screenshot("stepEnterEmailPassword");
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginSuccess() {
        Assert.assertEquals(DriverManager.getDriver().findElement(By.xpath(Locators.menuDashboard)).getText(), "Dashboard", "FAIL. Vẫn đang ở trang Login");
        WebUI.assertNotContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail() {
        WebUI.assertContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        //Assert.assertEquals(driver.findElement(errorMessage).getText(), "Invalid email or password", "Content of error massage NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage),"Invalid email or password", "Content of error message NOT match.");
    }

    public void verifyLoginFail(String message) { //khi gọi hàm phải truyền text nhưng sử dụng được nhiều TH
        WebUI.assertContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        //Assert.assertEquals(driver.findElement(errorMessage).getText(), message, "Content of error massage NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage),message, "Content of error massage NOT match.");
    }

    public void verifyLoginFailWithNullFields() {
        WebUI.assertContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage1), "Error message 1 NOT displays");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage2), "Error message 2 NOT displays");

        Assert.assertEquals(WebUI.getElementText(errorMessage1), "The Password field is required.", "Content of error massage 1 NOT match.");
        Assert.assertEquals(WebUI.getElementText(errorMessage2), "The Email Address field is required.", "Content of error massage 2 NOT match.");
    }


    //NẾU CÓ NHIỀU error message quá => dùng for
    public void verifyLoginFailWithNullFields_ArrayList(int totalNullFields) {
        List<String> messageString = new ArrayList<>();
        messageString.add("The Password field is required.");
        messageString.add("The Email Address field is required.");

        boolean check = false;

        //for ngoài là số lượng error message
        for (int i = 1; i <= totalNullFields; i++) { //Biến i được dùng để xây dựng chuỗi XPath (dưới)=> mà XPath, chỉ mục (index) của các phần tử bắt đầu từ 1
            Assert.assertTrue(DriverManager.getDriver().findElement(By.xpath("(//div[contains(@class,'alert-danger')])[" + i + "]")).isDisplayed(), "Error message " + i + " NOT displays");
            //for trong là số lượng message mình compare
            for (int j = 0; j < messageString.size(); j ++) {
                if(WebUI.getElementText(By.xpath("(//div[contains(@class,'alert-danger')])[" + i + "]")).equals(messageString.get(j))){
                    check = true;
                    break;
                }
            }
            Assert.assertTrue(check,"Content of error massage " + 1 + " NOT match.");
        }
    }


}

