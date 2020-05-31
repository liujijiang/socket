package cn.redarm.socketclient.swing;

import cn.redarm.socketclient.comm.SocketComm;
import cn.redarm.socketclient.socket.Client;
import cn.redarm.socketclient.util.IPUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * @Author redarm
 * @Description //TODO 窗口
 * @Date 7:15 下午 2020/5/31
 * @Param
 * @return
 **/
public class MyJframe extends JFrame {

    private Client client;

    // 标签
    private JLabel label1;
    private JLabel label2;
    private JLabel jLabel3;

    // 按钮
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;

    // 文件选择
    private JFileChooser jFileChooser;

    // 输入IP 和 Port username
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;

    // 输入框
    private JTextArea jTextArea;

    public MyJframe() throws IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(700,350);

        setLayout(null);

        this.init();

        setVisible(true);
    }

    private void init(){
        label1 = new JLabel("IP");
        label1.setBounds(20,20,30,20);
        add(label1);

        label2 = new JLabel("port");
        label2.setBounds(20,70,30,20);
        add(label2);

        jLabel3 = new JLabel("name");
        jLabel3.setBounds(20,120,35,20);
        add(jLabel3);

        jTextField1 = new JTextField(5);
        jTextField1.setBounds(60,20,150,20);
        add(jTextField1);

        jTextField2 = new JTextField(5);
        jTextField2.setBounds(60,70,150,20);
        add(jTextField2);

        jTextField3 = new JTextField(5);
        jTextField3.setBounds(60,120,150,20);
        add(jTextField3);

        jButton1 = new JButton("建立连接");
        jButton1.setBounds(30,170,100,20);
        jButton1.addActionListener(this::actionPerformed);
        add(jButton1);

        jButton2 = new JButton("发送文件");
        jButton2.setBounds(130,230,150,50);
        jButton2.addActionListener(this::actionPerformed);
        add(jButton2);

        jButton3 = new JButton("发送消息");
        jButton3.setBounds(400,230,200,50);
        jButton3.addActionListener(this::actionPerformed);
        add(jButton3);

        // add a keyboard event
        jTextArea = new JTextArea();
        jTextArea.setBounds(230,20,400,200);
        jTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    jButton3.doClick();
                }
            }
        });
        add(jTextArea);
    }

    /**
     * @Author redarm
     * @Description //TODO 事件
     * @Date 7:16 下午 2020/5/31
     * @Param [e]
     * @return void
     **/
    public void actionPerformed(ActionEvent e){
        try {

            // 处理点击 建立连接 按钮
            if (e.getSource() == jButton1){
                String port = String.valueOf(jTextField2.getText().trim());
                String IP = String.valueOf(jTextField1.getText().trim());
                String username = String.valueOf(jTextField3.getText().trim());

                if (!IPUtil.isTrue(IP)) {
                    JOptionPane.showMessageDialog(this, "IP格式错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (port == null || port.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Port格式错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (username == null || username.isEmpty()){
                    JOptionPane.showMessageDialog(this, "username wrong，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
                }

                int newPort = Integer.parseInt(port);

                SocketComm.IP = IP;
                SocketComm.PORT = newPort;
                SocketComm.USERNAME = username;

                // 判断是否已经建立了连接
                if (Client.connected){
                    JOptionPane.showMessageDialog(this, "have connected to server", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    client = new Client(username);
                    JOptionPane.showMessageDialog(this, "setting success！", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }

            // send file
            if (e.getSource() == jButton2){

                // 选择文件窗口
                jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jFileChooser.showDialog(new JLabel(),"send");

                File file = jFileChooser.getSelectedFile();

                // 发送信息
                client.sendText(SocketComm.USERNAME + " send a file: " + file.getName());

                // 发送文件
                client.sendFile(file);

                JOptionPane.showMessageDialog(this, "send file success!", "提示", JOptionPane.ERROR_MESSAGE);
            }

            // send text
            if (e.getSource() == jButton3){
                if (!client.connected){
                    JOptionPane.showMessageDialog(this, "please connect to server first！", "提示", JOptionPane.ERROR_MESSAGE);
                }

                String text = jTextArea.getText().trim();

                if (text == null || text.isEmpty()){
                    JOptionPane.showMessageDialog(this, "text is empty，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    // JOptionPane.showMessageDialog(this, "send success！", "提示", JOptionPane.ERROR_MESSAGE);
                    client.sendText(SocketComm.USERNAME + " said: " + text);

                    jTextArea.setText("");
                }
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
