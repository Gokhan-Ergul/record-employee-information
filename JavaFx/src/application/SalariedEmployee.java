package application;

public class SalariedEmployee extends Employee{
	private double weeklySalary;
	
	

	public SalariedEmployee(String firstname, String lastname, String socialSecurityNumber, double weeklySalary) {
		super(firstname, lastname, socialSecurityNumber);
		this.weeklySalary = weeklySalary;
		// weeklySalary must be >= 0
		if(weeklySalary<0) {
			main.showWarning("Weekly salary have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
	}
	
	public  double getweeklySalary(){
		return weeklySalary;
	}
	public void setweeklySalary(double monthlySalary) {
		if(weeklySalary<0) {
			main.showWarning("Weekly salary have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
		this.weeklySalary = monthlySalary;
	}
	@Override
	public double getPaymentAmount() {
		return weeklySalary;
	}
	@Override
	public String toString() {
		return "Salaried employee: " + super.toString() +String.format("Weekly salary: $%,.2f", weeklySalary);
    
    }
}
	
