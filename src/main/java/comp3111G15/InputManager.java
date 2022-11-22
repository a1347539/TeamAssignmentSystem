package comp3111G15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * 
 * The InputManager describes the resulting statistic data and student data
 * @author SzeWingKwan
 *
 */
public class InputManager {

	// Task 2: Define a ObservableList for statistics data
	/**
	 * List of statistics
	 */
	public final static ObservableList<Statistics> stat_data = FXCollections.observableArrayList();
	
	/**
	 * List of student information
	 */
	public final static ObservableList<Student> student_data = FXCollections.observableArrayList();
	
	/**
	 * This function get the mean, min, max of all students' K1 value
	 * @param student_data A list of Student objects
	 * @return A string array of elements mean, min, max in ascending order.
	 */
	public static String[] get_student_k1_mmm(List<Student> student_data) {
		int student0K1Energy = Integer.parseInt(student_data.get(0).getK1Energy());
		int student1K1Energy = Integer.parseInt(student_data.get(1).getK1Energy());
		float mean = student0K1Energy + student1K1Energy;
		int min = 0;
		int max = 0;

		if (student0K1Energy > student1K1Energy)
	    {
	        max = student0K1Energy;
	        min = student1K1Energy;
	    }
	    else
	    {
	    	max = student1K1Energy;
	        min = student0K1Energy;
	    }
		
		for (int i = 2; i < student_data.size(); ++i)
	    {
			int k1 = Integer.parseInt(student_data.get(i).getK1Energy());
			mean += k1;
	        if (k1 > max)    
	            max = k1;
	             
	        else if (k1 < min)    
	            min = k1;
	    }
		mean /= student_data.size();
		return new String[] { String.format("%.1f", mean), String.format("%d", min),  String.format("%d", max) };
	}
	
	/**
	 * This function get the mean, min, max of all students' K2 value
	 * @param student_data A list of Student objects
	 * @return A string array of elements mean, min, max in ascending order.
	 */
	public static String[] get_student_k2_mmm(List<Student> student_data) {
		int student0K2Energy = Integer.parseInt(student_data.get(0).getK2Energy());
		int student1K2Energy = Integer.parseInt(student_data.get(1).getK2Energy());
		float mean = student0K2Energy + student1K2Energy;
		int min = 0;
		int max = 0;

		if (student0K2Energy > student1K2Energy)
	    {
	        max = student0K2Energy;
	        min = student1K2Energy;
	    }
	    else
	    {
	    	max = student1K2Energy;
	        min = student0K2Energy;
	    }
		
		for (int i = 2; i < student_data.size(); ++i)
	    {
			int k2 = Integer.parseInt(student_data.get(i).getK2Energy());
			mean += k2;
	        if (k2 > max)    
	            max = k2;
	             
	        else if (k2 < min)    
	            min = k2;
	    }
		mean /= student_data.size();
		return new String[] { String.format("%.1f", mean), String.format("%d", min),  String.format("%d", max) };
	}
	
	/**
	 * This function get the mean, min, max of all students' K3 ticks
	 * @param student_data A list of Student objects
	 * @return A string array of elements tick1_count, tick2_count, my_preference in ascending order.
	 */
	public static String[] get_k3_ticks(List<Student> student_data) {
		int tick1_count = 0;
		int tick2_count = 0;
		int my_preference = 0;
		
		for (int i = 0; i < student_data.size(); ++i) {
			if (Integer.parseInt(student_data.get(i).getK3Tick1()) == 1) {
				++tick1_count;
			}
			if (Integer.parseInt(student_data.get(i).getK3Tick2()) == 1) {
				++tick2_count;
			}
			if (Integer.parseInt(student_data.get(i).getMyPreference()) == 1) {
				++my_preference;
			}
		}
		
		return new String[] { String.format("%d", tick1_count), String.format("%d", tick2_count),  String.format("%d", my_preference) };
	}

	/**
	 * Delimiter for student name
	 */
	public static final String delimiter = ",";

