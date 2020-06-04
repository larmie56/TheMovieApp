package com.sholasstore.themovieapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void formatMovieGenreStringTest() {
        //Arrange
        List<String> genres = getGenres();

        //Act
        String formattedGenre = StringUtil.formatMovieGenre(genres);

        //Assert
        assertEquals("Genre: Science, Fiction, Thriller", formattedGenre);
    }

    @Test
    public void formatRevenueTest() {
        //Arrange
        int revenueTest1 = 999999999;
        int revenueTest2 = 999;
        int revenueTest3 = 9999;


        //Act
        String formattedRevenue1 = StringUtil.formatRevenue(revenueTest1);
        String formattedRevenue2 = StringUtil.formatRevenue(revenueTest2);
        String formattedRevenue3 = StringUtil.formatRevenue(revenueTest3);

        //Assert
        assertEquals("Movie revenue: $999,999,999", formattedRevenue1);
        assertEquals("Movie revenue: $999", formattedRevenue2);
        assertEquals("Movie revenue: $9,999", formattedRevenue3);
    }

    private List<String> getGenres() {
        List<String> genres = new ArrayList<>();

        genres.add("Science");
        genres.add("Fiction");
        genres.add("Thriller");

        return genres;
    }

}