import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.*;

public class Test_Console extends JFrame implements  ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int SERVER_PORT = 9999;

    JLabel question;		//显示问题，即给出的单词内容或许中文或许英文
    JButton A,B,C,D;	//选项按钮
    JLabel answer_A, answer_B, answer_C, answer_D;	//显示选项的内容
    JLabel accuracy;		//实时显示总的题目数和正确的题目数量
    int total;			//测试的题目数
    int right;			//正确的题目数
    String answer;			//正确的答案内容
    boolean isRunning;		//测试是否依然进行
    Socket socket = null;
    DataInputStream in = null;
    DataOutputStream out = null;

    public Test_Console()
    //初始化各类组件
    {
        this.setTitle("WordsTest");
        this.setLayout(null);		//使用了空布局，使用setBounds()自定义各组件的位置
        this.setBounds(360, 240, 900, 630);

        isRunning = true;
        total = 0;
        right = 0;

        accuracy = new JLabel("Accuracy: 0/0", JLabel.CENTER);
        accuracy.setBounds(640, 10, 150, 27);
        accuracy.setFont(new Font("Serif",Font.BOLD+Font.ITALIC, 22));

        String inti = null;
        question = new JLabel(inti,JLabel.CENTER);
        question.setBounds(185, 50, 500, 120);
        question.setOpaque(true);
        question.setFont(new Font("Serif",Font.ITALIC, 24));

        //初始化选项按钮和选项框，设置其中的位置，大小，字体以及透明度
        A = new JButton("A");
        A.addActionListener(this);
        A.setFont(new Font("Serif",Font.ITALIC, 18));
        answer_A = new JLabel();
        A.setBounds(160, 240, 45, 45);
        answer_A.setBounds(220, 240, 240, 45);
        answer_A.setBorder(null);
        answer_A.setFont(new Font("Serif",Font.ITALIC, 20));
        answer_A.setOpaque(true);

        B = new JButton("B");
        B.addActionListener(this);
        B.setFont(new Font("Serif",Font.ITALIC, 18));
        answer_B = new JLabel();
        B.setBounds(560, 240, 45, 45);
        answer_B.setBounds(620, 240, 240,45);
        answer_B.setFont(new Font("Serif",Font.ITALIC, 20));
        answer_B.setOpaque(true);

        C = new JButton("C");
        C.addActionListener(this);
        C.setFont(new Font("Serif",Font.ITALIC, 18));
        answer_C = new JLabel();
        C.setBounds(160, 320, 45, 45);;
        answer_C.setBounds(220, 320, 240, 45);
        answer_C.setFont(new Font("Serif",Font.ITALIC, 20));
        answer_C.setOpaque(true);

        D = new JButton("D");
        D.addActionListener(this);
        D.setFont(new Font("Serif",Font.ITALIC, 18));
        answer_D = new JLabel();
        D.setBounds(560, 320, 45, 45);
        answer_D.setBounds(620, 320, 240, 45);
        answer_D.setFont(new Font("Serif",Font.ITALIC, 20));
        answer_D.setOpaque(true);

        this.add(accuracy);
        this.add(question);
        this.add(A);
        this.add(answer_A);
        this.add(B);
        this.add(answer_B);
        this.add(C);
        this.add(answer_C);
        this.add(D);
        this.add(answer_D);
        //当点击右上角退出时，弹出对话窗口确认
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                closeFrame();
            }
        });
        this.setVisible(true);

        //客户端声明套接字对象，与服务器实现套接字连接
        socket = new Socket();
        if(!socket.isConnected())
        {
            try {

                InetAddress address = InetAddress.getByName("127.0.0.1");
                InetSocketAddress socketAddress = new InetSocketAddress(address, SERVER_PORT);
                //连接服务器
                socket.connect(socketAddress);
                //与服务器建立流连接
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

            }  catch (IOException e) {
                e.printStackTrace();
                socket = new Socket();
            }
        }
        //新建一个线程接收服务器生成的测试题目
        new Thread(new GetTest(this)).start();

    }

    //设置精确度，显示正确的个数和总的个数
    public void setAccuracy(){
        accuracy.setText("Accuracy: "+right+"/"+total);

        if(total >= 10)
            confirmResult();
    }

    //获取测试是否还在进行
    public boolean getFrameState() {
        return isRunning;
    }
    //结束前会弹出对话框，显示你正确的个数
    private void confirmResult() {
        JOptionPane.showMessageDialog(null, "此次测试一共"+total+
                "道题\n您做对了"+right+"道题", "Result", JOptionPane.YES_OPTION);
        this.dispose();
        isRunning = false;

    }
    //关闭窗体，结束测试
    private void closeFrame() {
        int result = JOptionPane.showConfirmDialog(null, "你确定要退出此次测试？", "End",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION)
        {
            confirmResult();
            isRunning = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ans = null;
        if(e.getSource() == A){
            ans = answer_A.getText();
        }
        else if(e.getSource() == B){
            ans = answer_B.getText();
        }
        else if(e.getSource() == C) {
            ans = answer_C.getText();
        }
        else if(e.getSource() == D) {
            ans = answer_D.getText();
        }

        //点击相关按钮后，将对应选项的内容传回服务器，判断正确与否
        try {
            out.writeUTF(ans);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public static void main(String[] args)
    {
        try{
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }catch( Exception e ){
            e.printStackTrace();
        }
        Test_Console TC = new Test_Console();	//声明一个窗体
    }

}

/*单独的一个线程类实现了Runnable接口，用于接收服务器生成的测试题目*/
class GetTest implements Runnable{
    Test_Console words;
    public GetTest(Test_Console words){
        this.words =words;
    }

    @Override
    public void run() {
        DataInputStream in = words.in;

        while(true)
        {
            try {
                //接收问题和选项
                String questionText = in.readUTF();
                String choiceA = in.readUTF();
                String choiceB = in.readUTF();
                String choiceC = in.readUTF();
                String choiceD = in.readUTF();

                words.question.setText(questionText);
                words.answer_A.setText(choiceA);
                words.answer_B.setText(choiceB);
                words.answer_C.setText(choiceC);
                words.answer_D.setText(choiceD);
//                System.out.println("start");
                //接收对应的数目
                words.right = in.readInt();
                words.total = in.readInt();
//                System.out.println("end");
                words.setAccuracy();

                if(words.total >= 10||!words.getFrameState())
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
