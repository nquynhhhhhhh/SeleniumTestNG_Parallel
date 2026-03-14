package com.nhuquynh.Bai26_ParallelExecutionPOM.pages;

import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuSales = By.xpath("//span[@class='menu-text' and normalize-space()='Sales']");
    private By menuProposals = By.xpath("//span[normalize-space()='Proposals']");
    private By iconProfile = By.xpath("//li[@class='icon header-user-profile']");

    public CustomerPage clickMenuCustomer(){
        WebUI.waitForElementVisible(menuCustomers);
        WebUI.clickElement(menuCustomers);

        return new CustomerPage();
    }

}
