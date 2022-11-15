package comp3111G15;

import java.util.List;
import java.util.Collections;

/**
 * 
 * The Team contains all information for one team
 * @author SzeWingKwan
 *
 */
public class Team {
	private int team_id;
	private List<Student> team_members;
	private Student recommendedLeader;
	private double K1_average;
	private double K2_average;
	
	/**
	 * Class constructor
	 * @param id team ID
	 * @param members team member list
	 * @param leader recommended leader
	 */
	public Team(int id, List<Student> members, int leader) {
		this.team_id = id;
		this.team_members = members;
		this.recommendedLeader = this.team_members.get(leader);
		this.K1_average = computeK1Average();
		this.K2_average = computeK2Average();
	}
	
	/**
	 * The method calculates the average K1 energy in the team using the member list.
	 * @return average K1 energy
	 */
	private double computeK1Average() {
		double average = 0;
		
		for(int i = 0; i < team_members.size(); i++) {
			average += Integer.parseInt(team_members.get(i).getK1Energy());
		}
		return average /= team_members.size();
	}
	
	/**
	 * The method calculates the average K2 energy in the team using the member list.
	 * @return average K2 energy
	 */
	private double computeK2Average() {
		double average = 0;
		
		for(int i = 0; i < team_members.size(); i++) {
			average += Integer.parseInt(team_members.get(i).getK2Energy());
		}
		return average /= team_members.size();
	}
	
	/**
	 * The method sorts the team member list in descending K1 order.
	 */
	public void sortMember() {
		Collections.sort(team_members);
	}
	
	/**
	 * Gets team ID
	 * @return team ID
	 */
	public int getID() {
		return team_id;
	}
	
	/**
	 * Gets team member list
	 * @return team member list
	 */
	public List<Student> getMemberList(){
		return team_members;
	}
	
	/**
	 * Gets recommended leader
	 * @return recommended leader
	 */
	public Student getLeader() {
		return recommendedLeader;
	}
	
	/**
	 * Gets average K1 energy
	 * @return average K1 energy
	 */
	public double getK1Average() {
		return K1_average;
	}
	
	/**
	 * Gets average K2 energy
	 * @return average K2 energy
	 */
	public double getK2Average() {
		return K2_average;
	}
}