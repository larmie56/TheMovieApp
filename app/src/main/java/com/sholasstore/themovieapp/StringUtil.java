package com.sholasstore.themovieapp;

import java.util.Date;
import java.util.List;

public class StringUtil {

    public static String appendBaseImageUrl(String imagePath) {
        String imageUrl;

        imageUrl = "https://image.tmdb.org/t/p/w500" + imagePath;

        return imageUrl;
    }

    public static String formatMovieGenre(List<String> genres) {
        String formattedGenreString = "";

        for (String genre : genres) {
            formattedGenreString = "Genre: " + genre + ", ";
        }

        return formattedGenreString.substring(0, formattedGenreString.length() - 2);
    }

    public static String formatReleaseDate(String releaseDate) {
        return "Release Date: " + releaseDate;
    }

    public static String formatRevenue(int revenue) {
        final String revenueString = String.valueOf(revenue);
        StringBuffer revenueStringBuffer = new StringBuffer(revenueString);
        int revenueStringLength = revenueString.length();

        while (revenueStringLength > 3) {
            revenueStringLength -= 3;
            revenueStringBuffer.insert(revenueStringLength, ',');
        }

        String formattedRevenueString = revenueStringBuffer.toString();

        return "Movie revenue: $" + formattedRevenueString;
    }

    public static String formatMovieRuntime(int runtime) {
        return "Runtime: " + runtime + " minutes";
    }
}
