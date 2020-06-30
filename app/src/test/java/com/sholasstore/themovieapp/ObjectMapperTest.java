package com.sholasstore.themovieapp;

import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.model.movie_details.MovieGenre;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieResult;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectMapperTest {

    @Test
    public void assert_movieListResponseToUIModelMapping() {
        //Arrange
        MovieListResponse movieListResponse = new MovieListResponse();
        movieListResponse.setResults(getMovieResults());

        //Act
        List<MovieListUIModel> movieListUIModels =
                ObjectMapper.mapMovieListDbModelToUIModel(movieListResponse);

        //Assert
        assertEquals("Ad astra", movieListUIModels.get(0).getTitle());
        assertEquals("Scooby Doo", movieListUIModels.get(1).getTitle());
        assertEquals("The Martian", movieListUIModels.get(2).getTitle());
        assertEquals(3, movieListUIModels.get(2).getMovieId());
    }

    @Test
    public void assert_movieDetailsResponseToUIModelMapping() {
        //Arrange
        MovieDetailsResponse movieDetailsResponse = getMovieDetailsResponse();

        //Act
        MovieDetailsUIModel movieDetailsUIModel =
                ObjectMapper.mapMovieDetailsDbModelToUIModel(movieDetailsResponse);

        //Assert
        List<String> genre = new ArrayList<>();
        genre.add("Sci-Fi");
        genre.add("Thriller");
        genre.add("Fiction");

        assertEquals("The Martian", movieDetailsUIModel.getMovieTitle());
        assertEquals(500000000, movieDetailsUIModel.getRevenue());
        assertArrayEquals(genre.toArray(), movieDetailsUIModel.getMovieGenres().toArray());
    }

    private MovieDetailsResponse getMovieDetailsResponse() {
        List<MovieGenre> genre = new ArrayList<>();
        MovieGenre sci_fi = new MovieGenre();
        sci_fi.setName("Sci-Fi");
        MovieGenre thriller = new MovieGenre();
        thriller.setName("Thriller");
        MovieGenre fiction = new MovieGenre();
        fiction.setName("Fiction");
        genre.add(sci_fi);
        genre.add(thriller);
        genre.add(fiction);

        MovieDetailsResponse movieDetailsResponse = new MovieDetailsResponse();
        movieDetailsResponse.setTitle("The Martian");
        movieDetailsResponse.setId(1);
        movieDetailsResponse.setOverview("A movie about the astronaut that became a Martian");
        movieDetailsResponse.setGenres(genre);
        movieDetailsResponse.setReleaseDate("2-20-2017");
        movieDetailsResponse.setRevenue(500000000);
        movieDetailsResponse.setRuntime(220);

        return movieDetailsResponse;
    }

    private List<MovieResult> getMovieResults() {
        MovieResult adAstra = new MovieResult();
        adAstra.setTitle("Ad astra");
        adAstra.setId(1);
        adAstra.setPosterPath("");

        MovieResult scoobyDoo = new MovieResult();
        scoobyDoo.setTitle("Scooby Doo");
        scoobyDoo.setId(2);
        scoobyDoo.setPosterPath("");

        MovieResult theMartian = new MovieResult();
        theMartian.setTitle("The Martian");
        theMartian.setId(3);
        theMartian.setPosterPath("");

        List<MovieResult> listUIModels = new ArrayList<>();
        listUIModels.add(adAstra);
        listUIModels.add(scoobyDoo);
        listUIModels.add(theMartian);

        return listUIModels;
    }
}