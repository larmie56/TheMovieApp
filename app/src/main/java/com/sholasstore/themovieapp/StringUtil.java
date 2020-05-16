package com.sholasstore.themovieapp;

public class StringUtil {

    public static String appendBaseImageUrl(String imagePath) {
        String imageUrl;

        imageUrl = "https://image.tmdb.org/t/p/w500" + imagePath;

        return imageUrl;
    }
}
