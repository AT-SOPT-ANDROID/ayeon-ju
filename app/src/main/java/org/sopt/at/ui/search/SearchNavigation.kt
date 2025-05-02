package org.sopt.at.ui.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNavigation() {

    composable("search") { SearchScreen() }
}