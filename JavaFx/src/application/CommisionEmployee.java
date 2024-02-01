package application;

public class CommisionEmployee extends Employee{
	private double commissionRate;
    private double grossSales;
    
	public CommisionEmployee(String firstname, String lastname, String socialSecurityNumber, double commissionRate,
			double grossSales) {
		super(firstname, lastname, socialSecurityNumber);
		this.commissionRate = commissionRate;
		this.grossSales = grossSales;
		if(grossSales<0) {
			main.showWarning("Gross sales have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
		if(commissionRate<=0 || commissionRate>1 ) {
			main.showWarning("Commission rate have to be bigger than zero, and must be under 1. Please restart the system.");
			System.exit(0);
		}
	}
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		if(commissionRate<=0 || commissionRate>1 ) {
			main.showWarning("Commission rate have to be bigger than zero, and must be under 1. Please restart the system.");
			System.exit(0);
		}
		this.commissionRate = commissionRate;
	}
	public double getGross() {
		
		return grossSales;
	}
	public void setGross(double sales) {
		if(grossSales<0) {
			main.showWarning("Gross sales have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
		this.grossSales = sales;
	}
	@Override
	public double getPaymentAmount() {
		return commissionRate * grossSales;
	}
	@Override
	
	public String toString() {
		return "Commission Employee: " + super.toString()  +  String.format("gross sales: $%,.2f", grossSales) + ", Commission Rate:" + commissionRate ;	
	}
	

}

