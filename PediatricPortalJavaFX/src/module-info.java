module PediatricPortalJavaFX {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports yourPediatricsPortal to javafx.graphics, javafx.fxml ;
}
