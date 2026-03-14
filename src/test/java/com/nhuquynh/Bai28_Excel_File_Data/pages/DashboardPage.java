package com.nhuquynh.Bai28_Excel_File_Data.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private  By totalInvoicesAwaitingPayment = By.xpath(Locators.totalInvoicesAwaitingPayment);
    private  By totalConvertedLeads = By.xpath(Locators.totalConvertedLeads);


    public void verifyInvoicesAwaitingPayment(String total){
        //lúc nào cũng phải check tồn tại trc
        Assert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment),"The Invoices Awaiting Payment total label not match");
        Assert.assertEquals(WebUI.getElementText(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total not match");
    }


}
