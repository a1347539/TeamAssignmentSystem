package comp3111G15;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * The ATUEngine Produces Team-up results and have them stored in ATU_Team dataset.
 * @author HE Qihao
 *
 */
public class ATUEngine {
	private List<Student> student_data = new ArrayList<Student>();
	private int Team_Number = 0;

	private List<Student> K1_list = new ArrayList<Student>();
	private List<Student> K2_list = new ArrayList<Student>();
	private List<Student> K3_list = new ArrayList<Student>();
	
	/**
	 * List of team
	 */
	public List<Team> ATU_Team = new ArrayList<Team>();
	
	/**
	 * Select top(Team_Size) student_id from student_data order by K1_Energy in descending order 
	 * and store into K1_List, Then select top(Team_size) student_id from student_data order by K2_Energy
	 * and put in K2_list by ascending order, and then put rest students in K3_list with no order
	 */
	public void Order_by_energies() {
		
		Collections.sort(student_data, new Comparator<Student>() {
		    @Override
		    public int compare(Student s1, Student s2) {
		    	int myID = Integer.parseInt(s1.getStudentID());
				int otherID = Integer.parseInt(s2.getStudentID());
		    	if (s1.getK1Energy_int()>s2.getK1Energy_int())
	                return -1;
	            else if (s1.getK1Energy_int()<s2.getK1Energy_int())
	                return 1;
	            else if(s1.getK1Energy_int()==s2.getK1Energy_int()) {
	    			if (myID < otherID) {
	    				return -1;
	    			}
	    			return 1;
		        }
	            else return -1;
		    }
		});
		
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
	
	/**
	 * Create normal teams with three students.
	 * @param i for (team.id-1)
	 */
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
	
	/**
	 * Some finishing work on remaining students not allocated with a group in K3_list, this method
	 * create team with four students
	 * @param i for (team.id-1)
	 */
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
	
	/**
	 * Create teams and put students from K1_list, K2_list and K3_list in each of them.
	 * @param studentData for teams to be created
	 */
	public void Create_Team(List<Student> studentData) {
		student_data = studentData;
		Team_Number = student_data.size()/3;
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
	
	/**
	 * Class constructor, calls Create_Team() method to produce team-up results.
	 * @param studentData for ATUEngine to start running
	 */
	public ATUEngine(List<Student> studentData){
		Create_Team(studentData);
//		for (int i = 0; i < Team_Number; i++) {
//			System.out.println("Team " + ATU_Team.get(i).getID() + " has Student 1: " + ATU_Team.get(i).getMemberList().get(0).getStudentID() + 
//					"; has Student 2: " + ATU_Team.get(i).getMemberList().get(1).getStudentID() + 
//					"; has Student 3: " + ATU_Team.get(i).getMemberList().get(2).getStudentID());
//		}
	}
	
	/**
	 * Accessor that returns team-up results.
	 * @return list of resulting team
	 */
	public List<Team> getTeamlist() {
		return ATU_Team;
	}
}
