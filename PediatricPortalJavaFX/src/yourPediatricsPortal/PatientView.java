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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import yourPediatricsPortal.DoctorView.ButtonHandler;

public class PatientView {

	private Label patientSelectLabel, vitalLabel, recordDirecLabel, HRTypeLabel, presLabel, titleLabel, healthInfoLabel;
	private Label weightLabel, tempLabel, heightLabel, bloodLabel, patientWeight, patientTemp, patientHeight, patientBlood, userLabel, visitSumLabel;
	
	private Label errMess1;

	private Button logoutButton, changeContactButton;
	private VBox mainPane, rightPane, leftPane, displayText;
	private TextArea texts, textingTA;
	private Button chooseTextButton, messageButton;
	private HBox patientUI, topPane,  userLabelPane;
	private Scene scene;
	private BorderPane messagePane;
	private String loggedInText, usernameString;
	private ComboBox<String> messageSelect;
	private String numberString;
	private TextArea visitDetailsTextArea;

	private TextField patientContactTextField;
	
	public PatientView(String username)  {
		mainPane = new VBox();
		patientUI = new HBox();
		
		this.usernameString = username;
		
		top();
		leftSide();
		rightSide();
		buttonHandler();
		try {
			visitGetter();
		}catch(FileNotFoundException e) {
			System.out.println("ERROR!");
		}
		patientUI.getChildren().addAll(leftPane, rightPane);
		mainPane.getChildren().addAll(topPane, patientUI);
		
		scene = new Scene(mainPane, 1280, 700);
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
		// Left side with visit summaries and pres

		visitSumLabel = new Label("Visit Details");
		visitSumLabel.setFont(new Font("Comic Sans MS", 12));
		visitSumLabel.setStyle("-fx-font-weight: bold");
		
        visitDetailsTextArea = new TextArea();
        visitDetailsTextArea.setEditable(false); // Patient should only view the details
        visitDetailsTextArea.setMaxHeight(500);
        visitDetailsTextArea.setMinHeight(500);
        visitDetailsTextArea.setMaxWidth(500);
        visitDetailsTextArea.setMinWidth(500);
        
        leftPane.getChildren().addAll(visitSumLabel,  visitDetailsTextArea);
        leftPane.setSpacing(5);
        leftPane.setPadding(new Insets(10));
	}
	private void rightSide()  {
		rightPane = new VBox();
		// Right side with contact information and messaging system
        Label patientContactLabel = new Label("My Contact Number:");
        patientContactTextField = new TextField(); // To be populated with patient's contact information
        changeContactButton = new Button("Change Contact");
        patientContactLabel.setFont(new Font("Comic Sans MS", 12));
        patientContactLabel.setStyle("-fx-font-weight: bold");
        errMess1 = new Label("");
        
        try {
        	 numberString = numberGetter();
        } catch(FileNotFoundException e) {
        	System.out.println("ERROR");
        }
       
        patientContactTextField.setPromptText(numberString);
      
        HBox number = new HBox();
        number.getChildren().addAll(patientContactTextField, changeContactButton, errMess1);
        number.setSpacing(20);
        
        Label providerContactLabel = new Label("Provider's Contact Information:");
        TextField providerContactTextField = new TextField(); // To be populated with provider's contact information
        providerContactTextField.setEditable(false);
        providerContactTextField.setText("9493310205");
        
        // Messaging system
        // Populate the list with message threads

        messageSystem();
       
        rightPane.getChildren().addAll(patientContactLabel, number, providerContactLabel, providerContactTextField,
                messagePane);
        rightPane.setSpacing(5);
        rightPane.setPadding(new Insets(10));
	
	}
	
	private String numberGetter() throws FileNotFoundException {
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + usernameString + ".txt");
		
