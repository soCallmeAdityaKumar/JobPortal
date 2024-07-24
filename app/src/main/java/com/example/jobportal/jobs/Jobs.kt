package com.example.jobportal.jobs

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Jobs() {
    Scaffold(modifier = Modifier.fillMaxSize().background(Color.Blue)) {it->
        Text(text = "Jobs Screen", modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
        Log.d("JOBS","Showing job screen")
    }


}