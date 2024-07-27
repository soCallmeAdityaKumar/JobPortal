package com.example.jobportal.jobs.components

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.Button
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jobportal.R

@Composable
fun ImageCard(image:String?,isSaved:Boolean=false, onSaved:(saved:Boolean)->Unit) {
    var saved by remember {
        mutableStateOf(isSaved)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Gray)
        .wrapContentHeight()){

        Image(painter = rememberImagePainter(data = image),
            contentDescription ="job_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Button(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(5.dp)
            .size(56.dp)
            .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {
                    saved=saved.not()
                    onSaved(saved)

            }){
                if(saved)
                    Image(painter = painterResource(id = R.drawable.filled_heart),
                        contentDescription = "filled_star")
                else
                    Image(painter = painterResource(id = R.drawable.outlined_heart),
                        contentDescription = "empty_star")
        }

    }
}