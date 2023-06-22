package models;

import lombok.Data;

@Data
public class DepartmentModel {

    public static int row;

    public static String departmentName = "DEPARTMENT_NAME";

    public static String userName = "USERNAME";


    public static int getRow() {
        return row;
    }

    public static String getDepartmentName() {
        return departmentName;
    }

}
