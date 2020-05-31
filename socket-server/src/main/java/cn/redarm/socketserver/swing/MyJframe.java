package cn.redarm.socketserver.swing;

import cn.redarm.socketserver.comm.SocketComment;
import cn.redarm.socketserver.socket.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyJframe extends JFrame {

    // 标签
    private JLabel label1;
    private JLabel label2;

    private JLabel labelIP;

    // 按钮
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;

    // Port
    private JTextField jTextField2;

    private static JTextArea jTextArea;

    public MyJframe() throws UnknownHostException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(700,350);

        setLayout(null);

        this.init();

        setVisible(true);
    }

    private void init() throws UnknownHostException {
        label1 = new JLabel("IP");
        label1.setBounds(20,20,30,20);
        add(label1);

        label2 = new JLabel("Port");
        label2.setBounds(20,70,30,20);
        add(label2);

        jTextField2 = new JTextField(5);
        jTextField2.setBounds(60,70,150,20);
        add(jTextField2);

        InetAddress address = InetAddress.getLocalHost();

        String ip = address.getHostAddress().toString();

        labelIP = new JLabel(ip);
        labelIP.setBounds(60,20,150,20);
        add(labelIP);

        jButton1 = new JButton("配置");
        jButton1.setBounds(30,120,100,20);
        jButton1.addActionListener(this::actionPerformed);
        add(jButton1);

        jButton2 = new JButton("send file");
        jButton2.setBounds(30,160,100,20);
        jButton2.addActionListener(this::actionPerformed);
        add(jButton2);

        jButton3 = new JButton("send");
        jButton3.setBounds(400,230,200,50);
        jButton3.addActionListener(this::actionPerformed);
        add(jButton3);

        jTextArea = new JTextArea();
        jTextArea.setBounds(230,20,400,200);
        add(jTextArea);
    }

    public static void addText(String s){
        jTextArea.append(s);

        // jTextArea.paintImmediately(jTextArea.getBounds());

        jTextArea.paintImmediately(jTextArea.getX(),jTextArea.getY(),jTextArea.getWidth(),jTextArea.getHeight());
    }

    public void actionPerformed(ActionEvent e){
        try {
            if (e.getSource() == jButton1){
                String Port = String.valueOf(jTextField2.getText().trim());

                if (Port == null || Port.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Port格式错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
                }

                int newPort = Integer.parseInt(Port);

                SocketComment.PORT = newPort;

                if (Server.serverUp){
                    JOptionPane.showMessageDialog(this, "server is running！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "server starts success！", "提示", JOptionPane.ERROR_MESSAGE);
                    Server.start();
                }
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
