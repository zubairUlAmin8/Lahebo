package models;

import lombok.Data;

@Data
public class JobFunctionModel {

    public static int row;

    public static String jobFunctionName = "JOB_FUNCTION_NAME";

    public static String userName = "USERNAME";


    public static int getRow() {
        return row;
    }

    public static String getJobFunctionName() {
        return jobFunctionName;
    }

}
