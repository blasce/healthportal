package yourPediatricsPortal;
//main file that shows the stage and scene

import javafx.application.Application;
import yourPediatricsPortal.user;

public class patient extends user {
	private String patientID;
	
	public patient(String username, String password, String dateOfBirth) {
		super(username,password, dateOfBirth);
		
	}
	
	public void set_patient_id (String id) {
		patientID = id;
	}
	
	public String get_id() {
		return patientID;
	}
}