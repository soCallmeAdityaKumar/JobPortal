package com.example.jobportal.jobs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobportal.navigation.JobsScreen
import com.example.jobportal.retrofit.model.Result
import com.example.jobportal.ui.theme.albumBlackBackground
import com.example.jobportal.ui.theme.montserrat

@Composable
fun Card(result: Result,saved: Boolean,onSaved:(saved:Boolean)->Unit) {
    androidx.compose.material.Card(elevation = 10.dp,
        modifier = Modifier.padding(vertical = 10.dp), shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(),verticalArrangement = Arrangement.Center) {
            if(!result.creatives.isNullOrEmpty())
                ImageCard(image = result.creatives[0].file,saved){
                    onSaved(it)
                }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                if(!result.job_role.isNullOrBlank())Text(text=result.job_role,
                    modifier = Modifier.width(240.dp),
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontFamily = montserrat
                )
                if(!result.amount.isNullOrBlank())Text(text = result.amount,maxLines = 1,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color= Color.Black,
                    fontFamily = montserrat)
            }
            Spacer(modifier = Modifier.height(2.dp))
//            if(result.job_tags.isNotEmpty()){
//                LazyRow(modifier = Modifier.fillMaxWidth()) {
//                    items(result.tags.size){
//                        TagsItem(result.job_tags[it])
//                    }
//                }
//            }
            TwoButtons(result.primary_details?.Place.toString(),result.whatsapp_no.toString())
        }
    }

}

