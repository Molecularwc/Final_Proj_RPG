package hw7;

public class DealershipInfo {

	public DealershipInfo() {
		
	}
	
	public int dealerNum(Scan s)
	{
		int dealerNums = s.ScanInt("Enter the number of dealers you wish to track: ");
		while(dealerNums < 0)
		{
			dealerNums = s.ScanInt("You can not have less than 0 dealers. "
					+ "Please re-enter the number of dealers");
		}
		while(dealerNums > 30)
		{
			dealerNums = s.ScanInt("You can not have more than 30 dealers. "
					+ "Please re-enter the number of dealers");
		}
		return dealerNums;
	}
	
	public String [] dealerName(int dealerNums, Scan s)
	{
		String [] name = new String[dealerNums];
		for(int i = 0; i < name.length; i++)
		{
			String dName = s.ScanString("Enter the name of the dealer: ");
//			while(dName.isEmpty())
//			{				
//				dName = s.ScanString("Something happened. Please Re-enter the name: ");
//			}
			name[i] = dName;
		}
		return name;
	}
	
	public double [] dealerSales(int dealerNums, Scan s)
	{
		double [] sales = new double[dealerNums];		
		for(int i = 0; i < sales.length; i++)
		{
			double numCheck = s.ScanDouble("Enter dealer sales total: $");
			while(numCheck < 0)
			{
				numCheck = s.ScanDouble("Sales can not be less than 0" 
						+ "Re-enter dealer sales total: $");
			}			
			sales[i] = numCheck;
		}
		return sales;
	}
	
	public double totalSales(double[] sales)
	{
		double total = 0;
		for(int i = 0; i < sales.length; i++)
		{
			total += sales[i];
		}
		return total;
	}
	
	public double avgSales(int dealers, double total)
	{
		double averageSales = total / dealers;
		return averageSales;
	}

	public void dealer_Printout(int dealers, String [] name, double [] sales, double [] commission)
	{
		System.out.printf("Dealer \t Sales \t \t Commission %n");
		for(int x = 0; x < name.length; x++)
		{			
			System.out.printf("%s \t %.2f \t %.2f %n", name[x], sales[x], commission[x]);
		}
		System.out.println();
	}
	
	public void sales_Printout(double totalSales, double avgSales, double totalCommissions)
	{
		System.out.printf("Average sales: \t Total sales: \t Total commissions: %n");
		System.out.printf("$%.2f \t $%.2f \t $%.2f %n", 
				avgSales, totalSales, totalCommissions);
		System.out.println();
	}

}
