package com.sholasstore.themovieapp.movie_details_fragment;

import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import org.junit.Before;
import org.junit.BeforeClass;
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
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {

    @Mock RemoteRepoImpl mRemoteRepo;
    @Mock MovieDetailsContract.View mView;
    private MovieDetailsPresenter mMovieDetailsPresenter;
    private static TestScheduler mIoTestScheduler = new TestScheduler();
    private static TestScheduler mMainTestScheduler = new TestScheduler();

    @BeforeClass
    public static void classSetup() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return mIoTestScheduler;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return mMainTestScheduler;
            }
        });
    }


    @Before
    public void setup() {
        mMovieDetailsPresenter = new MovieDetailsPresenter(mRemoteRepo);
        mMovieDetailsPresenter.attachView(mView);
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
    public void fetchDataSuccessCaseTest() {
        //Arrange
        MovieDetailsUIModel movieDetailsUIModel = getResponse();
        when(mRemoteRepo.getMovieDetails(1)).thenReturn(Single.just(movieDetailsUIModel));

        //Act
        mMovieDetailsPresenter.fetchData(1);
        mIoTestScheduler.triggerActions();
        mMainTestScheduler.triggerActions();

        //Assert
        verify(mView).showLoading();
        verify(mView).showMovieDetails(movieDetailsUIModel);
        verify(mView).hideLoading();
        verifyNoMoreInteractions(mView);
    }

    @Test
    public void fetchDataErrorCaseTest() {
        Throwable error = new Throwable();
        when(mRemoteRepo.getMovieDetails(1))
                .thenReturn(Single.<MovieDetailsUIModel>error(error));

        mMovieDetailsPresenter.fetchData(1);
        mIoTestScheduler.triggerActions();
        mMainTestScheduler.triggerActions();

        verify(mView).showLoading();
        verify(mView).showError(error);
        verify(mView).hideLoading();
    }

    private MovieDetailsUIModel getResponse() {
        List<String> genre = new ArrayList<>();
        genre.add("Sci-FI");
        genre.add("Thriller");
        return new MovieDetailsUIModel("", "The Martian", "A movie about the astronaut that became a Martian", genre, "2-20-2017", 500000000, 220);
    }
}