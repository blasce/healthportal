package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientView {

	private Label patientSelectLabel, vitalLabel, recordDirecLabel, HRTypeLabel, presLabel, titleLabel, healthInfoLabel;
	private Label weightLabel, tempLabel, heightLabel, bloodLabel, patientWeight, patientTemp, patientHeight, patientBlood;
	private Text patientContactInfoText;
	private Button healthIssButton, medButton, immuButton, alleButton, healthConcButton, presButton, cancelButton, cancel2Button, logoutButton, sumButton;
	private ComboBox<String> patientSelect; //maybe make it a text field instead
	private TextArea prescribeTA, summTA, patTA, healthHistoryTA;
	private TextField  addressTF, dateTF;
	private VBox mainPane, rightPane, leftPane, aCbuttonPane, bottomLeftPane, topRightPane, HRButtonsPane, displayHisPane;
	private HBox patientUI, topPane, aCPane, recordsPane;
	private Scene scene;
	private GridPane vitalPanes;
	
	 private ListView<String> visitDatesListView;
	    private ListView<String> messagesListView;
	    private TextArea visitDetailsTextArea;
	    private TextArea messageTextArea;
	    private TextField patientContactTextField;
	
	    public void start(Stage primaryStage) {
		mainPane = new VBox();
		patientUI = new HBox();
		
		top();
		leftSide();
		rightSide();
		patientUI.getChildren().addAll(leftPane, rightPane);
		mainPane.getChildren().addAll(topPane, patientUI);
		
		scene = new Scene(mainPane, 1280, 730);
		
		// Finalize and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Pediatric Portal - Patient View");
        primaryStage.show();
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
		logoutButton = new Button("",imageLogout);
		String topPaneLayout ="-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1.5;\n" +
                "-fx-background-color: #9abaed;\n";
		topPane.setStyle(topPaneLayout);
		topPane.setMargin(titleLabel, new Insets(5,10,10,0));
		topPane.setMargin(logoutButton, new Insets(15,0,0,960));
		
		topPane.getChildren().add(titleLabel);
		topPane.getChildren().add(logoutButton);
		
	}
	
	private void leftSide() {
		leftPane = new VBox();
		// Left side with visit summaries and prescriptions
        visitDatesListView = new ListView<>();
        // Populate the list with visit dates

        visitDetailsTextArea = new TextArea();
        visitDetailsTextArea.setEditable(false); // Patient should only view the details

        leftPane.getChildren().addAll(new Label("Visit Dates"), visitDatesListView, new Label("Visit Details"), visitDetailsTextArea);
        leftPane.setSpacing(5);
        leftPane.setPadding(new Insets(10));
	}
	private void rightSide() {
		rightPane = new VBox();
		// Right side with contact information and messaging system
        Label patientContactLabel = new Label("My Contact Number:");
        patientContactTextField = new TextField(); // To be populated with patient's contact information

        Label providerContactLabel = new Label("Provider's Contact Information:");
        TextField providerContactTextField = new TextField(); // To be populated with provider's contact information
        providerContactTextField.setEditable(false);

        // Messaging system
        messagesListView = new ListView<>();
        // Populate the list with message threads

        messageTextArea = new TextArea();
        Button sendMessageButton = new Button("Send");
        rightPane.getChildren().addAll(patientContactLabel, patientContactTextField, providerContactLabel, providerContactTextField,
                new Label("Messages"), messagesListView, messageTextArea, sendMessageButton);
        rightPane.setSpacing(5);
        rightPane.setPadding(new Insets(10));
	
	}
	public Scene getScene() {
		return scene;
	}
	
	public class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
		}
	}
	
	

	
}


		
	
	