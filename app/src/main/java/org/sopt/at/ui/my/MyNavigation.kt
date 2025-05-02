package org.sopt.at.ui.my

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.at.ui.component.navigation.AuthNavItem

fun NavGraphBuilder.myNavigation(navController: NavController) {
    composable(AuthNavItem.My.route) {

        MyScreen(

            onLogOut = {
                navController.navigate(AuthNavItem.SignIn.route) {
                    popUpTo("main") { inclusive = true }
                }
            }

        )

    }
}