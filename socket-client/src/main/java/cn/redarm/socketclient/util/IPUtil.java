package cn.redarm.socketclient.util;

/**
 * @Author redarm
 * @Description //TODO 判断 是否是 IP
 * @Date 7:24 下午 2020/5/31
 * @Param 
 * @return 
 **/
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
