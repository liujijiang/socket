package cn.redarm.socketserver;

import cn.redarm.socketserver.swing.MyJframe;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SocketServerApplication {

    public static void main(String[] args) throws IOException {
        // SpringApplication.run(SocketServerApplication.class, args);

        // new MyJframe();

        new MyJframe();
    }

}
