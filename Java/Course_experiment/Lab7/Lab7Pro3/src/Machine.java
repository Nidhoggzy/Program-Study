
public class Machine {

	public void checkBag(Goods goods) throws DangerException
	{
		if(goods.getDanger())
		{
			DangerException error = new DangerException();
			throw(error);
		}
	}
	
	public static void main(String[] args) {
		Goods goods = new Goods(true);
		Machine machine = new Machine();
		try
		{
			machine.checkBag(goods);
		}
		catch(DangerException error)
		{
			error.toShow();
		}

	}

}
