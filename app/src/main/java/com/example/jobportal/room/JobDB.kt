package com.example.jobportal.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

@Database(entities = [JobEnitity::class], version = 1)
abstract class JobDB :RoomDatabase(){

    abstract fun getJobDao():JobDao
}