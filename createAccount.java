package yourPediatricsPortal;

import javafx.application.Application;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
	private Label testing, titleLabel;
	private BorderPane newAccountUI;
	private Scene accountScene;
	private HBox middlePane;
	private HBox topPane;
	private String first, last, dob;
	
	public createAccount () {
		newAccountUI = new BorderPane();
		accountScene = new Scene(newAccountUI, 1280,720);
		top();
		middle();
		
	}
	
	private void top() {
		topPane = new HBox();
		
		ImageView image = new ImageView("https://cdn-icons-png.flaticon.com/256/3959/3959107.png");
		image.setFitHeight(50);
		image.setFitWidth(50);
		titleLabel = new Label("YourPediatricsPortal", image);
		titleLabel.setFont(new Font("Comic Sans MS", 24));
		titleLabel.setStyle("-fx-font-weight: bold");
		titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		ImageView imageLogout = new ImageView ("https://static-00.iconduck.com/assets.00/log-out-outline-icon-512x432-rbmppekf.png");
		imageLogout.setFitHeight(30);
		imageLogout.setFitWidth(30);
		logout = new Button("",imageLogout);
	
		
		
		String topPaneLayout ="-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1.5;\n" +
                "-fx-background-color: #9abaed;\n";
		topPane.setStyle(topPaneLayout);
		topPane.setMargin(titleLabel, new Insets(10,10,10,0));
		topPane.setMargin(logout, new Insets(15,0,0,890));
		
		topPane.getChildren().add(titleLabel);
		topPane.getChildren().add(logout);
		
		newAccountUI.setTop(topPane);
	}
	 private void middle() {
		 middlePane = new HBox();
		 newAccountUI.setCenter(middlePane);
		 something = new Button("press!");
		 
		 
		 
		 middlePane.getChildren().add(something);
		 something.setOnAction(new ButtonHandler());
		 
	 }
	
	 //gets info from login page
	 public void getInfo(String fn, String ln, String birthdate) {
			this.first = fn;
			this.last = ln;
			this.dob = birthdate;
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == something) {
				System.out.println("Welcome to your new account " + first + " " + last);
				System.out.println("Your username will be: " + first.substring(0,1) + last+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10)); //username creation
			}
		}
	}
	
	public Scene getScene() {
		return accountScene;
	}
	

}
