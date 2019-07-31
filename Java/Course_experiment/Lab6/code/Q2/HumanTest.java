
public class HumanTest {

	public static void main(String[] args) {
		
		Human[] person = new Human[3];
		
		person[0] = new Chinese("test");
		person[1] = new Japanese("test");
		person[2] = new English("test");
		
		for(int i = 0;i<3;i++)
		{
			person[i].sayHello();
		}

	}

}
