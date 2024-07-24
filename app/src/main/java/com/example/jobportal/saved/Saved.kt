package com.example.jobportal.saved

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Saved() {
    Scaffold(modifier = Modifier.fillMaxSize().background(Color.Blue)) {it->
        Text(text = "Saved Screen", modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
        Log.d("Saved","Showing saved screen")

    }
}