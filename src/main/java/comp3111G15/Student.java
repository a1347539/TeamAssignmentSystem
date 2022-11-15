package comp3111G15;

import java.util.Comparator;

import javafx.beans.property.SimpleStringProperty;

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

	
	public int getK1Energy_int() {
		return Integer.parseInt(k1Energy.get());
	}
	
	public int getK2Energy_int() {
		return Integer.parseInt(k2Energy.get());
	}
	
	public boolean getK3Tick1_bool() {
		return Integer.parseInt(k3Tick1.get()) == 1;
	}
	
	public boolean getK3Tick2_bool() {
		return Integer.parseInt(k3Tick2.get()) == 1;
	}
	
	
	public String getIndex() {
		return index.get();
	}

	public void setIndex(String val) {
		index.set(val);
	}
	
	public String getStudentID() {
		return studentID.get();
	}

	public void setStudentID(String val) {
		studentID.set(val);
	}

	public String getStudentName() {
		return studentName.get().replaceAll("\"", "");
	}

	public void setStudentName(String val) {
		studentName.set(val);
	}
	
	public String getStudentEmail() {
		return studentEmail.get().replaceAll("\"", "");
	}

	public void setStudentEmail(String val) {
		studentEmail.set(val);
	}

	public String getK1Energy() {
		return k1Energy.get();
	}

	public void setK1Energy(String val) {
		k1Energy.set(val);
	}

	public String getK2Energy() {
		return k2Energy.get();
	}

	public void setK2Energy(String val) {
		k2Energy.set(val);
	}

	public String getK3Tick1() {
		return k3Tick1.get();
	}

	public void setK3Tick1(String val) {
		k3Tick1.set(val);
	}

	public String getK3Tick2() {
		return k3Tick2.get();
	}

	public void setK3Tick2(String val) {
		k3Tick2.set(val);
	}

	public String getMyPreference() {
		return myPreference.get();
	}

	public void setMyPreference(String val) {
		myPreference.set(val);
	}

	public String getConcerns() {
		return concerns.get().replaceAll("\"", "");
	}

	public void setConcerns(String val) {
		concerns.set(val);
	}
}