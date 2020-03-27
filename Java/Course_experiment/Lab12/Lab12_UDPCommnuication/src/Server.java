import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;

public class Server {
    private final static int SERVER_PORT = 2048;

    DatagramSocket serverScoket = null;
    DatagramPacket packet = null;

    public Server()
    {
        System.out.println("等待客户端接入");
        byte[] messages = null;

        try {
            serverScoket = new DatagramSocket(SERVER_PORT);

        } catch (SocketException e) {
            e.printStackTrace();
        }

        while(true)
        {
            try {
                messages = new byte[8192];
                packet = new DatagramPacket(messages, messages.length);
                /*接收数据并对其进行处理*/
                serverScoket.receive(packet);
                handle(new String(messages));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle(String messeages)
    {
        try {
            int senderPort = packet.getPort();
            InetAddress address = packet.getAddress();

            /*通过区分传入数据的接口来区分是哪个客户端传入信息，并确定目标地址*/
            int recevierPort = 0;
            if(senderPort == ClientA.PORT_A_SEND)
                recevierPort = ClientB.PORT_B_RECEIVE;
            else if(senderPort == ClientB.PORT_B_SEND)
                recevierPort = ClientA.PORT_A_RECEIVE;
            else
                throw new Exception();

            System.out.println("客户端" +senderPort +"接入"  + address);
            /*输出接收数据的时间，并规定时间输出格式*/
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
            System.out.println(dateFormat.format(System.currentTimeMillis()));
            /*将接收内容和其相关的信息（时间，地址）整合*/
            messeages = dateFormat.format(System.currentTimeMillis()) + "收到来自" + senderPort + address + "的消息\n" + messeages;
            /*将整合完整的信息打包*/
            DatagramSocket sendSocket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(messeages.getBytes(), messeages.length(),
                    InetAddress.getByName("127.0.0.1"), recevierPort);
            /*传到目标地址*/
            sendSocket.send(sendPacket);
            sendSocket.close();

            } catch (Exception e) {
                System.out.println("注意！陌生客户端口");
            }
    }

    public static void main(String[] args){
       Server test = new Server();
    }

}
