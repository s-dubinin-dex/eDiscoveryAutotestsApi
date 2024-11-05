package eDiscovery.helpers;

import java.util.UUID;

public class DataChecker {

    public static String dateTimeUTCPattern(){
        return "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2,7}Z$";
    }

    public static String dateTimeISOPattern(){
        return "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2,6}\\+02:00$";
    }

    public static boolean isValidUUID(String uuid){
        try {
            UUID.fromString(uuid);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
}
