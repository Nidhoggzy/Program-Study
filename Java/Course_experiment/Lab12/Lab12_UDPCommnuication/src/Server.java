import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;

public class Server {
    private final static int SERVER_PORT = 2048;

    DatagramSocket serverScoket = null;
    DatagramPacket packet = null;

    public Server()
    {
        System.out.println("�ȴ��ͻ��˽���");
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
                /*�������ݲ�������д���*/
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

            /*ͨ�����ִ������ݵĽӿ����������ĸ��ͻ��˴�����Ϣ����ȷ��Ŀ���ַ*/
            int recevierPort = 0;
            if(senderPort == ClientA.PORT_A_SEND)
                recevierPort = ClientB.PORT_B_RECEIVE;
            else if(senderPort == ClientB.PORT_B_SEND)
                recevierPort = ClientA.PORT_A_RECEIVE;
            else
                throw new Exception();

            System.out.println("�ͻ���" +senderPort +"����"  + address);
            /*����������ݵ�ʱ�䣬���涨ʱ�������ʽ*/
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
            System.out.println(dateFormat.format(System.currentTimeMillis()));
            /*���������ݺ�����ص���Ϣ��ʱ�䣬��ַ������*/
            messeages = dateFormat.format(System.currentTimeMillis()) + "�յ�����" + senderPort + address + "����Ϣ\n" + messeages;
            /*��������������Ϣ���*/
            DatagramSocket sendSocket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(messeages.getBytes(), messeages.length(),
                    InetAddress.getByName("127.0.0.1"), recevierPort);
            /*����Ŀ���ַ*/
            sendSocket.send(sendPacket);
            sendSocket.close();

            } catch (Exception e) {
                System.out.println("ע�⣡İ���ͻ��˿�");
            }
    }

    public static void main(String[] args){
       Server test = new Server();
    }

}
