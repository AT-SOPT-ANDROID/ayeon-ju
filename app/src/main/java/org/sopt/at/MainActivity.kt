package org.sopt.at

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.at.data.AuthPreferences
import org.sopt.at.ui.component.navigation.AuthNavItem
import org.sopt.at.ui.component.navigation.MainNavHost
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.signin.SignInScreen
import org.sopt.at.ui.signup.SignUpScreen
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val authPreferences = AuthPreferences(this)
            val isLoggedIn = authPreferences.isLoggedIn()



            ATSOPTANDROIDTheme {


                    MainNavHost(startDestination = if (isLoggedIn) "main" else AuthNavItem.SignIn.route)





            }
        }
    }
}



