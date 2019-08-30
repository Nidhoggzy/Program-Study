import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.json.*;
import com.baidu.translate.demo.TransApi;

public class Translator extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	//使用了百度翻译的API，下面是百度翻译API的ID和密钥
	private static final String APP_ID = "20171125000099295";
    private static final String SECURITY_KEY = "F5dkC2qJBKwd7dckRtuC";
    private static TransApi api = new TransApi(APP_ID,SECURITY_KEY);
    
	private String from = "en";
	private String to = "zh";
	
	//待翻译文本语言和目标语言，该程序中默认只有英文和中文
	JLabel soulan;
	JLabel deslan;
	
	JButton translate;//翻译按钮
	JButton exchange;//切换中译英和英译中模式的按钮
	
	JTextArea source;//输入框，输入待翻译的文本
	JTextArea  result;//翻译结果框
	
	public Translator()
	//构造方法，初始化一些组件和容器，并实现简单的布局
	{
		this.setTitle("Translator");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//下面是初始化各种 
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
		
		//默认初次翻译是英译中
		soulan = new JLabel("英文");
		deslan = new JLabel("中文");
		
		//上层的按钮面板，包括带翻译文本的语言和目标语言，切换按钮，翻译按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(soulan);
		soulan.setLocation(5,5);
		buttonPanel.add(exchange);
		exchange.setLocation(300, 5);
		buttonPanel.add(deslan);
		deslan.setLocation(320, 5);
		buttonPanel.add(translate);
		translate.setLocation(630, 5);
		
		//下层的文本框面板，包括输入文本框和结果文本框
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
		//点击切换按钮，交换soulan和 deslan中的值
		{
			String str = from;
			from = to;
			to = str;
			
			soulan.setText( from.equals("en") ? "英文" : "中文" );
			deslan.setText( to.equals("zh") ? "中文" : "英文" );
		}
		else if( e.getSource()==translate )
		//点击翻译按钮，调用getResult()方法，得到翻译结果
		{
			String query = source.getText();//获取待翻译文本
			result.setText( 
					getResult( query , from , to ) );//设置结果文本
		}
	}

	private String getResult(String query, String from, String to)
	{
	    
	    if(query.isEmpty())
	    {
	    	return null;
	    }
	    
	    //调用百度翻译API实现翻译操作，并返回翻译结果
	    String result = api.getTransResult(query, from, to);
	    //从API返回的JSON格式中提取出翻译结果
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
		//使该界面适应当前系统的风格
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