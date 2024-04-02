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


public class NurseView {
	private Scene nurseScene;
	private VBox mainPane,rightPane, leftPane;
	private HBox nurseUI, topPane;
	private Label patientSelectLabel, vitalLabel, recordDirecLabel, HRTypeLabel, presLabel, titleLabel, healthInfoLabel;
	private Label weightLabel, tempLabel, heightLabel, bloodLabel;
	TextField patientName, patientWeight, patientHeight, patientTemp, patientBlood;
	private Text patientContactInfoText;
	private Button healthIssButton, medButton, immuButton, alleButton, healthConcButton, presButton, cancelButton, cancel2Button, logoutButton, sumButton;
	private ComboBox<String> patientSelect; //maybe make it a text field instead
	private TextArea prescribeTA, summTA, patTA, healthHistoryTA;
	private TextField  addressTF, dateTF;
	private GridPane vitalPanes;
	public NurseView() {
		mainPane = new VBox();
		nurseUI = new HBox();
		
		top();
		mainPane.getChildren().addAll(topPane, nurseUI);
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
		vitalPaneFormatting();
		
		leftPane.getChildren().addAll(vitalPanes);
		
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
		
		vitalPanes.setAlignment(Pos.CENTER_RIGHT); 
		
		vitalPanes = new GridPane();
		vitalPanes.setPadding(new Insets(-5,0,0,20));
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
	
	public Scene getScene() {
		return nurseScene;
	}
}
