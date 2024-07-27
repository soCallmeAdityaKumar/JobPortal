package com.example.jobportal.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jobportal.retrofit.model.Jobs

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertJobs(jobs: JobEnitity)

    @Delete
    suspend fun deleteJobs(job:JobEnitity)

    @Query("SELECT * FROM job_table where id=:id")
    suspend fun getJobById(id:Int):JobEnitity?

    @Query("SELECT * FROM job_table")
    suspend fun getSavedJobs():List<JobEnitity>
}