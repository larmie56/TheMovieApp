package com.sholasstore.themovieapp.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sholasstore.themovieapp.room.MovieListDbModel.MovieListDbFlag;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToListOfString(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        final Type listType = new TypeToken<List<String>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String listOfStringToString(List<String> stringList) {
        return gson.toJson(stringList);
    }

    @TypeConverter
    public static MovieListDbFlag fromStringToFlag(String flagString) {
        switch (flagString) {
            case "popular movies": return MovieListDbFlag.POPULAR_MOVIES;
            case "top movies": return MovieListDbFlag.TOP_MOVIES;
            case "upcoming movies": return MovieListDbFlag.UPCOMING_MOVIES;
            default: return null;
        }
    }

    @TypeConverter
    public static String fromFlagToString(MovieListDbFlag flag) {
        switch (flag) {
            case POPULAR_MOVIES: return "popular movies";
            case UPCOMING_MOVIES: return "top movies";
            case TOP_MOVIES: return "upcoming movies";
            default: return null;
        }
    }

}