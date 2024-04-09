package yourPediatricsPortal;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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


public class NurseView {
	private Scene nurseScene;
	private VBox mainPane,rightPane, leftPane, topRightPane, displayHisPane, HRButtonsPane;
	private HBox nurseUI, topPane, bottomPane, recordsPane, patientSelPane, patientContact, currentPatientPane, topLeftPane;
	private Label patientSelectLabel, vitalLabel, recordDirecLabel, HRTypeLabel,titleLabel, healthInfoLabel, patInfoLabel, currentPatient;
	private Label weightLabel, tempLabel, heightLabel, bloodLabel, allergyLabel, healthConLabel;
	private TextField patientWeight, patientHeight, patientTemp, patientBlood;
	private TextArea patientContactInfoText;
	private Button healthIssButton, medButton, immuButton, submitButton, cancelButton,  logoutButton, choosePatientButton;
	private ComboBox<String> patientSelect; //maybe make it a text field instead
	private TextArea allergyTA, healthConcernTA, healthHistoryTA;
	private GridPane vitalPanes;
	
	public NurseView() {
		mainPane = new VBox();
		nurseUI = new HBox();
		
		top();
		leftSide();
		rightSide();
		buttonHandler();
		nurseUI.getChildren().addAll(leftPane, rightPane);
		mainPane.getChildren().addAll(topPane, nurseUI);
		nurseUI.setPadding(new Insets(10,0,0,0));
		nurseScene = new Scene(mainPane,1280,730);
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
		vitalPanes = new GridPane();
		currentPatientPane = new HBox();
		topLeftPane = new HBox();
		
		
		vitalLabel = new Label("Vitals:");
		healthInfoLabel = new Label("Health Info:");
		vitalLabel.setPadding(new Insets(-10,0,0,0));
		leftPane.setSpacing(20);
		
	
		vitalLabel.setFont(new Font("Comic Sans MS", 13));
		vitalLabel.setStyle("-fx-font-weight: bold");
		patientSelect();
		vitalPaneFormatting();
		healthInformation();
		vitalFormButtons();
		
		leftPane.setPadding(new Insets(0,0,0,20));
		leftPane.getChildren().addAll(patientSelPane, topLeftPane,vitalLabel,vitalPanes, allergyLabel, allergyTA, healthConLabel, healthConcernTA, bottomPane);
		
	}
	
	private void patientSelect() {
		patientSelPane = new HBox(2);
		currentPatient = new Label("<No Patient Selected>");
		
		
		patientSelectLabel = new Label("Select a Patient");
		choosePatientButton = new Button("Choose Patient");
		
		patientSelectLabel.setFont(new Font("Comic Sans MS", 13));
		patientSelectLabel.setStyle("-fx-font-weight: bold");
		
		patientSelect = new ComboBox<String>();
		patInfoLabel=new Label("Patient Contact #: ");
		try {
			patientListing();
		} catch (FileNotFoundException ex) {
			System.out.println("FAIL");
		}

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
		
		patientContactInfoText.setText("");
		
		patientContact.setMargin(patInfoLabel, new Insets(3,5,2,5));
		patientSelPane.setPadding(new Insets(10,0,0,0));
		currentPatientPane.setMargin(currentPatient, new Insets(2,5,0,5));
		
		patientSelPane.setSpacing(10);
		topLeftPane.setSpacing(0.1);
		
		String contactLayout = "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n" ;
		patientContact.setStyle(contactLayout);
		currentPatientPane.setStyle(contactLayout);
		choosePatientButton.setOnAction(new ButtonHandler());
		
		currentPatientPane.getChildren().addAll(currentPatient);
		topLeftPane.getChildren().addAll(currentPatientPane, patientContact);
		patientSelPane.getChildren().addAll(patientSelectLabel,patientSelect, choosePatientButton);
	}
	
