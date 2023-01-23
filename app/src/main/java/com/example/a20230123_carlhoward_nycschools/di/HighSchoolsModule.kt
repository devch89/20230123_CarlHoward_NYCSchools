package com.example.a20230123_carlhoward_nycschools.di

import com.example.a20230123_carlhoward_nycschools.model.Network
import com.example.a20230123_carlhoward_nycschools.model.Repository
import com.example.a20230123_carlhoward_nycschools.model.RepositoryImpl
import com.example.a20230123_carlhoward_nycschools.viewmodel.HighSchoolVMProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {
    @Singleton
    @Provides
    fun providesRepository(): Repository {
        return RepositoryImpl(Network.getInstance())
    }
}

@InstallIn(ActivityComponent::class)
@Module
object ViewModelModule{
    @Provides
    fun providesVM(repository: Repository): HighSchoolVMProvider {
        return HighSchoolVMProvider(
            repository
        )
    }
}