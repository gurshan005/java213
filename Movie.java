package cp213;

import java.io.PrintStream;

/**
 * Movie class definition.
 *
 * @author Gurshan Bhogal , 169052062
 * @version 2024-09-01
 */
public class Movie implements Comparable<Movie> {

    // Constants
    /**
     * The first year movies were produced.
     */
    public static final int FIRST_YEAR = 1888;
    /**
     * The names of movie genres.
     */
    public static final String[] GENRES = { "science fiction", "fantasy", "drama", "romance", "comedy", "zombie",
	    "action", "historical", "horror", "war", "mystery" };
    /**
     * The maximum allowed Movie rating.
     */
    public static final double MAX_RATING = 10.0;
    /**
     * The minimum allowed Movie rating.
     */
    public static final double MIN_RATING = 0.0;

    /**
     * Creates a string of movie genres in the format:
     *
     * <pre>
     0: science fiction
     1: fantasy
     2: drama
    ...
    10: mystery
     * </pre>
     *
     * @return A formatted numbered string of Movie genres.
     */
    public static String genresMenu() {

	// your code here
    	
    	StringBuilder sb = new StringBuilder();

        //loop condition to iterate through all genres
        for (int i = 0; i < Movie.GENRES.length; i++) {
            sb.append(i).append(": ").append(Movie.GENRES[i]).append("\n");
        }

        // Return the final string
        return sb.toString();
    }
    

    // Attributes
    private String director = "";
    private int genre = -1;
    private double rating = 0;
    private String title = "";
    private int year = 0;

    /**
     * Instantiates a Movie object.
     *
     * @param title    Movie title.
     * @param year     Year of release.
     * @param director Name of director.
     * @param rating   Rating of 1 - 10 from IMDB.
     * @param genre    Number representing Movie genre.
     */
    public Movie(final String title, final int year, final String director, final double rating, final int genre) {

	// your code here
    	
        if (year < FIRST_YEAR) {
            throw new IllegalArgumentException("Year must be greater than or equal to " + FIRST_YEAR);
        }

        // Validate the rating to ensure it's within the allowable range
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between " + MIN_RATING + " and " + MAX_RATING);
        }

        // Validate the genre index to ensure it's valid
        if (genre < 0 || genre >= GENRES.length) {
            throw new IllegalArgumentException("Invalid genre index. Must be between 0 and " + (GENRES.length - 1));
        }

    	
    	this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.genre = genre;


    }

    /**
     * Movies are compared by title, then by year if the titles match. Must ignore
     * case. Ex: Zulu, 1964 precedes Zulu, 2013. Returns:
     * <ul>
     * <li>0 if this equals target</li>
     * <li>&lt; 0 if this precedes target</li>
     * <li>&gt; 0 if this follows target</li>
     * </ul>
     */
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Movie target) {

	// your code here
    	
    	int titleComparison = this.title.compareToIgnoreCase(target.title);
        
        if (titleComparison != 0) {
            //if titles are not equal, return the result of the title comparison
            return titleComparison;
        }

        //if titles are the same, compare by year
        return Integer.compare(this.year, target.year);
    }
    	
    	

	

    /**
     * Converts a genre integer to a string.
     *
     * @return The string version of the genre number.
     */
    public String genreToName() {

	// your code here
    	// Ensure the genre index is within the valid range
        if (genre >= 0 && genre < Movie.GENRES.length) {
            return Movie.GENRES[genre];
        } else {
            // Handle invalid genre index
            return "Unknown Genre";  // Or throw an exception if necessary
        }

    }

    /**
     * Director getter.
     *
     * @return The director.
     */
    public String getDirector() {

	// your code here

	return this.director;
    }

    /**
     * Genre getter.
     *
     * @return The genre number.
     */
    public int getGenre() {

	// your code here

	return this.genre;
    }

    /**
     * Rating getter.
     *
     * @return The rating.
     */
    public double getRating() {

	// your code here

	return this.rating;
    }

    /**
     * Title getter.
     *
     * @return The title.
     */
    public String getTitle() {

	// your code here

	return this.title;
    }

    /**
     * Year getter.
     *
     * @return The year.
     */
    public int getYear() {

	// your code here

	return this.year;
    }

    /**
     * Creates a formatted string of Movie key data in the format "title, year". Ex:
     * "Zulu, 1964".
     *
     * @return A Movie key as a string.
     */
    public String key() {
	return String.format("%s, %d", this.title, this.year);
    }

    /**
     * Rating setter.
     *
     * @param rating The new rating.
     */
    public void setRating(final double rating) {

	// your code here
    	if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between " + MIN_RATING + " and " + MAX_RATING);
        }

        // If valid, assign the new rating to the instance variable
        this.rating = rating;
    }

    

    /**
     * Returns a string in the format:
     *
     * <pre>
    Title:    title
    Year:     year
    Director: director
    Rating:   rating
    Genre:    genre
     * </pre>
     *
     */
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of movie data.
     */
    @Override
    public String toString() {

	// your code here
    	return String.format(
    	        "Title:    %s\nYear:     %d\nDirector: %s\nRating:   %.1f\nGenre:    %s", 
    	        this.title, 
    	        this.year, 
    	        this.director, 
    	        this.rating, 
    	        genreToName() // Convert genre number to name using genreToName()
    	    );

	
    }

    /**
     * Writes a single line of movie data to an open PrintStream in the format:
     * title|year|director|rating|genre
     *
     * @param ps Output PrintStream.
     */
    public void write(final PrintStream ps) {

	// your code here
    	String formattedData = String.format(
    	        "%s|%d|%s|%.1f|%d", 
    	        this.title, 
    	        this.year, 
    	        this.director, 
    	        this.rating, 
    	        this.genre  // Output the genre number (not the genre name)
    	    );

    	    // Write the formatted data to the PrintStream
    	    ps.println(formattedData);
    	


    }

}
