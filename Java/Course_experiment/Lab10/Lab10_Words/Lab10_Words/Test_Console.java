import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test_Console extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel question;		//显示问题，即给出的单词内容或许中文或许英文
	JButton A,B,C,D;	//选项按钮
	JLabel answer_A, answer_B, answer_C, answer_D;	//显示选项的内容
	JLabel accuracy;		//实时显示总的题目数和正确的题目数量
	int times;			//测试的题目数
	int right;			//正确的题目数
	String answer;			//正确的答案内容
	boolean isRunning;		//测试是否依然进行
	
	public Test_Console()
	//初始化各类组件
	{
		this.setTitle("WordsTest");
		this.setLayout(null);		//使用了空布局，使用setBounds()自定义各组件的位置
		this.setBounds(360, 240, 900, 630);
		
		isRunning = true;
		times = 0;
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
		
	}
	//设置当前测试的正确答案
	public void setAnswer(String answer){
		this.answer = answer;
	}
	//设置精确度，显示正确的个数和总的个数
	public void setAccuracy(){
		accuracy.setText("Accuracy: "+right+"/"+times);
	}
	//设置测试的次数，并在相应的时候执行某些操作
	public void setTimes(){
		times++;
		if(times > 10)
			confirmResult();
	}
	//获取已经进行了测试的次数
	public int getTimes() {
		return times;
	}
	//获取测试是否还在进行
	public boolean getFrameState() {
		return isRunning;
	}
	//结束前会弹出对话框，显示你正确的个数
	private void confirmResult() {
		int t = times-1;
		JOptionPane.showMessageDialog(null, "此次测试一共"+t+
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
	//检查选择的答案是否正确
	private boolean check(String ans){
		if(ans.equals(answer))
		{
			right++;
			return true;
		}
		else
			return false;
	}
	//重置选项栏的背景颜色
	public void setColor(){
		answer_A.setBackground(null);
		answer_B.setBackground(null);
		answer_C.setBackground(null);
		answer_D.setBackground(null);
	}
	//使按钮失效，在每道题选择了之后便不能再选了
	private void setDisability(){
		A.setEnabled(false);
		B.setEnabled(false);
		C.setEnabled(false);
		D.setEnabled(false);
	}
	//使按钮有效，下一道题出现时，是按钮能够被点击
	public void setAbility(){
		A.setEnabled(true);
		B.setEnabled(true);
		C.setEnabled(true);
		D.setEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ans = null;
		JLabel temp = null;
		if(e.getSource() == A){
			ans = answer_A.getText();
			temp = answer_A;
		}
		else if(e.getSource() == B){
			ans = answer_B.getText();
			temp = answer_B;
		}
		else if(e.getSource() == C) {
			ans = answer_C.getText();
			temp = answer_C;
		}
		else if(e.getSource() == D) {
			ans = answer_D.getText();
			temp = answer_D;
		}
		
		//如果选择的选项正确，就是选择的选项变绿；若果错误就变红
		if(check(ans)) {
			temp.setBackground(new Color(0, 255, 25));
		}else {
			temp.setBackground(new Color(255,72,72));
		}
		setDisability();
		setAccuracy();
	}
	
	public static void main(String[] args)
	{
		try{
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		}catch( Exception e ){
			e.printStackTrace();
		}
		Test_Console TC = new Test_Console();	//声明一个窗体
		GivesWords word = new GivesWords(TC);	
		Thread giveW = new Thread(word);		//创建一个givesWords线程
		giveW.start();
	}
}
