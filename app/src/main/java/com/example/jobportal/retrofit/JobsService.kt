package com.example.jobportal.retrofit

import com.example.jobportal.retrofit.model.Jobs
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsService {

    @GET("common/jobs/")
    suspend fun getJobs(@Query("page")page:Int=1):Jobs
}