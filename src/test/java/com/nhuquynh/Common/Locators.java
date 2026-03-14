package com.nhuquynh.Common;

public class Locators {

        //LoginTest page
        public static String headerLogin = "//h1[normalize-space()='LoginTest']";
        public static String inputEmail = "//input[@id='email']";
        public static String inputPassword = "//input[@id='password']";
        public static String buttonLogin = "//button[normalize-space()='LoginTest']";
        public static String checkboxRememberMe = "//input[@id='remember']";
        public static String linkForgotPassword = "//a[normalize-space()='Forgot Password?']";
        public static String alertErrorMessage = "//div[@id='alerts']/div";

        //Dashboard page
        public static String menuDashboard = "//span[normalize-space()='Dashboard']";
        public static String menuCustomers = "//span[normalize-space()='Customers']";
        public static String menuTasks = "//span[normalize-space()='Tasks']";
        public static String menuProjects = "//span[normalize-space()='Projects']";
        public static String menuSales = "//span[@class='menu-text' and normalize-space()='Sales']";
        public static String menuProposals = "//span[normalize-space()='Proposals']";
        public static String iconProfile = "//li[@class='icon header-user-profile']";

        public static String totalInvoicesAwaitingPayment = "(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span";
        public static String totalConvertedLeads = "(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span";
        public static String totalProjectsInProgress = "(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span";
        public static String totalTasksNotFinished = "(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span";

        //Customer page
        public static String headerCustomerPage = "//span[normalize-space()='Customers Summary']";
        public static String buttonAddNewCustomer = "//a[normalize-space()='New Customer']";
        public static String buttonImportCustomers = "//a[normalize-space()='Import Customers']";
        public static String inputSearchCustomer = "//div[@id='clients_filter']//input[@type='search']";
        //Add New Customer
        //kh nên chọn thẻ select vì đó là thẻ trung gian ngầm lưu giữ value => chọn button or div
        public static String tabCustomerDetails = "//a[normalize-space()='Customer Details']/preceding::hi4";
        public static String inputCompany = "//input[@id='company']";
        public static String inputVAT = "//input[@id='vat']";
        public static String inputPhoneNumber = "//input[@id='phonenumber']";
        public static String inputWebsite = "//input[@id='website']";
        public static String dropdownGroup = "//button[@data-id='groups_in[]']";
        public static String labelGroup = "//label[normalize-space()='Groups']";
        public static String addGroup = "//div[@class='input-group-btn']";
        public static String inputSearchGroup = "//button[@data-id='groups_in[]']/following-sibling::div//input";
        public static String itemVIP = "//button[@data-id='groups_in[]']/following-sibling::div//span[normalize-space()='VIP']";

        public static String dropdownCurrency = "//button[@data-id='default_currency']";
        public static String inputSearchCurrency = "//button[@data-id='default_currency']/following-sibling::div//input";
        public static String notFoundCurrency = "//button[@data-id='default_currency']/following-sibling::div//li[@class='no-results']";
        public static String itemUSD = "//button[@data-id='default_currency']/following-sibling::div//span[contains(normalize-space(),'USD')]";

        public static String dropdownLanguage = "//button[@data-id='default_language']";
        public static String itemVietnamese = "//span[normalize-space()='Vietnamese']";
        public static String inputAddress = "//textarea[@id='address']";
        public static String inputCity = "//input[@id='city']";
        public static String inputState = "//input[@id='state']";
        public static String inputZip = "//input[@id='zip']";
        public static String dropdownCountry = "//button[@data-id='country']";
        public static String inputSearchCountry = "//button[@data-id='country']/following-sibling::div//input";
        public static String itemVietnamCountry = "//button[@data-id='country']/following-sibling::div//span[normalize-space()='Vietnam']";
        public static String buttonSaveAndCreate = "//button[normalize-space()='Save and create contact']";
        //=> có khả năng button bị trùng tên nên dùng thẻ div trên nó có id//button sẽ chuẩn hơn
        public static String buttonSave = "//div[@id='profile-save-section']//button[normalize-space()='Save']";
        public static String errorCompany = "//p[@id='company-error']";
        public static String itemCustomerFirst = "//table[@id='clients']/tbody/tr[1]/td[3]/a";

        //Project
        public static String inputSearchProject = "//div[@id='projects_filter']//input";
        public static String buttonAddNewProject = "//a[normalize-space()='New Project']";
        public static String titleAddNewProject = "//a[normalize-space()='Project']/preceding::h4";
        public static String inputProjectName = "//input[@id='name']";
        public static String dropdownCustomer = "//button[@data-id='clientid']";
        public static String inputSearchCustomerProject = "//button[@data-id='clientid']/following-sibling::div//input[@type='search']";
        public static String itemYHL = "//button[@data-id='clientid']/following-sibling::div//span[normalize-space()='CTy Vĩnh Tường YHL 10']";

        public static String checkboxCalculateProgress = "//input[@id='progress_from_tasks']";
        public static String scrollProgress = "//input[@name='progress']/following-sibling::div/span";

        public static String dropdowmBillingType = "//button[@data-id='billing_type']";
        public static String itemTaskHours = "//button[@data-id='billing_type']/following-sibling::div//a[@id='bs-select-1-3']";

        public static String dropdownStatus = "//button[@data-id='status']";
        public static String itemInProgress = "//div[@id='bs-select-2']//span[normalize-space()='In Progress']";

        //public static String inputRatePerHour = "//input[@id='project_rate_per_hour']"; => chrome kh có
        public static String inputEstimatedHours = "//input[@id='estimated_hours']";

        public static String dropdownMember = "//button[@data-id='project_members[]']";
        public static String buttonSelectAll = "//button[@data-id='project_members[]']/following-sibling::div//button[normalize-space()='Select All']";

        public static String inputStartDate = "//input[@id='start_date']/following-sibling::div[@class='input-group-addon']";
        public static String item20251027 = "//div[contains(@class,'xdsoft_calendar')]//td[@data-date='27' and @data-month='9' and @data-year='2025']/div";
        //tháng 10 nhưng bắt đầu từ 0 => data-month='9'
        public static String inputDealine = "//input[@id='deadline']";

        public static String inputTag = "//input[@id='tags']/following-sibling::ul/li[@class='tagit-new']/input";
        public static String inputDescription = "//body[@id='tinymce']";
        public static String checkboxSendProjectCreateEmail = "//input[@id='send_created_email']";
        public static String buttonSaveProject = "//button[normalize-space()='Save']";

        public static String buttonProjectName = "//button[@title='Xây nhà cùng Vĩnh Tường YHL - CTy Vĩnh Tường YHL 10']";
        public static String tabOverview = "//a[normalize-space()='Overview']";
        public static String progressBarOfProject = "//div[@class='progress progress-bar-mini']/preceding-sibling::p/span";
        public static String progressBarOfOpenTask = "//div[@class='project-overview-open-tasks']";
        public static String progressBarOfDateLeft = "//div[@class='col-md-6 project-progress-bars project-overview-days-left']//div[@class='panel-body !tw-px-5 !tw-py-4']";
        public static String dropdownMenuProjectLoggedTime = "//a[@id='dropdownMenuProjectLoggedTime']";
        public static String itemThisMonth = "//a[normalize-space()='This Month']";
        public static String timesheetsChart = "//canvas[@id='timesheetsChart']";


}


