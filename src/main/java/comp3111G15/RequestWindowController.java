package comp3111G15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * The RequestWindowController describes the components used for the starting window
 * of the ATU system.
 * @author SzeWingKwan, LiChunTak, HE Qihao
 *
 */
public class RequestWindowController {
	
	public static String levels[] = { "Student", "TA" };

	private static List<Team> ATUResult;
	
	// all area
    @FXML
    private TextField requestTextField;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private Button executeButton1;
    // ta area
    
    @FXML
    private TextField filenameTextField;
    
    @FXML
    private Button LoadCSVButton;

    @FXML
    private HBox TA_Area;
    
    @FXML
    private Button energyViewButton;
    
    /**
     * Initialize the application with user authentication and display UI window
     */
    @FXML
    public void initialize() {
    	ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>(levels[0], levels);
    	choiceDialog.setHeaderText("Select a user level");
    	choiceDialog.setContentText("Who are you: ");
    	Optional<String> result = choiceDialog.showAndWait();

    	// Set search and line chart button to disabled
    	submitButton.setDisable(true);
    	TA_Area.setVisible(false);
    	energyViewButton.setDisable(true);
    	executeButton1.setDisable(true);
    	
    	if (!result.isPresent()) {
    		System.exit(0);
    	}
    	
    	// System.out.println(result.isPresent());
    	
    	
    	ArrayList<Boolean> temp = onDialogGetResult(choiceDialog.getSelectedItem());
    	
    	if (temp.get(0)) {
    		// selected TA
    		TextInputDialog td = new TextInputDialog();
    		
    		while (true) {
    			td.setHeaderText("Enter password");
            	Optional<String> pwDialogResult = td.showAndWait();
            	if (!pwDialogResult.isPresent()) { System.exit(0); }
    			if (Security.checkPW(td.getEditor().getText())) {
    				break;
    			}
    			Alert a = new Alert(AlertType.ERROR);
    			a.setContentText("Incorrect password");
        		a.showAndWait();
    		}

        	TA_Area.setVisible(true);
        	
    	} else {
    		// selected student
    		submitButton.setDisable(temp.get(1));
    		executeButton1.setVisible(false);
    	}
    	
    }
    
    /**
     * Get the result of the dialog which asks for user level
     * @param result from the dialog
     * @return ArrayList<Boolean>, first element: If TA return true, else false, second element: If csv read successful return true, else false
     */
    public static ArrayList<Boolean> onDialogGetResult(String result) {
    	ArrayList<Boolean> temp = new ArrayList<Boolean>();
    	if (result == levels[1]) {
    		// selected TA
    		temp.add(true);
    	} else {
    		// selected student
    		temp.add(false);
    		temp.add(!InputManager.read("StudentData.CSV"));
    		ATUEngine engine = new ATUEngine(InputManager.student_data);
        	ATUResult = new ArrayList<Team>(engine.getTeamlist());
    	}
    	return temp;
    }

    /**
     * The method used for controlling the button event for ATU Engine Execution that will produce
     * team-up results and store them for later display use.
     * @param event on execute button is pressed 
     */
    @FXML
    void onExecuteButtonPressed(ActionEvent event) {
    	ATUEngine engine = new ATUEngine(InputManager.student_data);
    	ATUResult = new ArrayList<Team>(engine.getTeamlist());
		submitButton.setDisable(false);
    }
    
    /**
     * The method used for controlling the button event for displaying the Student Key Energies
     * Zoom Out View window.
     * @param event on button is pressed 
     */
    @FXML
    private void onEnergyVewButtonPressed(ActionEvent event) {
    	createChartWindow();
    }
    
    @FXML
    private void onLoadCsvButtonPressed(ActionEvent event) {
    	String CSVfilename = filenameTextField.getText();
    	
    	// Set search and line chart button enabled when file is successfully loaded
    	if (InputManager.read(CSVfilename)) {
    		// submitButton.setDisable(false);
        	energyViewButton.setDisable(false);
        	executeButton1.setDisable(false);
        	statisticsTableSetup();
        	studentTableSetup();
    	}
    }

