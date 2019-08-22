package hw7;

public class Commissions {

	public Commissions() {
		
	}
	
	public double [] commish(int dealers, double [] sales)
	{
		double [] commissions = new double[dealers];
		
		for(int j = 0; j < commissions.length; j++)
		{
			if(sales[j] >= 1 || sales[j] <= 5000)
			{
				commissions[j] = sales[j] * 0.08;
			}
			else if(sales[j] >= 5001 || sales[j] <= 15000)
			{
				commissions[j] = sales[j] * 0.15;
			}
			else if(sales[j] > 15000)
			{
				commissions[j] = sales[j] * 0.20;
			}
		}
		return commissions;
	}
	
	public double totalCommish(double [] commissions)
	{
		double totalCommission = 0;
		
		for(int j = 0; j < commissions.length; j++)
		{
			totalCommission += commissions[j]; 
		}
		return totalCommission;
	}

}
