
public class Grade {

	Student ZhangSan;
	Student LiSi;
	Student WangWu;
	
	public Grade()
	{
		ZhangSan = new Student("张三");
		LiSi = new Student("李四");
		WangWu = new Student("王五");
	}
	
	public void getStudentNames()
	{
		System.out.println(ZhangSan.getName());
		System.out.println(LiSi.getName());
		System.out.println(WangWu.getName());
	}
	
}