	/**
	 * Read csv file
	 * @param csvFile A String of file name in the parent directory
	 * @return true if the csv file is successfully read, false otherwise 
	 */
	// read csv file
	public static boolean read(String csvFile) {
		student_data.clear();
		File myObj = new File("csvfilename.txt");
		
		try {
		    if (csvFile.isEmpty()) {
		    	// student
		    	Alert alert = new Alert(Alert.AlertType.ERROR);
		    	alert.setTitle("Error");
	    		alert.setContentText("Data is not initialized, contact the TA for further instruction.");
		    	boolean csvFilenameFileExist = myObj.exists();
		    	if (csvFilenameFileExist) {
		    		Scanner myReader = new Scanner(myObj);
		    	    while (myReader.hasNextLine()) {
		    	    	String data = myReader.nextLine();
		    	        csvFile = data;
		    	    }
		    	    myReader.close();
		    	    File file = new File(csvFile);
					
					if (!file.exists()) {
						// do something
			    		alert.showAndWait();
						System.exit(0);
					}
		    	} else {
		    		alert.showAndWait();
		    		System.exit(0);
		    	}
		    } else {
		    	// TA
	    		FileWriter myWriter = new FileWriter(myObj);
	    		myWriter.write(csvFile);
	    		myWriter.close();
		    }
		} catch (IOException e) {

		}
		
		try {
			File file = new File(csvFile);
			
			if (file.exists()) {
				// do something
			}
			else {
				// report error
				RequestWindowController.displayIncorrectFilenameDialog(csvFile);
				return false;
			}
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = " ";
			String[] tempArr;
			br.readLine(); // skip the first line
			int index = 0;
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				// System.out.println(tempArr.length);
//				if (tempArr.length == 8) {
//					student_data.add( 
//							new Student( index++,
//									tempArr[0], tempArr[1].concat(tempArr[2]), tempArr[3],
//									tempArr[4], tempArr[5], tempArr[6], 
//									tempArr[7], "", ""
//									)
//							);
				if (tempArr.length == 9) {
					student_data.add( 
							new Student( index++,
									tempArr[0], tempArr[1].concat(tempArr[2]), tempArr[3],
									tempArr[4], tempArr[5], tempArr[6], 
									tempArr[7], tempArr[8], ""
									)
							);
				} else if (tempArr.length == 10) {
					student_data.add( 
							new Student( index++,
									tempArr[0], tempArr[1].concat(tempArr[2]), tempArr[3],
									tempArr[4], tempArr[5], tempArr[6], 
									tempArr[7], tempArr[8], tempArr[9]
									)
							);
				}
			}
			br.close();
			
			System.out.format("read complete with %d records \n", student_data.size());
			boolean student_data_is_empty = student_data.size() == 0;
			if (student_data_is_empty) {
				return false;
			} else {
				return true;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Populate the statistics stat_data, the ArrayList will contain number_of_student, K1mmm, K2mmm, K3_Tick1, K3_Tick2, My_preference, in order
	 * @param studentData list of student data
	 * @return statistics
	 */
	public static ArrayList<Statistics> getStatistics(List<Student> studentData) {
		ArrayList<Statistics> statistics = new ArrayList<Statistics>();
		
//		for (String a : ta) {
//			System.out.println(a);
//		}
		statistics.add(new Statistics(0, "Total Number of Students", Integer.toString(studentData.size())));
		String[] k1s = get_student_k1_mmm(studentData);
		String k1_mean = k1s[0];
		String k1_min = k1s[1];
		String k1_max = k1s[2];
		statistics.add(new Statistics(1, "K1_Energy(Average, Min, Max)", String.format("(%s, %s, %s)", k1_mean, k1_min, k1_max)));
		String[] k2s = get_student_k2_mmm(studentData);
		String k2_mean = k2s[0];
		String k2_min = k2s[1];
		String k2_max = k2s[2];
		statistics.add(new Statistics(2, "K2_Energy(Average, Min, Max)", String.format("(%s, %s, %s)", k2_mean, k2_min, k2_max)));
		String[] t = get_k3_ticks(studentData);
		String k3t1 = t[0];
		String k3t2 = t[1];
		String mp = t[2];
		statistics.add(new Statistics(3, "K3_Tick1 = 1", k3t1));
		statistics.add(new Statistics(4, "K3_Tick2 = 1", k3t2));
		statistics.add(new Statistics(5, "My_Preference = 1", mp));
		
		return statistics;
	}
}
