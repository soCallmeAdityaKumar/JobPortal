package com.example.jobportal.jobs

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FloatAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.jobportal.jobDetails.BoxItem
import com.example.jobportal.jobDetails.JobDetailsViewModel
import com.example.jobportal.jobs.components.Card
import com.example.jobportal.jobs.components.ImageCard
import com.example.jobportal.jobs.viewmodel.JobsViewModel
import com.example.jobportal.navigation.GraphRoutes
import com.example.jobportal.navigation.JobsScreen
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.room.JobEnitity
import com.example.jobportal.room.toJobEntity
import com.example.jobportal.ui.theme.colorHeart
import com.example.jobportal.ui.theme.montserrat
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Jobs(navController: NavHostController,viewModel: JobsViewModel =hiltViewModel()){
    val state=viewModel._jobs.collectAsState().value
    val dbState=viewModel._dbWork.collectAsState().value
    var loading by remember{
        mutableStateOf(false)
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()) { it ->
        Column(Modifier.fillMaxSize()) {

            HeadingBox("Discover Jobs")

            state.let { jobState ->
                when {
                    !jobState.jobs?.results.isNullOrEmpty() -> {
                        loading=false
                        val jobsList = jobState.jobs?.results
                        jobsList?.let {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                            ) {
                                items(jobsList.size) {
                                    var contains=viewModel.checkPrefContains(jobsList[it].id)
                                    if(jobsList[it].job_role!=null){
                                        JobsItem(jobsList[it], navController,contains) {result,it->
                                            val entity=result.toJobEntity()
                                            if(!contains){
                                                viewModel.addToDb(entity)

                                            }else{
                                                viewModel.deleteFromDb(entity)
                                            }
                                            contains=viewModel.checkPrefContains(entity.id)
//
                                        }
                                    }

                                }
                            }
                        }
                    }

                    jobState.isLoading -> {
                        loading=true
                        Log.d("Hello", "isLoading")
                        if(loading) {
                            ProgressLoad()
                        }
                    }

                    else -> {
                        loading=false
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Please Check Your Internet or Try after Sometime...", fontSize = 20.sp, fontFamily = montserrat, modifier = Modifier.padding(20.dp))
                        }

                    }
                }
            }

        }
    }
}

@Composable
fun JobsItem(jobs: Result, navController: NavHostController,saved:Boolean,onClick:(result:Result,saved:Boolean)->Unit) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .clickable {
//            onClick(jobs)
//            Log.d("hello",jobs.toString())
            navController.currentBackStackEntry?.savedStateHandle?.set("job", jobs)
            navController.navigate(JobsScreen.JobDetails.route)
        }
    ){
        Card(result = jobs,saved){
            onClick(jobs,it)
        }
    }
}
@Composable
fun ProgressLoad(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//        CircularProgressIndicator(progress = Float.POSITIVE_INFINITY,Modifier.size(70.dp), Color.Gray, strokeWidth = 4.dp,)
        Text(text = "Loading..", fontSize = 30.sp,fontFamily = montserrat, modifier = Modifier.padding(20.dp))

    }
}


@Composable
fun HeadingBox(title:String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, top = 40.dp, end = 10.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(colorHeart)){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontFamily = montserrat,
                color = Color.White,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        }

    }
}
