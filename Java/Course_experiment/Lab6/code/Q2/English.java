
public class English extends Human {

	public English(String name) 
	{
		super(name);
	}

	public double sayHello()
	{
		System.out.println("Hello!");
		return 0;
	}
	
	public static void main(String[] args)
	{
		English Eman = new English("test");
		Eman.sayHello();
	}
}
