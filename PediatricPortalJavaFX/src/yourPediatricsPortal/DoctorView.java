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
import yourPediatricsPortal.NurseView.ButtonHandler;
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
	private Label userLabel;
	private Button healthIssButton, medButton, immuButton, alleButton, healthConcButton, presButton, cancelButton, cancel2Button, logoutButton, sumButton, choosePatientButton;
	private Button chooseTextButton, messageButton;
	private ComboBox<String> patientSelect; //maybe make it a text field instead
	private ComboBox<String> messageSelect;
	private TextArea prescribeTA, summTA, patTA, healthHistoryTA, patientContactInfoText, texts;
	private TextArea textingTA;
	private TextField  addressTF, dateTF, datePresTF;
	private VBox mainPane, rightPane, leftPane, aCbuttonPane, bottomLeftPane, topRightPane, HRButtonsPane, displayHisPane;
	private VBox listOfUsers, displayText;
	private HBox doctorUI, topPane, aCPane, recordsPane, currentPatientPane, topLeftPane, patientSelPane, patientContact, userLabelPane;
	private Scene scene;
	private GridPane vitalPanes;
	private String patientCurrent;
	private BorderPane messagePane;
	private ScrollPane usersScroll, messageScroll;
	private Label usernameLabel, errMess1, errMess2;
	private String usernameString;
	private String loggedInText;
	
	public DoctorView(String username) {
		mainPane = new VBox();
		doctorUI = new HBox();
		
		this.usernameLabel = new Label(username);
		this.usernameString = username;
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
		topPane.setMargin(usernameLabel, new Insets(20,10,0,850));
		
		usernameLabel.setFont(new Font("Comic Sans MS", 13));
		usernameLabel.setStyle("-fx-font-weight: bold");
		topPane.setMargin(logoutButton, new Insets(15,0,0,10));
		
		topPane.getChildren().add(titleLabel);
		topPane.getChildren().add(usernameLabel);
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
		currentPatient = new Label("<No Patient Selected>");
		
		
		patientSelectLabel = new Label("Select a Patient");
		choosePatientButton = new Button("Choose Patient");
		
		patientSelectLabel.setFont(new Font("Comic Sans MS", 13));
		patientSelectLabel.setStyle("-fx-font-weight: bold");
		
		patientSelect = new ComboBox<String>();
		patInfoLabel=new Label("Patient Contact #: ");
		try {
			patientListing(patientSelect);
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
		patientSelPane.setPadding(new Insets(3,0,0,0));
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

	
	private void patientListing(ComboBox choosing)throws FileNotFoundException{
		String dir = System.getProperty("user.dir") + "\\users\\Patient\\Patients.txt";
		File patient = new File(dir);
		Scanner scnr = new Scanner(patient);
		int count = 0;
		System.out.println("something");
		while(scnr.hasNextLine()) {
			if (count%2 == 0) {
				String temp = scnr.nextLine();
				System.out.println(temp);
				choosing.getItems().add(temp);
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
		
		patientWeight = new Label("  ");
		patientTemp = new Label("  ");
		patientHeight = new Label("  ");
		patientBlood = new Label("  ");
		
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
	    patientWeight.setPadding(new Insets(0,10,0,0));
	    //112
	    patientWeight.setAlignment(Pos.BASELINE_RIGHT);
	    patientWeight.setPrefWidth(125);
	    //weight.setMargin(patientWeight, new Insets(0,0,0,120));
		weightLabel.setFont(new Font("Comic Sans MS", 12));
		weightLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox temp = new HBox();
	    temp.getChildren().addAll(tempLabel, patientTemp);
	    temp.setStyle(vitalLayout);
	    temp.setPadding(new Insets(1,1,1,10));
	    patientTemp.setPadding(new Insets(0,10,0,0));
	    //108
	    patientTemp.setAlignment(Pos.BASELINE_RIGHT);
	    patientTemp.setPrefWidth(125);
	    //temp.setMargin(patientTemp, new Insets(0,0,0,118));
		tempLabel.setFont(new Font("Comic Sans MS", 12));
		tempLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox height = new HBox();
	    height.getChildren().addAll(heightLabel, patientHeight);
	    height.setStyle(vitalLayout);
	    height.setPadding(new Insets(1,1,1,10));
	    patientHeight.setPadding(new Insets(0,10,0,0));
	    //74
	    patientHeight.setAlignment(Pos.BASELINE_RIGHT);
	    patientHeight.setPrefWidth(85);
	    //height.setMargin(patientHeight, new Insets(0,0,0,84));
		heightLabel.setFont(new Font("Comic Sans MS", 12));
		heightLabel.setStyle("-fx-font-weight: bold");
	    
	    HBox blood = new HBox();
	    blood.getChildren().addAll(bloodLabel, patientBlood);
	    blood.setStyle(vitalLayout);
	    blood.setPadding(new Insets(1,1,1,10));
	    patientBlood.setPadding(new Insets(0,10,0,0));
	    //10
	    patientBlood.setAlignment(Pos.BASELINE_RIGHT);
	    patientBlood.setPrefWidth(50);
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
		presLabel = new Label("Prescription and Patient Findings/Summaries");
		addressTF = new TextField();
		prescribeTA = new TextArea();
		dateTF = new TextField();
		summTA = new TextArea();
		errMess1 = new Label("");
		errMess2 = new Label("");
		// NOTE will take pharmacy address from user records
		addressTF.setPromptText("<Pharmacy Address Here>");
		addressTF.setEditable(false);
		datePresTF = new TextField();
		
		datePresTF.setPromptText("MM/DD/YYY");
		
		dateTF.setPromptText("<Place date in format: MM/DD/YYYY>");
		
		errMess1.setFont(new Font("Comic Sans MS", 13));
		errMess1.setStyle("-fx-font-weight: bold");
		errMess2.setFont(new Font("Comic Sans MS", 13));
		errMess2.setStyle("-fx-font-weight: bold");
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
		buttons.getChildren().addAll(datePresTF, presButton, cancelButton, errMess1);
		
		HBox buttons2= new HBox();
		sumButton = new Button("Submit Summary");
		cancel2Button = new Button("Cancel");
		buttons2.setSpacing(10);
		buttons2.getChildren().addAll(sumButton,cancel2Button, errMess2);
		
		summTA.setPromptText("Place physical findings and visit summary");
		
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
		messageSystem();
		
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
		rightPane.getChildren().addAll(topRightPane, messagePane);
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
	
	
	private void professionalListing() throws FileNotFoundException{
		String dir = System.getProperty("user.dir") + "\\users\\healthcare professionals\\Professionals.txt";
		File prof = new File(dir);
		Scanner scnr = new Scanner(prof);
		int count = 0;
		String temp = "";
		System.out.println("something");
		while(scnr.hasNextLine()) {
			if (count%2 == 0) {
				temp = scnr.nextLine();
				System.out.println(temp);
				messageSelect.getItems().add(temp);
			} else {
				String temp1 = scnr.nextLine();
				if (temp1.equals(usernameString)) {
					System.out.println(temp1);
					messageSelect.getItems().remove(temp);
				}
			}
			count +=1;
		}
		scnr.close();
	}
	
	private void messageSystem() {
		messagePane = new BorderPane();
		usersScroll = new ScrollPane();
		userLabel = new Label("<No user selected>");
		chooseTextButton = new Button("Choose");
		messageButton = new Button("Message");
		textingTA = new TextArea();
		userLabelPane = new HBox();
		texts = new TextArea();
		
		texts.setEditable(false);
		String edit =  "-fx-background-color: #9abaed;\n";
	
		userLabelPane.getChildren().addAll(userLabel);
		userLabelPane.setStyle(edit);
		
		
		HBox message = new HBox();
		message.getChildren().addAll(textingTA, messageButton);	
		
		messageSelect = new ComboBox<String>();
		//messageSelect.set
		try {
			patientListing(messageSelect);
			professionalListing();
		} catch(FileNotFoundException e) {
			System.out.println("ERROR");
		}
		//message.setSpacing(10);
		loggedInText = "";
		
		HBox newUser = new HBox();
		newUser.getChildren().addAll(messageSelect, chooseTextButton);
		newUser.setSpacing(10);
		newUser.setPadding(new Insets(5,0,0,10));
		
		listOfUsers = new VBox();
		listOfUsers.getChildren().addAll(newUser);
		//TODO add for loop for each button
		//maybe need arraylist of buttons!!!!!
		
		
		//copy paste from text file, and make it look like old chat rooms
		//example below
		//Doctor: xxxx
		//User: xxxx
		
		userLabel.setFont(new Font("Comic Sans MS", 12));
		userLabel.setStyle("-fx-font-weight: bold");
		
		displayText = new VBox();
		usersScroll = new ScrollPane(listOfUsers);
		
		usersScroll.setMinWidth(250);
		usersScroll.setMaxWidth(250);
		
		messageSelect.setMinWidth(150);
		messageSelect.setMaxWidth(150);
		
		userLabelPane.setPadding(new Insets(10,10,10,10));
		displayText.getChildren().addAll(userLabelPane, texts, message);
		messageButton.setPadding(new Insets(0,15,0,15));
		
		texts.setMaxHeight(300);
		texts.setMinHeight(300);
		textingTA.setMaxWidth(400);
		textingTA.setMinWidth(400);
		textingTA.setMaxHeight(40);
		textingTA.setMinHeight(40);
		
		messageButton.setMaxHeight(40);
		messageButton.setMinHeight(40);
		String textLayout =  "-fx-border-color: black;\n" +
                "-fx-border-width: 1;\n";
		
		
		displayText.setStyle(textLayout);
		usersScroll.setStyle(textLayout);
		messagePane.setPadding(new Insets(10,10,10,20));
		
		messagePane.setMaxHeight(400);
		messagePane.setMinHeight(400);
		messagePane.setLeft(usersScroll);
		messagePane.setCenter(displayText);
		
	}
	
	private void buttonHandler() {
		logoutButton.setOnAction(new ButtonHandler());
		healthIssButton.setOnAction(new ButtonHandler());
		medButton.setOnAction(new ButtonHandler());
		immuButton.setOnAction(new ButtonHandler());
		alleButton.setOnAction(new ButtonHandler());
		healthConcButton.setOnAction(new ButtonHandler());
		chooseTextButton.setOnAction(new ButtonHandler());
		messageButton.setOnAction(new ButtonHandler());
		presButton.setOnAction(new ButtonHandler());
		sumButton.setOnAction(new ButtonHandler());
		cancelButton.setOnAction(new ButtonHandler());
		cancel2Button.setOnAction(new ButtonHandler());
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
			} else if (source == choosePatientButton) {
				System.out.println("choose patient pressed");
				try {
					numberSetting();
					vitals();
					vitalLabel.setText("Vitals: ");
					vitalLabel.setTextFill(Color.BLACK);
					healthInfoLabel.setText("Health Info: ");
					vitalLabel.setTextFill(Color.BLACK);
					
				} catch(FileNotFoundException g) {
					vitalLabel.setText("Vitals NOT taken");
					vitalLabel.setTextFill(Color.RED);
					healthInfoLabel.setText("Health Info NOT taken");
					healthInfoLabel.setTextFill(Color.RED);
				}
			} else if (source == alleButton || source == healthConcButton) {
				if (patientSelect.getValue()==null) {
					errMess1.setText("Select a patient, and click choose patient");
					errMess1.setTextFill(Color.RED);
					errMess2.setText("Select a patient, and click choose patient");
					errMess2.setTextFill(Color.RED);
				} else {
					try {
						if (source == alleButton) {
							System.out.println("Help");
							vitalsGetter(alleButton);
						} else {
							vitalsGetter(healthConcButton);
						}
					}catch (FileNotFoundException e2) {
						System.out.println("ERROR");
					}
				}
				
			} else if (source == chooseTextButton) {
				try {
					if (messageSelect.getValue()!= null) {
						String convo = findConversation(usernameString);
						if(convo.length() >= texts.getText().length() || !convo.contains(texts.getText())) {
							texts.setText(convo);
							userLabel.setText(messageSelect.getValue());
						}
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (source == messageButton) {
				
				
				loggedInText = texts.getText() + "Doctor " + usernameString.substring(0,3) + ": " + textingTA.getText() + "\n";	
				texts.setText(loggedInText);
				if(messageSelect.getValue()!= null) {
					collectConversation(usernameString);
				}
				textingTA.setText("");
				
			} else if (source == presButton) {
				if (prescribeTA.getText().equals("") || datePresTF.getText().equals("")) {
					errMess1.setText("Empty Input");

					errMess1.setTextFill(Color.RED);
				} else if (patientSelect.getValue()==null) {
					errMess1.setText("Pick a patient!");
					errMess1.setTextFill(Color.RED);
				}
				else {
					prescribing();
					errMess1.setText("");
					datePresTF.setText("");
					prescribeTA.setText("");
				}
				
			} else if (source == sumButton) {
				if (dateTF.getText().equals("") || summTA.getText().equals("")) {
					errMess2.setText("Empty Input");
					errMess2.setTextFill(Color.RED);
				} else if (patientSelect.getValue()==null) {
					errMess2.setText("Pick a patient!");
					errMess2.setTextFill(Color.RED);
				}
				else {
					visitSumm();
					dateTF.setText("");
					summTA.setText("");
					errMess2.setText("");
					errMess2.setTextFill(Color.RED);
				}
			} else if (source == cancelButton) {
				datePresTF.setText("");
				prescribeTA.setText("");
				errMess1.setText("");
			} else if (source == cancel2Button) {
				dateTF.setText("");
				summTA.setText("");
				errMess2.setText("");
			}
			else {
				try {
					healthHistoryTA.setText(getter(source));
				}catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}//end of handle
	}
	private void prescribing() {
		//add to end of medications for each patient
		String data = datePresTF.getText() + " "+ prescribeTA.getText() + "\n";
		String patientInfo = "";
		String none = "";
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient\\"+username+".txt";
		File file = new File(dir);
		Scanner scnr;
		try {
			scnr = new Scanner(file);
			while(scnr.hasNextLine()) {
				patientInfo = patientInfo + "\n" + scnr.nextLine();
				
			}
			System.out.println(patientInfo);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		none = patientInfo.substring(patientInfo.indexOf("medication") + "medication\n".length());
		patientInfo = patientInfo.substring(1, patientInfo.indexOf("medication") + "medication\n".length()) + data + "\n";
		try {
            if (none.contains("None")) {
            	FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
            	bw.write(patientInfo);
            	bw.close();
            } else {
            	FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
            	bw.write(data);
            	bw.close();
            }
		}catch (IOException e) {
			System.out.println("File not found");
		}
		
	}
	
	private void visitSumm() {
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String data = dateTF.getText() + "\n" +summTA.getText();
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		String date = dateTF.getText().substring(0,2) + dateTF.getText().substring(3,5)+dateTF.getText().substring(8,10);
		File file = new File(dir + "\\" + username + "_"+date+".txt");
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void collectConversation(String doctor) {
		String information = messageSelect.getValue();
		String firstName = "";
		String LastName = "";
		String dob = "";
		String user = "";
		int tracker = 0;
		if (information.contains("Nurse")) {
			information = information.substring(6, information.length());
		} else if (information.contains("Doctor")) {
			information = information.substring(7, information.length());
			tracker = 1;
		}
		firstName = information.substring(0, information.indexOf(" "));
		LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		dob = information.substring(information.lastIndexOf(" ") + 1);
		user = firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		String convo = texts.getText();
		String dir = System.getProperty("user.dir") + "\\conversations";
		File location = new File(dir);
		if(!location.exists()) {
			location.mkdirs();
		}
		File file;
		if (tracker ==1) {
			if (doctor.compareTo(user) <0) {
				file = new File(dir + "\\" + doctor + "_" + user + ".txt");
			} else {
				file = new File(dir + "\\" + user + "_" + doctor + ".txt");
			}
		} else {
			file = new File(dir + "\\" + doctor + "_" + user + ".txt");
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(convo);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	private String findConversation(String doctor) throws FileNotFoundException {
		String info = "";
		String information = messageSelect.getValue();
		int tracker = 0;
		if (information.contains("Nurse")) {
			information = information.substring(6, information.length());
		} else if (information.contains("Doctor")) {
			information = information.substring(7, information.length());
			tracker = 1;
		}
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String patient =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String dir = System.getProperty("user.dir") + "\\conversations";
		File location = new File(dir);
		
		File file;
		if (tracker ==1) {
			if (doctor.compareTo(patient) <0) {
				file = new File(dir + "\\" + doctor + "_" + patient + ".txt");
			} else {
				file = new File(dir + "\\" + patient + "_" + doctor + ".txt");
			}
		} else {
			file = new File(dir + "\\" + doctor + "_" + patient + ".txt");
		}
		
		if(!location.exists()) {
			location.mkdirs();
		
		}
		if (!file.exists()) {
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write("");
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
			
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()) {
			info = info + scnr.nextLine()+ "\n" ;
		}
		
		return info;
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
		scnr.nextLine();
		addressTF.setText(scnr.nextLine());
	}
	private void vitals() throws FileNotFoundException {
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + username + "_Vitals.txt");
		Scanner scnr = new Scanner(file);
		
		patientWeight.setText(scnr.nextLine());
		patientTemp.setText(scnr.nextLine());
		patientHeight.setText(scnr.nextLine());
		patientBlood.setText(scnr.nextLine());
		
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

	private void vitalsGetter(Object source) throws FileNotFoundException{
		String information = patientSelect.getValue();
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String username =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + username + "_Vitals.txt");
		Scanner scnr = new Scanner(file);
		
		String info = "";
		while (scnr.hasNextLine()) {
			info = info + "\n"+scnr.nextLine();
		}
		
		if (source == alleButton) {
			info = info.substring(info.indexOf("Allergies:\n")+ "Allergies:\n".length(), info.indexOf("Health Concerns\n"));
			System.out.println(info);
			patTA.setText(info);
		} else if (source == healthConcButton) {
			info = info.substring(info.indexOf("Health Concerns\n") + "Health Concerns\n".length());
			patTA.setText(info);
		}
	}
}
