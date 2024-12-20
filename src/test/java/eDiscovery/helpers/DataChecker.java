package eDiscovery.helpers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class DataChecker {

    public static String dateTimeYYYYMMDDHHmmssPattern(){
        return "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$";
    }

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

    public static boolean isValidIPAddress(String ip){
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.getHostAddress().equals(ip);
        } catch (UnknownHostException e){
            return false;
        }
    }
}
