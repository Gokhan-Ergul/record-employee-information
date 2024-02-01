package application;

public class HourlyEmployee extends Employee {
	private double wage;
	private double hours;
	
	public HourlyEmployee(String firstname, String lastname, String socialSecurityNumber, double wage, double hours) {
		super(firstname, lastname, socialSecurityNumber);
		this.wage = wage;
		this.hours = hours;
		if(wage<0) {
			main.showWarning("Wage have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
		if(hours<0 || hours>168) {
			main.showWarning("hours have to be bigger than or equal to zero and must be under 168. Please restart the system.");
			System.exit(0);
		}
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		if(wage<0) {
			main.showWarning("Wage have to be bigger than or equal to zero. Please restart the system.");
			System.exit(0);
		}
		this.wage = wage;
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		if(hours<0 || hours>168) {
			main.showWarning("hours have to be bigger than or equal to zero and must be under 168. Please restart the system.");
			System.exit(0);
		}
		this.hours = hours;
	}
	@Override
	public double getPaymentAmount() {
		if(hours>= 40)
			return 40 * wage + ((hours-40) *1.5) * wage;
		else
			 return wage * hours;
	}
	@Override
	public String toString() {
        return "Hourly Employee:" + super.toString()  + "Hourly wage:$" + wage + ", Hours worked:" + hours ;
    }
}
	
