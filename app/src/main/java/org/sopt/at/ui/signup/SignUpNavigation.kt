package org.sopt.at.ui.signup

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.sopt.at.ui.component.navigation.AuthNavItem


fun NavGraphBuilder.signUpNavigation(navController: NavHostController) {
    composable(AuthNavItem.SignUp.route) {
        SignUpScreen(
            onSignUpSuccess = {
                navController.popBackStack()
            }
        )
    }

}