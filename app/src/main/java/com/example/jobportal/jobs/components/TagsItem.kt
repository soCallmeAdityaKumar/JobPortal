package com.example.jobportal.jobs.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jobportal.retrofit.model.JobTag

@Composable
fun TagsItem(tag: JobTag) {
    Box {
        if(tag.value!=null)Text(text = tag.value, modifier = Modifier.padding(10.dp))
    }
}