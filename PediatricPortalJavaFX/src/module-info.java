module PediatricPortalJavaFX {
	requires javafx.controls;
	exports yourPediatricsPortal;
	opens application to javafx.graphics, javafx.fxml;
	
}
