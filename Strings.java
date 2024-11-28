package cp213;

/**
 * @author Your name and id here
 * @version 2024-09-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here
        StringBuilder cleanedString = new StringBuilder();

    	
    	for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isLetter(c)) {
                cleanedString.append(Character.toLowerCase(c));
            }
        }

        // Step 2: Check if the cleaned string is a palindrome by comparing it to its reverse
        String forward = cleanedString.toString();
        String backward = cleanedString.reverse().toString();
        
        return forward.equals(backward);  // Returns true if the cleaned string is equal to its reverse
    }

	

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here
    	if (name == null || name.isEmpty() || name.equals("_")) {
            return false;
        }

        // check if the first character is a letter or an underscore
        char firstChar = name.charAt(0);
        if (!Character.isLetter(firstChar) && firstChar != '_') {
            return false;
        }

        // check the remaining characters (they can be letters, digits, or underscores)
        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }

        // If all checks pass
        return true;
    }

	

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// your code here
    	if (word == null || word.isEmpty()) {
            return word;  // Handle edge case for null or empty input
        }

        // Step 1: Define vowels, treating 'y' as a consonant if it's the first character
        String vowels = "aeiouAEIOU";
        String originalWord = word;  // Keep a copy of the original word for case handling

        // Step 2: Check if the word starts with a vowel
        char firstChar = word.charAt(0);
        boolean startsWithVowel = vowels.indexOf(firstChar) != -1;

        if (startsWithVowel) {
            // Word starts with a vowel, append "way" and return
            return word + "way";
        } else {
            // Word starts with a consonant or 'y'
            int firstVowelIndex = -1;

            // Find the first occurrence of a vowel, treating 'y' as a vowel if not first
            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                if (vowels.indexOf(c) != -1 || c == 'y' || c == 'Y') {
                    firstVowelIndex = i;
                    break;
                }
            }

            // Step 3: Rearrange the word based on where the first vowel is found
            if (firstVowelIndex != -1) {
                String consonantCluster = word.substring(0, firstVowelIndex);
                String restOfWord = word.substring(firstVowelIndex);
                String pigLatinWord = restOfWord + consonantCluster + "ay";

                // Step 4: Preserve the case of the first character
                if (Character.isUpperCase(firstChar)) {
                    pigLatinWord = pigLatinWord.substring(0, 1).toUpperCase() + pigLatinWord.substring(1).toLowerCase();
                }

                return pigLatinWord;
            }
        }

        // Default return, though this should not be reached in a valid scenario
        return word + "ay";
    }


}
