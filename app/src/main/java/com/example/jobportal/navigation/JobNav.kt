package com.example.jobportal.navigation

import android.os.Build
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jobportal.jobDetails.JobDetails
import com.example.jobportal.jobs.Jobs
import com.example.jobportal.retrofit.model.Result


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun NavGraphBuilder.JobNav (
    navController: NavHostController
){
    navigation(startDestination = JobsScreen.Jobs.route,route=GraphRoutes.JobGraph.route){
        composable(route = JobsScreen.Jobs.route){
            Jobs(navController)
        }

        composable(route = JobsScreen.JobDetails.route, ){
            val resultJson=navController.previousBackStackEntry?.savedStateHandle?.get<Result>("job")
            if (resultJson != null) {
                JobDetails(resultJson,navController)
            }
        }
    }

}

sealed class GraphRoutes(val route:String){
    data object JobGraph:GraphRoutes("job_graph_route")
    data object RootGraph:GraphRoutes("root_graph_route")
}

sealed class JobsScreen(
    val title:String,
    val route:String,
){
    data object JobDetails:JobsScreen(
        title = "Details",
        route = "jobs_details"
    )
    data object Jobs:JobsScreen(
        title = "Jobs",
        route = "jobs"
    )
}