import java.util.regex.*;

public class Price {

	public static void main(String[] args) {

		Pattern p;
		Matcher m;
		float totalPrice = 0;
		p = Pattern.compile("\\d+([.]\\d+)?");
		m = p.matcher("������Ѽ��189Ԫ�����۳��⣺12.9Ԫ������㣺69Ԫ������ţ����32Ԫ");
		
		while(m.find())
		{
			String str = m.group();
			totalPrice += Float.parseFloat(str);
		}
		
		System.out.println("�˵��ܼ۸�Ϊ��"+totalPrice+"Ԫ");
	}

}
