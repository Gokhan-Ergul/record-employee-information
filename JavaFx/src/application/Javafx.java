package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Javafx extends Application {
	private List<Employee> employees = new ArrayList<>();
	private String fileTxt = "C:\\Users\\Gökhan ergül\\Desktop\\dataBase.txt";// Select your txt file in your PC.

	TextField tfFirstName = new TextField();
	TextField tfLastName = new TextField();
	TextField tfSSN = new TextField();
	TextField tfSUSSN = new TextField();
	TextField tfSalary = new TextField();
	TextField tfGross = new TextField();
	TextField tfCommission = new TextField();
	TextField tfBase = new TextField();
	TextField tfWeekly = new TextField();
	TextField tfWage = new TextField();
	TextField tfHours = new TextField();

	Label lbfirsName = new Label("First Name");
	Label lbLastName = new Label("Last Name");
	Label lbSSN = new Label("SSN");
	Label lbSUSSN = new Label("Search/Update by SSN");
	Label lbSalary = new Label("SALARY");
	Label lbGross = new Label(" Gross Sales");
	Label lbCommission = new Label("Commission Rate");
	Label lbBase = new Label("Base Salary");
	Label lbWeekly = new Label("Weekly Salary");
	Label lbWage = new Label("Wage");
	Label lbHours = new Label("Hours");

	Button btAdd = new Button("Add");
	Button btSbSSN = new Button("Search by SSN");
	Button btUbSSN = new Button("Update by SSN");
	Button btClear = new Button("Clean textfield");
	ComboBox<String> comboBox = new ComboBox<>();

	@Override
	public void start(Stage primaryStage) {

		try {
			VBox forspace = new VBox(20);
			HBox buttons = new HBox();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 600, 400);
			GridPane gp = new GridPane();
			BorderPane.setMargin(buttons, new Insets(0, 0, 20, 0));

			filetoEmployees(fileTxt, employees);
			tfSalary.setEditable(false);
			tfSSN.setEditable(false);

			comboBox.getItems().addAll("Salaried Employee", "Hourly Employee", "Commission Employee",
					"Base Plus Commission Employee");
			comboBox.setValue("Select an employee");

			tfSSN.setPrefColumnCount(11);
			tfSUSSN.setPrefColumnCount(11);

			gp.setVgap(10);
			gp.setHgap(4);
			gp.add(lbfirsName, 0, 0);
			gp.add(tfFirstName, 1, 0);
			gp.add(lbLastName, 0, 1);
			gp.add(tfLastName, 1, 1);
			gp.add(lbSSN, 0, 2);
			gp.add(tfSSN, 1, 2);
			gp.add(lbSUSSN, 0, 3);
			gp.add(tfSUSSN, 1, 3);
			gp.add(lbSalary, 0, 4);
			gp.add(tfSalary, 1, 4);
			gp.add(lbGross, 4, 0);
			gp.add(tfGross, 5, 0);
			gp.add(lbCommission, 4, 1);
			gp.add(tfCommission, 5, 1);
			gp.add(lbBase, 4, 2);
			gp.add(tfBase, 5, 2);
			gp.add(lbWeekly, 4, 3);
			gp.add(tfWeekly, 5, 3);
			gp.add(lbWage, 4, 4);
			gp.add(tfWage, 5, 4);
			gp.add(lbHours, 4, 5);
			gp.add(tfHours, 5, 5);

			forspace.getChildren().addAll(comboBox, gp);
			forspace.setAlignment(Pos.CENTER);

			buttons.getChildren().addAll(btAdd, btSbSSN, btUbSSN, btClear);
			buttons.setSpacing(10);
			buttons.setAlignment(Pos.CENTER);

			root.setTop(forspace);
			root.setBottom(buttons);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("EMPLOYEE SALARY CALCULATOR");
			primaryStage.setScene(scene);
			primaryStage.show();

			comboBox.setOnAction(e -> {
				comboBoxAction();

			});

			btAdd.setOnAction(e -> {
				btaddAction();
			});

			btClear.setOnAction(e -> {
				btclearAction();
			});

			btSbSSN.setOnAction(e -> {
				btSearch();
			});

			btUbSSN.setOnAction(e -> {
				btUpdate();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String[] readfromFile() {
		Path filepathobject = Paths.get(fileTxt);
		try {

			byte[] filecontent = Files.readAllBytes(filepathobject);

			String filecontentString = new String(filecontent);

			String[] filearray = filecontentString.split("\n");
			return filearray;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writetoFile(String rows, String file) {
		try {
			FileWriter fileWriter = new FileWriter(file, true); // true meaning is dont delete data from file.
			// Append the content to the file
			fileWriter.write(rows);
			fileWriter.close();
		} catch (IOException e) {
			showWarning(("Error clearing the file: " + e.getMessage()));
		}
	}

	private void filetoEmployees(String file, List<Employee> employees) {
		String[] stringarray = readfromFile();
		for (String str : stringarray) {
			String[] employeeData = str.split(",");
			if (employeeData.length > 4) {
				String employeeType = employeeData[0];
				Employee employee = null;
				switch (employeeType) {
				case "Salaried Employee":
					employee = new SalariedEmployee(employeeData[1], employeeData[2], employeeData[3],
							Double.parseDouble(employeeData[4]));
					break;
				case "Hourly Employee":
					employee = new HourlyEmployee(employeeData[1], employeeData[2], employeeData[3],
							Double.parseDouble(employeeData[4]), Double.parseDouble(employeeData[5]));
					break;
				case "Commission Employee":
					employee = new CommisionEmployee(employeeData[1], employeeData[2], employeeData[3],
							Double.parseDouble(employeeData[4]), Double.parseDouble(employeeData[5]));
					break;
				case "Base Plus Commission Employee":
					employee = new BasePlusCommisionEmployee(employeeData[1], employeeData[2], employeeData[3],
							Double.parseDouble(employeeData[4]), Double.parseDouble(employeeData[5]),
							Double.parseDouble(employeeData[6]));
					break;
				default:
					System.out.println("Bilinmeyen çalışan türü: " + employeeType);
					break;
				}

				if (employee != null) {
					employees.add(employee);
				}
			}
		}
	}

	private void employeetoFile(String file, List<Employee> employees) {
		// delete data from txt file
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("");
			fileWriter.close();
		} catch (IOException e) {
			showWarning(("Error clearing the file: " + e.getMessage()));
		}

		for (int i = 0; i < employees.size(); i++) {
			String str = "";

			switch (employees.get(i).getClass().getSimpleName()) {
			case "SalariedEmployee":
				str = "Salaried Employee," + ((SalariedEmployee) employees.get(i)).getFirstname() + ","
						+ ((SalariedEmployee) employees.get(i)).getLastname() + ","
						+ ((SalariedEmployee) employees.get(i)).getSocialSecurityNumber() + ","
						+ Double.toString(((SalariedEmployee) employees.get(i)).getweeklySalary()) + ","
						+ Double.toString(((SalariedEmployee) employees.get(i)).getPaymentAmount()) + "\n";
				break;
			case "HourlyEmployee":
				str = "Hourly Employee," + ((HourlyEmployee) employees.get(i)).getFirstname() + ","
						+ ((HourlyEmployee) employees.get(i)).getLastname() + ","
						+ ((HourlyEmployee) employees.get(i)).getSocialSecurityNumber() + ","
						+ Double.toString(((HourlyEmployee) employees.get(i)).getWage()) + ","
						+ Double.toString(((HourlyEmployee) employees.get(i)).getHours()) + ","
						+ Double.toString(((HourlyEmployee) employees.get(i)).getPaymentAmount()) + "\n";
				break;
			case "BasePlusCommisionEmployee":
				str = "Base Plus Commission Employee," + ((BasePlusCommisionEmployee) employees.get(i)).getFirstname()
						+ "," + ((BasePlusCommisionEmployee) employees.get(i)).getLastname() + ","
						+ ((BasePlusCommisionEmployee) employees.get(i)).getSocialSecurityNumber() + ","
						+ Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getCommissionRate()) + ","
						+ Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getGross()) + ","
						+ Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getBaseSalary()) + ","
						+ Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getPaymentAmount()) + "\n";
				break;
			case "CommisionEmployee":
				str = "Commission Employee," + ((CommisionEmployee) employees.get(i)).getFirstname() + ","
						+ ((CommisionEmployee) employees.get(i)).getLastname() + ","
						+ ((CommisionEmployee) employees.get(i)).getSocialSecurityNumber() + ","
						+ Double.toString(((CommisionEmployee) employees.get(i)).getCommissionRate()) + ","
						+ Double.toString(((CommisionEmployee) employees.get(i)).getGross()) + ","
						+ Double.toString(((CommisionEmployee) employees.get(i)).getPaymentAmount()) + "\n";
				break;
			default:
				break;
			}
			writetoFile(str, fileTxt);
		}
	}

	private void comboBoxAction() {

		String selectedOption = comboBox.getValue();
		switch (selectedOption) {
		case "Salaried Employee":
			tfSalary.setDisable(false);
			tfGross.setDisable(true);
			tfCommission.setDisable(true);
			tfBase.setDisable(true);
			tfWeekly.setDisable(false);
			tfWage.setDisable(true);
			tfHours.setDisable(true);
			break;
		case "Hourly Employee":
			tfSalary.setDisable(false);
			tfGross.setDisable(true);
			tfCommission.setDisable(true);
			tfBase.setDisable(true);
			tfWeekly.setDisable(true);
			tfWage.setDisable(false);
			tfHours.setDisable(false);
			break;
		case "Commission Employee":
			tfSalary.setDisable(false);
			tfGross.setDisable(false);
			tfCommission.setDisable(false);
			tfBase.setDisable(true);
			tfWeekly.setDisable(true);
			tfWage.setDisable(true);
			tfHours.setDisable(true);
			break;
		case "Base Plus Commission Employee":
			tfSalary.setDisable(false);
			tfGross.setDisable(false);
			tfCommission.setDisable(false);
			tfBase.setDisable(false);
			tfWeekly.setDisable(true);
			tfWage.setDisable(true);
			tfHours.setDisable(true);
			break;
		default:
			tfSalary.setDisable(true);
			tfGross.setDisable(true);
			tfCommission.setDisable(true);
			tfBase.setDisable(true);
			tfWeekly.setDisable(true);
			tfWage.setDisable(true);
			tfHours.setDisable(true);
			break;
		}
	}

	private void btaddAction() {
		Random random = new Random();
		int randomnumber = 0;
		String ssnNumber = "";

		for (int i = 0; i < 9; i++) {
			randomnumber = random.nextInt(10);
			if (i == 3 || i == 5)
				ssnNumber = ssnNumber + "-";

			ssnNumber = ssnNumber + randomnumber;
		}
		tfSSN.setText(ssnNumber);
		String selectedOption = comboBox.getValue();

		if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfSSN.getText().isEmpty()) {
			showWarning("Please enter information where required!");
		} else if (selectedOption.equals("Salaried Employee")) {
			SalariedEmployee salariedEmployee = new SalariedEmployee(tfFirstName.getText(), tfLastName.getText(),
					tfSSN.getText(), Double.parseDouble(tfWeekly.getText()));
			tfSalary.setText(Double.toString(salariedEmployee.getPaymentAmount()));
			employees.add(salariedEmployee);

		} else if (selectedOption.equals("Hourly Employee")) {
			HourlyEmployee hourlyEmployee = new HourlyEmployee(tfFirstName.getText(), tfLastName.getText(),
					tfSSN.getText(), Double.parseDouble(tfWage.getText()), Double.parseDouble(tfHours.getText()));
			tfSalary.setText(Double.toString(hourlyEmployee.getPaymentAmount()));
			employees.add(hourlyEmployee);

		} else if (selectedOption.equals("Commission Employee")) {
			CommisionEmployee commissionEmployee = new CommisionEmployee(tfFirstName.getText(), tfLastName.getText(),
					tfSSN.getText(), Double.parseDouble(tfCommission.getText()), Double.parseDouble(tfGross.getText()));
			employees.add(commissionEmployee);
			tfSalary.setText(Double.toString(commissionEmployee.getPaymentAmount()));

		} else if (selectedOption.equals("Base Plus Commission Employee")) {
			BasePlusCommisionEmployee basePlusCommissionEmployee = new BasePlusCommisionEmployee(tfFirstName.getText(),
					tfLastName.getText(), tfSSN.getText(), Double.parseDouble(tfGross.getText()),
					Double.parseDouble(tfCommission.getText()), Double.parseDouble(tfBase.getText()));
			employees.add(basePlusCommissionEmployee);
			tfSalary.setText(Double.toString(basePlusCommissionEmployee.getPaymentAmount()));
		} else {
			showWarning("Please choice a operation from menu");
		}

		employeetoFile(fileTxt, employees);
	}

	private void btclearAction() {
		tfFirstName.clear();
		tfLastName.clear();
		tfSalary.clear();
		tfBase.clear();
		tfCommission.clear();
		tfGross.clear();
		tfHours.clear();
		tfSSN.clear();
		tfSUSSN.clear();
		tfWage.clear();
		tfWeekly.clear();
	}

	private void btSearch() {
		int check = 0;
		if (employees != null) {
			for (int i = 0; i < employees.size(); i++) {
				if (employees.get(i).getSocialSecurityNumber().equals(tfSUSSN.getText())) {
					switch (employees.get(i).getClass().getSimpleName()) {
					case "SalariedEmployee":
						comboBox.setValue("Salaried Employee");
						tfFirstName.setText(((SalariedEmployee) employees.get(i)).getFirstname());
						tfLastName.setText(((SalariedEmployee) employees.get(i)).getLastname());
						tfSSN.setText(((SalariedEmployee) employees.get(i)).getSocialSecurityNumber());
						tfWeekly.setText(Double.toString(((SalariedEmployee) employees.get(i)).getweeklySalary()));
						tfSalary.setText(Double.toString(((SalariedEmployee) employees.get(i)).getPaymentAmount()));
						check = 1;
						break;
					case "HourlyEmployee":
						comboBox.setValue("Hourly Employee");
						tfFirstName.setText(((HourlyEmployee) employees.get(i)).getFirstname());
						tfLastName.setText(((HourlyEmployee) employees.get(i)).getLastname());
						tfSSN.setText(((HourlyEmployee) employees.get(i)).getSocialSecurityNumber());
						tfWage.setText(Double.toString(((HourlyEmployee) employees.get(i)).getWage()));
						tfHours.setText(Double.toString(((HourlyEmployee) employees.get(i)).getHours()));
						tfSalary.setText(Double.toString(((HourlyEmployee) employees.get(i)).getPaymentAmount()));
						check = 1;
						break;
					case "BasePlusCommisionEmployee":
						comboBox.setValue("Base Plus Commission Employee");
						tfFirstName.setText(((BasePlusCommisionEmployee) employees.get(i)).getFirstname());
						tfLastName.setText(((BasePlusCommisionEmployee) employees.get(i)).getLastname());
						tfSSN.setText(((BasePlusCommisionEmployee) employees.get(i)).getSocialSecurityNumber());
						tfGross.setText(Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getGross()));
						tfCommission.setText(
								Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getCommissionRate()));
						tfBase.setText(Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getBaseSalary()));
						tfSalary.setText(
								Double.toString(((BasePlusCommisionEmployee) employees.get(i)).getPaymentAmount()));
						check = 1;
						break;
					case "CommisionEmployee":
						comboBox.setValue("Commission Employee");
						tfFirstName.setText(((CommisionEmployee) employees.get(i)).getFirstname());
						tfLastName.setText(((CommisionEmployee) employees.get(i)).getLastname());
						tfSSN.setText(((CommisionEmployee) employees.get(i)).getSocialSecurityNumber());
						tfGross.setText(Double.toString(((CommisionEmployee) employees.get(i)).getGross()));
						tfCommission
								.setText(Double.toString(((CommisionEmployee) employees.get(i)).getCommissionRate()));
						tfSalary.setText(Double.toString(((CommisionEmployee) employees.get(i)).getPaymentAmount()));
						check = 1;

					default:
						showWarning("The file is empty");
						break;
					}
				}
			}

			if (check == 0) {
				check = 0;
				showWarning("No employees found with this SSN");
				btClear.fire();
			}
			tfSUSSN.clear();

		}
	}

	private void btUpdate() {
		String strnoArray = null;
		if (employees != null) {
			for (int i = 0; i < employees.size(); i++) {
				if (employees.get(i).getSocialSecurityNumber().equals(tfSUSSN.getText())) {
					if (employees.get(i) instanceof SalariedEmployee) {
						comboBox.setValue("Salaried Employee");
						if (!tfFirstName.getText().isEmpty())
							((SalariedEmployee) employees.get(i)).setFirstname(tfFirstName.getText());

						if (!tfLastName.getText().isEmpty())
							((SalariedEmployee) employees.get(i)).setLastname(tfLastName.getText());

						if (!tfWeekly.getText().isEmpty()) {
							((SalariedEmployee) employees.get(i))
									.setweeklySalary(Double.parseDouble(tfWeekly.getText()));

						} else if ((employees.get(i) instanceof HourlyEmployee)) {
							comboBox.setValue("Hourly Employee");

							if (!tfFirstName.getText().isEmpty()) {
								((HourlyEmployee) employees.get(i)).setFirstname(tfFirstName.getText());
							}

							if (!tfLastName.getText().isEmpty()) {
								((HourlyEmployee) employees.get(i)).setLastname(tfLastName.getText());
							}

							if (!tfWage.getText().isEmpty()) {
								((HourlyEmployee) employees.get(i)).setWage(Double.parseDouble(tfWage.getText()));
							}

							if (!tfHours.getText().isEmpty()) {
								((HourlyEmployee) employees.get(i)).setHours(Double.parseDouble(tfHours.getText()));
							}
						} else if ((employees.get(i) instanceof CommisionEmployee)) {
							comboBox.setValue("Commission Employee");
							if (!tfFirstName.getText().isEmpty())
								((CommisionEmployee) employees.get(i)).setFirstname(tfFirstName.getText());

							if (!tfLastName.getText().isEmpty())
								((CommisionEmployee) employees.get(i)).setLastname(tfLastName.getText());

							if (!tfGross.getText().isEmpty())
								((CommisionEmployee) employees.get(i))
										.setGross((Double.parseDouble(tfGross.getText())));

							if (!tfCommission.getText().isEmpty())
								((CommisionEmployee) employees.get(i))
										.setCommissionRate(Double.parseDouble(tfCommission.getText()));

						} else if ((employees.get(i) instanceof BasePlusCommisionEmployee)) {
							comboBox.setValue("Base Plus Commission Employee");
							if (!tfFirstName.getText().isEmpty())
								((BasePlusCommisionEmployee) employees.get(i)).setFirstname(tfFirstName.getText());

							if (!tfLastName.getText().isEmpty())
								((BasePlusCommisionEmployee) employees.get(i)).setLastname(tfLastName.getText());

							if (!tfGross.getText().isEmpty())
								((BasePlusCommisionEmployee) employees.get(i))
										.setGross((Double.parseDouble(tfGross.getText())));

							if (!tfCommission.getText().isEmpty())
								((BasePlusCommisionEmployee) employees.get(i))
										.setCommissionRate(Double.parseDouble(tfCommission.getText()));

						}
						employeetoFile(fileTxt, employees);
					}
				} else {
					showWarning("There is no employee in the records");
				}
			}
		}
		// writetoFile(strSourceArray);
		btClear.fire();
	}

	public static void showWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
