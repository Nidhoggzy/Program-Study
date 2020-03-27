/*
 * ��score.txt�ļ����д���
 * �ֱ�õ�ÿ���鱦��Ʒ�����ݼ�¼�����ʱ�����������
 * ��ÿ������ѷ�û��ڸö�ʱ����鱦������
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class MyProcess {

	String source;
	
	public MyProcess() {
		source = "score.txt";
	}
	
	public void getSalesAndPrchs(String desSales, String desPrchs) {
		//ʹ��FileReader��BufferedReader��ȡ����
		FileReader reader = null;
		BufferedReader input = null;
		//ʹ��FileWriter��BufferedWriterд����
		FileWriter writerSales = null, writerPrchs = null;
		BufferedWriter outputSales = null, outputPrchs = null;
		
		//ʹ��HashMap�ֱ𴢴� ��Ӧ������
		//��Id��Ϊkey���������͹�������Ϊvalue
		HashMap<String, Integer> productSales = new HashMap<String, Integer>();
		HashMap<String, Integer> userPurchase = new HashMap<String, Integer>();
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			
			String line = null;
			while(true){
				line = input.readLine();
				if(line == null)
					break;
				
				String regex = "; ";
				String[] result = line.split(regex);
				
				if(result.length > 3) continue;
				//ͳ��ÿ����Ʒ������
				productSales.putIfAbsent(result[1], 0);
				productSales.put(result[1], productSales.get(result[1])+1);
				//ͳ��ÿ���û����鱦������
				userPurchase.putIfAbsent(result[0], 0);
				userPurchase.put(result[0], userPurchase.get(result[0])+1);	
			}
			
			/*
			 * ͳ����󣬶Դ�������HashMap���б���
			 * �����д���ļ���
			 */
			
			writerSales = new FileWriter(desSales);
			outputSales = new BufferedWriter(writerSales);
			
			Iterator<Entry<String, Integer>> iterProduct = productSales.entrySet().iterator();
			while(iterProduct.hasNext()) {
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterProduct.next();
					
				String productId = entry.getKey();
				Integer tempS = entry.getValue();

				outputSales.write(productId + "; " + tempS);
				outputSales.newLine();
			}
			
			writerPrchs = new FileWriter(desPrchs);
			outputPrchs = new BufferedWriter(writerPrchs);
			
			Iterator<Entry<String, Integer>> iterUser = userPurchase.entrySet().iterator();
			while(iterUser.hasNext()) {
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterUser.next();
					
				String userId = entry.getKey();
				Integer tempP = entry.getValue();

				outputPrchs.write(userId + "; " + tempP);
				outputPrchs.newLine();
			}
			
			outputSales.flush();outputSales.close();outputPrchs.flush();outputPrchs.close();input.close();
			reader.close();writerSales.close();writerPrchs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		 * ������Ӧ�Ľ���ļ���
		 * �����������һ��ʵ�����������з����õ����
		 */
		String desSales = "productSales.txt";
		String desPrchs = "userPurchase.txt";
		
		File productSales = new File(desSales);
		File userPurchase = new File(desPrchs);
		try {
			productSales.createNewFile();
			userPurchase.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		MyProcess MP =new MyProcess();
		MP.getSalesAndPrchs(desSales, desPrchs);
	}

}
