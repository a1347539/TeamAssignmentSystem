package comp3111G15;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisplayWindowController implements Initializable {
	
	static Student searching_student;
	static Team belonging_team;

	@FXML
    private Label searching_name;
    
    @FXML
    private Label searching_id;

    @FXML
    private Label team_number;

    @FXML
    private TreeTableView<?> teammates_table;
    
    @FXML
    private TreeTableColumn<?, ?> teammates_column;
    
    @FXML
    private TreeTableColumn<?, ?> leader_column;
    
    @FXML
    private TableView<Team> energy_table;
	
	 @FXML
    private TableColumn<Team, Double> energy_title_column;
	
    @FXML
    private TableColumn<Team, Double> energy_content_column;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize student info
		searching_name.setText(searching_student.getName());
		searching_id.setText(searching_student.getID());
		
		// Initialize team number
		team_number.setText(Integer.toString(belonging_team.getID()));
		
		// Set up energy table
//		energy_title_column.setCellValueFactory(new PropertyValueFactory<Team, Double>("K1_average"));
//		energy_content_column.setCellValueFactory(new PropertyValueFactory<Team, Double>("K2_average"));
		
//		energy_table.setItems(getTeam());;
	}
	
	private ObservableList<Team> getTeam() {
		return FXCollections.observableArrayList(belonging_team);
	}

}
