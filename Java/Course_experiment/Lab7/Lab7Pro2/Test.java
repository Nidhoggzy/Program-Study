import java.util.*;

public class Test {

	public static void main(String[] args) {
		int row1,row2;
		int col1,col2;
		
		Matrix matrixA,matrixB,result;
		Scanner in =new Scanner(System.in);
		
		System.out.println("����A��");
		System.out.print("�У�");row1 = in.nextInt();
		System.out.print("�У�");col1 = in.nextInt();
		matrixA = new Matrix(row1,col1);
		matrixA.setMap();
		
		System.out.println("����B:");
		System.out.print("�У�");row2 = in.nextInt();
		System.out.print("�У�");col2 = in.nextInt();
		matrixB = new Matrix(row2,col2);
		matrixB.setMap();
		
		result = new Matrix();
		
		if(matrixA.Multiple(matrixB, result))
		{
			System.out.println("����A��˾���B�Ľ���ǣ�");
			result.Display();
		}
		else
			System.out.println("��������������ˣ�");
		

	}

}
