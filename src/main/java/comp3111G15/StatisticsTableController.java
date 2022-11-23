package comp3111G15;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The StatisticTableController controls the window for displaying statistics table
 * @author SzeWingKwan
 *
 */
public class StatisticsTableController {

    @FXML
    private TableView<Statistics> stat_table;
    
    @FXML
    private TableColumn row_index_column;
    
    @FXML
    private TableColumn entry_column;
    
    @FXML
    private TableColumn value_column;
    

    /**
	 * Initialize table UI with statistics
	 */
    @FXML
    protected void initialize() {
    	ArrayList<Statistics> stats = InputManager.getStatistics(InputManager.student_data);
    	InputManager.stat_data.clear();
    	InputManager.stat_data.addAll(stats);
    	row_index_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("index"));
    	entry_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("entry"));
    	value_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("value"));
    	stat_table.setItems(InputManager.stat_data);
    }
}
