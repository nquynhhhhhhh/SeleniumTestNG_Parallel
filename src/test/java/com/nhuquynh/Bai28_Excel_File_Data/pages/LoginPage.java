package com.nhuquynh.Bai28_Excel_File_Data.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.drivers.DriverManager;
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


    //Xây dựng hàm xử lý => hàm public, phải gọi sang chỗ khác dùng được, đảm nhiệm xử lý chức năng nội bộ của trang Login

    //C1: Đã có WebUI nên không cần khởi tạo hàm trung gian
    //chạy hàm automation login (dùng chung ở toàn bộ TC)
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
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
    }

    //hàm verify (dùng ở từng TC riêng)
    public void verifyLoginSuccess() {
        Assert.assertEquals(DriverManager.getDriver().findElement(By.xpath(Locators.menuDashboard)).getText(), "Dashboard", "FAIL. Vẫn đang ở trang Login");
        WebUI.assertNotContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail() { //đã truyền text r nên gọi hàm kh cần truyền text nữa nhưng chỉ sử dụng được 1 TH
        WebUI.assertContains(WebUI.getCurrentURL(),"authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        //Assert.assertEquals(driver.findElement(errorMessage).getText(), "Invalid email or password", "Content of error massage NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage),"Invalid email or password", "Content of error message NOT match.");
    }
    //giống cái trên => tính đa hình
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

