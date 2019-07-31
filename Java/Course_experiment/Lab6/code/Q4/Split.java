import java.util.*;
import java.util.regex.*;

public class Split {

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String str;
		String regNum = "[^0-9]";
		String regLower = "[^a-z]";
		String regCaptial = "[^A-Z]";
		Pattern p;
		Matcher m;
		
		for(int i=0;i<10;i++)
		{
			str = in.nextLine();
			
			p = Pattern.compile(regCaptial);
			m = p.matcher(str);
			System.out.print("��д��ĸ��Ϊ��");
			System.out.println(m.replaceAll("").trim());
			
			p = Pattern.compile(regLower);
			m = p.matcher(str);
			System.out.print("Сд��ĸ��Ϊ��");
			System.out.println(m.replaceAll("").trim());
			
			p = Pattern.compile(regNum);
			m = p.matcher(str);
			System.out.print("���ִ�Ϊ��");
			System.out.println(m.replaceAll("").trim());
			
		}
		
		in.close();
	}
}
