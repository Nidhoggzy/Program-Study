import java.util.*;

public class GivesWords implements Runnable {

	Test_Console myFrame;
	Dictionary myDic;
	
	GivesWords(Test_Console myFrame)
	{
		this.myFrame = myFrame;
		myDic =new Dictionary("CET6.txt");
	}
	
	@Override
	public void run() {

		while(myFrame.getFrameState()&&myFrame.getTimes()<=10)
		//������û�б��ر��Լ����Դ������ﵽ����30ʱ��ѭ�����²���
		{
			myFrame.setAbility();
			myFrame.setTimes();//���õ�ǰ���ԵĴ���
			
			//ʹ��һ��HashMap����Ӵʵ������������ĸ����ĸ�ѡ��	
			HashMap<String, String> choice = myDic.getChoice();
			//����ת��ΪArrayList������ͨ������������������ѡ��
			ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choice.entrySet());
				
			int index = (int) (Math.random()*100*4)%4;//�������������ȷ����Ŀ�еĵ�������
			int temp = (int) (Math.random()*1000);//�����������ʹ�����������ѡӢ�ģ���Ӣ��ѡ����
			myFrame.setColor();
			
			/*���temp��������������ΪӢ��ѡ���ģ�tempΪż��������Ϊ����ѡӢ��
			   ��ѡӢ �� Ӣѡ�� ���ֵĸ������
			  list�е�key��Ӣ�ģ�value�����ģ�������ģʽʱ��ֻ�轫��Ҫ��д���ı���key��value����*/
			
			if(temp%2==1)
			{
				myFrame.question.setText(list.get(index).getKey());//���������е��ı�
				myFrame.setAnswer(list.get(index).getValue());//������ȷ�𰸣�����Ƚ�
				//�����ĸ�ѡ���е�����
				myFrame.answer_A.setText(list.get(0).getValue());
				myFrame.answer_B.setText(list.get(1).getValue());
				myFrame.answer_C.setText(list.get(2).getValue());
				myFrame.answer_D.setText(list.get(3).getValue());
			}
			else
			{
				myFrame.question.setText(list.get(index).getValue());
				myFrame.setAnswer(list.get(index).getKey());
					
				myFrame.answer_A.setText(list.get(0).getKey());
				myFrame.answer_B.setText(list.get(1).getKey());
				myFrame.answer_C.setText(list.get(2).getKey());
				myFrame.answer_D.setText(list.get(3).getKey());
					
			}
			
			myFrame.setAccuracy();//ʵʱ��ʾ������ȷ�ĸ���
				
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
