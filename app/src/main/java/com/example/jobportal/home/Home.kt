package com.example.jobportal.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jobportal.navigation.bottomNav.BottomBarScreens
import com.example.jobportal.navigation.bottomNav.BottomNavGraph
import com.example.jobportal.ui.theme.albumBlackBackground
import com.example.jobportal.ui.theme.appCustomBackground
import com.example.jobportal.ui.theme.colorHeart

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Home(){
    val navController= rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    Scaffold (
        bottomBar = { if(currentDestination?.route==BottomBarScreens.Jobs.route|| currentDestination?.route==BottomBarScreens.Saved.route)BottomBar(navController)}
    ){it->
        BottomNavGraph(navController)
    }
}


@Composable
fun BottomBar(
    navController: NavHostController
){
    val screens= listOf(
        BottomBarScreens.Jobs,
        BottomBarScreens.Saved
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
        BottomNavigation(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 60.dp)
                .clip(RoundedCornerShape(20.dp),)
        ) {
            screens.forEach {
                BottomItem(
                    screen = it,
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        }
}

@Composable
fun RowScope.BottomItem(
    screen:BottomBarScreens,
    navController: NavHostController,
    currentDestination:NavDestination?
){
        BottomNavigationItem(
            modifier = Modifier.background(colorHeart),
            label = {
                Text(
                    text = screen.title,
                    color = if (navController.currentDestination?.route == screen.route) Color.Black else Color.LightGray
                )
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }, icon = {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "bottom_screen",
                    tint = if (navController.currentDestination?.route == screen.route) Color.Black else Color.LightGray
                )
            },
            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
        )
}