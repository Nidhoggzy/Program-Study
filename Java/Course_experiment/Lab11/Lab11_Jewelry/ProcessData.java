/*
 * 处理Jewelry.txt文件得到相关的产品、用户、评分、评价信息
 */
import java.io.*;
import java.util.HashSet;

public class ProcessData {

	String source;
	
	public ProcessData(){
		source = "Jewelry.txt";
	}
	
	public void getProduct(String destination) {
		//由于文件的特殊性，使用FileReader和BufferedReader对文件逐行读入
		FileReader reader = null;
		BufferedReader input = null;
		//由于输出格式的限制，也使用FileWriter和BufferedWriter将结果写入文件
		FileWriter writer = null;
		BufferedWriter output = null;
		//使用一个HashSet记录productId，防止重复记录产品信息
		HashSet<String> productId = new HashSet<String>();
		
		try {
			reader = new FileReader(source);
			input = new BufferedReader(reader);
			writer = new FileWriter(destination);
			output = new BufferedWriter(writer);
			
			String line = null;
			while(true)
			{
				line = input.readLine();//逐行读取文件
				if(line == null)
					break;
				
				String regex = ": ";//将“: ”作为分隔符
				String[] result = line.split(regex);
				/*
				 * 每条记录后面的内容也许会出现“: ”,为了补救这种情况就执行如下操作
				 * 如果分割的块大于2（即后面会有分隔符的出现），就将后面分割的块整合起来
				 * 而如果分割的块小于2，则该行数据有误，进行下一轮循环
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2) continue;
				
				
				//如果读取的该行是“product/productId”一行
				/*
				 * 如果productId中有该产品的记录
				 * 则跳过这一段的记录一直向下读取不做输出，直到此段记录结束，继续一轮最外层循环
				 * 否则，就像productId中加入此产品的Id，表示该产品信息已被记录
				 */
				if(result[0].contains("productId")) {
					
					if(productId.contains(result[1])) {
						while(true) {
							line = input.readLine();
							if(line.contains("review/"))//一直向下读取，直到读取完这一段数据
								break;
						}
						continue;//再开始新一轮的外层循环
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
		//使用一个HashSet记录userId，防止重复记录用户信息
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
				 * 每条记录后面的内容也许会出现“: ”,为了补救这种情况就执行如下操作
				 * 如果分割的块大于2（即后面会有分隔符的出现），就将后面分割的块整合起来
				 * 而如果分割的块小于2，则该行数据有误，进行下一轮循环
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				/*
				 * 如果userId中有该用户的记录
				 * 则跳过这一段的记录一直向下读取不做输出，直到此段记录结束，继续一轮最外层循环
				 * 否则，就向userId中加入此用户的Id，表示该用户信息已被记录
				 */
				if(result[0].contains("userId")) {
					if(userId.contains(result[1])) {
						while(true) {
							line = input.readLine();
							if(line.contains("product/"))//一直向下读取，直到此段记录结束
								break;
						}
						continue;//继续下一轮的循环
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
				 * 每条记录后面的内容也许会出现“: ”,为了补救这种情况就执行如下操作
				 * 如果分割的块大于2（即后面会有分隔符的出现），就将后面分割的块整合起来
				 * 而如果分割的块小于2，则该行数据有误，进行下一轮循环
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				if(result[0].contains("productId")) {
					//由于输出格式要求，用一个字符串临时变量储存productId
					temp = result[1];
				}
				else if(result[0].contains("userId")) {
					//在将userId输出后，接着输出productId
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
				 * 每条记录后面的内容也许会出现“: ”,为了补救这种情况就执行如下操作
				 * 如果分割的块大于2（即后面会有分隔符的出现），就将后面分割的块整合起来
				 * 而如果分割的块小于2，则该行数据有误，进行下一轮循环
				 */
				if(result.length > 2) {
					for(int i = 2; i < result.length; i++)
						result[1] += result[i];
				}
				else if(result.length < 2)
					continue;
				
				if(result[0].contains("productId")) {
					temp = result[1];//使用临时变量储存productId
				}
				else if(result[0].contains("userId")) {
					output.write(result[1] + "; ");//先写入userId
					output.write(temp + "; ");//接着写入productId
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
		 * 创建结果文件，并用字符串存下路径
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
		 * 声明一个处理文件的类，并调用方法得到结果文件
		 */
		ProcessData process = new ProcessData();
		process.getProduct(desPro);
		process.getUser(desUser);
		process.getScore(desScore);
		process.getReview(desReview);
	}
}
