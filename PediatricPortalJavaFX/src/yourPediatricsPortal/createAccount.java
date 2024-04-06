package yourPediatricsPortal;



import javafx.scene.paint.Color;

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
	private Button something, logout, immuSubmit, healthIssSubmit, medSubmit, cancel1, cancel2, cancel3;
	private Label titleLabel, passwordTitle, recordHealthLabel, immuLabel, healthIssLabel, medLabel, nameLabel, dobLabel, userLabel;
	private Label healthLabel, immuLabel2, healthIssLabel2, medLabel2;
	private Label date;
	private TextField passwordTF, reEnterTF, healthCareVeriTF, immuTF, healthIssTF, medTF, date1TF, date2TF, date3TF;
	private TextArea immunizationsTA, healthIssTA, medTA;
	private BorderPane newAccountUI;
	private Scene accountScene;
	private VBox passwordPane, patientPane, passUserPane, accountInfoPane, fillOutPane;
	private HBox topPane, midPane;
	private String first, last, dob, role,username;
	
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
		 passwordTF.setPromptText("Enter a password");
		 reEnterTF = new TextField("");
		 reEnterTF.setPromptText("Re-enter password");
		 healthCareVeriTF = new TextField("");
		 healthCareVeriTF.setPromptText("Enter verification code");
		 something = new Button("Confirm Account");
		 
		 passwordPane.setMargin(passwordTitle, new Insets(10,0,10,-250));
		 passwordPane.setMargin(passwordTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(reEnterTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(healthCareVeriTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(something, new Insets(10,0,0,0));
		 passwordPane.setAlignment(Pos.TOP_CENTER);
			
		 passwordTitle.setFont(new Font("Comic Sans MS", 14));
		 passwordTitle.setStyle("-fx-font-weight: bold");
		 
		 String createAccLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		passwordPane.setStyle(createAccLayout);

		 passwordPane.setMaxWidth(400);
		 passwordPane.setMaxHeight(370);
		 passwordPane.setMinWidth(400);
		 passwordPane.setMinHeight(370);
		 //passwordTF,setPrompt("")
		 newAccountUI.setCenter(passwordPane);
		 
		 
		 
		 passwordPane.getChildren().add(passwordTitle);
		 passwordPane.getChildren().add(passwordTF);
		 passwordPane.getChildren().add(reEnterTF);
		 if (role.equals("Doctor") || role.equals("Nurse")) {
			 passwordPane.getChildren().add(healthCareVeriTF);
		 } else {
			 patientForm();
		 }
		 passwordPane.getChildren().add(something);
	
		 something.setOnAction(new ButtonHandler());
		 
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
		
		healthLabel = new Label("Health Form: Fill out below accordingly");
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
		immuSubmit = new Button("Submit");
		healthIssSubmit = new Button("Submit");
		medSubmit = new Button("Submit");
		cancel1 = new Button("Cancel");
		cancel2 =new Button("Cancel");
		cancel3 = new Button("Cancel");
		
		fillOutPane.setMargin(recordHealthLabel, new Insets(0,10,0,5));
		
		HBox label = new HBox();
		label.getChildren().addAll(immuLabel2, date);
		label.setSpacing(80);
		fillOutPane.setMargin(label, new Insets(0,10,0,10));
		
		HBox immu = new HBox();
		immu.getChildren().addAll(immuTF, date1TF);
		HBox buttons1 = new HBox();
		buttons1.getChildren().addAll(immuSubmit, cancel1);
		fillOutPane.setMargin(immu, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons1, new Insets(5,10,20,10));
		immu.setSpacing(10);
		buttons1.setSpacing(10);
		
		HBox healthIss = new HBox();
		healthIss.getChildren().addAll(healthIssTF, date2TF);
		HBox buttons2 = new HBox();
		buttons2.getChildren().addAll(healthIssSubmit, cancel2);
		fillOutPane.setMargin(healthIssLabel2, new Insets(0,10,0,10));
		fillOutPane.setMargin(healthIss, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons2, new Insets(5,10,20,10));
		healthIss.setSpacing(10);
		buttons2.setSpacing(10);
		
		HBox med = new HBox();
		med.getChildren().addAll(medTF, date3TF);
		HBox buttons3 = new HBox();
		buttons3.getChildren().addAll(medSubmit, cancel3);
		fillOutPane.setMargin(medLabel2, new Insets(0,10,0,10));
		fillOutPane.setMargin(med, new Insets(0,10,0,10));
		fillOutPane.setMargin(buttons3, new Insets(5,10,20,10));
		med.setSpacing(10);
		buttons3.setSpacing(10);
		
		recordHealthLabel.setFont(new Font("Comic Sans MS", 14));
		recordHealthLabel.setStyle("-fx-font-weight: bold");
		
		immuLabel2.setFont(new Font("Comic Sans MS", 12));
		immuLabel2.setStyle("-fx-font-weight: bold");
		healthIssLabel2.setFont(new Font("Comic Sans MS", 12));
		healthIssLabel2.setStyle("-fx-font-weight: bold");
		medLabel2.setFont(new Font("Comic Sans MS", 12));
		medLabel2.setStyle("-fx-font-weight: bold");
		
		date.setFont(new Font("Comic Sans MS", 12));
		date.setStyle("-fx-font-weight: bold");
		
		 String accLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		 fillOutPane.setStyle(accLayout);
		
		fillOutPane.getChildren().addAll(recordHealthLabel, label, immu,buttons1, healthIssLabel2, healthIss, buttons2, medLabel2, med, buttons3);
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == something) {
				System.out.println("Welcome to your new account " + first + " " + last);
				System.out.println("Your username will be: " + first.substring(0,1) + last+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10)); //username creation
				System.out.println("You are a " + role);
			} else if (source == logout) {
				System.out.println("Logout pressed");
				
				Login backToLogin = new Login();
				Window newWindow = accountScene.getWindow();
				if (newWindow instanceof Stage) {
					Stage newStage = (Stage) newWindow;
					newStage.setScene(backToLogin.getScene());
				}
			}
		}
	}
	
	public Scene getScene() {
		return accountScene;
	}
	

}