/*
 * 处理score.txt 文件得到数据的数字特征
 */
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class CalculateAvg {

	String source;
	//为了计算评分均值，分别用两个HashMap存储产品和用户的内容
	//将ID作为key，将一个Double型大小为2的数组作为value，数组第一个数据存储分数的和，第二个存储总的个数
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
		//输出写入产品均值的结果
		FileWriter writerPro = null;
		BufferedWriter outputPro = null;
		//输出写入用户均值结果
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
				 * 对分割结果进行分析
				 * 将不同的数据分别加入对应的HashMap
				 */
				if(user.containsKey(result[0])) {
					//如果已经记录过该用户数据，则在原数据的基础上更新数据项
					user.get(result[0])[0] += Double.parseDouble(result[2]);
					user.get(result[0])[1]++;
				}
				else {
					//否则就新建一个二维数组，初始化总评分为当前的评分以及次数为1，并加入HashMap中，
					Double[] temp = new Double[2];
					temp[0] = Double.parseDouble(result[2]);
					temp[1] = 1.0;
					user.put(result[0], temp);
				}
				//下面对产品评分的处理与前者类似
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
			//分别对两个HashMap进行遍历，并将结果写入对应文件
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
		 * 创建结果文件，并声明CalculateAvg类的对象
		 * 调用其中的方法获取结果
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
