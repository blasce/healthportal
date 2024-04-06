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
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DoctorView {
	private Label patientSelectLabel, vitalLabel, recordDirecLabel, HRTypeLabel, presLabel, titleLabel, healthInfoLabel;
	private Label weightLabel, tempLabel, heightLabel, bloodLabel, patientWeight, patientTemp, patientHeight, patientBlood, currentPatient, patInfoLabel;
	private Button healthIssButton, medButton, immuButton, alleButton, healthConcButton, presButton, cancelButton, cancel2Button, logoutButton, sumButton, choosePatientButton;
	private ComboBox<String> patientSelect; //maybe make it a text field instead
	private TextArea prescribeTA, summTA, patTA, healthHistoryTA, patientContactInfoText;
	private TextField  addressTF, dateTF;
	private VBox mainPane, rightPane, leftPane, aCbuttonPane, bottomLeftPane, topRightPane, HRButtonsPane, displayHisPane;
	private HBox doctorUI, topPane, aCPane, recordsPane, currentPatientPane, topLeftPane, patientSelPane, patientContact;
	private Scene scene;
	private GridPane vitalPanes;
	private String patientCurrent;
	
	public DoctorView() {
		mainPane = new VBox();
		doctorUI = new HBox();
		
		top();
		leftSide();
		rightSide();
		buttonHandler();
		doctorUI.getChildren().addAll(leftPane, rightPane);
		mainPane.getChildren().addAll(topPane, doctorUI);
		
		scene = new Scene(mainPane, 1280, 730);
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
		currentPatientPane = new HBox();
		topLeftPane = new HBox();
		
		vitalLabel = new Label("Vitals:");
		healthInfoLabel = new Label("Health Info:");
		
		
		vitalLabel.setFont(new Font("Comic Sans MS", 13));
		vitalLabel.setStyle("-fx-font-weight: bold");
		healthInfoLabel.setFont(new Font("Comic Sans MS", 13));
		healthInfoLabel.setStyle("-fx-font-weight: bold");
		vitalPanes = new GridPane();
		patientSelect();
		vitalPaneFormatting();
		patientAllergiesAndConcerns();
		prescribeAndSummary();		
		leftPane.setPadding(new Insets(5,0,10,20));
		leftPane.setSpacing(10);	
		leftPane.getChildren().addAll(patientSelPane, topLeftPane,vitalLabel,vitalPanes, healthInfoLabel,aCPane, bottomLeftPane);
	}
	
	
	private void patientSelect() {
		patientSelPane = new HBox(2);
		patientCurrent = "Bob Marley";
		currentPatient = new Label("Current Patient: " + patientCurrent);
		
		
		patientSelectLabel = new Label("Select a Patient");
		choosePatientButton = new Button("Choose Patient");
		
		patientSelectLabel.setFont(new Font("Comic Sans MS", 13));
		patientSelectLabel.setStyle("-fx-font-weight: bold");
		
		patientSelect = new ComboBox<String>();
		patientSelect.getItems().addAll("Patient1", "Patient2", "Patient3");
		patInfoLabel=new Label("Patient Contact #: ");

		currentPatient.setFont(new Font("Comic Sans MS", 13));
		currentPatient.setStyle("-fx-font-weight: bold");
		patInfoLabel.setFont(new Font("Comic Sans MS", 12));
		patInfoLabel.setStyle("-fx-font-weight: bold");
		patientSelect.setMinWidth(200);
		patientContact = new HBox();
		
		patientContact.setMaxWidth(250);
		patientContact.setMinWidth(250);
		
		patientContactInfoText = new TextArea();
		patientContactInfoText.setEditable(false);
		patientContact.getChildren().addAll(patInfoLabel, patientContactInfoText);
		patientContactInfoText.setMinWidth(120);
		patientContactInfoText.setMaxWidth(120);
		patientContactInfoText.setMinHeight(27);
		patientContactInfoText.setMaxHeight(27);
		
		patientContactInfoText.setText("213-332-9131");
		
		patientContact.setMargin(patInfoLabel, new Insets(3,5,2,5));
		patientSelPane.setPadding(new Insets(3,0,0,0));
		currentPatientPane.setMargin(currentPatient, new Insets(2,5,0,5));
		
		patientSelPane.setSpacing(10);
		topLeftPane.setSpacing(0.1);
		
		String contactLayout = "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n" ;
		patientContact.setStyle(contactLayout);
		currentPatientPane.setStyle(contactLayout);
		
		currentPatientPane.getChildren().addAll(currentPatient);
		topLeftPane.getChildren().addAll(currentPatientPane, patientContact);
		patientSelPane.getChildren().addAll(patientSelectLabel,patientSelect, choosePatientButton);
	}

	
	private void vitalPaneFormatting() {
		weightLabel = new Label("Weight (lbs):	");
		tempLabel = new Label("Temp (F):	");
		heightLabel = new Label("Height (ft. in.):	");
		bloodLabel = new Label("Blood Pressure (mmHg):	");
		
		patientWeight = new Label("34");
		patientTemp = new Label("98.6");
		patientHeight = new Label("5'7");
		patientBlood = new Label("120/80");
		
		//vitalPanes.setAlignment(Pos.CENTER_RIGHT); 
		
		vitalPanes = new GridPane();
		vitalPanes.setPadding(new Insets(-5,0,0,00));
		vitalPanes.setVgap(0.1); 
	    vitalPanes.setHgap(10); 
	    vitalPanes.setPrefWidth(400);
	    
	   
	    
	   HBox weight = new HBox();
	    weight.getChildren().addAll(weightLabel, patientWeight);
	    String vitalLayout = "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n" ;
	    weight.setStyle(vitalLayout);
	    weight.setPadding(new Insets(1,1,1,10));
	    patientWeight.setPadding(new Insets(0,10,0,112));
	    //weight.setMargin(patientWeight, new Insets(0,0,0,120));
		weightLabel.setFont(new Font("Comic Sans MS", 12));
		weightLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox temp = new HBox();
	    temp.getChildren().addAll(tempLabel, patientTemp);
	    temp.setStyle(vitalLayout);
	    temp.setPadding(new Insets(1,1,1,10));
	    patientTemp.setPadding(new Insets(0,10,0,108));
	    //temp.setMargin(patientTemp, new Insets(0,0,0,118));
		tempLabel.setFont(new Font("Comic Sans MS", 12));
		tempLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox height = new HBox();
	    height.getChildren().addAll(heightLabel, patientHeight);
	    height.setStyle(vitalLayout);
	    height.setPadding(new Insets(1,1,1,10));
	    patientHeight.setPadding(new Insets(0,10,0,74));
	    //height.setMargin(patientHeight, new Insets(0,0,0,84));
		heightLabel.setFont(new Font("Comic Sans MS", 12));
		heightLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox blood = new HBox();
	    blood.getChildren().addAll(bloodLabel, patientBlood);
	    blood.setStyle(vitalLayout);
	    blood.setPadding(new Insets(1,1,1,10));
	    patientBlood.setPadding(new Insets(0,10,0,10));
	    //blood.setMargin(patientBlood, new Insets(0,4,0,20));
	    bloodLabel.setFont(new Font("Comic Sans MS", 12));
		bloodLabel.setStyle("-fx-font-weight: bold");
	    
	    vitalPanes.add(weight, 0, 0);
	    vitalPanes.add(temp, 0, 1);
	    vitalPanes.add(height, 0, 2);
	    vitalPanes.add(blood, 0, 3);
	
		
	}
	
	private void patientAllergiesAndConcerns() {
		aCbuttonPane = new VBox();
		aCPane = new HBox();
		aCPane.setSpacing(10);
		aCbuttonPane.setSpacing(10);
		VBox textPane = new VBox();
		alleButton = new Button("Allergies");
		healthConcButton = new Button("Health Concerns");
		patTA = new TextArea("<Click on Button>");
		patTA.setEditable(false);
		
		
		alleButton.setMaxWidth(120);
		healthConcButton.setMaxWidth(120);
		
		aCPane.setPadding(new Insets(-10,0,0,0));
		
		String textLayout =  "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n";
		textPane.setStyle(textLayout);
		textPane.setMinWidth(50);
		textPane.setMaxWidth(300);
		textPane.getChildren().addAll(patTA);
		aCbuttonPane.getChildren().addAll(alleButton, healthConcButton);
		aCPane.getChildren().addAll(aCbuttonPane, textPane);
		
	}

	
	private void prescribeAndSummary() {
		bottomLeftPane = new VBox();
		presLabel = new Label("Prescription and Patient Summary");
		addressTF = new TextField();
		prescribeTA = new TextArea();
		dateTF = new TextField();
		summTA = new TextArea();
		
		// NOTE will take pharmacy address from user records
		addressTF.setPromptText("<Pharmacy Address Here>");
		addressTF.setEditable(false);
		
		dateTF.setPromptText("<Place date in format: MM/DD/YYYY>");
		
		presLabel.setFont(new Font("Comic Sans MS", 13));
		presLabel.setStyle("-fx-font-weight: bold");
		
		prescribeTA.setMaxHeight(100);
		prescribeTA.setMinHeight(100);
		prescribeTA.setPrefHeight(Region.USE_COMPUTED_SIZE);
		summTA.setMinHeight(100);
		summTA.setMaxHeight(100);
		summTA.setPrefHeight(Region.USE_COMPUTED_SIZE);
		
		bottomLeftPane.setMargin(addressTF, new Insets(-10,0,-9,0));
		bottomLeftPane.setMargin(dateTF, new Insets(0,0,-9,0));
		prescribeTA.setWrapText(true);
		summTA.setWrapText(true);
		
		HBox buttons = new HBox();
		presButton = new Button("Prescribe");
		cancelButton = new Button("Cancel");
		buttons.setSpacing(10);
		buttons.getChildren().addAll(presButton, cancelButton);
		
		HBox buttons2= new HBox();
		sumButton = new Button("Submit Summary");
		cancel2Button = new Button("Cancel");
		buttons2.setSpacing(10);
		buttons2.getChildren().addAll(sumButton,cancel2Button);
		
		bottomLeftPane.setSpacing(10);
		bottomLeftPane.getChildren().addAll(presLabel,addressTF,  prescribeTA, buttons,dateTF, summTA, buttons2);
		
		
	}
 
	private void rightSide() {
		rightPane = new VBox();
		topRightPane = new VBox();
		recordsPane = new HBox();
		recordDirecLabel = new Label("Select the type of records that you want to view:");
		displayHisPane = new VBox();
		
		HRTypeLabel = new Label("<None Selected>");
		healthHistoryTA = new TextArea();
		healthHistoryTA.setEditable(false);
		
		healthRecordsButtons();
		
		recordDirecLabel.setFont(new Font("Comic Sans MS", 13));
		recordDirecLabel.setStyle("-fx-font-weight: bold");
		
		HRTypeLabel.setFont(new Font("Comic Sans MS", 13));
		HRTypeLabel.setStyle("-fx-font-weight: bold");
		
		rightPane.setPadding(new Insets(5,2,0,0));
		rightPane.setSpacing(10);
		
		topRightPane.setSpacing(10);
		
		recordsPane.setSpacing(10);
		
		displayHisPane.setMinWidth(640);
		displayHisPane.setMaxWidth(640);
		
		displayHisPane.getChildren().addAll(HRTypeLabel, healthHistoryTA);
		recordsPane.getChildren().addAll(HRButtonsPane, displayHisPane);
		topRightPane.getChildren().addAll(recordDirecLabel, recordsPane);
		rightPane.getChildren().addAll(topRightPane);
	}
	
	private void healthRecordsButtons() {
		HRButtonsPane = new VBox();
		healthIssButton = new Button("Health Issues");
		medButton = new Button("Medications");
		immuButton = new Button("Immunizations");
		
		HRButtonsPane.setSpacing(10);
		healthIssButton.setMinWidth(95);
		medButton.setMinWidth(95);
		immuButton.setMinWidth(95);
		
		HRButtonsPane.setPadding(new Insets(20,0,0,0));
		HRButtonsPane.getChildren().addAll(healthIssButton, medButton,immuButton);
	}
	
	private void buttonHandler() {
		logoutButton.setOnAction(new ButtonHandler());
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == logoutButton) {
				System.out.println("Logout pressed");
				
				Login backToLogin = new Login();
				Window newWindow = scene.getWindow();
				if (newWindow instanceof Stage) {
					Stage newStage = (Stage) newWindow;
					newStage.setScene(backToLogin.getScene());
				}
			}
			
		}
	}
	
}
