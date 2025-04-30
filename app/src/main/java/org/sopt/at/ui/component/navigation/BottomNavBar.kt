package org.sopt.at.ui.component.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomNavBar() {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {
            BottomNavigation(

                navController = navController

            )
        }
    ) { innerPadding ->


        BottomNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = BottomNavItem.Home.title,

            )
    }


}


@Composable
private fun BottomNavigation(
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


    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.DarkGray
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = isSelected,
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
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title
                    )

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.DarkGray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.DarkGray,
                    indicatorColor = Color.Transparent

                ),
                onClick = {
                    navController.navigateBottomTab(item.route)
                }
            )
        }
    }
}






