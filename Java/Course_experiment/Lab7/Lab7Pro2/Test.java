import java.util.*;

public class Test {

	public static void main(String[] args) {
		int row1,row2;
		int col1,col2;
		
		Matrix matrixA,matrixB,result;
		Scanner in =new Scanner(System.in);
		
		System.out.println("矩阵A：");
		System.out.print("行：");row1 = in.nextInt();
		System.out.print("列：");col1 = in.nextInt();
		matrixA = new Matrix(row1,col1);
		matrixA.setMap();
		
		System.out.println("矩阵B:");
		System.out.print("行：");row2 = in.nextInt();
		System.out.print("列：");col2 = in.nextInt();
		matrixB = new Matrix(row2,col2);
		matrixB.setMap();
		
		result = new Matrix();
		
		if(matrixA.Multiple(matrixB, result))
		{
			System.out.println("矩阵A左乘矩阵B的结果是：");
			result.Display();
		}
		else
			System.out.println("该两个矩阵不能相乘！");
		

	}

}
