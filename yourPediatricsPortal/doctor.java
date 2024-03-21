package yourPediatricsPortal;
//main file that shows the stage and scene

import javafx.application.Application;
import yourPediatricsPortal.healthWorker;

public class doctor extends healthWorker {
	private String ID;
	
	public doctor(String username, String password, String dateOfBirth, String ID) {
		super(username,password, dateOfBirth, ID);
	}
	
	public void set_id (String id) {
		ID = id;
	}
	
	public String get_id() {
		return ID;
	}
}