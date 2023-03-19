package com.example.networkdemo.di

import com.example.networkdemo.example.ExampleIRepo
import com.example.networkdemo.example.ExampleRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindDatabaseRepo(
        repoImpl: ExampleRepoImpl
    ): ExampleIRepo
}