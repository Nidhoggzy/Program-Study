import java.util.*;
import java.util.Map.Entry;

public class Matrix {

	int row,column;
	public HashMap<Coordinate,Double> matrix;
	
	//�������캯��
	public Matrix() {
		matrix = new HashMap<Coordinate, Double>();
	}
	
	public Matrix(int r,int c)
	{
		row = r;
		column = c;
		matrix = new HashMap<Coordinate, Double>();
	}
	
	//���þ����е�ֵ
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
	
	//���þ����������
	public void setRandC(int r,int c)
	{
		row = r;
		column = c;
	}
	
	//��r�У�c�����ֵΪva����Ԫ��
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
	
	//����˷�����Ҫʵ�ֲ���
	public boolean Multiple(Matrix matrixB, Matrix result)
	//��һ�������Ǳ���˵ľ��󣬵ڶ��������Ǵ�Ž���Ķ���
	{
		int r,c;
		
		r = this.row;
		c = matrixB.getColumn();
		
		if(this.column != matrixB.row)
			return false;
		
		result.setRandC(r, c);
		
		Iterator<Entry<Coordinate, Double>> iterA = this.matrix.entrySet().iterator();			
		while(iterA.hasNext())
		//�����������A��HashMap
		{
			Map.Entry entryA = (Map.Entry) iterA.next();
			Coordinate coorA = (Coordinate) entryA.getKey();
						
			Iterator<Entry<Coordinate, Double>> iterB = matrixB.matrix.entrySet().iterator();
			while(iterB.hasNext())
			//�����������B��HashMap
			{
				Map.Entry entryB = (Map.Entry) iterB.next();
				Coordinate coorB = (Coordinate) entryB.getKey();
							
				if(coorA.j.equals(coorB.i))
				//����ڴ˴α����д���A���е���B���У������
				{
					double value = (Double)entryA.getValue();
					value *= (Double)entryB.getValue();
								
					result.addValue(coorA.i, coorB.j, value);
				}
			}
		}
		return true;	
	}
	//�����ʾһ������
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
