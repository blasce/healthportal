package yourPediatricsPortal;



import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class createAccount {
	private Button confirmAccountButton, logout, immuSubmit, healthIssSubmit, medSubmit, cancel1, cancel2, cancel3, delete, cancel4;
	private Label titleLabel, passwordTitle, recordHealthLabel, immuLabel, healthIssLabel, medLabel, nameLabel, dobLabel, userLabel;
	private Label healthLabel, immuLabel2, healthIssLabel2, medLabel2, passwordLabel, reEnterLabel, numberLabel;
	private Label deleteLabel, date;
	private Label errorMess1, errorMess2;
	private TextField passwordTF, reEnterTF, healthCareVeriTF, immuTF, healthIssTF, medTF, date1TF, date2TF, date3TF, date4TF, deleteTF, numberTF;
	private TextArea immunizationsTA, healthIssTA, medTA;
	private BorderPane newAccountUI;
	private Scene accountScene;
	private VBox passwordPane, patientPane, passUserPane, accountInfoPane, fillOutPane;
	private HBox topPane, midPane;
	private String first, last, dob, role,username, immu, healthIss, med;
	private ComboBox<String> healthCB;
	private String verificationCode = "P3diatrics";
	
	public createAccount (String first, String last, String dob, String role) {
		this.first = first;
		this.last = last;
		this.dob = dob;
		this.role = role;
		this.username =  first.substring(0,1) + last+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		newAccountUI = new BorderPane();
		accountScene = new Scene(newAccountUI, 1280,730);
		top();
		middle();
		
	}
	
	private void top() {
		topPane = new HBox();
		
		ImageView image = new ImageView("https://cdn-icons-png.flaticon.com/256/3959/3959107.png");
		image.setFitHeight(40);
		image.setFitWidth(40);
		titleLabel = new Label("YourPediatricsPortal", image);
		titleLabel.setFont(new Font("Comic Sans MS", 19));
		titleLabel.setStyle("-fx-font-weight: bold");
		titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		ImageView imageLogout = new ImageView ("https://static-00.iconduck.com/assets.00/log-out-outline-icon-512x432-rbmppekf.png");
		imageLogout.setFitHeight(20);
		imageLogout.setFitWidth(20);
		logout = new Button("",imageLogout);
	
		
		
		String topPaneLayout ="-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1.5;\n" +
                "-fx-background-color: #9abaed;\n";
		topPane.setStyle(topPaneLayout);
		topPane.setMargin(titleLabel, new Insets(5,10,10,0));
		topPane.setMargin(logout, new Insets(15,0,0,960));
		
		topPane.getChildren().add(titleLabel);
		topPane.getChildren().add(logout);
		
		logout.setOnAction(new ButtonHandler());
		newAccountUI.setTop(topPane);
	}
	
	private void middle() {
		 passwordPane = new VBox();
		 passwordTitle = new Label("Create a Password");
		 passwordTF = new TextField("");
		 passwordLabel = new Label("Enter a password - 8 or more characters,\nwith one special character");
		 passwordTF.setPromptText("Enter a password");
		 reEnterTF = new TextField("");
		 reEnterLabel = new Label("Re-enter password");
		 reEnterTF.setPromptText("Re-enter password");
		 healthCareVeriTF = new TextField("");
		 healthCareVeriTF.setPromptText("Enter verification code");
		 confirmAccountButton = new Button("Confirm Account");
		 errorMess1 = new Label("");
		 
		 errorMess1.setFont(new Font("Comic Sans MS", 12));
		 errorMess1.setTextFill(Color.RED);
		 errorMess1.setPadding(new Insets(10,0,0,10));
		 passwordPane.setMargin(passwordTitle, new Insets(10,0,10,-250));
		 passwordPane.setMargin(passwordTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(reEnterTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(healthCareVeriTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(confirmAccountButton, new Insets(5,0,0,0));
		 passwordPane.setAlignment(Pos.TOP_CENTER);
			
		 passwordTitle.setFont(new Font("Comic Sans MS", 14));
		 passwordTitle.setStyle("-fx-font-weight: bold");
		 
		 passwordLabel.setFont(new Font("Comic Sans MS", 12));
		 passwordLabel.setStyle("-fx-font-weight: bold");
		 
		 reEnterLabel.setFont(new Font("Comic Sans MS", 12));
		 reEnterLabel.setStyle("-fx-font-weight: bold");
		 
		 passwordLabel.setPadding(new Insets(0,110,0,0));
		 reEnterLabel.setPadding(new Insets(0,250,0,0));
		 
		 String createAccLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		passwordPane.setStyle(createAccLayout);

		 passwordPane.setMaxWidth(400);
		 passwordPane.setMaxHeight(400);
		 passwordPane.setMinWidth(400);
		 passwordPane.setMinHeight(400);
		 newAccountUI.setCenter(passwordPane);
		 
		 
		 
		 passwordPane.getChildren().add(passwordTitle);
		 passwordPane.getChildren().add(passwordLabel);
		 passwordPane.getChildren().add(passwordTF);
		 passwordPane.getChildren().add(reEnterLabel);
		 passwordPane.getChildren().add(reEnterTF);
		 
		 confirmAccountButton.setOnAction(new ButtonHandler());
		 if (role.equals("Doctor") || role.equals("Nurse")) {
			 newAccountUI.setMargin(passwordPane, new Insets(-50,0,0,0));
			 passwordPane.getChildren().add(healthCareVeriTF);
			
		 } else {
			 patientForm();
			 buttonHandling();
		 }
		 passwordPane.getChildren().add(confirmAccountButton);
		 passwordPane.getChildren().add(errorMess1);
		 
	 }
	
	private void patientForm() {
		midPane = new HBox();
		patientPane = new VBox();
		passUserPane = new VBox();
		accountInfoPane = new VBox();
		
		nameLabel = new Label("Welcome, "+ first + " " + last);
		dobLabel = new Label("Your DOB is " + dob);
		userLabel = new Label("Your username will be " + username);
		
		nameLabel.setTextFill(Color.NAVY);
		
		nameLabel.setPadding(new Insets(0,0,0,10));
		dobLabel.setPadding(new Insets(0,0,0,10));
		userLabel.setPadding(new Insets(0,0,0,10));
		
		healthLabel = new Label("Health Form:");
		immuLabel = new Label("Immunizations:");
		healthIssLabel = new Label("Health Issues:");
		medLabel = new Label("Medications: ");
		immunizationsTA = new TextArea();
		healthIssTA = new TextArea();
		medTA = new TextArea();
		
		immunizationsTA.setMaxHeight(100);
		immunizationsTA.setMinHeight(100);
		healthIssTA.setMaxHeight(100);
		healthIssTA.setMinHeight(100);
		medTA.setMaxHeight(100);
		medTA.setMinHeight(100);
		
		immunizationsTA.setMaxWidth(400);
		healthIssTA.setMaxWidth(400);
		medTA.setMaxWidth(400);
		
		nameLabel.setFont(new Font("Comic Sans MS", 12));
		nameLabel.setStyle("-fx-font-weight: bold");
		dobLabel.setFont(new Font("Comic Sans MS",12));
		dobLabel.setStyle("-fx-font-weight: bold");
		userLabel.setFont(new Font("Comic Sans MS", 12));
		userLabel.setStyle("-fx-font-weight: bold");
		
		healthLabel.setFont(new Font("Comic Sans MS", 14));
		healthLabel.setStyle("-fx-font-weight: bold");
		
		immuLabel.setFont(new Font("Comic Sans MS", 12));
		immuLabel.setStyle("-fx-font-weight: bold");
		
		healthIssLabel.setFont(new Font("Comic Sans MS", 12));
		healthIssLabel.setStyle("-fx-font-weight: bold");
		
		medLabel.setFont(new Font("Comic Sans MS", 12));
		medLabel.setStyle("-fx-font-weight: bold");
		
		 String createAccLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		patientPane.setStyle(createAccLayout);
		patientPane.setMargin(healthLabel, new Insets(0,0,0,5));
		patientPane.setMargin(immuLabel, new Insets(0,0,0,10));
		patientPane.setMargin(healthIssLabel, new Insets(10,0,0,10));
		patientPane.setMargin(medLabel, new Insets(10,0,0,10));
		
		patientPane.setMargin(immunizationsTA, new Insets(0,10,0,10));
		patientPane.setMargin(healthIssTA, new Insets(0,10,0,10));
		patientPane.setMargin(medTA, new Insets(0,10,0,10));
		
		 String accLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #a9b4d1;\n";
		accountInfoPane.setStyle(accLayout);
		
		immunizationsTA.setWrapText(true);
		healthIssTA.setWrapText(true);
		medTA.setWrapText(true);
		immunizationsTA.setEditable(false);
		healthIssTA.setEditable(false);
		medTA.setEditable(false);
		
		immunizationsTA.setText("None");
		healthIssTA.setText("None");
		medTA.setText("None");
		
		fillOutPane = new VBox();
		formFillOut();
		midPane.setPadding(new Insets(80,50,160,70));
		accountInfoPane.getChildren().addAll(nameLabel, dobLabel, userLabel);
		passUserPane.getChildren().addAll(accountInfoPane, passwordPane);
		patientPane.getChildren().addAll(healthLabel, immuLabel, immunizationsTA, healthIssLabel, healthIssTA,medLabel, medTA);
		midPane.getChildren().addAll(passUserPane, fillOutPane, patientPane);
		newAccountUI.setCenter(midPane);
	}

	private void formFillOut() {
		recordHealthLabel = new Label("Add if necessary & input appr. date");
		immuLabel2 = new Label("Immunizations");
		healthIssLabel2 = new Label("Health Issues");
		medLabel2 = new Label("Medications");
		date = new Label("Dates");
		medTF = new TextField();
		immuTF= new TextField();
		healthIssTF = new TextField();
		date1TF = new TextField();
		date2TF = new TextField();
		date3TF = new TextField();
		date4TF = new TextField();
		immuSubmit = new Button("Submit");
		healthIssSubmit = new Button("Submit");
		medSubmit = new Button("Submit");
		cancel1 = new Button("Cancel");
		cancel2 =new Button("Cancel");
		cancel3 = new Button("Cancel");
		cancel4 = new Button("Cancel");
		deleteLabel = new Label("Delete input");
		delete = new Button("Delete");
		deleteTF = new TextField();
		healthCB = new ComboBox();
		healthCB.getItems().addAll("Immunizations", "Health Issues", "Medications");
		healthCB.setValue("Select");
		numberLabel = new Label("Number:");
		numberTF = new TextField();
		
		errorMess2 = new Label("");
		errorMess2.setFont(new Font("Comic Sans MS", 12));
		errorMess2.setTextFill(Color.RED);
		errorMess2.setPadding(new Insets(10,0,0,10));
		
		immu = "None";
		healthIss = "None";
		med = "None";
		
		
		fillOutPane.setMargin(recordHealthLabel, new Insets(0,10,0,5));
		
		HBox number = new HBox();
		number.getChildren().addAll(numberLabel, numberTF);
		fillOutPane.setMargin(number, new Insets(1,10,1,10));
		numberTF.setPromptText("NNNNNNNNNN");
		number.setSpacing(10);
		
		HBox label = new HBox();
		label.getChildren().addAll(immuLabel2, date);
		label.setSpacing(80);
		fillOutPane.setMargin(label, new Insets(0,10,0,10));
		
		HBox immu = new HBox();
		immu.getChildren().addAll(immuTF, date1TF);
		HBox buttons1 = new HBox();
		buttons1.getChildren().addAll(immuSubmit, cancel1);
		fillOutPane.setMargin(immu, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons1, new Insets(5,10,10,10));
		immu.setSpacing(10);
		buttons1.setSpacing(10);
		
		HBox healthIss = new HBox();
		healthIss.getChildren().addAll(healthIssTF, date2TF);
		HBox buttons2 = new HBox();
		buttons2.getChildren().addAll(healthIssSubmit, cancel2);
		fillOutPane.setMargin(healthIssLabel2, new Insets(0,10,0,10));
		fillOutPane.setMargin(healthIss, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons2, new Insets(2,10,10,10));
		healthIss.setSpacing(10);
		buttons2.setSpacing(10);
		
		HBox med = new HBox();
		med.getChildren().addAll(medTF, date3TF);
		HBox buttons3 = new HBox();
		buttons3.getChildren().addAll(medSubmit, cancel3);
		fillOutPane.setMargin(medLabel2, new Insets(0,10,0,10));
		fillOutPane.setMargin(med, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons3, new Insets(2,10,10,10));
		med.setSpacing(10);
		buttons3.setSpacing(10);
		
		HBox deletion = new HBox();
		deletion.getChildren().addAll(deleteTF, date4TF);
		HBox buttons4 = new HBox();
		buttons4.getChildren().addAll(delete, cancel4, healthCB);
		fillOutPane.setMargin(deleteLabel, new Insets(0,10,0,10));
		fillOutPane.setMargin(deletion, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons4, new Insets(2,10,0,10));
		deletion.setSpacing(10);
		buttons4.setSpacing(10);
		
		date1TF.setPromptText("MM/DD/YYYY");
		date2TF.setPromptText("MM/DD/YYYY");
		date3TF.setPromptText("MM/DD/YYYY");
		date4TF.setPromptText("MM/DD/YYYY");
		
		numberLabel.setFont(new Font("Comic Sans MS", 12));
		numberLabel.setStyle("-fx-font-weight: bold");
		
		recordHealthLabel.setFont(new Font("Comic Sans MS", 14));
		recordHealthLabel.setStyle("-fx-font-weight: bold");
		
		immuLabel2.setFont(new Font("Comic Sans MS", 12));
		immuLabel2.setStyle("-fx-font-weight: bold");
		healthIssLabel2.setFont(new Font("Comic Sans MS", 12));
		healthIssLabel2.setStyle("-fx-font-weight: bold");
		medLabel2.setFont(new Font("Comic Sans MS", 12));
		medLabel2.setStyle("-fx-font-weight: bold");
		deleteLabel.setFont(new Font("Comic Sans MS", 12));
		deleteLabel.setStyle("-fx-font-weight: bold");
		
		date.setFont(new Font("Comic Sans MS", 12));
		date.setStyle("-fx-font-weight: bold");
		
		 String accLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		 fillOutPane.setStyle(accLayout);
		

		 
		fillOutPane.getChildren().addAll(recordHealthLabel,number, label, immu,buttons1, healthIssLabel2, healthIss, buttons2, medLabel2, med, buttons3, deleteLabel, deletion, buttons4, errorMess2);
	}
	
	private void buttonHandling() {
		 confirmAccountButton.setOnAction(new ButtonHandler());
		 immuSubmit.setOnAction(new ButtonHandler());
		 healthIssSubmit.setOnAction(new ButtonHandler());
		 medSubmit.setOnAction(new ButtonHandler());
		 delete.setOnAction(new ButtonHandler());
		 
	}
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == confirmAccountButton) {
				if (role.equals("Patient")) {
					if (passwordTF.getText().equals("") || reEnterTF.getText().equals("") || numberTF.getText().equals("")) {
						System.out.println("empty");
						errorMess1.setText("Invalid Password");
					} else if (numberTF.getText().length()!=10) {
						errorMess1.setText("Invalid phone number");
					} else if (!phoneChecker(numberTF.getText())) {
						errorMess1.setText("Invalid phone number");
					}else if (passwordTF.getText().length()<8) {
						errorMess1.setText("Too little characters");
					} else if (!specialCharChecker(passwordTF.getText())) {
						errorMess1.setText("No special characters");
					} else if (!passwordTF.getText().equals(reEnterTF.getText())){
						errorMess1.setText("Passwords do not match");
					} else {
						System.out.println("You are a " + role);
						userMaker();
					}
				} else {
					if (passwordTF.getText().equals("") || reEnterTF.getText().equals("")) {
						System.out.println("empty");
						errorMess1.setText("Invalid Password");
					}else if (passwordTF.getText().length()<8) {
						errorMess1.setText("Too little characters");
					} else if (!specialCharChecker(passwordTF.getText())) {
						errorMess1.setText("No special characters");
					} else if (!passwordTF.getText().equals(reEnterTF.getText())){
						errorMess1.setText("Passwords do not match");
					} else {
						System.out.println("You are a " + role);
						userMaker();
					}
				}
		

			} else if (source == logout) {
				System.out.println("Logout pressed");
				
				Login backToLogin = new Login();
				Window newWindow = accountScene.getWindow();
				if (newWindow instanceof Stage) {
					Stage newStage = (Stage) newWindow;
					newStage.setScene(backToLogin.getScene());
				}
			} else if (source == immuSubmit) {
				String placeholder = date1TF.getText()+ " " + immuTF.getText() +"\n";
				System.out.println("immu submit clicked");
				if(date1TF.getText().equals("") || immuTF.getText().equals("")) {
					errorMess2.setText("Invalid input");
				}
				else if(immu.contains(placeholder)) {
					errorMess2.setText("Duplicate input");
				}else if(date1TF.getText().length() != 10||date1TF.getText().charAt(2) != '/' || date1TF.getText().charAt(5) != '/'){
					errorMess2.setText("Date is not entered correctly. \nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);

				}
				else if(hasLetter(date1TF.getText())) {
					errorMess2.setText("Date is not entered correctly.\nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);
				}
				else {
					errorMess2.setText("");
					System.out.println("immunizations submitted");
					if (immu.equals("None")) {
						immu="";
					}
					immu = immu + date1TF.getText() + " " + immuTF.getText() +"\n";
					date1TF.setText("");
					immuTF.setText("");
					immunizationsTA.setText(immu);
				}
				
			} else if (source == healthIssSubmit) {
				System.out.println("health iss submit clicked");
				String placeholder = date2TF.getText()+ " " + healthIssTF.getText() +"\n";
				if(date2TF.getText().equals("") || healthIssTF.getText().equals("")) {
					errorMess2.setText("Invalid input");
				}
				else if(healthIss.contains(placeholder)) {
					errorMess2.setText("Duplicate input");
				}else if(date2TF.getText().length() != 10||date2TF.getText().charAt(2) != '/' || date2TF.getText().charAt(5) != '/'){
					errorMess2.setText("Date is not entered correctly. \nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);

				}
				else if(hasLetter(date2TF.getText())) {
					errorMess2.setText("Date is not entered correctly.\nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);
				}
				else {
					errorMess2.setText("");
					System.out.println("health issues submitted");
					if (healthIss.equals("None")) {
						healthIss="";
					}
					healthIss = healthIss+date2TF.getText() + " " + healthIssTF.getText()+"\n";
					date2TF.setText("");
					healthIssTF.setText("");
					healthIssTA.setText(healthIss);
				}
				
			} else if (source == medSubmit) {
				System.out.println("med submit clicked");
				String placeholder = date3TF.getText()+ " " + medTF.getText() +"\n";
				if(date3TF.getText().equals("") || medTF.getText().equals("")) {
					errorMess2.setText("Invalid input");
				}
				else if (med.contains(placeholder)) {
					errorMess2.setText("Duplicate input");
				}else if(date3TF.getText().length() != 10||date3TF.getText().charAt(2) != '/' || date3TF.getText().charAt(5) != '/'){
					errorMess2.setText("Date is not entered correctly. \nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);

				}
				else if(hasLetter(date3TF.getText())) {
					errorMess2.setText("Date is not entered correctly.\nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);
				}
				else {
					errorMess2.setText("");
					System.out.println("medications submitted");
					if (med.equals("None")) {
						med="";
					}
					med = med+date3TF.getText() + " " + medTF.getText()+"\n";
					date3TF.setText("");
					medTF.setText("");
					medTA.setText(med);
				}
				
			} else if (source == delete) {
				System.out.println("delete clicked");
				String placeholder = date4TF.getText()+ " " + deleteTF.getText() +"\n";
				if(date4TF.getText().equals("") || deleteTF.getText().equals("")) {
					errorMess2.setText("Invalid input");
				}else if(date4TF.getText().length() != 10||date4TF.getText().charAt(2) != '/' || date4TF.getText().charAt(5) != '/'){
					errorMess2.setText("Date is not entered correctly. \nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);

				}
				else if(hasLetter(date4TF.getText())) {
					errorMess2.setText("Date is not entered correctly.\nEnter in this format MM/DD/YYYY");
					errorMess2.setTextFill(Color.RED);
				}
				else {
					if (healthCB.getValue().equals("Immunizations")) {
						if (immu.contains(placeholder)) {
							int indexStart = immu.indexOf(placeholder);
							int indexEnd = indexStart+placeholder.length()-1;
							System.out.println("start: " + indexStart + " end: "+ indexEnd);
							immu = immu.substring(0, indexStart) + immu.substring(indexEnd+1, immu.length());
							System.out.println("This is final immu text: " + immu);
						
							immunizationsTA.setText(immu);
						}
					} else if (healthCB.getValue().equals("Health Issues")) {

						if (healthIss.contains(placeholder)) {
							int indexStart = healthIss.indexOf(placeholder);
							int indexEnd = indexStart+placeholder.length()-1;
							System.out.println("start: " + indexStart + " end: "+ indexEnd);
							healthIss = healthIss.substring(0,indexStart) + healthIss.substring(indexEnd+1, healthIss.length());
							System.out.println("This is final immu text: " + healthIss);
					
							healthIssTA.setText(healthIss);
						}
					} else if (healthCB.getValue().equals("Medications")) {
						if (med.contains(placeholder)) {
							int indexStart = med.indexOf(placeholder);
							int indexEnd = indexStart+placeholder.length()-1;
							System.out.println("start: " + indexStart + " end: "+ indexEnd);
							med = med.substring(0,indexStart) + med.substring(indexEnd+1, med.length());
							System.out.println("This is final immu text: " + med);
					
							medTA.setText(med);
						}
					} else {
						errorMess2.setText("No value selected in dropdown menu");
					}
				}// end of error if

			}//end of button if statements
		}//end of handle method
	} // end of button handler event class

	private boolean phoneChecker(String number) {
		for (int i = 0; i<number.length(); i++) {
			if (Character.isDigit(number.charAt(i))) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	private boolean specialCharChecker(String placeholder) {
		for (int i = 0; i<placeholder.length(); i++) {
			if (!Character.isDigit(placeholder.charAt(i))  && !Character.isLetter(placeholder.charAt(i)) ) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasLetter(String s) { //checks if there is a letter in a Date
		for(int i = 0; i < s.length(); i++) {
			if (i == 2 || i ==5) {
				continue;
			}
			if(!Character.isDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	public Scene getScene() {
		return accountScene;
	}
	
	private void userMaker() {
		if(role.equals("Nurse") || role.equals("Doctor")) {
			if (healthCareVeriTF.getText().equals(verificationCode)) {
				String dir = System.getProperty("user.dir") + "\\users\\healthcare professionals";
				File location = new File(dir);
				if(!location.exists()) {
					location.mkdirs();
				}
				File file = new File(dir + "\\" + username + ".txt");
				try {
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
		            BufferedWriter bw = new BufferedWriter(fw);
		            bw.write(first + " " + last + "\n" + username + "\n" + passwordTF.getText() + "\n" + dob);
		            bw.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			} else {
				errorMess1.setText("Wrong Verification Code");
			}

		} else if(role.equals("Patient")){
			String dir = System.getProperty("user.dir") + "\\users\\Patient";
			File location = new File(dir);
			if(!location.exists()) {
				location.mkdirs();
			}
			File file = new File(dir + "\\" + username + ".txt");
			File patients = new File(dir + "\\" + "Patients.txt");
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				FileWriter all = new FileWriter(patients.getAbsoluteFile(), true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(first + " " + last + "\n" + username + "\n" + passwordTF.getText() + "\n"+numberTF.getText()+"\n" + dob  + "\nimmunization\n" + immu + "\nhealth issues\n" + healthIss+ "\nmedication\n" + med);
	            bw.close();
	            BufferedWriter bdub = new BufferedWriter(all);
	            bdub.append(first + " " + last + " " + dob + "\n" + username + "\n");
	            bdub.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}//end of userMaker function

}


