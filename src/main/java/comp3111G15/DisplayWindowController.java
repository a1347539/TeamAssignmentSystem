package comp3111G15;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

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
    private Label K1_average;

    @FXML
    private Label K2_average;

    @FXML
    private TreeTableView<TableDisplay> teammates_table;
    
    @FXML
    private TreeTableColumn<TableDisplay, String> title_column;
    
    @FXML
    private TreeTableColumn<TableDisplay, String> content_column;
    
    @FXML
    private TreeTableColumn<TableDisplay, String> leader_column;
    
    class TableDisplay{
    	private SimpleStringProperty column1;
    	private SimpleStringProperty column2;
    	private SimpleStringProperty column3;
    	
    	TableDisplay(String col1, String col2, String col3){
    		this.column1 = new SimpleStringProperty(col1);
    		this.column2 = new SimpleStringProperty(col2);
    		this.column3 = new SimpleStringProperty(col3);
    	}

		public SimpleStringProperty getColumn1() {
			return column1;
		}

		public void setColumn1(SimpleStringProperty column1) {
			this.column1 = column1;
		}

		public SimpleStringProperty getColumn2() {
			return column2;
		}

		public void setColumn2(SimpleStringProperty column2) {
			this.column2 = column2;
		}

		public SimpleStringProperty getColumn3() {
			return column3;
		}

		public void setColumn3(SimpleStringProperty column3) {
			this.column3 = column3;
		}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize student info
		searching_name.setText(searching_student.getStudentName());
		searching_id.setText(searching_student.getStudentID());
		
		// Initialize team number and average energy
		team_number.setText(Integer.toString(belonging_team.getID()));
		K1_average.setText(String.format("%.1f", belonging_team.getK1Average()));
		K2_average.setText(String.format("%.1f", belonging_team.getK2Average()));
		
		// Set up root item for tree table and get student data from team
		TreeItem<TableDisplay> root = new TreeItem<>(new TableDisplay("My Teammates", "", ""));
		initTableData(root);
		
		// Set up column property for each column
		title_column.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableDisplay, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TableDisplay, String> param) {
				return param.getValue().getValue().getColumn1();
			}
			
		});
		
		content_column.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableDisplay, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TableDisplay, String> param) {
				return param.getValue().getValue().getColumn2();
			}
			
		});
		
		leader_column.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableDisplay, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TableDisplay, String> param) {
				return param.getValue().getValue().getColumn3();
			}
			
		});
		
		// Initialize tree table with the root item
		teammates_table.setRoot(root);
		teammates_table.setShowRoot(false);
	}
	
	private void initTableData(TreeItem<TableDisplay> root) {
		for(Student student : belonging_team.getMemberList()) {
			// Only show teammate but not the self
			if(student.equals(searching_student)) continue;
			
			// Initialize teammate
			TreeItem<TableDisplay> temp_student;
			if(student.equals(belonging_team.getLeader()))
				temp_student = new TreeItem<>(new TableDisplay(student.getStudentName(), "", "yes"));
			else
				temp_student = new TreeItem<>(new TableDisplay(student.getStudentName(), "", ""));
			
			// Add info to teammate
			TreeItem<TableDisplay> temp_id = new TreeItem<>(new TableDisplay("Student ID:", student.getStudentID(), ""));
			TreeItem<TableDisplay> temp_email = new TreeItem<>(new TableDisplay("Email:", student.getStudentEmail(), ""));
			TreeItem<TableDisplay> temp_K1 = new TreeItem<>(new TableDisplay("K1 Energy:", student.getK1Energy(), ""));
			TreeItem<TableDisplay> temp_K2 = new TreeItem<>(new TableDisplay("K2 Energy:", student.getK2Energy(), ""));
			TreeItem<TableDisplay> temp_K3_1 = new TreeItem<>(new TableDisplay("K3 Tick 1:", student.getK3Tick1(), ""));
			TreeItem<TableDisplay> temp_K3_2 = new TreeItem<>(new TableDisplay("K3 Tick 2:", student.getK3Tick2(), ""));
			TreeItem<TableDisplay> temp_pref = new TreeItem<>(new TableDisplay("Preference:", student.getMyPreference(), ""));
			TreeItem<TableDisplay> temp_concern = new TreeItem<>(new TableDisplay("Concerns:", student.getConcerns(), ""));
			
			temp_student.getChildren().addAll(temp_id, temp_email, temp_K1, temp_K2, temp_K3_1, temp_K3_2, temp_pref, temp_concern);
			temp_student.setExpanded(true);
			
			root.getChildren().add(temp_student);
		}
		
	}

}
