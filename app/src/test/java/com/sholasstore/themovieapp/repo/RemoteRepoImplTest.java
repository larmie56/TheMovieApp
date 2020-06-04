package com.sholasstore.themovieapp.repo;

import com.sholasstore.themovieapp.MovieService;
import com.sholasstore.themovieapp.model.movie_details.MovieDetailsResponse;
import com.sholasstore.themovieapp.model.movie_details.MovieGenre;
import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieResult;
import com.sholasstore.themovieapp.movie_details_fragment.MovieDetailsUIModel;
import com.sholasstore.themovieapp.movie_list_fragment.MovieListUIModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoteRepoImplTest {
    @Mock MovieService mMovieService;
    private RemoteRepoImpl mRemoteRepo;

    @Before
    public void setup() {
        mRemoteRepo = new RemoteRepoImpl(mMovieService);
    }

    @Test
    //getPopularMovies, getTopMovies & getUpcomingMovies are grouped into one test -> getMovieListTest.
    //As the functions are similar in logic and functionality.
    public void getMovieListTest() {
        //Arrange
        when(mMovieService.getPopularMovies(1)).thenReturn(Single.just(getMovieListResponse()));

        //Act
        final TestObserver<List<MovieListUIModel>> listTestObserver =
                mRemoteRepo.getPopularMovies(1).test();
        final List<List<MovieListUIModel>> values = listTestObserver.values();

        //Assert
        listTestObserver.assertNoErrors();
        listTestObserver.assertComplete();
        listTestObserver.assertValueCount(1);

        final List<MovieListUIModel> listUIModels = values.get(0);
        assertEquals("Ad astra", listUIModels.get(0).getTitle());
        assertEquals("Scooby Doo", listUIModels.get(1).getTitle());

        verify(mMovieService).getPopularMovies(1);

    }

    @Test
    public void getMovieDetailsTest() {
        //Arrange
        when(mMovieService.getMovieDetails(1)).thenReturn(Single.just(getMovieDetailsResponse()));

        //Act
        final TestObserver<MovieDetailsUIModel> movieDetailsTestObserver
                = mRemoteRepo.getMovieDetails(1).test();


        //Assert
        movieDetailsTestObserver.assertNoErrors();
        movieDetailsTestObserver.assertComplete();
        movieDetailsTestObserver.assertValueCount(1);

        assertEquals("The Martian", getMovieDetailsResponse().getTitle());
        verify(mMovieService).getMovieDetails(1);
    }



    private MovieDetailsResponse getMovieDetailsResponse() {
        List<MovieGenre> genre = new ArrayList<>();
        final MovieGenre sci_fi = new MovieGenre();
        sci_fi.setName("Sci-FI");
        final MovieGenre thriller = new MovieGenre();
        thriller.setName("Thriller");
        MovieDetailsResponse movieDetailsResponse =  new MovieDetailsResponse();
        genre.add(sci_fi);

        movieDetailsResponse.setTitle("The Martian");
        movieDetailsResponse.setOverview("A movie about the astronaut that became a Martian");
        movieDetailsResponse.setGenres(genre);
        movieDetailsResponse.setReleaseDate("2-20-2017");
        movieDetailsResponse.setRevenue(500000000);
        movieDetailsResponse.setRuntime(220);
        return movieDetailsResponse;
    }

    private MovieListResponse getMovieListResponse() {
        MovieListResponse movieListResponse = new MovieListResponse();

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

        movieListResponse.setResults(listUIModels);

        return movieListResponse;
    }

}