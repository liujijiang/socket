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

    private JPanel panelMain;

    // 按钮
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;

    // 输入IP 和 Port
    private JTextField jTextField1;
    private JTextField jTextField2;

    private JTextArea jTextArea;

    public MyJframe() throws UnknownHostException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(1344,756);

        setVisible(true);

        setLayout(null);

//        final ImageIcon imageIcon1 = new ImageIcon("images/background.PNG");
//
//        panelMain = new JPanel() {
//            //设置背景图片
////            public void paintComponent(Graphics g) {
////                g.drawImage(imageIcon1.getImage(), 0, 0, getSize().width, getSize().height, this);
////                super.paintComponents(g);
////            }
//        };

        this.init();
    }

    private void init() throws UnknownHostException {
        label1 = new JLabel("IP");
        label1.setBounds(120,100,80,20);
        add(label1);

        label2 = new JLabel("Port");
        label2.setBounds(120,200,80,20);
        add(label2);

//        jTextField1 = new JTextField(5);
//        jTextField1.setBounds(150,100,150,20);
//        add(jTextField1);

        jTextField2 = new JTextField(5);
        jTextField2.setBounds(150,200,150,20);
        add(jTextField2);

        InetAddress address = InetAddress.getLocalHost();

        String ip = address.getHostAddress().toString();

        labelIP = new JLabel(ip);
        labelIP.setBounds(150,100,150,20);
        add(labelIP);

        jButton1 = new JButton("配置");
        jButton1.setBounds(135,300,100,20);
        jButton1.addActionListener(this::actionPerformed);
        add(jButton1);

        jButton2 = new JButton("send file");
        jButton2.setBounds(135,500,100,20);
        jButton2.addActionListener(this::actionPerformed);
        add(jButton2);

        jButton3 = new JButton("send");
        jButton3.setBounds(1100,600,200,50);
        jButton3.addActionListener(this::actionPerformed);
        add(jButton3);

        jTextArea = new JTextArea();
        jTextArea.setBounds(430,50,800,500);
        add(jTextArea);
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
