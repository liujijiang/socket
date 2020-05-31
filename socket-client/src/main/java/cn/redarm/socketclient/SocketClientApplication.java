package cn.redarm.socketclient;

import cn.redarm.socketclient.swing.MyJframe;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class SocketClientApplication {

    public static void main(String[] args) throws IOException {
        // SpringApplication.run(SocketClientApplication.class, args);

        new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                new MyJframe();
            }
        }.start();


    }

}
