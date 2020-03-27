/*
 * ����score.txt �ļ��õ����ݵ���������
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class CalculateAvg {

	String source;
	//Ϊ�˼������־�ֵ���ֱ�������HashMap�洢��Ʒ���û�������
	//��ID��Ϊkey����һ��Double�ʹ�СΪ2��������Ϊvalue�������һ�����ݴ洢�����ĺͣ��ڶ����洢�ܵĸ���
	HashMap<String, Double[]> product;
	HashMap<String, Double[]> user;

	public CalculateAvg() {
		source = "score.txt";
		product = new HashMap<String, Double[]>();
		user = new HashMap<String, Double[]>();
	}
	
	public void Process(String desPro, String desUser) {
		FileReader reader = null;
		BufferedReader input = null;
		//���д���Ʒ��ֵ�Ľ��
		FileWriter writerPro = null;
		BufferedWriter outputPro = null;
		//���д���û���ֵ���
		FileWriter writerUse = null;
		BufferedWriter outputUse = null;
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			String line = null;
			
			while(true) {
				line = input.readLine();
				if(line == null)
					break;
				
				String regex = "; ";
				String[] result = line.split(regex);
				
				if(result.length < 3)
					continue;
				/*
				 * �Էָ������з���
				 * ����ͬ�����ݷֱ�����Ӧ��HashMap
				 */
				if(user.containsKey(result[0])) {
					//����Ѿ���¼�����û����ݣ�����ԭ���ݵĻ����ϸ���������
					user.get(result[0])[0] += Double.parseDouble(result[2]);
					user.get(result[0])[1]++;
				}
				else {
					//������½�һ����ά���飬��ʼ��������Ϊ��ǰ�������Լ�����Ϊ1��������HashMap�У�
					Double[] temp = new Double[2];
					temp[0] = Double.parseDouble(result[2]);
					temp[1] = 1.0;
					user.put(result[0], temp);
				}
				//����Բ�Ʒ���ֵĴ�����ǰ������
				if(product.containsKey(result[1])) {
					product.get(result[1])[0] += Double.parseDouble(result[2]);
					product.get(result[1])[1]++;
				}
				else {
					Double[] temp = new Double[2];
					temp[0] = Double.parseDouble(result[2]);
					temp[1] = 1.0;
					product.put(result[1], temp);
				}
			}
			//�ֱ������HashMap���б������������д���Ӧ�ļ�
			writerPro = new FileWriter(desPro);
			outputPro = new BufferedWriter(writerPro);
			
			Iterator<Entry<String, Double[]>> iterProduct = product.entrySet().iterator();
			while(iterProduct.hasNext()) {
				Map.Entry<String, Double[]> entryPro = (Map.Entry<String, Double[]>) iterProduct.next();
				
				String productId = entryPro.getKey();
				Double[] tempPro = entryPro.getValue();

				double proAvg = tempPro[0]/tempPro[1];
				outputPro.write(productId + "; " + proAvg);
				outputPro.newLine();
			}
			
			writerUse = new FileWriter(desUser);
			outputUse = new BufferedWriter(writerUse);
			
			Iterator<Entry<String, Double[]>> iterUse = user.entrySet().iterator();
			while(iterUse.hasNext()) {
				Map.Entry<String, Double[]> entryUse = (Map.Entry<String, Double[]>) iterUse.next();
				
				String userId = entryUse.getKey();
				Double[] tempUse = entryUse.getValue();
				
				double useAvg = tempUse[0]/tempUse[1];
				outputUse.write(userId + "; " + useAvg);
				outputUse.newLine();
			}
			outputPro.flush();outputUse.flush();outputPro.close();outputUse.close();input.close();
			reader.close();writerPro.close();writerUse.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		 * ��������ļ���������CalculateAvg��Ķ���
		 * �������еķ�����ȡ���
		 */
		String desproAvg = "productAvg.txt";
		String desuseAvg = "userAvg.txt";
		
		File productAvg = new File(desproAvg);
		File userAvg = new File(desuseAvg);
		
		try {
			productAvg.createNewFile();
			userAvg.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		CalculateAvg CA = new CalculateAvg();
		CA.Process(desproAvg, desuseAvg);
	}

}
