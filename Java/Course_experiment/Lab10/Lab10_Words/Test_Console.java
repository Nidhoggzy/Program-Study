import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test_Console extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel question;		//��ʾ���⣬�������ĵ������ݻ������Ļ���Ӣ��
	JButton A,B,C,D;	//ѡ�ť
	JLabel answer_A, answer_B, answer_C, answer_D;	//��ʾѡ�������
	JLabel accuracy;		//ʵʱ��ʾ�ܵ���Ŀ������ȷ����Ŀ����
	int times;			//���Ե���Ŀ��
	int right;			//��ȷ����Ŀ��
	String answer;			//��ȷ�Ĵ�����
	boolean isRunning;		//�����Ƿ���Ȼ����
	
	public Test_Console()
	//��ʼ���������
	{
		this.setTitle("WordsTest");
		this.setLayout(null);		//ʹ���˿ղ��֣�ʹ��setBounds()�Զ���������λ��
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
		
		//��ʼ��ѡ�ť��ѡ����������е�λ�ã���С�������Լ�͸����
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
		//��������Ͻ��˳�ʱ�������Ի�����ȷ��
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)  
            {   
                closeFrame();  
            }  
		});
		this.setVisible(true);
		
	}
	//���õ�ǰ���Ե���ȷ��
	public void setAnswer(String answer){
		this.answer = answer;
	}
	//���þ�ȷ�ȣ���ʾ��ȷ�ĸ������ܵĸ���
	public void setAccuracy(){
		accuracy.setText("Accuracy: "+right+"/"+times);
	}
	//���ò��ԵĴ�����������Ӧ��ʱ��ִ��ĳЩ����
	public void setTimes(){
		times++;
		if(times > 10)
			confirmResult();
	}
	//��ȡ�Ѿ������˲��ԵĴ���
	public int getTimes() {
		return times;
	}
	//��ȡ�����Ƿ��ڽ���
	public boolean getFrameState() {
		return isRunning;
	}
	//����ǰ�ᵯ���Ի�����ʾ����ȷ�ĸ���
	private void confirmResult() {
		int t = times-1;
		JOptionPane.showMessageDialog(null, "�˴β���һ��"+t+
				"����\n��������"+right+"����", "Result", JOptionPane.YES_OPTION);  
        this.dispose();  
        isRunning = false;

	}
	//�رմ��壬��������
	private void closeFrame() {
		int result = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳��˴β��ԣ�", "End", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
		
        if (result == JOptionPane.YES_OPTION)  
        {   
        	confirmResult();  
        	isRunning = false;
        }
	}
	//���ѡ��Ĵ��Ƿ���ȷ
	private boolean check(String ans){
		if(ans.equals(answer))
		{
			right++;
			return true;
		}
		else
			return false;
	}
	//����ѡ�����ı�����ɫ
	public void setColor(){
		answer_A.setBackground(null);
		answer_B.setBackground(null);
		answer_C.setBackground(null);
		answer_D.setBackground(null);
	}
	//ʹ��ťʧЧ����ÿ����ѡ����֮��㲻����ѡ��
	private void setDisability(){
		A.setEnabled(false);
		B.setEnabled(false);
		C.setEnabled(false);
		D.setEnabled(false);
	}
	//ʹ��ť��Ч����һ�������ʱ���ǰ�ť�ܹ������
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
		
		//���ѡ���ѡ����ȷ������ѡ���ѡ����̣���������ͱ��
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
		Test_Console TC = new Test_Console();	//����һ������
		GivesWords word = new GivesWords(TC);	
		Thread giveW = new Thread(word);		//����һ��givesWords�߳�
		giveW.start();
	}
}
