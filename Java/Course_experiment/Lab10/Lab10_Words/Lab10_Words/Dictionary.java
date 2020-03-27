import java.io.*;
import java.util.*;

public class Dictionary {

	private String path;//�����ļ��ĵ�ַ
	//ʹ��ArrayList�ֱ�洢Ӣ�ĵ��ʼ����Ӧ�����Ľ��ͣ������������ѡ��
	private final List<String> keyWord = new ArrayList<>();//Ӣ�ĵ���
	private final List<String> valueWord = new ArrayList<>();//���Ľ���
	
	Dictionary(String path)
	{
		this.path = path;
		initiDic();
	}
	
	private void initiDic()
	//ʹ��FileReader��BUfferedReader���ļ��ж�ȡ��������
	{
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			while(true) {
				String line = bufferedReader.readLine();
				if(line==null) break;
				
				String regex = "[\\s]+";
				String[] word = line.split(regex);
				
				if(word.length < 2) continue;
				//��Ӣ�ļ���keyWord�У����Ӧ�����ļ���valueWord�У�
				//һ�鵥�ʵ���Ӣ��������ArrayList�е�������һ�µ�
				keyWord.add(word[0]);
				valueWord.add(word[1]);
			}
		} catch(Exception e){
            System.out.println(e);
        }
		finally {
			try{
                bufferedReader.close();
                reader.close();

            }catch(Exception e){
                System.out.println(e);
            }
		}
	}
	
	private int getRandomIndex()
	//�������������������������ĸ�����ѡ��
	{
		int size = keyWord.size();
		return (int) (Math.random()*100*size)%size;
	}
	
	HashMap<String, String> getChoice()
	//�����ĸ�ѡ��
	{
		//��ֹ���ɵ����������ظ�����֤�ĸ�ѡ��ظ�
		List<Integer> indexTemp = new ArrayList<>();
		//���ɵ�ѡ����HashMap�洢���Ա��ֶ���֮��Ķ�Ӧ��ϵ
		HashMap<String, String> choiceTemp = new HashMap<>();
		for (int i = 0; i < 4 ; i++) {
            int index = getRandomIndex();
            while( indexTemp.contains( index ) )
            //������ɵ��������ظ���һֱ����ֱ��û���ظ�
            {
                index = getRandomIndex();
            }
            
            indexTemp.add( index );
            
            choiceTemp.put(keyWord.get(index), valueWord.get(index));
		}
		
		return choiceTemp;
	}
}
