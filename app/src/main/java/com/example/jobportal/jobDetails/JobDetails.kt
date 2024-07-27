package com.example.jobportal.jobDetails

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jobportal.R
import com.example.jobportal.jobs.viewmodel.JobsViewModel
import com.example.jobportal.navigation.JobsScreen
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.room.toJobEntity
import com.example.jobportal.ui.theme.colorHeart
import com.example.jobportal.ui.theme.montserrat
import kotlinx.coroutines.joinAll
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobDetails(job:Result,navController: NavHostController,jobDetailsViewModel: JobDetailsViewModel= hiltViewModel()) {


  jobDetailsViewModel.updateResult(job)
    val scrollState= rememberScrollState()
    var saved by remember {
        mutableStateOf(jobDetailsViewModel.checkPrefContains(job.id))
    }
    val t= listOf("hello","my name","is ","Aditya","kumar","hello","my name","is ","Aditya","kumar")
    val result=jobDetailsViewModel.result
   Column(modifier = Modifier
       .fillMaxSize()) {

       BoxItem(navController = navController, title = job.job_role.toString(), saved = saved) {
           val entity=job.toJobEntity()
           if(saved){
               jobDetailsViewModel.deleteFromDb(entity)
           }else{
               jobDetailsViewModel.addToDb(entity)
           }
           saved=saved.not()
       }
       Column(modifier = Modifier
           .fillMaxSize()
           .verticalScroll(scrollState)) {
       if(result!=null) {
           if (!result.company_name.isNullOrBlank()) RowItem(
               typeTitle = "Company Name",
               value = result.company_name
           )
           if (!result.title.isNullOrBlank()) RowItem(typeTitle = "Title", value = result.title)
           if (!result.job_hours.isNullOrBlank()) RowItem(
               typeTitle = "Hours",
               value = result.job_hours
           )
//           JobTags(t)
           if (!result.job_role.isNullOrBlank()) RowItem(
               typeTitle = "Role",
               value = result.job_role
           )
           if (!result.amount.isNullOrBlank()) RowItem(typeTitle = "Amount", value = result.amount)
//           val date=DateTimeFormatter.ofPattern("DD-MM-YYYY").parse(result.created_on)
           if (!result.created_on.isNullOrBlank()) RowItem(
               typeTitle = "Created On",
               value = result.created_on
           )
           if (!result.content.isNullOrBlank()) RowItem(
               typeTitle = "Content",
               value = result.content
           )

       }
    }

   }
}

@Composable
fun RowItem(typeTitle:String,value:String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .wrapContentHeight()) {
        Text(text=typeTitle,modifier = Modifier.weight(0.20f), fontSize = MaterialTheme.typography.bodySmall.fontSize, fontFamily = montserrat)
        Text(text = value,modifier = Modifier.weight(0.80f), fontSize = MaterialTheme.typography.bodySmall.fontSize, fontFamily = montserrat)
    }
}

@Composable
fun JobTags(tags:List<String>){
    LazyRow (modifier = Modifier.fillMaxWidth()){
        items(tags.size){
            JobTagsItems(tags[it])
        }
    }
}

@Composable
fun JobTagsItems(tag:String){
    Box(modifier = Modifier
        .widthIn(min = 0.dp, max = 140.dp)
        .padding(5.dp)
        .border(BorderStroke(2.dp, colorHeart), shape = RoundedCornerShape(20.dp))
        .padding(15.dp)
    ){
        Text(text = tag, fontSize = MaterialTheme.typography.bodySmall.fontSize, fontFamily = montserrat)
    }
}


@Composable
fun BoxItem(navController: NavHostController,title:String,saved:Boolean,onClick:()->Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, top = 30.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(colorHeart)
    ){
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()) {
                    IconButton(
                        onClick =
                        {
                            navController.navigate(JobsScreen.Jobs.route) {
                                popUpTo = navController.graph.id
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "navigate_back",
                            tint = Color.White
                        )
                    }

                Text(
                    text = title,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(top = 10.dp, end = 10.dp, bottom = 10.dp),
                    fontFamily = montserrat,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )


                    Button(modifier = Modifier
                        .padding(5.dp)
                        .size(56.dp)
                        .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        onClick = {

                            onClick()
                        }) {
                        if (saved)
                            Image(
                                painter = painterResource(id = R.drawable.filled_heart),
                                contentDescription = "filled_star"
                            )
                        else
                            Image(
                                painter = painterResource(id = R.drawable.outlined_heart),
                                contentDescription = "empty_star"
                            )
                    }
            }


        }
    }
}