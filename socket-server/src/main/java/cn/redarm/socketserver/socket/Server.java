package cn.redarm.socketserver.socket;

import cn.redarm.socketserver.comm.SocketComment;
import cn.redarm.socketserver.swing.MyJframe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author redarm
 * @Description //TODO 服务器 socket
 * @Date 7:31 下午 2020/5/31
 * @Param
 * @return
 **/
public class Server {

    public static boolean serverUp = false;

    public static void start() throws IOException {

        // 服务器socket
        ServerSocket serverSocket = new ServerSocket(SocketComment.PORT);

        serverUp = true;

        ExecutorService service = Executors.newCachedThreadPool();

        while (true){

            // 如果有socket连接
            final Socket socket = serverSocket.accept();

            Runnable runnable = () -> {
                try {
                    while (!Thread.interrupted()){

                        // 从socket中接受消息
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        String s = null;

                        while ( (s = reader.readLine()) != null){

                            // 把消息显示到窗口中并 刷新
                            MyJframe.jTextArea.append(s + "\n");
                            MyJframe.jTextArea.paintImmediately(MyJframe.jTextArea.getBounds());

                            // 如果消息中包含 send a file ， 准备接受文件
                            if (s.contains("send a file:")){

                                // 正则匹配文件名
                                Pattern pattern = Pattern.compile("send a file: (.*)");

                                Matcher matcher = pattern.matcher(s);

                                String filename = null;

                                if (matcher.find()){
                                    filename = matcher.group(1);
                                }

                                // 建立字节流IO， 接受文件
                                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());

                                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(SocketComment.FILE_PATH + "/" + filename)));

                                byte[] buff = new byte[1024];

                                int n = 0;

                                while ( (n = inputStream.read(buff)) != -1){
                                    outputStream.write(buff,0,n);
                                }

                                inputStream.close();
                                outputStream.close();

                                break;
                            }
                        }

                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            };

            service.execute(runnable);
        }
    }
}
