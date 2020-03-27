/*
 * ����Jewelry.txt�ļ��õ���صĲ�Ʒ���û������֡�������Ϣ
 */
import java.io.*;
import java.util.HashSet;

public class ProcessData {

	String source;
	
	public ProcessData(){
		source = "Jewelry.txt";
	}
	
	public void getProduct(String destination) {
		//�����ļ��������ԣ�ʹ��FileReader��BufferedReader���ļ����ж���
		FileReader reader = null;
		BufferedReader input = null;
		//���������ʽ�����ƣ�Ҳʹ��FileWriter��BufferedWriter�����д���ļ�
		FileWriter writer = null;
		BufferedWriter output = null;
		//ʹ��һ��HashSet��¼productId����ֹ�ظ���¼��Ʒ��Ϣ
		HashSet<String> productId = new HashSet<String>();
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			writer = new FileWriter(destination);
			output = new BufferedWriter(writer);
			
			String line = null;
			while(true)
			{
				line = input.readLine();//���ж�ȡ�ļ�
				if(line == null)
					break;
				
				String regex = ": ";//����: ����Ϊ�ָ���
				String[] result = line.split(regex);
				/*
				 * ÿ����¼���������Ҳ�����֡�: ��,Ϊ�˲������������ִ�����²���
				 * ����ָ�Ŀ����2����������зָ����ĳ��֣����ͽ�����ָ�Ŀ���������
				 * ������ָ�Ŀ�С��2��������������󣬽�����һ��ѭ��
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2) continue;
				
				
				//�����ȡ�ĸ����ǡ�product/productId��һ��
				/*
				 * ���productId���иò�Ʒ�ļ�¼
				 * ��������һ�εļ�¼һֱ���¶�ȡ���������ֱ���˶μ�¼����������һ�������ѭ��
				 * ���򣬾���productId�м���˲�Ʒ��Id����ʾ�ò�Ʒ��Ϣ�ѱ���¼
				 */
				if(result[0].contains("productId")) {
					
					if(productId.contains(result[1])) {
						while(true) {
							line = input.readLine();
							if(line.contains("review/"))//һֱ���¶�ȡ��ֱ����ȡ����һ������
								break;
						}
						continue;//�ٿ�ʼ��һ�ֵ����ѭ��
					}
					else {
						productId.add(result[1]);
						output.write(result[1]+"; ");
					}
				}
				else if(result[0].contains("title")) {
					output.write(result[1]+"; ");
				}
				else if(result[0].contains("price")) {
					output.write(result[1]);
					output.newLine();
				}
			}
			output.flush();output.close();input.close();
			reader.close();writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUser(String destination) {
		FileReader reader = null;
		BufferedReader input = null;
		FileWriter writer = null;
		BufferedWriter output = null;
		//ʹ��һ��HashSet��¼userId����ֹ�ظ���¼�û���Ϣ
		HashSet<String> userId = new HashSet<String>();
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			writer = new FileWriter(destination);
			output = new BufferedWriter(writer);
			
			String line = null;
			while(true) {
				line = input.readLine();
				if(line == null)
					break;
				
				String regex = ": ";
				String[] result = line.split(regex);
				/*
				 * ÿ����¼���������Ҳ�����֡�: ��,Ϊ�˲������������ִ�����²���
				 * ����ָ�Ŀ����2����������зָ����ĳ��֣����ͽ�����ָ�Ŀ���������
				 * ������ָ�Ŀ�С��2��������������󣬽�����һ��ѭ��
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				/*
				 * ���userId���и��û��ļ�¼
				 * ��������һ�εļ�¼һֱ���¶�ȡ���������ֱ���˶μ�¼����������һ�������ѭ��
				 * ���򣬾���userId�м�����û���Id����ʾ���û���Ϣ�ѱ���¼
				 */
				if(result[0].contains("userId")) {
					if(userId.contains(result[1])) {
						while(true) {
							line = input.readLine();
							if(line.contains("product/"))//һֱ���¶�ȡ��ֱ���˶μ�¼����
								break;
						}
						continue;//������һ�ֵ�ѭ��
					}
					else {
						userId.add(result[1]);
						output.write(result[1]+"; ");
					}
				}
				else if(result[0].contains("profileName")) {
					output.write(result[1]);
					output.newLine();
				}
				
			}
			output.flush();output.close();input.close();
			reader.close();writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getScore(String destination) {
		FileReader reader = null;
		BufferedReader input = null;
		FileWriter writer = null;
		BufferedWriter output = null;
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			writer = new FileWriter(destination);
			output = new BufferedWriter(writer);
			
			String line = null;
			String temp = null;
			while(true) {
				line = input.readLine();
				if(line == null)
					break;
				
				String regex = ": ";
				String[] result = line.split(regex);
				/*
				 * ÿ����¼���������Ҳ�����֡�: ��,Ϊ�˲������������ִ�����²���
				 * ����ָ�Ŀ����2����������зָ����ĳ��֣����ͽ�����ָ�Ŀ���������
				 * ������ָ�Ŀ�С��2��������������󣬽�����һ��ѭ��
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				if(result[0].contains("productId")) {
					//���������ʽҪ����һ���ַ�����ʱ��������productId
					temp = result[1];
				}
				else if(result[0].contains("userId")) {
					//�ڽ�userId����󣬽������productId
					output.write(result[1] + "; ");
					output.write(temp + "; ");
				}
				else if(result[0].contains("score")) {
					output.write(result[1]);
					output.newLine();
				}
			}
			output.flush();output.close();input.close();
			reader.close();writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getReview(String destination) {
		FileReader reader = null;
		BufferedReader input = null;
		FileWriter writer = null;
		BufferedWriter output = null;
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			writer = new FileWriter(destination);
			output = new BufferedWriter(writer);
			
			String line = null;
			String temp = null;
			while(true) {
				line = input.readLine();
				if(line == null)
					break;
				
				String regex = ": ";
				String[] result = line.split(regex);
				/*
				 * ÿ����¼���������Ҳ�����֡�: ��,Ϊ�˲������������ִ�����²���
				 * ����ָ�Ŀ����2����������зָ����ĳ��֣����ͽ�����ָ�Ŀ���������
				 * ������ָ�Ŀ�С��2��������������󣬽�����һ��ѭ��
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				if(result[0].contains("productId")) {
					temp = result[1];//ʹ����ʱ��������productId
				}
				else if(result[0].contains("userId")) {
					output.write(result[1] + "; ");//��д��userId
					output.write(temp + "; ");//����д��productId
				}
				else if(result[0].contains("review/summary")) {
					output.write(result[1]);
					output.newLine();
				}
			}
			output.flush();output.close();input.close();
			reader.close();writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		 * ��������ļ��������ַ�������·��
		 */
		String desPro = "product.txt";
		String desUser = "user.txt";
		String desScore = "score.txt";
		String desReview = "review.txt";
		
		File product = new File(desPro);
		File user = new File(desUser);
		File score = new File(desScore);
		File review = new File(desReview);
		
		try {
			product.createNewFile();
			user.createNewFile();
			score.createNewFile();
			review.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * ����һ�������ļ����࣬�����÷����õ�����ļ�
		 */
		ProcessData process = new ProcessData();
		process.getProduct(desPro);
		process.getUser(desUser);
		process.getScore(desScore);
		process.getReview(desReview);
	}
}
