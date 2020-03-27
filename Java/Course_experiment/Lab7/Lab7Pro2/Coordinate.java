
public class Coordinate {

	Integer i,j;
	
	Coordinate(int i,int j)
	{
		this.i = i;
		this.j = j;
	}
		
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null||getClass() != o.getClass())
			return false;
		
		Coordinate coor = (Coordinate) o;
		if( i != null && j !=null? 
				!(i.equals( coor.i ) && j.equals( coor.j ) ) : 
				coor.i != null && coor.j != null )
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		return i != null &&j !=null ?
				i.hashCode() + j.hashCode() : 0;
	}
}
