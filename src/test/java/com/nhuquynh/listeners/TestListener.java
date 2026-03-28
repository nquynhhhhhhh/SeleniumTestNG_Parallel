package com.nhuquynh.listeners;
import com.nhuquynh.helpers.CaptureHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.utils.LogUtils;
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
        LogUtils.info("Setup môi trường onStart: " + result.getStartDate());
        //CaptureHelper.startRecord("VideoSuite01");
        //Load file Properties cofig
        PropertiesHelper.loadAllFiles();

        //Có thể kết nối Database trước để lấy data test
        //Call API bên t3 để xác thực 1 cái gì đó khi cần
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("Kết thúc bộ test: " + result.getEndDate());
        LogUtils.info("Test Total: " +test_total);
        LogUtils.info("Test Passed Total: " +test_passed_total);
        LogUtils.info("Test Failed Total: " +test_failed_total);
        LogUtils.info("Test Skipped Total: " +test_skipped_total);
        //CaptureHelper.stopRecord(1); //chỉ record được TC cuối
        //Gửi mail
        //Xuất report

    }

    @Override
    public void onTestStart(ITestResult result) {
        //ghi vào logs Files
        //ghi vào report chi tiết từng bước
        LogUtils.info("Bắt đầu chạy test case: " + result.getName());
        test_total++;
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        test_passed_total++;
        CaptureHelper.stopRecord(1);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());
        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());
        CaptureHelper.stopRecord(1);

        //Tạo ticket Jira
        //Gửi evidence và logs lên Slack/MSTeam...

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test case " + result.getName() + " is skipped.");
        LogUtils.warn(result.getThrowable());
        test_skipped_total++;
        CaptureHelper.stopRecord(1);

    }
}
