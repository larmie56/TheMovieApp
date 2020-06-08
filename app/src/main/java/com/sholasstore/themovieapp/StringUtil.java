package com.sholasstore.themovieapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StringUtil {

    public static String appendBaseImageUrl(String imagePath) {
        String imageUrl;

        imageUrl = "https://image.tmdb.org/t/p/w500" + imagePath;

        return imageUrl;
    }

    public static String formatMovieGenre(List<String> genres) {
        String formattedGenreString = "Genre: ";

        for (String genre : genres) {
            formattedGenreString = formattedGenreString + genre + ", ";
        }

        return formattedGenreString.substring(0, formattedGenreString.length() - 2);
    }

    public static String formatReleaseDateString(String releaseDate) {
        return "Release Date: " + releaseDate;
    }

    public static String formatRevenueString(int revenue) {
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

    public static String formatMovieRuntimeInt(int runtime) {
        if (runtime < 60) { return "Runtime: " + runtime + " minutes"; }

        if ((runtime%60) == 0) { return "Runtime: " + runtime/60 + " hours"; }

        int hours = runtime/60;
        int scale = 20;
        BigDecimal num1 = new BigDecimal(runtime);
        BigDecimal num2 = new BigDecimal(60);
        String allRuntimeDigits = num1.divide(num2, scale, RoundingMode.HALF_UP).toString();
        
        //get digits after the decimal, which will be translated to minutes
        String minutesAsDecimal = allRuntimeDigits.substring(allRuntimeDigits.indexOf(".")+1);
        
        int minutes = (int) (Double.parseDouble("0." + minutesAsDecimal) * 60);

        return "Runtime: " + hours + " hours "  + minutes  +" minutes";
    }
}