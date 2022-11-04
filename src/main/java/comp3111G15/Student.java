package comp3111G15;

public class Student implements Comparable<Student> {
	private String student_name;
	private String student_id;
	private String student_email;
	private int K1_energy;
	private int K2_energy;
	private boolean K3_tick1;
	private boolean K3_tick2;
	private boolean my_preference;
	private String concerns;
	
	public Student(String student_id, String student_name,  String email, String k1_energy, String k2_energy, String k3_trick1,
			String k3_trick2, String my_preference, String concerns) {
		this.student_id = student_id;
		this.student_name = student_name;
		this.student_email = email;
		this.K1_energy = Integer.parseInt(k1_energy);
		this.K2_energy = Integer.parseInt(k2_energy);
		this.K3_tick1 = Integer.parseInt(k3_trick1) == 1;
		this.K3_tick2 = Integer.parseInt(k3_trick2) == 1;
		this.my_preference = Integer.parseInt(k3_trick2) == 1;
		this.concerns = concerns;
	}
	
	@Override
	public int compareTo(Student o) {
		if(this.K1_energy < o.K1_energy)
			return 1;
		else if(this.K1_energy == o.K1_energy)
			return 0;
		else return -1;
	}

	public String getName() {
		return student_name;
	}
	
	public String getID() {
		return student_id;
	}
	
	public String getEmail() {
		return student_email;
	}
	
	public int getK1Energy() {
		return K1_energy;
	}
	
	public int getK2Energy() {
		return K2_energy;
	}
	
	public boolean getK3Tick1() {
		return K3_tick1;
	}
	
	public boolean getK3Tick2() {
		return K3_tick2;
	}
	
	public boolean getPreference() {
		return my_preference;
	}
	
	public String getConcerns() {
		return concerns;
	}
}