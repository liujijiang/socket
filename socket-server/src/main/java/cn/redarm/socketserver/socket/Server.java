package cn.redarm.socketserver.socket;

import cn.redarm.socketserver.comm.SocketComment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
                            System.out.println(s);
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
