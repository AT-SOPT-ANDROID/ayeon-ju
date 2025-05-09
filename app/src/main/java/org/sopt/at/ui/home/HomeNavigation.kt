package org.sopt.at.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.at.ui.component.navigation.AuthNavItem

fun NavGraphBuilder.homeNavigation(navController: NavController) {

    composable("home") {
        HomeScreen(
            viewModel = HomeViewModel(),
            onProfileClick = { navController.navigate(AuthNavItem.My.route) }
        )
    }
}