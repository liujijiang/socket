package cn.redarm.socketclient.socket;

import cn.redarm.socketclient.comm.SocketComm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author redarm
 * @Date 2020/5/30 8:03 下午
 **/
@Slf4j
public class Client {

    private Socket socket;

    public static boolean connected = false;

    private String username;

    public Client(String username) {
        try {
            socket = new Socket(SocketComm.IP,SocketComm.PORT);

            this.username = username;
            connected = true;
        } catch (UnknownHostException e) {
            log.warn("connect error" + e);
        } catch (IOException e) {
            log.warn("connect error" + e);
        }
    }

    /**
     * @Author redarm
     * @Description //TODO 发送信息
     * @Date 7:15 下午 2020/5/31
     * @Param [text]
     * @return void
     **/
    @Async
    public void sendText(String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write(text + "\n");

        writer.flush();
    }

    /**
     * @Author redarm
     * @Description //TODO 发送文件
     * @Date 7:15 下午 2020/5/31
     * @Param [file]
     * @return void
     **/
    @Async
    public void sendFile(File file) throws IOException {

        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        byte[] buff = new byte[1024];

        int n = 0;

        while ( (n = in.read(buff)) != -1){
            out.write(buff,0,n);
        }

        out.flush();
        in.close();
        out.close();

        connected = false;
    }
}
