package com.sholasstore.themovieapp.movie_list_fragment;

import com.sholasstore.themovieapp.model.movie_list.MovieListResponse;
import com.sholasstore.themovieapp.model.movie_list.MovieResult;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieListPresenterTest {

    @Mock RemoteRepoImpl mRemoteRepo;
    @Mock MovieListContract.View mView;
    private MovieListPresenter mMovieListPresenter;

    @Before
    public void setup() {
        mMovieListPresenter = new MovieListPresenter(mRemoteRepo);
        mMovieListPresenter.attachView(mView);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void verify_dataFetchingOrder() {
        //Arrange
        when(mRemoteRepo.getPopularMovies(1)).thenReturn(Single.just(getResponse()));
        when(mRemoteRepo.getTopRatedMovies(1)).thenReturn(Single.just(getResponse()));
        when(mRemoteRepo.getUpcomingMovies(1)).thenReturn(Single.just(getResponse()));

        //Act
        mMovieListPresenter.fetchData();

        //Assert
        InOrder repoInvocationOrder = Mockito.inOrder(mRemoteRepo);
        repoInvocationOrder.verify(mRemoteRepo).getPopularMovies(1);
        repoInvocationOrder.verify(mRemoteRepo).getTopRatedMovies(1);
        repoInvocationOrder.verify(mRemoteRepo).getUpcomingMovies(1);
        verifyNoMoreInteractions(mRemoteRepo);
    }

    @Test
    public void verify_getMovieDetailsIsNeverCalled_byMovieListPresenter() {
        //Arrange
        when(mRemoteRepo.getPopularMovies(1)).thenReturn(Single.just(getResponse()));
        when(mRemoteRepo.getTopRatedMovies(1)).thenReturn(Single.just(getResponse()));
        when(mRemoteRepo.getUpcomingMovies(1)).thenReturn(Single.just(getResponse()));

        //Arrange
        mMovieListPresenter.fetchData();

        //Assert
        verify(mRemoteRepo, never()).getMovieDetails(anyInt());
    }

    private List<MovieListUIModel> getResponse() {
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

        List<MovieListUIModel> movieListUIModels = new ArrayList<>();

        for (MovieResult movieResult : movieListResponse.getResults()) {
            final MovieListUIModel uiModel =
                    new MovieListUIModel("", movieResult.getTitle(), movieResult.getId());
            movieListUIModels.add(uiModel);
        }

        return movieListUIModels;
    }
}