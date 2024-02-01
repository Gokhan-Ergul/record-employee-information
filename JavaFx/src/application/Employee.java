package application;


public abstract class Employee implements Payable {
	Javafx main = new Javafx();
	private String firstname;
	private String lastname;
	private String socialSecurityNumber;
	
	 
	public Employee(String firstname, String lastname, String socialSecurityNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.socialSecurityNumber = socialSecurityNumber;
		
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	@Override
	public abstract double getPaymentAmount(); // All subclasses must make this method
	
	
	public String toString() {
		return  firstname  +" "+ lastname + "\nSocial Security Number:"
				+ socialSecurityNumber + "\n";
	}
	
	
	 
}

