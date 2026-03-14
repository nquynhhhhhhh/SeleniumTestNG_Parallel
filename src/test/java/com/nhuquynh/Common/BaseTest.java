package com.nhuquynh.Common;

import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    @BeforeSuite
    public void setupEnviroment(){
        PropertiesHelper.loadAllFiles(); // để đa tránh xung đột
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) { //nếu muốn ưu tiên xml thì lấy biến này (lấy từ Parameters của xml)
        WebDriver driver;
//        PropertiesHelper.loadAllFiles(); // khi để đây nếu chạy song song 1 TC với 2 bộ data (data provider) thì nó có khả năng bị xung đột
        //sẽ ưu tiên chạy theo file config (mặc dù file xml đển browser khác)
        if (PropertiesHelper.getValue("BROWSER").isEmpty() && PropertiesHelper.getValue("BROWSER").isBlank()){
            browser = browser;
        }else {
            browser = PropertiesHelper.getValue("BROWSER");
        }

        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt ChromeTest");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Khởi chạy trình duyệt Firefox");
                break;
            default:
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt ChromeTest mặc định");
                break;
        }

        DriverManager.setDriver(driver);  //set giá trị driver vào ThreadLocal
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
    @AfterMethod
    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

}
