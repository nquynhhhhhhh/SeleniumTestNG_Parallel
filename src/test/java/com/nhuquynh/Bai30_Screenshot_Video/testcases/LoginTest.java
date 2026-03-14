package com.nhuquynh.Bai30_Screenshot_Video.testcases;

import com.nhuquynh.Bai30_Screenshot_Video.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.dataproviders.DataProviderFactory;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.CaptureHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) throws AWTException {
        loginPage = new LoginPage(); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cũng sẽ mang giá trị
        loginPage.loginCRM(email,password);

        //CHỤP TOÀN BỘ MÀN HÌNH (bao gồm Taskbar) bằng Robot
        Robot robot = new Robot();
        // Lấy kích thước toàn bộ màn hình (Resolution hiện tại của Windows 11)
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        // Chụp ảnh màn hình
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        // Lưu file
        try {
            ImageIO.write(screenFullImage, "png", new File("full_desktop_with_taskbar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //CHỤP TOÀN BỘ MÀN HÌNH (KHÔNG gồm Taskbar)
        CaptureHelper.screenshot("loginSuccessCaptureScreenShot");

        //CHỤP FULL TOÀN BỘ MÀN HÌNH BẰNG ASHOT (thư viện ngoài)
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000)) // Cuộn sau mỗi 1s
                .takeScreenshot(DriverManager.getDriver());
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File("full_page_ashot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
