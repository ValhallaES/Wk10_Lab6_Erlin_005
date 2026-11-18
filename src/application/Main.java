package application;

import java.sql.SQLException;
import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		EmploymentFormDao formDao = new EmploymentFormDao();

		primaryStage.setTitle("Employment Application");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label star = new Label("\u2731");
		star.setStyle("-fx-font-size: 30px;");
		Label companyName = new Label("John");
		companyName.setStyle("-fx-font-weight: bold;");
		VBox companyText = new VBox(-2, companyName, new Label("Company"));
		HBox logo = new HBox(8, star, companyText);
		logo.setAlignment(Pos.CENTER_LEFT);
		logo.setPadding(new Insets(10, 20, 10, 10));

		Label title = new Label("Employment Application");
		title.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold; -fx-padding: 15 30;");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setMaxHeight(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		HBox.setHgrow(title, Priority.ALWAYS);

		HBox header = new HBox(logo, title);
		header.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		grid.add(header, 0, 0, 2, 1);

		TextField fullNameTextField = new TextField();
		TextField addressTextField = new TextField();
		TextField contactTextField = new TextField();
		TextField emailTextField = new TextField();
		TextField educationTextField = new TextField();

		ToggleGroup genderGroup = new ToggleGroup();
		HBox genderBox = radioRow(genderGroup, "Male", "Female", "Other");

		DatePicker dateAvailablePicker = new DatePicker();
		TextField positionTextField = new TextField();
		TextField salaryTextField = new TextField();

		ToggleGroup authorizedGroup = new ToggleGroup();
		HBox authorizedBox = radioRow(authorizedGroup, "Yes", "No");

		ToggleGroup relativesGroup = new ToggleGroup();
		HBox relativesBox = radioRow(relativesGroup, "Yes", "No");

		TextField explanationTextField = new TextField();
		TextField signatureTextField = new TextField();

		grid.addRow(1, new Label("Full Name:"), fullNameTextField);
		grid.addRow(2, new Label("Current Address:"), addressTextField);
		grid.addRow(3, new Label("Contact Number:"), contactTextField);
		grid.addRow(4, new Label("Email Address:"), emailTextField);
		grid.addRow(5, new Label("Highest Educational Attainment:"), educationTextField);
		grid.addRow(6, new Label("Gender:"), genderBox);
		grid.addRow(7, new Label("Date Available:"), dateAvailablePicker);
		grid.addRow(8, new Label("Desired Position:"), positionTextField);
		grid.addRow(9, new Label("Desired Salary:"), salaryTextField);
		grid.addRow(10, new Label("Legally authorized to work in the country?"), authorizedBox);
		grid.addRow(11, new Label("Relatives working for our company?"), relativesBox);
		grid.addRow(12, new Label("If yes, please explain further:"), explanationTextField);
		grid.addRow(13, new Label("Applicant's Signature:"), signatureTextField);

		Button submitButton = new Button("Submit");
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(submitButton);
		grid.add(hBox, 1, 14);

		submitButton.setOnAction(actionEvent -> {
			String fullName = fullNameTextField.getText().trim();
			String address = addressTextField.getText().trim();
			String contact = contactTextField.getText().trim();
			String email = emailTextField.getText().trim();

			if (!StringPool.BLANK.equals(fullName) && !StringPool.BLANK.equals(address)
					&& !StringPool.BLANK.equals(contact) && !StringPool.BLANK.equals(email)) {
				EmploymentForm form = new EmploymentForm();
				form.setFullName(fullName);
				form.setAddress(address);
				form.setContactNumber(contact);
				form.setEmail(email);
				form.setEducation(educationTextField.getText().trim());
				form.setGender(selected(genderGroup));
				form.setDateAvailable(dateAvailablePicker.getValue());
				form.setDesiredPosition(positionTextField.getText().trim());
				form.setDesiredSalary(salaryTextField.getText().trim());
				form.setAuthorizedToWork("Yes".equals(selected(authorizedGroup)));
				form.setHasRelatives("Yes".equals(selected(relativesGroup)));
				form.setRelativesExplanation(explanationTextField.getText().trim());
				form.setSignature(signatureTextField.getText().trim());

				try {
					int formId = formDao.saveForm(form);
					if (formId > 0) {
						this.alert("Submit", "Application submitted successfully! ID: " + formId, AlertType.INFORMATION);
					} else {
						this.alert("Error", "Failed!", AlertType.ERROR);
					}
				} catch (SQLException ex) {
					this.alert("Error", "Database error: " + ex.getMessage(), AlertType.ERROR);
				}
			} else {
				this.alert("Error", "Please complete Full Name, Address, Contact Number and Email!", AlertType.ERROR);
			}
		});

		Scene scene = new Scene(grid, 650, 650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HBox radioRow(ToggleGroup group, String... options) {
		HBox box = new HBox(10);
		for (String option : options) {
			RadioButton radioButton = new RadioButton(option);
			radioButton.setToggleGroup(group);
			box.getChildren().add(radioButton);
		}
		return box;
	}

	private String selected(ToggleGroup group) {
		RadioButton radioButton = (RadioButton) group.getSelectedToggle();
		return radioButton == null ? null : radioButton.getText();
	}

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		launch(args);
	}
}