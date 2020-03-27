import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class ClientB extends JFrame implements Runnable, ActionListener{
    public static final int PORT_B_SEND = 4099;
    public static final int PORT_B_RECEIVE = 4098;
    private static final int SERVER_PORT = 2048;

    JTextField outMessage = new JTextField(20);
    JTextArea inMessage = new JTextArea(12,20);
    JButton send = new JButton("发送数据");

    ClientB()
    {
        // ---
        super("I AM B");
        setBounds(350,100,640,630);
        setVisible(true);
        // ---
        send.addActionListener(this);
        outMessage.addActionListener(this);

        // ---
        JPanel p = new JPanel();
        p.add(outMessage);
        p.add(send);
        ;
        inMessage.setFont(new Font("微软雅黑light", Font.ITALIC,18));

        Container con  = getContentPane();
        con.add(new JScrollPane(inMessage),BorderLayout.CENTER);
        con.add(p,BorderLayout.SOUTH);

        // ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();

        // ---
        Thread thread = new Thread(this);
        thread.start(); // 线程负责接收数据
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        byte[] messages = outMessage.getText().trim().getBytes();

        if(messages.length == 0) return;
        outMessage.setText("");
        //从文本框中获取待传输的数据并将其打包，传出
        try {
            DatagramSocket sendSocket = new DatagramSocket(PORT_B_SEND);
            DatagramPacket sendPacket = new DatagramPacket( messages , messages.length ,
                    InetAddress.getByName("127.0.0.1") ,  SERVER_PORT );

            sendSocket.send( sendPacket );
            sendSocket.close();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (SocketException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run() {
        DatagramSocket receiveScoket = null;
        DatagramPacket receivePacket = null;

        byte[] messages = new byte[8192];
        //接收传入的数据并转换为字符串，写入文本框中
        try{
            receiveScoket = new DatagramSocket(PORT_B_RECEIVE);
            receivePacket = new DatagramPacket(messages, messages.length);
        }catch (SocketException e){
            e.printStackTrace();
        }

        while(true)
        {
            try {
                receiveScoket.receive(receivePacket);

                String messagesText = new String(messages);

                inMessage.append(messagesText + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ClientB();
    }
}
