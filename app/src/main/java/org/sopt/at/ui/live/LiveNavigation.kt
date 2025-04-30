package org.sopt.at.ui.live

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.liveNavigation() {

    composable("live") { LiveScreen() }
}