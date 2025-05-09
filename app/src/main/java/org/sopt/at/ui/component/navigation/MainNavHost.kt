package org.sopt.at.ui.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.my.myNavigation
import org.sopt.at.ui.signin.signInNavigation
import org.sopt.at.ui.signup.signUpNavigation

@Composable
fun MainNavHost(

    navController: NavHostController = rememberNavController(),
    startDestination: String = AuthNavItem.SignIn.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        signInNavigation(navController)
        signUpNavigation(navController)
        myNavigation(navController)


        composable("main") {
            BottomNavBar(navController = navController)
        }
    }

}