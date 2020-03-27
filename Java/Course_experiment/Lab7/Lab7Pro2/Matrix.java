import java.util.*;
import java.util.Map.Entry;

public class Matrix {

	int row,column;
	public HashMap<Coordinate,Double> matrix;
	
	//两个构造函数
	public Matrix() {
		matrix = new HashMap<Coordinate, Double>();
	}
	
	public Matrix(int r,int c)
	{
		row = r;
		column = c;
		matrix = new HashMap<Coordinate, Double>();
	}
	
	//设置矩阵中的值
	public void setMap()
	{
		Scanner in =new Scanner(System.in);
		double num;
		
		for(int i = 0; i < row; i++)
			for(int j = 0; j < column; j++)
			{
				num = in.nextDouble();
				
				if(num!=0)
				{
					matrix.put(new Coordinate(i,j), num);
				}
			}
		

	}
	
	//设置矩阵的行与列
	public void setRandC(int r,int c)
	{
		row = r;
		column = c;
	}
	
	//在r行，c列添加值为va的新元素
	public void addValue(int r, int c, double va)
	{
		Coordinate c_temp = new Coordinate(r, c);
		double oldVa = 0;
		
		if(matrix.containsKey(c_temp))
		{
			oldVa = matrix.get(c_temp);
			matrix.remove(c_temp);
		}
		
		double newVa = oldVa + va;
		if(newVa != 0)
			matrix.put(c_temp, newVa);
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	//矩阵乘法的主要实现部分
	public boolean Multiple(Matrix matrixB, Matrix result)
	//第一个参数是被左乘的矩阵，第二个参数是存放结果的对象
	{
		int r,c;
		
		r = this.row;
		c = matrixB.getColumn();
		
		if(this.column != matrixB.row)
			return false;
		
		result.setRandC(r, c);
		
		Iterator<Entry<Coordinate, Double>> iterA = this.matrix.entrySet().iterator();			
		while(iterA.hasNext())
		//遍历储存矩阵A的HashMap
		{
			Map.Entry entryA = (Map.Entry) iterA.next();
			Coordinate coorA = (Coordinate) entryA.getKey();
						
			Iterator<Entry<Coordinate, Double>> iterB = matrixB.matrix.entrySet().iterator();
			while(iterB.hasNext())
			//遍历储存矩阵B的HashMap
			{
				Map.Entry entryB = (Map.Entry) iterB.next();
				Coordinate coorB = (Coordinate) entryB.getKey();
							
				if(coorA.j.equals(coorB.i))
				//如果在此次遍历中存在A的列等于B的行，就相乘
				{
					double value = (Double)entryA.getValue();
					value *= (Double)entryB.getValue();
								
					result.addValue(coorA.i, coorB.j, value);
				}
			}
		}
		return true;	
	}
	//输出显示一个矩阵
	public void Display()
	{
		for(int i = 0; i < row; i++)
		{	for(int j = 0; j < column; j++)
			{
				Coordinate coor = new Coordinate(i,j);
				if(matrix.containsKey(coor))
				{
					System.out.print(matrix.get(coor)+" ");
				}
				else
					System.out.print("0.0 ");
			}
			System.out.println();
		}
	}
	
}
