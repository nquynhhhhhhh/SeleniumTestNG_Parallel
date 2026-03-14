package com.nhuquynh.helpers;

import com.nhuquynh.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CaptureHelper {
    public static void screenshot(String screenshotName){
        //Chụp màn hình trình duyệt
        // Tạo tham chiếu của TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        // Gọi hàm để chụp ảnh màn hình - getScreenshotAs
        File source = ts.getScreenshotAs(OutputType.FILE);
        // Kiểm tra folder tồn tại. Nếu không thì tạo mới folder theo đường dẫn
        File theDir = new File("./evidence/screenshots/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        //Lưu file ảnh với tên cụ thể vào đường dẫn
        try {
            Date now = new Date();
            String dateTime = now.toString().replaceAll(" ","_");
            dateTime = dateTime.replaceAll(":","_");
            FileHandler.copy(source, new File("./evidence/screenshots/" + screenshotName + "_" + dateTime + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
