package hw7;

public class DealerSearch {

	public DealerSearch() {
		
	}
	
	public double dealer_Info(int dealers, String dName, String [] dealerName, double[] _commissions, Scan s)
	{
		double ansReturn = 0;
		for(int k = 0; k < dealers; k++)
		{
			if(dealerName[k].equals(dName))
			{
				ansReturn = _commissions[k];
				break;				
			}
			else
			{
				ansReturn = -1;
			}
		}		
		return ansReturn;
	}

}

