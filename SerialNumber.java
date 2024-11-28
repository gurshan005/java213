package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author gurshan bhogal 169062062
 * @version 2024-09-01
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here
    	 // Check if the string is empty, return false if it is
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Iterate through each character in the string
        for (int i = 0; i < str.length(); i++) {
            // If any character is not a digit, return false
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        // If all characters are digits, return true
        return true;
    }



    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	// your code here
    	
    	
    	
    	// First, check if the length of the serial number is exactly 10
        if (sn == null || sn.length() != 10) {
            return false;
        }

        // Check if the first part is "SN/"
        if (!sn.startsWith("SN/")) {
            return false;
        }

        // Check if the 5th character is a '-'
        if (sn.charAt(7) != '-') {
            return false;
        }

        // Now, check if the characters between 'SN/' and '-' are digits
        for (int i = 3; i < 7; i++) {
            if (!Character.isDigit(sn.charAt(i))) {
                return false;
            }
        }

        // Finally, check if the last three characters after '-' are digits
        for (int i = 8; i < 10; i++) {
            if (!Character.isDigit(sn.charAt(i))) {
                return false;
            }
        }

        // If all checks pass, return true
        return true;
    }




    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here
    	
    	while (fileIn.hasNextLine()) {
            // Read the next serial number from the input file
            String serialNumber = fileIn.nextLine();

            // Check if the serial number is valid using the validSn method
            if (validSn(serialNumber)) {
                // If valid, write to the goodSns file
                goodSns.println(serialNumber);
            } else {
                // If invalid, write to the badSns file
                badSns.println(serialNumber);
            }
        }
    

	
}
}
