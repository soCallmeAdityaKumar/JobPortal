package com.example.jobportal.navigation.bottomNav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobportal.navigation.GraphRoutes
import com.example.jobportal.navigation.JobNav
import com.example.jobportal.saved.Saved

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(route = GraphRoutes.RootGraph.route, navController = navController, startDestination =GraphRoutes.JobGraph.route){
        JobNav(navController)

        composable(route = BottomBarScreens.Saved.route){
            Saved(navController)
        }
    }
}

