package comp3111G15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3111G15.Library_sample._Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InputManager {

	// Task 2: Define a ObservableList for statistics data
	public final static ObservableList<Statistics> stat_data = FXCollections.observableArrayList();
	
	public final static ObservableList<Student> student_data = FXCollections.observableArrayList();
	
	public static String get_student_k1_mmm(List<Student> student_data) {
		float mean = student_data.get(0).getK1Energy() + student_data.get(1).getK1Energy();
		int max = 0;
		int min = 0;

		if (student_data.get(0).getK1Energy() > student_data.get(1).getK1Energy())
	    {
	        max = student_data.get(0).getK1Energy();
	        min = student_data.get(1).getK1Energy();
	    }
	    else
	    {
	    	max = student_data.get(1).getK1Energy();
	        min = student_data.get(0).getK1Energy();
	    }
		
		for (int i = 2; i < student_data.size(); ++i)
	    {
			int k1 = student_data.get(i).getK1Energy();
			mean += k1;
	        if (k1 > max)    
	            max = k1;
	             
	        else if (k1 < min)    
	            min = k1;
	    }
		mean /= student_data.size();
		
	    return String.format("(%.1f, %d, %d)", mean, min, max);
	}
	
	public static String get_student_k2_mmm(List<Student> student_data) {
		float mean = student_data.get(0).getK2Energy() + student_data.get(1).getK2Energy();
		int max = 0;
		int min = 0;

		if (student_data.get(0).getK2Energy() > student_data.get(1).getK2Energy())
	    {
	        max = student_data.get(0).getK2Energy();
	        min = student_data.get(1).getK2Energy();
	    }
	    else
	    {
	    	max = student_data.get(1).getK2Energy();
	        min = student_data.get(0).getK2Energy();
	    }
		
		for (int i = 2; i < student_data.size(); ++i)
	    {
			int k2 = student_data.get(i).getK2Energy();
			mean += k2;
	        if (k2 > max)    
	            max = k2;
	             
	        else if (k2 < min)    
	            min = k2;
	    }
		mean /= student_data.size();
		
	    return String.format("(%.1f, %d, %d)", mean, min, max);
	}
	
	public static String[] get_k3_ticks(List<Student> student_data) {
		int tick1_count = 0;
		int tick2_count = 0;
		int my_preference = 0;
		
		for (int i = 0; i < student_data.size(); ++i) {
			if (student_data.get(i).getK3Tick1() == true) {
				++tick1_count;
			}
			if (student_data.get(i).getK3Tick2() == true) {
				++tick2_count;
			}
			if (student_data.get(i).getPreference() == true) {
				++my_preference;
			}
		}
		
		return new String[] { String.format("%d", tick1_count), String.format("%d", tick2_count),  String.format("%d", my_preference) };
	}

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

	public static void getStatistics() {
		System.out.println(get_student_k2_mmm(student_data));
		String[] ta = get_k3_ticks(student_data);
		for (String a : ta) {
			System.out.println(a);
		}
		stat_data.add(new Statistics("Total Number of Students", Integer.toString(student_data.size())));
		stat_data.add(new Statistics("K1_Energy(Average, Min, Max)", get_student_k1_mmm(student_data)));
		stat_data.add(new Statistics("K2_Energy(Average, Min, Max)", get_student_k2_mmm(student_data)));
		String[] t = get_k3_ticks(student_data);
		stat_data.add(new Statistics("K3_Tick1 = 1", t[0]));
		stat_data.add(new Statistics("K3_Tick2 = 1", t[1]));
		stat_data.add(new Statistics("My_Preference = 1", t[2]));
	}
}
