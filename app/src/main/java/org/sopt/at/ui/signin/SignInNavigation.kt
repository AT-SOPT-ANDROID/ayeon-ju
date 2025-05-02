package org.sopt.at.ui.signin

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.at.ui.component.navigation.AuthNavItem



fun NavGraphBuilder.signInNavigation(navController: NavController) {


    composable(AuthNavItem.SignIn.route) {
        SignInScreen(

            onSignSuccess = {
                navController.navigate("main") {
                    popUpTo(AuthNavItem.SignIn.route) {inclusive  =true}
                }
            },
            onNavigateToSignUp ={
                navController.navigate(AuthNavItem.SignUp.route)
            }


        )
    }
}