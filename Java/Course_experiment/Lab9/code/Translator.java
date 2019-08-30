import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.json.*;
import com.baidu.translate.demo.TransApi;

public class Translator extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	//ʹ���˰ٶȷ����API�������ǰٶȷ���API��ID����Կ
	private static final String APP_ID = "20171125000099295";
    private static final String SECURITY_KEY = "F5dkC2qJBKwd7dckRtuC";
    private static TransApi api = new TransApi(APP_ID,SECURITY_KEY);
    
	private String from = "en";
	private String to = "zh";
	
	//�������ı����Ժ�Ŀ�����ԣ��ó�����Ĭ��ֻ��Ӣ�ĺ�����
	JLabel soulan;
	JLabel deslan;
	
	JButton translate;//���밴ť
	JButton exchange;//�л�����Ӣ��Ӣ����ģʽ�İ�ť
	
	JTextArea source;//����������������ı�
	JTextArea  result;//��������
	
	public Translator()
	//���췽������ʼ��һЩ�������������ʵ�ּ򵥵Ĳ���
	{
		this.setTitle("Translator");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�����ǳ�ʼ������ 
		translate = new JButton("Translate");
		translate.setActionCommand("translate");
		translate.setForeground(Color.BLUE);
		translate.addActionListener(this);
		
		exchange = new JButton(); 
		exchange.setPreferredSize(new Dimension(80,30));
		exchange.setIcon(new ImageIcon("src/exchange.png"));
		exchange.addActionListener(this);
		exchange.setActionCommand("exchange");
		exchange.setName("Exchange");
		
		source = new JTextArea(4,10);
		source.setLineWrap(true);
		
		result = new JTextArea(4,10);
		result.setEditable(false);
		result.setLineWrap(true);
		
		//Ĭ�ϳ��η�����Ӣ����
		soulan = new JLabel("Ӣ��");
		deslan = new JLabel("����");
		
		//�ϲ�İ�ť��壬�����������ı������Ժ�Ŀ�����ԣ��л���ť�����밴ť
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(soulan);
		soulan.setLocation(5,5);
		buttonPanel.add(exchange);
		exchange.setLocation(300, 5);
		buttonPanel.add(deslan);
		deslan.setLocation(320, 5);
		buttonPanel.add(translate);
		translate.setLocation(630, 5);
		
		//�²���ı�����壬���������ı���ͽ���ı���
		JPanel textArea = new JPanel();
		textArea.add(source);
		source.setBounds(0, 50, 280, 275);
		textArea.add(result);
		result.setBounds(360, 50, 280, 275);
		
		JPanel translator = new JPanel();
		translator.add(buttonPanel);
		translator.add(textArea);
		
		this.add(translator);
		this.setBounds(360, 240, 640, 250);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource()==exchange )
		//����л���ť������soulan�� deslan�е�ֵ
		{
			String str = from;
			from = to;
			to = str;
			
			soulan.setText( from.equals("en") ? "Ӣ��" : "����" );
			deslan.setText( to.equals("zh") ? "����" : "Ӣ��" );
		}
		else if( e.getSource()==translate )
		//������밴ť������getResult()�������õ�������
		{
			String query = source.getText();//��ȡ�������ı�
			result.setText( 
					getResult( query , from , to ) );//���ý���ı�
		}
	}

	private String getResult(String query, String from, String to)
	{
	    
	    if(query.isEmpty())
	    {
	    	return null;
	    }
	    
	    //���ðٶȷ���APIʵ�ַ�������������ط�����
	    String result = api.getTransResult(query, from, to);
	    //��API���ص�JSON��ʽ����ȡ��������
	    try {
			JSONObject jsonObject = new JSONObject( result );
			JSONArray jsonArray = (JSONArray)jsonObject.get( "trans_result" );
			JSONObject _result = jsonArray.getJSONObject(0);
			result = _result.get( "dst" ).toString();
		}catch( Exception ect )
		{
			ect.printStackTrace();
		}
		return result;
	    
	}
	
	public static void main(String[] args)
	{
		//ʹ�ý�����Ӧ��ǰϵͳ�ķ��
		try{
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		}catch( Exception e ){
			e.printStackTrace();
		}
		
		javax.swing.SwingUtilities.invokeLater( new Runnable() {
			public void run()
			{
				Translator translator = new Translator();
			}
		});
	}
}