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
        String formattedGenreReturned = StringUtil.formatMovieGenre(genres);

        //Assert
        assertEquals("Genre: Science, Fiction, Thriller", formattedGenreReturned);
    }

    @Test
    public void formatRevenueStringTest() {
        //Arrange
        int revenueTest1 = 999999999;
        int revenueTest2 = 999;
        int revenueTest3 = 9999;


        //Act
        String formattedRevenueReturned1 = StringUtil.formatRevenueString(revenueTest1);
        String formattedRevenueReturned2 = StringUtil.formatRevenueString(revenueTest2);
        String formattedRevenueReturned3 = StringUtil.formatRevenueString(revenueTest3);

        //Assert
        assertEquals("Movie revenue: $999,999,999", formattedRevenueReturned1);
        assertEquals("Movie revenue: $999", formattedRevenueReturned2);
        assertEquals("Movie revenue: $9,999", formattedRevenueReturned3);
    }

    @Test
    public void baseURl_isAppendedToImagePathString() {
        String imagePath = "/images/ad_astra/gn3547v4hgv";

        String returnedPath = StringUtil.appendBaseImageUrl(imagePath);
        String expectedPath = "https://image.tmdb.org/t/p/w500/images/ad_astra/gn3547v4hgv";

        assertEquals(expectedPath, returnedPath);
    }

    @Test
    public void formatReleaseDateStringTest() {
        String releaseDate = "22-06-2019";

        String formattedReleaseDateReturned = StringUtil.formatReleaseDateString(releaseDate);

        assertEquals("Release Date: 22-06-2019", formattedReleaseDateReturned);
    }

    @Test
    public void formatMovieRuntimeIntTest() {
        int runtime1 = 49;
        int runtime2 = 420;
        int runtime3 = 220;
        int runtime4 = 119;
        int runtime5 = 1093;

        String returnedRuntime1 = StringUtil.formatMovieRuntimeInt(runtime1);
        String returnedRuntime2 = StringUtil.formatMovieRuntimeInt(runtime2);
        String returnedRuntime3 = StringUtil.formatMovieRuntimeInt(runtime3);
        String returnedRuntime4 = StringUtil.formatMovieRuntimeInt(runtime4);
        String returnedRuntime5 = StringUtil.formatMovieRuntimeInt(runtime5);


        assertEquals("Runtime: 49 minutes", returnedRuntime1);
        assertEquals("Runtime: 7 hours", returnedRuntime2);
        assertEquals("Runtime: 3 hours 40 minutes", returnedRuntime3);
        assertEquals("Runtime: 1 hours 59 minutes", returnedRuntime4);
        assertEquals("Runtime: 18 hours 13 minutes", returnedRuntime5);
    }

    private List<String> getGenres() {
        List<String> genres = new ArrayList<>();

        genres.add("Science");
        genres.add("Fiction");
        genres.add("Thriller");

        return genres;
    }

}