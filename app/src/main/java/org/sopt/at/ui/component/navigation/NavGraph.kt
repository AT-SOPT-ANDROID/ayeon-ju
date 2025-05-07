package org.sopt.at.ui.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.sopt.at.ui.history.historyNavigation
import org.sopt.at.ui.home.homeNavigation
import org.sopt.at.ui.live.liveNavigation
import org.sopt.at.ui.search.searchNavigation
import org.sopt.at.ui.shorts.shortsNavigation

@Composable
fun BottomNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavItem.Home.route
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        homeNavigation()
        shortsNavigation()
        liveNavigation()
        searchNavigation()
        historyNavigation()
    }


}