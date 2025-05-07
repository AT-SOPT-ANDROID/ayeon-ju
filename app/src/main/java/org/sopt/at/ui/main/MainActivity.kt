package org.sopt.at.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.at.ui.component.navigation.AuthNavItem
import org.sopt.at.ui.component.navigation.MainNavHost
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import androidx.compose.runtime.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {



            ATSOPTANDROIDTheme {

                val viewModel: SplashViewModel = hiltViewModel()
                val startDestination by viewModel.startDestination.collectAsState()

                if (startDestination != null) {

                    MainNavHost(startDestination = startDestination!!)


                }
             }
           }
        }
    }
