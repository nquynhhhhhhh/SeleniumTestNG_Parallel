package com.nhuquynh.dataproviders;

import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {

    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + "src/test/resources/testdata/CRM.xlsx", "LoginDataProvider");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataLoginFromExcelHashTable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/testdata/CRM.xlsx", "LoginDataProvider",3,5);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_success", parallel = true)
    public Object[][] dataProviderLoginSuccess() {
        return new Object[][]{
                {"admin@example.com","123456"},
                {"admin@example.com","123456"}};
    }

    @DataProvider(name = "data_provider_01")
    public Object[][] dataProvider01() {
        return new Object[][]{{"Value1", "Value2", "Value3"}, {"Value4", "Value5", "Value6"}};
    }

    @DataProvider(name = "data_provider_02", parallel = true)
    public Object[][] dataProvider02() {
        return new Object[][]{{"Value1", "Value2", "Value3"}, {"Value4", "Value5", "Value6"}, {"Value7", "Value8", "Value9"}};
    }

    @DataProvider(name = "data_provider_03")
    public Object[][] dataProvider03() {
        return new Object[][]{{"Value1", 5, 100}, {"Value4", 10, 200}};
    }
}
