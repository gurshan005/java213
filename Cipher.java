package cp213;

/**
 * @author Your name and id here
 * @version 2024-09-01
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
    	
    	String uppercase = s.toUpperCase(); 
    	StringBuilder result = new StringBuilder(); 
    	
    	int shift = n % ALPHA_LENGTH;  
    	

	// your code here
    	
    	for (char c: uppercase.toCharArray()) { 
    		// if char is a letter shift 
    		
    		if (Character.isLetter(c)) { 
    			int originalposition = ALPHA.indexOf(c); 
    			int newposition = (originalposition + shift) % ALPHA_LENGTH; 
    			result.append(ALPHA.charAt(newposition)); 
    			
    		} else { 
    			result.append(c); 
    		}
    	}
    	return result.toString(); 
    	
    	
    	
    		
    	
    	

    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	// your code here
    	
    	 String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    	    StringBuilder result = new StringBuilder();

    	    for (int i = 0; i < s.length(); i++) {
    	        char ch = s.charAt(i);

    	        if (Character.isLetter(ch)) {
    	            boolean isLowerCase = Character.isLowerCase(ch);
    	            char upperChar = Character.toUpperCase(ch);  // Convert to upper case for substitution
    	            int index = alphabet.indexOf(upperChar);     // Find the index of the letter in the alphabet

    	            char cipherChar = ciphertext.charAt(index);

    	            // If the original character was lower-case, convert the substitution back to lower-case
    	            if (isLowerCase) {
    	                cipherChar = Character.toLowerCase(cipherChar);
    	            }

    	            result.append(cipherChar);  // Append the substituted character to the result
    	        } else {
    	            result.append(ch);
    	        }
    	    }

    	    return result.toString().toUpperCase();
    	}

	
    }


