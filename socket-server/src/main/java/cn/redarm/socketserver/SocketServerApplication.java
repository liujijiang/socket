package cn.redarm.socketserver;

import cn.redarm.socketserver.swing.MyJframe;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class SocketServerApplication {

    public static void main(String[] args) throws IOException {
        // SpringApplication.run(SocketServerApplication.class, args);

        // new MyJframe();

        new MyJframe();
    }

}
