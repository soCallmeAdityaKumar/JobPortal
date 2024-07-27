package com.example.jobportal.jobDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.common.Resource
import com.example.jobportal.common.SharedPref
import com.example.jobportal.jobs.useCases.AddJobDbUsecase
import com.example.jobportal.jobs.useCases.DeleteJobDbUseCases
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.room.JobEnitity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val sharedPref: SharedPref,
    private val addJobDbUsecase: AddJobDbUsecase,
    private val deleteJobDbUseCases: DeleteJobDbUseCases,
):ViewModel() {
    var result:Result? by mutableStateOf(null)
        private  set

    fun updateResult(value: Result){
        result=value
    }

    fun checkPrefContains(id:Int?):Boolean{
        return  sharedPref.ifContain(id.toString())
    }

    fun addToDb(job: JobEnitity) {
        addJobDbUsecase.invoke(job).onEach { result ->
            when (result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                    sharedPref.save(job.id, job.id.toString())
                }

            }
        }.launchIn(viewModelScope)
    }


    fun deleteFromDb(job: JobEnitity) {
        deleteJobDbUseCases.invoke(job).onEach { result ->
            when (result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success ->{
                    sharedPref.delete(job.id.toString())

                }
            }
        }.launchIn(viewModelScope)
    }


}