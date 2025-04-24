package org.sopt.at.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.history.HistoryScreen
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.live.LiveScreen
import org.sopt.at.ui.search.SearchScreen
import org.sopt.at.ui.shorts.ShortsScreen


@Composable
fun BottomNavBar() {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {
            BottomNavigation(

                containerColor = Color.Black,
                contentColor = Color.DarkGray,
                indicatorColor = Color.Transparent,
                navController = navController

            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {

            BottomNavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.title
            )
        }

    }

}


@Composable
private fun BottomNavigation(
    modifier: Modifier = Modifier,
    containerColor : Color,
    contentColor: Color,
    indicatorColor: Color,
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Shorts,
        BottomNavItem.Live,
        BottomNavItem.Search,
        BottomNavItem.History
    )

    AnimatedVisibility(
        visible = items.map { it.route }.contains(currentRoute)
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    label = {
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )

                    },
                    onClick =  {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let {
                                //popUpTo(it) {saveState = true}
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

            }
        }
    }

}


@Composable
private fun BottomNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Shorts.route) { ShortsScreen() }
        composable(BottomNavItem.Live.route) { LiveScreen() }
        composable(BottomNavItem.Search.route) { SearchScreen() }
        composable(BottomNavItem.History.route) { HistoryScreen() }
    }
}



