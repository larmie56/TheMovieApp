package com.sholasstore.themovieapp.di;

import com.sholasstore.themovieapp.repo.LocalRepo;
import com.sholasstore.themovieapp.repo.LocalRepoImpl;
import com.sholasstore.themovieapp.repo.RemoteRepo;
import com.sholasstore.themovieapp.repo.RemoteRepoImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepoBinders {

    @Binds
    public abstract RemoteRepo providesRemoteRepo(RemoteRepoImpl remoteRepo);

    @Binds
    public abstract LocalRepo providesLocalRepo(LocalRepoImpl localRepo);
}
