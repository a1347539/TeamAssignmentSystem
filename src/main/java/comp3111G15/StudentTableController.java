package comp3111G15;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTableController {
	
    @FXML
    private TableColumn rowIndexColumn;

    @FXML
    private TableColumn studentIDColumn;

    @FXML
    private TableColumn studentNameColumn;
    
    @FXML
    private TableColumn studentEmailColumn;

    @FXML
    private TableColumn K1EnergyColumn;

    @FXML
    private TableColumn K2EnergyColumn;

    @FXML
    private TableColumn K3Tick1Column;

    @FXML
    private TableColumn K3Tick2Column;
    
    @FXML
    private TableColumn myPreferenceColumn;

    @FXML
    private TableColumn conernsColumn;

    @FXML
    private TableView<Student> student_table;

    @FXML
    protected void initialize() {
    	rowIndexColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("index"));
    	studentIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
    	studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
    	studentEmailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentEmail"));
    	K1EnergyColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("k1Energy"));
    	K2EnergyColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("k2Energy"));
    	K3Tick1Column.setCellValueFactory(new PropertyValueFactory<Student, String>("k3Tick1"));
    	K3Tick2Column.setCellValueFactory(new PropertyValueFactory<Student, String>("k3Tick2"));
    	myPreferenceColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("myPreference"));
    	conernsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("concerns"));
    	
    	
    	
    	student_table.setItems(InputManager.student_data);
    }
}