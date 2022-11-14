package comp3111G15;

public class Security {

	private static final String predefinedPW = "1234";
	
	/**
	 * Check input password against the default password
	 * @param String of input
	 * @return true if match, false otherwise
	 */
	public static boolean checkPW(String input) {
		if (input.isEmpty()) return false;
		if (!input.equals(predefinedPW)) return false;
		return true;
	}
}
