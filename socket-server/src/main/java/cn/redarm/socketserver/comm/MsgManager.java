package cn.redarm.socketserver.comm;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author redarm
 * @Date 2020/5/31 10:48 上午
 **/
public class MsgManager {

    public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public static void addMsg(String s){
        queue.add(s);
    }

    public static String getFirstAndRemove(){
        return queue.poll();
    }

    public static boolean isEmpty(){
        return queue.isEmpty();
    }
}
