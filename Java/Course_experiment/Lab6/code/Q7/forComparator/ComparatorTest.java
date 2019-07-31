package forComparator;
import java.util.*;

class comparePrice implements Comparator<Displayer>{
	public int compare( Displayer o1 , Displayer o2 )
	{
		if( o1.getPrice() <= o2.getPrice() )
			return 1;
		else
			return -1;
	}
}

public class ComparatorTest {
	
	public static void main(String[] args) {
		Displayer[] disp = new Displayer[8];
		disp[0] = new Displayer(27,4799);
		disp[1] = new Displayer(23.8,1699);
		disp[2] = new Displayer(25,2599);
		disp[3] = new Displayer(34,14999);
		disp[4] = new Displayer(34,6699);
		disp[5] = new Displayer(27,4499);
		disp[6] = new Displayer(31.5,4699);
		disp[7] = new Displayer(34,12999);
		
		comparePrice comp =new comparePrice();
		TreeMap<Displayer,Integer> displayers = new TreeMap<Displayer, Integer>(comp);
		for(int i = 0;i < 8;i++)
			displayers.put(disp[i],i+1);

		List<Map.Entry<Displayer,Integer>> list = 
				new ArrayList<>(displayers.entrySet());
		for(Map.Entry<Displayer,Integer> entry : list)
		{
			System.out.println("ÏÔÊ¾Æ÷:"+entry.getValue()
					+"   "+entry.getKey());
		}
	}

}

class Displayer {
	
	double size;
	double price;
	
	public Displayer(double size,double price)
	{
		this.size = size;
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String toString()
	{
		String info = "³ß´ç:" + String.valueOf( size ) +
				" ¼Û¸ñ:" + String.valueOf( price );
		return info;
	}
}

