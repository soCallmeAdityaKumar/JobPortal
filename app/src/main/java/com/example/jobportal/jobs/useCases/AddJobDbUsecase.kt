package com.example.jobportal.jobs.useCases

import com.example.jobportal.common.Repository
import com.example.jobportal.common.Resource
import com.example.jobportal.room.JobEnitity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddJobDbUsecase @Inject constructor(private val repo:Repository) {

    operator fun invoke(job:JobEnitity): Flow<Resource<JobEnitity>> = flow {
        try {
                emit(Resource.Loading(null))
                repo.addJob(job)
                    emit(Resource.Success(job))

        } catch (e: Exception) {
            emit(
                Resource.Error(
                    null,
                    e.localizedMessage ?: "Unknown Error occurred in getJobsUseCase"
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    null,
                    "Couldn't reach Server . Check your Internet Connection "
                )
            )
        }

    }
}