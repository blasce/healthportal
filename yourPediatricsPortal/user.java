package yourPediatricsPortal;
//main file that shows the stage and scene

import javafx.application.Application;

public class user {
	private String username;
	private String password;
	private String dateOfBirth;

	public user(String username_input, String password_input, String dateOfBirthInput) {
		username = username_input;
		password = password_input;
		dateOfBirth = dateOfBirthInput;
		
		System.out.println(username + " is created.");
		
	}
	
	public String get_username() {
		return username;
	}
	
	public String get_password() {
		return password;
	}
	
	public String get_date_of_birth() {
		return dateOfBirth;
	}
	
	public void change_date_of_birth(String newDate) {
		dateOfBirth = newDate;
	}
	
	public void change_username(String input) {
		username = input;
	}
	
	public void change_password(String input) {
		password = input;
	}
}

