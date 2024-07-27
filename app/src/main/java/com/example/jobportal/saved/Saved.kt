package com.example.jobportal.saved

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jobportal.jobs.HeadingBox
import com.example.jobportal.jobs.JobsItem
import com.example.jobportal.room.toJobEntity
import com.example.jobportal.room.toResult
import com.example.jobportal.ui.theme.montserrat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Saved(navController: NavHostController,savedViewModel: SavedViewModel= hiltViewModel()) {

    val state=savedViewModel._jobs.collectAsState().value
    Scaffold(modifier = Modifier
        .fillMaxSize()) { it ->
        Column(Modifier.fillMaxSize()) {

            HeadingBox("Saved Jobs")

            state.let { jobState ->
                when {
                    !jobState.jobs.isNullOrEmpty() -> {
                        val jobsList = jobState.jobs
                        jobsList?.let {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                            ) {
                                items(jobsList.size) {
                                    var contains=savedViewModel.checkPrefContains(jobsList[it].id)
                                    val result=jobsList[it].toResult()
                                    JobsItem(result, navController,contains) {result,it->
                                        val entity=result.toJobEntity()
                                        savedViewModel.deleteFromDb(entity)
                                    }
                                }
                            }
                        }
                    }

                    jobState.isLoading -> {
//                        loading=true
//                        Log.d("Hello", "isLoading")
//                        if(loading) {
//                            Progress()
//                        }
                        Text(text = "Loading", fontSize = 30.sp, modifier = Modifier.fillMaxSize())
                    }

                    jobState.jobs?.size==0->{
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "No Saved Item", fontSize = MaterialTheme.typography.bodySmall.fontSize,)
                        }
                    }
                    else -> {
                        androidx.compose.material.Text(
                            text = jobState.error.toString(),
                            fontSize = 30.sp,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

        }
    }
}