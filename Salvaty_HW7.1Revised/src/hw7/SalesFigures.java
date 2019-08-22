package hw7;

public class SalesFigures {

	public static void main(String[] args) {
		Scan s = new Scan();
		DealershipInfo dInfo = new DealershipInfo();
		Commissions cInfo = new Commissions();
		DealerSearch dSearch = new DealerSearch();
				
		int _dealers = dInfo.dealerNum(s);		
		String [] _dName = dInfo.dealerName(_dealers, s);
		double [] _dSales = dInfo.dealerSales(_dealers, s);		
		double _totalSales = dInfo.totalSales(_dSales);
		double _avgSales = dInfo.avgSales(_dealers, _totalSales);
		double [] _commissions = cInfo.commish(_dealers, _dSales);
		double _totalCommissions = cInfo.totalCommish(_commissions);
		
		dInfo.sales_Printout(_totalSales, _avgSales, _totalCommissions);
		dInfo.dealer_Printout(_dealers, _dName, _dSales, _commissions);
				
		String choice = s.ScanString("Would you like to perform a dealer search? Y/N: ");
		
		switch(choice)
		{
			case "y":
			case "Y":
				String dName = s.ScanString("Enter the name of the dealer: ");
				double result = dSearch.dealer_Info(_dealers, dName, _dName, _commissions, s);
				if(result == -1)
				{
					System.out.println("The dealer name does not exist, or the dealer info is empty.");
				}
				else
				{
					System.out.printf("Dealer Name \t \t Commission %n");
					System.out.printf("%s \t %.2f %n", dName, result);
				}
				break;
			case "n":
			case "N":
				System.out.println("Goodbye.");
				break;
		}		
	}
}
