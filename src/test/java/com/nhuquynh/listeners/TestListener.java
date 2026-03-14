package com.nhuquynh.listeners;
import com.nhuquynh.helpers.CaptureHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;

    @Override
    public void onStart(ITestContext result) {
        System.out.println("Setup môi trường onStart: " + result.getStartDate());
        CaptureHelper.startRecord("VideoSuite01"); //
        //Load file Properties cofig
        PropertiesHelper.loadAllFiles();

        //Có thể kết nối Database trước để lấy data test
        //Call API bên t3 để xác thực 1 cái gì đó khi cần
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Kết thúc bộ test: " + result.getEndDate());
        System.out.println("Test Total: " +test_total);
        System.out.println("Test Passed Total: " +test_passed_total);
        System.out.println("Test Failed Total: " +test_failed_total);
        System.out.println("Test Skipped Total: " +test_skipped_total);
        CaptureHelper.stopRecord(1); //chỉ reocord được TC cuối
        //Gửi mail
        //Xuất report

    }

    @Override
    public void onTestStart(ITestResult result) {
        //ghi vào logs Files
        //ghi vào report chi tiết từng bước
        System.out.println("Bắt đầu chạy test case: " + result.getName());
        test_total++;
//        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is passed.");
        System.out.println("==> Status: " + result.getStatus());
        test_passed_total++;
//        CaptureHelper.stopRecord(1);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is failed.");
        System.out.println("==> Status: " + result.getStatus());
        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());
//        CaptureHelper.stopRecord(1);

        //Tạo ticket Jira
        //Gửi evidence và logs lên Slack/MSTeam...

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is skipped.");
        System.out.println("==> Status: " + result.getStatus());
        test_skipped_total++;
        CaptureHelper.stopRecord(1);

    }
}