		Scanner scnr = new Scanner(file);
		int count = 0;
		while (count <3) {
			scnr.nextLine();
			count ++;
		}
		String something = scnr.nextLine();
		scnr.close();
		return something;
		
	} 
	private void numberChanger(String number) {
		String data = "";
		String dir = System.getProperty("user.dir") + "\\users\\Patient\\"+usernameString+".txt";
		File file = new File(dir);
		Scanner scnr;
		
		int count = 0;
		String lineholder = "";
		
		try {
			scnr = new Scanner(file);
			data = scnr.nextLine();
			while(scnr.hasNextLine()) {
				lineholder = scnr.nextLine();
				if (count == 2) {
					data = data+ "\n" + number;
				} else {
					data = data + "\n" + lineholder;
				}
				count++;
			}
			scnr.close();
			System.out.println(data);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
        	bw.write(data);
        	bw.close();
		}catch (IOException e) {
			System.out.println("File not found");
		}
		
		
	}
	
	
	
	private void messageSystem() {
		messagePane = new BorderPane();
		ScrollPane usersScroll= new ScrollPane();
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
		VBox listOfUsers = new VBox();
		listOfUsers.getChildren().addAll(newUser);
	
		//copy paste from text file, and make it look like old chat rooms
		//example below
		//Doctor: xxxx
		//User: xxxx
		
		userLabel.setFont(new Font("Comic Sans MS", 12));
		userLabel.setStyle("-fx-font-weight: bold");
		
		displayText = new VBox();
		usersScroll = new ScrollPane(listOfUsers);
		
		
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
	public Scene getScene() {
		return scene;
	}
	private void buttonHandler() {
		logoutButton.setOnAction(new ButtonHandler());
		
		chooseTextButton.setOnAction(new ButtonHandler());
		messageButton.setOnAction(new ButtonHandler());
		changeContactButton.setOnAction(new ButtonHandler());
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
			}else if (source == chooseTextButton) {
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
				
				loggedInText = texts.getText() + "Patient: "  + textingTA.getText() + "\n";	
				texts.setText(loggedInText);
				if(messageSelect.getValue()!= null) {
					collectConversation(usernameString);
				}
				textingTA.setText("");
				
			} else if (source == changeContactButton) {
				if (patientContactTextField.getText().equals("")) {
					errMess1.setText("Error");
					errMess1.setTextFill(Color.RED);
				} else if (patientContactTextField.getText().length()!=10) {
					errMess1.setText("Error");
					errMess1.setTextFill(Color.RED);
				} else {
					numberChanger(patientContactTextField.getText());
					numberString = patientContactTextField.getText();
					patientContactTextField.setText("");
					patientContactTextField.setPromptText(numberString);
					errMess1.setText("");
				}
				
			}
		}
	}
	
	
	private void collectConversation(String nurse) {
		String information = messageSelect.getValue();
		int tracker = 0;
		if (information.contains("Nurse")) {
			information = information.substring(6, information.length());
			tracker = 1;
		} else if (information.contains("Doctor")) {
			information = information.substring(7, information.length());
			tracker = 2;
		}
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String patient = firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		String convo = texts.getText();
		String dir = System.getProperty("user.dir") + "\\conversations";
		File location = new File(dir);
		if(!location.exists()) {
			location.mkdirs();
		}
		File file;
		if (tracker == 1) {
			if (nurse.compareTo(patient)<0) {
				file = new File(dir + "\\" + nurse + "_" + patient + ".txt");
			} else {
				file = new File(dir + "\\" + patient + "_" + nurse + ".txt");
			}
			
		} else if (tracker == 2) {
			file = new File(dir + "\\" + patient + "_" + nurse + ".txt");
		} else {
			file = new File(dir + "\\" + nurse + "_" + patient + ".txt");
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
	private String findConversation(String nurse) throws FileNotFoundException {
		String info = "";
		String information = messageSelect.getValue();
		int tracker = 0;
		if (information.contains("Nurse")) {
			information = information.substring(6, information.length());
			tracker = 1;
		} else if (information.contains("Doctor")) {
			information = information.substring(7, information.length());
			tracker = 2;
		}
		String firstName = information.substring(0, information.indexOf(" "));
		String LastName = information.substring(information.indexOf(" ") + 1, information.lastIndexOf(" "));
		String dob = information.substring(information.lastIndexOf(" ") + 1);
		String patient =  firstName.substring(0,1) + LastName+dob.substring(0,2) + dob.substring(3,5)+dob.substring(8,10);
		
		String dir = System.getProperty("user.dir") + "\\conversations";
		File location = new File(dir);
		File file;
		if (tracker == 1) {
			if (nurse.compareTo(patient)<0) {
				file = new File(dir + "\\" + nurse + "_" + patient + ".txt");
			} else {
				file = new File(dir + "\\" + patient + "_" + nurse + ".txt");
			}
		} else if (tracker == 2) {
			file = new File(dir + "\\" + patient + "_" + nurse + ".txt");
		} else {
			file = new File(dir + "\\" + nurse + "_" + patient + ".txt");
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
	private void visitGetter() throws FileNotFoundException{
		
		String dir = System.getProperty("user.dir") + "\\users\\Patient";
		File file = new File(dir + "\\" + usernameString + "_"+"Findings_Summary"+".txt");
		Scanner scnr = new Scanner(file);
		
		String info = "";
		while (scnr.hasNextLine()) {
			info = info + "\n"+scnr.nextLine();
		}
		visitDetailsTextArea.setText(info);
		scnr.close();
	}
	
}


		
	
	