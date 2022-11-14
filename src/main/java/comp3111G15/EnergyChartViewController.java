package comp3111G15;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class EnergyChartViewController implements Initializable {

    @FXML
    private LineChart<Integer, Integer> energyLineChart;
    
    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		xAxis.setLabel("Number of Students = " + InputManager.student_data.size());
		yAxis.setLabel("Energy %");
		
		XYChart.Series<Integer, Integer> K1_series = new XYChart.Series<Integer, Integer>();
		K1_series.setName("K1_energy");
		XYChart.Series<Integer, Integer> K2_series = new XYChart.Series<Integer, Integer>();
		K2_series.setName("K2_energy");
		
		// Create a new student list in descending K1 order
		List<Student> descendingStudentList = new ArrayList<Student>();
		descendingStudentList.addAll(InputManager.student_data);
		Collections.sort(descendingStudentList);
		
		initK1SeriesData(K1_series, descendingStudentList);
		initK2SeriesData(K2_series, descendingStudentList);
		
		energyLineChart.getData().add(K1_series);
		energyLineChart.getData().add(K2_series);
	}
	
	private void initK1SeriesData(XYChart.Series<Integer, Integer> series, List<Student> students) {
		for(int i = 0; i < students.size(); i++)
			series.getData().add(new XYChart.Data<Integer, Integer>(i+1, Integer.parseInt(students.get(i).getK1Energy())));
	}

	private void initK2SeriesData(XYChart.Series<Integer, Integer> series, List<Student> students) {
		for(int i = 0; i < students.size(); i++)
			series.getData().add(new XYChart.Data<Integer, Integer>(i+1, Integer.parseInt(students.get(i).getK2Energy())));
	}
}
