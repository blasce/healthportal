package yourPediatricsPortal;

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
	private Button something, logout;
	private Label titleLabel, passwordTitle;
	private TextField passwordTF, reEnterTF, healthCareVeriTF;
	private TextArea immunizationsTA, healthIssTA, medTA;
	private BorderPane newAccountUI;
	private Scene accountScene;
	private VBox passwordPane;
	private HBox topPane;
	private String first, last, dob, role;
	
	public createAccount (String first, String last, String dob, String role) {
		this.first = first;
		this.last = last;
		this.dob = dob;
		this.role = role;
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
		 something = new Button("Confirm Password");
		 
		 passwordPane.setMargin(passwordTitle, new Insets(10,0,10,0));
		 passwordPane.setMargin(passwordTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(reEnterTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(healthCareVeriTF, new Insets(5,10,10,10));
		 passwordPane.setMargin(something, new Insets(10,0,0,0));
		 passwordPane.setAlignment(Pos.TOP_CENTER);
			
		 passwordTitle.setFont(new Font("Comic Sans MS", 20));
		 passwordTitle.setStyle("-fx-font-weight: bold");
		 
		 String createAccLayout = "-fx-border-color: black;\n" +
	                "-fx-border-insets: 1;\n" +
	                "-fx-border-width: 2;\n" +
	                "-fx-background-color: #9abaed;\n";
		 passwordPane.setStyle(createAccLayout);

		 passwordPane.setMaxWidth(400);
		 passwordPane.setMaxHeight(350);
		 passwordPane.setMinWidth(400);
		 passwordPane.setMinHeight(350);
		 //passwordTF,setPrompt("")
		 newAccountUI.setCenter(passwordPane);
		 
		 
		 
		 passwordPane.getChildren().add(passwordTitle);
		 passwordPane.getChildren().add(passwordTF);
		 passwordPane.getChildren().add(reEnterTF);
		 if (role.equals("Doctor") || role.equals("Nurse")) {
			 passwordPane.getChildren().add(healthCareVeriTF);
		 }
		 passwordPane.getChildren().add(something);
	
		 something.setOnAction(new ButtonHandler());
		 
	 }
	
	private void passwordSec() {
		
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
