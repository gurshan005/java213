package cp213;

/**
 * @author Your name and id here
 * @version 2024-09-01
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

	// your code here 
    	
    	// Calculate the absolute difference between target and v1, and target and v2
        double diff1 = Math.abs(target - v1);
        double diff2 = Math.abs(target - v2);

        // Compare the differences
        if (diff1 < diff2) {
            return v1;  // v1 is closer
        } else if (diff2 < diff1) {
            return v2;  // v2 is closer
        } else {
            return v1;  // If they are equal, return v1 as per the instructions
        }
    }
    		
        	
    	

    

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {

	// your code here
    	
    	if (n <= 1) {
            return false;
        }

        // Check if n is divisible by any number from 2 to the square root of n
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // If divisible by any i, n is not a prime number
            }
        }

        // If no divisor was found, n is a prime number
        return true;
    }


    

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {

	// your code here
    	double sum = 0.0;  // Initialize the sum to 0

        // Iterate from 1 to n and add 1/i to the sum
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;  // Add the term 1/i to the sum
        }

        return sum;  // Return the final sum
    }

	

}
