import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int SERVER_PORT = 9999;
    ServerSocket server = null;
    ProduceWords thread;
    Socket socketAtServer = null;

    public Server()
    {

            try {
                server = new ServerSocket(SERVER_PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //在服务器端建立套接字对象，与客户端建立套接字连接
            while(true)
            {
            try {
                socketAtServer = server.accept();
                System.out.println("客户端地址为：" + socketAtServer.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("等待客户接入...");
            }

            if(socketAtServer != null)
            {
                //与某个客户端建立连接后，就创建一个线程来负责该客户端
                ProduceWords temp = new ProduceWords(socketAtServer);
                new Thread(temp).start();
            }
        }
    }

    public static void main(String[] args)
    {
        new Server();
    }
}
//一个线程类，主要用于生成测试题目
class ProduceWords implements Runnable{

    //两个词库的相对路径
    private static final String PATH_CET = "src/CET6.txt";
    private static final String PATH_MUL = "src/MULTIPLE.txt";

    DataInputStream inputStream = null;
    DataOutputStream outputStream = null;

    int total;//目前总的题目个数
    int right;//目前正确的题目个数
    String answer;//储存正确答案

    public ProduceWords(Socket socket)
    {
        try
        {
            /*使用服务器传入的套接字对象初始化两个流对象
             *并与对应的客户端建立流连接
             */
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            total = 0;
            right = 0;
        }catch (IOException e){}

    }

    /**
     * 接收一个从客户端传入的字符串
     * 在该方法中将字符串与标准答案进行比较，判断客户端选择的正确与否
     * 并在该方法中向客户端传出当前正确个数和当前总的个数
     */

    private boolean checkAnswer()
    {
        try {
            String result = inputStream.readUTF();

            /*使用contain方法是由于多选中，答案不唯一，
               所以将接收的回答与全体答案库比较，判断正误
             */
            if(answer.contains(result))
            {
                right++;//如果回答与答案库匹配，就正确个数+1
//                System.out.println("right");
            }
            //将当前正确个数和总的个数传回客户端
            outputStream.writeInt(right);
            outputStream.writeInt(total);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //由英文选中文意思的出题方法
    private void getTestEnToCh()
    {
        String regex = "[\\s]+";
        Dictionary dictionary = new Dictionary(PATH_CET, regex);
        //使用一个HashMap储存从词典库中随机生成四个的四个选项（四组里面均包括中英文）
        HashMap<String, String> choiceAll = dictionary.getChoice();
        //将其转化为ArrayList，方便通过生成随机数随机排列选项
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choiceAll.entrySet());
        int index = (int) (Math.random()*127*4)%4;//随机生成索引，确定题目中的单词内容

        try {
                outputStream.writeUTF(list.get(index).getKey());//将英文提示传给客户端
                answer = list.get(index).getValue();//并设置正确答案

                //传给客户端四个选项
                for(int i = 0; i < 4; i++)
                    outputStream.writeUTF(list.get(i).getValue());

            } catch (IOException e) {
                e.printStackTrace();
            }

            total++;//每出完一组题，总数目total++；
    }

    //由中文选的英文的方法
    private void getTestChToEn()
    {
        String regex = "[\\s]+";
        Dictionary dictionary = new Dictionary(PATH_CET, regex);
        //使用一个HashMap储存从词典库中随机生成四个的四个选项（均包括中英文）
        HashMap<String, String> choice = dictionary.getChoice();
        //将其转化为ArrayList，方便通过生成随机数随机排列选项
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choice.entrySet());
        int index = (int) (Math.random()*127*4)%4;//随机生成索引，确定题目中的单词内容

        try {
            outputStream.writeUTF(list.get(index).getValue());
            answer = list.get(index).getKey();

            for(int i = 0; i < 4; i++)
                outputStream.writeUTF(list.get(i).getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }

        total++;
    }

    //生成多选测试的方法
    private void getMultipleTest()
    {
        Dictionary dictionaryEn = new Dictionary(PATH_CET,"[\\s]+");
        Dictionary dictionaryCh = new Dictionary(PATH_MUL, "[：]+");
        String[][] word = new String[4][];
        String[] choice = new String[4];
        String question;

        /*
        *由于从Dictionary内中获取的四组数据，value中会包含多个意思
        *生成多选便基于此，将value中的字符串进行二次分割，分离出多个正确选项
        * 中选英和英选中，由于词库的限制就使用了两个词库来分别实现
        * */
        while(true)//为了避免出现异常的选项，使用while循环，直至选项内容正常
        {
            int type = (int) (Math.random()*127)%2;
            if(type==0)
            {
                //使用一个HashMap储存从词典库中随机生成四个的四个选项
                HashMap<String, String> choiceAll = dictionaryEn.getChoice();
                //将其转化为ArrayList，方便通过生成随机数随机排列选项
                ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choiceAll.entrySet());
                question = list.get(0).getKey();
                answer = list.get(0).getValue();
                for (int i = 0; i < 4; i++)
                    word[i] = list.get(i).getValue().split("[a-z\\s，；,.]+");
            }
            else
            {
                //使用一个HashMap储存从词典库中随机生成四个的四个选项
                HashMap<String, String> choiceAll = dictionaryCh.getChoice();
                //将其转化为ArrayList，方便通过生成随机数随机排列选项
                ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choiceAll.entrySet());
                question = list.get(0).getKey();
                answer = list.get(0).getValue();
                for (int i = 0; i < 4; i++)
                    word[i] = list.get(i).getValue().split("[\\s，；,.]+");
            }

            //一种异常选项
            if(word[0].length<2)
                continue;

            //使用该数组储存生成的选项
            choice[0] = word[0][0];
            choice[1] = word[0][1];
            choice[2] = word[3][0];
            choice[3] = word[1][0];
            //第二种异常选项
            if(choice[0].equals("")||choice[1].equals("")||choice[2].equals("")||choice[3].equals(""))
                continue;

//            for(int i = 0; i < 4;i++)
////                System.out.println(choice[i]);

            try {
                //向客户端传回相关的题目，多选题会在右上角有'*'，表示该题为多选
                outputStream.writeUTF(question + " *");
                List<Integer> indexTemp = new ArrayList<>();
                for(int i = 0; i < 4; i++)
                {
                    //简单的将四个选项以随机顺序的传回客户端
                    int t = (int) (Math.random() * 127) % 4;
                    while (indexTemp.contains(t))
                        t = (int) (Math.random() * 127) % 4;

                    indexTemp.add(t);

                    outputStream.writeUTF(choice[t]);
                }
                total++;
                break;//题目生成后结束外层循环
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true)
        {
            //题目出完则结束循环
            if(total >= 10)
                break;

            //依此来确定题目的类型
            int type = (int) (Math.random()*127);

            if(type%3==0)
                getTestEnToCh();
            else if (type%3==1)
                getTestChToEn();
            else
                getMultipleTest();

            //检查回答是否正确
            checkAnswer();
        }
    }

}
