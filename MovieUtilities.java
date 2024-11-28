package cp213;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author Gurshan Bhogal, 169052062 
 * @version 2024-09-01
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {

	// your code here 
    	// Create an array to hold the count for each genre, initialized to 0
        int[] count = new int[Movie.GENRES.length];

        // Iterate over each movie in the list
        for (Movie movie : movies) {
            // Get the genre index of the movie
            int genreIndex = movie.getGenre();

            // Ensure the genre index is valid (within bounds of the GENRES array)
            if (genreIndex >= 0 && genreIndex < Movie.GENRES.length) {
                // Increment the count for the corresponding genre
                count[genreIndex]++;
            }
        }

        // Return the count array with the number of movies per genre
        return count;
    }
    	
    	
    	
    
    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery

    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {

	// your code here
    	 // Get the title
        System.out.print("Title: "); 
        String titleInput = keyboard.nextLine(); 
         
        // Get the year
        System.out.print("Year: "); 
        int yearInput = keyboard.nextInt();
        
        // Consume the leftover newline after nextInt
        keyboard.nextLine();
        
        // Get the director
        System.out.print("Director: "); 
        String directorInput = keyboard.nextLine(); 
        
        // Get the rating (as a double)
        System.out.print("Rating: "); 
        double ratingInput = keyboard.nextDouble(); 
        
        // Display available genres
        System.out.println("Genres: "); 
        for (int i = 0; i < Movie.GENRES.length; i++) { 
            System.out.println(i + ": " + Movie.GENRES[i]); 
        }

        // Get the genre number
        System.out.print("Enter a genre number: ");
        int genreInput = keyboard.nextInt();     
        
        // Construct and return the Movie object
        return new Movie(titleInput, yearInput, directorInput, ratingInput, genreInput); 
    }
    	
    	
    	

    

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

	// your code here 
    	// intalize Array for movie list 
    	ArrayList <Movie> moviescount = new ArrayList<>(); 
    	
    	// ileterate through the array and then find via getgenre if the movie is or is not 
    	
    	for (Movie movie : movies ){
    		if (movie.getGenre() == genre) { 
    			moviescount.add(movie); 
    		}
    		
    	}
    	

	return moviescount;
    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

	// your code here
    	
    	// intialize array 
    	
    	ArrayList <Movie> ratingcount = new ArrayList<>(); 
    	
    	//illeterate through making changes where needed. 
    	
    	for (Movie movie : movies ) { 
    		if (movie.getRating() == rating ) { 
    			ratingcount.add(movie); 
    		}
    	}

	return ratingcount;
    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

	// your code here 
    	
    	// intitalize array 
    	
    	ArrayList <Movie> yearcount = new ArrayList<>(); 
    	
    	// illeterate through and making changes when needed, 
    	
    	for (Movie movie : movies ) { 
    		
    		if (movie.getYear() == year) { 
    			yearcount.add(movie); 
    		}
    	}

	return yearcount; 
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {

	// your code here 
    	// display option nd then take the input 
    	System.out.println("select a genre from the selection " + Movie.genresMenu()); 
    	
    	int code = keyboard.nextInt(); 
    	
    	//consume next line 
    	keyboard.nextLine(); 
    	
    	// has to be valid, if is not valid then break 
    	
    	if (code >= 0 && code < Movie.GENRES.length) {
    		
    		// this is valid 
    		
    		return code; 
    		
    		
    		
    	} else { 
    		System.out.println("not valid");
    	

	return -1;
    }
    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {

	// your code here 
    	// split the data 
    	String [] moviedata = line.split("\\|"); 
    	
    	// extract needed data. 
    	
    	String title = moviedata[0]; 
    	
    	int year = Integer.parseInt(moviedata[1]); 
    	
    	String director = moviedata[2]; 
    	
    	int rating = Integer.parseInt(moviedata[3]); 
    	
    	int genre = Integer.parseInt(moviedata[4]); 
	
    	// return 
    	
    	return new Movie(title, year, director, rating, genre); 
    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {

	// your code here
    	
    	// inintalize the array 
    	
    	ArrayList<Movie> moviesread = new ArrayList<>(); 
    	
    	// loop through 
    	
    	while (fileIn.hasNextLine()) {
    		
    		//input string 
    		
    		String Line = fileIn.nextLine(); 
    		
    		//split 
    		
    		String [] data = Line.split("\\|"); 
    		
    		// extract data 
    		
    		String title = data[0]; 
    		
    		int year = Integer.parseInt(data[1]); 
    		
    		String director = data[2]; 
    		
    		int rating = Integer.parseInt(data[3]); 
    		
    		int genre = Integer.parseInt(data[4]);  
    		
    		// create object 
    		
    		Movie movie = new Movie (title, year, director, rating, genre); 
    		
    		moviesread.add(movie); 
    		
    		
    		
    		
    		
    		
    	}

	return moviesread;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	// your code here
    	
    	// Iterate over the list of movies
        for (Movie movie : movies) {
            // Call the write method of each Movie object to write to the PrintStream
            movie.write(ps);
        }

	

}
}
