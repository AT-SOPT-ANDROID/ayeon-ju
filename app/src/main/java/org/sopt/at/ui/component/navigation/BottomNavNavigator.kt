package org.sopt.at.ui.component.navigation

import androidx.navigation.NavController


fun NavController.navigateBottomTab(targetRoute: String) {

    val currentRoute = this.currentDestination?.route

    if (currentRoute != targetRoute) {
        this.navigate(targetRoute) {
            popUpTo(graph.startDestinationRoute ?: return@navigate) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }


}