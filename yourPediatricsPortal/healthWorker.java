package yourPediatricsPortal;
//main file that shows the stage and scene

import javafx.application.Application;
import yourPediatricsPortal.user;

public class healthWorker extends user {
	private String ID;
	
	public healthWorker(String username, String password, String dateOfBirth, String ID) {
		super(username,password, dateOfBirth);
		this.ID = ID;
	}
	
	public void set_id (String id) {
		ID = id;
	}
	
	public String get_id() {
		return ID;
	}
}