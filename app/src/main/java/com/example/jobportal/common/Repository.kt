package com.example.jobportal.common


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.jobportal.retrofit.JobsService
import com.example.jobportal.retrofit.model.Jobs
import com.example.jobportal.room.JobDB
import com.example.jobportal.room.JobDao
import com.example.jobportal.room.JobEnitity
import javax.inject.Inject

class Repository @Inject constructor(private val jobsService: JobsService,private val jobDao: JobDao) {

    suspend fun  getJobs():Jobs{
        return jobsService.getJobs()
    }

    suspend fun addJob(job:JobEnitity){
        try {
            jobDao.insertJobs(job)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun getIdJob(id:Int?):JobEnitity?{
        try {
            return jobDao.getJobById(id!!)
        }catch (e:Exception){
            e.printStackTrace()
            return null
        }
    }


    suspend fun deleteJob(job:JobEnitity){
        try {
            jobDao.deleteJobs(job)
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }
    suspend fun getSavedJob(): List<JobEnitity>? {
        return jobDao.getSavedJobs()
    }


}