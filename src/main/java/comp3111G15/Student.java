package comp3111G15;

import java.util.Comparator;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * The Student contains all information used for teaming up.
 * @author SzeWingKwan
 *
 */
public class Student implements Comparable<Student> {
	private final SimpleStringProperty index;
	private final SimpleStringProperty studentID;
	private final SimpleStringProperty studentName;
	private final SimpleStringProperty studentEmail;
	private final SimpleStringProperty k1Energy;
	private final SimpleStringProperty k2Energy;
	private final SimpleStringProperty k3Tick1;
	private final SimpleStringProperty k3Tick2;
	private final SimpleStringProperty myPreference;
	private final SimpleStringProperty concerns;
	
	/**
	 * Class constructor
	 * @param index
	 * @param student_id student ID
	 * @param student_name student name
	 * @param email student email
	 * @param k1_energy K1 energy
	 * @param k2_energy K2 energy
	 * @param k3_tick1 whether is creative and participate aggressively
	 * @param k3_tick2 whether is willing to take more workloads
	 * @param my_preference my preference to be the leader
	 * @param concerns concerns/comments
	 */
	public Student(int index, String student_id, String student_name,  String email, String k1_energy, String k2_energy, String k3_tick1,
			String k3_tick2, String my_preference, String concerns) {
		this.index = new SimpleStringProperty(Integer.toString(index));
		this.studentID = new SimpleStringProperty(student_id);
		this.studentName = new SimpleStringProperty(student_name);
		this.studentEmail = new SimpleStringProperty(email);
		this.k1Energy = new SimpleStringProperty(k1_energy);
		this.k2Energy = new SimpleStringProperty(k2_energy);
		this.k3Tick1 = new SimpleStringProperty(k3_tick1);
		this.k3Tick2 = new SimpleStringProperty(k3_tick2);
		this.myPreference = new SimpleStringProperty("1");
		this.concerns = new SimpleStringProperty(concerns);
	}
	
	/**
	 * The method used for sorting the students in a list in descending K1 order.
	 */
	@Override
	public int compareTo(Student other) {
		int myK1 = Integer.parseInt(this.getK1Energy());
		int otherK1 = Integer.parseInt(other.getK1Energy());
		int myID = Integer.parseInt(this.getStudentID());
		int otherID = Integer.parseInt(other.getStudentID());
		if(myK1 < otherK1)
			return 1;
		else if(myK1 == otherK1) {
			if (myID < otherID) {
				return -1;
			}
			return 1;
		}
		else return -1;
	}
  /**
	 * Gets K1 energy in integer
	 * @return K1 energy
	 */
   public int getK1Energy_int() {
		return Integer.parseInt(k1Energy.get());
	}
	
	/**
	 * Gets K2 energy in integer
	 * @return K2 energy
	 */
	public int getK2Energy_int() {
		return Integer.parseInt(k2Energy.get());
	}
	
	/**
	 * Gets K3 tick 1
	 * @return true/false
	 */
	public boolean getK3Tick1_bool() {
		return Integer.parseInt(k3Tick1.get()) == 1;
	}
	
	/**
	 * Gets K3 tick 2
	 * @return true/false
	 */
	public boolean getK3Tick2_bool() {
		return Integer.parseInt(k3Tick2.get()) == 1;
	}
	
	/**
	 * Gets index
	 * @return index
	 */
	public String getIndex() {
		return index.get();
	}

	/**
	 * Sets index
	 * @param val for index
	 */
	public void setIndex(String val) {
		index.set(val);
	}
	
	/**
	 * Gets student ID
	 * @return student ID
	 */
	public String getStudentID() {
		return studentID.get();
	}

	/**
	 * Sets student ID
	 * @param val for student ID
	 */
	public void setStudentID(String val) {
		studentID.set(val);
	}

	/**
	 * Gets student name
	 * @return student name
	 */
	public String getStudentName() {
		return studentName.get().replaceAll("\"", "");
	}

	/**
	 * Sets student name
	 * @param val for student name
	 */
	public void setStudentName(String val) {
		studentName.set(val);
	}
	
	/**
	 * Gets student email
	 * @return student email
	 */
	public String getStudentEmail() {
		return studentEmail.get().replaceAll("\"", "");
	}

	/**
	 * Sets student email
	 * @param val for student email
	 */
	public void setStudentEmail(String val) {
		studentEmail.set(val);
	}

	/**
	 * Gets K1 energy in String
	 * @return K1 energy
	 */
	public String getK1Energy() {
		return k1Energy.get();
	}

	/**
	 * Sets K1 energy
	 * @param val for K1 energy
	 */
	public void setK1Energy(String val) {
		k1Energy.set(val);
	}

	/**
	 * Gets K2 energy in String
	 * @return K2 energy
	 */
	public String getK2Energy() {
		return k2Energy.get();
	}

	/**
	 * Sets K2 energy
	 * @param val for K2 energy
	 */
	public void setK2Energy(String val) {
		k2Energy.set(val);
	}

	/**
	 * Gets K3 tick 1 in String
	 * @return 1/0
	 */
	public String getK3Tick1() {
		return k3Tick1.get();
	}

	/**
	 * Sets K3 tick 1
	 * @param val for K3 tick 1
	 */
	public void setK3Tick1(String val) {
		k3Tick1.set(val);
	}

	/**
	 * Gets K3 tick 2 in String
	 * @return 1/0
	 */
	public String getK3Tick2() {
		return k3Tick2.get();
	}

	/**
	 * Sets K3 tick 2
	 * @param val for K3 tick 2
	 */
	public void setK3Tick2(String val) {
		k3Tick2.set(val);
	}

	/**
	 * Gets my preference
	 * @return 1/0
	 */
	public String getMyPreference() {
		return myPreference.get();
	}

	/**
	 * Sets my preference
	 * @param val for my preference
	 */
	public void setMyPreference(String val) {
		myPreference.set(val);
	}

	/**
	 * Gets concerns
	 * @return concerns
	 */
	public String getConcerns() {
		return concerns.get().replaceAll("\"", "");
	}

	/**
	 * Sets concerns
	 * @param val for concerns
	 */
	public void setConcerns(String val) {
		concerns.set(val);
	}
}