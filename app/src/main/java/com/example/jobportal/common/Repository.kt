package com.example.jobportal.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobportal.retrofit.JobsService
import com.example.jobportal.retrofit.model.Jobs
import com.example.jobportal.room.JobDB
import com.example.jobportal.room.JobEnitity
import javax.inject.Inject

class Repository @Inject constructor(private val jobsService: JobsService,private val jobDB: JobDB) {

    suspend fun  getJobs():Jobs{
        return jobsService.getJobs(1)
    }

    suspend fun addJob(job:JobEnitity){
        jobDB.getJobDao().insertJobs(job)
    }
}