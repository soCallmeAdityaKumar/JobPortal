package com.example.jobportal.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.jobportal.room.JobDB
import com.example.jobportal.room.JobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun getJobDB(@ApplicationContext context: Context):JobDB{
        val db=  Room.databaseBuilder(context,JobDB::class.java,"job_db").build()
        return  db
    }

    @Provides
    @Singleton
    fun getJobDao(jobDB: JobDB):JobDao{
        val dao= jobDB.getJobDao()
        return dao
    }


}