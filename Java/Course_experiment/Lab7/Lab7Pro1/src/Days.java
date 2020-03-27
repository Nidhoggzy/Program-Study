import java.util.*;
import java.util.regex.*;


public class Days {

	public static void main(String[] args) {
		
		String str1;
		String str2;
		Scanner in = new Scanner(System.in);
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		Pattern p;
		Matcher m;
		
		int[] num = new int[3];
		
		p = Pattern.compile("\\d+");
		
		str1 = in.nextLine();
		m = p.matcher(str1);
		int i = 0;
		while(m.find())
		{
			num[i] = Integer.parseInt(m.group());
			++i;
		}
		date1.set(num[0], num[1], num[2]);
		
		str2 = in.nextLine();
		m = p.matcher(str2);
		i = 0;
		while(m.find())
		{
			num[i] = Integer.parseInt(m.group());
			++i;
		}
		date2.set(num[0], num[1], num[2]);
		
		long days;
		long dayOne = date1.getTimeInMillis();
		long dayTwo = date2.getTimeInMillis();
		
		days = (dayOne - dayTwo)/(1000*60*60*24);
		days = Math.abs(days);
		
		System.out.println(str1+"∫Õ"+str2+"œ‡∏Ù£∫ "+days+"ÃÏ");
		in.close();
	}

}
