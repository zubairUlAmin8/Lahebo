/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package constants;


import Utils.ReportUtils;
import helpers.Helpers;
import helpers.PropertiesHelpers;

//final -> We do not want any class to extend this class
public final class TestDataConstants {

    //private -> We do not want anyone to create the object of this class
    private TestDataConstants() {
    }

    public static final String DEPARTMENT_NAME = PropertiesHelpers.getValue("DEPARTMENT_NAME");
    public static final String EDIT_DEPARTMENT_NAME = PropertiesHelpers.getValue("EDIT_DEPARTMENT_NAME");
    public static final String Existing_User_Password = PropertiesHelpers.getValue("Existing_User_Password");

}
