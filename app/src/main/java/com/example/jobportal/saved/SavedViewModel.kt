package com.example.jobportal.saved

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.common.Resource
import com.example.jobportal.common.SharedPref
import com.example.jobportal.jobs.useCases.DeleteJobDbUseCases
import com.example.jobportal.jobs.viewmodel.JobState
import com.example.jobportal.retrofit.model.Jobs
import com.example.jobportal.room.JobEnitity
import com.example.jobportal.saved.usecase.GetAllJobDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getAllJobDBUseCase: GetAllJobDBUseCase,
    private val deleteJobDbUseCases: DeleteJobDbUseCases,
    private val sharedPref: SharedPref
    ):ViewModel() {
    private var jobs = MutableStateFlow(SavedJobState())
    val _jobs: StateFlow<SavedJobState> = jobs

    init {
            getAllJobsFromDB()
        }

    fun getAllJobsFromDB(){
        getAllJobDBUseCase.invoke().onEach {result->
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

    fun deleteFromDb(job: JobEnitity) {
        deleteJobDbUseCases.invoke(job).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    jobs.update {
                        it.copy(error = result.message ?: "An unexpected error occurred ")
                    }
                }
                is Resource.Loading -> {
                    jobs.update { currentState ->
                        currentState.copy(isLoading = true)
                    }
                }
                is Resource.Success ->{
                    sharedPref.delete(job.id.toString())
                    jobs.update { currrentState ->
                        currrentState.copy()
                    }
                    getAllJobsFromDB()
                }
            }
        }.launchIn(viewModelScope)
    }
    fun checkPrefContains(id:Int?):Boolean{
        return  sharedPref.ifContain(id.toString())
    }

}


data class SavedJobState(
    val jobs: List<JobEnitity>?=null,
    val error:String?=null,
    val isLoading:Boolean=false
)
