package comp3111G15;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ATUEngine {
	private ObservableList<Student> student_data = InputManager.student_data;
	private int Team_Number = student_data.size()/3;
	// Using maps for easier check when selecting
	private Map<Student, Integer> K1_map_top = new HashMap<Student, Integer>();
	private Map<Student, Integer> K2_map_top = new HashMap<Student, Integer>();
	private Map<Student, Integer> K1_map = new HashMap<Student, Integer>();
	private Map<Student, Integer> K2_map = new HashMap<Student, Integer>();
	private List<Student> K1_list = new ArrayList<Student>();
	private List<Student> K2_list = new ArrayList<Student>();
	private List<Student> K3_list = new ArrayList<Student>();
	public List<Team> ATU_Team = new ArrayList<Team>();
	
	// Select top(Team_Size) student_id from student_data order by K1_Energy in descending order and store into K1_List  
	public void Select_K1_member() {
		for (int i = 0; i < student_data.size(); i++) {
			K1_map.put(student_data.get(i),student_data.get(i).getK1Energy_int());
		}
		
		K1_map_top =
		K1_map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(Team_Number)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		K1_list = K1_map_top.entrySet().stream()
			    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());
		for (int i = 0; i < Team_Number; i++)
			System.out.println("id is: " + K1_list.get(i).getStudentID() + " K1 energy is: " + K1_list.get(i).getK1Energy());		
	}
	
	public void Select_K2_member() {
		Select_K1_member();
		// Select top(Team_Number) student_id from student_data where student_id not in K1_List order by  
		// K2_Energy in ascending order into K2_List 
		for (int i = 0; i < student_data.size(); i++) {
			if (!K1_map_top.containsKey(student_data.get(i))) {
				K2_map.put(student_data.get(i),student_data.get(i).getK2Energy_int());
			}
		}
		
		K2_map_top =
		K2_map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(Team_Number)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		K2_list = K2_map_top.entrySet().stream()
			    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());
	}
	
	public void Select_K3_member() {
		Select_K2_member();
		// Select student_id from student_data where student_id not in (K2_List & K2_List) into K3_List without  
		// Sorting sequency
		for (int i = 0; i < student_data.size(); i++) {
			if (!K1_map_top.containsKey(student_data.get(i)) 
			 && !K2_map_top.containsKey(student_data.get(i))) {
				K3_list.add(student_data.get(i));
			}
		}
	}
	// Some finishing work on remaining students not allocated with a group in K3_list
	public void Create_Special_Team(int i) {
		List<Student> temp = new ArrayList<Student>();
		temp.add(K1_list.get(i));
		temp.add(K2_list.get(i));
		temp.add(K3_list.get(i));
		temp.add(K3_list.get(i+1));
		if (K1_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i, temp, 0));
		else if (K2_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i, temp, 1));
		else if (K3_list.get(i).getMyPreference().equals("1"))
			ATU_Team.add(new Team(i, temp, 2));
		else
			ATU_Team.add(new Team(i, temp, 3));
	}
	
	public void Create_Team() {
		System.out.println("Create_Team executed!");
		Select_K3_member();
		if (student_data.size() == Team_Number*3) {
			System.out.println("Create_Team 3 times executed!");
			for (int i = 0; i < Team_Number; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				List<Student> temp = new ArrayList<Student>();
				temp.add(K1_list.get(i));
				temp.add(K2_list.get(i));
				temp.add(K3_list.get(i));
				if (K1_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 0));
				else if (K2_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 1));
				else
					ATU_Team.add(new Team(i, temp, 2));// Default to 2 if there is no volunteer leader or the volunteer leader is the K3_list student
			}
		}
		else if (student_data.size() == Team_Number*3+1) {
			System.out.println("Create_Team 3 times + 1 executed!");
			for (int i = 0; i < Team_Number - 1; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				List<Student> temp = new ArrayList<Student>();
				temp.add(K1_list.get(i));
				temp.add(K2_list.get(i));
				temp.add(K3_list.get(i));
				if (K1_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 0));
				else if (K2_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 1));
				else
					ATU_Team.add(new Team(i, temp, 2));// Default to 2 if there is no volunteer leader or the volunteer leader is the K3_list student
			}
			Create_Special_Team(Team_Number-1);
		}
		else if (student_data.size() == Team_Number*3+2) {
			System.out.println("Create_Team 3 times + 2 executed!");
			for (int i = 0; i < Team_Number - 2; i++) {
	//			System.out.println("creating in progress..");
	//			System.out.println(i);
				List<Student> temp = new ArrayList<Student>();
				temp.add(K1_list.get(i));
				temp.add(K2_list.get(i));
				temp.add(K3_list.get(i));
				if (K1_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 0));
				else if (K2_list.get(i).getMyPreference().equals("1"))
					ATU_Team.add(new Team(i, temp, 1));
				else
					ATU_Team.add(new Team(i, temp, 2));// Default to 2 if there is no volunteer leader or the volunteer leader is the K3_list student
			}
			Create_Special_Team(Team_Number-2);
			Create_Special_Team(Team_Number-1);
		}
	}
	
	public ATUEngine(){
		Create_Team();
	}
	
	public List<Team> getTeamlist() {
		return ATU_Team;
	}
}
