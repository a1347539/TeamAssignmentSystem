package comp3111G15;

import java.util.List;
import java.util.Collections;

public class Team {
	private int team_id;
	private List<Student> team_members;
	private Student recommendedLeader;
	private double K1_average;
	private double K2_average;
	
	public Team(int id, List<Student> members, int leader) {
		this.team_id = id;
		this.team_members = members;
		this.recommendedLeader = this.team_members.get(leader);
		this.K1_average = computeK1Average();
		this.K2_average = computeK2Average();
	}
	
	private double computeK1Average() {
		double average = 0;
		
		for(int i = 0; i < team_members.size(); i++) {
			average += team_members.get(i).getK1Energy();
		}
		return average /= team_members.size();
	}
	
	private double computeK2Average() {
		double average = 0;
		
		for(int i = 0; i < team_members.size(); i++) {
			average += team_members.get(i).getK2Energy();
		}
		return average /= team_members.size();
	}
	
	public void sortMember() {
		Collections.sort(team_members);
	}
	
	public int getID() {
		return team_id;
	}
	
	public List<Student> getMemberList(){
		return team_members;
	}
	
	public Student getLeader() {
		return recommendedLeader;
	}
	
	public double getK1Average() {
		return K1_average;
	}
	
	public double getK2Average() {
		return K2_average;
	}
}