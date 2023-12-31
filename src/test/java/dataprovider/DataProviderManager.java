/*
 * Copyright (c) 2022. Anh Tester
 * Automation Framework Selenium
 */

package dataprovider;


import constants.FrameworkConstants;
import helpers.ExcelHelpers;
import helpers.Helpers;
import helpers.PropertiesHelpers;
import models.SignUpModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public final class DataProviderManager {

    private DataProviderManager() {
    }

//    @Test(dataProvider = "getSignInDataHashTable")
    public void testGetSignInData(Hashtable<String, String> data) {
//        System.out.println("signInData.testCaseName = " + data.get(SignInModel.getTestCaseName()));
//        System.out.println("signInData.username = " + data.get(SignInModel.getEmail()));
//        System.out.println("signInData.password = " + data.get(SignInModel.getPassword()));
//        System.out.println("signInData.expectedTitle = " + data.get(SignInModel.getExpectedTitle()));
//        System.out.println("signInData.expectedError = " + data.get(SignInModel.getExpectedError()));
    }

//    @Test(dataProvider = "getSignUpDataHashTable" )
    public void testGetSignUpData(Hashtable<String, String> data) {
        System.out.println("signInData.firname = " + data.get(SignUpModel.getFirstName()));
        System.out.println("signInData.email = " + data.get(SignUpModel.getEmail()));
        System.out.println("signInData.password = " + data.get(SignUpModel.getPassword()));
        System.out.println("signInData.username = " + data.get(SignUpModel.getUsername()));
        System.out.println("signInData.phone = " + data.get(SignUpModel.getPhoneNumber()));
    }

//    @Test(dataProvider = "getClientDataHashTable")

    @DataProvider(name = "getSignInDataHashTable", parallel = false)
    public static Object[][] getSignInData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 1, 6);
        System.out.println("getSignInData: " + data);
        return data;
    }
    @DataProvider(name = "getSignUpDataHashTable", parallel = false)
    public static Object[][] getSignUpdata() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignUp", 1, 16);
        System.out.println("getSignInData: " + data);
        return data;
    }
    @DataProvider(name = "getLocationDataHashTable", parallel = false)
    public static Object[][] getLocationData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "Locations", 1, 3);
        System.out.println("getLocationDataHashTable: " + data);
        return data;
    }
    @DataProvider(name = "getJobFunctionDataHashTable", parallel = false)
    public static Object[][] getJobFunctionData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "JobFunction", 1, 3);
        System.out.println("getJobFunctionDataHashTable: " + data);
        return data;
    }
    @DataProvider(name = "getDepartmentDataHashTable", parallel = false)
    public static Object[][] getDepartmentData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "Departments", 1, 3);
        System.out.println("getSignInData: " + data);
        return data;
    }

    @DataProvider(name = "getClientDataHashTable", parallel = true)
    public static Object[][] getClientData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "Client", 1, 4);
        System.out.println("getClientData: " + data);
        return data;
    }

}
