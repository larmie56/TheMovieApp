package com.sholasstore.themovieapp.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class MovieGenreTypeConverter {

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

}
