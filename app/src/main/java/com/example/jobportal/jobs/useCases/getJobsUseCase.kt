package com.example.jobportal.jobs.useCases

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.jobportal.common.Repository
import com.example.jobportal.common.Resource
import com.example.jobportal.retrofit.model.Jobs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class getJobsUseCase @Inject constructor(private val repo:Repository) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke():Flow<Resource<Jobs?>> = flow{

        try {
            emit(Resource.Loading(null))
            val jobs=repo.getJobs()
            emit(Resource.Success(jobs))
        }catch (e:Exception){
            emit(
                Resource.Error(null,
                    e.localizedMessage ?: "Unknown Error occurred in getJobsUseCase"
                )
            )
        }catch (e:Exception){
            emit(
                Resource.Error(null,
                    "Couldn't reach Server . Check your Internet Connection "
                )
            )
        }

    }

}