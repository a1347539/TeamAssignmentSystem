package comp3111G15;

import comp3111G15.Library_sample._Statistics;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatisticsTableController {

    @FXML
    private TableView<Statistics> stat_table;
    
    @FXML
    private TableColumn row_index_column;
    
    @FXML
    private TableColumn entry_column;
    
    @FXML
    private TableColumn value_column;
    

    @FXML
    protected void initialize() {
    	stat_table.setEditable(true);
    	row_index_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("index"));
    	entry_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("entry"));
    	value_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("value"));
    	stat_table.setItems(InputManager.stat_data);
    	stat_table.getColumns().addAll(row_index_column, entry_column, value_column);
    }
}
