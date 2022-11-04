package comp3111G15;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RequestWindowController {

	private int user_level;
	
	// all area
    @FXML
    private TextField requestTextField;
    
    @FXML
    private Button submitButton;
    
    // ta area
    
    @FXML
    private TextField filenameTextField;
    
    @FXML
    private Button LoadCSVButton;

    @FXML
    private HBox TA_Area;
    
    @FXML
    private Button energyViewButton;
    
    @FXML
    public void initialize() {
    	TextInputDialog td = new TextInputDialog();
    	td.setHeaderText("Enter TA pw, empty for students");
    	td.showAndWait();
    	user_level = Security.checkPW(td.getEditor().getText());
    	if (user_level == 0) {
    		TA_Area.setVisible(false);
    		// pretend the student data is pre-loaded
        	InputManager.read("StudentData.CSV");
    	} else {
    		System.out.println("you are a TA");
    		TA_Area.setVisible(true);
    	}
    }
    
    @FXML
    private void onEnergyVewButtonPressed(ActionEvent event) {
    	createChartWindow();
    }
    
    @FXML
    private void onLoadCsvButtonPressed(ActionEvent event) {
    	String CSVfilename = filenameTextField.getText();
    	if (InputManager.read(CSVfilename)) {
    		
    		statisticsTableSetup();
    	}
    }

    @FXML
    private void onSubmitButtonPressed(ActionEvent event) {
    	if(verifyInput(requestTextField.getText().trim())) {
    		// TODO - search for team info
    		createTeamTableWindow();
    	}
    	else if(requestTextField.getText().trim().length() > 0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Invalid Input");
    		alert.setContentText("Incorrect student name or ID. Please try again.");
    		alert.showAndWait();
    	}
    }
    
    private boolean verifyInput(String input) {
    	for(Student student : InputManager.student_data) {
    		if(student.getName().replaceAll(",", "").toLowerCase().equals(input.toLowerCase())
    				|| student.getID().toLowerCase().equals(input.toLowerCase())) {
    			DisplayWindowController.searching_student = student;
    			return true;
    		}
    			
    	}
    	return false;
    }
    
    private void createChartWindow() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/lineChartUI.fxml"));
			Stage stage = new Stage();
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Student Key Energies Zoom Out View");
			stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void createTeamTableWindow() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/displayUI.fxml"));
			Stage stage = new Stage();
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Search Result");
			stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void statisticsTableSetup() {
    	InputManager.getStatistics();
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/statisticsTableUI.fxml"));
			Stage stage = new Stage();
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Table of students' personal data");
			stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}

