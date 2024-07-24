package com.example.jobportal.navigation.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jobportal.jobs.Jobs
import com.example.jobportal.saved.Saved

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarScreens.Saved.route){
        composable(route = BottomBarScreens.Jobs.route){
            Jobs()
        }
        composable(route = BottomBarScreens.Saved.route){
            Saved()
        }
    }
}