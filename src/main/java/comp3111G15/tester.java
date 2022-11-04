package comp3111G15;

import java.io.File;
import java.util.List;
import java.util.Arrays;

public class tester {
	static Team team1 = new Team(1, InputManager.student_data.subList(0, 3), 2);
	static Team team2 = new Team(2, InputManager.student_data.subList(3, 6), 1);
	static Team team3 = new Team(3, InputManager.student_data.subList(6, 10), 0);
	
	static List<Team> teams = Arrays.asList(team1, team2, team3);
	
	public static void main(String[] args) throws Exception {

		String csvFile = "Sample Student Data File.CSV";
		File file = new File(csvFile);
		System.out.println(file.getAbsolutePath());
		if(file.canRead())
		{
			System.out.println("readable");
		    // read file here
		} else {
			System.out.println("not readable");
			
		}
		

	}
}
