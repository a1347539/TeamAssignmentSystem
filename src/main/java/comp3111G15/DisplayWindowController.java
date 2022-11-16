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

/**
 * 
 * The DisplayWindowController describes the components used for the teaming up 
 * result table.
 * @author SzeWingKwan
 *
 */
public class DisplayWindowController implements Initializable {
	
	/**
	 * The student being searched
	 */
	static Student searching_student;
	
	/**
	 * The team that the student is belongs to
	 */
	static Team belonging_team;

	@FXML
    private Label searching_name;
    
    @FXML
    private Label searching_id;
    
    @FXML
    private Label my_K1_energy;

    @FXML
    private Label my_K2_energy;

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
    
    /**
     * The TableDisplay specifies the format of each row of the output table
     * @author SzeWingKwan
     */
    class TableDisplay{
    	private SimpleStringProperty column1;
    	private SimpleStringProperty column2;
    	private SimpleStringProperty column3;
    	
    	/**
    	 * Class constructor
    	 * @param col1 column 1
    	 * @param col2 column 2
    	 * @param col3 column 3
    	 */
    	TableDisplay(String col1, String col2, String col3){
    		this.column1 = new SimpleStringProperty(col1);
    		this.column2 = new SimpleStringProperty(col2);
    		this.column3 = new SimpleStringProperty(col3);
    	}

    	/**
    	 * Gets the content in column 1
    	 * @return column1
    	 */
		public SimpleStringProperty getColumn1() {
			return column1;
		}

		/**
		 * Sets the content in column 1
		 * @param column1
		 */
		public void setColumn1(SimpleStringProperty column1) {
			this.column1 = column1;
		}

		/**
    	 * Gets the content in column 2
    	 * @return column2
    	 */
		public SimpleStringProperty getColumn2() {
			return column2;
		}

		/**
		 * Sets the content in column 2
		 * @param column2
		 */
		public void setColumn2(SimpleStringProperty column2) {
			this.column2 = column2;
		}

		/**
    	 * Gets the content in column 3
    	 * @return column3
    	 */
		public SimpleStringProperty getColumn3() {
			return column3;
		}

		/**
		 * Sets the content in column 3
		 * @param column3
		 */
		public void setColumn3(SimpleStringProperty column3) {
			this.column3 = column3;
		}
    	
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize student info
		searching_name.setText(searching_student.getStudentName());
		searching_id.setText(searching_student.getStudentID());
		// Display key energies
		my_K1_energy.setText(searching_student.getK1Energy());
		my_K2_energy.setText(searching_student.getK2Energy());
		
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
	
	/**
	 * The method used for initializing the output table. 
	 * Each team mate is a child of the major item, and information about that team mate
	 * is contained as his sub-items.
	 * @param root the major item in the output table
	 */
	private void initTableData(TreeItem<TableDisplay> root) {
		for(Student student : belonging_team.getMemberList()) {
			// Only show teammate but not the self
			if(student.equals(searching_student)) continue;
			
			// Initialize teammate
			TreeItem<TableDisplay> temp_student;
			if(student.equals(belonging_team.getLeader()))
				temp_student = new TreeItem<>(new TableDisplay(student.getStudentName(), "", "Yes"));
			else
				temp_student = new TreeItem<>(new TableDisplay(student.getStudentName(), "", "No"));
			
			// Add info to teammate
			TreeItem<TableDisplay> temp_id = new TreeItem<>(new TableDisplay("Student ID:", student.getStudentID(), ""));
			TreeItem<TableDisplay> temp_email = new TreeItem<>(new TableDisplay("Email:", student.getStudentEmail(), ""));
			TreeItem<TableDisplay> temp_K1 = new TreeItem<>(new TableDisplay("K1 Energy:", student.getK1Energy(), ""));
			TreeItem<TableDisplay> temp_K2 = new TreeItem<>(new TableDisplay("K2 Energy:", student.getK2Energy(), ""));
			TreeItem<TableDisplay> temp_K3_1 = new TreeItem<>(new TableDisplay("K3 Tick 1:", convertBooleanString(student.getK3Tick1()), ""));
			TreeItem<TableDisplay> temp_K3_2 = new TreeItem<>(new TableDisplay("K3 Tick 2:", convertBooleanString(student.getK3Tick2()), ""));
			TreeItem<TableDisplay> temp_pref = new TreeItem<>(new TableDisplay("Preference:", convertBooleanString(student.getMyPreference()), ""));
			TreeItem<TableDisplay> temp_concern = new TreeItem<>(new TableDisplay("Concerns:", student.getConcerns(), ""));
			
			temp_student.getChildren().addAll(temp_id, temp_email, temp_K1, temp_K2, temp_K3_1, temp_K3_2, temp_pref, temp_concern);
			temp_student.setExpanded(true);
			
			root.getChildren().add(temp_student);
		}
	}
	
	/**
	 * The method converts boolean expressions to Yes or No
	 * @param val boolean string
	 * @return Yes/No
	 */
	private String convertBooleanString(String val) {
		if(val.equals("0") || val.equals("false"))
			return "No";
		else if(val.equals("1") || val.equals("true"))
			return "Yes";
		else return val;
	}

}
