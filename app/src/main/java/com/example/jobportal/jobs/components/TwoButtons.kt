package com.example.jobportal.jobs.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jobportal.ui.theme.colorHeart
import com.example.jobportal.ui.theme.montserrat

@Composable
fun TwoButtons (location:String,phoneNumber:String){

    val context= LocalContext.current
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

            Text(text = location,
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically).padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontFamily = montserrat,
            )

        Button(onClick ={
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clip(RoundedCornerShape(20.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(colorHeart)) {
            Text(text = "Call",fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontFamily = montserrat, color = Color.White)
        }
    }
}