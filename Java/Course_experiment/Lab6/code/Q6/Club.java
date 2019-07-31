import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Club {

	public static void main(String[] args) {
		HashSet<String> ClubA = new HashSet<>();
		HashSet<String> ClubB = new HashSet<>();

		ClubA.add("张三");
		ClubA.add("李四");
		Object[] A = ClubA.toArray();
		System.out.print("A社团的成员：");
		for(int i = 0;i < A.length;i++)
		{
			System.out.printf("%s ",A[i]);

		}
		System.out.print("\n");
		
		Collection<String> A_copy = (Collection<String>) ClubA.clone(); 
		 
//		Iterator<String> iterator = ClubA.iterator(); 
//		while(iterator.hasNext()){ 
//		    A_copy.add((String) iterator.next()); 
//		}
		
		ClubB.add("李四");
		ClubB.add("王五");
		Object[] B = ClubB.toArray();
		System.out.print("B社团的成员：");
		for(int i = 0;i < B.length;i++)
		{
			System.out.printf("%s ",B[i]);
		}
		System.out.print("\n");
		
		A_copy.retainAll(ClubB);
		Object[] Intersection = A_copy.toArray();
		System.out.print("同时参加两个社团的成员：");
		for(int i = 0;i < Intersection.length;i++)
		{
			System.out.printf("%s ",Intersection[i]);
		}
		
		System.out.print("A社团的成员：");
		for(int i = 0;i < A.length;i++)
		{
			System.out.printf("%s ",A[i]);

		}
		System.out.print("\n");
	}

}
