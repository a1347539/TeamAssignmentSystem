package comp3111G15;

public class Security {

	private static final String predefinedPW = "1234";
	
	public static int checkPW(String input) {
		if (input.isEmpty()) return 0;
		if (!input.equals(predefinedPW)) return 0;
		return 1;
	}
}
