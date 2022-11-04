package comp3111G15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import comp3111G15.Library_sample._Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InputManager {

	public final static ObservableList<Statistics> stat_data = FXCollections.observableArrayList();
	
	public final static ObservableList<Student> student_data = FXCollections.observableArrayList();
	// Task 2: Define a ObservableList for statistics data

	public static final String delimiter = ",";

	// read csv file
	public static boolean read(String csvFile) {
		student_data.clear();
		
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

	/*
	 * new Statistics("Total Number of Students", "100"), new
	 * Statistics("K1_Energy(Average, Min, Max)", "(59.8, 10, 80)"), new
	 * Statistics("K2_Energy(Average, Min, Max)", "(62.3, 40, 85)"), new
	 * Statistics("K3_Tick1 = 1", "12"), new Statistics("K3_Tick2 = 1", "3"), new
	 * Statistics("My_Preference = 1", "19"));
	 */

	
	public static void getStatistics() {
		stat_data.add(new Statistics("Total Number of Students", Integer.toString(student_data.size())));
		Collections.max(student_data);
		Collections.min(student_data);
		stat_data.add(new Statistics("K1_Energy(Average, Min, Max)", "1"));
	}
}
