package org.sopt.at.ui.shorts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.shortsNavigation() {

    composable("shorts") { ShortsScreen() }
}