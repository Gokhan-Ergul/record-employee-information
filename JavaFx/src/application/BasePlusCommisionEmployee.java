package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BasePlusCommisionEmployee extends CommisionEmployee{
	private double baseSalary;
	
	
	public BasePlusCommisionEmployee(String firstname, String lastname, String socialSecurityNumber,
			double commissionRate, double grossSales, double baseSalary) {
		super(firstname, lastname, socialSecurityNumber, commissionRate, grossSales);
		this.baseSalary = baseSalary;
		if(baseSalary<0) {
			main.showWarning("Base salary have to be bigger than or equal to zero. Please restart the system.");			System.exit(0);
		}
	}
	
	
	
	
	

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		if(baseSalary<0) {
			main.showWarning("Base salary have to be bigger than or equal to zero. Please restart the system.");
			
			System.exit(0);
		}
		this.baseSalary = baseSalary;
	}
	@Override
	public double getPaymentAmount() {
		return super.getPaymentAmount() + baseSalary;
	}

}


