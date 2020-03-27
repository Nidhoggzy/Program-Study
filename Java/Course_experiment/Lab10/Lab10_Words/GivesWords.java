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
		//当窗体没有被关闭以及测试次数不达到上限30时，循环以下操作
		{
			myFrame.setAbility();
			myFrame.setTimes();//设置当前测试的次数
			
			//使用一个HashMap储存从词典库中随机生成四个的四个选项	
			HashMap<String, String> choice = myDic.getChoice();
			//将其转化为ArrayList，方便通过生成随机数随机排列选项
			ArrayList<Map.Entry<String, String>> list = new ArrayList<>(choice.entrySet());
				
			int index = (int) (Math.random()*100*4)%4;//随机生成索引，确定题目中的单词内容
			int temp = (int) (Math.random()*1000);//生成随机数，使随机出现中文选英文，和英文选中文
			myFrame.setColor();
			
			/*如果temp是奇数，就生成为英文选中文；temp为偶数就生成为中文选英文
			   中选英 和 英选中 出现的概率相等
			  list中的key是英文，value是中文，在设置模式时，只需将需要填写的文本将key和value互换*/
			
			if(temp%2==1)
			{
				myFrame.question.setText(list.get(index).getKey());//设置问题中的文本
				myFrame.setAnswer(list.get(index).getValue());//设置正确答案，方便比较
				//设置四个选项中的内容
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
			
			myFrame.setAccuracy();//实时显示做题正确的个数
				
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
