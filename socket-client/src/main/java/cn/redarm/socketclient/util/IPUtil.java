package cn.redarm.socketclient.util;

import org.springframework.stereotype.Component;

public class IPUtil {

    public static boolean isTrue(String IP){
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

        if (IP != null && !IP.isEmpty()){

            if (IP.matches(regex)){
                return true;
            }
        }
        return false;
    }
}
