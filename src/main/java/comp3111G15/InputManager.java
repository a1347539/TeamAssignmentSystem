package comp3111G15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3111G15.Library_sample.Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InputManager {

	private final static ObservableList<Statistic> stat_data = FXCollections.observableArrayList();
	
	public final static ObservableList<Student> student_data = FXCollections.observableArrayList();
	// Task 2: Define a ObservableList for statistics data

	public static final String delimiter = ",";

	// read csv file
	public static boolean read(String csvFile) {

		System.out.print("\n");
		try {
			File file = new File(csvFile);
			
			if (file.exists()) {
				// do something
			}
			else {
				// report error
				return false;
			}
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = " ";
			String[] tempArr;
			br.readLine(); // skip the first line
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				
				student_data.add( 
						new Student( 
								tempArr[0], tempArr[1].concat(tempArr[2]), tempArr[3],
								tempArr[4], tempArr[5], tempArr[6], 
								tempArr[7], tempArr[8], tempArr[9]
								)
						);
				 
			}
			br.close();
			
			System.out.format("read complete with %d records \n", student_data.size());
			
			if (student_data.size() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return false;
	}

}
