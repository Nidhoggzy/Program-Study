import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Club {

	public static void main(String[] args) {
		HashSet<String> ClubA = new HashSet<>();
		HashSet<String> ClubB = new HashSet<>();

		ClubA.add("����");
		ClubA.add("����");
		Object[] A = ClubA.toArray();
		System.out.print("A���ŵĳ�Ա��");
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
		
		ClubB.add("����");
		ClubB.add("����");
		Object[] B = ClubB.toArray();
		System.out.print("B���ŵĳ�Ա��");
		for(int i = 0;i < B.length;i++)
		{
			System.out.printf("%s ",B[i]);
		}
		System.out.print("\n");
		
		A_copy.retainAll(ClubB);
		Object[] Intersection = A_copy.toArray();
		System.out.print("ͬʱ�μ��������ŵĳ�Ա��");
		for(int i = 0;i < Intersection.length;i++)
		{
			System.out.printf("%s ",Intersection[i]);
		}
		
		System.out.print("A���ŵĳ�Ա��");
		for(int i = 0;i < A.length;i++)
		{
			System.out.printf("%s ",A[i]);

		}
		System.out.print("\n");
	}

}
