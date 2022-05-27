package movielist;

import java.util.Scanner;

public class MovieList {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String movie, rating;
        int year, runTime;
        System.out.println("Please enter Movie Title: ");
        movie = in.nextLine();
        System.out.println("Please enter Movie rating: ");
        rating = in.nextLine();
        System.out.println("Please enter Movie year: ");
        year = in.nextInt();
        System.out.println("Please enter Movie runTime: ");
        runTime = in.nextInt();
        Movie favoriteMovie = new Movie(movie, rating, year, runTime);
        System.out.println(favoriteMovie.toString());
    }
}
