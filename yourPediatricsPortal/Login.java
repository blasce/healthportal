package yourPediatricsPortal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Login{
	//private VBox vbox1;
	private Button createAccountButton, loginButton;
	private Label titleLabel, createAccountLabel, loginLabel, createError, loginError;
	private TextField firstNameTF, lastNameTF, dobTF, usernameTF, passwordTF;
	private ComboBox<String> roleSelectCB, roleSelectCB2;
	private BorderPane loginUI;
	private HBox centerFieldPane, topPane;
	private VBox newAccountPane, loginPane;
	private Scene scene;
	
	public String firstName, lastName, dob, role;
	
	public Login() {
		//creates new pane and new scene
		loginUI = new BorderPane();
		scene = new Scene(loginUI, 1280, 720);
		top();
		middle();
		
	}

	//implements top of the borderpane
	private void top() {
		topPane = new HBox();
		ImageView image = new ImageView("https://cdn-icons-png.flaticon.com/256/3959/3959107.png");
		image.setFitHeight(50);
		image.setFitWidth(50);
		titleLabel = new Label("YourPediatricsPortal", image);
		titleLabel.setFont(new Font("Comic Sans MS", 24));
		titleLabel.setStyle("-fx-font-weight: bold");
		titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	
		
		
		
		String topPaneLayout ="-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1.5;\n" +
                "-fx-background-color: #9abaed;\n";
		topPane.setStyle(topPaneLayout);
		topPane.setMargin(titleLabel, new Insets(10,10,10,10));
		
		topPane.getChildren().add(titleLabel);
		loginUI.setTop(topPane);
		
	}
	
	//implements the middle of the border pane
	private void middle() {
		loginUI.setRight(null);
		loginUI.setLeft(null);
		centerFieldPane = new HBox();
		BorderPane.setAlignment(centerFieldPane, Pos.CENTER);
		centerFieldPane.setMaxWidth(800);
		centerFieldPane.setMaxHeight(350);


		loginUI.setCenter(centerFieldPane);

		accountPaneFunction();
		loginPaneFunction();
		
		centerFieldPane.getChildren().add(newAccountPane);
		centerFieldPane.getChildren().add(loginPane);
		
		
	}
	
	//all ui involving the Create an Account
	private void accountPaneFunction() {
		newAccountPane = new VBox();
		createAccountLabel = new Label("Create an Account");
		createAccountButton = new Button("Create Account");
		createError = new Label();
		firstNameTF = new TextField("");
		firstNameTF.setPromptText("First Name");
		lastNameTF = new TextField("");
		lastNameTF.setPromptText("Last Name");
		dobTF = new TextField("");
		dobTF.setPromptText("DOB: MM/DD/YYYY");
		roleSelectCB2 = new ComboBox<String>();
		roleSelectCB2.getItems().addAll("Patient", "Doctor", "Nurse");
		roleSelectCB2.setValue("Select");
		
		
		createAccountLabel.setFont(new Font("Comic Sans MS", 20));
		createAccountLabel.setStyle("-fx-font-weight: bold");
		
		createError.setFont(new Font("Comic Sans MS", 12));
		createError.setStyle("-fx-font-weight: bold");
		
		newAccountPane.setMargin(createAccountLabel, new Insets(10,60,10,10));
		newAccountPane.setMargin(firstNameTF, new Insets(5,60,10,10));
		newAccountPane.setMargin(lastNameTF, new Insets(5,60,10,10));
		newAccountPane.setMargin(dobTF, new Insets(5,60,10,10));
		newAccountPane.setMargin(roleSelectCB2, new Insets(5,60,10,10));
		newAccountPane.setMargin(createAccountButton, new Insets(5,120,10,10));
		newAccountPane.setMargin(createError, new Insets(0,60,10,10));
	
		
		String newAccLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-background-color: #9abaed;\n";
		newAccountPane.setStyle(newAccLayout);
		

		
		newAccountPane.setMaxWidth(400);
		newAccountPane.setMaxHeight(300);
		newAccountPane.setMinWidth(400);
		newAccountPane.setMinHeight(300);
		createAccountButton.setOnAction(new ButtonHandler());
		
		
		newAccountPane.getChildren().add(createAccountLabel);
		newAccountPane.getChildren().add(firstNameTF);
		newAccountPane.getChildren().add(lastNameTF);
		newAccountPane.getChildren().add(dobTF);
		newAccountPane.getChildren().add(roleSelectCB2);
		newAccountPane.getChildren().add(createAccountButton);
		newAccountPane.getChildren().add(createError);
		
		
	}
	
	// all ui involving Login
	private void loginPaneFunction() {
		loginPane = new VBox();
		loginLabel = new Label("Login");
		usernameTF = new TextField("");
		usernameTF.setPromptText("Username");
		passwordTF = new TextField("");
		passwordTF.setPromptText("Password");
		roleSelectCB = new ComboBox<String>();
		roleSelectCB.getItems().addAll("Patient", "Doctor", "Nurse");
		roleSelectCB.setValue("Select");
		loginButton = new Button("Login");
		
		loginLabel.setFont(new Font("Comic Sans MS", 20));
		loginLabel.setStyle("-fx-font-weight: bold");
		
		String loginLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1.5;\n" +
                "-fx-background-color: #9abaed;\n";
		loginPane.setStyle(loginLayout);
		
		loginPane.setMargin(loginLabel, new Insets(9,50,10,10));
		loginPane.setMargin(usernameTF, new Insets(5,60,10,10));
		loginPane.setMargin(passwordTF, new Insets(5,60,10,10));
		loginPane.setMargin(roleSelectCB, new Insets(5,60,10,10));
		loginPane.setMargin(loginButton, new Insets(5,120,10,10));
		loginPane.setMaxWidth(400);
		loginPane.setMaxHeight(300);
		loginPane.setMinWidth(400);
		loginPane.setMinHeight(300);
		loginPane.getChildren().add(loginLabel);
		loginPane.getChildren().add(usernameTF);
		loginPane.getChildren().add(passwordTF);
		loginPane.getChildren().add(roleSelectCB);
		loginPane.getChildren().add(loginButton);
	}
	
	//function to switch scene
	public Scene getScene() {
		return scene;
	}
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			try {
				//exception error handling
				if(firstNameTF.getText().equals("") || lastNameTF.getText().equals("") || dobTF.getText().equals("") || roleSelectCB2.getValue().equals("Select")) {
					createError.setText("One or more of the boxes is empty");
					createError.setTextFill(Color.RED);
				}
				else if(hasNumber(firstNameTF.getText()) || hasNumber(lastNameTF.getText())) {
					createError.setText("First or last name entered incorrectly!");
					createError.setTextFill(Color.RED);
				}
				else if(dobTF.getText().length() != 10|dobTF.getText().charAt(2) != '/' || dobTF.getText().charAt(5) != '/'){
					createError.setText("Date of birth is not entered correctly. \nEnter in this format MM/DD/YYYY");
					createError.setTextFill(Color.RED);

				}
				else if(hasLetter(dobTF.getText())) {
					createError.setText("SDate of birth is not entered correctly.\nEnter in this format MM/DD/YYYY");
					createError.setTextFill(Color.RED);
				}
				else { // switches to new page
					if (source == createAccountButton) {
						System.out.println("Account will be created!");
						
						firstName = firstNameTF.getText();
						lastName = lastNameTF.getText();
						dob = dobTF.getText();
						role = roleSelectCB2.getValue();
						
						createAccount acc = new createAccount(firstName, lastName, dob, role); //changes to account screen
						Window newWindow = scene.getWindow();
						if (newWindow instanceof Stage) {
							Stage newStage = (Stage) newWindow;
							newStage.setScene(acc.getScene());
						}
					}
				}
			}
			catch(Exception exception) {
				System.out.println("Error");
			}
		}
	}
	private boolean hasNumber(String s) { //checks if there is a number in a string
		boolean num = false;
		for(int i = 0; i < s.length(); i++) {
			
			if(Character.isDigit(s.charAt(i))) {
				num = true;
				return num;
			}
		}
		return num;
	}
	private boolean hasLetter(String s) { //checks if there is a letter in a date of birth
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
}
