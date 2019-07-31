
public class Japanese extends Human {

	public Japanese(String name) 
	{
		super(name);
	}

	public double sayHello()
	{
		System.out.println("こんにちは!");
		return 0;
	}
	
	public static void main(String[] args)
	{
		Japanese Jman = new Japanese("test");
		Jman.sayHello();
	}
}
