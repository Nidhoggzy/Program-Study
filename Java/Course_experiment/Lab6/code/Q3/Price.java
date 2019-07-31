import java.util.regex.*;

public class Price {

	public static void main(String[] args) {

		Pattern p;
		Matcher m;
		float totalPrice = 0;
		p = Pattern.compile("\\d+([.]\\d+)?");
		m = p.matcher("北京烤鸭：189元；西芹炒肉：12.9元；酸菜鱼：69元；铁板牛柳：32元");
		
		while(m.find())
		{
			String str = m.group();
			totalPrice += Float.parseFloat(str);
		}
		
		System.out.println("账单总价格为："+totalPrice+"元");
	}

}
