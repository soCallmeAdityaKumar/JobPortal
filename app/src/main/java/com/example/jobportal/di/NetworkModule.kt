package com.example.jobportal.di

import com.example.jobportal.retrofit.JobsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit():JobsService{
        return  Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(JobsService::class.java)
    }
}