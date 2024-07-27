package com.example.jobportal.jobs.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.common.Resource
import com.example.jobportal.common.SharedPref
import com.example.jobportal.jobs.useCases.AddJobDbUsecase
import com.example.jobportal.jobs.useCases.DeleteJobDbUseCases
import com.example.jobportal.jobs.useCases.getJobsUseCase
import com.example.jobportal.retrofit.model.Jobs
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.room.JobEnitity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class JobsViewModel @Inject constructor(
     private val getJobsUseCase: getJobsUseCase,
    private val addJobDbUsecase: AddJobDbUsecase,
    private val deleteJobDbUseCases: DeleteJobDbUseCases,
    private val sharedPref: SharedPref
):ViewModel() {

    private var jobs = MutableStateFlow(JobState())
    val _jobs: StateFlow<JobState> = jobs

    private var dbWork = MutableStateFlow(DBState())
    val _dbWork: StateFlow<DBState> = dbWork

    init {
        getJobsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    jobs.update { currrentState ->
                        currrentState.copy(jobs = result.data, isLoading = false)
                    }
                }

                is Resource.Loading -> {
                    jobs.update { currentState ->
                        currentState.copy(isLoading = true)
                    }
                }

                is Resource.Error -> {
                    jobs.update { currentState ->
                        currentState.copy(error = result.message ?: "An unexpected error occurred ", isLoading = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


      fun addToDb(job: JobEnitity) {
             addJobDbUsecase.invoke(job).onEach { result ->
                 when (result) {
                     is Resource.Error -> {
                         dbWork.update {
                             it.copy(error = result.message ?: "An unexpected error occurred ")
                         }
                     }
                     is Resource.Loading -> {
                         dbWork.update { currentState ->
                             currentState.copy(isLoading = true)
                         }
                     }
                     is Resource.Success -> {
                         dbWork.update { currrentState ->
                             currrentState.copy(done = true, result = result.data)
                         }
                         sharedPref.save(job.id, job.id.toString())
                     }

                 }
             }.launchIn(viewModelScope)
    }


      fun deleteFromDb(job: JobEnitity) {
        deleteJobDbUseCases.invoke(job).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    dbWork.update {
                        it.copy(error = result.message ?: "An unexpected error occurred ")
                    }
                }
                is Resource.Loading -> {
                    dbWork.update { currentState ->
                        currentState.copy(isLoading = true)
                    }
                }
                is Resource.Success ->{
                    sharedPref.delete(job.id.toString())
                    dbWork.update { currrentState ->
                        currrentState.copy(done = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkPrefContains(id:Int?):Boolean{
        return  sharedPref.ifContain(id.toString())
    }

}


data class JobState(
    val jobs:Jobs?=null,
    val error:String?=null,
    val isLoading:Boolean=false
)
data class DBState(
    val job:Jobs?=null,
    val result:JobEnitity?=null,
    val done:Boolean?=false,
    val error:String?=null,
    val isLoading:Boolean=false
)

