package org.sopt.at.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavigation() {

    composable("home") { HomeScreen() }
}