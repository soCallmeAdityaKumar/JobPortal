package com.example.jobportal.navigation.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens (
    val route:String,
    val title:String,
    val icon:ImageVector
){
    object Jobs: BottomBarScreens(
        route = "jobs",
        title = "JOBS",
        icon = Icons.Default.Person
    )
    object Saved: BottomBarScreens(
        route = "saved",
        title = "SAVED",
        icon = Icons.Default.Star
    )
}