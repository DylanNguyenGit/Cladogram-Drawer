package cladogram;

/**
 * 
 * Class with static helper functions
 *
 */
public class CladogramHelper {

	/**
	 * Method that determines if a string contains an acceptable integer
	 * for resizing
	 * @param s string to be checked
	 * @return true if s is positive integer and false otherwise
	 */
	public static boolean isAcceptableInt(String s) {
		if(s == null) {
			return false;
		}
		
		int l = s.length();
		if(l == 0) {
			return false;
		}
		for(int i = 0; i < l; i++) {
			if(s.charAt(i) < '0' || s.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
}
