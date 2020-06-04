package com.sholasstore.themovieapp.movie_details_fragment;

import com.sholasstore.themovieapp.model.movie_details.MovieGenre;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {

    @Mock RemoteRepoImpl mRemoteRepo;
    @Mock MovieDetailsContract.View mView;
    private MovieDetailsPresenter mMovieDetailsPresenter;

    @Before
    public void setup() {
        mMovieDetailsPresenter = new MovieDetailsPresenter(mRemoteRepo);
        mMovieDetailsPresenter.attachView(mView);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void verify_getMovieDetailsIsCalledOnce() {
        //Arrange
        when(mRemoteRepo.getMovieDetails(anyInt())).thenReturn(Single.just(getResponse()));

        //Act
        mMovieDetailsPresenter.fetchData(1);

        //Assert
        verify(mRemoteRepo).getMovieDetails(1);
    }

    @Test
    public void assert_correctValuesAreReceivedByConsumer() {
        //Arrange
        when(mRemoteRepo.getMovieDetails(1)).thenReturn(Single.just(getResponse()));

        //Act
        TestObserver<MovieDetailsUIModel> testObserver
                = mRemoteRepo.getMovieDetails(1).test();

        //Assert
        MovieDetailsUIModel movieDetailsUIModel = testObserver.values().get(0);

        List<String> genre = new ArrayList<>();
        genre.add("Sci-FI");
        genre.add("Thriller");

        assertEquals("The Martian", movieDetailsUIModel.getMovieTitle());
        assertEquals(500000000, movieDetailsUIModel.getRevenue());
        assertArrayEquals(genre.toArray(), movieDetailsUIModel.getMovieGenres().toArray());
    }



    private MovieDetailsUIModel getResponse() {
        List<String> genre = new ArrayList<>();
        genre.add("Sci-FI");
        genre.add("Thriller");
        return new MovieDetailsUIModel("", "The Martian", "A movie about the astronaut that became a Martian", genre, "2-20-2017", 500000000, 220);
    }
}