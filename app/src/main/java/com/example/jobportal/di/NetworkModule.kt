package com.example.jobportal.di

import android.util.Log
import com.example.jobportal.common.Constants
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
            .baseUrl(Constants.LOKAL_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(JobsService::class.java)
    }
}