	private void patientListing()throws FileNotFoundException{
		String dir = System.getProperty("user.dir") + "\\users\\Patient\\Patients.txt";
		File patient = new File(dir);
		Scanner scnr = new Scanner(patient);
		int count = 0;
		System.out.println("something");
		while(scnr.hasNextLine()) {
			if (count%2 == 0) {
				String temp = scnr.nextLine();
				System.out.println(temp);
				patientSelect.getItems().add(temp);
			} else {
				scnr.nextLine();
			}
			count +=1;
		}
	}


	private void vitalPaneFormatting() {
		weightLabel = new Label("Weight (lbs):	");
		tempLabel = new Label("Temp (F):	");
		heightLabel = new Label("Height (ft. in.):	");
		bloodLabel = new Label("Blood Pressure (mmHg):	");
		
		patientWeight = new TextField();
		patientTemp = new TextField();
		patientHeight = new TextField();
		patientBlood =new TextField();
		
		//vitalPanes.setAlignment(Pos.CENTER); 
		
		
		vitalPanes.setPadding(new Insets(-9,0,0,00));
		vitalPanes.setVgap(0.1); 
	    vitalPanes.setHgap(10); 
	    vitalPanes.setPrefWidth(400);
	    
	   
	    
	    HBox weight = new HBox();
	    weight.getChildren().addAll(weightLabel, patientWeight);
	    String vitalLayout = "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n" ;
	    weight.setStyle(vitalLayout);
	    weight.setPadding(new Insets(1,1,1,10));
	    weight.setMargin(patientWeight, new Insets(0,0,0,86));
		weightLabel.setFont(new Font("Comic Sans MS", 12));
		weightLabel.setStyle("-fx-font-weight: bold");
		patientWeight.setMinWidth(150);
		patientWeight.setMaxWidth(150);
	    
	    HBox temp = new HBox();
	    temp.getChildren().addAll(tempLabel, patientTemp);
	    temp.setStyle(vitalLayout);
	    temp.setPadding(new Insets(1,1,1,10));
	    temp.setMargin(patientTemp, new Insets(0,0,0,86));
		tempLabel.setFont(new Font("Comic Sans MS", 12));
		tempLabel.setStyle("-fx-font-weight: bold");
		patientTemp.setMinWidth(150);
		patientTemp.setMaxWidth(150);
	    
	    HBox height = new HBox();
	    height.getChildren().addAll(heightLabel, patientHeight);
	    height.setStyle(vitalLayout);
	    height.setPadding(new Insets(1,1,1,10));
	    height.setMargin(patientHeight, new Insets(0,0,0,44));
	    //height.setMargin(patientHeight, new Insets(0,0,0,84));
		heightLabel.setFont(new Font("Comic Sans MS", 12));
		heightLabel.setStyle("-fx-font-weight: bold");
		patientHeight.setMinWidth(150);
		patientHeight.setMaxWidth(150);
	    
	    HBox blood = new HBox();
	    blood.getChildren().addAll(bloodLabel, patientBlood);
	    blood.setStyle(vitalLayout);
	    blood.setPadding(new Insets(1,1,1,10));
	    blood.setMargin(patientBlood, new Insets(0,0,0,2));
	    bloodLabel.setFont(new Font("Comic Sans MS", 12));
		bloodLabel.setStyle("-fx-font-weight: bold");
		patientBlood.setMinWidth(150);
		patientBlood.setMaxWidth(150);
		
	    
	    vitalPanes.add(weight, 0, 0);
	    vitalPanes.add(temp, 0, 1);
	    vitalPanes.add(height, 0, 2);
	    vitalPanes.add(blood, 0, 3);
	
		
	}
	
