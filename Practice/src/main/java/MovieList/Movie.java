package MovieList;

public class Movie {
    private String movieTitle;
    private String rating;
    private int year;
    private int runTime;

    public Movie(String movieTitle, String rating, int year, int runTime) {
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.year = year;
        this.runTime = runTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "MovieTitle: " + movieTitle  +
                "\n rating: " + rating  +
                "\n year: " + year +
                "\n runTime: " + runTime;
    }
}
