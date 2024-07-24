package com.example.jobportal.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jobportal.retrofit.model.Jobs

@Dao
interface JobDao {

    @Insert
    suspend fun  insertJobs(jobs: JobEnitity)

    @Query("SELECT * FROM JOB_TABLE")
    fun getSavedJobs():LiveData<Jobs>
}