	private void healthInformation() {
		allergyLabel = new Label("Allergies");
		healthConLabel = new Label("Health Concerns");
		allergyTA = new TextArea();
		healthConcernTA = new TextArea();
		
		allergyLabel.setFont(new Font("Comic Sans MS", 13));
		allergyLabel.setStyle("-fx-font-weight: bold");
		healthConLabel.setFont(new Font("Comic Sans MS", 13));
		healthConLabel.setStyle("-fx-font-weight: bold");
		allergyTA.setWrapText(true);
		healthConcernTA.setWrapText(true);
		
		allergyTA.setMaxHeight(100);
		allergyTA.setMinHeight(100);
		healthConcernTA.setMaxHeight(100);
		healthConcernTA.setMinHeight(100);
		
		leftPane.setMargin(allergyTA, new Insets(-9,0,0,0));
		leftPane.setMargin(healthConcernTA, new Insets(-9,0,0,0));
	}
	
	private void vitalFormButtons() {
		bottomPane = new HBox();
		submitButton = new Button("Submit Form");
		cancelButton = new Button("Cancel");
		
		submitButton.setOnAction(null);
		bottomPane.getChildren().addAll(submitButton, cancelButton);
		
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
		
		rightPane.setPadding(new Insets(0,2,0,0));
		rightPane.setSpacing(5);
		
		topRightPane.setSpacing(2);
		
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
		submitButton.setOnAction(new ButtonHandler());
		healthIssButton.setOnAction(new ButtonHandler());
		medButton.setOnAction(new ButtonHandler());
		immuButton.setOnAction(new ButtonHandler());
	}
	
	public Scene getScene() {
		return nurseScene;
	}
	
	public class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == logoutButton) {
				System.out.println("Logout pressed");
				
				Login backToLogin = new Login();
				Window newWindow = nurseScene.getWindow();
				if (newWindow instanceof Stage) {
					Stage newStage = (Stage) newWindow;
					newStage.setScene(backToLogin.getScene());
				}
			}
			else if(source == submitButton) {
				collectData();
			}
			else if (source == choosePatientButton) {
				System.out.println("choose patient pressed");
				try {
					numberSetting();
				} catch(FileNotFoundException g) {
					System.out.println("error");
				}
			}
			else {
				try {
					healthHistoryTA.setText(getter(source));
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	private void numberSetting()throws FileNotFoundException{
		//String data = patientWeight.getText() + "\n" +  patientHeight.getText()+ "\n" +  patientTemp.getText() + "\n" +  patientBlood.getText()+"\n"+ "Allergies:\n" + allergyTA.getText() + "\n" +  "Health Concerns\n"+ healthConcernTA.getText();
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		currentPatient.setText(LastName + ", " + firstName.substring(0,4));
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + username + ".txt");
		
		Scanner scnr = new Scanner(file);
		int count = 0;
		while (count <3) {
			scnr.nextLine();
			count ++;
		}
		patientContactInfoText.setText(scnr.nextLine());
	}
	private String getter (Object source) throws FileNotFoundException {
		String info = "";
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		currentPatient.setText(LastName + ", " + firstName.substring(0,4));
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + username + ".txt");
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()) {
			info = info + "\n" + scnr.nextLine();
		}
		if(source == healthIssButton) {
			HRTypeLabel.setText("Health Issues");
			
			info = info.substring(info.indexOf("health issues")+ "health issues\n".length(), info.indexOf("medication"));
		}
		else if(source == medButton) {
			HRTypeLabel.setText("Medications");
			info = info.substring(info.indexOf("medication") + "medication\n".length());
		}
		else if(source == immuButton) {
			HRTypeLabel.setText("Immunizations");
			info = info.substring(info.indexOf("immunization")+"immunization\n".length(), info.indexOf("health issues"));
		}
		HRTypeLabel.setTextFill(Color.BLUE);
		return info;
	}
	public void collectData() {
		String data = patientWeight.getText() + "\n" +  patientHeight.getText()+ "\n" +  patientTemp.getText() + "\n" +  patientBlood.getText()+"\n"+ "Allergies:\n" + allergyTA.getText() + "\n" +  "Health Concerns\n"+ healthConcernTA.getText();
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + username + "_Vitals.txt");
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
	}
}
}