package com.example.jobportal.di

import android.content.Context
import androidx.room.Room
import com.example.jobportal.room.JobDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun getJobDB(context: Context):JobDB{
        return  Room.databaseBuilder(context,JobDB::class.java,"job_db").build()
    }
}