    /**
     * The method used for controlling the button event for displaying the window containing 
     * search result of the given student name or ID.
     * @param event on button is pressed 
     */
    @FXML
    private void onSubmitButtonPressed(ActionEvent event) {
    	// Verify user input
    	switch(verifyInput(requestTextField.getText().trim().replaceAll(",", ""))) {
    	case 1:
    		// Search for team info for that student
    		if(searchForTeam(DisplayWindowController.searching_student)) {
    			requestTextField.clear();
    			createTeamTableWindow();
    		}
    		else {
    			// When that student info is in the system but is not assigned to any team
    			Alert alert = new Alert(Alert.AlertType.ERROR);
        		alert.setTitle("Error");
        		alert.setContentText("Oops. No team has been assgined for this student. Please press button Execute ATU Engine.");
        		alert.showAndWait();
    		}
    		break;
    	case 2:
    		Alert duplicatedNameAlert = new Alert(Alert.AlertType.WARNING);
    		duplicatedNameAlert.setTitle("Duplicated Student Name");
    		duplicatedNameAlert.setContentText("More than one student have the same student name. Please use student ID instead!");
    		duplicatedNameAlert.showAndWait();
    		break;
    	case 0:
    	default:
    		// When user has entered something but invalid
    		if(requestTextField.getText().trim().length() > 0) {
        		Alert invalidInputalert = new Alert(Alert.AlertType.WARNING);
        		invalidInputalert.setTitle("Invalid Input");
        		invalidInputalert.setContentText("Incorrect student name or ID. Please try again.");
        		invalidInputalert.showAndWait();
        	}
    		break;
    	}
    }
    
    /**
     * The method used for searching the corresponding team of the given student.
     * @param student
     * @return whether the team is found successfully or not
     */
	private boolean searchForTeam(Student student) {
//		if (ATUResult.size() == 0)
//		{
//			Alert alert = new Alert(Alert.AlertType.WARNING);
//    		alert.setTitle("ATU Engine not executed yet.");
//    		alert.setContentText("Please press button Execute ATU Engine.");
//    		alert.showAndWait();
//		}
//		else {
			for (Team team : ATUResult) {
				for(Student s : team.getMemberList()) {
					if(s.equals(student)) {
						// Identify the team for that searching student
						DisplayWindowController.belonging_team = team;
						return true;
					}
				}
//			}
		}
		return false;
	}
    
	/**
	 * The method used for the verification of the search input.
	 * @param input search input
	 * @return validity
	 */
    private int verifyInput(String input) {
    	// Identify the searching student using student ID
    	for(Student student : InputManager.student_data) {
    		if(student.getStudentID().toLowerCase().equals(input.toLowerCase())) {
    			DisplayWindowController.searching_student = student;
    			return 1;
    		}
    	}
    	// Identify the searching student using student name
    	int count = 0;
    	for(Student student : InputManager.student_data) {
    		if(student.getStudentName().replaceAll(",", "").toLowerCase().equals(input.toLowerCase())) {
    			if(++count > 1) return 2;	// more than one student have the same name
    			DisplayWindowController.searching_student = student;
    		}
    	}
    	if(count == 1) return 1;	// only one student in the list with that name
    	return 0;	// no that student in the list
    }
    
    /**
     * The method used for displaying the window for the Student Key Energies
     * Zoom Out View.
     */
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
    
    /**
     * the method used for displaying the window for teaming up result table according to the search.
     */
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

    /**
     * Set up and display statistic table for input graph
     */
    private void statisticsTableSetup() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/statisticsTableUI.fxml"));
			Stage stage = new Stage();
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Table of statistics");
			stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Set up and display student table for input graph
     */
    private void studentTableSetup() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/studentTableUI.fxml"));
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
    
    /**
     * Display a error dialog for when the .csv filename does not exist.
     * @param filename 
     */
    public static void displayIncorrectFilenameDialog(String filename) {
    	Alert a = new Alert(AlertType.ERROR);
		a.setContentText(String.format("Cannot find the file with name: %s", filename));
		a.showAndWait();
    }
}

