package comp3111G15;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ATUEngine {
	// private ObservableList<Student> student_data = InputManager.student_data;
	
	private List<Student> student_data = new ArrayList<Student>(InputManager.student_data);
	private int Team_Number = student_data.size()/3;

	private List<Student> K1_list = new ArrayList<Student>();
	private List<Student> K2_list = new ArrayList<Student>();
	private List<Student> K3_list = new ArrayList<Student>();
	public List<Team> ATU_Team = new ArrayList<Team>();
	
	// Select top(Team_Size) student_id from student_data order by K1_Energy in descending order and store into K1_List  
	public void Order_by_energies() {
		
		Collections.sort(student_data);
		
		K1_list = student_data.subList(0, Team_Number);
		
		ArrayList<Student> remaining = new ArrayList<Student>(student_data.subList(Team_Number, student_data.size()));
		
		Collections.sort(remaining, new Comparator<Student>() {
		    @Override
		    public int compare(Student s1, Student s2) {
		    	int myID = Integer.parseInt(s1.getStudentID());
				int otherID = Integer.parseInt(s2.getStudentID());
		    	if (s1.getK2Energy_int()>s2.getK2Energy_int())
	                return 1;
	            else if (s1.getK2Energy_int()<s2.getK2Energy_int())
	                return -1;
	            else if(s1.getK2Energy_int()==s2.getK2Energy_int()) {
	    			if (myID < otherID) {
	    				return -1;
	    			}
	    			return 1;
		        }
	            else return -1;
		    }
		});
		
		K2_list = remaining.subList(remaining.size()-Team_Number, remaining.size());
		K3_list = remaining.subList(0, remaining.size()-Team_Number);
		
//		System.out.println("K1 max: " + InputManager.get_student_k1_mmm(student_data)[2]);
//		System.out.println("K2 max: " + InputManager.get_student_k2_mmm(student_data)[2]);
//		
//		for (int i = 0; i < Team_Number; i++)
//			System.out.println("id is: " + K1_list.get(i).getStudentID() + " K1 energy is: " + K1_list.get(i).getK1Energy());	
//		for (int i = 0; i < Team_Number; i++)
//			System.out.println("id is: " + K2_list.get(i).getStudentID() + " K2 energy is: " + K2_list.get(i).getK2Energy());	
	}
	
	public void Create_Common_Team(int i) {
		List<Student> temp = new ArrayList<Student>();
		temp.add(K1_list.get(i));
		temp.add(K2_list.get(i));
		temp.add(K3_list.get(i));
		if (K1_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i+1, temp, 0));
		else if (K2_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i+1, temp, 1));
		else
			ATU_Team.add(new Team(i+1, temp, 2));// Default to 2 if there is no volunteer leader or the volunteer leader is the K3_list student
	}
	
	// Some finishing work on remaining students not allocated with a group in K3_list
	public void Create_Special_Team(int i) {
		List<Student> temp = new ArrayList<Student>();
		temp.add(K1_list.get(i));
		temp.add(K2_list.get(i));
		temp.add(K3_list.get(i));
		temp.add(K3_list.get(i+1));
		if (K1_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i+1, temp, 0));
		else if (K2_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i+1, temp, 1));
		else if (K3_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i+1, temp, 2));
		else
			ATU_Team.add(new Team(i+1, temp, 3));
	}
	
	public void Create_Team() {
		System.out.println("Create_Team executed!");
		Order_by_energies();
		if (student_data.size() == Team_Number*3) {
			System.out.println("Create_Team 3 times executed!");
			for (int i = 0; i < Team_Number; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				Create_Common_Team(i);
			}
		}
		else if (student_data.size() == Team_Number*3+1) {
			System.out.println("Create_Team 3 times + 1 executed!");
			for (int i = 0; i < Team_Number - 1; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				Create_Common_Team(i);
			}
			Create_Special_Team(Team_Number-1);
		}
		else if (student_data.size() == Team_Number*3+2) {
			System.out.println("Create_Team 3 times + 2 executed!");
			for (int i = 0; i < Team_Number - 2; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				Create_Common_Team(i);
			}
			Create_Special_Team(Team_Number-2);
			Create_Special_Team(Team_Number-1);
		}
	}
	
	public ATUEngine(){
		Create_Team();
//		for (int i = 0; i < Team_Number; i++) {
//			System.out.println("Team " + ATU_Team.get(i).getID() + " has Student 1: " + ATU_Team.get(i).getMemberList().get(0).getStudentID() + 
//					"; has Student 2: " + ATU_Team.get(i).getMemberList().get(1).getStudentID() + 
//					"; has Student 3: " + ATU_Team.get(i).getMemberList().get(2).getStudentID());
//		}
	}
	
	public List<Team> getTeamlist() {
		return ATU_Team;
	}
}