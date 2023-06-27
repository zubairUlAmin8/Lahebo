package models;

import lombok.Data;

@Data
public class LocationModel {

    public static int row;

    public static String locationName = "LOCATION_NAME";

    public static String userName = "USERNAME";


    public static int getRow() {
        return row;
    }

    public static String getLocationName() {
        return locationName;
    }

}
