
public class Grade {

	Student ZhangSan;
	Student LiSi;
	Student WangWu;
	
	public Grade()
	{
		ZhangSan = new Student("����");
		LiSi = new Student("����");
		WangWu = new Student("����");
	}
	
	public void getStudentNames()
	{
		System.out.println(ZhangSan.getName());
		System.out.println(LiSi.getName());
		System.out.println(WangWu.getName());
	}
	
}
