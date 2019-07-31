
public class Chinese extends Human {

	public Chinese(String name) 
	{
		super(name);
	}

	public double sayHello()
	{
		System.out.println("ÄãºÃ");
		return 0;
	}
	
	public static void main(String[] args)
	{
		Chinese Cman = new Chinese("test");
		Cman.sayHello();
	}
}
