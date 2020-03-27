/*
 * 对score.txt文件进行处理
 * 分别得到每项珠宝产品在数据记录的这段时间里的总销量
 * 和每个亚马逊用户在该段时间的珠宝购买量
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
		//使用FileReader和BufferedReader读取数据
		FileReader reader = null;
		BufferedReader input = null;
		//使用FileWriter和BufferedWriter写入结果
		FileWriter writerSales = null, writerPrchs = null;
		BufferedWriter outputSales = null, outputPrchs = null;
		
		//使用HashMap分别储存 对应的数据
		//将Id作为key，将销量和购买量作为value
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
				//统计每个产品的销量
				productSales.putIfAbsent(result[1], 0);
				productSales.put(result[1], productSales.get(result[1])+1);
				//统计每个用户的珠宝购买量
				userPurchase.putIfAbsent(result[0], 0);
				userPurchase.put(result[0], userPurchase.get(result[0])+1);	
			}
			
			/*
			 * 统计完后，对储存结果的HashMap进行遍历
			 * 将结果写入文件中
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
		 * 创建对应的结果文件夹
		 * 声明上述类的一个实例并调用其中方法得到结果
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
