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

public class Server {

    public static boolean serverUp = false;

    public static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(SocketComment.PORT);

        serverUp = true;

        ExecutorService service = Executors.newCachedThreadPool();

        while (true){

            System.out.println("start listen");
            final Socket socket = serverSocket.accept();

            Runnable runnable = () -> {
                try {
                    while (!Thread.interrupted()){

                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        String s = null;

                        while ( (s = reader.readLine()) != null){


                            MyJframe.jTextArea.append(s + "\n");
                            MyJframe.jTextArea.paintImmediately(MyJframe.jTextArea.getBounds());

                            if (s.contains("send a file:")){

                                Pattern pattern = Pattern.compile("send a file: (.*)");

                                Matcher matcher = pattern.matcher(s);

                                String filename = null;

                                if (matcher.find()){
                                    filename = matcher.group(1);
                                }

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
